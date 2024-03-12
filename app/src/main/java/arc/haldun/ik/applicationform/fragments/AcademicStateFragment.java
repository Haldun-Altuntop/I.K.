package arc.haldun.ik.applicationform.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import arc.haldun.ik.R;
import arc.haldun.ik.applicationform.info.academicstate.AcademicState;
import arc.haldun.ik.applicationform.info.academicstate.First;
import arc.haldun.ik.applicationform.info.academicstate.High;
import arc.haldun.ik.exceptions.MissingInformationException;
import arc.haldun.ik.utility.EditTextUtility;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AcademicStateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AcademicStateFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Views
    private CheckBox cbPrimarySchool, cbMiddleSchool, cbHighSchool, cbUniversity, cbMaster;

    private EditText et_primarySchool_Start, et_primarySchool_End, et_primarySchool_Name,
            et_primarySchool_Region;

    private EditText et_middleSchool_Start, et_middleSchool_End, et_middleSchool_Name,
            et_middleSchool_Region;

    private EditText et_highSchool_Name, et_highSchool_Start, et_highSchool_End, et_highSchool_Degree,
            et_highSchool_Branch, et_highSchool_Region;

    private EditText et_university_Name, et_university_Start, et_university_End, et_university_Degree,
            et_university_Branch, et_university_Region;

    private EditText et_master_Name, et_master_Start, et_master_End, et_master_Degree,
            et_master_Branch, et_master_Region;

    // Other variables
    private boolean hasPrimarySchool, hasMiddleSchool, hasHighSchool, hasUniversity, hasMaster;

    public AcademicStateFragment() {

        // Constructor must be empty

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AcademicStateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AcademicStateFragment newInstance(String param1, String param2) {
        AcademicStateFragment fragment = new AcademicStateFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_academic_state, container, false);
        initViews(view);

        // Set all field's statue to false
        hasPrimarySchool = false;
        hasMiddleSchool = false;
        hasHighSchool = false;
        hasUniversity = false;
        hasMaster = false;
        updateFieldStatue();

        return view;
    }

    public AcademicState getAcademicState() throws MissingInformationException {

        ArrayList<String> missingFields = new ArrayList<>();

        AcademicState academicState;

        First primarySchool;
        First middleSchool;
        High highSchool;
        High university;
        High master;

        // Primary school
        String primarySchoolName = EditTextUtility.getTextFromEditText(et_primarySchool_Name);
        String primarySchoolStart = EditTextUtility.getTextFromEditText(et_primarySchool_Start);
        String primarySchoolEnd = EditTextUtility.getTextFromEditText(et_primarySchool_End);
        String primarySchoolRegion = EditTextUtility.getTextFromEditText(et_primarySchool_Region);

        // If primary school is checked and one of the areas is null throw exception
        if (hasPrimarySchool && (primarySchoolName.isEmpty() || primarySchoolStart.isEmpty() ||
                primarySchoolEnd.isEmpty() || primarySchoolRegion.isEmpty())) {

            missingFields.add("İlkokul Bilgileri");
        }

        // Init primary school
        primarySchool = new First(primarySchoolStart, primarySchoolEnd,
                primarySchoolName, primarySchoolRegion);

        // Middle school
        String middleSchoolName = EditTextUtility.getTextFromEditText(et_middleSchool_Name);
        String middleSchoolStart = EditTextUtility.getTextFromEditText(et_middleSchool_Start);
        String middleSchoolEnd = EditTextUtility.getTextFromEditText(et_middleSchool_End);
        String middleSchoolRegion = EditTextUtility.getTextFromEditText(et_middleSchool_Region);

        // If middle school is checked and one of the areas is null throw exception
        if (hasMiddleSchool && (middleSchoolName.isEmpty() || middleSchoolStart.isEmpty()
                || middleSchoolEnd.isEmpty() || middleSchoolRegion.isEmpty())) {

            missingFields.add("Ortaokul Bilgileri");

        }

        // Init middle school
        middleSchool = new First(middleSchoolStart, middleSchoolEnd,
                middleSchoolName, middleSchoolRegion);

        // High school
        String highSchoolName = EditTextUtility.getTextFromEditText(et_highSchool_Name);
        String highSchoolStart = EditTextUtility.getTextFromEditText(et_highSchool_Start);
        String highSchoolEnd = EditTextUtility.getTextFromEditText(et_highSchool_End);
        String highSchoolRegion = EditTextUtility.getTextFromEditText(et_highSchool_Region);
        String highSchoolDegree = EditTextUtility.getTextFromEditText(et_highSchool_Degree);
        String highSchoolBranch = EditTextUtility.getTextFromEditText(et_highSchool_Branch);

        // If high school is checked and one of the areas is null throw exception
        if (hasHighSchool && (highSchoolName.isEmpty() || highSchoolStart.isEmpty()
                || highSchoolEnd.isEmpty() || highSchoolRegion.isEmpty()
                || highSchoolDegree.isEmpty() || highSchoolBranch.isEmpty())) {

            missingFields.add("Lise Bilgileri");

        }

        // Init high school
        highSchool = new High(highSchoolStart, highSchoolEnd, highSchoolName,
                highSchoolRegion, highSchoolBranch, highSchoolDegree);

        // University
        String universityName = EditTextUtility.getTextFromEditText(et_university_Name);
        String universityStart =EditTextUtility.getTextFromEditText(et_university_Start);
        String universityEnd = EditTextUtility.getTextFromEditText(et_university_End);
        String universityRegion = EditTextUtility.getTextFromEditText(et_university_Region);
        String universityDegree = EditTextUtility.getTextFromEditText(et_university_Degree);
        String universityBranch = EditTextUtility.getTextFromEditText(et_university_Branch);

        // If university is checked and one of the areas is null throw exception
        if (hasUniversity && (universityName.isEmpty() || universityStart.isEmpty()
                || universityEnd.isEmpty() || universityRegion.isEmpty()
                || universityDegree.isEmpty() || universityBranch.isEmpty())) {

            missingFields.add("Üniversite Bilgileri");

        }

        // Init university
        university = new High(universityStart, universityEnd, universityName,
                universityRegion, universityBranch, universityDegree);

        // Master
        String masterName = EditTextUtility.getTextFromEditText(et_master_Name);
        String masterStart = EditTextUtility.getTextFromEditText(et_master_Start);
        String masterEnd = EditTextUtility.getTextFromEditText(et_master_End);
        String masterRegion = EditTextUtility.getTextFromEditText(et_master_Region);
        String masterDegree = EditTextUtility.getTextFromEditText(et_master_Degree);
        String masterBranch = EditTextUtility.getTextFromEditText(et_master_Branch);

        // If master is checked and one of the areas is null throw exception7
        if (hasMaster && (masterName.isEmpty() || masterStart.isEmpty() || masterEnd.isEmpty()
                || masterRegion.isEmpty() || masterDegree.isEmpty()|| masterBranch.isEmpty())) {

            missingFields.add("Lisansüstü Çalışmalar Bilgileri");

        }

        // Check missing fields
        if (missingFields.size() > 0)
            throw new MissingInformationException(missingFields.toArray(new String[0]));

        // Init master
        master = new High(masterStart, masterEnd, masterName,
                masterRegion, masterDegree, masterBranch);

        // Init academic state
        academicState = new AcademicState(primarySchool, middleSchool, highSchool,
                                                university, master);

        // Return result
        return academicState;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (buttonView.equals(cbPrimarySchool)) {
            hasPrimarySchool = isChecked;
        }

        if (buttonView.equals(cbMiddleSchool)) {
            hasMiddleSchool = isChecked;
        }

        if (buttonView.equals(cbHighSchool)) {
            hasHighSchool = isChecked;
        }

        if (buttonView.equals(cbUniversity)) {
            hasUniversity = isChecked;
        }

        if (buttonView.equals(cbMaster)) {
            hasMaster = isChecked;
        }

        updateFieldStatue();

    }

    /**
     * This code used for updating fields' enabled/disabled statue.
     */
    private void updateFieldStatue() {

        // Primary school
        et_primarySchool_Name.setEnabled(hasPrimarySchool);
        et_primarySchool_Start.setEnabled(hasPrimarySchool);
        et_primarySchool_End.setEnabled(hasPrimarySchool);
        et_primarySchool_Region.setEnabled(hasPrimarySchool);

        // Middle school
        et_middleSchool_Name.setEnabled(hasMiddleSchool);
        et_middleSchool_Start.setEnabled(hasMiddleSchool);
        et_middleSchool_End.setEnabled(hasMiddleSchool);
        et_middleSchool_Region.setEnabled(hasMiddleSchool);

        // High school
        et_highSchool_Name.setEnabled(hasHighSchool);
        et_highSchool_Start.setEnabled(hasHighSchool);
        et_highSchool_End.setEnabled(hasHighSchool);
        et_highSchool_Region.setEnabled(hasHighSchool);
        et_highSchool_Degree.setEnabled(hasHighSchool);
        et_highSchool_Branch.setEnabled(hasHighSchool);

        // University
        et_university_Name.setEnabled(hasUniversity);
        et_university_Start.setEnabled(hasUniversity);
        et_university_End.setEnabled(hasUniversity);
        et_university_Region.setEnabled(hasUniversity);
        et_university_Degree.setEnabled(hasUniversity);
        et_university_Branch.setEnabled(hasUniversity);

        // Master
        et_master_Name.setEnabled(hasMaster);
        et_master_Start.setEnabled(hasMaster);
        et_master_End.setEnabled(hasMaster);
        et_master_Region.setEnabled(hasMaster);
        et_master_Degree.setEnabled(hasMaster);
        et_master_Branch.setEnabled(hasMaster);

    }

    private void initViews(@NonNull View view) {

        // Primary school
        cbPrimarySchool = view.findViewById(R.id.fragment_academic_state_cb_primary_school);
        cbPrimarySchool.setOnCheckedChangeListener(this);

        et_primarySchool_Name = view.findViewById(R.id.fragment_academic_state_et_primary_school_name);
        et_primarySchool_Start = view.findViewById(R.id.fragment_academic_state_et_primary_school_start_date);
        et_primarySchool_End = view.findViewById(R.id.fragment_academic_state_et_primary_school_end_date);
        et_primarySchool_Region = view.findViewById(R.id.fragment_academic_state_et_primary_school_region);

        // Middle school
        cbMiddleSchool = view.findViewById(R.id.fragment_academic_state_cb_middle_school);
        cbMiddleSchool.setOnCheckedChangeListener(this);

        et_middleSchool_Name = view.findViewById(R.id.fragment_academic_state_et_middle_school_name);
        et_middleSchool_Start = view.findViewById(R.id.fragment_academic_state_et_middle_school_start_date);
        et_middleSchool_End = view.findViewById(R.id.fragment_academic_state_et_middle_school_end_date);
        et_middleSchool_Region = view.findViewById(R.id.fragment_academic_state_et_middle_school_region);

        // High school
        cbHighSchool = view.findViewById(R.id.fragment_academic_state_cb_high_school);
        cbHighSchool.setOnCheckedChangeListener(this);

        et_highSchool_Name = view.findViewById(R.id.fragment_academic_state_et_high_school_name);
        et_highSchool_Start = view.findViewById(R.id.fragment_academic_state_et_high_school_start_date);
        et_highSchool_End = view.findViewById(R.id.fragment_academic_state_et_high_school_end_date);
        et_highSchool_Region = view.findViewById(R.id.fragment_academic_state_et_high_school_region);
        et_highSchool_Degree = view.findViewById(R.id.fragment_academic_state_et_high_school_degree);
        et_highSchool_Branch = view.findViewById(R.id.fragment_academic_state_et_high_school_branch);

        // University
        cbUniversity = view.findViewById(R.id.fragment_academic_state_cb_university);
        cbUniversity.setOnCheckedChangeListener(this);

        et_university_Name = view.findViewById(R.id.fragment_academic_state_et_university_name);
        et_university_Start = view.findViewById(R.id.fragment_academic_state_et_university_start_date);
        et_university_End = view.findViewById(R.id.fragment_academic_state_et_university_end_date);
        et_university_Region = view.findViewById(R.id.fragment_academic_state_et_university_region);
        et_university_Degree = view.findViewById(R.id.fragment_academic_state_et_university_degree);
        et_university_Branch = view.findViewById(R.id.fragment_academic_state_et_university_branch);

        // Master
        cbMaster = view.findViewById(R.id.fragment_academic_state_cb_master);
        cbMaster.setOnCheckedChangeListener(this);

        et_master_Name = view.findViewById(R.id.fragment_academic_state_et_master_name);
        et_master_Start = view.findViewById(R.id.fragment_academic_state_et_master_start_date);
        et_master_End = view.findViewById(R.id.fragment_academic_state_et_master_end_date);
        et_master_Region = view.findViewById(R.id.fragment_academic_state_et_master_region);
        et_master_Degree = view.findViewById(R.id.fragment_academic_state_et_master_degree);
        et_master_Branch = view.findViewById(R.id.fragment_academic_state_et_master_branch);

    }

    @Override
    public String collectInformationAsString() throws MissingInformationException {
        return getAcademicState().toString();
    }
}