package arc.haldun.ik.applicationform.elements;

import androidx.annotation.NonNull;

public class Language {

    private String name;
    private Level speakingLevel, readingWritingLevel;
    private String customLanguageName;

    public Language() {
        name = "";
        this.speakingLevel = Level.BEGINNER;
        this.readingWritingLevel = Level.BEGINNER;
    }

    public Language(String name, Level speakingLevel, Level readingWritingLevel) {
        this.name = name;
        this.speakingLevel = speakingLevel;
        this.readingWritingLevel = readingWritingLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Level getSpeakingLevel() {
        return speakingLevel;
    }

    public void setSpeakingLevel(Level speakingLevel) {
        this.speakingLevel = speakingLevel;
    }

    public Level getReadingWritingLevel() {
        return readingWritingLevel;
    }

    public void setReadingWritingLevel(Level readingWritingLevel) {
        this.readingWritingLevel = readingWritingLevel;
    }

    /**
     * Bu metot aslında Language.name ögesini döndürür.
     * Ancak diller listesinde olmayan bir dil girileceği zaman bu metot kullanılabilr.
     * @return Language's name
     */
    public String getCustomLanguageName() {
        return customLanguageName;
    }

    /**
     * Bu metot aslında Language.name ögesini değiştirir.
     * Ancak diller listesinde olmayan bir dil girileceği zaman bu metot kullanılabilr.
     */
    public void setCustomLanguageName(String customLanguageName) {
        this.name = customLanguageName;
        this.customLanguageName = customLanguageName;
    }

    public enum Level {
        BEGINNER("Başlangıç", 0),
        INTERMEDIATE("Orta", 1),
        ADVANCED("İleri", 2);

        private final String value;
        private final int index;

        Level(String value, int index) {
            this.value = value;
            this.index = index;
        }

        public String getString() {
            return value;
        }

        public int getIndex() {
            return index;
        }

        public static Level findByValue(String value) {

            Level[] levels = Level.values();

            for (Level level : levels) {
                if (level.getString().equals(value)) {
                    return level;
                }
            }

            return null;
        }

        public static Level findByIndex(int i) {
            Level[] levels = Level.values();

            for (Level level : levels) {
                if (level.getIndex() == i) {
                    return level;
                }
            }

            return null;
        }
    }

    @NonNull
    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        // Append language name
        stringBuilder
                .append("\t")
                .append("Dil: ")
                .append(getName())
                .append("\n");

        // Append speaking level
        stringBuilder
                .append("\t")
                .append("Konuşma Seviyesi: ")
                .append(getSpeakingLevel().getString())
                .append("\n");

        // Append reading/writing level
        stringBuilder
                .append("\t")
                .append("Okuma/Yazma Seviyesi: ")
                .append(getReadingWritingLevel().getString())
                .append("\n");

        return stringBuilder.toString();
    }
}
