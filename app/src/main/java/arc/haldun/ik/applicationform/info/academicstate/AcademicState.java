package arc.haldun.ik.applicationform.info.academicstate;

public class AcademicState {

    private final First primarySchool;
    private First middleSchool;
    private High highSchool;
    private High university;
    private High master;

    public AcademicState(First primarySchool) {
        this.primarySchool = primarySchool;
    }

    public First getPrimarySchool() {
        return primarySchool;
    }

    public First getMiddleSchool() {
        return middleSchool;
    }

    public void setMiddleSchool(First middleSchool) {
        this.middleSchool = middleSchool;
    }

    public High getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(High highSchool) {
        this.highSchool = highSchool;
    }

    public High getUniversity() {
        return university;
    }

    public void setUniversity(High university) {
        this.university = university;
    }

    public High getMaster() {
        return master;
    }

    public void setMaster(High master) {
        this.master = master;
    }
}