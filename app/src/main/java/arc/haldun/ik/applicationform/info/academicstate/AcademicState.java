package arc.haldun.ik.applicationform.info.academicstate;

import androidx.annotation.NonNull;

public class AcademicState {

    private final First primarySchool;
    private First middleSchool;
    private High highSchool;
    private High university;
    private High master;

    public AcademicState(First primarySchool) {
        this.primarySchool = primarySchool;
    }

    public AcademicState(First primarySchool, First middleSchool, High highSchool, High university,
                         High master) {
        this.primarySchool = primarySchool;
        this.middleSchool = middleSchool;
        this.highSchool = highSchool;
        this.university = university;
        this.master = master;
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

    @NonNull
    @Override
    public String toString() {

        String classString;

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("İLKOKUL:").append("\n")
                .append("Adı: ").append(primarySchool.getName()).append("\n")
                .append("Başlama Tarihi: ").append(primarySchool.getStartDate()).append("\n")
                .append("Bitirme Tarihi: ").append(primarySchool.getEndDate()).append("\n")
                .append("Yeri: ").append(primarySchool.getRegion());

        stringBuilder.append("\n").append("ORTAOKUL:").append("\n")
                .append("Adı: ").append(middleSchool.getName()).append("\n")
                .append("Başlama Tarihi: ").append(middleSchool.getName()).append("\n")
                .append("Bitirme Tarihi: ").append(middleSchool.getName()).append("\n")
                .append("Yeri: ").append(middleSchool.getName());

        stringBuilder.append("\n").append("LİSE:").append("\n")
                .append("Adı: ").append(highSchool.getName()).append("\n")
                .append("Başlama Tarihi: ").append(highSchool.getName()).append("\n")
                .append("Bitirme Tarihi: ").append(highSchool.getName()).append("\n")
                .append("Yeri: ").append(highSchool.getName()).append("\n")
                .append("Derecesi: ").append(highSchool.getDegree()).append("\n")
                .append("Bölümü: ").append(highSchool.getBranch());

        stringBuilder.append("\n").append("ÜNİVERSİTE:").append("\n")
                .append("Adı: ").append(university.getName()).append("\n")
                .append("Başlama Tarihi: ").append(university.getName()).append("\n")
                .append("Bitirme Tarihi: ").append(university.getName()).append("\n")
                .append("Yeri: ").append(university.getName()).append("\n")
                .append("Derecesi: ").append(university.getDegree()).append("\n")
                .append("Bölümü: ").append(university.getBranch());

        stringBuilder.append("\n").append("DOKTORA:").append("\n")
                .append("Adı: ").append(master.getName()).append("\n")
                .append("Başlama Tarihi: ").append(master.getName()).append("\n")
                .append("Bitirme Tarihi: ").append(master.getName()).append("\n")
                .append("Yeri: ").append(master.getName()).append("\n")
                .append("Derecesi: ").append(master.getDegree()).append("\n")
                .append("Bölümü: ").append(master.getBranch());

        return stringBuilder.toString();
    }
}























