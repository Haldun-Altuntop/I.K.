package arc.haldun.ik.applicationform.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import arc.haldun.ik.R;
import arc.haldun.ik.applicationform.elements.Reference;

public class ReferencesAdapter extends RecyclerView.Adapter<ReferencesAdapter.ReferenceHolder> {

    private final ArrayList<Reference> references;

    public ReferencesAdapter() {
        this.references = new ArrayList<>();
    }

    public ReferencesAdapter(Reference[] references) {
        this.references = new ArrayList<>(Arrays.asList(references));
    }

    @NonNull
    @Override
    public ReferenceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_reference, parent, false);
        return new ReferenceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReferenceHolder holder, int position) {

        Reference reference = references.get(position);
        holder.setData(reference);
    }

    @Override
    public int getItemCount() {
        return references.size();
    }

    public void addItem(Reference reference) {
        references.add(reference);
        notifyItemChanged(references.size() - 1);
    }

    public Reference[] getReferences() {
        return references.toArray(new Reference[0]);
    }

    public static class ReferenceHolder extends RecyclerView.ViewHolder {

        private Reference reference;

        private final EditText et_nameSurname;
        private final EditText et_phone;
        private final EditText et_company;
        private final EditText et_job;
        private final EditText et_relativityDegree;

        public ReferenceHolder(@NonNull View itemView) {
            super(itemView);

            // Init views
            et_nameSurname = itemView.findViewById(R.id.item_reference_et_name_surname);
            et_phone = itemView.findViewById(R.id.item_reference_et_phone);
            et_company = itemView.findViewById(R.id.item_reference_et_company);
            et_job = itemView.findViewById(R.id.item_reference_et_job);
            et_relativityDegree = itemView.findViewById(R.id.item_reference_et_relativity_degree);
        }

        public void setData(Reference reference) {

            // Init holder's reference
            this.reference = reference;

            et_nameSurname.setText(reference.getNameSurname());
            et_phone.setText(reference.getPhone());
            et_company.setText(reference.getCompany());
            et_job.setText(reference.getJob());
            et_relativityDegree.setText(reference.getRelativityDegree());
        }

        /**
         * Puts the current reference values to list's item.
         */
        public void prepareData() {
            reference.setNameSurname(et_nameSurname.getText().toString());
            reference.setPhone(et_phone.getText().toString());
            reference.setCompany(et_company.getText().toString());
            reference.setJob(et_job.getText().toString());
            reference.setRelativityDegree(et_relativityDegree.getText().toString());
        }
    }

}