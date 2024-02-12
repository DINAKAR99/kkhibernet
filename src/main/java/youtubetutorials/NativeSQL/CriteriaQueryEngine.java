package youtubetutorials.NativeSQL;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;

import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import youtubetutorials.HibernateUtil;
import youtubetutorials.layer.Employee;
import youtubetutorials.layer.EmployeeDTO;

public class CriteriaQueryEngine {
    public static void selectingEntity(SessionFactory s1) {
        try (Session session = s1.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(root);

            criteriaQuery.where(builder.equal(root.get("empid"), 2));

            Query<Employee> query = session.createQuery(criteriaQuery);
            List<Employee> empList = query.list();
            empList.forEach(System.out::println);
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    public static void SingleAttributeSelection(SessionFactory s1) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(root.get("Empname"));

            Query<String> createQuery = session.createQuery(criteriaQuery);
            List<String> empNameList = createQuery.getResultList();
            empNameList.forEach(System.out::println);
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    public static void MultipleAttributeSelection(SessionFactory s1) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
            Root<Employee> root = criteriaQuery.from(Employee.class);

            Path<Object> employeeNamePath = root.get("Empname");

            Path<Object> salaryPath = root.get("salary");

            // criteriaQuery.select(builder.array(employeeNamePath, salaryPath));
            // can also use multi select
            criteriaQuery.multiselect(employeeNamePath, salaryPath);

            Query<Object[]> query = session.createQuery(criteriaQuery);
            List<Object[]> list = query.list();
            for (Object[] objects : list) {
                System.out.println("Employee Name:" + objects[0]);

                System.out.println("Salary:" + objects[1]);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    public static void getDTOobject(SessionFactory s1) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<EmployeeDTO> criteriaQuery = builder.createQuery(EmployeeDTO.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);

            Path<Object> employeeNamePath = root.get("Empname");

            Path<Object> salaryPath = root.get("salary");

            criteriaQuery.select(builder.construct(EmployeeDTO.class, employeeNamePath, salaryPath));

            Query<EmployeeDTO> query = session.createQuery(criteriaQuery);
            List<EmployeeDTO> list = query.list();
            for (EmployeeDTO objects : list) {
                System.out.println(objects);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    public static void getTuple(SessionFactory s1) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);

            Path<Object> employeeNamePath = root.get("Empname");

            Path<Object> salaryPath = root.get("salary");

            criteriaQuery.multiselect(employeeNamePath, salaryPath);
            Query<Tuple> query = session.createQuery(criteriaQuery);
            List<Tuple> list = query.list();
            for (Tuple t1 : list) {
                String employeeName = (String) t1.get(employeeNamePath);

                Double sal = (Double) t1.get(salaryPath);
                System.out.println(employeeName + "\t" + sal);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    public static void MultipleRoots(SessionFactory s1) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
            Root<Person> PersonRoot = criteriaQuery.from(Person.class);
            Root<Partner> PartnerRoot = criteriaQuery.from(Partner.class);

            criteriaQuery.multiselect(PersonRoot, PartnerRoot);

            Predicate personRestriction = builder.and(builder.equal(PersonRoot.get("occupation"), "mechanic"),
                    builder.isNotEmpty(PersonRoot.get("phones")));

            Predicate partnerRestriction = builder.and(builder.like(PartnerRoot.get("name"), "%ang%"),
                    builder.equal(PartnerRoot.get("version"), 10));

            criteriaQuery.where(builder.and(personRestriction, partnerRestriction));

            Query<Tuple> query = session.createQuery(criteriaQuery);
            List<Tuple> list = query.list();

            for (Tuple t1 : list) {
                Person person = (Person) t1.get(0);
                if (person != null) {
                    System.out.println(person);
                    List<Phone> phones = person.getPhones();
                    for (Phone phone : phones) {
                        System.out.println(phone);
                    }
                }

                Partner partner = (Partner) t1.get(1);
                System.out.println(partner);
            }
        }

        catch (

        HibernateException e) {
            e.printStackTrace();
        }

    }

    public static void JoinExample(SessionFactory s1) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Phone> criteriaQuery = builder.createQuery(Phone.class);
            Root<Phone> root = criteriaQuery.from(Phone.class);
            root.join("person_linked");
            Query<Phone> query = session.createQuery(criteriaQuery);

            criteriaQuery.where(builder.isNotEmpty(root.get("calls")));
            List<Phone> list = query.list();

            for (Phone p1 : list) {
                System.out.println("Phone Details::::::::::::::::::");
                System.out.println(p1);
                System.out.println("Person Details::::::::::::");
                Person person = p1.getPerson_linked();
                System.out.println(person);
                List<Call> calls = p1.getCalls();
                System.out.println("Phone call details::::::::::");
                for (Call call : calls) {
                    System.out.println(call);
                }

            }
        }

        catch (

        HibernateException e) {
            e.printStackTrace();
        }

    }

    public static void EagerLoading(SessionFactory s1) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Phone> criteriaQuery = builder.createQuery(Phone.class);
            Root<Phone> root = criteriaQuery.from(Phone.class);
            root.fetch("person_linked");
            root.fetch("calls");
            Query<Phone> query = session.createQuery(criteriaQuery);

            criteriaQuery.where(builder.isNotEmpty(root.get("calls")));
            List<Phone> list = query.list();

            for (Phone p1 : list) {
                System.out.println("Phone Details::::::::::::::::::");
                System.out.println(p1);
                System.out.println("Person Details::::::::::::");
                Person person = p1.getPerson_linked();
                System.out.println(person);
                List<Call> calls = p1.getCalls();
                System.out.println("Phone call details::::::::::");
                for (Call call : calls) {
                    System.out.println(call);
                }

            }
        }

        catch (

        HibernateException e) {
            e.printStackTrace();
        }

    }

    public static void CriteriaQueryParams(SessionFactory s1) {

        String nickName = "abrar";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
            Root<Person> root = criteriaQuery.from(Person.class);

            ParameterExpression<String> NickNameParam = builder.parameter(String.class);

            criteriaQuery.where(builder.equal(root.get("name"), NickNameParam));

            Query<Person> query = session.createQuery(criteriaQuery);
            query.setParameter(NickNameParam, nickName);

            List<Person> list = query.list();

            for (Person p1 : list) {
                System.out.println(p1);

            }
        }

        catch (

        HibernateException e) {
            e.printStackTrace();
        }

    }

    public static void getTotalNoOfEmployees(SessionFactory s1) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            JpaCriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);

            JpaRoot<Employee2> root = query.from(Employee2.class);

            query.select(criteriaBuilder.count(root));

            Long singleResult = session.createQuery(query).getSingleResult();

            System.out.println("tot no of emp :: " + singleResult);
        }

        catch (

        HibernateException e) {
            e.printStackTrace();
        }

    }

    public static void getMaxSalary(SessionFactory s1) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            JpaCriteriaQuery<Double> query = criteriaBuilder.createQuery(Double.class);

            JpaRoot<Employee2> root = query.from(Employee2.class);

            query.select(criteriaBuilder.max(root.get("salary")));

            Double singleResult = session.createQuery(query).getSingleResult();

            System.out.println("MAX SALARY AMONG EMPLOYEES IS  :: " + singleResult);
        }

        catch (

        HibernateException e) {
            e.printStackTrace();
        }

    }

    public static void getAvgSalary(SessionFactory s1) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            JpaCriteriaQuery<Double> query = criteriaBuilder.createQuery(Double.class);

            JpaRoot<Employee2> root = query.from(Employee2.class);

            query.select(criteriaBuilder.avg(root.get("salary")));

            Double singleResult = session.createQuery(query).getSingleResult();

            System.out.println("AVG SALARY AMONG EMPLOYEES IS  :: " + singleResult);
        }

        catch (

        HibernateException e) {
            e.printStackTrace();
        }

    }

    public static void getSumOfSalaries(SessionFactory s1) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            JpaCriteriaQuery<Double> query = criteriaBuilder.createQuery(Double.class);

            JpaRoot<Employee2> root = query.from(Employee2.class);

            query.select(criteriaBuilder.sum(root.get("salary")));

            Double singleResult = session.createQuery(query).getSingleResult();

            System.out.println("SUM OF  SALARY AMONG EMPLOYEES IS  :: " + singleResult);
        }

        catch (

        HibernateException e) {
            e.printStackTrace();
        }

    }

    public static void getDistinctCountOfEmployees(SessionFactory s1) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            JpaCriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);

            JpaRoot<Employee2> root = query.from(Employee2.class);

            query.select(criteriaBuilder.countDistinct(root));

            Long singleResult = session.createQuery(query).getSingleResult();

            System.out.println("DISTINCT EMPLOYEES ARE  :: " + singleResult);
        }

        catch (

        HibernateException e) {
            e.printStackTrace();
        }

    }

    public static void AggregateFunctionsResultMapWithDTO(SessionFactory s1)

    {
        try (Session session = s1.openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<EmployeeStatisticsInfo> criteriaQuery = builder.createQuery(EmployeeStatisticsInfo.class);
            Root<Employee2> root = criteriaQuery.from(Employee2.class);

            Expression<Long> totalNoOfEmployees = builder.count(root);
            Expression<Long> totalDistnctNoOfEmployees = builder.countDistinct(root);
            Expression<Double> maxSalaryOfEmployee = builder.max(root.get("salary"));
            Expression<Double> avgSalaryOfEmployees = builder.avg(root.get("salary"));
            Expression<Double> sumOfSalryOfEmployees = builder.sum(root.get("salary"));
            criteriaQuery.select(builder.construct(EmployeeStatisticsInfo.class, totalNoOfEmployees,
                    totalDistnctNoOfEmployees, maxSalaryOfEmployee, avgSalaryOfEmployees, sumOfSalryOfEmployees));

            EmployeeStatisticsInfo singleResult = session.createQuery(criteriaQuery).getSingleResult();
            System.out.println(singleResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void FromandJoinExample(SessionFactory s1) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
            Root<Employee2> rootEmp = criteriaQuery.from(Employee2.class);
            Root<Department> rootDept = criteriaQuery.from(Department.class);

            criteriaQuery.multiselect(rootEmp, rootDept);
            criteriaQuery.where(builder.equal(rootEmp.get("department").get("id"), rootDept.get("id")));

            Query<Object[]> query = session.createQuery(criteriaQuery);

            List<Object[]> resultList = query.getResultList();
            for (Object[] objects : resultList) {
                Employee2 employee = (Employee2) objects[0];
                System.out.println(employee);
                Department department = (Department) objects[1];

                System.out.println(department);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void GropbyAndHaving(SessionFactory s1) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
            Root<Employee2> root = criteriaQuery.from(Employee2.class);

            criteriaQuery.multiselect(builder.count(root.get("name")), builder.sum(root.get("salary")),
                    root.get("department"));

            // criteriaQuery.groupBy(root.get("department"));
            // criteriaQuery.having(builder.greaterThan(builder.sum(root.get("salary")),
            // 200000.00));

            criteriaQuery.groupBy(root.get("department"));
            criteriaQuery.having(builder.greaterThan(builder.sum(root.get("salary")), 20000));
            Query<Object[]> query = session.createQuery(criteriaQuery);

            List<Object[]> resultList = query.getResultList();
            for (Object[] objects : resultList) {
                Department department = (Department) objects[2];
                System.out.println(department);
                long count = (Long) objects[0];
                System.out.println("Count:" + count);
                Double salarySum = (Double) objects[1];
                System.out.println("Total Salary:" + salarySum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void OrderBy(SessionFactory s1) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Employee2> criteriaQuery = builder.createQuery(Employee2.class);
            Root<Employee2> root = criteriaQuery.from(Employee2.class);
            criteriaQuery.select(root);
            // criteriaQuery.orderBy(builder.asc(root.get("salary")));
            // criteriaQuery.orderBy(builder.desc(root.get("salary")));
            criteriaQuery.orderBy(builder.asc(root.get("salary")));
            Query<Employee2> query = session.createQuery(criteriaQuery);
            List<Employee2> list = query.getResultList();
            for (Employee2 employee2 : list) {
                System.out.println(employee2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void getById(SessionFactory s1) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            long personId = 1L;

            // Person person = session.byId(Person.class).load(personId);
            Employee2 employee2 = session.byId(Employee2.class).load(1);

            if (employee2 != null) {
                System.out.println(employee2);
            } else {
                System.out.println("Person Doesn't Exist..");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void loadPersonById() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Optional<Person> optional = session.byId(Person.class).loadOptional(2);
            Optional<Employee2> optional = session.byId(Employee2.class).loadOptional(3);

            if (optional.isPresent()) {
                Employee2 person = optional.get();
                System.out.println(person);
            } else {
                System.out.println("Person Doesn't Exist..");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadBookByOptionalJava8NaturalId() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String naturalId = "900-9730228524";
            Optional<book> optional = session.byNaturalId(book.class).using("isbn", naturalId).loadOptional();
            if (optional.isPresent()) {
                book book = optional.get();
                System.out.println(book.getTitle() + "\t" + book.getAuthor().getName());
            } else {
                System.out.println("Information not found!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadbookByNaturalId() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String naturalId = "900-9730228524";
            book book = session.byNaturalId(book.class).using("isbn", naturalId).load();
            if (book != null) {
                System.out.println(book.getTitle() + "\t" + book.getAuthor().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadbookBySimpleNaturalId() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String naturalId = "900-9730228524";

            book book = session.bySimpleNaturalId(book.class).getReference(naturalId);
            if (book != null) {
                System.out.println(book.getTitle() + "\t" + book.getAuthor().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void refreshEntity() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Person1 person = session.byId(Person1.class).load(1L);

            session.doWork(connection -> {
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate("UPDATE Person1 SET name=UPPER(name)");
                }
            });

            session.refresh(person);
            System.out.println(person.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void BlobFetcher() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            long personId = 1L;
            Person2 p2 = session.get(Person2.class, 2);

            Blob image = p2.getProfilepicc();
            InputStream inputStream = image.getBinaryStream();
            Files.copy(inputStream, Paths.get("OutPic/second.jpg"),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void flushChecker(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        // Load an entity from the database
        Employee2 entity = session.get(Employee2.class, 2);

        // Print the original state
        System.out.println("Original State: " + entity.getName());

        // Modify the entity
        entity.setName("martin rocky ");

        // Explicitly flush to synchronize the change with the database
        session.flush();
        // Print the state after the flush
        System.out.println("State after Flush: " + entity.getName());

        // Continue with other operations if needed

        // Changes are visible in the database at this point, but not yet permanent

        // Commit the transaction to make the changes permanent

    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        // SingleAttributeSelection(sessionFactory);
        // MultipleAttributeSelection(sessionFactory);
        // getDTOobject(sessionFactory);
        // getTuple(sessionFactory);
        // MultipleRoots(sessionFactory);
        // JoinExample(sessionFactory);
        // EagerLoading(sessionFactory);
        // CriteriaQueryParams(sessionFactory);
        // getTotalNoOfEmployees(sessionFactory);
        // getMaxSalary(sessionFactory);
        // getAvgSalary(sessionFactory);
        // getDistinctCountOfEmployees(sessionFactory);
        // getSumOfSalaries(sessionFactory);
        // AggregateFunctionsResultMapWithDTO(sessionFactory);
        // FromandJoinExample(sessionFactory);
        // GropbyAndHaving(sessionFactory);
        // OrderBy(sessionFactory);
        // getById(sessionFactory);
        // loadPersonById();
        // loadbookByNaturalId();
        // refreshEntity();
        // BlobFetcher();
        // flushChecker(sessionFactory);

    }

}
