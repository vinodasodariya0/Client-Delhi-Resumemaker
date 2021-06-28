package nithra.resume.maker.cv.builder.app.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import nithra.resume.maker.cv.builder.app.R;


public class Sample_Activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.sample2);
        final CardView cardView = (CardView) findViewById(R.id.ad);
        final CardView cardView2 = (CardView) findViewById(R.id.ad1);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.plan);
        ((TextView) findViewById(R.id.next)).setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                cardView.setVisibility(View.GONE);
                cardView2.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
            }
        });
        ((TextView) findViewById(R.id.next1)).setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                cardView.setVisibility(View.VISIBLE);
                cardView2.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);
            }
        });
    }

    public void openWhatsApp() {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+91 8667444548" + "&text="));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "it may be you dont have whats app", 1).show();
        }
    }
}
