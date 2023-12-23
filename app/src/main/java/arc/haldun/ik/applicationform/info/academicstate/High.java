package arc.haldun.ik.applicationform.info.academicstate;

/**
 * This class represents high school, university and master
 */

public class High extends First {

    private final String branch;
    private final String degree;

    public High(String startDate, String endDate, String name, String region, String branch, String degree) {
        super(startDate, endDate, name, region);

        this.branch = branch;
        this.degree = degree;
    }

    public String getBranch() {
        return branch;
    }

    public String getDegree() {
        return degree;
    }
}
