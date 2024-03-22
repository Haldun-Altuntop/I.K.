package arc.haldun.ik.applicationform.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import arc.haldun.ik.R;
import arc.haldun.ik.applicationform.adapters.ReferencesAdapter;
import arc.haldun.ik.applicationform.elements.Reference;
import arc.haldun.ik.exceptions.MissingInformationException;

public class ReferencesFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView_referencesList;
    private ReferencesAdapter referencesAdapter;
    private Button btn_add;

    public ReferencesFragment() {
        // Required empty public constructor
    }

    public static ReferencesFragment newInstance() {
        return new ReferencesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_references, container, false);
        init(view);

        //
        // Set recycler view
        //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView_referencesList.setLayoutManager(linearLayoutManager);
        recyclerView_referencesList.setAdapter(referencesAdapter);

        return view;
    }



    @Override
    public void onClick(View v) {

        if (v.getId() == btn_add.getId()) {
            referencesAdapter.addItem(new Reference());
        }

    }

    private void init(View v) {

        recyclerView_referencesList =
                v.findViewById(R.id.references_fragment_recyclerview_references_list);

        btn_add = v.findViewById(R.id.fragment_references_btn_add);
        btn_add.setOnClickListener(this);

        if (referencesAdapter == null) referencesAdapter = new ReferencesAdapter();;
    }

    private void prepareData() {
        for (int i = 0; i < referencesAdapter.getItemCount(); i++) {

            RecyclerView.ViewHolder viewHolder = recyclerView_referencesList
                    .findViewHolderForAdapterPosition(i);

            if (viewHolder instanceof ReferencesAdapter.ReferenceHolder) {
                ((ReferencesAdapter.ReferenceHolder) viewHolder).prepareData();
            }
        }
    }

    @Override
    public String collectInformationAsString() throws MissingInformationException {

        prepareData();

        StringBuilder stringBuilder = new StringBuilder();

        for (Reference reference : referencesAdapter.getReferences()) {

            stringBuilder.append(reference.toString()).append("\n");

        }

        return stringBuilder.toString();
    }

    @Override
    public void onShift() {
        super.onShift();

        prepareData();
    }
}