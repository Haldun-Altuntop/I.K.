package arc.haldun.ik.applicationform.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import arc.haldun.ik.R;
import arc.haldun.ik.applicationform.adapters.ExperienceRecyclerAdapter;
import arc.haldun.ik.applicationform.elements.Experience;
import arc.haldun.ik.exceptions.MissingInformationException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExperiencesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExperiencesFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView_experiencesList;
    private ExperienceRecyclerAdapter experiencesAdapter;
    private Button btn_add;

    public ExperiencesFragment() {
        // Required empty public constructor
    }

    public static ExperiencesFragment newInstance() {
        return new ExperiencesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_experiences, container, false);

        init(view);

        return view;
    }

    private void prepareData() {
        for (int i = 0; i < experiencesAdapter.getItemCount(); i++) {
            RecyclerView.ViewHolder viewHolder = recyclerView_experiencesList
                    .findViewHolderForAdapterPosition(i);

            if (viewHolder instanceof ExperienceRecyclerAdapter.ExperienceHolder) {
                ((ExperienceRecyclerAdapter.ExperienceHolder) viewHolder).prepareData();
            }
        }
    }

    @Override
    public String collectInformationAsString() throws MissingInformationException {

        prepareData();

        StringBuilder stringBuilder = new StringBuilder();

        for (Experience xp : experiencesAdapter.getExperiences()) {

            xp.checkValidity();

            stringBuilder.append("Şirket: ").append(xp.getCompany()).append("\n");
            stringBuilder.append("Başlama Tarihi: ").append(xp.getStartingDate()).append("\n");
            stringBuilder.append("Ayrılma Tarihi: ").append(xp.getQuittingDate()).append("\n");
            stringBuilder.append("Referans: ").append(xp.getReference()).append("\n");
            stringBuilder.append("Maaş: ").append(xp.getSalary()).append("\n");
            stringBuilder.append("Ayrılma Sebebi: ").append(xp.getCauseOfQuit()).append("\n");

        }

        return stringBuilder.toString();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == btn_add.getId()) {

            experiencesAdapter.addItem(Experience.emptyInstance());
        }
    }

    private void init(View v) {

        btn_add = v.findViewById(R.id.fragment_experiences_btn_add);
        btn_add.setOnClickListener(this);

        //
        // Init recycler view
        //
        recyclerView_experiencesList = v.findViewById(R.id.fragment_experiences_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        if (experiencesAdapter == null) experiencesAdapter = new ExperienceRecyclerAdapter();

        recyclerView_experiencesList.setLayoutManager(linearLayoutManager);
        recyclerView_experiencesList.setAdapter(experiencesAdapter);
    }

    @Override
    public void onShift() {
        super.onShift();

        prepareData();
    }
}