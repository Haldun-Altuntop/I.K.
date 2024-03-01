package arc.haldun.ik.applicationform.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import arc.haldun.ik.R;
import arc.haldun.ik.applicationform.elements.Currency;
import arc.haldun.ik.applicationform.elements.Residence;
import arc.haldun.ik.applicationform.elements.SocialAssurance;
import arc.haldun.ik.exceptions.MissingInformationException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdditionalInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdditionalInfoFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText et_additionalMonthlyIncome;
    private Spinner spinner_currency;

    private RadioGroup radioGroup_currentResidence;
    private RadioGroup radioGroup_socialAssurance;

    public AdditionalInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdditionalIncomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdditionalInfoFragment newInstance(String param1, String param2) {
        AdditionalInfoFragment fragment = new AdditionalInfoFragment();
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
        View view = inflater.inflate(R.layout.fragment_additional_info, container, false);

        // Init components
        init(view);

        // TODO: Create string-array in values.xml for additional infos.
        // TODO: Handle additional info.

        return view;
    }

    private void init(View v) {

        et_additionalMonthlyIncome = v.findViewById(R.id.fragment_additional_info_et_additional_income_amount);
        spinner_currency = v.findViewById(R.id.fragment_additional_info_spinner_dolar_tl);

        // Create array adapter for spinner
        ArrayAdapter<CharSequence> currencyAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.currency,
                android.R.layout.simple_spinner_item
        );
        spinner_currency.setAdapter(currencyAdapter);

        radioGroup_currentResidence = v.findViewById(R.id.fragment_additional_info_radio_group_current_residence);
        radioGroup_currentResidence.setOnCheckedChangeListener(this);

        radioGroup_socialAssurance = v.findViewById(R.id.fragment_additional_info_radio_group_social_assurance);
        radioGroup_socialAssurance.setOnCheckedChangeListener(this);
    }

    private String currentResidence;
    private String socialAssurance;
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        int selectedIndex;

        if (group.equals(radioGroup_currentResidence)) {

            // Get selected radio button index
            if (checkedId == R.id.fragment_additional_info_rb_rent) {
                selectedIndex = 0;
            } else if (checkedId == R.id.fragment_additional_info_rb_possession) {
                selectedIndex = 1;
            } else if (checkedId == R.id.fragment_additional_info_rb_familys) {
                selectedIndex = 2;
            } else {
                selectedIndex = -1;
            }

            currentResidence = Residence.findById(selectedIndex).toString();

        } else if (group.equals(radioGroup_socialAssurance)) {

            // Get selected radio button index
            if (checkedId == R.id.fragment_additional_info_rb_ssk) {
                selectedIndex = 0;
            } else if (checkedId == R.id.fragment_additional_info_rb_bagkur) {
                selectedIndex = 1;
            } else if (checkedId == R.id.fragment_additional_info_rb_emkeli_sandigi) {
                selectedIndex = 2;
            } else if (checkedId == R.id.fragment_additional_info_rb_yesil_kart) {
                selectedIndex = 3;
            } else {
                selectedIndex = -1;
            }

            socialAssurance = SocialAssurance.findById(selectedIndex).toString();

        }
    }

    @Override
    public String collectInformationAsString() throws MissingInformationException {

        String incomeAmount = et_additionalMonthlyIncome.getText().toString();
        String currency = Currency.findById(spinner_currency.getSelectedItemPosition()).toString();

        // Check fields
        if (incomeAmount.isEmpty()) throw new MissingInformationException("Aylık Gelir");
        if (currency.isEmpty()) throw new MissingInformationException("Para Birimi");
        if (currentResidence.isEmpty()) throw new MissingInformationException("Mevcut İkamet Yeri");
        if (socialAssurance.isEmpty()) throw new MissingInformationException("Mevcut Sosyel Güvence");

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Ek Bilgiler:");
        stringBuilder.append(incomeAmount);
        stringBuilder.append(currency);
        stringBuilder.append("\n");

        stringBuilder.append("Mevcut İkametgâh Yeri:");
        stringBuilder.append(currentResidence);
        stringBuilder.append("\n");

        stringBuilder.append("Mevcut Sosyal Güvence:");
        stringBuilder.append(socialAssurance);
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }
}