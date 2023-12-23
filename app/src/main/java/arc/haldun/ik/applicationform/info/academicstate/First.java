package arc.haldun.ik.applicationform.info.academicstate;

/**
 * This class represents primary school and middle school
 */

public class First {

    private final String startDate;
    private final String endDate;
    private final String name;
    private final String region;

    public First(String startDate, String endDate, String name, String region) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.region = region;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }
}
