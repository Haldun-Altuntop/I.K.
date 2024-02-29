package arc.haldun.ik.applicationform.info;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import arc.haldun.ik.applicationform.elements.BloodType;
import arc.haldun.ik.applicationform.elements.Children;
import arc.haldun.ik.applicationform.elements.DrivingLicence;
import arc.haldun.ik.exceptions.MissingInformationException;

public class PersonalInformation {

    private String id, name, surname, registrationProvince, bornPlace, birthDate, motherName,
                fatherName, job, sskNo, email, phoneNumber, homePhoneNumber, livingAddress;

    private BloodType bloodType;
    private Children children;
    private DrivingLicence drivingLicence;

    public PersonalInformation(String id, String name, String surname, String registrationProvince,
                               String bornPlace, String birthDate, String motherName,
                               String fatherName, String job, String sskNo, String email,
                               String phoneNumber, String homePhoneNumber,
                               String bloodType, String bloodTypeRh, int son, int daughter,
                               DrivingLicence drivingLicence,
                               String livingAddress) {

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.registrationProvince = registrationProvince;
        this.bornPlace = bornPlace;
        this.birthDate = birthDate;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.job = job;
        this.sskNo = sskNo;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.homePhoneNumber = homePhoneNumber;
        this.livingAddress = livingAddress;

        this.drivingLicence = drivingLicence;
        this.bloodType = new BloodType(bloodType, bloodTypeRh);
        this.children = new Children(son, daughter);
    }

    @Deprecated() // Driving licence input was changed. Send driving licence infos as object.
    public PersonalInformation(String id, String name, String surname, String registrationProvince,
                               String bornPlace, String birthDate, String motherName,
                               String fatherName, String job, String sskNo, String email,
                               String phoneNumber, String homePhoneNumber,
                               String bloodType, String bloodTypeRh, int son, int daughter,
                               String drivingLicenceReceptionDate, String drivingLicenceClass,
                               String livingAddress) throws MissingInformationException {

        if (drivingLicenceReceptionDate.equals("") && drivingLicenceClass.equals("")) {

            this.drivingLicence = new DrivingLicence();

        } else {

            this.drivingLicence = new DrivingLicence(drivingLicenceReceptionDate, drivingLicenceClass);

        }

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.registrationProvince = registrationProvince;
        this.bornPlace = bornPlace;
        this.birthDate = birthDate;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.job = job;
        this.sskNo = sskNo;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.homePhoneNumber = homePhoneNumber;
        this.livingAddress = livingAddress;

        this.bloodType = new BloodType(bloodType, bloodTypeRh);
        this.children = new Children(son, daughter);
    }

    @NonNull
    @Override
    public String toString() {

        String classString;

        classString = "T.C. Kimlik No: " + id + "\n" +
                "Adı: " + name + "\n" +
                "Soyadı: " + surname + "\n" +
                "Nüfusa Kayıtlı Olduğu İl: " + registrationProvince + "\n" +
                "Doğum Yeri: " + bornPlace + "\n" +
                "Doğum Tarihi: " + birthDate + "\n" +
                "Anne Adı: " + motherName + "\n" +
                "Baba Adı: " + fatherName + "\n" +
                "Mesleği: " + job + "\n" +
                "SSK No: " + sskNo + "\n" +
                "E Posta: " + email + "\n" +
                "Telefon Numarası: " + phoneNumber + "\n" +
                "Ev Telefonu Numarası: " + homePhoneNumber + "\n" +
                "İkametgah Adresi: " + livingAddress + "\n" +
                "Kan Grubu: " + bloodType.toString() + "\n" +
                "Çocukları: " + children.toString() + "\n" +
                "Ehliyet: " + drivingLicence.toString();

        return classString;
    }

    public void checkValidity() throws MissingInformationException {

        ArrayList<String> missingFields = new ArrayList<>();

        if (name.isEmpty()) missingFields.add("name");
        if (surname.isEmpty()) missingFields.add("surname");
        if (registrationProvince.isEmpty()) missingFields.add("registrationProvince");
        if (bornPlace.isEmpty()) missingFields.add("bornPlace");
        if (birthDate.isEmpty()) missingFields.add("birthDate");
        if (motherName.isEmpty()) missingFields.add("motherName");
        if (fatherName.isEmpty()) missingFields.add("fatherName");
        if (job.isEmpty()) missingFields.add("job");
        if (sskNo.isEmpty()) missingFields.add("sskNo");
        if (email.isEmpty()) missingFields.add("email");
        if (phoneNumber.isEmpty()) missingFields.add("phoneNumber");
        if (homePhoneNumber.isEmpty()) missingFields.add("homePhoneNumber");
        if (livingAddress.isEmpty()) missingFields.add("livingAddress");
        if (!bloodType.isValid()) missingFields.add("bloodType");
        if (!children.isValid()) missingFields.add("children");
        if (!drivingLicence.isValid()) missingFields.add("drivingLicence");

        // Check missing fields.
        if (missingFields.size() > 0)
            throw new MissingInformationException(
                    missingFields.toArray(missingFields.toArray(new String[0])));
    }

    private boolean isValid() {

        return id != null && !name.isEmpty() && !surname.isEmpty() && !registrationProvince.isEmpty()
                && !bornPlace.isEmpty() && !birthDate.isEmpty() && !motherName.isEmpty()
                && !fatherName.isEmpty() && !job.isEmpty() && !sskNo.isEmpty() && !email.isEmpty()
                && !phoneNumber.isEmpty() && !homePhoneNumber.isEmpty() && !livingAddress.isEmpty()
                && bloodType.isValid() && children.isValid() && drivingLicence.isValid();
    }
}
