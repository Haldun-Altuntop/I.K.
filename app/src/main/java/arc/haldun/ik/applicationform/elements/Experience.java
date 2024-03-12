package arc.haldun.ik.applicationform.elements;

import java.util.ArrayList;
import java.util.Date;

import arc.haldun.ik.exceptions.MissingInformationException;

public class Experience {

    private String company;
    private String startingDate, quittingDate;
    private String reference;
    private String position;
    private String salary;
    private String causeOfQuit;

    private Experience() {

    }

    public Experience(String company, String startingDate, String quittingDate, String reference,
                      String position, String salary, String causeOfQuit) {
        this.company = company;
        this.startingDate = startingDate;
        this.quittingDate = quittingDate;
        this.reference = reference;
        this.position = position;
        this.salary = salary;
        this.causeOfQuit = causeOfQuit;
    }

    public static Experience emptyInstance() {
        return new Experience();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getQuittingDate() {
        return quittingDate;
    }

    public void setQuittingDate(String quittingDate) {
        this.quittingDate = quittingDate;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCauseOfQuit() {
        return causeOfQuit;
    }

    public void setCauseOfQuit(String causeOfQuit) {
        this.causeOfQuit = causeOfQuit;
    }

    /**
     * Checks the validity of the Experience.
     * @throws MissingInformationException Thrown if experience is invalid.
     */
    public void checkValidity() throws MissingInformationException {

        ArrayList<String> missingFields = new ArrayList<>();

        if (company == null || company.isEmpty()) missingFields.add("Firma");
        if (startingDate == null || startingDate.isEmpty()) missingFields.add("Başlama tarihi");
        if (quittingDate == null || quittingDate.isEmpty()) missingFields.add("Ayrılma tarihi");
        if (reference == null || reference.isEmpty()) missingFields.add("İlk Amiri");
        if (position == null || position.isEmpty()) missingFields.add("Pozisyon");
        if (salary == null || salary.isEmpty()) missingFields.add("Net ücret");
        if (causeOfQuit == null || causeOfQuit.isEmpty()) missingFields.add("Ayrılma nedeni");

        if (missingFields.size() > 0)
            throw new MissingInformationException(missingFields.toArray(new String[0]));
    }
}
