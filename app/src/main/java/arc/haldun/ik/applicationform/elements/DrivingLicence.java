package arc.haldun.ik.applicationform.elements;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import arc.haldun.ik.exceptions.MissingInformationException;

public class DrivingLicence {

    private boolean hasLicence;
    private String receiptDate, licenceClass;

    public DrivingLicence() {
        this.hasLicence = false;
    }

    public DrivingLicence(@NotNull String receiptDate, @NotNull String licenceClass) throws MissingInformationException {

        if (receiptDate.isEmpty() || licenceClass.isEmpty()) {
            throw new MissingInformationException("Ehliyet Veriliş tarihi", "Ehliyet Sınıfı");
        }

        this.receiptDate = receiptDate;
        this.licenceClass = licenceClass;
    }

    public boolean hasLicence() {
        return hasLicence;
    }

    public String getReceiptDate() {
        return this.receiptDate;
    }

    public String getLicenceClass() {
        return licenceClass;
    }

    @NonNull
    @Override
    public String toString() {

        String classString;

        if (hasLicence) {

            classString = "Veriliş Tarihi: " + receiptDate + " " +
                        "Sınıfı: " + licenceClass;
        } else {
            classString = "Yok";
        }

        return classString;
    }
}
