package arc.haldun.ik.exceptions;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;

@Deprecated(since = "This feature is on development")
public class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private final Context context;

    public UncaughtExceptionHandler(Context context) {
        this.context = context;
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {

        showToast(e.getMessage());

    }

    private void showToast(final String message) {
        new Thread() {
            @Override
            public void run() {
                // UI işlemleri ana thread üzerinde yapılmalıdır
                Looper.prepare();


                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                /*
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                dialogBuilder
                        .setTitle("Beklenmeyen Hata")
                        .setMessage(message)
                        .setPositiveButton("Rapotla", null);

                AlertDialog dialog = dialogBuilder.create();
                dialog.show();

                 */

                Looper.loop();
            }
        }.start();
    }
}
