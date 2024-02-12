package youtubetutorials.NativeSQL;

public class EmployeeStatisticsInfo {

    private Long totalNoOfEmployees;
    private Long totalDistnctNoOfEmployees;

    private Double maxSalaryOfEmployee;
    private Double avgSalaryOfEmployees;
    private Double sumOfSalryOfEmployees;

    public EmployeeStatisticsInfo(Long totalNoOfEmployees, Long totalDistnctNoOfEmployees, Double maxSalaryOfEmployee,
            Double avgSalaryOfEmployees, Double sumOfSalryOfEmployees) {
        super();
        this.totalNoOfEmployees = totalNoOfEmployees;
        this.totalDistnctNoOfEmployees = totalDistnctNoOfEmployees;
        this.maxSalaryOfEmployee = maxSalaryOfEmployee;
        this.avgSalaryOfEmployees = avgSalaryOfEmployees;
        this.sumOfSalryOfEmployees = sumOfSalryOfEmployees;
    }

    public Long getTotalNoOfEmployees() {
        return totalNoOfEmployees;
    }

    public Long getTotalDistnctNoOfEmployees() {
        return totalDistnctNoOfEmployees;
    }

    public Double getMaxSalaryOfEmployee() {
        return maxSalaryOfEmployee;
    }

    public Double getAvgSalaryOfEmployees() {
        return avgSalaryOfEmployees;
    }

    public Double getSumOfSalryOfEmployees() {
        return sumOfSalryOfEmployees;
    }

    @Override
    public String toString() {
        return "EmployeeStatisticsInfo [totalNoOfEmployees=" + totalNoOfEmployees + ", totalDistnctNoOfEmployees="
                + totalDistnctNoOfEmployees + ", maxSalaryOfEmployee=" + maxSalaryOfEmployee + ", avgSalaryOfEmployees="
                + avgSalaryOfEmployees + ", sumOfSalryOfEmployees=" + sumOfSalryOfEmployees + "]";
    }
}