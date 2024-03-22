package arc.haldun.ik.applicationform.elements;

import androidx.annotation.NonNull;

public class Reference {

    private String nameSurname;
    private String job;
    private String company;
    private String phone;
    private String relativityDegree;

    public Reference() {
        nameSurname = "";
        job = "";
        company = "";
        phone = "";
        relativityDegree = "";
    }

    public Reference(String nameSurname, String job, String company, String phone, String relativityDegree) {
        this.nameSurname = nameSurname;
        this.job = job;
        this.company = company;
        this.phone = phone;
        this.relativityDegree = relativityDegree;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRelativityDegree() {
        return relativityDegree;
    }

    public void setRelativityDegree(String relativityDegree) {
        this.relativityDegree = relativityDegree;
    }

    @NonNull
    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Adı Soyadı: ").append(nameSurname).append("\n");
        stringBuilder.append("Mesleği: ").append(job).append("\n");
        stringBuilder.append("Telefon: ").append(phone).append("\n");
        stringBuilder.append("Firma: ").append(company).append("\n");
        stringBuilder.append("Yakınlık Derecesi: ").append(relativityDegree).append("\n");

        return stringBuilder.toString();
    }
}
