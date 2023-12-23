package arc.haldun.ik.applicationform.info;

import androidx.annotation.NonNull;

public class MilitaryState {

    private boolean hasDone;

    private String entryDate;
    private String dischargeDate;
    private String dutyArea;
    private String rank;

    public MilitaryState(boolean hasDone) {

        if (!hasDone) {
            this.hasDone = false;
        }
    }

    public MilitaryState(String entryDate, String dischargeDate, String dutyArea, String rank) {
        this.hasDone = true;
        this.entryDate = entryDate;
        this.dischargeDate = dischargeDate;
        this.dutyArea = dutyArea;
        this.rank = rank;
    }

    public boolean isHasDone() {
        return hasDone;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public String getDutyArea() {
        return dutyArea;
    }

    public String getRank() {
        return rank;
    }

    @NonNull
    @Override
    public String toString() {

        String classString = "Askerlik Durumu:\n";

        if (hasDone) {


            classString += "\t";
            classString += "Yaptı";
            classString += "\n";

            classString += "\t";
            classString += "Giriş Tarihi: " + entryDate;
            classString += "\n";

            classString += "\t";
            classString += "Terhis Tarihi: " + dischargeDate;
            classString += "\n";

            classString += "\t";
            classString += "Görev Yeri: " + dutyArea;
            classString += "\n";

            classString += "\t";
            classString += "Rütbesi: " + rank;

        } else {

            classString += "\t";
            classString += "Yapmadı";
        }

        return classString;
    }
}
