package arc.haldun.ik.exceptions;

/**
 * Throws when at least one input is null is null.
 */

public class MissingInformationException extends Exception{

    private String[] missingFields;

    public MissingInformationException(String msg) {
        super(msg);
    }

    public MissingInformationException(String... missingFields) {
        this.missingFields = missingFields;

        String msg = "Eksik bilgiler var:\n";
        StringBuilder stringBuilder = new StringBuilder(msg);

        for (String missingField : missingFields) {

            // Give some space
            stringBuilder.append("\t");

            // Apply the string
            stringBuilder.append(missingField);
        }

        System.err.println(msg);
    }

    public String[] getMissingFields() {
        return missingFields;
    }
}
