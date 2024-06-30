package arc.haldun.ik.applicationform.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import arc.haldun.ik.R;
import arc.haldun.ik.applicationform.elements.Language;
import arc.haldun.ik.editor.LanguageItem;

public class LanguageRecyclerAdapter
        extends RecyclerView.Adapter<LanguageRecyclerAdapter.LanguageViewHolder> {

    private static final ArrayList<Language> languages = new ArrayList<Language>();

    public LanguageRecyclerAdapter() {

    }

    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_language_list, parent, false);
        return new LanguageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {

        Language currentLanguage = languages.get(position);

        holder.setData(currentLanguage, null);
    }

    @Override
    public int getItemCount() {
        return languages.size();
    }

    public void addItem(Language language) {
        languages.add(language);
        notifyItemInserted(languages.size() - 1);
    }

    public Language[] getLanguages() {
        return languages.toArray(new Language[0]);
    }

    static class LanguageViewHolder
            extends RecyclerView.ViewHolder
            implements AdapterView.OnItemSelectedListener {

        //private final Spinner spinner_language, spinner_speakingLevel, spinner_readingWritingLevel;
        private final LanguageItem languageItem;

        private final Language language;

        public LanguageViewHolder(@NonNull View itemView) {
            super(itemView);

            /*
            spinner_language = itemView.findViewById(R.id.item_language_spinner_language);
            spinner_speakingLevel = itemView.findViewById(R.id.item_language_spinner_speak_level);
            spinner_readingWritingLevel = itemView.findViewById(R.id.item_language_spinner_readwrite_level);

             String languageName = (String) spinner_language.getSelectedItem();
             int speakingLevel = spinner_speakingLevel.getSelectedItemPosition();
             int readingWritingLevel = spinner_readingWritingLevel.getSelectedItemPosition();

             language = new Language(
                     languageName,
                     Language.Level.findByIndex(speakingLevel),
                     Language.Level.findByIndex(readingWritingLevel)
             );

             spinner_language.setOnItemSelectedListener(this);
             spinner_speakingLevel.setOnItemSelectedListener(this);
             spinner_readingWritingLevel.setOnItemSelectedListener(this);
             */

            languageItem = itemView.findViewById(R.id.item_language_list);

            language = languageItem.getLanguage();

            languageItem.setOnItemSelectedListener(this);

        }

        public Language getLanguage() {
            return language;
        }

        public void setData(Language language, AdapterView.OnItemSelectedListener itemSelectedListener) {
            //this.spinner_speakingLevel.setOnItemSelectedListener(itemSelectedListener);
            //this.spinner_readingWritingLevel.setOnItemSelectedListener(itemSelectedListener);


            /*
            String[] langs = itemView.getContext().getResources().getStringArray(R.array.langs);
            ArrayList<String> lArray = new ArrayList<>(Arrays.asList(langs));
            spinner_language.setSelection(lArray.indexOf(language.getName()));
             */



            languageItem.init(language);

            // TODO: Load other attributes like above
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            /*
            String languageName = (String) spinner_language.getSelectedItem();
            int speakingLevel = spinner_speakingLevel.getSelectedItemPosition();
            int readingWritingLevel = spinner_readingWritingLevel.getSelectedItemPosition();

            language = new Language(
                    languageName,
                    Language.Level.findByIndex(speakingLevel),
                    Language.Level.findByIndex(readingWritingLevel)
            );

             */

            Language l = languageItem.getLanguage();
            languages.set(getAdapterPosition(), l);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
