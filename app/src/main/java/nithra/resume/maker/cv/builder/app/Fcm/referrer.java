package nithra.resume.maker.cv.builder.app.Fcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import nithra.resume.maker.cv.builder.app.HttpHandler;
import nithra.resume.maker.cv.builder.app.Utils;
import org.json.JSONException;
import org.json.JSONObject;

public class referrer extends BroadcastReceiver {
    String comp = "";
    String medium = "";
    String source = "";

    public void onReceive(final Context context, Intent intent) {
        String string;
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        Bundle extras = intent.getExtras();
        if (!(extras == null || (string = extras.getString("referrer")) == null || string.length() <= 0)) {
            String[] split = string.split("&");
            for (int i = 0; i < split.length; i++) {
                if (i == 0) {
                    this.source = split[i].substring(split[i].indexOf("=") + 1);
                } else if (i == 1) {
                    this.medium = split[i].substring(split[i].indexOf("=") + 1);
                } else if (i == 2) {
                    this.comp = split[i].substring(split[i].indexOf("=") + 1);
                }
            }
            if (isNetworkAvailable(context)) {
                new AsyncTask<String, String, String>() {
                    /* class nithra.resume.maker.cv.builder.app.Fcm.referrer.AnonymousClass1 */


                    public String doInBackground(String... strArr) {
                        referrer referrer = referrer.this;
                        referrer.send(context, referrer.source, referrer.this.medium, referrer.this.comp);
                        return null;
                    }
                }.execute(new String[0]);
            }
        }
    }

    public void send(Context context, String str, String str2, String str3) {
        HttpHandler httpHandler = new HttpHandler();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("app", "Resume_India");
            jSONObject.put("ref", str);
            jSONObject.put("mm", str2);
            jSONObject.put("cn", str3);
            jSONObject.put("email", Utils.android_id(context));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpHandler.makeServiceCall("https://nithra.mobi/apps/referrer.php", jSONObject);
    }

    private boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
