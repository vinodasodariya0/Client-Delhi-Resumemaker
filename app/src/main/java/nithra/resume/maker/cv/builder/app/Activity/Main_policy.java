package nithra.resume.maker.cv.builder.app.Activity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.Utils;

public class Main_policy extends AppCompatActivity {

    @Override
    public void onCreate(Bundle bundle) {
        getWindow().setFlags(1024, 1024);
        super.onCreate(bundle);
        setRequestedOrientation(1);
        if (Build.VERSION.SDK_INT >= 11) {
            getWindow().setFlags(8192, 8192);
        }
        setContentView(R.layout.activity_main_policy);
        WebView webView = (WebView) findViewById(R.id.webb);
        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.loadUrl("https://www.nithra.mobi/privacy.php");
        webView.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View view) {
                return true;
            }
        });
        webView.setWebViewClient(new WebViewClient() {

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                Main_policy.this.runOnUiThread(new Runnable() {

                    public void run() {
                        Utils.mProgress(Main_policy.this, "Loading....", false).show();
                    }
                });
                super.onPageStarted(webView, str, bitmap);
            }

            public void onPageFinished(WebView webView, String str) {
                try {
                    Utils.mProgress.dismiss();
                } catch (Exception unused) {
                }
                super.onPageFinished(webView, str);
            }
        });
    }
}
