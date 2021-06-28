package nithra.resume.maker.cv.builder.app.Fcm;

import android.content.Context;
import android.media.ExifInterface;
import android.net.ParseException;
import android.os.Build;

import java.io.PrintStream;
import nithra.resume.maker.cv.builder.app.HttpHandler;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.SharedPreference;
import nithra.resume.maker.cv.builder.app.Utils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ServerUtilities {
    private static JSONArray jArray;
    private static String result;

    public static void gcmpost(String str, String str2, String str3, int i, Context context) {
        SharedPreference sharedPreference = new SharedPreference();
        HttpHandler httpHandler = new HttpHandler();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("email", str2);
            jSONObject.put("regId", str);
            jSONObject.put("vname", str3);
            jSONObject.put("vcode", i + "");
            jSONObject.put("andver", Build.VERSION.RELEASE);
            jSONObject.put("sw", "" + context.getResources().getString(R.string.screen_identification));
            jSONObject.put("asw", sharedPreference.getString(context, "smallestWidth"));
            jSONObject.put("w", sharedPreference.getString(context, "widthPixels"));
            jSONObject.put("h", sharedPreference.getString(context, "heightPixels"));
            jSONObject.put("d", sharedPreference.getString(context, "density"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        result = httpHandler.makeServiceCall("https://www.nithra.mobi/appgcm/gcmresumeindia/register.php", jSONObject);
        PrintStream printStream = System.out;
        printStream.println("response : " + result);
        try {
            PrintStream printStream2 = System.out;
            printStream2.println("ERROR----" + result);
            if (result == null) {
                PrintStream printStream3 = System.out;
                printStream3.println("ERROR----" + result + "1");
                return;
            }
            PrintStream printStream4 = System.out;
            printStream4.println("ERROR----" + result + "2");
            jArray = new JSONArray(result);
            PrintStream printStream5 = System.out;
            printStream5.println("result---/" + result);
            for (int i2 = 0; i2 < jArray.length(); i2++) {
                sharedPreference.putInt(context, "isvalid", jArray.getJSONObject(i2).getInt("isvalid"));
                sharedPreference.putInt(context, "vcode", Utils.versioncode_get(context));
                sharedPreference.putInt(context, "fcm_update", Utils.versioncode_get(context));
            }
        } catch (JSONException unused) {
            PrintStream printStream6 = System.out;
            printStream6.println("ERROR----" + result + "3");
        } catch (ParseException unused2) {
            PrintStream printStream7 = System.out;
            printStream7.println("ERROR----" + result + "4");
        }
    }

    public static void gcmupdate(Context context, String str, int i, String str2) {
        SharedPreference sharedPreference = new SharedPreference();
        HttpHandler httpHandler = new HttpHandler();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("vname", str);
            jSONObject.put("vcode", Integer.toString(i));
            jSONObject.put("email", "" + Utils.android_id(context));
            jSONObject.put("regid", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        result = httpHandler.makeServiceCall("https://www.nithra.mobi/appgcm/gcmresumeindia/update.php", jSONObject);
        PrintStream printStream = System.out;
        printStream.println("response : " + result);
        try {
            PrintStream printStream2 = System.out;
            printStream2.println("ERROR----" + result);
            if (result == null) {
                PrintStream printStream3 = System.out;
                printStream3.println("ERROR----" + result + "1");
                return;
            }
            PrintStream printStream4 = System.out;
            printStream4.println("ERROR----" + result + "2");
            jArray = new JSONArray(result);
            PrintStream printStream5 = System.out;
            printStream5.println("result---/" + result);
            for (int i2 = 0; i2 < jArray.length(); i2++) {
                jArray.getJSONObject(i2).getInt("isvalid");
                sharedPreference.putInt(context, "fcm_update", Utils.versioncode_get(context));
            }
        } catch (JSONException unused) {
            PrintStream printStream6 = System.out;
            printStream6.println("ERROR----" + result + "3");
        } catch (ParseException unused2) {
            PrintStream printStream7 = System.out;
            printStream7.println("ERROR----" + result + "4");
        }
    }
}
