package arc.haldun.ik.applicationform.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import arc.haldun.ik.R;
import arc.haldun.ik.applicationform.elements.Experience;

public class ExperienceRecyclerAdapter
        extends RecyclerView.Adapter<ExperienceRecyclerAdapter.ExperienceHolder> {

    ArrayList<Experience> experiences = new ArrayList<>();

    @NonNull
    @Override
    public ExperienceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_experience, parent, false);
        return new ExperienceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceHolder holder, int position) {

        Experience currentXp = experiences.get(position);
        holder.setData(currentXp);
    }

    @Override
    public int getItemCount() {
        return experiences.size();
    }

    public void addItem(Experience xp) {
        experiences.add(xp);
        notifyItemInserted(experiences.size() - 1);
    }

    public Experience getItem(int i) {
        return experiences.get(i);
    }

    /**
     * Gets added experiences.
     * @return Experience[]
     */
    public Experience[] getExperiences() {
        return experiences.toArray(new Experience[0]);
    }

    public static class ExperienceHolder extends RecyclerView.ViewHolder{

        private Experience currentXP;
        private EditText et_company, et_startingDate, et_quittingDate, et_reference, et_position,
                        et_salary, et_causeOfQuit;

        public ExperienceHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        public void setData(Experience xp) {
            try {
                currentXP = xp;

                // Set text fields
                et_company.setText(xp.getCompany());
                //et_company.addTextChangedListener(this);

                et_startingDate.setText(xp.getStartingDate());
                //et_startingDate.addTextChangedListener(this);

                et_quittingDate.setText(xp.getQuittingDate());
                //et_quittingDate.addTextChangedListener(this);

                et_reference.setText(xp.getReference());
                //et_reference.addTextChangedListener(this);

                et_position.setText(xp.getPosition());
                //et_position.addTextChangedListener(this);

                et_salary.setText(xp.getSalary());
                //et_salary.addTextChangedListener(this);

                et_causeOfQuit.setText(xp.getCauseOfQuit());
                //et_causeOfQuit.addTextChangedListener(this);

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        /**
         * Assign data in item to array in adapter.
         */
        public void prepareData() {

            try {
                currentXP.setCompany(et_company.getText().toString());
                currentXP.setStartingDate(et_startingDate.getText().toString());
                currentXP.setQuittingDate(et_quittingDate.getText().toString());
                currentXP.setReference(et_reference.getText().toString());
                currentXP.setPosition(et_position.getText().toString());
                currentXP.setSalary(et_salary.getText().toString());
                currentXP.setCauseOfQuit(et_causeOfQuit.getText().toString());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        private void init(View v) {
            et_company = v.findViewById(R.id.item_experience_et_company);
            et_startingDate = v.findViewById(R.id.item_experience_et_starting_date);
            et_quittingDate = v.findViewById(R.id.item_experience_et_quitting_date);
            et_reference = v.findViewById(R.id.item_experience_et_reference);
            et_position = v.findViewById(R.id.item_experience_et_position);
            et_salary = v.findViewById(R.id.item_experience_et_salary);
            et_causeOfQuit = v.findViewById(R.id.item_experience_et_cause_of_quit);
        }
    }
}
