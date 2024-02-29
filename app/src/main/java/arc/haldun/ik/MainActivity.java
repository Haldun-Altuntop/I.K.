package arc.haldun.ik;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.appcompat.app.AppCompatActivity;

import arc.haldun.ik.applicationform.ApplicationFormActivity;

public class MainActivity extends AppCompatActivity {

    /**
     * This activity will be implemented as Splash Screen
     */

    HandlerThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread = new HandlerThread("Splash Screen Thread");
        thread.start();

        Handler handler = new Handler(thread.getLooper());
        handler.post(() -> {

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            startActivity(new Intent(getApplicationContext(), ApplicationFormActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

        });

    }
}