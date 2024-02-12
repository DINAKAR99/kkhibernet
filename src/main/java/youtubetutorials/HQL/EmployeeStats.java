package youtubetutorials.HQL;

public class EmployeeStats {
    @Override
    public String toString() {
        return "EmployeeStats [count=" + count + ", summ=" + summ + ", avgg=" + avgg + ", minn=" + minn + ", maxx="
                + maxx + "]";
    }

    public EmployeeStats(Long count, Double summ, Double avgg, Double minn, Double maxx) {
        this.count = count;
        this.summ = summ;
        this.avgg = avgg;
        this.minn = minn;
        this.maxx = maxx;
    }

    private Long count;
    private Double summ;
    private Double avgg;
    private Double minn;
    private Double maxx;

    public Long getCount() {
        return count;
    }

    public Double getSumm() {
        return summ;
    }

    public Double getAvgg() {
        return avgg;
    }

    public Double getMinn() {
        return minn;
    }

    public Double getMaxx() {
        return maxx;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setSumm(Double summ) {
        this.summ = summ;
    }

    public void setAvgg(Double avgg) {
        this.avgg = avgg;
    }

    public void setMinn(Double minn) {
        this.minn = minn;
    }

    public void setMaxx(Double maxx) {
        this.maxx = maxx;
    }

}
