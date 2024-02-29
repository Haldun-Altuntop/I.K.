package arc.haldun.ik.editor;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import arc.haldun.ik.R;
import arc.haldun.ik.applicationform.elements.Language;

public class LanguageItem
        extends RelativeLayout
        implements AdapterView.OnItemSelectedListener, TextWatcher {

    private Spinner spinner_language, spinner_speakLevel, spinner_readWriteLevel;
    private EditText et_languageName;
    private AdapterView.OnItemSelectedListener onItemSelectedListener;
    private String customLanguageName = "";
    public static final int OTHER_INDEX = 5;

    public LanguageItem(Context context) {
        super(context);
        init();
    }

    public LanguageItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LanguageItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init() {
        inflate(getContext(), R.layout.item_language, this);
    }

    private void init(AttributeSet attrs) {
        init();

        String languageName;

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LanguageItem);

        spinner_language = findViewById(R.id.item_language_spinner_language);
        spinner_language.setOnItemSelectedListener(this);

        spinner_speakLevel = findViewById(R.id.item_language_spinner_speak_level);
        spinner_speakLevel.setOnItemSelectedListener(this);

        spinner_readWriteLevel = findViewById(R.id.item_language_spinner_readwrite_level);
        spinner_readWriteLevel.setOnItemSelectedListener(this);

        et_languageName = findViewById(R.id.item_language_et_language_name);

        ArrayAdapter<CharSequence> arrayAdapter_langs = ArrayAdapter.createFromResource(
                getContext(),
                R.array.langs,
                android.R.layout.simple_spinner_item
        );
        spinner_language.setAdapter(arrayAdapter_langs);

        ArrayAdapter<CharSequence> arrayAdapter_levels = ArrayAdapter.createFromResource(
                getContext(),
                R.array.lang_levels,
                android.R.layout.simple_spinner_item
        );
        spinner_speakLevel.setAdapter(arrayAdapter_levels);
        spinner_readWriteLevel.setAdapter(arrayAdapter_levels);

        languageName = typedArray.getString(R.styleable.LanguageItem_lang_name);

        et_languageName.setText(languageName);

        if (spinner_language.getSelectedItemPosition() != OTHER_INDEX) {
            et_languageName.setVisibility(GONE);
        }

        et_languageName.addTextChangedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        // Additional action
        if (onItemSelectedListener != null) {
            onItemSelectedListener.onNothingSelected(parent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // Default action
        if (parent == spinner_language) {
            if (position == 5) { // 5 is the other languages
                et_languageName.setVisibility(VISIBLE);
            } else {
                et_languageName.setVisibility(GONE);
            }
        }

        // Additional action
        if (onItemSelectedListener != null) {
            onItemSelectedListener.onItemSelected(parent, view, position, id);
        }
    }

    @Deprecated
    public void setLanguageName(String languageName) {

    }

    public String getLanguageName() {

        return (String) spinner_language.getSelectedItem();
    }

    public Language.Level getSpeakingLevel() {
        return Language.Level.findByIndex(spinner_speakLevel.getSelectedItemPosition());
    }

    public Language.Level getReadingWritingLevel() {
        return Language.Level.findByIndex(spinner_readWriteLevel.getSelectedItemPosition());
    }

    public Language getLanguage() {

        Language language = new Language(
                getLanguageName(),
                getSpeakingLevel(),
                getReadingWritingLevel()
        );
        language.setCustomLanguageName(customLanguageName);

        return language;
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public void init(Language language) {

        int selectedLanguage = OTHER_INDEX;
        String[] languages = getContext().getResources().getStringArray(R.array.langs);

        if (language.getName() == null) {
            selectedLanguage = 0;
        }

        // Find selected language
        String lang;
        for (int i = 0; i < languages.length; i++) {
            lang = languages[i];

            if (lang.equals(language.getName())) {
                selectedLanguage = i;
            }
        }

        // If selected language is still OTHER
        if (selectedLanguage == OTHER_INDEX) {
            et_languageName.setVisibility(VISIBLE);
            et_languageName.setText(language.getCustomLanguageName());
        }

        spinner_language.setSelection(selectedLanguage);
        spinner_speakLevel.setSelection(language.getSpeakingLevel().getIndex());
        spinner_readWriteLevel.setSelection(language.getReadingWritingLevel().getIndex());

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        customLanguageName += s;
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
