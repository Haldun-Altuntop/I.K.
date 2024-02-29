package arc.haldun.ik.applicationform.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import arc.haldun.ik.R;
import arc.haldun.ik.applicationform.info.MilitaryState;
import arc.haldun.ik.exceptions.MissingInformationException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MilitaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MilitaryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText et_entryDate, et_dischargeDate, et_dutyArea, et_rank;
    private ToggleButton tbHasDone;
    private boolean hasDone;

    public MilitaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MilitaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MilitaryFragment newInstance(String param1, String param2) {
        MilitaryFragment fragment = new MilitaryFragment();
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

        View view = inflater.inflate(R.layout.fragment_military, container, false);

        // Initialize views
        initViews(view);

        // Inflate the layout for this fragment
        return view;
    }

    public MilitaryState getMilitaryState() throws MissingInformationException {

        MilitaryState militaryState;

        if (hasDone) {

            String entryDate = et_entryDate.getText().toString();
            String dischargeDate = et_dischargeDate.getText().toString();
            String dutyArea = et_dutyArea.getText().toString();
            String rank = et_rank.getText().toString();

            militaryState = new MilitaryState(entryDate, dischargeDate, dutyArea, rank);
            militaryState.checkValidity();

            if (!militaryState.isValid()) throw new MissingInformationException();

        } else {
            militaryState = new MilitaryState(false);
        }

        return militaryState;
    }

    private void onHasDoneCheckedChange(CompoundButton compoundButton, boolean b) {

        hasDone = b;

        if (!b) {
            et_entryDate.setText(null);
            et_dischargeDate.setText(null);
            et_dutyArea.setText(null);
            et_rank.setText(null);
        }

        et_entryDate.setEnabled(b);
        et_dischargeDate.setEnabled(b);
        et_dutyArea.setEnabled(b);
        et_rank.setEnabled(b);
    }

    private void initViews(View view) {

        et_entryDate = view.findViewById(R.id.fragment_military_et_entry_date);
        et_dischargeDate = view.findViewById(R.id.fragment_military_et_discharge_date);
        et_dutyArea = view.findViewById(R.id.fragment_military_et_duty_area);
        et_rank = view.findViewById(R.id.fragment_military_et_rank);

        tbHasDone = view.findViewById(R.id.fragment_military_toggle_button_has_done);
        tbHasDone.setOnCheckedChangeListener(this::onHasDoneCheckedChange);

    }

    @Override
    public String collectInformationAsString() throws MissingInformationException {
        return getMilitaryState().toString();
    }
}