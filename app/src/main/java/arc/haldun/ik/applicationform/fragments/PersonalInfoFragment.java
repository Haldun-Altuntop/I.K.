package arc.haldun.ik.applicationform.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import arc.haldun.ik.R;
import arc.haldun.ik.applicationform.elements.DrivingLicence;
import arc.haldun.ik.exceptions.MissingInformationException;
import arc.haldun.ik.applicationform.info.PersonalInformation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Views
    private EditText et_id, et_name, et_surname, et_registrationProvince, et_bornPlace,
            et_birthDate, et_motherName, et_fatherName, et_job, et_bloodType, et_bloodTypeRh,
            et_sskNo, et_email, et_phoneNumber, et_homePhoneNumber, et_son, et_daughter,
            et_drivingLicenceReceiptDate, et_drivingLicenceClass, et_livingAddress;

    private CheckBox cb_drivingLicence;

    // Other variables
    private boolean hasDrivingLicence;

    public PersonalInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalInfoFragment newInstance(String param1, String param2) {
        PersonalInfoFragment fragment = new PersonalInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personal_info, container, false);

        initViews(view); // Init fragment views

        // Inflate the layout for this fragment
        return view;
    }

    public PersonalInformation getPersonalInfo() throws MissingInformationException {

        String id = et_id.getText().toString();
        String name = et_name.getText().toString();
        String surname = et_surname.getText().toString();
        String registrationProvince = et_registrationProvince.getText().toString();
        String bornPlace = et_bornPlace.getText().toString();
        String birthDate = et_birthDate.getText().toString();
        String motherName = et_motherName.getText().toString();
        String fatherName = et_fatherName.getText().toString();
        String job = et_job.getText().toString();
        String sskNo = et_sskNo.getText().toString();
        String email = et_email.getText().toString();
        String phoneNumber = et_phoneNumber.getText().toString();
        String homePhoneNumber = et_homePhoneNumber.getText().toString();
        String bloodType = et_bloodType.getText().toString();
        String bloodTypeRh = et_bloodTypeRh.getText().toString();
        int son = Integer.parseInt(et_son.getText().toString());
        int daughter = Integer.parseInt(et_daughter.getText().toString());

        String livingAddress = et_livingAddress.getText().toString();

        PersonalInformation personalInformation = new PersonalInformation(id, name, surname,
                registrationProvince, bornPlace, birthDate, motherName, fatherName, job, sskNo,
                email, phoneNumber, homePhoneNumber, bloodType, bloodTypeRh, son, daughter,
                getDrivingLicence(), livingAddress);

        personalInformation.checkValidity();

        return personalInformation;

    }

    private DrivingLicence getDrivingLicence() throws MissingInformationException {

        DrivingLicence drivingLicence;

        // Check has driving licence
        if (hasDrivingLicence) {

            String drivingLicenceReceiptDate = et_drivingLicenceReceiptDate.getText().toString();
            String drivingLicenceClass = et_drivingLicenceClass.getText().toString();

            drivingLicence = new DrivingLicence(drivingLicenceReceiptDate, drivingLicenceClass);
        } else {
            drivingLicence = new DrivingLicence();
        }

        return drivingLicence;
    }

    private void onHasDrivingLicenceChange(CompoundButton compoundButton, boolean b) {

        // Set public variable
        hasDrivingLicence = b;

        // Set driving licence ino fields' state
        et_drivingLicenceReceiptDate.setEnabled(b);
        et_drivingLicenceClass.setEnabled(b);

        // Reset fields on toggle is on
        if (!b) {
            et_drivingLicenceReceiptDate.setText(null);
            et_drivingLicenceClass.setText(null);
        }
    }

    private void initViews(View container) {

        // Input fields

        et_id = container.findViewById(R.id.fragment_personal_info_et_id);

        et_name = container.findViewById(R.id.fragment_personal_info_et_name);

        et_surname = container.findViewById(R.id.fragment_personal_info_et_surname);

        et_registrationProvince = container
                .findViewById(R.id.fragment_personal_info_et_registration_province);

        et_bornPlace = container.findViewById(R.id.fragment_personal_info_et_born_place);

        et_birthDate = container.findViewById(R.id.fragment_personal_info_et_birth_date);

        et_motherName = container.findViewById(R.id.fragment_personal_info_et_mother_name);

        et_fatherName = container.findViewById(R.id.fragment_personal_info_et_father_name);

        et_job = container.findViewById(R.id.fragment_personal_info_et_job);

        et_bloodType = container.findViewById(R.id.fragment_personal_info_et_blood_type);

        et_bloodTypeRh = container.findViewById(R.id.fragment_personal_info_et_blood_type_rh);

        et_sskNo = container.findViewById(R.id.fragment_personal_info_et_ssk_no);

        et_email = container.findViewById(R.id.fragment_personal_info_et_email);

        et_phoneNumber = container.findViewById(R.id.fragment_personal_info_et_phone_number);

        et_homePhoneNumber = container.findViewById(R.id.fragment_personal_info_et_home_phone_number);

        et_son = container.findViewById(R.id.fragment_personal_info_et_son);

        et_daughter = container.findViewById(R.id.fragment_personal_info_et_daughter);

        et_drivingLicenceReceiptDate = container
                .findViewById(R.id.fragment_personal_info_et_driving_licence_got_date);

        et_drivingLicenceClass = container
                .findViewById(R.id.fragment_personal_info_et_driving_licence_class);

        et_livingAddress = container.findViewById(R.id.fragment_personal_info_et_living_address);


        // Selectables

        cb_drivingLicence = container.findViewById(R.id.fragment_personal_info_check_box_driving_licence);
        cb_drivingLicence.setOnCheckedChangeListener(this::onHasDrivingLicenceChange);

    }

    @Override
    public String collectInformationAsString() throws MissingInformationException {
        return getPersonalInfo().toString();
    }
}