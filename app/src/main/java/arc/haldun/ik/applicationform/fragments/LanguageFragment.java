package arc.haldun.ik.applicationform.fragments;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import arc.haldun.ik.R;
import arc.haldun.ik.applicationform.adapters.LanguageRecyclerAdapter;
import arc.haldun.ik.applicationform.elements.Language;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LanguageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LanguageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView languageList;
    private FloatingActionButton fab;
    LanguageRecyclerAdapter languageAdapter;

    public LanguageFragment() {
        // Required empty public constructor
    }

    public LanguageFragment(FragmentType fragmentType) {
        super(fragmentType);
    }

    public static LanguageFragment newInstance(String param1, String param2) {
        LanguageFragment fragment = new LanguageFragment();
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
        View view = inflater.inflate(R.layout.fragment_language, container, false);
        init(view);


        return view;
    }

    private void onFABClick(View view) {
        languageAdapter.addItem(new Language());
    }

    private void init(View view) {

        languageAdapter = new LanguageRecyclerAdapter();

        languageList = view.findViewById(R.id.fragment_language_recycler_view);
        setupLanguageList();

        fab = view.findViewById(R.id.fragment_language_fab);
        fab.setOnClickListener(this::onFABClick);
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    private void setupLanguageList() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        languageList.setLayoutManager(linearLayoutManager);
        languageList.setAdapter(languageAdapter);
    }

    public Language[] getLanguages() {
        return languageAdapter.getLanguages();
    }

    @Override
    public String collectInformationAsString() {

        StringBuilder stringBuilder = new StringBuilder();

        Language[] languages = getLanguages();

        for (Language language : languages) {
            stringBuilder.append(language.toString());
        }

        return stringBuilder.toString();
    }
}