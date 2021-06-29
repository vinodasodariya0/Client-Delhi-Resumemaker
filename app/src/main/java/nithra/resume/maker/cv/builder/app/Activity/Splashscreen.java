package nithra.resume.maker.cv.builder.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;

import nithra.resume.maker.cv.builder.app.MainActivity;
import nithra.resume.maker.cv.builder.app.R;


public class Splashscreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 500;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_splashscreen);



        new Handler().postDelayed(new Runnable() {

            public void run() {
                Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                intent.addFlags(335544320);
                Splashscreen.this.startActivity(intent);
                Splashscreen.this.finish();
            }
        }, (long) SPLASH_TIME_OUT);
    }
}
