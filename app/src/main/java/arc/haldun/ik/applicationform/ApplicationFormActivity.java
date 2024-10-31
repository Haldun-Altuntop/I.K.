package arc.haldun.ik.applicationform;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import arc.haldun.ik.R;
import arc.haldun.ik.applicationform.elements.Experience;
import arc.haldun.ik.applicationform.elements.Language;
import arc.haldun.ik.applicationform.elements.Reference;
import arc.haldun.ik.applicationform.fragments.AcademicStateFragment;
import arc.haldun.ik.applicationform.fragments.AdditionalInfoFragment;
import arc.haldun.ik.applicationform.fragments.ExperiencesFragment;
import arc.haldun.ik.applicationform.fragments.Fragment;
import arc.haldun.ik.applicationform.fragments.FragmentType;
import arc.haldun.ik.applicationform.fragments.LanguageFragment;
import arc.haldun.ik.applicationform.fragments.MilitaryFragment;
import arc.haldun.ik.applicationform.fragments.PersonalInfoFragment;
import arc.haldun.ik.applicationform.fragments.ReferencesFragment;
import arc.haldun.ik.applicationform.info.AdditionalInfo;
import arc.haldun.ik.applicationform.info.MilitaryState;
import arc.haldun.ik.applicationform.info.PersonalInformation;
import arc.haldun.ik.applicationform.info.academicstate.AcademicState;
import arc.haldun.ik.database.DatabaseManager;
import arc.haldun.ik.database.mysql.MySqlDB;
import arc.haldun.ik.exceptions.MissingInformationException;

public class ApplicationFormActivity extends AppCompatActivity {

    // Views
    private Button btnPrevious, btnCommit, btnNext;

    // Fragments
    private Fragment personalInfoFragment;
    private Fragment militaryFragment;
    private Fragment academicStateFragment;
    private Fragment  languageFragment;
    private Fragment additionalInfoFragment;
    private Fragment experiencesFragment;
    private Fragment referencesFragment;

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

        // Save data
        getCurrentFragment().onShift();

        // On next button clicked
        if (view.equals(btnNext)) {

            try {
                getCurrentFragment().collectInformationAsString();
            } catch (MissingInformationException e) {
                showMissingInformationDialog(e.getMissingFields());
                e.printStackTrace();
                //return; // DEBUG
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

        StringBuilder information = new StringBuilder();

        AcademicState academicState = null;
        AdditionalInfo additionalInfo = null;
        Experience[] experiences = new Experience[0];
        Language[] languages = new Language[0];
        MilitaryState militaryState = null;
        PersonalInformation personalInformation = null;
        Reference[] references = new Reference[0];

        try {

            for (Fragment fragment : fragments) {
                information.append(fragment.collectInformationAsString()).append("\n");

                // Collect info
                if (fragment.getFragmentType() == FragmentType.ACADEMIC_STATE) {
                    academicState = ((AcademicStateFragment) fragment).getAcademicState();
                } else if (fragment.getFragmentType() == FragmentType.MILITARY_STATE) {
                    militaryState = ((MilitaryFragment) fragment).getMilitaryState();
                } else if (fragment.getFragmentType() == FragmentType.ADDITIONAL_INFORMATION) {
                    additionalInfo = ((AdditionalInfoFragment) fragment).getAdditionalInfo();
                } else if (fragment.getFragmentType() == FragmentType.EXPERIENCES) {
                    experiences = ((ExperiencesFragment) fragment).getExperiences();
                } else if (fragment.getFragmentType() == FragmentType.LANGUAGE) {
                    languages = ((LanguageFragment) fragment).getLanguages();
                } else if (fragment.getFragmentType() == FragmentType.PERSONAL_INFORMATION) {
                    personalInformation = ((PersonalInfoFragment) fragment).getPersonalInfo();
                } else if (fragment.getFragmentType() == FragmentType.REFERENCES) {
                    references = ((ReferencesFragment) fragment).getReferences();
                }
            }

            Application application = new Application(
                    personalInformation,
                    militaryState,
                    academicState,
                    languages,
                    additionalInfo,
                    experiences,
                    references
            );

            commitApplication(application);

            Log.i("Application Form", application.toString());

        } catch (MissingInformationException e) {
            showMissingInformationDialog(e.getMissingFields());
            e.printStackTrace();
            return;
        }

        System.out.println(information.toString());

        /*
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

         */
    }

    private void commitApplication(Application application) {

        HandlerThread commitThread = new HandlerThread("CommitApplicationThread");
        commitThread.start();

        Handler commitHandler = new Handler(commitThread.getLooper());
        commitHandler.post(() -> {
            DatabaseManager databaseManager = new DatabaseManager(new MySqlDB());
            databaseManager.addApplication(application);
        });

        commitThread.quitSafely();

    }

    private void showMissingInformationDialog(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setTitle("Eksik bilgiler var")
                .setMessage(msg)
                .setPositiveButton("Tamam", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Deprecated
    private void printLanguages() {
        throw new UnsupportedOperationException();

        /*
        Language[] languages = languageFragment.getLanguages();

        System.out.println("\nYabancı dil:\n");

        for (Language language : languages) {
            System.out.println(language.toString());
        }

         */
    }

    private Fragment getCurrentFragment() {
        return fragments[currentFragmentIndex];
    }

    private void setCurrentFragmentIndex(int index) {

        Fragment fragment = fragments[index];

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_form_fragment_container, fragment)
                .commit();

        getSupportActionBar().setTitle(fragment.getClass().getSimpleName());
    }

    private void initFragments() {

        personalInfoFragment = new PersonalInfoFragment(FragmentType.PERSONAL_INFORMATION);
        militaryFragment = new MilitaryFragment(FragmentType.MILITARY_STATE);
        academicStateFragment = new AcademicStateFragment(FragmentType.ACADEMIC_STATE);
        languageFragment = new LanguageFragment(FragmentType.LANGUAGE);
        additionalInfoFragment = new AdditionalInfoFragment(FragmentType.ADDITIONAL_INFORMATION);
        experiencesFragment = ExperiencesFragment.newInstance(FragmentType.EXPERIENCES);
        referencesFragment = ReferencesFragment.newInstance(FragmentType.REFERENCES);
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
                personalInfoFragment, // 0
                militaryFragment, // 1
                academicStateFragment, // 2
                languageFragment, // 3
                additionalInfoFragment, // 4
                experiencesFragment, // 5
                referencesFragment // 6
        };
    }
}