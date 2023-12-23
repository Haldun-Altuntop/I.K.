package arc.haldun.ik.applicationform;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import arc.haldun.ik.R;

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

    private EditText et_university_Name, et_university_Start, et_university_End, et_university_degree,
            et_university_branch, et_university_region;

    private EditText et_master_Name, et_master_Start, et_master_End, et_master_degree,
            et_master_branch, et_master_region;

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

    }
}