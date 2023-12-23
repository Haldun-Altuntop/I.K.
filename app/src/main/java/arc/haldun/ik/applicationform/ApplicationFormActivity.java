package arc.haldun.ik.applicationform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import arc.haldun.ik.R;
import arc.haldun.ik.applicationform.info.MilitaryState;
import arc.haldun.ik.exceptions.MissingInformationException;
import arc.haldun.ik.applicationform.info.PersonalInformation;

public class ApplicationFormActivity extends AppCompatActivity {

    // Views
    private Button btnPrevious, btnCommit, btnNext;

    // Fragments
    private PersonalInfoFragment personalInfoFragment;
    private MilitaryFragment militaryFragment;
    private AcademicStateFragment academicStateFragment;

    // Other components
    private Fragment[] fragments;

    private int currentFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // Initialize activity views
        initViews();

        // Initialize fragments
        initFragments();

        // Initialize other components
        initOtherComponents();

        // Set current fragment
        setCurrentFragment(currentFragment);
    }

    private void onShiftPageButtonsClicked(View view) {

        // On next button clicked
        if (view.equals(btnNext)) {

            if (currentFragment == fragments.length - 1) {
                return; // If fragment index is max value, do nothing
            }

            // Increase fragment index
            currentFragment++;

        }

        // On previous button clicked
        if (view.equals(btnPrevious)) {

            if (currentFragment == 0) {
                return; // If current fragment value is min value, do nothing
            }

            // Decrease fragment index
            currentFragment--;

        }

        // Set active fragment to current fragment
        setCurrentFragment(currentFragment);

        // Update forwarding buttons' activity
        btnPrevious.setEnabled(currentFragment != 0);
        btnNext.setEnabled(currentFragment != fragments.length - 1);
    }

    private void btnCommitClicked(View view) {

        try {
            PersonalInformation personalInformation = this.personalInfoFragment.getPersonalInfo();
            MilitaryState militaryState = this.militaryFragment.getMilitaryState();

            System.out.println("Kişisel bilgileri:\n");
            System.out.println(personalInformation.toString());

            System.out.println("\nAskerlik durumu:\n");
            System.out.println(militaryState);
        } catch (MissingInformationException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCurrentFragment(int index) {

        Fragment fragment = fragments[index];

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_form_fragment_container, fragment)
                .commit();
    }

    private void initFragments() {

        personalInfoFragment = new PersonalInfoFragment();
        militaryFragment = new MilitaryFragment();
        academicStateFragment = new AcademicStateFragment();
    }

    private void  initViews() {

        btnPrevious = findViewById(R.id.activity_form_btn_previous);
        btnPrevious.setOnClickListener(this::onShiftPageButtonsClicked);

        btnCommit = findViewById(R.id.activity_form_btn_commit);
        btnCommit.setOnClickListener(this::btnCommitClicked);

        btnNext = findViewById(R.id.activity_form_btn_next);
        btnNext.setOnClickListener(this::onShiftPageButtonsClicked);

    }

    private void initOtherComponents() {

        fragments = new Fragment[]{personalInfoFragment, militaryFragment, academicStateFragment};
    }
}