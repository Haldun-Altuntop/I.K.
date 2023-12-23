package arc.haldun.ik.applicationform.elements;

import androidx.annotation.NonNull;

public class Children {

    private int son, daughter;

    public Children(int son, int daughter) {
        this.son = son;
        this.daughter = daughter;
    }

    @NonNull
    @Override
    public String toString() {

        String classString;

        classString = "Kız: " + daughter + " " +
                    "Erkek: " + son;

        return classString;
    }
}
