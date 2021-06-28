package nithra.resume.maker.cv.builder.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;


import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class Utils {
    public static ProgressDialog mProgress;

    public static ProgressDialog mProgress(Context context, String str, Boolean bool) {
        mProgress = new ProgressDialog(context);
        mProgress.setMessage(str);
        mProgress.setCancelable(bool.booleanValue());
        return mProgress;
    }

    public static String android_id(Context context) {
        PrintStream printStream = System.out;
        printStream.println("andrroid : " + Settings.Secure.getString(context.getContentResolver(), "android_id"));
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }

    public static boolean checkGET_StoragePermission(Context context) {
        boolean z = true;
        Boolean bool = true;
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                z = false;
            }
            bool = Boolean.valueOf(z);
        }
        return bool.booleanValue();
    }

    public static String getDeviceName() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        String str3 = Build.BRAND;
        String str4 = Build.PRODUCT;
        return str + "-" + str2 + "-" + str3 + "-" + str4;
    }

    public static int getDefaultActionBarSize(Context context) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{16843499});
        int dimension = (int) obtainStyledAttributes.getDimension(0, 0.0f);
        obtainStyledAttributes.recycle();
        return dimension;
    }

    public static void custom_tabs(Context context, String str) {
        try {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setExitAnimations(context, 17432578, 17432579);
            builder.build().launchUrl(context, Uri.parse(str));
        } catch (Exception e) {
            e.printStackTrace();
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setData(Uri.parse(str));
            context.startActivity(intent);
        }
    }

    public static void toast_center(Context context, String str) {
        Toast makeText = Toast.makeText(context, "" + str, 0);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    public static int versioncode_get(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        return packageInfo.versionCode;
    }

    public static String versionname_get(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        return packageInfo.versionName;
    }

    public static String am_pm1(int i, int i2) {
        String str;
        if (i >= 12) {
            i -= 12;
            str = "PM";
        } else {
            str = "AM";
        }
        if (i == 0) {
            i = 12;
        }
        return pad("" + i) + " : " + pad("" + i2) + " " + str;
    }

    public static String pad(String str) {
        if (str.length() != 1) {
            return str;
        }
        return "0" + str;
    }


    public static boolean rate_check(Context r8) {


        return false;

    }

    public static void date_put(Context context, String str, int i) {
        StringTokenizer stringTokenizer = new StringTokenizer(new SimpleDateFormat("dd/M/yyyy").format(new Date(Calendar.getInstance().getTimeInMillis() + (((long) i) * 86400000))), "/");
        int parseInt = Integer.parseInt(stringTokenizer.nextToken());
        int parseInt2 = Integer.parseInt(stringTokenizer.nextToken());
        int parseInt3 = Integer.parseInt(stringTokenizer.nextToken());
        StringBuilder sb = new StringBuilder();
        sb.append(parseInt);
        sb.append("/");
        sb.append(parseInt2 - 1);
        sb.append("/");
        sb.append(parseInt3);
        new SharedPreference().putString(context, str, sb.toString());
    }

    public static void alert(Activity activity, String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(str);
        builder.setNeutralButton("OK", (DialogInterface.OnClickListener) null);
        Log.d("", "Showing alert dialog: " + str);
        if (!activity.isFinishing()) {
            builder.create().show();
        }
    }
}
