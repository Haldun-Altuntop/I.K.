package arc.haldun.ik.utility;

import android.text.Editable;
import android.widget.EditText;
public class EditTextUtility {

    public static String getTextFromEditText(EditText editText) {
        Editable e = editText.getText();
        if (e == null) {
            return "";
        }
        return e.toString();
    }

}
