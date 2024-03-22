package arc.haldun.ik.applicationform.fragments;

import android.util.Log;

import arc.haldun.ik.exceptions.MissingInformationException;

public abstract class Fragment extends androidx.fragment.app.Fragment {

    /**
     * Returns the fragment's information as string.
     * @return Information String
     */
    public abstract String collectInformationAsString() throws MissingInformationException;

    public void onShift() {
        Log.i("Fragment", "Fragment kayboluyor");

    }
}
