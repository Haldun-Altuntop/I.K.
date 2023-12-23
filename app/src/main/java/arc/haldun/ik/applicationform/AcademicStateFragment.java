package arc.haldun.ik.applicationform;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import arc.haldun.ik.R;
import arc.haldun.ik.applicationform.info.academicstate.AcademicState;
import arc.haldun.ik.applicationform.info.academicstate.First;
import arc.haldun.ik.applicationform.info.academicstate.High;
import arc.haldun.ik.exceptions.MissingInformationException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AcademicStateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AcademicStateFragment extends Fragment {

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
        // Required empty public constructor
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

        return view;
    }

    public AcademicState getAcademicState() throws MissingInformationException {

        AcademicState academicState;

        First primarySchool;
        First middleSchool;
        High highSchool;
        High university;
        High master;

        // Primary school
        String primarySchoolName = et_primarySchool_Name.getText().toString();
        String primarySchoolStart = et_primarySchool_Start.getText().toString();
        String primarySchoolEnd = et_primarySchool_End.getText().toString();
        String primarySchoolRegion = et_primarySchool_Region.getText().toString();

        // If primary school is checked and one of the areas is null throw exception
        if (hasPrimarySchool && primarySchoolName.isEmpty() || primarySchoolStart.isEmpty() ||
                primarySchoolEnd.isEmpty() || primarySchoolRegion.isEmpty()) {

            throw new MissingInformationException("Bad Primary School Information");
        }

        // Init primary school
        primarySchool = new First(primarySchoolStart, primarySchoolEnd,
                primarySchoolName, primarySchoolRegion);

        // Middle school
        String middleSchoolName = et_middleSchool_Name.getText().toString();
        String middleSchoolStart = et_middleSchool_Start.getText().toString();
        String middleSchoolEnd = et_middleSchool_End.getText().toString();
        String middleSchoolRegion = et_middleSchool_Region.getText().toString();

        // If middle school is checked and one of the areas is null throw exception
        if (hasMiddleSchool && middleSchoolName.isEmpty() || middleSchoolStart.isEmpty()
                || middleSchoolEnd.isEmpty() || middleSchoolRegion.isEmpty()) {

            throw new MissingInformationException("Bad Middle School Information");

        }

        // Init middle school
        middleSchool = new First(middleSchoolStart, middleSchoolEnd,
                middleSchoolName, middleSchoolRegion);

        // High school
        String highSchoolName = et_highSchool_Name.getText().toString();
        String highSchoolStart = et_highSchool_Start.getText().toString();
        String highSchoolEnd = et_highSchool_End.getText().toString();
        String highSchoolRegion = et_highSchool_Region.getText().toString();
        String highSchoolDegree = et_highSchool_Degree.getText().toString();
        String highSchoolBranch = et_highSchool_Branch.getText().toString();

        // If high school is checked and one of the areas is null throw exception
        if (hasHighSchool && highSchoolName.isEmpty() || highSchoolStart.isEmpty()
                || highSchoolEnd.isEmpty() || highSchoolRegion.isEmpty()
                || highSchoolDegree.isEmpty() || highSchoolBranch.isEmpty()) {

            throw new MissingInformationException("Bad High School Information");

        }

        // Init high school
        highSchool = new High(highSchoolStart, highSchoolEnd, highSchoolName,
                highSchoolRegion, highSchoolBranch, highSchoolDegree);

        // University
        String universityName = et_university_Name.getText().toString();
        String universityStart = et_university_Start.getText().toString();
        String universityEnd = et_university_End.getText().toString();
        String universityRegion = et_university_Region.getText().toString();
        String universityDegree = et_university_Degree.getText().toString();
        String universityBranch = et_university_Branch.getText().toString();

        // If university is checked and one of the areas is null throw exception
        if (hasUniversity && universityName.isEmpty() || universityStart.isEmpty()
                || universityEnd.isEmpty() || universityRegion.isEmpty()
                || universityDegree.isEmpty() || universityBranch.isEmpty()) {

            throw new MissingInformationException("Bad University Information");

        }

        // Init university
        university = new High(universityStart, universityEnd, universityName,
                universityRegion, universityBranch, universityDegree);

        // Master
        String masterName = et_master_Name.getText().toString();
        String masterStart = et_master_Start.getText().toString();
        String masterEnd = et_master_End.getText().toString();
        String masterRegion = et_master_Region.getText().toString();
        String masterDegree = et_master_Degree.getText().toString();
        String masterBranch = et_master_Branch.getText().toString();

        // If master is checked and one of the areas is null throw exception7
        if (hasMaster && masterName.isEmpty() || masterStart.isEmpty() || masterEnd.isEmpty()
                || masterRegion.isEmpty() || masterDegree.isEmpty()|| masterBranch.isEmpty()) {

            throw new MissingInformationException("Bad Master Information");

        }

        // Init master
        master = new High(masterStart, masterEnd, masterName,
                masterRegion, masterDegree, masterBranch);

        // Init academic state
        academicState = new AcademicState(primarySchool, middleSchool, highSchool,
                                                university, master);

        // Return result
        return academicState;
    }

    private void initViews(View view) {

        // Primary school
        cbPrimarySchool = view.findViewById(R.id.fragment_academic_state_cb_primary_school);

        et_primarySchool_Name = view.findViewById(R.id.fragment_academic_state_et_primary_school_name);
        et_primarySchool_Start = view.findViewById(R.id.fragment_academic_state_et_primary_school_start_date);
        et_primarySchool_End = view.findViewById(R.id.fragment_academic_state_et_primary_school_end_date);
        et_primarySchool_Region = view.findViewById(R.id.fragment_academic_state_et_primary_school_region);

        // Middle school
        cbMiddleSchool = view.findViewById(R.id.fragment_academic_state_cb_middle_school);

        et_middleSchool_Name = view.findViewById(R.id.fragment_academic_state_et_middle_school_name);
        et_middleSchool_Start = view.findViewById(R.id.fragment_academic_state_et_middle_school_start_date);
        et_middleSchool_End = view.findViewById(R.id.fragment_academic_state_et_middle_school_end_date);
        et_middleSchool_Region = view.findViewById(R.id.fragment_academic_state_et_middle_school_region);

        // High school
        cbHighSchool = view.findViewById(R.id.fragment_academic_state_cb_high_school);

        et_highSchool_Name = view.findViewById(R.id.fragment_academic_state_et_high_school_name);
        et_highSchool_Start = view.findViewById(R.id.fragment_academic_state_et_high_school_start_date);
        et_highSchool_End = view.findViewById(R.id.fragment_academic_state_et_high_school_end_date);
        et_highSchool_Region = view.findViewById(R.id.fragment_academic_state_et_high_school_region);
        et_highSchool_Degree = view.findViewById(R.id.fragment_academic_state_et_high_school_degree);
        et_highSchool_Branch = view.findViewById(R.id.fragment_academic_state_et_high_school_branch);

        // University
        cbUniversity = view.findViewById(R.id.fragment_academic_state_cb_university);

        et_university_Name = view.findViewById(R.id.fragment_academic_state_et_university_name);
        et_university_Start = view.findViewById(R.id.fragment_academic_state_et_university_start_date);
        et_university_End = view.findViewById(R.id.fragment_academic_state_et_university_end_date);
        et_university_Region = view.findViewById(R.id.fragment_academic_state_et_university_region);
        et_university_Degree = view.findViewById(R.id.fragment_academic_state_et_university_degree);
        et_university_Branch = view.findViewById(R.id.fragment_academic_state_et_university_branch);

        // Master
        cbMaster = view.findViewById(R.id.fragment_academic_state_cb_master);

        et_master_Name = view.findViewById(R.id.fragment_academic_state_et_master_name);
        et_master_Start = view.findViewById(R.id.fragment_academic_state_et_master_start_date);
        et_master_End = view.findViewById(R.id.fragment_academic_state_et_master_end_date);
        et_master_Region = view.findViewById(R.id.fragment_academic_state_et_master_region);
        et_master_Degree = view.findViewById(R.id.fragment_academic_state_et_master_degree);
        et_master_Branch = view.findViewById(R.id.fragment_academic_state_et_master_branch);
    }
}