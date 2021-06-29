package nithra.resume.maker.cv.builder.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.PointerIconCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.Key;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import nithra.resume.maker.cv.builder.app.Activity.Main_policy;
import nithra.resume.maker.cv.builder.app.Activity.Splashscreen;
import nithra.resume.maker.cv.builder.app.Drawer.AdvanceDrawerLayout;
import nithra.resume.maker.cv.builder.app.Fcm.Config;
import nithra.resume.maker.cv.builder.app.Fcm.Noti_Fragment;
import nithra.resume.maker.cv.builder.app.Fcm.ServerUtilities;
import nithra.resume.maker.cv.builder.app.Models.DB_Items;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static final int RC_REQUEST = 10001;
    static final String TAG1 = "TrivialDrive";
    static AdView adView = null;
    static NativeContentAdView adView_con = null;
    static NativeContentAdView adView_con1 = null;
    static NativeAppInstallAdView adView_ins = null;
    static NativeAppInstallAdView adView_ins1 = null;
    public static AdView adView_rect = null;
    public static LinearLayout add = null;
    static FrameLayout add_natt = null;
    static FloatingActionButton add_new = null;
    static AppCompatTextView add_new_text = null;
    static CardView add_profile = null;
    public static LinearLayout add_rect = null;
    static boolean ads_type = false;
    public static int backupp = 1001;
    static AppCompatEditText en_name = null;
    public static LinearLayout new_add = null;
    static RecyclerView profile_recycle = null;
    private static AdRequest request = null;
    public static AdRequest request_rect = null;
    static SharedPreference sp = new SharedPreference();
    public static int uploadd = 1002;
    String[] PERMISSIONS = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    String academic_table = "academic_table";
    String achievement_table = "achievement_table";
    LinearLayout adds_lay;
    int back_flag = 0;
    LinearLayout backup;
    String cocurricular_table = "cocurricular_table";
    String cover_body_table = "cover_body_table";
    String cover_table = "cover_table";
    Button crt_btn;
    String extra_table = "extra_table";
    String extracurricular_table = "extracurricular_table";
    LinearLayout feedback;
    int feedcheck = 0;
    String feedstr = "";
    String file;
    String file1;
    String hobbies_table = "hobbies_table";
    String industrial_table = "industrial_table";
    String inplant_table = "inplant_table";
    String interest_table = "interest_table";
    InterstitialAd interstitialAd;
    Profile_Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    LinearLayout more_apps;
    SQLiteDatabase myDB;
    InputStream myInputs;
    FileOutputStream myOutput;
    ArrayList<DB_Items> names = new ArrayList<>();
    TextView nocount;
    ImageView noti;
    LinearLayout notification;
    LinearLayout notification_off;
    String objective_table = "objective_table";
    String objmain_table = "objmain_table";
    String personal_info = "personal_info";
    LinearLayout privacy;
    String profile_table = "profile_table";
    String project_table = "project_table";
    LinearLayout rate_us;
    String reference_table = "reference_table";
    String search_table = "search_table";
    LinearLayout share_app;
    String skill_table = "skill_table";
    String strength_table = "strength_table";
    String tablenew = "noti_cal";
    String title_table = "title_table";
    CardView view_profile;
    String work_table = "work_table";

    public static void AdvancedAdrequest(final Context context) {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        ViewGroup viewGroup3;
        ViewGroup viewGroup4;
        sp.putInt(context, "add_loadd_1", 0);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AdLoader.Builder builder = new AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110");
        adView_con = (NativeContentAdView) layoutInflater.inflate(R.layout.ad_content, (ViewGroup) null);
        adView_con1 = (NativeContentAdView) layoutInflater.inflate(R.layout.ad_content1, (ViewGroup) null);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {

            @Override
            // com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                MainActivity.ads_type = true;
                MainActivity.populateContentAdView(nativeContentAd, MainActivity.adView_con);
                MainActivity.populateContentAdView(nativeContentAd, MainActivity.adView_con1);
            }
        });
        adView_ins = (NativeAppInstallAdView) layoutInflater.inflate(R.layout.ad_app_install, (ViewGroup) null);
        adView_ins1 = (NativeAppInstallAdView) layoutInflater.inflate(R.layout.ad_app_install1, (ViewGroup) null);
        builder.forAppInstallAd(new NativeAppInstallAd.OnAppInstallAdLoadedListener() {

            @Override
            // com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener
            public void onAppInstallAdLoaded(NativeAppInstallAd nativeAppInstallAd) {
                MainActivity.ads_type = false;
                MainActivity.populateAppInstallAdView(nativeAppInstallAd, MainActivity.adView_ins);
                MainActivity.populateAppInstallAdView(nativeAppInstallAd, MainActivity.adView_ins1);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(true).build()).setAdChoicesPlacement(0).build());
        builder.withAdListener(new AdListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass3 */

            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(int i) {
                MainActivity.AdvancedAdrequest(context);
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                super.onAdLoaded();
                MainActivity.load_addFromMain_natt(context, MainActivity.add_natt);
                MainActivity.load_addFromMain_nattsmall(context, MainActivity.add_natt);
                MainActivity.sp.putInt(context, "add_loadd_1", 1);
            }
        }).build().loadAd(new AdRequest.Builder().build());
        NativeContentAdView nativeContentAdView = adView_con;
        if (!(nativeContentAdView == null || (viewGroup4 = (ViewGroup) nativeContentAdView.getParent()) == null)) {
            viewGroup4.removeAllViews();
        }
        NativeAppInstallAdView nativeAppInstallAdView = adView_ins;
        if (!(nativeAppInstallAdView == null || (viewGroup3 = (ViewGroup) nativeAppInstallAdView.getParent()) == null)) {
            viewGroup3.removeAllViews();
        }
        NativeContentAdView nativeContentAdView2 = adView_con1;
        if (!(nativeContentAdView2 == null || (viewGroup2 = (ViewGroup) nativeContentAdView2.getParent()) == null)) {
            viewGroup2.removeAllViews();
        }
        NativeAppInstallAdView nativeAppInstallAdView2 = adView_ins1;
        if (nativeAppInstallAdView2 != null && (viewGroup = (ViewGroup) nativeAppInstallAdView2.getParent()) != null) {
            viewGroup.removeAllViews();
        }
    }

    public static void load_addFromMain_natt(Context context, FrameLayout frameLayout) {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        if (sp.getInt(context, "add_loadd_1") == 1) {
            add_natt = frameLayout;
            try {
                if (ads_type) {
                    if (!(adView_con == null || (viewGroup2 = (ViewGroup) adView_con.getParent()) == null)) {
                        viewGroup2.removeAllViews();
                    }
                    frameLayout.setVisibility(View.VISIBLE);
                    frameLayout.removeAllViews();
                    frameLayout.addView(adView_con);
                    return;
                }
                if (!(adView_ins == null || (viewGroup = (ViewGroup) adView_ins.getParent()) == null)) {
                    viewGroup.removeAllViews();
                }
                frameLayout.setVisibility(View.VISIBLE);
                frameLayout.removeAllViews();
                frameLayout.addView(adView_ins);
            } catch (Exception unused) {
            }
        }
    }

    public static void load_addFromMain_nattsmall(Context context, FrameLayout frameLayout) {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        if (sp.getInt(context, "add_loadd_1") == 1) {
            add_natt = frameLayout;
            try {
                if (ads_type) {
                    if (!(adView_con1 == null || (viewGroup2 = (ViewGroup) adView_con1.getParent()) == null)) {
                        viewGroup2.removeAllViews();
                    }
                    frameLayout.setVisibility(View.VISIBLE);
                    frameLayout.removeAllViews();
                    frameLayout.addView(adView_con1);
                    return;
                }
                if (!(adView_ins1 == null || (viewGroup = (ViewGroup) adView_ins1.getParent()) == null)) {
                    viewGroup.removeAllViews();
                }
                frameLayout.setVisibility(View.VISIBLE);
                frameLayout.removeAllViews();
                frameLayout.addView(adView_ins1);
            } catch (Exception unused) {
            }
        }
    }


    public static void populateContentAdView(NativeContentAd nativeContentAd, NativeContentAdView nativeContentAdView) {
        nativeContentAdView.setHeadlineView(nativeContentAdView.findViewById(R.id.contentad_headline));
        nativeContentAdView.setImageView(nativeContentAdView.findViewById(R.id.contentad_image));
        nativeContentAdView.setBodyView(nativeContentAdView.findViewById(R.id.contentad_body));
        nativeContentAdView.setCallToActionView(nativeContentAdView.findViewById(R.id.contentad_call_to_action));
        nativeContentAdView.setLogoView(nativeContentAdView.findViewById(R.id.contentad_logo));
        nativeContentAdView.setAdvertiserView(nativeContentAdView.findViewById(R.id.contentad_advertiser));
        ((TextView) nativeContentAdView.getHeadlineView()).setText("" + ((Object) nativeContentAd.getHeadline()));
        ((TextView) nativeContentAdView.getBodyView()).setText("" + ((Object) nativeContentAd.getBody()));
        ((AppCompatButton) nativeContentAdView.getCallToActionView()).setText("" + ((Object) nativeContentAd.getCallToAction()));
        ((TextView) nativeContentAdView.getAdvertiserView()).setText("" + ((Object) nativeContentAd.getAdvertiser()));
        List<NativeAd.Image> images = nativeContentAd.getImages();
        if (images.size() > 0) {
            ((ImageView) nativeContentAdView.getImageView()).setImageDrawable(images.get(0).getDrawable());
        } else {
            nativeContentAdView.getImageView().setVisibility(View.GONE);
        }
        NativeAd.Image logo = nativeContentAd.getLogo();
        if (logo == null) {
            nativeContentAdView.getLogoView().setVisibility(View.GONE);
        } else {
            ((ImageView) nativeContentAdView.getLogoView()).setImageDrawable(logo.getDrawable());
            nativeContentAdView.getLogoView().setVisibility(View.VISIBLE);
        }
        nativeContentAdView.setNativeAd(nativeContentAd);
    }


    public static void populateAppInstallAdView(NativeAppInstallAd nativeAppInstallAd, NativeAppInstallAdView nativeAppInstallAdView) {
        nativeAppInstallAd.getVideoController().setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass4 */

            @Override // com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks
            public void onVideoEnd() {
                super.onVideoEnd();
            }
        });
        nativeAppInstallAdView.setHeadlineView(nativeAppInstallAdView.findViewById(R.id.appinstall_headline));
        nativeAppInstallAdView.setBodyView(nativeAppInstallAdView.findViewById(R.id.appinstall_desc));
        nativeAppInstallAdView.setCallToActionView(nativeAppInstallAdView.findViewById(R.id.appinstall_call_to_action));
        nativeAppInstallAdView.setIconView(nativeAppInstallAdView.findViewById(R.id.appinstall_app_icon));
        nativeAppInstallAdView.setMediaView((MediaView) nativeAppInstallAdView.findViewById(R.id.appinstall_media));
        ((TextView) nativeAppInstallAdView.getHeadlineView()).setText(nativeAppInstallAd.getHeadline());
        ((TextView) nativeAppInstallAdView.getBodyView()).setText(nativeAppInstallAd.getBody());
        ((AppCompatButton) nativeAppInstallAdView.getCallToActionView()).setText(nativeAppInstallAd.getCallToAction());
        NativeAd.Image icon = nativeAppInstallAd.getIcon();
        if (icon == null) {
            nativeAppInstallAdView.getIconView().setVisibility(View.INVISIBLE);
        } else {
            ((ImageView) nativeAppInstallAdView.getIconView()).setImageDrawable(icon.getDrawable());
            nativeAppInstallAdView.getIconView().setVisibility(View.VISIBLE);
        }
        nativeAppInstallAdView.setNativeAd(nativeAppInstallAd);
    }

    public static void load_addFromMain(Context context, LinearLayout linearLayout) {
        ViewGroup viewGroup;
        add = linearLayout;
        try {
            if (!(adView == null || (viewGroup = (ViewGroup) adView.getParent()) == null)) {
                viewGroup.removeAllViews();
            }
            if (sp.getInt(context, "addloded") == 1) {
                linearLayout.setVisibility(View.VISIBLE);
                linearLayout.removeAllViews();
                linearLayout.addView(adView);
            }
        } catch (Exception ignored) {
        }
    }

    public static void createFolder() {
        File file2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Nithra");
        if (!file2.exists()) {
            file2.mkdir();
        }
        File file3 = new File(file2 + "/ResumeBuilder");
        if (!file3.exists()) {
            file3.mkdir();
        }
    }

    public void load_add(LinearLayout linearLayout) {
        ViewGroup viewGroup;
        adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId("ca-app-pub-4267540560263635/5605553907");
        request = new AdRequest.Builder().build();
        sp.putInt(getApplicationContext(), "addloded", 0);
        adView.setAdListener(new AdListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass5 */

            @Override // com.google.android.gms.ads.AdListener
            public void onAdFailedToLoad(int i) {
            }

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                MainActivity.sp.putInt(MainActivity.this.getApplicationContext(), "addloded", 1);
                if (MainActivity.sp.getInt(MainActivity.this, "remove_ads_unlock") == 0) {
                    MainActivity.load_addFromMain(MainActivity.this, MainActivity.add);
                }
                super.onAdLoaded();
            }
        });
        adView.loadAd(request);
        AdView adView2 = adView;
        if (adView2 != null && (viewGroup = (ViewGroup) adView2.getParent()) != null) {
            viewGroup.removeAllViews();
        }
    }

    private void sendRegistrationToServer(String str) {
        String str2 = "TAG";
        Log.e(str2, "sendRegistrationToServer: " + str);
        PrintStream printStream = System.out;
        printStream.println("sendRegistrationToServer: " + str);
        new SharedPreference().putString(this, "token", str);
        ServerUtilities.gcmpost(str, Utils.android_id(this), Utils.versionname_get(this), Utils.versioncode_get(this), this);
    }

    private void storeRegIdInPref(String str) {
        SharedPreferences.Editor edit = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0).edit();
        edit.putString("regId", str);
        edit.commit();
    }


    @Override
    // android.support.v7.app.AppCompatActivity, android.support.v4.app.SupportActivity, android.support.v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);



        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            //Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        Log.e("TAG", "onComplete: "+token);
                        // Log and toast
                      /*  String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);*/
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();



                        storeRegIdInPref(token);
                        sendRegistrationToServer(token);
                        Intent intent = new Intent(Config.REGISTRATION_COMPLETE);
                        intent.putExtra("token", token);
                        LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);
                    }
                });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        add_profile = (CardView) findViewById(R.id.add_profile);
        en_name = (AppCompatEditText) findViewById(R.id.en_name);
        this.crt_btn = (Button) findViewById(R.id.crt_btn);
        new_add = (LinearLayout) findViewById(R.id.new_add);
        this.privacy = (LinearLayout) findViewById(R.id.privacy);
        this.backup = (LinearLayout) findViewById(R.id.backup);
        this.rate_us = (LinearLayout) findViewById(R.id.rate_us);
        this.share_app = (LinearLayout) findViewById(R.id.share_app);
        this.feedback = (LinearLayout) findViewById(R.id.feedback);
        this.more_apps = (LinearLayout) findViewById(R.id.more_apps);
        this.notification = (LinearLayout) findViewById(R.id.notification);
        this.notification_off = (LinearLayout) findViewById(R.id.notification_off);
        this.nocount = (TextView) findViewById(R.id.nocount);
        this.noti = (ImageView) findViewById(R.id.noti);
        this.adds_lay = (LinearLayout) findViewById(R.id.adds_lay);
        sp.putString(this, "copy", "no");
        add_new = (FloatingActionButton) findViewById(R.id.add_new);
        add_new_text = (AppCompatTextView) findViewById(R.id.add_new_text);
        profile_recycle = (RecyclerView) findViewById(R.id.profile_recycle);
        if (Build.VERSION.SDK_INT >= 28) {
            this.backup.setVisibility(View.GONE);
        } else {
            this.backup.setVisibility(View.VISIBLE);
        }
        setSupportActionBar(toolbar);
        this.myDB = openOrCreateDatabase("RESUME_BUILDER", 0, null);
        SQLiteDatabase sQLiteDatabase = this.myDB;
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + this.tablenew + " (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,message VARCHAR,date VARCHAR,time VARCHAR,isclose INT(4),isshow INT(4) default 0,type VARCHAR,bm VARCHAR,ntype VARCHAR,url VARCHAR);");
        SQLiteDatabase sQLiteDatabase2 = this.myDB;
        sQLiteDatabase2.execSQL("CREATE TABLE IF NOT EXISTS " + this.profile_table + " (profile_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,name VARCHAR,photo VARCHAR,sign VARCHAR);");
        SQLiteDatabase sQLiteDatabase3 = this.myDB;
        sQLiteDatabase3.execSQL("CREATE TABLE IF NOT EXISTS " + this.personal_info + " (personal_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,name VARCHAR,address VARCHAR,email VARCHAR,phone INT,dob VARCHAR,maritalstatus VARCHAR,language VARCHAR,nationality VARCHAR);");
        SQLiteDatabase sQLiteDatabase4 = this.myDB;
        sQLiteDatabase4.execSQL("CREATE TABLE IF NOT EXISTS " + this.academic_table + " (academic_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,course VARCHAR,institute VARCHAR,pertype VARCHAR,percentage VARCHAR,yop INT,extra_id INT);");
        SQLiteDatabase sQLiteDatabase5 = this.myDB;
        sQLiteDatabase5.execSQL("CREATE TABLE IF NOT EXISTS " + this.work_table + " (work_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,organization VARCHAR,designation VARCHAR,emptype VARCHAR,date_of_join VARCHAR,end_date VARCHAR,role VARCHAR,extra_id INT);");
        SQLiteDatabase sQLiteDatabase6 = this.myDB;
        sQLiteDatabase6.execSQL("CREATE TABLE IF NOT EXISTS " + this.project_table + " (project_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,title VARCHAR,discription VARCHAR,duration VARCHAR,role VARCHAR,company VARCHAR,team_size INT,extra_id INT);");
        SQLiteDatabase sQLiteDatabase7 = this.myDB;
        sQLiteDatabase7.execSQL("CREATE TABLE IF NOT EXISTS " + this.skill_table + " (skill_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,skill VARCHAR);");
        SQLiteDatabase sQLiteDatabase8 = this.myDB;
        sQLiteDatabase8.execSQL("CREATE TABLE IF NOT EXISTS " + this.interest_table + " (interest_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,interest VARCHAR);");
        SQLiteDatabase sQLiteDatabase9 = this.myDB;
        sQLiteDatabase9.execSQL("CREATE TABLE IF NOT EXISTS " + this.strength_table + " (strength_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,strength VARCHAR);");
        SQLiteDatabase sQLiteDatabase10 = this.myDB;
        sQLiteDatabase10.execSQL("CREATE TABLE IF NOT EXISTS " + this.hobbies_table + " (hobbies_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,hobbies VARCHAR);");
        SQLiteDatabase sQLiteDatabase11 = this.myDB;
        sQLiteDatabase11.execSQL("CREATE TABLE IF NOT EXISTS " + this.achievement_table + " (achieve_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,achieve VARCHAR);");
        SQLiteDatabase sQLiteDatabase12 = this.myDB;
        sQLiteDatabase12.execSQL("CREATE TABLE IF NOT EXISTS " + this.cocurricular_table + " (cocurricular_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,cocurricular VARCHAR);");
        SQLiteDatabase sQLiteDatabase13 = this.myDB;
        sQLiteDatabase13.execSQL("CREATE TABLE IF NOT EXISTS " + this.extracurricular_table + " (extracurricular_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,extracurricular VARCHAR);");
        SQLiteDatabase sQLiteDatabase14 = this.myDB;
        sQLiteDatabase14.execSQL("CREATE TABLE IF NOT EXISTS " + this.industrial_table + " (industrial_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,industrial VARCHAR);");
        SQLiteDatabase sQLiteDatabase15 = this.myDB;
        sQLiteDatabase15.execSQL("CREATE TABLE IF NOT EXISTS " + this.inplant_table + " (inplant_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,inplant VARCHAR);");
        SQLiteDatabase sQLiteDatabase16 = this.myDB;
        sQLiteDatabase16.execSQL("CREATE TABLE IF NOT EXISTS " + this.reference_table + " (reference_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,name VARCHAR,designation VARCHAR,organization VARCHAR,email VARCHAR,phone INT,extra_id INT);");
        SQLiteDatabase sQLiteDatabase17 = this.myDB;
        sQLiteDatabase17.execSQL("CREATE TABLE IF NOT EXISTS " + this.objective_table + " (objective_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,objective VARCHAR,date VARCHAR,place VARCHAR,declaration VARCHAR);");
        SQLiteDatabase sQLiteDatabase18 = this.myDB;
        sQLiteDatabase18.execSQL("CREATE TABLE IF NOT EXISTS " + this.objmain_table + " (objmain_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,objective_id INT,date VARCHAR,place VARCHAR,declar_id INT);");
        SQLiteDatabase sQLiteDatabase19 = this.myDB;
        sQLiteDatabase19.execSQL("CREATE TABLE IF NOT EXISTS " + this.extra_table + " (extra_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,table_id INT,table_name VARCHAR,title VARCHAR,value VARCHAR);");
        SQLiteDatabase sQLiteDatabase20 = this.myDB;
        sQLiteDatabase20.execSQL("CREATE TABLE IF NOT EXISTS " + this.search_table + " (search_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,table_name VARCHAR,title VARCHAR);");
        SQLiteDatabase sQLiteDatabase21 = this.myDB;
        sQLiteDatabase21.execSQL("CREATE TABLE IF NOT EXISTS " + this.cover_table + " (cover_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,date VARCHAR,address VARCHAR,body VARCHAR);");
        SQLiteDatabase sQLiteDatabase22 = this.myDB;
        sQLiteDatabase22.execSQL("CREATE TABLE IF NOT EXISTS " + this.title_table + " (title_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,profile_id INT,personal_title VARCHAR,academic_title VARCHAR,work_title VARCHAR,project_title VARCHAR,skill_title VARCHAR,interest_title VARCHAR,strength_title VARCHAR,hobbies_title VARCHAR,achieve_title VARCHAR,cocurricular_title VARCHAR,extracurricular_title VARCHAR,industrial_title VARCHAR,inplant_title VARCHAR,reference_title VARCHAR);");
        SQLiteDatabase sQLiteDatabase23 = this.myDB;
        sQLiteDatabase23.execSQL("CREATE TABLE IF NOT EXISTS " + this.cover_body_table + " (title_id integer NOT NULL PRIMARY KEY AUTOINCREMENT,cover_body VARCHAR);");
        MobileAds.initialize(this, "ca-app-pub-4267540560263635~6640658682");
        if (!sp.getBoolean(this, "install").booleanValue()) {
            privacy_dialog();
        }
        this.mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        profile_recycle.setLayoutManager(this.mLayoutManager);
        load_add(add);
        AdvancedAdrequest(this);
        this.interstitialAd = new InterstitialAd(this);
        this.interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        this.interstitialAd.loadAd(new AdRequest.Builder().build());
        adds(this.adds_lay);
        this.privacy.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass6 */

            public void onClick(View view) {
                ((AdvanceDrawerLayout) MainActivity.this.findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
                if (Utils.isNetworkAvailable(MainActivity.this)) {
                    MainActivity.this.startActivity(new Intent(MainActivity.this, Main_policy.class));
                    return;
                }
                Utils.toast_center(MainActivity.this, "Please check your internet connection");
            }
        });
        this.backup.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass7 */

            public void onClick(View view) {
                ((AdvanceDrawerLayout) MainActivity.this.findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
                MainActivity.this.Backup_dialog();
            }
        });
        this.rate_us.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass8 */

            public void onClick(View view) {
                ((AdvanceDrawerLayout) MainActivity.this.findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
                String packageName = MainActivity.this.getPackageName();
                PrintStream printStream = System.out;
                printStream.println("PackageName :" + packageName);
                MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
            }
        });
        this.share_app.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass9 */

            public void onClick(View view) {
                ((AdvanceDrawerLayout) MainActivity.this.findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.SUBJECT", "Resume Builder");
                intent.putExtra("android.intent.extra.TEXT", "To create a best professional resume reflecting your skills and abilities, Click here to download \n \n https://goo.gl/AJqYAx");
                MainActivity.this.startActivity(Intent.createChooser(intent, "Share via"));
            }
        });
        this.feedback.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass10 */

            public void onClick(View view) {
                ((AdvanceDrawerLayout) MainActivity.this.findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
                MainActivity.this.send_feed();
            }
        });
        this.more_apps.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass11 */

            public void onClick(View view) {
                ((AdvanceDrawerLayout) MainActivity.this.findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=Nithra"));
                MainActivity.this.startActivity(intent);
            }
        });
        this.notification.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass12 */

            public void onClick(View view) {
                ((AdvanceDrawerLayout) MainActivity.this.findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
                MainActivity.this.startActivity(new Intent(MainActivity.this, Noti_Fragment.class));
            }
        });
        this.notification_off.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass13 */

            public void onClick(View view) {
                ((AdvanceDrawerLayout) MainActivity.this.findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
                MainActivity.this.notification_on_off_dialog();
            }
        });
        this.noti.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass14 */

            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, Noti_Fragment.class));
            }
        });
        en_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass15 */

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                ((InputMethodManager) textView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(textView.getWindowToken(), 0);
                return true;
            }
        });
        this.crt_btn.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass16 */

            public void onClick(View view) {
                String trim = MainActivity.en_name.getText().toString().trim();
                if (trim.length() <= 0) {
                    MainActivity.en_name.setError("Enter Resume name !!!");
                    MainActivity.en_name.requestFocus();
                } else if (trim.contains("'")) {
                    MainActivity.en_name.setError("Please don't enter special characters in profile name");
                    MainActivity.en_name.requestFocus();
                } else if (trim.contains("/")) {
                    MainActivity.en_name.setError("Please don't enter special characters in profile name");
                    MainActivity.en_name.requestFocus();
                } else {
                    SQLiteDatabase sQLiteDatabase = MainActivity.this.myDB;
                    Cursor rawQuery = sQLiteDatabase.rawQuery("Select upper(name) as name from profile_table where upper(name)='" + MainActivity.en_name.getText().toString().toUpperCase().replaceAll("'", "''") + "'", null);
                    if (rawQuery.getCount() > 0) {
                        rawQuery.moveToFirst();
                        MainActivity.en_name.setError("Profile name already exist !!!");
                    } else {
                        if (MainActivity.sp.getString(MainActivity.this, "copy").equals("no")) {
                            SQLiteDatabase sQLiteDatabase2 = MainActivity.this.myDB;
                            sQLiteDatabase2.execSQL("INSERT INTO profile_table(name,photo,sign) values ('" + MainActivity.en_name.getText().toString().replaceAll("'", "''") + "','null','null')");
                            MainActivity.sp.putString(MainActivity.this, "picturepath", "");
                            MainActivity.sp.putString(MainActivity.this, "signpath", "");
                            MainActivity.this.Check();
                            Cursor rawQuery2 = MainActivity.this.myDB.rawQuery("Select * from profile_table ORDER by profile_id DESC LIMIT '1'", null);
                            if (rawQuery2.getCount() > 0) {
                                rawQuery2.moveToFirst();
                                MainActivity.add_profile.setVisibility(View.GONE);
                                MainActivity.profile_recycle.setVisibility(View.VISIBLE);
                                MainActivity.new_add.setVisibility(View.VISIBLE);
                            } else {
                                MainActivity.add_profile.setVisibility(View.VISIBLE);
                                MainActivity.profile_recycle.setVisibility(View.GONE);
                                MainActivity.new_add.setVisibility(View.GONE);
                            }
                            rawQuery2.close();
                        } else {
                            String string = MainActivity.sp.getString(MainActivity.this, "copy_profile_id");
                            SQLiteDatabase sQLiteDatabase3 = MainActivity.this.myDB;
                            sQLiteDatabase3.execSQL("INSERT INTO profile_table (name,photo,sign) SELECT '" + MainActivity.en_name.getText().toString().replaceAll("'", "''") + "' ,photo,sign FROM profile_table WHERE profile_id = " + string + "");
                            MainActivity.sp.putString(MainActivity.this, "picturepath", "");
                            MainActivity.sp.putString(MainActivity.this, "signpath", "");
                            MainActivity.this.Check();
                            Cursor rawQuery3 = MainActivity.this.myDB.rawQuery("Select profile_id from profile_table", null);
                            if (rawQuery3.getCount() > 0) {
                                rawQuery3.moveToFirst();
                                MainActivity.add_profile.setVisibility(View.GONE);
                                MainActivity.profile_recycle.setVisibility(View.VISIBLE);
                                MainActivity.new_add.setVisibility(View.VISIBLE);
                            } else {
                                MainActivity.add_profile.setVisibility(View.VISIBLE);
                                MainActivity.profile_recycle.setVisibility(View.GONE);
                                MainActivity.new_add.setVisibility(View.GONE);
                            }
                            rawQuery3.moveToLast();
                            int i = rawQuery3.getInt(rawQuery3.getColumnIndex("profile_id"));
                            SQLiteDatabase sQLiteDatabase4 = MainActivity.this.myDB;
                            Cursor rawQuery4 = sQLiteDatabase4.rawQuery("select * from personal_info where profile_id='" + string + "'", null);
                            if (rawQuery4.getCount() > 0) {
                                for (int i2 = 0; i2 < rawQuery4.getCount(); i2++) {
                                    rawQuery4.moveToPosition(i2);
                                    SQLiteDatabase sQLiteDatabase5 = MainActivity.this.myDB;
                                    sQLiteDatabase5.execSQL("INSERT INTO personal_info (profile_id, name, address, email, phone,language,maritalstatus) VALUES ('" + i + "', '" + rawQuery4.getString(rawQuery4.getColumnIndex(AppMeasurementSdk.ConditionalUserProperty.NAME)).replaceAll("'", "''") + "', '" + rawQuery4.getString(rawQuery4.getColumnIndex("address")).replaceAll("'", "''") + "', '" + rawQuery4.getString(rawQuery4.getColumnIndex("email")).replaceAll("'", "''") + "', '" + rawQuery4.getString(rawQuery4.getColumnIndex("phone")).replaceAll("'", "''") + "', '" + rawQuery4.getString(rawQuery4.getColumnIndex("language")).replaceAll("'", "''") + "', '" + rawQuery4.getString(rawQuery4.getColumnIndex("maritalstatus")).replaceAll("'", "''") + "');");
                                    Cursor rawQuery5 = MainActivity.this.myDB.rawQuery("select * from personal_info", null);
                                    rawQuery5.moveToLast();
                                    int i3 = rawQuery5.getInt(rawQuery5.getColumnIndex("personal_id"));
                                    SQLiteDatabase sQLiteDatabase6 = MainActivity.this.myDB;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("INSERT INTO extra_table (profile_id, table_id, table_name,title,value) SELECT '");
                                    sb.append(i);
                                    sb.append("', '");
                                    sb.append(i3);
                                    sb.append("', table_name,title,value FROM extra_table WHERE profile_id = '");
                                    sb.append(string);
                                    sb.append("' and table_name='personal_info'");
                                    sQLiteDatabase6.execSQL(sb.toString());
                                    rawQuery5.close();
                                }
                            }
                            rawQuery4.close();
                            SQLiteDatabase sQLiteDatabase7 = MainActivity.this.myDB;
                            Cursor rawQuery6 = sQLiteDatabase7.rawQuery("select * from academic_table where profile_id='" + string + "'", null);
                            if (rawQuery6.getCount() > 0) {
                                for (int i4 = 0; i4 < rawQuery6.getCount(); i4++) {
                                    rawQuery6.moveToPosition(i4);
                                    SQLiteDatabase sQLiteDatabase8 = MainActivity.this.myDB;
                                    sQLiteDatabase8.execSQL("INSERT INTO academic_table (profile_id, course, institute,percentage,yop,extra_id) VALUES ('" + i + "', '" + rawQuery6.getString(rawQuery6.getColumnIndex("course")).replaceAll("'", "''") + "', '" + rawQuery6.getString(rawQuery6.getColumnIndex("institute")).replaceAll("'", "''") + "', '" + rawQuery6.getString(rawQuery6.getColumnIndex("percentage")).replaceAll("'", "''") + "', '" + rawQuery6.getString(rawQuery6.getColumnIndex("yop")).replaceAll("'", "''") + "', '" + rawQuery6.getString(rawQuery6.getColumnIndex("extra_id")).replaceAll("'", "''") + "');");
                                    Cursor rawQuery7 = MainActivity.this.myDB.rawQuery("select * from academic_table", null);
                                    rawQuery7.moveToLast();
                                    int i5 = rawQuery7.getInt(rawQuery7.getColumnIndex("academic_id"));
                                    int i6 = rawQuery6.getInt(rawQuery6.getColumnIndex("academic_id"));
                                    SQLiteDatabase sQLiteDatabase9 = MainActivity.this.myDB;
                                    sQLiteDatabase9.execSQL("INSERT INTO extra_table (profile_id, table_id, table_name,title,value) SELECT '" + i + "', '" + i5 + "', table_name,title,value FROM extra_table WHERE profile_id = '" + string + "' and table_name='academic_table' and table_id='" + i6 + "'");
                                    rawQuery7.close();
                                }
                            }
                            rawQuery6.close();
                            SQLiteDatabase sQLiteDatabase10 = MainActivity.this.myDB;
                            Cursor rawQuery8 = sQLiteDatabase10.rawQuery("select * from work_table where profile_id='" + string + "'", null);
                            if (rawQuery8.getCount() > 0) {
                                for (int i7 = 0; i7 < rawQuery8.getCount(); i7++) {
                                    rawQuery8.moveToPosition(i7);
                                    String replaceAll = rawQuery8.getString(rawQuery8.getColumnIndex("role")).replaceAll("'", "''");
                                    if (replaceAll == null) {
                                        replaceAll = "";
                                    }
                                    SQLiteDatabase sQLiteDatabase11 = MainActivity.this.myDB;
                                    sQLiteDatabase11.execSQL("INSERT INTO work_table (profile_id, organization, designation,emptype,date_of_join,end_date,role,extra_id) VALUES ('" + i + "', '" + rawQuery8.getString(rawQuery8.getColumnIndex("organization")).replaceAll("'", "''") + "', '" + rawQuery8.getString(rawQuery8.getColumnIndex("designation")).replaceAll("'", "''") + "', '" + rawQuery8.getString(rawQuery8.getColumnIndex("emptype")).replaceAll("'", "''") + "', '" + rawQuery8.getString(rawQuery8.getColumnIndex("date_of_join")).replaceAll("'", "''") + "', '" + rawQuery8.getString(rawQuery8.getColumnIndex("end_date")).replaceAll("'", "''") + "', '" + replaceAll + "', '" + rawQuery8.getString(rawQuery8.getColumnIndex("extra_id")).replaceAll("'", "''") + "');");
                                    Cursor rawQuery9 = MainActivity.this.myDB.rawQuery("select * from work_table", null);
                                    rawQuery9.moveToLast();
                                    int i8 = rawQuery9.getInt(rawQuery9.getColumnIndex("work_id"));
                                    int i9 = rawQuery8.getInt(rawQuery8.getColumnIndex("work_id"));
                                    SQLiteDatabase sQLiteDatabase12 = MainActivity.this.myDB;
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("INSERT INTO extra_table (profile_id, table_id, table_name,title,value) SELECT '");
                                    sb2.append(i);
                                    sb2.append("', '");
                                    sb2.append(i8);
                                    sb2.append("', table_name,title,value FROM extra_table WHERE profile_id = '");
                                    sb2.append(string);
                                    sb2.append("' and table_name='work_table' and table_id='");
                                    sb2.append(i9);
                                    sb2.append("'");
                                    sQLiteDatabase12.execSQL(sb2.toString());
                                    rawQuery9.close();
                                }
                            }
                            rawQuery8.close();
                            SQLiteDatabase sQLiteDatabase13 = MainActivity.this.myDB;
                            Cursor rawQuery10 = sQLiteDatabase13.rawQuery("select * from project_table where profile_id='" + string + "'", null);
                            if (rawQuery10.getCount() > 0) {
                                for (int i10 = 0; i10 < rawQuery10.getCount(); i10++) {
                                    rawQuery10.moveToPosition(i10);
                                    String replaceAll2 = rawQuery10.getString(rawQuery10.getColumnIndex("company")).replaceAll("'", "''");
                                    String replaceAll3 = rawQuery10.getString(rawQuery10.getColumnIndex("team_size")).replaceAll("'", "''");
                                    if (replaceAll2 == null) {
                                        replaceAll2 = "";
                                    }
                                    if (replaceAll3 == null) {
                                        replaceAll3 = "";
                                    }
                                    SQLiteDatabase sQLiteDatabase14 = MainActivity.this.myDB;
                                    sQLiteDatabase14.execSQL("INSERT INTO project_table (profile_id,title,discription,duration,role,company,team_size,extra_id) VALUES ('" + i + "', '" + rawQuery10.getString(rawQuery10.getColumnIndex("title")).replaceAll("'", "''") + "', '" + rawQuery10.getString(rawQuery10.getColumnIndex("discription")).replaceAll("'", "''") + "', '" + rawQuery10.getString(rawQuery10.getColumnIndex("duration")).replaceAll("'", "''") + "', '" + rawQuery10.getString(rawQuery10.getColumnIndex("role")).replaceAll("'", "''") + "', '" + replaceAll2 + "', '" + replaceAll3 + "', '" + rawQuery10.getString(rawQuery10.getColumnIndex("extra_id")).replaceAll("'", "''") + "');");
                                    Cursor rawQuery11 = MainActivity.this.myDB.rawQuery("select * from project_table", null);
                                    rawQuery11.moveToLast();
                                    int i11 = rawQuery11.getInt(rawQuery11.getColumnIndex("project_id"));
                                    int i12 = rawQuery10.getInt(rawQuery10.getColumnIndex("project_id"));
                                    SQLiteDatabase sQLiteDatabase15 = MainActivity.this.myDB;
                                    StringBuilder sb3 = new StringBuilder();
                                    sb3.append("INSERT INTO extra_table (profile_id, table_id, table_name,title,value) SELECT '");
                                    sb3.append(i);
                                    sb3.append("', '");
                                    sb3.append(i11);
                                    sb3.append("', table_name,title,value FROM extra_table WHERE profile_id = '");
                                    sb3.append(string);
                                    sb3.append("' and table_name='project_table' and table_id='");
                                    sb3.append(i12);
                                    sb3.append("'");
                                    sQLiteDatabase15.execSQL(sb3.toString());
                                    rawQuery11.close();
                                }
                            }
                            rawQuery10.close();
                            SQLiteDatabase sQLiteDatabase16 = MainActivity.this.myDB;
                            sQLiteDatabase16.execSQL("INSERT INTO skill_table (profile_id,skill) SELECT " + i + ",skill FROM skill_table WHERE profile_id = " + string + "");
                            SQLiteDatabase sQLiteDatabase17 = MainActivity.this.myDB;
                            sQLiteDatabase17.execSQL("INSERT INTO interest_table (profile_id,interest) SELECT " + i + ",interest FROM interest_table WHERE profile_id = " + string + "");
                            SQLiteDatabase sQLiteDatabase18 = MainActivity.this.myDB;
                            sQLiteDatabase18.execSQL("INSERT INTO strength_table (profile_id,strength) SELECT " + i + ",strength FROM strength_table WHERE profile_id = " + string + "");
                            SQLiteDatabase sQLiteDatabase19 = MainActivity.this.myDB;
                            sQLiteDatabase19.execSQL("INSERT INTO hobbies_table (profile_id,hobbies) SELECT " + i + ",hobbies FROM hobbies_table WHERE profile_id = " + string + "");
                            SQLiteDatabase sQLiteDatabase20 = MainActivity.this.myDB;
                            sQLiteDatabase20.execSQL("INSERT INTO achievement_table (profile_id,achieve) SELECT " + i + ",achieve FROM achievement_table WHERE profile_id = " + string + "");
                            SQLiteDatabase sQLiteDatabase21 = MainActivity.this.myDB;
                            sQLiteDatabase21.execSQL("INSERT INTO cocurricular_table (profile_id,cocurricular) SELECT " + i + ",cocurricular FROM cocurricular_table WHERE profile_id = " + string + "");
                            SQLiteDatabase sQLiteDatabase22 = MainActivity.this.myDB;
                            sQLiteDatabase22.execSQL("INSERT INTO extracurricular_table (profile_id,extracurricular) SELECT " + i + ",extracurricular FROM extracurricular_table WHERE profile_id = " + string + "");
                            SQLiteDatabase sQLiteDatabase23 = MainActivity.this.myDB;
                            sQLiteDatabase23.execSQL("INSERT INTO industrial_table (profile_id,industrial) SELECT " + i + ",industrial FROM industrial_table WHERE profile_id = " + string + "");
                            SQLiteDatabase sQLiteDatabase24 = MainActivity.this.myDB;
                            sQLiteDatabase24.execSQL("INSERT INTO inplant_table (profile_id,inplant) SELECT " + i + ",inplant FROM inplant_table WHERE profile_id = " + string + "");
                            SQLiteDatabase sQLiteDatabase25 = MainActivity.this.myDB;
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append("select * from reference_table where profile_id='");
                            sb4.append(string);
                            sb4.append("'");
                            Cursor rawQuery12 = sQLiteDatabase25.rawQuery(sb4.toString(), null);
                            if (rawQuery12.getCount() > 0) {
                                for (int i13 = 0; i13 < rawQuery12.getCount(); i13++) {
                                    rawQuery12.moveToPosition(i13);
                                    SQLiteDatabase sQLiteDatabase26 = MainActivity.this.myDB;
                                    sQLiteDatabase26.execSQL("INSERT INTO reference_table (profile_id,name,designation,organization,email,phone,extra_id) VALUES ('" + i + "', '" + rawQuery12.getString(rawQuery12.getColumnIndex(AppMeasurementSdk.ConditionalUserProperty.NAME)).replaceAll("'", "''") + "', '" + rawQuery12.getString(rawQuery12.getColumnIndex("designation")).replaceAll("'", "''") + "', '" + rawQuery12.getString(rawQuery12.getColumnIndex("organization")).replaceAll("'", "''") + "', '" + rawQuery12.getString(rawQuery12.getColumnIndex("email")).replaceAll("'", "''") + "', '" + rawQuery12.getString(rawQuery12.getColumnIndex("phone")).replaceAll("'", "''") + "', '" + rawQuery12.getString(rawQuery12.getColumnIndex("extra_id")).replaceAll("'", "''") + "');");
                                    Cursor rawQuery13 = MainActivity.this.myDB.rawQuery("select * from reference_table", null);
                                    rawQuery13.moveToLast();
                                    int i14 = rawQuery13.getInt(rawQuery13.getColumnIndex("reference_id"));
                                    int i15 = rawQuery12.getInt(rawQuery12.getColumnIndex("reference_id"));
                                    SQLiteDatabase sQLiteDatabase27 = MainActivity.this.myDB;
                                    StringBuilder sb5 = new StringBuilder();
                                    sb5.append("INSERT INTO extra_table (profile_id, table_id, table_name,title,value) SELECT '");
                                    sb5.append(i);
                                    sb5.append("', '");
                                    sb5.append(i14);
                                    sb5.append("', table_name,title,value FROM extra_table WHERE profile_id = '");
                                    sb5.append(string);
                                    sb5.append("' and table_name='reference_table' and table_id='");
                                    sb5.append(i15);
                                    sb5.append("'");
                                    sQLiteDatabase27.execSQL(sb5.toString());
                                    rawQuery13.close();
                                }
                            }
                            rawQuery12.close();
                            SQLiteDatabase sQLiteDatabase28 = MainActivity.this.myDB;
                            sQLiteDatabase28.execSQL("INSERT INTO objective_table (profile_id,objective, date, place, declaration) SELECT " + i + ",objective, date, place, declaration FROM objective_table WHERE profile_id = " + string + " ");
                            SQLiteDatabase sQLiteDatabase29 = MainActivity.this.myDB;
                            sQLiteDatabase29.execSQL("INSERT INTO objmain_table (profile_id,objective_id, date, place, declar_id) SELECT " + i + ",objective_id, date, place, declar_id FROM objmain_table WHERE profile_id = " + string + " ");
                            SQLiteDatabase sQLiteDatabase30 = MainActivity.this.myDB;
                            sQLiteDatabase30.execSQL("INSERT INTO search_table (profile_id,table_name, title) SELECT " + i + ",table_name, title FROM search_table WHERE profile_id = " + string + " ");
                            SQLiteDatabase sQLiteDatabase31 = MainActivity.this.myDB;
                            sQLiteDatabase31.execSQL("INSERT INTO cover_table (profile_id,date, address,body) SELECT " + i + ",date, address,body FROM cover_table WHERE profile_id = " + string + " ");
                            SQLiteDatabase sQLiteDatabase32 = MainActivity.this.myDB;
                            sQLiteDatabase32.execSQL("INSERT INTO title_table (profile_id,personal_title, academic_title,work_title,project_title,skill_title,interest_title,strength_title,hobbies_title,achieve_title,cocurricular_title,extracurricular_title,industrial_title,inplant_title,reference_title) SELECT " + i + ",personal_title, academic_title,work_title,project_title,skill_title,interest_title,strength_title,hobbies_title,achieve_title,cocurricular_title,extracurricular_title,industrial_title,inplant_title,reference_title FROM title_table WHERE profile_id = " + string + " ");
                            rawQuery3.close();
                        }
                        MainActivity.this.hideKeyboard();
                    }
                    rawQuery.close();
                }
            }
        });
        add_new.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass17 */

            public void onClick(View view) {
                MainActivity.sp.putString(MainActivity.this, "copy", "no");
                MainActivity.en_name.setText("");
                MainActivity.en_name.setError(null);
                MainActivity.add_profile.setVisibility(View.VISIBLE);
                MainActivity.profile_recycle.setVisibility(View.GONE);
                MainActivity.new_add.setVisibility(View.GONE);
            }
        });
        add_new_text.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass18 */

            public void onClick(View view) {
                MainActivity.sp.putString(MainActivity.this, "copy", "no");
                MainActivity.en_name.setText("");
                MainActivity.en_name.setError(null);
                MainActivity.add_profile.setVisibility(View.VISIBLE);
                MainActivity.profile_recycle.setVisibility(View.GONE);
                MainActivity.new_add.setVisibility(View.GONE);
            }
        });
        AdvanceDrawerLayout advanceDrawerLayout = (AdvanceDrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, advanceDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        advanceDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
        advanceDrawerLayout.setViewScale(GravityCompat.START, 0.9f);
        advanceDrawerLayout.setViewElevation(GravityCompat.START, 20.0f);
        advanceDrawerLayout.useCustomBehavior(GravityCompat.END);
        smallestWidth();
        this.mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass19 */

            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    //  FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                }
            }
        };
        SharedPreference sharedPreference = new SharedPreference();
        if (sharedPreference.getInt(this, "isvalid") == 0) {
            if (sharedPreference.getString(this, "token").length() > 0) {
                // String token = FirebaseInstanceId.getInstance().getToken();
                // new gcmpost_update2().execute(token);
            }
        } else if (sharedPreference.getInt(this, "fcm_update") < Utils.versioncode_get(this)) {
            //  String token2 = FirebaseInstanceId.getInstance().getToken();
            //new gcmpost_update1().execute(token2);
        }
    }



    private void hideKeyboard() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(en_name.getWindowToken(), 0);
        en_name.clearFocus();
    }

    public Animation zoomAnim() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.9f, 0.83f, 0.9f, 0.83f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimation.setDuration(500);
        scaleAnimation.setRepeatCount(-1);
        scaleAnimation.setRepeatMode(2);
        return scaleAnimation;
    }


    @Override // android.support.v4.app.FragmentActivity
    public void onResume() {
        Cursor rawQuery = this.myDB.rawQuery("Select * from profile_table", null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            add_profile.setVisibility(View.GONE);
            profile_recycle.setVisibility(View.VISIBLE);
            new_add.setVisibility(View.VISIBLE);
        } else {
            en_name.setText("");
            add_profile.setVisibility(View.VISIBLE);
            profile_recycle.setVisibility(View.GONE);
            new_add.setVisibility(View.GONE);
        }
        rawQuery.close();
        Check();
        Cursor rawQuery2 = this.myDB.rawQuery("Select id from noti_cal where isclose='0'", null);
        rawQuery2.moveToFirst();
        if (rawQuery2.getCount() == 0) {
            this.nocount.setVisibility(View.GONE);
            this.noti.setAnimation(null);
        } else if (rawQuery2.getCount() < 9) {
            this.noti.setAnimation(zoomAnim());
            this.nocount.setVisibility(View.VISIBLE);
            TextView textView = this.nocount;
            textView.setText("" + rawQuery2.getCount());
        } else if (rawQuery2.getCount() > 9) {
            this.noti.setAnimation(zoomAnim());
            this.nocount.setVisibility(View.VISIBLE);
            this.nocount.setText("9+");
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mRegistrationBroadcastReceiver, new IntentFilter(Config.REGISTRATION_COMPLETE));
        super.onResume();
    }


    @Override // android.support.v4.app.FragmentActivity
    public void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override // android.support.design.widget.NavigationView.OnNavigationItemSelectedListener
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (!(itemId == R.id.nav_camera || itemId == R.id.nav_gallery)) {
        }
        ((AdvanceDrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
        return true;
    }

    public void Check() {
        try {
            this.names.clear();
            Cursor rawQuery = this.myDB.rawQuery("SELECT * FROM profile_table", null);
            if (rawQuery.getCount() < 0) {
                add_new.setVisibility(View.GONE);
                add_new_text.setVisibility(View.GONE);
            } else {
                add_new.setVisibility(View.VISIBLE);
                add_new_text.setVisibility(View.VISIBLE);
            }
            if (rawQuery.moveToFirst()) {
                for (int i = 0; i < rawQuery.getCount(); i++) {
                    DB_Items dB_Items = new DB_Items();
                    dB_Items.setProfile_id(rawQuery.getInt(rawQuery.getColumnIndex("profile_id")));
                    dB_Items.setName(rawQuery.getString(rawQuery.getColumnIndex(AppMeasurementSdk.ConditionalUserProperty.NAME)));
                    dB_Items.setPhoto(rawQuery.getString(rawQuery.getColumnIndex("photo")));
                    this.names.add(dB_Items);
                    rawQuery.moveToNext();
                }
                rawQuery.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mAdapter = new Profile_Adapter(this, this.names);
        profile_recycle.setAdapter(this.mAdapter);
        this.mAdapter.notifyDataSetChanged();
    }

    public void send_feed() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar);
        dialog.setContentView(R.layout.send_feedback);
        final EditText editText = (EditText) dialog.findViewById(R.id.editText1);
        final EditText editText2 = (EditText) dialog.findViewById(R.id.mail_txt);
        editText2.requestFocus();
        ((AppCompatTextView) dialog.findViewById(R.id.polcy_txt)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass20 */

            public void onClick(View view) {
                if (Utils.isNetworkAvailable(MainActivity.this)) {
                    MainActivity.this.startActivity(new Intent(MainActivity.this, Main_policy.class));
                    return;
                }
                Utils.toast_center(MainActivity.this, "Please check your internet connection");
            }
        });
        ((ImageView) dialog.findViewById(R.id.btnSend)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass21 */

            public void onClick(View view) {
                if (editText.getText().toString().length() == 0) {
                    Utils.toast_center(MainActivity.this, "Please enter your feedback");
                    return;
                }
                ((InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(), 0);
                if (Utils.isNetworkAvailable(MainActivity.this)) {
                    final Handler r3 = new Handler() {
                        /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass21.AnonymousClass1 */

                        public void handleMessage(Message message) {
                            MainActivity.this.runOnUiThread(new Runnable() {
                                /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass21.AnonymousClass1.AnonymousClass1 */

                                public void run() {
                                }
                            });
                        }
                    };
                    new Thread() {
                        /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass21.AnonymousClass2 */

                        public void run() {
                            try {
                                MainActivity.this.send_feedback(editText.getText().toString(), editText2.getText().toString());
                            } catch (Exception unused) {
                            }
                            r3.sendEmptyMessage(0);
                        }
                    }.start();
                    Utils.toast_center(MainActivity.this, "Feedback sent, Thank you");
                    dialog.dismiss();
                    return;
                }
                Utils.toast_center(MainActivity.this, "Please check your internet connection");
            }
        });
        dialog.show();
    }

    public void send_feedback(String str, String str2) {
        try {
            HttpHandler httpHandler = new HttpHandler();
            String encode = URLEncoder.encode(str, Key.STRING_CHARSET_NAME);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", "Resume_India");
                jSONObject.put("feedback", encode);
                jSONObject.put("email", str2);
                jSONObject.put("vcode", "" + Utils.versioncode_get(this));
                jSONObject.put("model", Utils.getDeviceName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String makeServiceCall = httpHandler.makeServiceCall("https://www.nithra.mobi/apps/appfeedback.php", jSONObject);
            PrintStream printStream = System.out;
            printStream.println("response : " + makeServiceCall);
        } catch (IOException e2) {
            System.err.println(e2);
        }
    }

    @Override // android.support.v4.app.FragmentActivity
    public void onBackPressed() {
        AdvanceDrawerLayout advanceDrawerLayout = (AdvanceDrawerLayout) findViewById(R.id.drawer_layout);
        if (advanceDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            advanceDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (add_profile.isShown()) {
            Cursor rawQuery = this.myDB.rawQuery("Select * from profile_table ORDER by profile_id DESC LIMIT '1'", null);
            if (rawQuery.getCount() > 0) {
                rawQuery.moveToFirst();
                add_profile.setVisibility(View.GONE);
                profile_recycle.setVisibility(View.VISIBLE);
                new_add.setVisibility(View.VISIBLE);
            } else {
                en_name.setText("");
                add_profile.setVisibility(View.VISIBLE);
                profile_recycle.setVisibility(View.GONE);
                new_add.setVisibility(View.GONE);
                int i = this.back_flag;
                if (i != 0) {
                    exit_result_dialog();
                } else {
                    this.back_flag = i + 1;
                    Utils.toast_center(this, "Press one more time to exit");
                }
            }
            rawQuery.close();
        } else {
            int i2 = this.back_flag;
            if (i2 != 0) {
                exit_result_dialog();
                return;
            }
            this.back_flag = i2 + 1;
            Utils.toast_center(this, "Press one more time to exit");
        }
    }

    public void exit_result_dialog() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_exit);
        load_addFromMain_natt(this, (FrameLayout) dialog.findViewById(R.id.ads_lay));
        ((TextView) dialog.findViewById(R.id.yes)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass22 */

            public void onClick(View view) {
                MainActivity.this.ratefun();
                dialog.dismiss();
            }
        });
        ((TextView) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass23 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public void exit_dia() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.activity_splashscreen);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        ((RelativeLayout) dialog.findViewById(R.id.layy)).setBackgroundResource(R.drawable.exscrn);
        new Handler().postDelayed(new Runnable() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass24 */

            public void run() {
                MainActivity.this.finish();
                try {
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1500);
        dialog.show();
    }

    public void privacy_dialog() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        dialog.setContentView(R.layout.privacy_dia);
        dialog.setCancelable(false);
        TextView textView = (TextView) dialog.findViewById(R.id.btnok);
        ((TextView) dialog.findViewById(R.id.privacy)).setText("   Privacy & Terms");
        textView.setText("Agree & Continue");
        ((TextView) dialog.findViewById(R.id.editText1)).setText("Thanks for downloading Resume Builder \n\nBy clicking privacy tab you can read our privacy policy and agree to the terms of privacy policy to continue using Resume Builder Application.");
        textView.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass25 */

            public void onClick(View view) {
                MainActivity.sp.putBoolean(MainActivity.this, "install", true);
                dialog.dismiss();
            }
        });
        ((TextView) dialog.findViewById(R.id.btnSet)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass26 */

            public void onClick(View view) {
                if (Utils.isNetworkAvailable(MainActivity.this)) {
                    MainActivity.this.startActivity(new Intent(MainActivity.this, Main_policy.class));
                    return;
                }
                Utils.toast_center(MainActivity.this, "Please check your internet connection");
            }
        });
        dialog.show();
    }

    public void Backup_dialog() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        dialog.setContentView(R.layout.backup_dia);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        ((TextView) dialog.findViewById(R.id.backup_txt)).setText("Your information is backed-up in this internal path. Your can restore information even your profile is deleted.\n\nBackup path:/storage/emulated/0/Nithra/ResumeBuilder/RESUME_BUILDER.db");
        ((CardView) dialog.findViewById(R.id.backup_card)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass27 */

            public void onClick(View view) {
                if (!Utils.checkGET_StoragePermission(MainActivity.this)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(false);
                    builder.setTitle("Permission request");
                    if (MainActivity.sp.getInt(MainActivity.this, "permission") == 2) {
                        builder.setMessage("Enable the storage permission in app settings for backup your data");
                    } else {
                        builder.setMessage("Allow the storage permission for backup your data");
                    }
                    builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass27.AnonymousClass1 */

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            if (MainActivity.sp.getInt(MainActivity.this, "permission") == 2) {
                                Intent intent = new Intent();
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                intent.setData(Uri.fromParts("package", MainActivity.this.getApplicationContext().getPackageName(), null));
                                MainActivity.this.startActivity(intent);
                                return;
                            }
                            ActivityCompat.requestPermissions(MainActivity.this, MainActivity.this.PERMISSIONS, MainActivity.backupp);
                        }
                    });
                    builder.setNegativeButton("Later", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass27.AnonymousClass2 */

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                    return;
                }
                MainActivity.this.backup();
                dialog.dismiss();
            }
        });
        ((CardView) dialog.findViewById(R.id.restore_card)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass28 */

            public void onClick(View view) {
                if (!Utils.checkGET_StoragePermission(MainActivity.this)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(false);
                    builder.setTitle("Permission request");
                    if (MainActivity.sp.getInt(MainActivity.this, "permission") == 2) {
                        builder.setMessage("Enable the storage permission in app settings for restore your backup");
                    } else {
                        builder.setMessage("Allow the storage permission for restore your backup");
                    }
                    builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass28.AnonymousClass1 */

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            if (MainActivity.sp.getInt(MainActivity.this, "permission") == 2) {
                                Intent intent = new Intent();
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                intent.setData(Uri.fromParts("package", MainActivity.this.getApplicationContext().getPackageName(), null));
                                MainActivity.this.startActivity(intent);
                                return;
                            }
                            ActivityCompat.requestPermissions(MainActivity.this, MainActivity.this.PERMISSIONS, MainActivity.uploadd);
                        }
                    });
                    builder.setNegativeButton("Later", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass28.AnonymousClass2 */

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                    return;
                }
                dialog.dismiss();
                MainActivity.createFolder();
                if (new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/Nithra/ResumeBuilder/RESUME_BUILDER.db").exists()) {
                    MainActivity.this.restore();
                } else {
                    Toast.makeText(MainActivity.this.getApplicationContext(), "Restore unsuccessful! File not found! Directory does not exist?", Toast.LENGTH_LONG).show();
                }
            }
        });
        if (!isFinishing()) {
            dialog.show();
        }
    }

    public void backup() {
        String str;
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                str = getApplicationInfo().dataDir + "/databases/";
            } else {
                str = "/data/data/" + getPackageName() + "/databases/";
            }
            FileInputStream fileInputStream = new FileInputStream(str + "RESUME_BUILDER");
            File externalStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            File file2 = new File(externalStorageDirectory.getAbsolutePath() + "/Nithra/ResumeBuilder/");
            file2.mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file2, "RESUME_BUILDER.db"));
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    Toast.makeText(getApplicationContext(), "Backup done successfully!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        } catch (Exception e) {
            Log.d("CopyFileFromAssetsToSD", e.getMessage());
        }
    }



    private void restore() {
        String str;
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                str = getApplicationInfo().dataDir + "/databases/";
            } else {
                str = "/data/data/" + getPackageName() + "/databases/";
            }
            this.myOutput = new FileOutputStream(str + "RESUME_BUILDER");
            System.out.println("myOutput=" + this.myOutput);
            File externalStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            new File(externalStorageDirectory.getAbsolutePath() + "/Nithra/ResumeBuilder/").mkdirs();
            this.file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + "/Nithra";
            this.file1 = this.file + "/ResumeBuilder";
            File file2 = new File(this.file1);
            System.out.println("directory=" + file2);
            this.myInputs = new FileInputStream(file2 + "/RESUME_BUILDER.db");
            System.out.println("myInputs=" + this.myInputs);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = this.myInputs.read(bArr);
                if (read > 0) {
                    this.myOutput.write(bArr, 0, read);
                } else {
                    this.myOutput.flush();
                    this.myOutput.close();
                    this.myInputs.close();
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Restored Successfully ");
                    builder.setMessage("Click ok to restart the app.").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass29 */

                        public void onClick(DialogInterface dialogInterface, int i) {
                            SQLiteDatabase openOrCreateDatabase = MainActivity.this.openOrCreateDatabase("RESUME_BUILDER", 0, null);
                            openOrCreateDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + "noti_cal" + " (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,message VARCHAR,date VARCHAR,time VARCHAR,isclose INT(4),isshow INT(4) default 0,type VARCHAR,bm VARCHAR,ntype VARCHAR,url VARCHAR);");
                            Intent intent = new Intent(MainActivity.this.getBaseContext(), Splashscreen.class);
                            intent.addFlags(67108864);
                            intent.addFlags(32768);
                            intent.addFlags(524288);
                            MainActivity.this.finish();
                            MainActivity.this.startActivity(intent);
                        }
                    });
                    builder.create().show();
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Restore unsuccessful! File not found! Directory does not exist?", Toast.LENGTH_LONG).show();
            openOrCreateDatabase("RESUME_BUILDER", 0, null).execSQL("CREATE TABLE IF NOT EXISTS " + "noti_cal" + " (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,message VARCHAR,date VARCHAR,time VARCHAR,isclose INT(4),isshow INT(4) default 0,type VARCHAR,bm VARCHAR,ntype VARCHAR,url VARCHAR);");
            e.printStackTrace();
        } catch (IOException e2) {
            openOrCreateDatabase("RESUME_BUILDER", 0, null).execSQL("CREATE TABLE IF NOT EXISTS " + "noti_cal" + " (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,message VARCHAR,date VARCHAR,time VARCHAR,isclose INT(4),isshow INT(4) default 0,type VARCHAR,bm VARCHAR,ntype VARCHAR,url VARCHAR);");
            Toast.makeText(getApplicationContext(), "Restore unsuccessful.", Toast.LENGTH_SHORT).show();
            e2.printStackTrace();
        }
    }

    public void smallestWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        float f = displayMetrics.density;
        float min = Math.min(((float) i) / f, ((float) i2) / f);
        PrintStream printStream = System.out;
        printStream.println("Width Pixels : " + i);
        PrintStream printStream2 = System.out;
        printStream2.println("Height Pixels : " + i2);
        PrintStream printStream3 = System.out;
        printStream3.println("Dots per inch : " + displayMetrics.densityDpi);
        PrintStream printStream4 = System.out;
        printStream4.println("Scale Factor : " + f);
        PrintStream printStream5 = System.out;
        printStream5.println("Smallest Width : " + min);
        SharedPreference sharedPreference = sp;
        Context applicationContext = getApplicationContext();
        sharedPreference.putString(applicationContext, "smallestWidth", min + "");
        SharedPreference sharedPreference2 = sp;
        Context applicationContext2 = getApplicationContext();
        sharedPreference2.putString(applicationContext2, "widthPixels", i + "");
        SharedPreference sharedPreference3 = sp;
        Context applicationContext3 = getApplicationContext();
        sharedPreference3.putString(applicationContext3, "heightPixels", i2 + "");
        SharedPreference sharedPreference4 = sp;
        Context applicationContext4 = getApplicationContext();
        sharedPreference4.putString(applicationContext4, "density", displayMetrics.densityDpi + "");
    }

    public void ratefun() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.rate);
        final Dialog dialog2 = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog2.setContentView(R.layout.rate1);
        final Dialog dialog3 = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog3.setContentView(R.layout.send_feedback);
        dialog3.setCanceledOnTouchOutside(true);
        final EditText editText = (EditText) dialog3.findViewById(R.id.editText1);
        final EditText editText2 = (EditText) dialog3.findViewById(R.id.mail_txt);
        ((AppCompatTextView) dialog3.findViewById(R.id.polcy_txt)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass30 */

            public void onClick(View view) {
                if (Utils.isNetworkAvailable(MainActivity.this)) {
                    MainActivity.this.startActivity(new Intent(MainActivity.this, Main_policy.class));
                    return;
                }
                Utils.toast_center(MainActivity.this, "Please check your internet connection");
            }
        });
        editText2.requestFocus();
        editText.addTextChangedListener(new TextWatcher() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass31 */

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                editText.setError(null);
            }
        });
        ((ImageView) dialog3.findViewById(R.id.btnSend)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass32 */

            public void onClick(View view) {
                if (editText.getText().toString().length() == 0) {
                    Utils.toast_center(MainActivity.this, "Please enter your feedback");
                    return;
                }
                ((InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(), 0);
                MainActivity.this.feedstr = editText.getText().toString();
                if (Utils.isNetworkAvailable(MainActivity.this)) {
                    final Handler r3 = new Handler() {
                        /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass32.AnonymousClass1 */

                        public void handleMessage(Message message) {
                            MainActivity.this.runOnUiThread(new Runnable() {
                                /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass32.AnonymousClass1.AnonymousClass1 */

                                public void run() {
                                }
                            });
                        }
                    };
                    new Thread() {
                        /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass32.AnonymousClass2 */

                        public void run() {
                            try {
                                MainActivity.this.send_feedback(editText.getText().toString(), editText2.getText().toString());
                            } catch (Exception unused) {
                            }
                            r3.sendEmptyMessage(0);
                        }
                    }.start();
                    Utils.toast_center(MainActivity.this, "Feedback sent, Thank you");
                    dialog3.dismiss();
                    return;
                }
                Utils.toast_center(MainActivity.this, "Please check your internet connection");
            }
        });
        ((Button) dialog2.findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass33 */

            public void onClick(View view) {
                try {
                    String packageName = MainActivity.this.getPackageName();
                    MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
                } catch (Exception unused) {
                    System.out.println();
                }
                dialog2.dismiss();
            }
        });
        ((Button) dialog2.findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass34 */

            public void onClick(View view) {
                MainActivity.this.feedcheck = 1;
                dialog3.show();
                dialog2.dismiss();
            }
        });
        ((Button) dialog.findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass35 */

            public void onClick(View view) {
                MainActivity.this.feedcheck = 1;
                MainActivity.sp.putInt(MainActivity.this.getApplicationContext(), "ratecheckval", 1);
                dialog.dismiss();
                dialog2.show();
            }
        });
        ((Button) dialog.findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass36 */

            public void onClick(View view) {
                MainActivity.sp.putInt(MainActivity.this.getApplicationContext(), "ratecheckval", 1);
                MainActivity.this.feedcheck = 1;
                dialog.dismiss();
                dialog3.show();
            }
        });
        dialog2.setOnDismissListener(new DialogInterface.OnDismissListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass37 */

            public void onDismiss(DialogInterface dialogInterface) {
                if (MainActivity.this.feedcheck != 0) {
                    return;
                }
                if (MainActivity.this.interstitialAd.isLoaded()) {
                    MainActivity.this.interstitialAd.show();
                    MainActivity.this.interstitialAd.setAdListener(new AdListener() {
                        /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass37.AnonymousClass1 */

                        @Override // com.google.android.gms.ads.AdListener
                        public void onAdClosed() {
                            MainActivity.this.runOnUiThread(new Runnable() {
                                /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass37.AnonymousClass1.AnonymousClass1 */

                                public void run() {
                                    if (!MainActivity.this.isFinishing()) {
                                        MainActivity.this.exit_dia();
                                    }
                                }
                            });
                        }
                    });
                    return;
                }
                MainActivity.this.exit_dia();
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass38 */

            public void onDismiss(DialogInterface dialogInterface) {
                if (MainActivity.this.feedcheck != 0) {
                    return;
                }
                if (MainActivity.this.interstitialAd.isLoaded()) {
                    MainActivity.this.interstitialAd.show();
                    MainActivity.this.interstitialAd.setAdListener(new AdListener() {
                        /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass38.AnonymousClass1 */

                        @Override // com.google.android.gms.ads.AdListener
                        public void onAdClosed() {
                            MainActivity.this.runOnUiThread(new Runnable() {
                                /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass38.AnonymousClass1.AnonymousClass1 */

                                public void run() {
                                    if (!MainActivity.this.isFinishing()) {
                                        MainActivity.this.exit_dia();
                                    }
                                }
                            });
                        }
                    });
                    return;
                }
                MainActivity.this.exit_dia();
            }
        });
        dialog3.setOnDismissListener(new DialogInterface.OnDismissListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass39 */

            public void onDismiss(DialogInterface dialogInterface) {
                if (MainActivity.this.feedcheck != 1) {
                    MainActivity.this.exit_dia();
                } else if (MainActivity.this.interstitialAd.isLoaded()) {
                    MainActivity.this.interstitialAd.show();
                    MainActivity.this.interstitialAd.setAdListener(new AdListener() {
                        /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass39.AnonymousClass1 */

                        @Override // com.google.android.gms.ads.AdListener
                        public void onAdClosed() {
                            MainActivity.this.runOnUiThread(new Runnable() {
                                /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass39.AnonymousClass1.AnonymousClass1 */

                                public void run() {
                                    if (!MainActivity.this.isFinishing()) {
                                        MainActivity.this.exit_dia();
                                    }
                                }
                            });
                        }
                    });
                } else {
                    MainActivity.this.exit_dia();
                }
            }
        });
        if (Utils.rate_check(this)) {
            dialog.show();
        } else if (this.interstitialAd.isLoaded()) {
            this.interstitialAd.show();
            this.interstitialAd.setAdListener(new AdListener() {
                /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass40 */

                @Override // com.google.android.gms.ads.AdListener
                public void onAdClosed() {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass40.AnonymousClass1 */

                        public void run() {
                            if (!MainActivity.this.isFinishing()) {
                                MainActivity.this.exit_dia();
                            }
                        }
                    });
                }
            });
        } else {
            exit_dia();
        }
    }

    public void adds(final LinearLayout linearLayout) {
        AdView adView2 = new AdView(this);
        adView2.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        adView2.setAdSize(AdSize.SMART_BANNER);
        try {
            linearLayout.removeAllViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
        linearLayout.addView(adView2);
        adView2.setAdListener(new AdListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass41 */

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                linearLayout.setVisibility(View.VISIBLE);
            }
        });
        adView2.loadAd(new AdRequest.Builder().build());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    // android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback, android.support.v4.app.FragmentActivity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        switch (i) {
            case PointerIconCompat.TYPE_CONTEXT_MENU:
                if (iArr.length == 0 || iArr[0] != 0) {
                    Log.i("", "Permission has been denied by user");
                    if (!shouldShowRequestPermissionRationale(strArr[0])) {
                        sp.putInt(this, "permission", 2);
                        return;
                    } else if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(strArr[0])) {
                        sp.putInt(this, "permission", 0);
                        return;
                    } else {
                        return;
                    }
                } else {
                    Log.i("", "Permission has been granted by user");
                    sp.putInt(this, "permission", 1);
                    createFolder();
                    backup();
                    return;
                }
            case PointerIconCompat.TYPE_HAND:
                if (iArr.length != 0 && iArr[0] == 0) {
                    Log.i("", "Permission has been granted by user");
                    sp.putInt(this, "permission", 1);
                    createFolder();
                    if (new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/Nithra/ResumeBuilder/RESUME_BUILDER.db").exists()) {
                        restore();
                        return;
                    } else {
                        Toast.makeText(getApplicationContext(), "Restore unsuccessful! File not found! Directory does not exist?", Toast.LENGTH_LONG).show();
                        return;
                    }
                } else if (strArr.length != 0) {
                    Log.i("", "Permission has been denied by user");
                    if (!shouldShowRequestPermissionRationale(strArr[0])) {
                        sp.putInt(this, "permission", 2);
                        return;
                    } else if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(strArr[0])) {
                        sp.putInt(this, "permission", 0);
                        return;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void notification_on_off_dialog() {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_notifi_onoff);
        RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.noti_grp);
        RadioButton radioButton = (RadioButton) dialog.findViewById(R.id.noti_off);
        RadioButton radioButton2 = (RadioButton) dialog.findViewById(R.id.noti_on);
        if (!sp.getBoolean(this, "notificationstatus").booleanValue()) {
            radioButton2.setChecked(true);
            radioButton.setChecked(false);
        } else {
            radioButton2.setChecked(false);
            radioButton.setChecked(true);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /* class nithra.resume.maker.cv.builder.app.MainActivity.AnonymousClass42 */

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.noti_off) {
                    MainActivity.sp.putBoolean(MainActivity.this, "notificationstatus", true);
                    Utils.toast_center(MainActivity.this, "Notification disabled successfully...");
                } else if (i == R.id.noti_on) {
                    MainActivity.sp.putBoolean(MainActivity.this, "notificationstatus", false);
                    Utils.toast_center(MainActivity.this, "Notification enabled successfully...");
                }
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private class gcmpost_update1 extends AsyncTask<String, String, String> {
        private gcmpost_update1() {
        }


        public void onPreExecute() {
            super.onPreExecute();
        }


        public String doInBackground(String... strArr) {
            MainActivity mainActivity = MainActivity.this;
            ServerUtilities.gcmupdate(mainActivity, Utils.versionname_get(mainActivity), Utils.versioncode_get(MainActivity.this), strArr[0]);
            return "";
        }


        public void onPostExecute(String str) {
            super.onPostExecute(String.valueOf((Object) str));
            SharedPreference sharedPreference = new SharedPreference();
            MainActivity mainActivity = MainActivity.this;
            sharedPreference.putInt(mainActivity, "fcm_update", Utils.versioncode_get(mainActivity));
        }
    }

    private class gcmpost_update2 extends AsyncTask<String, String, String> {
        private gcmpost_update2() {
        }


        public void onPreExecute() {
            super.onPreExecute();
        }


        public String doInBackground(String... strArr) {
            ServerUtilities.gcmpost(strArr[0], Utils.android_id(MainActivity.this), Utils.versionname_get(MainActivity.this), Utils.versioncode_get(MainActivity.this), MainActivity.this);
            return "";
        }


        public void onPostExecute(String str) {
            super.onPostExecute(String.valueOf((Object) str));
            SharedPreference sharedPreference = new SharedPreference();
            MainActivity mainActivity = MainActivity.this;
            sharedPreference.putInt(mainActivity, "fcm_update", Utils.versioncode_get(mainActivity));
        }
    }
}
