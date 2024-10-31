package arc.haldun.ik;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.ConnectException;

import arc.haldun.ik.applicationform.ApplicationFormActivity;
import arc.haldun.ik.database.ExceptionListenerHolder;
import arc.haldun.ik.database.mysql.Connector;
import arc.haldun.ik.error.ErrorActivity;
import arc.haldun.ik.exceptions.ExceptionListener;

/**
 * This activity will be implemented as Splash Screen
 */
public class MainActivity extends AppCompatActivity {

    HandlerThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initExceptionListener();

        thread = new HandlerThread("Splash Screen Thread");
        thread.start();

        Handler handler = new Handler(thread.getLooper());
        handler.post(() -> {

            Connector.connect(getResources().openRawResource(R.raw.config));

            if (!Connector.isConnected()) {
                ExceptionListenerHolder.exceptionListener.onException(new ConnectException("Bağlanılamadı"));
                finish();
                return;
            }

            startActivity(new Intent(getApplicationContext(), ApplicationFormActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

        });

    }

    private void initExceptionListener() {

        ExceptionListenerHolder.exceptionListener = e -> {

            if (e instanceof ConnectException) {
                Toast.makeText(getApplicationContext(), "Bağlanılamadı", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, ErrorActivity.class));
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (thread != null) {
            thread.quitSafely();
        }
    }
}