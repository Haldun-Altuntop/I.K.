package arc.haldun.ik.applicationform;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import arc.haldun.ik.R;
import arc.haldun.ik.applicationform.elements.Language;
import arc.haldun.ik.applicationform.fragments.AcademicStateFragment;
import arc.haldun.ik.applicationform.fragments.Fragment;
import arc.haldun.ik.applicationform.fragments.LanguageFragment;
import arc.haldun.ik.applicationform.fragments.MilitaryFragment;
import arc.haldun.ik.applicationform.fragments.PersonalInfoFragment;
import arc.haldun.ik.applicationform.info.MilitaryState;
import arc.haldun.ik.applicationform.info.academicstate.AcademicState;
import arc.haldun.ik.exceptions.MissingInformationException;
import arc.haldun.ik.applicationform.info.PersonalInformation;

public class ApplicationFormActivity extends AppCompatActivity {

    // Views
    private Button btnPrevious, btnCommit, btnNext;

    // Fragments
    private PersonalInfoFragment personalInfoFragment;
    private MilitaryFragment militaryFragment;
    private AcademicStateFragment academicStateFragment;
    private LanguageFragment  languageFragment;

    // Other components
    private Fragment[] fragments;

    private int currentFragmentIndex = 0;

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
        setCurrentFragmentIndex(currentFragmentIndex);

        // DEBUG
        //setCurrentFragmentIndex(3);
    }

    private void showMissingInformationDialog(String... missingFields) {

        StringBuilder stringBuilder = new StringBuilder();

        for (String missingField : missingFields) {

            // Give some space
            stringBuilder.append("\n");

            // Apply the string
            stringBuilder.append(missingField);
        }

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getCurrentFragment().requireContext());
        dialogBuilder.setTitle("Eksik bilgiler var:\n")
                .setMessage(stringBuilder)
                .setPositiveButton("Tamam", null);

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    private void onShiftPageButtonsClicked(View view) {

        // On next button clicked
        if (view.equals(btnNext)) {

            try {
                getCurrentFragment().collectInformationAsString();
            } catch (MissingInformationException e) {
                showMissingInformationDialog(e.getMissingFields());
                return;
            }

            if (currentFragmentIndex == fragments.length - 1) {
                return; // If fragment index is max value, do nothing
            }

            // Increase fragment index
            currentFragmentIndex++;

        }

        // On previous button clicked
        if (view.equals(btnPrevious)) {

            if (currentFragmentIndex == 0) {
                return; // If current fragment value is min value, do nothing
            }

            // Decrease fragment index
            currentFragmentIndex--;
        }

        // Set active fragment to current fragment
        setCurrentFragmentIndex(currentFragmentIndex);

        // Update forwarding buttons' activity
        btnPrevious.setEnabled(currentFragmentIndex != 0);
        btnNext.setEnabled(currentFragmentIndex != fragments.length - 1);
    }

    private void btnCommitClicked(View view) {

        try {

            PersonalInformation personalInformation = this.personalInfoFragment.getPersonalInfo();
            MilitaryState militaryState = this.militaryFragment.getMilitaryState();
            AcademicState academicState = academicStateFragment.getAcademicState();

            System.out.println("Kişisel bilgileri:\n");
            System.out.println(personalInformation.toString());

            System.out.println("\nAskerlik durumu:\n");
            System.out.println(militaryState);

            System.out.println("\nAkademik durumu:\n");
            System.out.println(academicState.toString());

            printLanguages();

        } catch (MissingInformationException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            showMissingInformationDialog(e.getMessage());
        } catch (Exception e) {

            Toast.makeText(this, "Bilgiler gönderilemedi. Bilgileri eksiksiz " +
                    "doldurduğuğunuzdan emin olun ve bağlantınızı kontrol edin.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void showMissingInformationDialog(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Eksik bilgiler var")
                .setMessage(msg)
                .setPositiveButton("Tamam", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void printLanguages() {

        Language[] languages = languageFragment.getLanguages();

        System.out.println("\nYabancı dil:\n");

        for (Language language : languages) {
            System.out.println(language.toString());
        }
    }

    private Fragment getCurrentFragment() {
        return fragments[currentFragmentIndex];
    }

    private void setCurrentFragmentIndex(int index) {

        Fragment fragment = fragments[index];

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_form_fragment_container, fragment)
                .commit();
    }

    private void initFragments() {

        personalInfoFragment = new PersonalInfoFragment();
        militaryFragment = new MilitaryFragment();
        academicStateFragment = new AcademicStateFragment();
        languageFragment = new LanguageFragment();
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

        fragments = new Fragment[] {
                personalInfoFragment,
                militaryFragment,
                academicStateFragment,
                languageFragment
        };
    }
}