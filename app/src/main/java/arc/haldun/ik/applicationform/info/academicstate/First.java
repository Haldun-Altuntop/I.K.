package arc.haldun.ik.applicationform.info.academicstate;

/**
 * This class represents primary school and middle school
 */

public class First {

    private String startDate;
    private String endDate;
    private String name;
    private String region;

    public First(String startDate, String endDate, String name, String region) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.region = region;

        checkFields();
    }

    /**
     * If fields are null, makes them empty
     */
    protected void checkFields() {

        if (startDate == null) startDate = "";
        if (endDate == null) endDate = "";
        if (name == null) name = "";
        if (region == null) region = "";

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
