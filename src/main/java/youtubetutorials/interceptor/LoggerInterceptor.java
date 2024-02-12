package youtubetutorials.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import youtubetutorials.NativeSQL.Person;
import youtubetutorials.layer.Employee;

;

public class LoggerInterceptor extends EmptyInterceptor {
    private static final Logger logger = LogManager.getLogger(InterceptorEngine.class);

    @Override
    public boolean onLoad(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types)
            throws CallbackException {
        logger.info("onLoad method is called...");
        if (entity instanceof Employee) {
            Employee Employee = (Employee) entity;
            logger.info(Employee);
        }

        return super.onLoad(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onSave(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types)
            throws CallbackException {
        logger.info("onSave method is called...");
        if (entity instanceof Employee) {
            Employee Employee = (Employee) entity;
            logger.info(Employee);
        }

        return super.onSave(entity, id, state, propertyNames, types);
    }

}
