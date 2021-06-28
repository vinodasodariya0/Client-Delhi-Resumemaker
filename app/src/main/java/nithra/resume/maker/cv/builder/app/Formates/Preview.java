package nithra.resume.maker.cv.builder.app.Formates;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;



import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

import com.google.android.gms.measurement.api.AppMeasurementSdk;


import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import nithra.resume.maker.cv.builder.app.BoxLoaderView;
import nithra.resume.maker.cv.builder.app.MainActivity;
import nithra.resume.maker.cv.builder.app.PdfWriter;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.SharedPreference;
import nithra.resume.maker.cv.builder.app.Utils;
import nithra.resume.maker.cv.builder.app.color.AmbilWarnaDialog;
import nithra.resume.maker.cv.builder.app.showcase.MaterialShowcaseSequence;
import nithra.resume.maker.cv.builder.app.showcase.MaterialShowcaseView;
import nithra.resume.maker.cv.builder.app.showcase.ShowcaseConfig;

public class Preview extends AppCompatActivity {
    static String SHOWCASE_ID = "PREVIEW";
    SQLiteDatabase DB;
    String achive;
    String achive2;
    String achive3;
    String achive4;
    String achive5;
    String achive6;
    String achive7;
    String achive8;
    String achive9;
    String address;
    LinearLayout ads_lay;
    String bg_color;
    String cocuri;
    String cocuri2;
    String cocuri3;
    String cocuri4;
    String cocuri5;
    String cocuri6;
    String cocuri7;
    String cocuri8;
    String cocuri9;
    int color;
    Dialog color_dialog;
    String cover_letter;
    String cover_letter2;
    String cover_letter3;
    String cover_letter4;
    String cover_letter5;
    String cover_letter6;
    String date;
    String declar;
    String declar2;
    String declar3;
    String declar4;
    String declar5;
    String declar6;
    String declar7;
    String declar8;
    String declar9;
    String designation;
    String dob;
    String education;
    String education2;
    String education3;
    String education4;
    String education5;
    String education6;
    String education7;
    String education8;
    String education9;
    String email;
    String experience;
    String experience2;
    String experience3;
    String experience4;
    String experience5;
    String experience6;
    String experience7;
    String experience8;
    String experience9;
    String extra;
    String extra2;
    String extra3;
    String extra4;
    String extra5;
    String extra6;
    String extra7;
    String extra8;
    String extra9;
    String fileName;
    TextView generat_txt;
    String hobbi;
    String hobbi2;
    String hobbi3;
    String hobbi4;
    String hobbi5;
    String hobbi6;
    String hobbi7;
    String hobbi8;
    String hobbi9;
    String image;
    String image2;
    String image3;
    String image4;
    String indrust;
    String indrust2;
    String indrust3;
    String indrust4;
    String indrust5;
    String indrust6;
    String indrust7;
    String indrust8;
    String indrust9;
    String info;
    String info2;
    String info3;
    String info4;
    String info5;
    String info6;
    String info7;
    String info8;
    String info9;
    String inplant;
    String inplant2;
    String inplant3;
    String inplant4;
    String inplant5;
    String inplant6;
    String inplant7;
    String inplant8;
    String inplant9;
    String intrest;
    String intrest2;
    String intrest3;
    String intrest4;
    String intrest5;
    String intrest6;
    String intrest7;
    String intrest8;
    String intrest9;
    String lang;
    WebView mWebView;
    String name;
    String objective;
    String objective2;
    String objective3;
    String objective4;
    String objective5;
    String objective6;
    String objective7;
    String objective8;
    String objective9;
    String personal;
    String phoneno;
    ImageView picker;
    String place;
    String pro_table;
    String profile3;
    String project;
    String project2;
    String project3;
    String project4;
    String project5;
    String project6;
    String project7;
    String project8;
    String project9;
    String reference;
    String reference2;
    String reference3;
    String reference4;
    String reference5;
    String reference6;
    String reference7;
    String reference8;
    String reference9;
    CardView save;
    String sign;
    String sign2;
    String sign_btm_name;
    String sign_btm_name1;
    String sign_name;
    String skill;
    String skill2;
    String skill3;
    String skill4;
    String skill5;
    String skill6;
    String skill7;
    String skill8;
    String skill9;
    SharedPreference sp = new SharedPreference();
    String status;
    String strength;
    String strength2;
    String strength3;
    String strength4;
    String strength5;
    String strength6;
    String strength7;
    String strength8;
    String strength9;
    String sub_achive;
    String sub_cocuri;
    String sub_edu;
    String sub_exp;
    String sub_extra;
    String sub_hobbi;
    String sub_indust;
    String sub_inplant;
    String sub_intrest;
    String sub_skill;
    String sub_strength;
    String sub_work;
    String tit_color;
    String title;
    List<String> titles = new ArrayList();
    Dialog wait_dialog;

    public static File getExternalBreezyDirectory() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/Nithra/ResumeBuilder");
        PrintStream printStream = System.out;
        printStream.println("dir==1" + file);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.SupportActivity, android.support.v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        setContentView(R.layout.preview_activity);
        this.mWebView = (WebView) findViewById(R.id.webview);
        this.ads_lay = (LinearLayout) findViewById(R.id.ads_lay);
        this.save = (CardView) findViewById(R.id.save);
        this.generat_txt = (TextView) findViewById(R.id.generat_txt);
        this.picker = (ImageView) findViewById(R.id.picker);
        this.titles.clear();
        this.titles.add("Formal Resume");
        this.titles.add("Expert Resume");
        this.titles.add("Innovative Resume");
        this.titles.add("Professional Resume");
        this.titles.add("Traditional Resume");
        this.titles.add("Simple Resume");
        this.titles.add("Effective Resume");
        this.titles.add("Classic Resume");
        this.titles.add("Graduate Resume");
        ((AppCompatTextView) findViewById(R.id.title_name)).setText(this.titles.get(this.sp.getInt(this, "key")));
        this.DB = openOrCreateDatabase("RESUME_BUILDER", 0, null);
        this.mWebView.getSettings().setAllowFileAccess(true);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setSupportZoom(true);
        this.mWebView.getSettings().setBuiltInZoomControls(true);
        this.mWebView.getSettings().setDisplayZoomControls(true);
        this.mWebView.getSettings().setLoadWithOverviewMode(true);
        this.mWebView.getSettings().setUseWideViewPort(true);
        this.mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Formates.Preview.AnonymousClass1 */

            public boolean onLongClick(View view) {
                return true;
            }
        });
        this.color = R.color.colorPrimary;
        Showcase();
        first_load();
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        if (this.sp.getInt(this, "key") == 0) {
            str = resueme();
        } else if (this.sp.getInt(this, "key") == 1) {
            str = resueme1();
        } else if (this.sp.getInt(this, "key") == 2) {
            str = resueme2();
        } else if (this.sp.getInt(this, "key") == 3) {
            str = resueme3();
        } else if (this.sp.getInt(this, "key") == 4) {
            str = resueme4();
        } else if (this.sp.getInt(this, "key") == 5) {
            str = resueme5();
        } else if (this.sp.getInt(this, "key") == 6) {
            str = resueme6();
        } else if (this.sp.getInt(this, "key") == 7) {
            str = resueme7();
        } else if (this.sp.getInt(this, "key") == 8) {
            str = resueme8();
        } else {
            str = "";
        }
        this.mWebView.loadDataWithBaseURL("file:///android_asset/", str, "text/html", "utf-8", null);
        this.save.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Formates.Preview.AnonymousClass2 */

            public void onClick(View view) {
                if (Preview.this.sp.getInt(Preview.this, "permissiond") == 1) {
                    try {
                        File externalStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                        new File(externalStorageDirectory.getAbsolutePath() + "/Nithra/ResumeBuilder/").mkdirs();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Preview.this.createPdfFile();
                    Preview.this.wait_dialog.show();
                    return;
                }
                Preview.this.PermissionFun();
            }
        });
        this.picker.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Formates.Preview.AnonymousClass3 */

            public void onClick(View view) {
                if (Preview.this.sp.getInt(Preview.this, "key") == 0) {
                    Preview.this.sp.putString(Preview.this, "type", "text");
                    Preview.this.openDialog(false);
                } else if (Preview.this.sp.getInt(Preview.this, "key") == 1) {
                    Preview.this.color_dialog.show();
                } else if (Preview.this.sp.getInt(Preview.this, "key") == 2) {
                    Preview.this.color_dialog.show();
                } else if (Preview.this.sp.getInt(Preview.this, "key") == 3) {
                    Preview.this.sp.putString(Preview.this, "type", "text");
                    Preview.this.openDialog(false);
                } else if (Preview.this.sp.getInt(Preview.this, "key") == 4) {
                    Preview.this.sp.putString(Preview.this, "type", "text");
                    Preview.this.openDialog(false);
                } else if (Preview.this.sp.getInt(Preview.this, "key") == 5) {
                    Preview.this.sp.putString(Preview.this, "type", "text");
                    Preview.this.openDialog(false);
                } else if (Preview.this.sp.getInt(Preview.this, "key") == 6) {
                    Preview.this.sp.putString(Preview.this, "type", "text");
                    Preview.this.openDialog(false);
                } else if (Preview.this.sp.getInt(Preview.this, "key") == 7) {
                    Preview.this.sp.putString(Preview.this, "type", "text");
                    Preview.this.openDialog(false);
                } else if (Preview.this.sp.getInt(Preview.this, "key") == 8) {
                    Preview.this.sp.putString(Preview.this, "type", "text");
                    Preview.this.openDialog(false);
                }
            }
        });
        this.color_dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        this.color_dialog.setContentView(R.layout.dialog_color_view);
        ((LinearLayout) this.color_dialog.findViewById(R.id.text_lay)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Formates.Preview.AnonymousClass4 */

            public void onClick(View view) {
                Preview.this.sp.putString(Preview.this, "type", "text");
                Preview.this.openDialog(false);
            }
        });
        ((LinearLayout) this.color_dialog.findViewById(R.id.border_lay)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Formates.Preview.AnonymousClass5 */

            public void onClick(View view) {
                Preview.this.sp.putString(Preview.this, "type", "bg");
                Preview.this.openDialog(false);
            }
        });
        this.color_dialog.setCancelable(false);
        this.color_dialog.setCanceledOnTouchOutside(true);
        this.wait_dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        this.wait_dialog.setContentView(R.layout.dialog_load);
        BoxLoaderView boxLoaderView = (BoxLoaderView) this.wait_dialog.findViewById(R.id.boxer);
        this.wait_dialog.setCancelable(false);
        this.wait_dialog.setCanceledOnTouchOutside(true);
    }

    private void first_load() {
        String str;
        String string;
        String string2;
        String string3;
        String string4;
        String string5;
        String string6;
        String string7;
        String string8;
        String string9;
        String string10;
        String string11;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String string12;
        String str7;
        String str8;
        String str9;
        String string13;
        String string14;
        Preview preview = this;
        preview.name = "";
        preview.sign_btm_name = "";
        preview.sign_btm_name1 = "";
        preview.sign_name = "";
        preview.image = "";
        preview.sign = "";
        preview.email = "";
        preview.phoneno = "";
        preview.status = "";
        preview.dob = "";
        preview.lang = "";
        preview.address = "";
        preview.cover_letter = "";
        preview.title = "";
        preview.personal = "";
        preview.pro_table = "";
        preview.objective = "";
        preview.experience = "";
        preview.sub_exp = "";
        preview.project = "";
        preview.sub_work = "";
        preview.education = "";
        preview.sub_edu = "";
        preview.skill = "";
        preview.sub_skill = "";
        preview.strength = "";
        preview.sub_strength = "";
        preview.intrest = "";
        preview.sub_intrest = "";
        preview.indrust = "";
        preview.sub_indust = "";
        preview.inplant = "";
        preview.sub_inplant = "";
        preview.achive = "";
        preview.sub_achive = "";
        preview.cocuri = "";
        preview.sub_cocuri = "";
        preview.extra = "";
        preview.sub_extra = "";
        preview.hobbi = "";
        preview.sub_hobbi = "";
        preview.declar = "";
        preview.date = "";
        preview.place = "";
        preview.info = "";
        preview.reference = "";
        preview.designation = "";
        preview.skill2 = "";
        preview.strength2 = "";
        preview.intrest2 = "";
        preview.info2 = "";
        preview.hobbi2 = "";
        preview.cover_letter2 = "";
        preview.objective2 = "";
        preview.experience2 = "";
        preview.project2 = "";
        preview.education2 = "";
        preview.indrust2 = "";
        preview.inplant2 = "";
        preview.sign2 = "";
        preview.achive2 = "";
        preview.cocuri2 = "";
        preview.extra2 = "";
        preview.declar2 = "";
        preview.image2 = "";
        preview.reference2 = "";
        preview.objective3 = "";
        preview.experience3 = "";
        preview.project3 = "";
        preview.education3 = "";
        preview.indrust3 = "";
        preview.inplant3 = "";
        preview.achive3 = "";
        preview.cover_letter3 = "";
        preview.cover_letter6 = "";
        preview.cocuri3 = "";
        preview.extra3 = "";
        preview.declar3 = "";
        preview.image3 = "";
        preview.info3 = "";
        preview.skill3 = "";
        preview.strength3 = "";
        preview.intrest3 = "";
        preview.profile3 = "";
        preview.hobbi3 = "";
        preview.reference3 = "";
        preview.objective4 = "";
        preview.experience4 = "";
        preview.project4 = "";
        preview.education4 = "";
        preview.skill4 = "";
        preview.strength4 = "";
        preview.intrest4 = "";
        preview.cover_letter4 = "";
        preview.indrust4 = "";
        preview.inplant4 = "";
        preview.achive4 = "";
        preview.cocuri4 = "";
        preview.extra4 = "";
        preview.hobbi4 = "";
        preview.info4 = "";
        preview.declar4 = "";
        preview.image4 = "";
        preview.reference4 = "";
        preview.objective5 = "";
        preview.experience5 = "";
        preview.project5 = "";
        preview.education5 = "";
        preview.skill5 = "";
        preview.strength5 = "";
        preview.intrest5 = "";
        preview.cover_letter5 = "";
        preview.indrust5 = "";
        preview.inplant5 = "";
        preview.achive5 = "";
        preview.cocuri5 = "";
        preview.extra5 = "";
        preview.hobbi5 = "";
        preview.info5 = "";
        preview.declar5 = "";
        preview.reference5 = "";
        preview.objective6 = "";
        preview.experience6 = "";
        preview.project6 = "";
        preview.education6 = "";
        preview.skill6 = "";
        preview.strength6 = "";
        preview.intrest6 = "";
        preview.indrust6 = "";
        preview.inplant6 = "";
        preview.achive6 = "";
        preview.cocuri6 = "";
        preview.extra6 = "";
        preview.hobbi6 = "";
        preview.info6 = "";
        preview.declar6 = "";
        preview.reference6 = "";
        preview.objective7 = "";
        preview.experience7 = "";
        preview.project7 = "";
        preview.education7 = "";
        preview.skill7 = "";
        preview.strength7 = "";
        preview.intrest7 = "";
        preview.indrust7 = "";
        preview.inplant7 = "";
        preview.achive7 = "";
        preview.cocuri7 = "";
        preview.extra7 = "";
        preview.hobbi7 = "";
        preview.info7 = "";
        preview.declar7 = "";
        preview.reference7 = "";
        preview.objective8 = "";
        preview.experience8 = "";
        preview.project8 = "";
        preview.education8 = "";
        preview.skill8 = "";
        preview.strength8 = "";
        preview.intrest8 = "";
        preview.indrust8 = "";
        preview.inplant8 = "";
        preview.achive8 = "";
        preview.cocuri8 = "";
        preview.extra8 = "";
        preview.hobbi8 = "";
        preview.info8 = "";
        preview.declar8 = "";
        preview.reference8 = "";
        preview.objective9 = "";
        preview.experience9 = "";
        preview.project9 = "";
        preview.education9 = "";
        preview.skill9 = "";
        preview.strength9 = "";
        preview.intrest9 = "";
        preview.indrust9 = "";
        preview.inplant9 = "";
        preview.achive9 = "";
        preview.cocuri9 = "";
        preview.extra9 = "";
        preview.hobbi9 = "";
        preview.info9 = "";
        preview.declar9 = "";
        preview.reference9 = "";
        Cursor rawQuery = preview.DB.rawQuery("select * from profile_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            String string15 = rawQuery.getString(rawQuery.getColumnIndex("photo"));
            Log.e("0000901", rawQuery.getString(rawQuery.getColumnIndex("photo")));
            String string16 = rawQuery.getString(rawQuery.getColumnIndex("sign"));
            if (string15.equals("") || string15.equals("null")) {
                preview.image = "<div class='w3-quarter'><img class='t4-profile-small_image' src='" + "man.png" + "' id='image' alt='Avatar'> </div>";
                preview.image2 = "<style>img {  display: block;  margin-left: auto;  margin-right: auto;}</style> <div class='w3-display-container w3-padding-24'><img src=" + "man.png" + " alt='Paris' style='width:30%;'> </div>";
                preview.image3 = "<img class='w3-circle w3-profile-image' src='" + "man.png" + "' id='image' alt='Avatar'>";
                preview.image4 = "<div class='w3-quarter t3-profile1'>   <img style='box-shadow:0px 0px 5px 10px #" + preview.tit_color + "; width=30; height=30; padding:25px 25px 25px 25px;' class='t3-profile-image' src='" + "profile_img.png" + "' id='image' alt='Avatar'> </div>";
            } else if (preview.sp.getBoolean(preview, "photo_check").booleanValue()) {
                preview.image = "<div class='w3-quarter'><img class='t4-profile-image' src='" + string15 + "' id='image' alt='Avatar'> </div>";
                preview.image2 = " <div class='w3-display-container w3-padding-24'>     <img class='w3-circle w3-profile-image' src='" + string15 + "' id='image' alt='Avatar'> </div>";
                preview.image3 = "<img class='w3-circle w3-profile-image' src='" + string15 + "' id='image' alt='Avatar'>";
                preview.image4 = "<div class='w3-quarter t3-profile1'>   <img style='box-shadow:0px 0px 5px 10px #" + preview.tit_color + ";' class='t3-profile-image' src='" + string15 + "' id='image' alt='Avatar'> </div>";
            } else {
                preview.image = "<div class='w3-quarter'><img class='t4-profile-small_image' src='" + "man.png" + "' id='image' alt='Avatar'> </div>";
                preview.image2 = "<style>img {  display: block;  margin-left: auto;  margin-right: auto;}</style> <div class='w3-display-container w3-padding-24'><img src=" + "man.png" + " alt='Paris' style='width:30%;'> </div>";
                preview.image3 = "<img class='w3-circle w3-profile-image' src='" + "man.png" + "' id='image' alt='Avatar'>";
                preview.image4 = "<div class='w3-quarter t3-profile1'>   <img style='box-shadow:0px 0px 5px 10px #" + preview.tit_color + "; width=30; height=30; padding:25px 25px 25px 25px;' class='t3-profile-image' src='" + "profile_img.png" + "' id='image' alt='Avatar'> </div>";
            }
            if (string16.equals("null") || string16.equals("")) {
                preview.sign = "<td colspan='2'>  </td>";
                preview.sign2 = "<td></td>";
            } else if (preview.sp.getBoolean(preview, "sign_check").booleanValue()) {
                preview.sign = "<td class='t3-signature' colspan='2'><img src='" + string16 + "'></td>";
                preview.sign2 = "<td><img class='signature' src='" + string16 + "'></td>";
            } else {
                preview.sign = "<td colspan='2'>  </td>";
                preview.sign2 = "<td></td>";
            }
        } else {
            Log.e("0000901", "man.png");
            preview.image = "<div class='w3-quarter'><img class='t4-profile-small_image' src='" + "man.png" + "' id='image' alt='Avatar'> </div>";
        }
        rawQuery.close();
        Cursor rawQuery2 = preview.DB.rawQuery("select * from personal_info where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery2.getCount() > 0) {
            rawQuery2.moveToFirst();
            preview.name = rawQuery2.getString(rawQuery2.getColumnIndex(AppMeasurementSdk.ConditionalUserProperty.NAME));
            preview.sign_name = rawQuery2.getString(rawQuery2.getColumnIndex(AppMeasurementSdk.ConditionalUserProperty.NAME));
            preview.email = rawQuery2.getString(rawQuery2.getColumnIndex("email"));
            preview.phoneno = rawQuery2.getString(rawQuery2.getColumnIndex("phone"));
            preview.status = rawQuery2.getString(rawQuery2.getColumnIndex("maritalstatus"));
            preview.dob = rawQuery2.getString(rawQuery2.getColumnIndex("dob"));
            preview.lang = rawQuery2.getString(rawQuery2.getColumnIndex("language"));
            preview.address = rawQuery2.getString(rawQuery2.getColumnIndex("address"));
            Cursor rawQuery3 = preview.DB.rawQuery("Select personal_title from title_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
            rawQuery3.moveToFirst();
            if (rawQuery3.getCount() > 0 && (string14 = rawQuery3.getString(rawQuery3.getColumnIndex("personal_title"))) != null) {
                preview.title = string14;
            }
            rawQuery3.close();
            Log.e("addressss", preview.address);
            String str10 = "";
            String str11 = "";
            String str12 = "";
            if (!preview.phoneno.equals("")) {
                str10 = "<td><i class='fa fa-mobile fa-fw w3-margin-right w3-border'> </i></td><td>" + preview.phoneno + "</td>";
            }
            if (!preview.email.equals("")) {
                str11 = "<td><i class='fa fa-envelope fa-fw w3-margin-right w3-border'> </i></td><td>" + preview.email + "</td>";
            }
            if (!preview.address.equals("")) {
                str12 = "<tr><td><i class='fa fa-map-marker fa-fw w3-margin-right w3-border'> </i></td><td colspan=3>" + preview.address + "</td></tr>";
            }
            if (!preview.status.equals("")) {
                preview.pro_table += "<tr><td><b>Marital Status</b></td><td> : </td><td>" + preview.status + "</td></tr>";
            }
            if (!preview.lang.equals("")) {
                preview.pro_table += "<tr><td><b>Languages Known</b></td><td> : </td><td>" + preview.lang + "</td></tr>";
            }
            Cursor rawQuery4 = preview.DB.rawQuery("select *from extra_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "' and table_name='personal_info'", null);
            if (rawQuery4.getCount() > 0) {
                rawQuery4.moveToFirst();
                String str13 = "";
                for (int i = 0; i < rawQuery4.getCount(); i++) {
                    preview.pro_table += "<p><b>" + rawQuery4.getString(rawQuery4.getColumnIndex("title")) + " : </b>" + rawQuery4.getString(rawQuery4.getColumnIndex("value")) + "</p>";
                    str13 = str13 + "<p  class='w3-margin-top-0 w3-margin-bottom-0'><b>" + rawQuery4.getString(rawQuery4.getColumnIndex("title")) + " : </b>" + rawQuery4.getString(rawQuery4.getColumnIndex("value")) + "</p>";
                    rawQuery4.moveToNext();
                }
                preview.info = "    <div class='w3-no-break w3-row w3-container'>     <h6 style='color:#" + preview.tit_color + ";' class='w3-quarter t4-left t3-headeing-color w3-upper-case'>" + preview.title + " -</h6>     <div style='border-color:#" + preview.tit_color + ";' class='w3-threequarter t4-right w3-container t4-border-bottom'>" + preview.pro_table + "</div>    </div>";
                preview.info2 = "<div class='w3-no-break'>    <p style='color:#" + preview.tit_color + ";' class='w3-large w3-templete_color_3 w3-capitalize'><b><i style='color:#" + preview.tit_color + ";' class='fa fa-asterisk fa-fw w3-margin-right w3-templete_color_3'></i>" + preview.title + "</b></p>    <ul class='w3-large ul-space' style='list-style: none;'>        <li>            <table>" + preview.pro_table + "</table>        </li>    </ul>    <br></div>";
                StringBuilder sb = new StringBuilder();
                sb.append("<div class='w3-no-break w3-container'>     <table class='w3-large w3-profile-table'>         <tr><td><i class='fa fa-mobile fa-fw w3-margin-right w3-border'> </i></td><td>");
                sb.append(preview.phoneno);
                sb.append("</td></tr>         <tr><td><i class='fa fa-envelope fa-fw w3-margin-right w3-border'> </i></td><td>");
                sb.append(preview.email);
                sb.append("</td></tr>         <tr><td><i class='fa fa-map-marker fa-fw w3-margin-right w3-border'> </i></td><td>");
                sb.append(preview.address.replaceAll("<br>", "").replaceAll("<p dir=\"ltr\">", "").replaceAll("</p>", ""));
                sb.append("</td></tr>     </table>     <br> </div>");
                preview.info3 = sb.toString();
                preview.info4 = "<div style='border-color:#" + preview.tit_color + ";' class='w3-no-break w3-row w3-container t3-border-bottom'>  <h4 style='color:#" + preview.tit_color + ";' class='w3-quarter t3-left t3-headeing-color w3-upper-case'>" + preview.title + "</h4>  <div class='w3-threequarter t3-right w3-container'>    <h6>      <table class='t3-profile-table'>" + str13 + "                </table>    </h6>  </div></div>";
                StringBuilder sb2 = new StringBuilder();
                sb2.append("<div class='w3-no-break w3-row w3-container'>    <div class='w3-quarter'>        <h6 class='t5-left w3-upper-case'>");
                sb2.append(preview.title);
                sb2.append("</h6>    </div>    <div class='w3-threequarter w3-container t5-right'>        <h6 class='margin-0'>            <table class='t3-profile-table'>");
                sb2.append(preview.pro_table);
                sb2.append("            </table>        </h6>    </div></div>");
                preview.info5 = sb2.toString();
                preview.info6 = "<div class='w3-no-break w3-row w3-border-bottom w3-container'>    <div class='w3-quarter'>        <h6 class='t6-left w3-upper-case'>" + preview.title + "</h6>    </div>    <div class='w3-threequarter w3-container'>        <h6>            <table class='t3-profile-table'>" + preview.pro_table + "            </table>        </h6>    </div></div>";
                preview.info7 = "<div class='w3-no-break w3-row'>    <h6 style='background-color:#" + preview.tit_color + ";' class='t7-block_background w3-padding-tinysmall w3-upper-case'>" + preview.title + "</h6>    <div class='w3-container'>        <h6>            <table class='t7-profile-table'>" + preview.pro_table + "                            </table>        </h6>    </div></div>";
                preview.info8 = "<div class='w3-no-break w3-row w3-border-bottom'>    <h6 style='color:#" + preview.tit_color + ";' class='t8-block_background w3-padding-tinysmall w3-upper-case'>" + preview.title + "</h6>    <div class='w3-container'>        <h6>            <table class='t8-profile-table'>" + preview.pro_table + "            </table>        </h6>    </div></div>";
                preview.info9 = " <div class='w3-no-break w3-row'>     <h6 style='color:#" + preview.tit_color + ";border-bottom: 1px solid #" + preview.tit_color + "!important;border-top: 1px solid #" + preview.tit_color + "!important;' class='t9-block_background w3-padding-tinysmall w3-upper-case'>" + preview.title + "</h6>     <div class='w3-container'>         <h6>             <table class='t9-profile-table'>" + preview.pro_table + "               </table>         </h6>     </div> </div>";
                StringBuilder sb3 = new StringBuilder();
                sb3.append("<div class='w3-no-break w3-container'>      <h3 class='w3-block_background w3-capitalize w3-center w3-margin-top'>");
                sb3.append(preview.title);
                sb3.append("</h3>      <table class='w3-large w3-profile-table'>");
                sb3.append(preview.pro_table);
                sb3.append("      </table>  </div>");
                preview.profile3 = sb3.toString();
            }
            rawQuery4.close();
            String str14 = "<table><tr>" + str10 + str11 + "</tr>" + str12 + "</table>";
            preview.personal = "    <div  style='border-color:#" + preview.tit_color + ";' class='w3-threequarter t4-right w3-container t4-border-bottom'>     <h2 class='w3-upper-case t4-profile-name'>" + preview.name + "</h2></h6>      <h6 class='margin-0'>" + str14.replaceAll("<br>", "") + "      </h6>     </div>    </div>";
        }
        rawQuery2.close();
        if (preview.sp.getBoolean(preview, "sign_name_check").booleanValue()) {
            preview.sign_name = "<tr><td width='87%'></td><td class='w3-center'><h6>(" + preview.name + ")</h6></td></tr>";
            preview.sign_btm_name = "<tr><td width='7%'></td><td width='1%'></td><td width='82%'></td><td>(" + preview.name + ")</td></tr>";
            preview.sign_btm_name1 = "<h6 style='text-align: right;width: 95%;'>(" + preview.name + ")</h6>";
        } else {
            preview.sign_name = "";
            preview.sign_btm_name = "";
            preview.sign_btm_name1 = "";
        }
        Cursor rawQuery5 = preview.DB.rawQuery("select * from cover_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery5.getCount() > 0) {
            rawQuery5.moveToFirst();
            String string17 = rawQuery5.getString(rawQuery5.getColumnIndex("date"));
            String string18 = rawQuery5.getString(rawQuery5.getColumnIndex("address"));
            String string19 = rawQuery5.getString(rawQuery5.getColumnIndex("body"));
            if (preview.sp.getBoolean(preview, "cover_check").booleanValue()) {
                preview.cover_letter2 = "        <div class='w3-row cover-letter-full'>            <img style='background-color:#" + preview.tit_color + ";' class='top-img t6-background-color' src='w3images/top.png'>            <div class='w3-row w3-container w3-cover-letter'>                <div class='w3-col w3-container w3-cover-letter-from'>                    <h2 class='w3-upper-case t6-profile-name margin-0'>" + preview.name + "</h2>                    <h4 class='w3-upper-case t6-profile-role margin-0'>" + preview.designation + "</h4>                    <h6><span>" + preview.phoneno + " | </span><span>" + preview.email + "</span><br><span>" + preview.address.replaceAll("<p dir=\"ltr\">", "").replaceAll("</p>", "").replaceAll("<br>", "") + "</span></h6>                </div>                <div class='w3-col w3-container w3-cover-letter-to'>                    <h6><span>" + string18 + "</span></h6>                </div>                <div class='w3-col w3-container w3-cover-letter-date'>                    <h6 class='w3-upper-case margin-0'>" + string17 + "</h6>                </div>                <div class='w3-col w3-container w3-cover-letter-content'>                    <h4 class='margin-0'>Dear Sir/Madam,</h4>                    <p class='w3-indent'>" + string19 + "                    </p>                    <div class='w3-row'>                        <h6>                           <table>                               <tr><td width='100%'></td><td</td><td class='w3-center'>Your's Sincerely,</td></tr>                               <tr><td width='87%'></td>" + preview.sign + "</tr>" + preview.sign_name + "                                </table>                        </h6>                    </div>                </div>            </div>            <img style='background-color:#" + preview.tit_color + ";' class='bottom-img w3-right t6-background-color' src='w3images/bottom.png'>        </div>";
                preview.cover_letter = "<div class='w3-row cover-letter-full back-white'>           <img style='background-color:#" + preview.tit_color + ";' class='top-img t5-background-color' src='w3images/top.png'>           <div class='w3-row w3-container w3-cover-letter'>               <div class='w3-col w3-container w3-cover-letter-from'>                   <h2 class='w3-upper-case t5-profile-name margin-0'>" + preview.name + "</h2>                   <h4 class='w3-upper-case t5-profile-role margin-0'>" + preview.designation + "</h4>                   <h6><span>" + preview.phoneno + " | </span><span>" + preview.email + "</span><br><span>" + preview.address.replaceAll("<p dir=\"ltr\">", "").replaceAll("</p>", "").replaceAll("<br>", "") + "</span></h6>               </div>               <div class='w3-col w3-container w3-cover-letter-to'>" + string18 + "               </div>               <div class='w3-col w3-container w3-cover-letter-date'>                   <h6 class='w3-upper-case margin-0'>" + string17 + "</h6>               </div>               <div class='w3-col w3-container w3-cover-letter-content'>" + string19 + "                   <div class='w3-row'>                       <h6>                           <table>                               <tr><td width='100%'></td><td</td><td class='w3-center'>Your's Sincerely,</td></tr>                               <tr><td width='87%'></td>" + preview.sign + "</tr>" + preview.sign_name + "                           </table>                       </h6>                   </div>               </div>           </div>           <img style='background-color:#" + preview.tit_color + ";' class='bottom-img w3-right t5-background-color' src='w3images/bottom.png'>       </div>";
                preview.cover_letter6 = "        <div class='w3-row cover-letter-full'>            <img style='background-color:#" + preview.tit_color + ";' class='top-img t7-background-color' src='w3images/top.png'>            <div class='w3-row w3-container w3-cover-letter'>                <div class='w3-col w3-container w3-cover-letter-from'>                    <h2 class='w3-upper-case t7-profile-name margin-0'>" + preview.name + "</h2>                    <h4 class='w3-upper-case t7-profile-role margin-0'>" + preview.designation + "</h4>                    <h6><span>" + preview.phoneno + " | </span><span>" + preview.email + "</span><br><span>" + preview.address.replaceAll("<p dir=\"ltr\">", "").replaceAll("</p>", "").replaceAll("<br>", "") + "</span></h6>                </div>                <div class='w3-col w3-container w3-cover-letter-to'>" + string18 + "                </div>                <div class='w3-col w3-container w3-cover-letter-date'>                    <h6 class='w3-upper-case margin-0'>" + string17 + "</h6>                </div>                <div class='w3-col w3-container w3-cover-letter-content'>" + string19 + "                    <div class='w3-row'>                       <h6>                           <table>                               <tr><td width='100%'></td><td</td><td class='w3-center'>Your's Sincerely,</td></tr>                               <tr><td width='87%'></td>" + preview.sign + "</tr>" + preview.sign_name + "                           </table>                        </h6>                    </div>                </div>            </div>            <img style='background-color:#" + preview.tit_color + ";' class='bottom-img w3-right t7-background-color' src='w3images/bottom.png'>        </div>";
                StringBuilder sb4 = new StringBuilder();
                sb4.append("<div class='w3-container'>            <div class='w3-row cover-letter-full back-white'>                <img class='top-img t5-background-color' src='w3images/top.png'>                <div class='w3-row w3-container w3-cover-letter'>                    <div class='w3-col w3-container w3-cover-letter-from'>                        <h2 class='w3-upper-case t5-profile-name margin-0'>");
                sb4.append(preview.name);
                sb4.append("</h2>                    <h6><span>");
                sb4.append(preview.phoneno);
                sb4.append(" | </span><span>");
                sb4.append(preview.email);
                sb4.append("</span><br><span>");
                sb4.append(preview.address.replaceAll("<p dir=\"ltr\">", "").replaceAll("</p>", "").replaceAll("<br>", ""));
                sb4.append("</span></h6>                    </div>                    <div class='w3-col w3-container w3-cover-letter-to'>");
                sb4.append(string18);
                sb4.append("                    </div>                    <div class='w3-col w3-container w3-cover-letter-date'>                        <h6 class='w3-upper-case margin-0'>");
                sb4.append(string17);
                sb4.append("</h6>                    </div>                    <div class='w3-col w3-container w3-cover-letter-content'>");
                sb4.append(string19);
                sb4.append("                        <div class='w3-row'>                                                   <h6>                           <table>                               <tr><td width='100%'></td><td</td><td class='w3-center'>Your's Sincerely,</td></tr>                               <tr><td width='87%'></td>");
                sb4.append(preview.sign);
                sb4.append("</tr>");
                sb4.append(preview.sign_name);
                sb4.append("                           </table>                        </h6>                        </div>                    </div>                </div>                <img class='bottom-img w3-right t5-background-color' src='w3images/bottom.png'>            </div>        </div>");
                preview.cover_letter3 = sb4.toString();
                preview.cover_letter4 = "<div class='w3-row cover-letter-full'>            <img style='background-color:#" + preview.tit_color + ";' class='top-img t8-background-color' src='w3images/top.png'>            <div class='w3-row w3-container w3-cover-letter'>                <div class='w3-col w3-container w3-cover-letter-from'>                    <h2 class='w3-upper-case t8-profile-name margin-0'>" + preview.name + "</h2>                    <h4 class='w3-upper-case t8-profile-role margin-0'>" + preview.designation + "</h4>                    <h6><span>" + preview.phoneno + " | </span><span>" + preview.email + "</span><br><span>" + preview.address.replaceAll("<p dir=\"ltr\">", "").replaceAll("</p>", "").replaceAll("<br>", "") + "</span></h6>                </div>                <div class='w3-col w3-container w3-cover-letter-to'>" + string18 + "                </div>                <div class='w3-col w3-container w3-cover-letter-date'>                    <h6 class='w3-upper-case margin-0'>" + string17 + "</h6>                </div>                <div class='w3-col w3-container w3-cover-letter-content'>" + string19 + "                    <div class='w3-row'>                        <h6>                           <table>                               <tr><td width='100%'></td><td</td><td class='w3-center'>Your's Sincerely,</td></tr>                               <tr><td width='87%'></td>" + preview.sign + "</tr>" + preview.sign_name + "                           </table>                        </h6>                    </div>                </div>            </div>            <img style='background-color:#" + preview.tit_color + ";' class='bottom-img w3-right t8-background-color' src='w3images/bottom.png'>        </div>";
                StringBuilder sb5 = new StringBuilder();
                sb5.append("<div class='w3-row cover-letter-full'>                <img class='top-img t9-background-color' src='w3images/top.png'>                <div class='w3-row w3-container w3-cover-letter'>                    <div class='w3-col w3-container w3-cover-letter-from'>                        <h2 class='w3-upper-case t9-profile-name margin-0'>");
                sb5.append(preview.name);
                sb5.append("</h2>                        <h4 class='w3-upper-case t9-profile-role margin-0'>");
                sb5.append(preview.designation);
                sb5.append("</h4>                        <h6><span>");
                sb5.append(preview.phoneno);
                sb5.append(" | </span><span>");
                sb5.append(preview.email);
                sb5.append("</span><br><span>");
                sb5.append(preview.address.replaceAll("<p dir=\"ltr\">", "").replaceAll("</p>", "").replaceAll("<br>", ""));
                sb5.append("</span></h6>                    </div>                    <div class='w3-col w3-container w3-cover-letter-to'>");
                sb5.append(string18);
                sb5.append("                    </div>                    <div class='w3-col w3-container w3-cover-letter-date'>                        <h6 class='w3-upper-case margin-0'>");
                sb5.append(string17);
                sb5.append("</h6>                    </div>                    <div class='w3-col w3-container w3-cover-letter-content'>");
                sb5.append(string19);
                sb5.append("                        <div class='w3-row'>                            <h6>                           <table>                               <tr><td width='100%'></td><td</td><td class='w3-center'>Your's Sincerely,</td></tr>                               <tr><td width='87%'></td>");
                sb5.append(preview.sign);
                sb5.append("</tr>");
                sb5.append(preview.sign_name);
                sb5.append("                           </table>                            </h6>                        </div>                    </div>                </div>                <img class='bottom-img w3-right t9-background-color' src='w3images/bottom.png'>        </div>");
                preview.cover_letter5 = sb5.toString();
            } else {
                preview.cover_letter = "";
                preview.cover_letter2 = "";
                preview.cover_letter3 = "";
                preview.cover_letter4 = "";
                preview.cover_letter5 = "";
                preview.cover_letter6 = "";
            }
        }
        Cursor rawQuery6 = preview.DB.rawQuery("select * from objective_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery6.getCount() > 0) {
            rawQuery6.moveToFirst();
            String string20 = rawQuery6.getString(rawQuery6.getColumnIndex("objective"));
            String string21 = rawQuery6.getString(rawQuery6.getColumnIndex("declaration"));
            preview.date = rawQuery6.getString(rawQuery6.getColumnIndex("date"));
            preview.place = rawQuery6.getString(rawQuery6.getColumnIndex("place"));
            if (!string20.equals("")) {
                preview.objective = "<div class='w3-no-break w3-row w3-container'><h6 style='color:#" + preview.tit_color + ";' class='w3-quarter t4-left t3-headeing-color w3-upper-case'>Objective -</h6><div style='border-color:#" + preview.tit_color + ";' class='w3-threequarter t4-right w3-container t4-border-bottom'><p>" + string20 + "</p></div></div>";
                StringBuilder sb6 = new StringBuilder();
                sb6.append("     <div class='w3-no-break w3-container w3-white'>         <h2 class='w3-text-black w3-capitalize'><i style='color:#");
                sb6.append(preview.bg_color);
                sb6.append(";' class='fa fa-grav fa-fw w3-margin-right w3-xlarge w3-text-templete_color_1'></i>Objective</h2>         <div class='w3-container'>             <p class='w3-indent'>");
                sb6.append(string20);
                sb6.append("</p>         </div>         <hr>     </div>");
                preview.objective2 = sb6.toString();
                preview.objective3 = "<div class='w3-no-break w3-container'>    <h2 style='background-color:#" + preview.tit_color + ";' class='w3-block_background w3-margin-top-large w3-capitalize'><i style='box-shadow:0px 0px 0px 15px #" + preview.bg_color + "; background-color:#" + preview.bg_color + ";' class='fa fa-grav w3-xxlarge w3-circle w3-color-shadow'></i>Objective</h2>    <div class='w3-container w3-margin-top-large'>        <p class='w3-indent'>" + string20 + "</p>    </div></div>";
                StringBuilder sb7 = new StringBuilder();
                sb7.append(" <div style='border-color:#");
                sb7.append(preview.tit_color);
                sb7.append(";' class='w3-no-break w3-row w3-container t3-border-bottom'>  <h4 style='color:#");
                sb7.append(preview.tit_color);
                sb7.append(";' class='w3-quarter t3-left t3-headeing-color w3-upper-case'>Objective</h4>  <div class='w3-threequarter t3-right w3-container'>    <p>");
                sb7.append(string20);
                sb7.append("</p>  </div></div>");
                preview.objective4 = sb7.toString();
                preview.objective5 = "<div class='w3-no-break w3-row w3-container'>         <h6 class='w3-quarter t5-left w3-upper-case'>Objective</h6>         <div class='w3-threequarter w3-container t5-right'>             <p>" + string20 + "</p>         </div>     </div>";
                preview.objective6 = "<div class='t6-obgective w3-padding-12 w3-row w3-container'>         <h6 class='t6-left w3-center w3-upper-case w3-margin-bottom-0'>Objective</h6>         <p class='w3-center  w3-margin-top-0'>" + string20 + "</p>     </div>";
                preview.objective7 = "<div class='w3-padding-12 w3-row'>         <h6 style='background-color:#" + preview.tit_color + ";' class='t7-block_background w3-padding-tinysmall w3-margin-top-small w3-upper-case'>Objective</h6>         <div class='w3-container list'>             <p>" + string20 + "</p>         </div>     </div>";
                preview.objective8 = "<div class='w3-row w3-border-bottom'>         <h6 style='color:#" + preview.tit_color + ";' class='t8-block_background w3-padding-tinysmall w3-margin-top-small w3-upper-case'>Objective</h6>         <div class='w3-container list'>             <p>" + string20 + "</p>         </div>     </div>";
                preview.objective9 = "<div class='w3-padding-12 w3-row'>         <h6 style='color:#" + preview.tit_color + ";border-bottom: 1px solid #" + preview.tit_color + "!important;border-top: 1px solid #" + preview.tit_color + "!important;' class='t9-block_background w3-padding-tinysmall w3-margin-top-small w3-upper-case'>Objective</h6>         <div class='w3-container list'>             <p>" + string20 + "</p>         </div>     </div>";
            }
            if (!string21.equals("")) {
                preview.declar = "    <div class='w3-no-break w3-row w3-container'>       <h6 style='color:#" + preview.tit_color + ";' class='w3-quarter t4-left t3-headeing-color w3-upper-case'>Declaration -</h6>       <div class='w3-threequarter t4-right w3-container'>       <h6>" + string21 + "</h6>       <br>     </div> ";
                preview.declar2 = "  <h2 class='w3-text-black w3-capitalize'><i style='color:#" + preview.bg_color + ";' class='fa fa-pencil fa-fw w3-margin-right w3-xlarge w3-text-templete_color_1'></i>Declaration</h2>  <div class='w3-container'>      <p class='w3-indent'>" + string21 + "</p>  </div>";
                StringBuilder sb8 = new StringBuilder();
                sb8.append("<h2 class='w3-block_background w3-margin-top-large w3-capitalize'><i class='fa fa-pencil w3-xxlarge w3-circle w3-color-shadow'></i>Declaration</h2>    <h5 class='w3-margin-top-10'>");
                sb8.append(string21);
                sb8.append("</h5>");
                preview.declar3 = sb8.toString();
                preview.declar4 = "<h4 style='color:#" + preview.tit_color + ";' class='w3-quarter t3-left t3-headeing-color w3-upper-case'>Declaration</h4>  <div class='w3-threequarter t3-right w3-container'>    <h6>" + string21 + "</h6>    <br>  </div>";
                StringBuilder sb9 = new StringBuilder();
                sb9.append("<div class='w3-no-break w3-row w3-container'>      <h6 class='w3-quarter t5-left w3-upper-case '>Declaration</h6>      <div class='w3-threequarter w3-container t5-right  bord-btm'>          <h6>");
                sb9.append(string21);
                sb9.append("</h6>          <br>      </div>  </div>");
                preview.declar5 = sb9.toString();
                preview.declar6 = "<div class='w3-no-break w3-row w3-border-bottom w3-container'>      <h6 class='w3-quarter t6-left w3-upper-case'>Declaration</h6>      <div class='w3-threequarter w3-container'>          <h6>" + string21 + "</h6>          <br>      </div>  </div>";
                preview.declar7 = "<div class='w3-no-break w3-row'>      <h6 style='background-color:#" + preview.tit_color + ";' class='t7-block_background w3-padding-tinysmall w3-upper-case'>Declaration</h6>      <div class='w3-container'>          <h6>" + string21 + "</h6>      </div>  </div>";
                preview.declar8 = "<div class='w3-no-break w3-row w3-border-bottom'>      <h6 style='color:#" + preview.tit_color + ";' class='t8-block_background w3-padding-tinysmall w3-upper-case'>Declaration</h6>      <div class='w3-container'>          <h6>" + string21 + "</h6>      </div>  </div>";
                preview.declar9 = "<div class='w3-no-break w3-row'>      <h6 style='color:#" + preview.tit_color + ";border-bottom: 1px solid #" + preview.tit_color + "!important;border-top: 1px solid #" + preview.tit_color + "!important;' class='t9-block_background w3-padding-tinysmall w3-upper-case'>Declaration</h6>      <div class='w3-container'>          <h6>" + string21 + "</h6>      </div>  </div>";
            }
        }
        Cursor rawQuery7 = preview.DB.rawQuery("select * from work_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery7.getCount() > 0) {
            rawQuery7.moveToFirst();
            String str15 = "";
            Cursor rawQuery8 = preview.DB.rawQuery("Select work_title from title_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
            rawQuery8.moveToFirst();
            if (rawQuery8.getCount() > 0 && (string13 = rawQuery8.getString(rawQuery8.getColumnIndex("work_title"))) != null) {
                str15 = string13;
            }
            rawQuery8.close();
            String str16 = "";
            String str17 = "";
            int i2 = 0;
            while (i2 < rawQuery7.getCount()) {
                int i3 = rawQuery7.getInt(rawQuery7.getColumnIndex("work_id"));
                String string22 = rawQuery7.getString(rawQuery7.getColumnIndex("date_of_join"));
                String string23 = rawQuery7.getString(rawQuery7.getColumnIndex("end_date"));
                String string24 = rawQuery7.getString(rawQuery7.getColumnIndex("designation"));
                String string25 = rawQuery7.getString(rawQuery7.getColumnIndex("organization"));
                String str18 = rawQuery7.getString(rawQuery7.getColumnIndex("role")).trim().equals("") ? "" : " <p class='w3-margin-top-0'><b>Role : </b>" + rawQuery7.getString(rawQuery7.getColumnIndex("role")) + "</p>";
                preview.designation = string24;
                Cursor rawQuery9 = preview.DB.rawQuery("Select * from extra_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "' and table_name='work_table' and table_id='" + i3 + "'", null);
                rawQuery9.moveToFirst();
                if (rawQuery9.getCount() > 0) {
                    str8 = "";
                    int i4 = 0;
                    while (i4 < rawQuery9.getCount()) {
                        str8 = str8 + "<p class='w3-margin-top-0'><b>" + rawQuery9.getString(rawQuery9.getColumnIndex("title")) + " : </b>" + rawQuery9.getString(rawQuery9.getColumnIndex("value")) + "</p>";
                        rawQuery9.moveToNext();
                        i4++;
                        str15 = str15;
                    }
                    str7 = str15;
                } else {
                    str7 = str15;
                    str8 = "";
                }
                rawQuery9.close();
                if (string23.contains("Still")) {
                    str9 = "<span style='background-color:#" + preview.tit_color + ";' class='w3-tag t3-block_background w3-round'>" + string23 + "</span>";
                    string23 = "<span style='background-color:#" + preview.bg_color + ";' class='w3-tag w3-templete_color_1 w3-round'>" + string23 + "</span>";
                } else {
                    str9 = string23;
                }
                preview.sub_exp += "<h6 class='w3-margin-bottom-0'><b>" + string25 + "</b><span class=w3-right>" + string22 + " - " + str9 + "</span></h6><p class='w3-margin-top-0'><b>Designation : </b>" + string24 + "</p>" + str18 + str8;
                str17 = str17 + "<div class='w3-container'>    <h5><b>" + string25 + "</b></h5>    <h6 style='color:#" + preview.bg_color + ";' class='w3-text-templete_color_1'><i class='fa fa-calendar fa-fw w3-margin-right'></i>" + string22 + " - " + string23 + "</h6><p class='w3-margin-top-0'><b>Designation : </b>" + string24 + "</p>" + str18 + str8 + "</div><hr>";
                str16 = str16 + "    <div class='w3-container w3-margin-top-large'>    <h5 class=' w3-margin-bottom-0'><b>" + string25 + "</b></h5>    <h6 style='color:#" + preview.bg_color + ";' class='w3-text-templete_color_1'><i class='fa fa-calendar fa-fw w3-margin-right'></i>" + string22 + " - " + string23 + "</h6><p class='w3-margin-top-0 w3-margin-bottom-0'><b>Designation : </b>" + string24 + "</p>" + str18 + str8 + "</div>";
                rawQuery7.moveToNext();
                i2++;
                str15 = str7;
            }
            preview.experience = "<div class='w3-no-break w3-row w3-container'><h6 style='color:#" + preview.tit_color + ";' class='w3-quarter t4-left t3-headeing-color w3-upper-case'>" + str15 + " -</h6><div style='border-color:#" + preview.tit_color + ";' class='w3-threequarter t4-right w3-container t4-border-bottom'>" + preview.sub_exp + "</div></div>";
            StringBuilder sb10 = new StringBuilder();
            sb10.append("<div class='w3-no-break w3-container w3-white w3-margin-bottom'>          <h2 class='w3-text-black w3-capitalize'><i style='color:#");
            sb10.append(preview.bg_color);
            sb10.append(";' class='fa fa-suitcase fa-fw w3-margin-right w3-xlarge w3-text-templete_color_1'></i>");
            sb10.append(str15);
            sb10.append("</h2>");
            sb10.append(str17);
            sb10.append("</div>");
            preview.experience2 = sb10.toString();
            preview.experience3 = "<div class='w3-no-break w3-container'>    <h2 style='background-color:#" + preview.tit_color + ";' class='w3-block_background w3-margin-top-large w3-capitalize'><i style='box-shadow:0px 0px 0px 15px #" + preview.bg_color + "; background-color:#" + preview.bg_color + ";' class='fa fa-suitcase w3-xxlarge w3-circle w3-color-shadow'></i>" + str15 + "</h2>" + str16 + "</div>";
            StringBuilder sb11 = new StringBuilder();
            sb11.append("<div style='border-color:#");
            sb11.append(preview.tit_color);
            sb11.append(";' class='w3-no-break w3-row w3-container t3-border-bottom'>  <h4 style='color:#");
            sb11.append(preview.tit_color);
            sb11.append(";' class='w3-quarter t3-left t3-headeing-color w3-upper-case'>");
            sb11.append(str15);
            sb11.append("</h4>  <div class='w3-threequarter t3-right w3-container'>");
            sb11.append(preview.sub_exp);
            sb11.append("  </div></div>");
            preview.experience4 = sb11.toString();
            preview.experience5 = "<div class='w3-no-break w3-row w3-container'>          <h6 class='w3-quarter t5-left w3-upper-case'>" + str15 + "</h6>          <div class='w3-threequarter w3-container t5-right'>" + preview.sub_exp + "          </div>      </div>";
            preview.experience6 = "<div class='w3-no-break w3-row w3-border-bottom w3-container'>          <h6 class='w3-quarter t6-left w3-upper-case'>" + str15 + "</h6>          <div class='w3-threequarter w3-container'>" + preview.sub_exp + "          </div>      </div>";
            preview.experience7 = "<div class='w3-no-break w3-row'>          <h6 style='background-color:#" + preview.tit_color + ";' class='t7-block_background w3-padding-tinysmall w3-upper-case'>" + str15 + "</h6>          <div class='w3-container list'>" + preview.sub_exp + "          </div>      </div>";
            preview.experience8 = "<div class='w3-no-break w3-row w3-border-bottom'>          <h6 style='color:#" + preview.tit_color + ";' class='t8-block_background w3-padding-tinysmall w3-upper-case'>" + str15 + "</h6>          <div class='w3-container list'>" + preview.sub_exp + "          </div>      </div>";
            preview.experience9 = "<div class='w3-no-break w3-row'>          <h6 style='color:#" + preview.tit_color + ";border-bottom: 1px solid #" + preview.tit_color + "!important;border-top: 1px solid #" + preview.tit_color + "!important;' class='t9-block_background w3-padding-tinysmall w3-upper-case'>" + str15 + "</h6>          <div class='w3-container list'>" + preview.sub_exp + "          </div>      </div>";
        } else {
            preview.designation = "Fresher";
        }
        rawQuery7.close();
        Cursor rawQuery10 = preview.DB.rawQuery("select * from project_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery10.getCount() > 0) {
            rawQuery10.moveToFirst();
            String str19 = "";
            Cursor rawQuery11 = preview.DB.rawQuery("Select project_title from title_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
            rawQuery11.moveToFirst();
            if (rawQuery11.getCount() > 0 && (string12 = rawQuery11.getString(rawQuery11.getColumnIndex("project_title"))) != null) {
                str19 = string12;
            }
            rawQuery11.close();
            String str20 = "";
            String str21 = "";
            int i5 = 0;
            while (i5 < rawQuery10.getCount()) {
                int i6 = rawQuery10.getInt(rawQuery10.getColumnIndex("project_id"));
                String string26 = rawQuery10.getString(rawQuery10.getColumnIndex("title"));
                String string27 = rawQuery10.getString(rawQuery10.getColumnIndex("discription"));
                String string28 = rawQuery10.getString(rawQuery10.getColumnIndex("duration"));
                String string29 = rawQuery10.getString(rawQuery10.getColumnIndex("role"));
                String string30 = rawQuery10.getString(rawQuery10.getColumnIndex("team_size"));
                String str22 = "";
                if (!string30.trim().equals("")) {
                    str22 = string30.contains("member") ? " / <b>Team Size : </b> " + string30 : " / <b>Team Size : </b> " + string30 + " Members";
                }
                Cursor rawQuery12 = preview.DB.rawQuery("Select * from extra_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "' and table_name='project_table' and table_id='" + i6 + "'", null);
                rawQuery12.moveToFirst();
                if (rawQuery12.getCount() > 0) {
                    str3 = "";
                    int i7 = 0;
                    while (i7 < rawQuery12.getCount()) {
                        if (i7 == 0) {
                            String string31 = rawQuery12.getString(rawQuery12.getColumnIndex("title"));
                            String string32 = rawQuery12.getString(rawQuery12.getColumnIndex("value"));
                            StringBuilder sb12 = new StringBuilder();
                            str5 = str19;
                            sb12.append("<p class='w3-margin-top-0 w3-margin-bottom-0'><b>");
                            sb12.append(string31);
                            sb12.append(" : </b> ");
                            sb12.append(string32);
                            sb12.append("</p>");
                            str6 = sb12.toString();
                            rawQuery12.moveToNext();
                        } else {
                            str5 = str19;
                            if (i7 == rawQuery12.getCount() - 1) {
                                str6 = str3 + "<p class='w3-margin-top-0'><b>" + rawQuery12.getString(rawQuery12.getColumnIndex("title")) + " : </b> " + rawQuery12.getString(rawQuery12.getColumnIndex("value")) + "</p>";
                                rawQuery12.moveToNext();
                            } else {
                                str6 = str3 + "<p class='w3-margin-top-0 w3-margin-bottom-0'><b>" + rawQuery12.getString(rawQuery12.getColumnIndex("title")) + " : </b> " + rawQuery12.getString(rawQuery12.getColumnIndex("value")) + "</p>";
                                rawQuery12.moveToNext();
                            }
                        }
                        str3 = str6;
                        i7++;
                        str19 = str5;
                    }
                    str2 = str19;
                    str4 = rawQuery10.getString(rawQuery10.getColumnIndex("company")).trim().equals("") ? "" : "<p class='w3-margin-top-0 w3-margin-bottom-0'><b>Company / Institute : </b>" + rawQuery10.getString(rawQuery10.getColumnIndex("company")) + "</p>";
                } else {
                    str2 = str19;
                    if (rawQuery10.getString(rawQuery10.getColumnIndex("company")).trim().equals("")) {
                        str4 = "";
                        str3 = "";
                    } else {
                        str4 = "<p class='w3-margin-top-0'><b>Company / Institute : </b>" + rawQuery10.getString(rawQuery10.getColumnIndex("company")) + "</p>";
                        str3 = "";
                    }
                }
                rawQuery12.close();
                preview.sub_work += " <h6 class='w3-margin-bottom-0'><b>" + string26 + "</b></h6>      <p style='color:#" + preview.tit_color + ";' class='t3-headeing-color w3-margin-top-0 w3-margin-bottom-0'><i class='fa fa-clock-o fa-fw w3-margin-right'></i><b>Duration : </b> " + string28 + str22 + " / <b>Role : </b>" + string29 + "</p>      <p class='w3-margin-top-0 w3-margin-bottom-0'><b>Description : </b>" + string27 + "</p>" + str4 + str3;
                str21 = str21 + "    <div class='w3-container'>        <h5><b>" + string26 + "</b></h5>        <h6 style='color:#" + preview.bg_color + ";' class='w3-text-templete_color_1'><i class='fa fa-clock-o fa-fw w3-margin-right'></i><b>Duration : </b>" + string28 + str22 + "/ <b>Role : </b>" + string29 + "</h6>        <p class='w3-margin-top-0'><b>Description : </b>" + string27 + "</p>" + str4 + str3 + "    </div>    <hr>";
                str20 = str20 + "    <div class='w3-container w3-margin-top-large'>        <h5 class=' w3-margin-bottom-0'><b>" + string26 + "</b></h5>        <h6 style='color:#" + preview.bg_color + ";' class='w3-text-templete_color_1'><i class='fa fa-clock-o fa-fw w3-margin-right'></i><b>Duration : </b>" + string28 + str22 + " / <b>Role : </b>" + string29 + "</h6>        <p class='w3-margin-top-0 w3-margin-bottom-0'><b>Description : </b>" + string27 + "</p>" + str4 + str3 + "    </div>";
                rawQuery10.moveToNext();
                i5++;
                str19 = str2;
            }
            preview.project = "    <div class='w3-no-break w3-row w3-container'>     <h6 style='color:#" + preview.tit_color + ";' class='w3-quarter t4-left t3-headeing-color w3-upper-case'>" + str19 + " -</h6>     <div  style='border-color:#" + preview.tit_color + ";' class='w3-threequarter t4-right w3-container t4-border-bottom'>" + preview.sub_work + "</div>    </div>";
            StringBuilder sb13 = new StringBuilder();
            sb13.append("<div class='w3-no-break w3-container w3-white w3-margin-bottom'>    <h2 class='w3-text-black w3-capitalize'><i style='color:#");
            sb13.append(preview.bg_color);
            sb13.append(";' class='fa fa-window-maximize fa-fw w3-margin-right w3-xlarge w3-text-templete_color_1'></i>");
            sb13.append(str19);
            sb13.append("</h2>");
            sb13.append(str21);
            sb13.append("</div>");
            preview.project2 = sb13.toString();
            preview.project3 = "<div class='w3-no-break w3-container'>    <h2 style='background-color:#" + preview.tit_color + ";' class='w3-block_background w3-margin-top-large w3-capitalize'><i style='box-shadow:0px 0px 0px 15px #" + preview.bg_color + "; background-color:#" + preview.bg_color + ";' class='fa fa-window-maximize w3-xxlarge w3-circle w3-color-shadow'></i>" + str19 + "</h2>" + str20 + "</div>";
            StringBuilder sb14 = new StringBuilder();
            sb14.append("<div style='border-color:#");
            sb14.append(preview.tit_color);
            sb14.append(";' class='w3-no-break w3-row w3-container t3-border-bottom w3-margin-top-10'>  <h4 style='color:#");
            sb14.append(preview.tit_color);
            sb14.append(";' class='w3-quarter t3-left t3-headeing-color w3-upper-case'>");
            sb14.append(str19);
            sb14.append("</h4>  <div class='w3-threequarter t3-right w3-container'>");
            sb14.append(preview.sub_work);
            sb14.append("  </div></div>");
            preview.project4 = sb14.toString();
            preview.project5 = "<div class='w3-no-break w3-row w3-container w3-margin-top-10'>       <h6 class='w3-quarter t5-left w3-upper-case'>" + str19 + "</h6>       <div class='w3-threequarter w3-container t5-right'>" + preview.sub_work + "       </div>   </div>";
            preview.project6 = "<div class='w3-no-break w3-row w3-border-bottom w3-container w3-margin-top-10'>       <h6 class='w3-quarter t6-left w3-upper-case'>" + str19 + "</h6>       <div class='w3-threequarter w3-container'>" + preview.sub_work + "       </div>   </div>";
            preview.project7 = "<div class='w3-no-break w3-row'>       <h6 style='background-color:#" + preview.tit_color + ";' class='t7-block_background w3-padding-tinysmall w3-upper-case'>" + str19 + "</h6>       <div class='w3-container list'>" + preview.sub_work + "       </div>   </div>";
            preview.project8 = "<div class='w3-no-break w3-row w3-border-bottom'>       <h6 style='color:#" + preview.tit_color + ";' class='t8-block_background w3-padding-tinysmall w3-upper-case'>" + str19 + "</h6>       <div class='w3-container list'>" + preview.sub_work + "       </div>   </div>";
            preview.project9 = "<div class='w3-no-break w3-row'>    <h6 style='color:#" + preview.tit_color + ";border-bottom: 1px solid #" + preview.tit_color + "!important;border-top: 1px solid #" + preview.tit_color + "!important;' class='t9-block_background w3-padding-tinysmall w3-upper-case'>" + str19 + "</h6>    <div class='w3-container list'>" + preview.sub_work + "                    </div>                </div>";
        }
        rawQuery10.close();
        Cursor rawQuery13 = preview.DB.rawQuery("select * from academic_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery13.getCount() > 0) {
            rawQuery13.moveToFirst();
            String str23 = "";
            Cursor rawQuery14 = preview.DB.rawQuery("Select academic_title from title_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
            rawQuery14.moveToFirst();
            if (rawQuery14.getCount() > 0 && (string11 = rawQuery14.getString(rawQuery14.getColumnIndex("academic_title"))) != null) {
                str23 = string11;
            }
            rawQuery14.close();
            String str24 = "";
            String str25 = "";
            for (int i8 = 0; i8 < rawQuery13.getCount(); i8++) {
                int i9 = rawQuery13.getInt(rawQuery13.getColumnIndex("academic_id"));
                String string33 = rawQuery13.getString(rawQuery13.getColumnIndex("course"));
                String string34 = rawQuery13.getString(rawQuery13.getColumnIndex("institute"));
                String string35 = rawQuery13.getString(rawQuery13.getColumnIndex("percentage"));
                String string36 = rawQuery13.getString(rawQuery13.getColumnIndex("yop"));
                String str26 = "";
                Cursor rawQuery15 = preview.DB.rawQuery("Select * from extra_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "' and table_name='academic_table' and table_id='" + i9 + "'", null);
                rawQuery15.moveToFirst();
                if (rawQuery15.getCount() > 0) {
                    String str27 = str26;
                    for (int i10 = 0; i10 < rawQuery15.getCount(); i10++) {
                        if (i10 == rawQuery15.getCount() - 1) {
                            str27 = str27 + "<p class='w3-margin-top-0'><b>" + rawQuery15.getString(rawQuery15.getColumnIndex("title")) + " : </b>" + rawQuery15.getString(rawQuery15.getColumnIndex("value")) + "</p>";
                            rawQuery15.moveToNext();
                        } else if (i10 == 0) {
                            str27 = "<p class='w3-margin-top-0 w3-margin-bottom-0'><b>" + rawQuery15.getString(rawQuery15.getColumnIndex("title")) + " : </b>" + rawQuery15.getString(rawQuery15.getColumnIndex("value")) + "</p>";
                            rawQuery15.moveToNext();
                        } else {
                            str27 = str27 + "<p class='w3-margin-top-0 w3-margin-bottom-0'><b>" + rawQuery15.getString(rawQuery15.getColumnIndex("title")) + " : </b>" + rawQuery15.getString(rawQuery15.getColumnIndex("value")) + "</p>";
                            rawQuery15.moveToNext();
                        }
                    }
                    str26 = str27;
                }
                rawQuery15.close();
                preview.sub_edu += "      <h6 class='w3-margin-bottom-0'><b>" + string33 + " <span class='w3-right'>" + string35 + "</span></b></h6>      <p class='w3-margin-top-0 w3-margin-bottom-0'>" + string34 + "<span class='w3-right w3-margin-top-0 w3-margin-bottom-0'>" + string36 + "</span></p>" + str26;
                str25 = str25 + "    <div class='w3-container'>    <h5 class='w3-margin-bottom-0'><b>" + string33 + "<span class='w3-right'>" + string35 + "</span></b> </h5>    <p class='w3-margin-bottom-0'>" + string34 + "<span class='w3-right'>" + string36 + "</span></p>" + str26 + "</div><hr>";
                str24 = str24 + "    <div class='w3-container w3-margin-top-large'>        <h5  class=' w3-margin-bottom-0'><b>" + string33 + " <span class='w3-right'>" + string35 + "</b></span></h5>        <p class='w3-margin-top-0 w3-margin-bottom-0'>" + string34 + "<span class='w3-right'>" + string36 + "</span></p>" + str26 + "    </div>";
                rawQuery13.moveToNext();
            }
            preview.education = "    <div class='w3-no-break w3-row w3-container'>     <h6 style='color:#" + preview.tit_color + ";' class='w3-quarter t4-left t3-headeing-color w3-upper-case'>" + str23 + " -</h6>     <div  style='border-color:#" + preview.tit_color + ";' class='w3-threequarter t4-right w3-container t4-border-bottom'>" + preview.sub_edu + "</div>    </div>";
            StringBuilder sb15 = new StringBuilder();
            sb15.append("<div class='w3-no-break w3-container w3-white w3-margin-bottom'>    <h2 class='w3-text-black w3-capitalize'><i style='color:#");
            sb15.append(preview.bg_color);
            sb15.append(";' class='fa fa-book fa-fw w3-margin-right w3-xlarge w3-text-templete_color_1'></i>");
            sb15.append(str23);
            sb15.append("</h2>");
            sb15.append(str25);
            sb15.append("</div>");
            preview.education2 = sb15.toString();
            preview.education3 = "<div class='w3-no-break w3-container'>    <h2 style='background-color:#" + preview.tit_color + ";' class='w3-block_background w3-margin-top-large w3-capitalize'><i style='box-shadow:0px 0px 0px 15px #" + preview.bg_color + "; background-color:#" + preview.bg_color + ";' class='fa fa-book w3-xxlarge w3-circle w3-color-shadow'></i>" + str23 + "</h2>" + str24 + "</div>";
            StringBuilder sb16 = new StringBuilder();
            sb16.append("<div style='border-color:#");
            sb16.append(preview.tit_color);
            sb16.append(";' class='w3-no-break w3-row w3-container t3-border-bottom'>  <h4 style='color:#");
            sb16.append(preview.tit_color);
            sb16.append(";' class='w3-quarter t3-left t3-headeing-color w3-upper-case'>");
            sb16.append(str23);
            sb16.append("</h4>  <div class='w3-threequarter t3-right w3-container'>");
            sb16.append(preview.sub_edu);
            sb16.append(" </div > </div>");
            preview.education4 = sb16.toString();
            preview.education5 = "<div class='w3-no-break w3-row w3-container'>         <h6 class='w3-quarter t5-left w3-upper-case'>" + str23 + "</h6>         <div class='w3-threequarter w3-container t5-right'>" + preview.sub_edu + "         </div>     </div>";
            preview.education6 = "<div class='w3-no-break w3-row w3-border-bottom w3-container'>         <h6 class='w3-quarter t6-left w3-upper-case'>" + str23 + "</h6>         <div class='w3-threequarter w3-container'>" + preview.sub_edu + "                </div>     </div>";
            preview.education7 = "<div class='w3-no-break w3-row'>         <h6 style='background-color:#" + preview.tit_color + ";' class='t7-block_background w3-padding-tinysmall w3-upper-case'>" + str23 + "</h6>         <div class='w3-container list'>" + preview.sub_edu + "         </div>     </div>";
            preview.education8 = "<div class='w3-no-break w3-row w3-border-bottom'>         <h6 style='color:#" + preview.tit_color + ";' class='t8-block_background w3-padding-tinysmall w3-upper-case'>" + str23 + "</h6>         <div class='w3-container list'>" + preview.sub_edu + "         </div>     </div>";
            preview.education9 = "<div class='w3-no-break w3-row'>         <h6 style='color:#" + preview.tit_color + ";border-bottom: 1px solid #" + preview.tit_color + "!important;border-top: 1px solid #" + preview.tit_color + "!important;' class='t9-block_background w3-padding-tinysmall w3-upper-case'>" + str23 + "</h6>         <div class='w3-container list'>" + preview.sub_edu + "         </div>     </div>";
        }
        rawQuery13.close();
        Cursor rawQuery16 = preview.DB.rawQuery("select * from skill_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery16.getCount() > 0) {
            rawQuery16.moveToFirst();
            String str28 = "";
            Cursor rawQuery17 = preview.DB.rawQuery("Select skill_title from title_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
            rawQuery17.moveToFirst();
            if (rawQuery17.getCount() > 0 && (string10 = rawQuery17.getString(rawQuery17.getColumnIndex("skill_title"))) != null) {
                str28 = string10;
            }
            rawQuery17.close();
            String str29 = "";
            for (int i11 = 0; i11 < rawQuery16.getCount(); i11++) {
                String string37 = rawQuery16.getString(rawQuery16.getColumnIndex("skill"));
                preview.sub_skill += "<h6>" + string37 + "</h6>";
                str29 = str29 + "<li>" + string37 + "</li>";
                rawQuery16.moveToNext();
            }
            preview.skill2 = " <div class='w3-no-break'>     <p style='color:#" + preview.tit_color + ";' class='w3-large w3-templete_color_3 w3-capitalize'><b><i style='color:#" + preview.tit_color + ";' class='fa fa-asterisk fa-fw w3-margin-right w3-templete_color_3'></i>" + str28 + "</b></p>     <ul class='w3-large ul-space' style='list-style: none;'>" + str29 + "     </ul>     <br> </div>";
            preview.skill = "<div class='w3-no-break w3-row w3-container'> <h6 style='color:#" + preview.tit_color + ";' class='w3-quarter t4-left t3-headeing-color w3-upper-case'>" + str28 + " -</h6> <div style='border-color:#" + preview.tit_color + ";' class='w3-threequarter t4-right w3-container t4-border-bottom'>" + preview.sub_skill + "</div></div>";
            StringBuilder sb17 = new StringBuilder();
            sb17.append(" <div class='w3-no-break w3-container'>     <h3 style='background-color:#");
            sb17.append(preview.tit_color);
            sb17.append(";' class='w3-block_background right w3-margin-top'>");
            sb17.append(str28);
            sb17.append("</h3>     <ul class='w3-large' style='list-style: square;padding-left: 24px'>");
            sb17.append(str29);
            sb17.append("     </ul>     <br> </div>");
            preview.skill3 = sb17.toString();
            preview.skill4 = "<div style='border-color:#" + preview.tit_color + ";' class='w3-no-break w3-row w3-container t3-border-bottom'>   <h4 style='color:#" + preview.tit_color + ";' class='w3-quarter t3-left t3-headeing-color w3-upper-case'>" + str28 + "</h4>   <div class='w3-threequarter t3-right w3-container'>" + preview.sub_skill + "   </div> </div>";
            StringBuilder sb18 = new StringBuilder();
            sb18.append("<div class='w3-no-break w3-row w3-container'>     <h6 class='w3-quarter t5-left w3-upper-case'>");
            sb18.append(str28);
            sb18.append("</h6>     <div class='w3-threequarter w3-container t5-right'>");
            sb18.append(preview.sub_skill);
            sb18.append("     </div> </div>");
            preview.skill5 = sb18.toString();
            preview.skill6 = "<div class='w3-no-break w3-row w3-border-bottom w3-container'>     <h6 class='w3-quarter t6-left w3-upper-case'>" + str28 + "</h6>     <div class='w3-threequarter w3-container'>" + preview.sub_skill + "     </div> </div>";
            preview.skill7 = "<div class='w3-no-break w3-row'>     <h6 style='background-color:#" + preview.tit_color + ";' class='t7-block_background w3-padding-tinysmall w3-upper-case'>" + str28 + "</h6>     <div class='w3-container list'>" + preview.sub_skill + "     </div> </div>";
            preview.skill8 = "<div class='w3-no-break w3-row w3-border-bottom'>     <h6 style='color:#" + preview.tit_color + ";' class='t8-block_background w3-padding-tinysmall w3-upper-case'>" + str28 + "</h6>     <div class='w3-container list'>" + preview.sub_skill + "     </div> </div>";
            preview.skill9 = "<div class='w3-no-break w3-row'>     <h6 style='color:#" + preview.tit_color + ";border-bottom: 1px solid #" + preview.tit_color + "!important;border-top: 1px solid #" + preview.tit_color + "!important;' class='t9-block_background w3-padding-tinysmall w3-upper-case'>" + str28 + "</h6>     <div class='w3-container list'>" + preview.sub_skill + "     </div> </div>";
        }
        rawQuery16.close();
        Cursor rawQuery18 = preview.DB.rawQuery("select * from strength_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery18.getCount() > 0) {
            rawQuery18.moveToFirst();
            String str30 = "";
            Cursor rawQuery19 = preview.DB.rawQuery("Select strength_title from title_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
            rawQuery19.moveToFirst();
            if (rawQuery19.getCount() > 0 && (string9 = rawQuery19.getString(rawQuery19.getColumnIndex("strength_title"))) != null) {
                str30 = string9;
            }
            rawQuery19.close();
            String str31 = "";
            for (int i12 = 0; i12 < rawQuery18.getCount(); i12++) {
                String string38 = rawQuery18.getString(rawQuery18.getColumnIndex("strength"));
                preview.sub_strength += "<h6>" + string38 + "</h6>";
                str31 = str31 + "<li>" + string38 + "</li>";
                rawQuery18.moveToNext();
            }
            preview.strength = "    <div class='w3-no-break w3-row w3-container'>     <h6 style='color:#" + preview.tit_color + ";' class='w3-quarter t4-left t3-headeing-color w3-upper-case'>" + str30 + " -</h6>     <div style='border-color:#" + preview.tit_color + ";' class='w3-threequarter t4-right w3-container t4-border-bottom'>" + preview.sub_strength + "</div>    </div>";
            preview.strength2 = "  <div class='w3-no-break'>      <p style='color:#" + preview.tit_color + ";' class='w3-large w3-templete_color_3 w3-capitalize'><b><i style='color:#" + preview.tit_color + ";' class='fa fa-asterisk fa-fw w3-margin-right w3-templete_color_3'></i>" + str30 + "</b></p>      <ul class='w3-large ul-space' style='list-style: none;'>" + str31 + "      </ul>      <br>  </div>";
            StringBuilder sb19 = new StringBuilder();
            sb19.append("<div class='w3-no-break w3-container'>    <h3 style='background-color:#");
            sb19.append(preview.tit_color);
            sb19.append(";' class='w3-block_background right w3-margin-top'>");
            sb19.append(str30);
            sb19.append("</h3>    <ul class='w3-large' style='list-style: square;padding-left: 24px'>");
            sb19.append(str31);
            sb19.append("    </ul>    <br></div>");
            preview.strength3 = sb19.toString();
            preview.strength4 = "<div style='border-color:#" + preview.tit_color + ";' class='w3-no-break w3-row w3-container t3-border-bottom'>      <h4 style='color:#" + preview.tit_color + ";' class='w3-quarter t3-left t3-headeing-color w3-upper-case'>" + str30 + "</h4>      <div class='w3-threequarter t3-right w3-container'>" + preview.sub_strength + "      </div>    </div>";
            StringBuilder sb20 = new StringBuilder();
            sb20.append("<div class='w3-no-break w3-row w3-container'>        <h6 class='w3-quarter t5-left w3-upper-case'>");
            sb20.append(str30);
            sb20.append("</h6>        <div class='w3-threequarter w3-container t5-right'>");
            sb20.append(preview.sub_strength);
            sb20.append("        </div>    </div>");
            preview.strength5 = sb20.toString();
            preview.strength6 = "<div class='w3-no-break w3-row w3-border-bottom w3-container'>        <h6 class='w3-quarter t6-left w3-upper-case'>" + str30 + "</h6>        <div class='w3-threequarter w3-container'>" + preview.sub_strength + "        </div>    </div>";
            preview.strength7 = "<div class='w3-no-break w3-row'>    <h6 style='background-color:#" + preview.tit_color + ";' class='t7-block_background w3-padding-tinysmall w3-upper-case'>" + str30 + "</h6>    <div class='w3-container list'>" + preview.sub_strength + "                    </div>                </div>";
            preview.strength8 = "<div class='w3-no-break w3-row w3-border-bottom'>        <h6 style='color:#" + preview.tit_color + ";' class='t8-block_background w3-padding-tinysmall w3-upper-case'>" + str30 + "</h6>        <div class='w3-container list'>" + preview.sub_strength + "        </div>    </div>";
            preview.strength9 = "<div class='w3-no-break w3-row'>        <h6 style='color:#" + preview.tit_color + ";border-bottom: 1px solid #" + preview.tit_color + "!important;border-top: 1px solid #" + preview.tit_color + "!important;' class='t9-block_background w3-padding-tinysmall w3-upper-case'>" + str30 + "</h6>        <div class='w3-container list'>" + preview.sub_strength + "        </div>    </div>";
        }
        rawQuery18.close();
        Cursor rawQuery20 = preview.DB.rawQuery("select * from interest_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery20.getCount() > 0) {
            rawQuery20.moveToFirst();
            String str32 = "";
            Cursor rawQuery21 = preview.DB.rawQuery("Select interest_title from title_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
            rawQuery21.moveToFirst();
            if (rawQuery21.getCount() > 0 && (string8 = rawQuery21.getString(rawQuery21.getColumnIndex("interest_title"))) != null) {
                str32 = string8;
            }
            rawQuery21.close();
            String str33 = "";
            for (int i13 = 0; i13 < rawQuery20.getCount(); i13++) {
                String string39 = rawQuery20.getString(rawQuery20.getColumnIndex("interest"));
                preview.sub_intrest += "<h6>" + string39 + "</h6>";
                str33 = str33 + "<li>" + string39 + "</li>";
                rawQuery20.moveToNext();
            }
            preview.intrest = "    <div class='w3-no-break w3-row w3-container'>     <h6 style='color:#" + preview.tit_color + ";' class='w3-quarter t4-left t3-headeing-color w3-upper-case'>" + str32 + " -</h6>     <div style='border-color:#" + preview.tit_color + ";' class='w3-threequarter t4-right w3-container t4-border-bottom'>" + preview.sub_intrest + "</div>    </div>";
            preview.intrest2 = "   <div class='w3-no-break'>       <p style='color:#" + preview.tit_color + ";' class='w3-large w3-templete_color_3 w3-capitalize'><b><i style='color:#" + preview.tit_color + ";' class='fa fa-asterisk fa-fw w3-margin-right w3-templete_color_3'></i>" + str32 + "</b></p>       <ul class='w3-large ul-space' style='list-style: none;'>" + str33 + "       </ul>       <br>   </div>";
            StringBuilder sb21 = new StringBuilder();
            sb21.append("<div class='w3-no-break w3-container'>    <h3 style='background-color:#");
            sb21.append(preview.tit_color);
            sb21.append(";' class='w3-block_background right w3-margin-top'>");
            sb21.append(str32);
            sb21.append("</h3>    <ul class='w3-large' style='list-style: square;padding-left: 24px'>");
            sb21.append(str33);
            sb21.append("    </ul>    <br></div>");
            preview.intrest3 = sb21.toString();
            preview.intrest4 = "<div style='border-color:#" + preview.tit_color + ";' class='w3-no-break w3-row w3-container t3-border-bottom'>     <h4 style='color:#" + preview.tit_color + ";' class='w3-quarter t3-left t3-headeing-color w3-upper-case'>" + str32 + "</h4>     <div class='w3-threequarter t3-right w3-container'>" + preview.sub_intrest + "</div>   </div>";
            StringBuilder sb22 = new StringBuilder();
            sb22.append("   <div class='w3-no-break w3-row w3-container'>       <h6 class='w3-quarter t5-left w3-upper-case'>");
            sb22.append(str32);
            sb22.append("</h6>       <div class='w3-threequarter w3-container t5-right'>");
            sb22.append(preview.sub_intrest);
            sb22.append("       </div>   </div>");
            preview.intrest5 = sb22.toString();
            preview.intrest6 = "<div class='w3-no-break w3-row w3-border-bottom w3-container'>       <h6 class='w3-quarter t6-left w3-upper-case'>" + str32 + "</h6>       <div class='w3-threequarter w3-container'>" + preview.sub_intrest + "       </div>   </div>";
            preview.intrest7 = "<div class='w3-no-break w3-row'>       <h6 style='background-color:#" + preview.tit_color + ";' class='t7-block_background w3-padding-tinysmall w3-upper-case'>" + str32 + "</h6>       <div class='w3-container list'>" + preview.sub_intrest + "       </div>   </div>";
            preview.intrest8 = "<div class='w3-no-break w3-row w3-border-bottom'>       <h6 style='color:#" + preview.tit_color + ";' class='t8-block_background w3-padding-tinysmall w3-upper-case'>" + str32 + "</h6>       <div class='w3-container list'>" + preview.sub_intrest + "       </div>   </div>";
            preview.intrest9 = "<div class='w3-no-break w3-row'>       <h6 style='color:#" + preview.tit_color + ";border-bottom: 1px solid #" + preview.tit_color + "!important;border-top: 1px solid #" + preview.tit_color + "!important;' class='t9-block_background w3-padding-tinysmall w3-upper-case'>" + str32 + "</h6>       <div class='w3-container list'>" + preview.sub_intrest + "       </div>   </div>";
        }
        rawQuery20.close();
        Cursor rawQuery22 = preview.DB.rawQuery("select * from industrial_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery22.getCount() > 0) {
            rawQuery22.moveToFirst();
            String str34 = "";
            Cursor rawQuery23 = preview.DB.rawQuery("Select industrial_title from title_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
            rawQuery23.moveToFirst();
            if (rawQuery23.getCount() > 0 && (string7 = rawQuery23.getString(rawQuery23.getColumnIndex("industrial_title"))) != null) {
                str34 = string7;
            }
            rawQuery23.close();
            String str35 = "";
            String str36 = "";
            for (int i14 = 0; i14 < rawQuery22.getCount(); i14++) {
                String string40 = rawQuery22.getString(rawQuery22.getColumnIndex("industrial"));
                preview.sub_indust += "<h6>" + string40 + "</h6>";
                str36 = str36 + "<div class='w3-container'>        <h5>" + string40 + "</h5>    </div><hr>";
                str35 = str35 + "<div class='w3-container w3-margin-top-large'>        <h5>" + string40 + "</h5>    </div>";
                rawQuery22.moveToNext();
            }
            preview.indrust = "    <div class='w3-no-break w3-row w3-container'>     <h6 style='color:#" + preview.tit_color + ";' class='w3-quarter t4-left t3-headeing-color w3-upper-case'>" + str34 + " -</h6>     <div style='border-color:#" + preview.tit_color + ";' class='w3-threequarter t4-right w3-container t4-border-bottom'>" + preview.sub_indust + "</div>    </div>";
            StringBuilder sb23 = new StringBuilder();
            sb23.append("<div class='w3-no-break w3-container w3-white w3-margin-bottom'>    <h2 class='w3-text-black w3-capitalize'><i style='color:#");
            sb23.append(preview.bg_color);
            sb23.append(";' class='fa fa-anchor fa-fw w3-margin-right w3-xlarge w3-text-templete_color_1'></i>");
            sb23.append(str34);
            sb23.append("</h2>");
            sb23.append(str36);
            sb23.append("</div>");
            preview.indrust2 = sb23.toString();
            preview.indrust3 = "<div class='w3-no-break w3-container'>    <h2 style='background-color:#" + preview.tit_color + ";' class='w3-block_background w3-margin-top-large w3-capitalize'><i style='box-shadow:0px 0px 0px 15px #" + preview.bg_color + "; background-color:#" + preview.bg_color + ";' class='fa fa-anchor w3-xxlarge w3-circle w3-color-shadow'></i>" + str34 + "</h2>" + str35 + "</div>";
            StringBuilder sb24 = new StringBuilder();
            sb24.append("<div style='border-color:#");
            sb24.append(preview.tit_color);
            sb24.append(";' class='w3-no-break w3-row w3-container t3-border-bottom'>     <h4 style='color:#");
            sb24.append(preview.tit_color);
            sb24.append(";' class='w3-quarter t3-left t3-headeing-color w3-upper-case'>");
            sb24.append(str34);
            sb24.append("</h4>     <div class='w3-threequarter t3-right w3-container'>");
            sb24.append(preview.sub_indust);
            sb24.append("     </div>   </div>");
            preview.indrust4 = sb24.toString();
            preview.indrust5 = "<div class='w3-no-break w3-row w3-container'>       <h6 class='w3-quarter t5-left w3-upper-case'>" + str34 + "</h6>       <div class='w3-threequarter w3-container t5-right'>" + preview.sub_indust + "       </div>   </div>";
            preview.indrust6 = "<div class='w3-no-break w3-row w3-border-bottom w3-container'>        <h6 class='w3-quarter t6-left w3-upper-case'>" + str34 + "</h6>        <div class='w3-threequarter w3-container'>" + preview.sub_indust + "                </div>   </div>";
            preview.indrust7 = "<div class='w3-no-break w3-row'>       <h6 style='background-color:#" + preview.tit_color + ";' class='t7-block_background w3-padding-tinysmall w3-upper-case'>" + str34 + "</h6>       <div class='w3-container list'>" + preview.sub_indust + "       </div>   </div>";
            preview.indrust8 = "<div class='w3-no-break w3-row w3-border-bottom'>       <h6 style='color:#" + preview.tit_color + ";' class='t8-block_background w3-padding-tinysmall w3-upper-case'>" + str34 + "</h6>       <div class='w3-container list'>" + preview.sub_indust + "       </div>   </div>";
            preview.indrust9 = "<div class='w3-no-break w3-row'>       <h6 style='color:#" + preview.tit_color + ";border-bottom: 1px solid #" + preview.tit_color + "!important;border-top: 1px solid #" + preview.tit_color + "!important;' class='t9-block_background w3-padding-tinysmall w3-upper-case'>" + str34 + "</h6>       <div class='w3-container list'>" + preview.sub_indust + "       </div>   </div>";
        }
        rawQuery22.close();
        Cursor rawQuery24 = preview.DB.rawQuery("select * from inplant_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery24.getCount() > 0) {
            rawQuery24.moveToFirst();
            String str37 = "";
            Cursor rawQuery25 = preview.DB.rawQuery("Select inplant_title from title_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
            rawQuery25.moveToFirst();
            if (rawQuery25.getCount() > 0 && (string6 = rawQuery25.getString(rawQuery25.getColumnIndex("inplant_title"))) != null) {
                str37 = string6;
            }
            rawQuery25.close();
            String str38 = "";
            String str39 = "";
            for (int i15 = 0; i15 < rawQuery24.getCount(); i15++) {
                String string41 = rawQuery24.getString(rawQuery24.getColumnIndex("inplant"));
                preview.sub_inplant += "<h6>" + string41 + "</h6>";
                str39 = str39 + "    <div class='w3-container'>        <h5>" + string41 + "</h5>    </div>    <hr>";
                str38 = str38 + "    <div class='w3-container w3-margin-top-large'>    <h5>" + string41 + "</h5></div>";
                rawQuery24.moveToNext();
            }
            preview.inplant = "    <div class='w3-no-break w3-row w3-container'>   <h6 style='color:#" + preview.tit_color + ";' class='w3-quarter t4-left t3-headeing-color w3-upper-case'>" + str37 + " -</h6>   <div style='border-color:#" + preview.tit_color + ";' class='w3-threequarter t4-right w3-container t4-border-bottom'>" + preview.sub_inplant + "</div></div>";
            StringBuilder sb25 = new StringBuilder();
            sb25.append("<div class='w3-no-break w3-container w3-white w3-margin-bottom'>    <h2 class='w3-text-black w3-capitalize'><i style='color:#");
            sb25.append(preview.bg_color);
            sb25.append(";' class='fa fa-building fa-fw w3-margin-right w3-xlarge w3-text-templete_color_1'></i>");
            sb25.append(str37);
            sb25.append("</h2>");
            sb25.append(str39);
            sb25.append("</div>");
            preview.inplant2 = sb25.toString();
            preview.inplant3 = "<div class='w3-no-break w3-container'>    <h2 style='background-color:#" + preview.tit_color + ";' class='w3-block_background w3-margin-top-large w3-capitalize'><i style='box-shadow:0px 0px 0px 15px #" + preview.bg_color + "; background-color:#" + preview.bg_color + ";' class='fa fa-building w3-xxlarge w3-circle w3-color-shadow'></i>" + str37 + "</h2>" + str38 + "</div>";
            StringBuilder sb26 = new StringBuilder();
            sb26.append("<div style='border-color:#");
            sb26.append(preview.tit_color);
            sb26.append(";' class='w3-no-break w3-row w3-container t3-border-bottom'>     <h4 style='color:#");
            sb26.append(preview.tit_color);
            sb26.append(";' class='w3-quarter t3-left t3-headeing-color w3-upper-case'>");
            sb26.append(str37);
            sb26.append("</h4>     <div class='w3-threequarter t3-right w3-container'>");
            sb26.append(preview.sub_inplant);
            sb26.append("     </div>   </div>");
            preview.inplant4 = sb26.toString();
            preview.inplant5 = "<div class='w3-no-break w3-row w3-container'>       <h6 class='w3-quarter t5-left w3-upper-case'>Inplant Training</h6>       <div class='w3-threequarter w3-container t5-right'>" + preview.sub_inplant + "       </div>   </div>";
            preview.inplant6 = "<div class='w3-no-break w3-row w3-border-bottom w3-container'>       <h6 class='w3-quarter t6-left w3-upper-case'>" + str37 + "</h6>       <div class='w3-threequarter w3-container'>" + preview.sub_inplant + "       </div>   </div>";
            preview.inplant7 = "<div class='w3-no-break w3-row'>       <h6 style='background-color:#" + preview.tit_color + ";' class='t7-block_background w3-padding-tinysmall w3-upper-case'>" + str37 + "</h6>       <div class='w3-container list'>" + preview.sub_inplant + "       </div>   </div>";
            preview.inplant8 = "<div class='w3-no-break w3-row w3-border-bottom'>       <h6 style='color:#" + preview.tit_color + ";' class='t8-block_background w3-padding-tinysmall w3-upper-case'>" + str37 + "</h6>       <div class='w3-container list'>" + preview.sub_inplant + "       </div>   </div>";
            preview.inplant9 = "<div class='w3-no-break w3-row'>       <h6 style='color:#" + preview.tit_color + ";border-bottom: 1px solid #" + preview.tit_color + "!important;border-top: 1px solid #" + preview.tit_color + "!important;' class='t9-block_background w3-padding-tinysmall w3-upper-case'>" + str37 + "</h6>       <div class='w3-container list'>" + preview.sub_inplant + "       </div>   </div>";
        }
        rawQuery24.close();
        Cursor rawQuery26 = preview.DB.rawQuery("select * from achievement_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery26.getCount() > 0) {
            rawQuery26.moveToFirst();
            String str40 = "";
            Cursor rawQuery27 = preview.DB.rawQuery("Select achieve_title from title_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
            rawQuery27.moveToFirst();
            if (rawQuery27.getCount() > 0 && (string5 = rawQuery27.getString(rawQuery27.getColumnIndex("achieve_title"))) != null) {
                str40 = string5;
            }
            rawQuery27.close();
            String str41 = "";
            String str42 = "";
            for (int i16 = 0; i16 < rawQuery26.getCount(); i16++) {
                String string42 = rawQuery26.getString(rawQuery26.getColumnIndex("achieve"));
                preview.sub_achive += "<h6>" + string42 + "</h6>";
                str42 = str42 + "    <div class='w3-container'>        <h5>" + string42 + "</h5>    </div>    <hr>";
                str41 = str41 + "    <div class='w3-container w3-margin-top-large'>        <h5>" + string42 + "</h5>    </div>";
                rawQuery26.moveToNext();
            }
            preview.achive = "    <div class='w3-no-break w3-row w3-container'>     <h6 style='color:#" + preview.tit_color + ";' class='w3-quarter t4-left t3-headeing-color w3-upper-case'>" + str40 + " -</h6>     <div style='border-color:#" + preview.tit_color + ";' class='w3-threequarter t4-right w3-container t4-border-bottom'>" + preview.sub_achive + "</div>    </div>";
            StringBuilder sb27 = new StringBuilder();
            sb27.append(" <div class='w3-no-break w3-container w3-white w3-margin-bottom'>    <h2 class='w3-text-black w3-capitalize'><i style='color:#");
            sb27.append(preview.bg_color);
            sb27.append(";' class='fa fa-diamond fa-fw w3-margin-right w3-xlarge w3-text-templete_color_1'></i>");
            sb27.append(str40);
            sb27.append("</h2>");
            sb27.append(str42);
            sb27.append("</div>");
            preview.achive2 = sb27.toString();
            preview.achive3 = "            <div class='w3-no-break w3-container'>    <h2 style='background-color:#" + preview.tit_color + ";' class='w3-block_background w3-margin-top-large w3-capitalize'><i style='box-shadow:0px 0px 0px 15px #" + preview.bg_color + "; background-color:#" + preview.bg_color + ";' class='fa fa-diamond w3-xxlarge w3-circle w3-color-shadow'></i>" + str40 + "</h2>" + str41 + "</div>";
            StringBuilder sb28 = new StringBuilder();
            sb28.append("  <div style='border-color:#");
            sb28.append(preview.tit_color);
            sb28.append(";' class='w3-no-break w3-row w3-container t3-border-bottom'>    <h4 style='color:#");
            sb28.append(preview.tit_color);
            sb28.append(";' class='w3-quarter t3-left t3-headeing-color w3-upper-case'>");
            sb28.append(str40);
            sb28.append("</h4>    <div class='w3-threequarter t3-right w3-container'>");
            sb28.append(preview.sub_achive);
            sb28.append("    </div>  </div>");
            preview.achive4 = sb28.toString();
            preview.achive5 = "<div class='w3-no-break w3-row w3-container'>      <h6 class='w3-quarter t5-left w3-upper-case'>" + str40 + "</h6>      <div class='w3-threequarter w3-container t5-right'>" + preview.sub_achive + "      </div>  </div>";
            preview.achive6 = "<div class='w3-no-break w3-row w3-border-bottom w3-container'>      <h6 class='w3-quarter t6-left w3-upper-case'>" + str40 + "</h6>      <div class='w3-threequarter w3-container'>" + preview.sub_achive + "      </div>  </div>";
            preview.achive7 = "<div class='w3-no-break w3-row'>      <h6 style='background-color:#" + preview.tit_color + ";' class='t7-block_background w3-padding-tinysmall w3-upper-case'>" + str40 + "</h6>      <div class='w3-container list'>" + preview.sub_achive + "      </div>  </div>";
            preview.achive8 = "<div class='w3-no-break w3-row w3-border-bottom'>      <h6 style='color:#" + preview.tit_color + ";' class='t8-block_background w3-padding-tinysmall w3-upper-case'>" + str40 + "</h6>      <div class='w3-container list'>" + preview.sub_achive + "      </div>  </div>";
            preview.achive9 = "<div class='w3-no-break w3-row'>      <h6 style='color:#" + preview.tit_color + ";border-bottom: 1px solid #" + preview.tit_color + "!important;border-top: 1px solid #" + preview.tit_color + "!important;' class='t9-block_background w3-padding-tinysmall w3-upper-case'>" + str40 + "</h6>      <div class='w3-container list'>" + preview.sub_achive + "      </div>  </div>";
        }
        rawQuery26.close();
        Cursor rawQuery28 = preview.DB.rawQuery("select * from cocurricular_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery28.getCount() > 0) {
            rawQuery28.moveToFirst();
            String str43 = "";
            Cursor rawQuery29 = preview.DB.rawQuery("Select cocurricular_title from title_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
            rawQuery29.moveToFirst();
            if (rawQuery29.getCount() > 0 && (string4 = rawQuery29.getString(rawQuery29.getColumnIndex("cocurricular_title"))) != null) {
                str43 = string4;
            }
            rawQuery29.close();
            String str44 = "";
            String str45 = "";
            for (int i17 = 0; i17 < rawQuery28.getCount(); i17++) {
                String string43 = rawQuery28.getString(rawQuery28.getColumnIndex("cocurricular"));
                str45 = str45 + "<div class='w3-container'>    <h5>" + string43 + "</h5></div><hr>";
                str44 = str44 + "    <div class='w3-container w3-margin-top-large'>        <h5>" + string43 + "</h5>    </div>";
                preview.sub_cocuri += "<h6>" + string43 + "</h6>";
                rawQuery28.moveToNext();
            }
            preview.cocuri = "    <div class='w3-no-break w3-row w3-container'>     <h6 style='color:#" + preview.tit_color + ";' class='w3-quarter t4-left t3-headeing-color w3-upper-case'>" + str43 + " -</h6>     <div style='border-color:#" + preview.tit_color + ";' class='w3-threequarter t4-right w3-container t4-border-bottom'>" + preview.sub_cocuri + "</div>    </div>";
            StringBuilder sb29 = new StringBuilder();
            sb29.append("<div class='w3-no-break w3-container w3-white w3-margin-bottom'>     <h2 class='w3-text-black w3-capitalize'><i style='color:#");
            sb29.append(preview.bg_color);
            sb29.append(";' class='fa fa-newspaper-o fa-fw w3-margin-right w3-xlarge w3-text-templete_color_1'></i>");
            sb29.append(str43);
            sb29.append("</h2>");
            sb29.append(str45);
            sb29.append(" </div>");
            preview.cocuri2 = sb29.toString();
            preview.cocuri3 = "<div class='w3-no-break w3-container'>    <h2 style='background-color:#" + preview.tit_color + ";' class='w3-block_background w3-margin-top-large w3-capitalize'><i style='box-shadow:0px 0px 0px 15px #" + preview.bg_color + "; background-color:#" + preview.bg_color + ";' class='fa fa-code w3-xxlarge w3-circle w3-color-shadow'></i>" + str43 + "</h2>" + str44 + "</div>";
            StringBuilder sb30 = new StringBuilder();
            sb30.append("<div style='border-color:#");
            sb30.append(preview.tit_color);
            sb30.append(";' class='w3-no-break w3-row w3-container t3-border-bottom'>    <h4 style='color:#");
            sb30.append(preview.tit_color);
            sb30.append(";' class='w3-quarter t3-left t3-headeing-color w3-upper-case'>");
            sb30.append(str43);
            sb30.append("</h4>    <div class='w3-threequarter t3-right w3-container'>");
            sb30.append(preview.sub_cocuri);
            sb30.append("    </div>  </div>");
            preview.cocuri4 = sb30.toString();
            preview.cocuri5 = "  <div class='w3-no-break w3-row w3-container'>      <h6 class='w3-quarter t5-left w3-upper-case'>" + str43 + "</h6>      <div class='w3-threequarter w3-container t5-right'>" + preview.sub_cocuri + "      </div>  </div>";
            preview.cocuri6 = "<div class='w3-no-break w3-row w3-border-bottom w3-container'>      <h6 class='w3-quarter t6-left w3-upper-case'>" + str43 + "</h6>      <div class='w3-threequarter w3-container'>" + preview.sub_cocuri + "      </div>  </div>";
            preview.cocuri7 = "<div class='w3-no-break w3-row'>      <h6 style='background-color:#" + preview.tit_color + ";' class='t7-block_background w3-padding-tinysmall w3-upper-case'>" + str43 + "</h6>      <div class='w3-container list'>" + preview.sub_cocuri + "      </div>  </div>";
            preview.cocuri8 = "<div class='w3-no-break w3-row w3-border-bottom'>      <h6 style='color:#" + preview.tit_color + ";' class='t8-block_background w3-padding-tinysmall w3-upper-case'>" + str43 + "</h6>      <div class='w3-container list'>" + preview.sub_cocuri + "      </div>  </div>";
            preview.cocuri9 = "<div class='w3-no-break w3-row'>      <h6 style='color:#" + preview.tit_color + ";border-bottom: 1px solid #" + preview.tit_color + "!important;border-top: 1px solid #" + preview.tit_color + "!important;' class='t9-block_background w3-padding-tinysmall w3-upper-case'>" + str43 + "</h6>      <div class='w3-container list'>" + preview.sub_cocuri + "      </div>  </div>";
        }
        rawQuery28.close();
        Cursor rawQuery30 = preview.DB.rawQuery("select * from extracurricular_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery30.getCount() > 0) {
            rawQuery30.moveToFirst();
            String str46 = "";
            Cursor rawQuery31 = preview.DB.rawQuery("Select extracurricular_title from title_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
            rawQuery31.moveToFirst();
            if (rawQuery31.getCount() > 0 && (string3 = rawQuery31.getString(rawQuery31.getColumnIndex("extracurricular_title"))) != null) {
                str46 = string3;
            }
            rawQuery31.close();
            String str47 = "";
            String str48 = "";
            for (int i18 = 0; i18 < rawQuery30.getCount(); i18++) {
                String string44 = rawQuery30.getString(rawQuery30.getColumnIndex("extracurricular"));
                preview.sub_extra += "<h6>" + string44 + "</h6>";
                str48 = str48 + "     <div class='w3-container'>         <h5>" + string44 + "</h5>     </div>     <hr>";
                str47 = str47 + "     <div class='w3-container w3-margin-top-large'>         <h5>" + string44 + "</h5>     </div>";
                rawQuery30.moveToNext();
            }
            preview.extra = "    <div class='w3-no-break w3-row w3-container'>     <h6 style='color:#" + preview.tit_color + ";' class='w3-quarter t4-left t3-headeing-color w3-upper-case'>" + str46 + " -</h6>     <div style='border-color:#" + preview.tit_color + ";' class='w3-threequarter t4-right w3-container t4-border-bottom'>" + preview.sub_extra + "</div>    </div>";
            StringBuilder sb31 = new StringBuilder();
            sb31.append(" <div class='w3-no-break w3-container w3-white w3-margin-bottom'>     <h2 class='w3-text-black w3-capitalize'><i style='color:#");
            sb31.append(preview.bg_color);
            sb31.append(";' class='fa fa-trophy fa-fw w3-margin-right w3-xlarge w3-text-templete_color_1'></i>");
            sb31.append(str46);
            sb31.append("</h2>");
            sb31.append(str48);
            sb31.append(" </div>");
            preview.extra2 = sb31.toString();
            preview.extra3 = " <div class='w3-no-break w3-container'>     <h2 style='background-color:#" + preview.tit_color + ";' class='w3-block_background w3-margin-top-large w3-capitalize'><i style='box-shadow:0px 0px 0px 15px #" + preview.bg_color + "; background-color:#" + preview.bg_color + ";' class='fa fa-trophy w3-xxlarge w3-circle w3-color-shadow'></i>" + str46 + "</h2>" + str47 + " </div>";
            StringBuilder sb32 = new StringBuilder();
            sb32.append("<div style='border-color:#");
            sb32.append(preview.tit_color);
            sb32.append(";' class='w3-no-break w3-row w3-container t3-border-bottom'>   <h4 style='color:#");
            sb32.append(preview.tit_color);
            sb32.append(";' class='w3-quarter t3-left t3-headeing-color w3-upper-case'>");
            sb32.append(str46);
            sb32.append("</h4>   <div class='w3-threequarter t3-right w3-container'>");
            sb32.append(preview.sub_extra);
            sb32.append("   </div> </div>");
            preview.extra4 = sb32.toString();
            preview.extra5 = "<div class='w3-no-break w3-row w3-container'>     <h6 class='w3-quarter t5-left w3-upper-case'>" + str46 + "</h6>     <div class='w3-threequarter w3-container t5-right'>" + preview.sub_extra + "     </div> </div>";
            preview.extra6 = "<div class='w3-no-break w3-row w3-border-bottom w3-container'>     <h6 class='w3-quarter t6-left w3-upper-case'>" + str46 + "</h6>     <div class='w3-threequarter w3-container'>" + preview.sub_extra + "     </div> </div>";
            preview.extra7 = "<div class='w3-no-break w3-row'>     <h6 style='background-color:#" + preview.tit_color + ";' class='t7-block_background w3-padding-tinysmall w3-upper-case'>" + str46 + "</h6>     <div class='w3-container list'>" + preview.sub_extra + "     </div> </div>";
            preview.extra8 = "<div class='w3-no-break w3-row w3-border-bottom'>     <h6 style='color:#" + preview.tit_color + ";' class='t8-block_background w3-padding-tinysmall w3-upper-case'>" + str46 + "</h6>     <div class='w3-container list'>" + preview.sub_extra + "     </div> </div>";
            preview.extra9 = "<div class='w3-no-break w3-row'>     <h6 style='color:#" + preview.tit_color + ";border-bottom: 1px solid #" + preview.tit_color + "!important;border-top: 1px solid #" + preview.tit_color + "!important;' class='t9-block_background w3-padding-tinysmall w3-upper-case'>" + str46 + "</h6>     <div class='w3-container list'>" + preview.sub_extra + "     </div> </div>";
        }
        rawQuery30.close();
        Cursor rawQuery32 = preview.DB.rawQuery("select * from hobbies_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery32.getCount() > 0) {
            rawQuery32.moveToFirst();
            String str49 = "";
            Cursor rawQuery33 = preview.DB.rawQuery("Select hobbies_title from title_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
            rawQuery33.moveToFirst();
            if (rawQuery33.getCount() > 0 && (string2 = rawQuery33.getString(rawQuery33.getColumnIndex("hobbies_title"))) != null) {
                str49 = string2;
            }
            rawQuery33.close();
            String str50 = "";
            for (int i19 = 0; i19 < rawQuery32.getCount(); i19++) {
                String string45 = rawQuery32.getString(rawQuery32.getColumnIndex("hobbies"));
                preview.sub_hobbi += "<h6>" + string45 + "</h6>";
                str50 = str50 + "<li>" + string45 + "</li>";
                rawQuery32.moveToNext();
            }
            preview.hobbi = "    <div class='w3-no-break w3-row w3-container'>     <h6 style='color:#" + preview.tit_color + ";' class='w3-quarter t4-left t3-headeing-color w3-upper-case'>" + str49 + " -</h6>     <div style='border-color:#" + preview.tit_color + ";' class='w3-threequarter t4-right w3-container t4-border-bottom'>" + preview.sub_hobbi + "</div>    </div>";
            preview.hobbi2 = " <div class='w3-no-break'>     <p style='color:#" + preview.tit_color + ";' class='w3-large w3-templete_color_3 w3-capitalize'><b><i style='color:#" + preview.tit_color + ";' class='fa fa-asterisk fa-fw w3-margin-right w3-templete_color_3'></i>" + str49 + "</b></p>     <ul class='w3-large ul-space' style='list-style: none;'>" + str50 + "     </ul>     <br> </div>";
            StringBuilder sb33 = new StringBuilder();
            sb33.append(" <div class='w3-no-break w3-container'>     <h3 style='background-color:#");
            sb33.append(preview.tit_color);
            sb33.append(";' class='w3-block_background right w3-margin-top'>");
            sb33.append(str49);
            sb33.append("</h3>     <ul class='w3-large' style='list-style: square;padding-left: 24px'>");
            sb33.append(str50);
            sb33.append("     </ul>     <br> </div>");
            preview.hobbi3 = sb33.toString();
            preview.hobbi4 = "<div style='border-color:#" + preview.tit_color + ";' class='w3-no-break w3-row w3-container t3-border-bottom'>   <h4 style='color:#" + preview.tit_color + ";' class='w3-quarter t3-left t3-headeing-color w3-upper-case'>" + str49 + "</h4>   <div class='w3-threequarter t3-right w3-container'>" + preview.sub_hobbi + "   </div> </div>";
            StringBuilder sb34 = new StringBuilder();
            sb34.append("<div class='w3-no-break w3-row w3-container'>     <h6 class='w3-quarter t5-left w3-upper-case'>");
            sb34.append(str49);
            sb34.append("</h6>     <div class='w3-threequarter w3-container t5-right'>");
            sb34.append(preview.sub_hobbi);
            sb34.append("     </div> </div>");
            preview.hobbi5 = sb34.toString();
            preview.hobbi6 = "<div class='w3-no-break w3-row w3-border-bottom w3-container'>     <h6 class='w3-quarter t6-left w3-upper-case'>" + str49 + "</h6>     <div class='w3-threequarter w3-container'>" + preview.sub_hobbi + "     </div> </div>";
            preview.hobbi7 = "<div class='w3-no-break w3-row'>     <h6 style='background-color:#" + preview.tit_color + ";' class='t7-block_background w3-padding-tinysmall w3-upper-case'>" + str49 + "</h6>     <div class='w3-container list'>" + preview.sub_hobbi + "     </div> </div>";
            preview.hobbi8 = "<div class='w3-no-break w3-row w3-border-bottom'>     <h6 style='color:#" + preview.tit_color + ";' class='t8-block_background w3-padding-tinysmall w3-upper-case'>" + str49 + "</h6>     <div class='w3-container list'>" + preview.sub_hobbi + "     </div> </div>";
            preview.hobbi9 = "<div class='w3-no-break w3-row'>     <h6 style='color:#" + preview.tit_color + ";border-bottom: 1px solid #" + preview.tit_color + "!important;border-top: 1px solid #" + preview.tit_color + "!important;' class='t9-block_background w3-padding-tinysmall w3-upper-case'>" + str49 + "</h6>     <div class='w3-container list'>" + preview.sub_hobbi + "     </div> </div>";
        }
        rawQuery32.close();
        Cursor rawQuery34 = preview.DB.rawQuery("select * from reference_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
        if (rawQuery34.getCount() > 0) {
            rawQuery34.moveToFirst();
            String str51 = "";
            Cursor rawQuery35 = preview.DB.rawQuery("Select reference_title from title_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "'", null);
            rawQuery35.moveToFirst();
            if (rawQuery35.getCount() > 0 && (string = rawQuery35.getString(rawQuery35.getColumnIndex("reference_title"))) != null) {
                str51 = string;
            }
            rawQuery35.close();
            String str52 = "";
            String str53 = "";
            String str54 = "";
            int i20 = 0;
            while (i20 < rawQuery34.getCount()) {
                int i21 = rawQuery34.getInt(rawQuery34.getColumnIndex("reference_id"));
                String string46 = rawQuery34.getString(rawQuery34.getColumnIndex(AppMeasurementSdk.ConditionalUserProperty.NAME));
                String string47 = rawQuery34.getString(rawQuery34.getColumnIndex("designation"));
                String string48 = rawQuery34.getString(rawQuery34.getColumnIndex("organization"));
                String string49 = rawQuery34.getString(rawQuery34.getColumnIndex("email"));
                String string50 = rawQuery34.getString(rawQuery34.getColumnIndex("phone"));
                Cursor rawQuery36 = preview.DB.rawQuery("Select * from extra_table where profile_id='" + preview.sp.getString(preview, "profile_id") + "' and table_name='reference_table' and table_id='" + i21 + "'", null);
                rawQuery36.moveToFirst();
                if (rawQuery36.getCount() > 0) {
                    str = "";
                    for (int i22 = 0; i22 < rawQuery36.getCount(); i22++) {
                        str = str + "<p class='w3-margin-top-0 w3-margin-bottom-0'><b>" + rawQuery36.getString(rawQuery36.getColumnIndex("title")) + " : </b>" + rawQuery36.getString(rawQuery36.getColumnIndex("value")) + "</p>";
                        rawQuery36.moveToNext();
                    }
                } else {
                    str = "";
                }
                rawQuery36.close();
                str54 = str54 + "<h5 class='w3-margin-bottom-0'><b>" + string46 + "</b></h5>            <p class='w3-margin-top-0 w3-margin-bottom-0'>" + string47 + "</p>            <p class='w3-margin-top-0 w3-margin-bottom-0'>" + string48 + "</p>            <p class='w3-margin-top-0 w3-margin-bottom-0'>" + string49 + "</p>            <p class='w3-margin-top-0 w3-margin-bottom-0'>" + string50 + "</p>" + str;
                str53 = str53 + "     <div class='w3-container'>         <h5><b>" + string46 + "</b></h5>         <h5>" + string47 + "</h5>         <h5>" + string48 + "</h5>         <h5>" + string49 + "</h5>         <h5>" + string50 + "</h5>" + str.replaceAll("<p class='w3-margin-top-0 w3-margin-bottom-0'>", "<h5>").replaceAll("</p>", "</h5>") + "     </div>     <hr>";
                str52 = str52 + "     <div class='w3-container w3-margin-top-large'>         <h5><b>" + string46 + "</b></h5>         <h5>" + string47 + "</h5>         <h5>" + string48 + "</h5>         <h5>" + string49 + "</h5>         <h5>" + string50 + "</h5>" + str.replaceAll("<p class='w3-margin-top-0 w3-margin-bottom-0'>", "<h5>").replaceAll("</p>", "</h5>") + "     </div>";
                rawQuery34.moveToNext();
                i20++;
                str51 = str51;
                preview = this;
            }
            this.reference = "    <div class='w3-no-break w3-row w3-container'>     <h6 style='color:#" + this.tit_color + ";' class='w3-quarter t4-left t3-headeing-color w3-upper-case'>" + str51 + " -</h6>     <div style='border-color:#" + this.tit_color + ";' class='w3-threequarter t4-right w3-container t4-border-bottom'>" + str54 + "</div>    </div>";
            StringBuilder sb35 = new StringBuilder();
            sb35.append(" <div class='w3-no-break w3-container w3-white w3-margin-bottom'>     <h2 class='w3-text-black w3-capitalize'><i style='color:#");
            sb35.append(this.bg_color);
            sb35.append(";' class='fa fa-handshake-o fa-fw w3-margin-right w3-xlarge w3-text-templete_color_1'></i>");
            sb35.append(str51);
            sb35.append("</h2>");
            sb35.append(str53);
            sb35.append(" </div>");
            this.reference2 = sb35.toString();
            this.reference3 = " <div class='w3-no-break w3-container'>     <h2 style='background-color:#" + this.tit_color + ";' class='w3-block_background w3-margin-top-large w3-capitalize'><i style='box-shadow:0px 0px 0px 15px #" + this.bg_color + "; background-color:#" + this.bg_color + ";' class='fa fa-handshake-o w3-xxlarge w3-circle w3-color-shadow'></i>" + str51 + "</h2>" + str52 + " </div>";
            StringBuilder sb36 = new StringBuilder();
            sb36.append("<div style='border-color:#");
            sb36.append(this.tit_color);
            sb36.append(";' class='w3-no-break w3-row w3-container t3-border-bottom'>   <h4 style='color:#");
            sb36.append(this.tit_color);
            sb36.append(";' class='w3-quarter t3-left t3-headeing-color w3-upper-case'>");
            sb36.append(str51);
            sb36.append("</h4>   <div class='w3-threequarter t3-right w3-container'>");
            sb36.append(str54);
            sb36.append("   </div> </div>");
            this.reference4 = sb36.toString();
            this.reference5 = "<div class='w3-no-break w3-row w3-container'>     <h6 class='w3-quarter t5-left w3-upper-case'>" + str51 + "</h6>     <div class='w3-threequarter w3-container t5-right'>" + str54 + "     </div> </div>";
            this.reference6 = "<div class='w3-no-break w3-row w3-border-bottom w3-container'>     <h6 class='w3-quarter t6-left w3-upper-case'>" + str51 + "</h6>     <div class='w3-threequarter w3-container'>" + str54 + "     </div> </div>";
            this.reference7 = "<div class='w3-no-break w3-row'>     <h6 style='background-color:#" + this.tit_color + ";' class='t7-block_background w3-padding-tinysmall w3-upper-case'>" + str51 + "</h6>     <div class='w3-container list'>" + str54 + "     </div> </div>";
            this.reference8 = "<div class='w3-no-break w3-row w3-border-bottom'>     <h6 style='color:#" + this.tit_color + ";' class='t8-block_background w3-padding-tinysmall w3-upper-case'>" + str51 + "</h6>     <div class='w3-container list'>" + str54 + "     </div> </div>";
            this.reference9 = "<div class='w3-no-break w3-row'>     <h6 style='color:#" + this.tit_color + ";border-bottom: 1px solid #" + this.tit_color + "!important;border-top: 1px solid #" + this.tit_color + "!important;' class='t9-block_background w3-padding-tinysmall w3-upper-case'>" + str51 + "</h6>     <div class='w3-container list'>" + str54 + "     </div> </div>";
        }
        rawQuery34.close();
    }

    public String resueme() {
        return "<html><title>NithrA - Resume Template 2</title><meta charset='UTF-8'><link rel='stylesheet' href='css/4.css'><link rel='stylesheet' href='css/font-awesome.min.css'><style>@page { size: A4 portrait;margin: 5mm;}/*html,body,h1,h2,h3,h4,h5,h6 {font-family: 'twcent', sans-serif}*/</style><body class='w3-templete_background'> <div class='w3-content' style='max-width:1400px;'>" + this.cover_letter + "  <div class='w3-row'>   <div class='w3-no-break t4-margin-top-xxlarge w3-row w3-container'>" + this.image + this.personal + this.objective + this.education + this.experience + this.project + this.skill + this.intrest + this.indrust + this.inplant + this.achive + this.cocuri + this.extra + this.strength + this.hobbi + this.info + this.reference + this.declar + "     <div class='w3-row'>       <h6>           <table>               <tr><td width='7%'>Date</td><td width='1%'> : </td><td width='84%'>" + this.date + "</td><td>Signature,</td></tr>               <tr><td width='7%'>Place</td><td  width='1%'> : </td><td width='80%'>" + this.place + "</td>" + this.sign2 + "</tr>                         </table>       </h6>" + this.sign_btm_name1 + "         </table>      </h6>     </div>    </div>   </div>  </div> </body> </html>";
    }

    public String resueme1() {
        return "<!DOCTYPE html><html><title>NithrA - Resume Template 1</title><meta charset='UTF-8'><link rel='stylesheet' href='css/1.css'><link rel='stylesheet' href='css/font-awesome.min.css'><style>@page { size: A4 portrait;margin: 5mm;}</style><body class='w3-light-grey'>    <div class='w3-content' style='max-width:1400px;'>" + this.cover_letter + "        <div style='background-color:#" + this.bg_color + ";' class='w3-row w3-templete_color_1'>            <div class='w3-third'>" + this.image2 + "                <div class='w3-container'>                    <h2 id='name' class='w3-upper-case w3-xlarge w3-center border-bottom-white'>" + this.name + "</h2>                    <p style='color:#" + this.tit_color + ";' class='w3-center w3-templete_color_3 w3-margin-bottom-0 w3-capitalize'><b>CONTACT NUMBER</b></p>                    <p class='w3-center w3-text-white w3-large w3-margin-top-0'>" + this.phoneno + "</p>                    <p style='color:#" + this.tit_color + ";' class='w3-center w3-templete_color_3 w3-margin-bottom-0 w3-capitalize'><b>EMAIL</b></p>                    <p style='color:#" + this.tit_color + ";' class='w3-center w3-text-white w3-large w3-margin-top-0'>" + this.email + "</p>                    <p style='color:#" + this.tit_color + ";' class='w3-center w3-templete_color_3 w3-margin-bottom-0 w3-capitalize'><b>CONTACT ADDRESS</b></p>" + this.address.replaceAll("<p", "<p class='w3-center w3-text-white w3-large w3-margin-top-0'") + this.skill2 + this.intrest2 + this.strength2 + this.hobbi2 + this.info2 + "                </div><br>            </div>            <div style='background-color:#" + this.bg_color + ";' class='w3-twothird w3-templete_color_1'>                <div class='main-content w3-white'>" + this.objective2 + this.education2 + this.experience2 + this.project2 + this.indrust2 + this.inplant2 + this.achive2 + this.cocuri2 + this.extra2 + this.reference2 + "                    <div class='w3-no-break w3-container w3-white w3-margin-bottom'>" + this.declar2 + "                        <div class='w3-container'>                            <br>                                <h6>                                    <table>                                        <tr><td width='7%'>Date</td><td width='1%'> : </td><td width='83.5%'>" + this.date + "</td><td>Signature,</td></tr>                                        <tr><td width='7%'>Place</td><td  width='1%'> : </td><td width='80%'>" + this.place + "</td>" + this.sign2 + "</tr>                                                                           </table>                                </h6>" + this.sign_btm_name1 + "                             <hr>                    </div>                </div>            </div>        </div>  </div></body></html>";
    }

    public String resueme2() {
        return "<!DOCTYPE html><html>    <title>NithrA - Resume Template 2</title>    <meta charset='UTF-8'>    <link rel='stylesheet' href='css/2.css'>    <link rel='stylesheet' href='css/font-awesome.min.css'>    <style>        @page { size: A4 portrait;margin: 5mm;}        html,body,h1,h2,h3,h4,h5,h6 {font-family: 'twcent', sans-serif}    </style>    <body class='w3-templete_background'>" + this.cover_letter3 + "        <div class='w3-content' style='max-width:1400px;'>            <div class='w3-margin-top-large'>                           <div class='w3-row w3-block_background'>                                    <div class='w3-twothird ht-300'>                        <h1 class='w3-upper-case t2-profile-name w3-margin-bottom-0'>" + this.name + "</h1>" + this.info3 + "                    </div>                    <div class='w3-third img-div ht-300'>                        <div class='w3-display-container w3-pad-top-50'>" + this.image3 + "                        </div>                       </div>                </div>            </div>            <div class='w3-row color-div'>                <div class='w3-twothird   first-div'>" + this.objective3 + this.education3 + this.experience3 + this.project3 + this.indrust3 + this.inplant3 + this.achive3 + this.cocuri3 + this.extra3 + this.reference3 + "                    <div class='w3-no-break w3-container'>                        <div class='w3-container'>" + this.declar3 + "                    <h6>                        <table>                            <tr><td width='7%'><b>Date</b></td><td width='1%'> : </td><td width='84.5%'><b>" + this.date + "</b></td><td><b>Signature,</b></td></tr>                            <tr><td width='7%'><b>Place</b></td><td  width='1%'> : </td><td width='82%'><b>" + this.place + "</b></td>" + this.sign2 + "</tr>                        </table>                    </h6>" + this.sign_btm_name1 + "                        </div>                    </div>                </div>                <div class='w3-third  w3-templete_color_2'>" + this.skill3 + this.intrest3 + this.strength3 + this.hobbi3 + this.profile3 + "                    <div style='content: '';height: 100vh'></div>                </div>            </div>        </div>    </body></html>";
    }

    public String resueme3() {
        return "<!DOCTYPE html><html><title>NithrA - Resume Template 3</title><meta charset='UTF-8'><link rel='stylesheet' href='css/3.css'><link rel='stylesheet' href='css/font-awesome.min.css'><style>@page { size: A4 portrait;margin: 5mm;}</style><body class='w3-templete_background'>  <div class='w3-content' style='max-width:1400px;'>" + this.cover_letter + "    <div style='border-color:#" + this.tit_color + ";' class='w3-row t3-block1 t3-border-bottom'>      <div style='background-color:#" + this.tit_color + ";' class='w3-row w3-display-container t3-block2 t3-block_background w3-padding-24'>" + this.image4 + "        <div class='w3-threequarter t3-profile2'>          <h2 class='w3-upper-case t3-profile-name'>" + this.name + "</h2>          <h6 class='t3-profile-address'>" + this.address.replaceAll("<p dir='ltr'>", "").replaceAll("<br>", "") + "</h5>            <h6 class='t3-profile-detail'>" + this.phoneno + " | " + this.email + "</h5>        </div>      </div>      <div class='w3-display-container'>        <div class='t3-profile2'>            </div>          </div>        </div>        <div class='w3-row'>" + this.objective4 + this.education4 + this.experience4 + this.project4 + this.skill4 + this.intrest4 + this.indrust4 + this.inplant4 + this.achive4 + this.cocuri4 + this.extra4 + this.strength4 + this.hobbi4 + this.info4 + this.reference4 + "          <div class='w3-no-break w3-row w3-container'>" + this.declar4 + "            <div class='w3-row'>              <h6>                <table>                    <tr><td width='7%'>Date</td><td width='1%'> : </td><td width='84.5%'>" + this.date + "</td><td>Signature,</td></tr>                    <tr><td width='7%'>Place</td><td  width='1%'> : </td><td width='82%'>" + this.place + "</td>" + this.sign2 + "</tr>                </table>              </h6>" + this.sign_btm_name1 + "                </div>          </div>        </div>      </div>    </body>    </html>";
    }

    public String resueme4() {
        return "<!DOCTYPE html><html><title>NithrA - Resume Template 2</title><meta charset='UTF-8'><link rel='stylesheet' href='css/5.css'><link rel='stylesheet' href='css/font-awesome.min.css'><style>@page { size: A4 portrait;margin: 7.5mm 2.5mm 7.5mm 7.5mm;cover-letter {page-break-after: always;}}</style><body class='w3-templete_background'>    <div class='w3-content' style='max-width:1400px;'>" + this.cover_letter + "        <div class='w3-row t5-margin-top-xxlarge '>            <div class='w3-no-break w3-row w3-container'>                <div class='w3-quarter'>                    <h2 class='w3-upper-case t5-profile-name margin-0'>" + this.name + "</h2>                    <h6>" + this.phoneno + "</h6>                    <h6>" + this.email + "</h6>                    <h6>" + this.address.replaceAll("<p dir=\"ltr\">", "").replaceAll("<br>", "") + "</h6>                </div>            </div>        </div>        <div style='background-color:#" + this.tit_color + ";' class='w3-row t5-background-color'>" + this.objective5 + this.education5 + this.experience5 + this.project5 + this.skill5 + this.intrest5 + this.indrust5 + this.inplant5 + this.achive5 + this.cocuri5 + this.extra5 + this.strength5 + this.hobbi5 + this.info5 + this.reference5 + this.declar5 + "        </div>        <div class='w3-row'>              <h6>                <table>                    <tr><td width='7%'>Date</td><td width='1%'> : </td><td width='85.8%'>" + this.date + "</td><td>Signature,</td></tr>                    <tr><td width='7%'>Place</td><td  width='1%'> : </td><td width='82%'>" + this.place + "</td>" + this.sign2 + "</tr>                </table>              </h6>" + this.sign_btm_name1 + "             </div>    </div></body></html>";
    }

    public String resueme5() {
        return "<!DOCTYPE html><html><title>NithrA - Resume Template 2</title><meta charset='UTF-8'><link rel='stylesheet' href='css/6.css'><link rel='stylesheet' href='css/font-awesome.min.css'><style>@page { size: A4 portrait;margin: 7.5mm 2.5mm 7.5mm 7.5mm;cover-letter {page-break-after: always;}}</style><body class='w3-templete_background'>    <div class='w3-content' style='max-width:1400px;'>" + this.cover_letter2 + "        <div style='background-color:#" + this.tit_color + ";' class='w3-row t6-background-color t6-header t6-margin-top-xxlarge '>            <div class='w3-no-break w3-row w3-container'>                <div class='w3-quarter'>                    <h2 class='w3-upper-case t6-profile-name margin-0'>" + this.name + "</h2>                    <h6>" + this.phoneno + "</h6>                    <h6>" + this.email + "</h6>                    <h6>" + this.address.replaceAll("<p dir=\"ltr\">", "").replaceAll("</p>", "").replaceAll("<br>", "") + "</h6>                </div>            </div>        </div>        <div class='w3-row'>" + this.objective6 + this.education6 + this.experience6 + this.project6 + this.skill6 + this.intrest6 + this.indrust6 + this.strength6 + this.inplant6 + this.achive6 + this.cocuri6 + this.extra6 + this.hobbi6 + this.info6 + this.reference6 + this.declar6 + "        </div>        <div class='w3-row'>              <h6>                <table>                    <tr><td width='7%'>Date</td><td width='1%'> : </td><td width='85.8%'>" + this.date + "</td><td style='text-align: left;'>Signature,</td></tr>                    <tr><td width='7%'>Place</td><td  width='1%'> : </td><td width='82%'>" + this.place + "</td>" + this.sign2 + "</tr>                </table>              </h6>" + this.sign_btm_name1 + "              </div>    </div></body></html>";
    }

    public String resueme6() {
        return "<!DOCTYPE html><html>    <title>NithrA - Resume Template 2</title>    <meta charset='UTF-8'>    <link rel='stylesheet' href='css/7.css'>    <link rel='stylesheet' href='css/font-awesome.min.css'>    <style>        @page { size: A4 portrait;margin: 7.5mm 2.5mm 7.5mm 7.5mm;cover-letter {page-break-after: always;}}    </style>    <body class='w3-templete_background'>        <div class='w3-content' style='max-width:1400px;'>" + this.cover_letter6 + "            <div style='background-color:#" + this.tit_color + ";' class='w3-row t7-background-color t7-header'>                <div  class='w3-no-break w3-row w3-container'>                    <div class='w3-center'>                        <h2 class='w3-upper-case t7-profile-name w3-show-inline-block margin-0'>" + this.name + "</h2>                        <h6 class='margin-0'>" + this.phoneno + " | " + this.email + "</h6>                        <h6 class='margin-0'>" + this.address.replaceAll("<p dir=\"ltr\">", "").replaceAll("</p>", "").replaceAll("<br>", "") + "</h6>                    </div>                </div>            </div>            <div class='w3-row'>" + this.objective7 + this.education7 + this.experience7 + this.project7 + this.skill7 + this.intrest7 + this.indrust7 + this.inplant7 + this.achive7 + this.cocuri7 + this.extra7 + this.strength7 + this.hobbi7 + this.info7 + this.reference7 + this.declar7 + "                <div class='w3-row'>                    <div class='w3-container'>              <h6>                <table>                    <tr><td width='7%'>Date</td><td width='1%'> : </td><td width='85.7%'>" + this.date + "</td><td>Signature,</td></tr>                    <tr><td width='7%'>Place</td><td  width='1%'> : </td><td width='82%'>" + this.place + "</td>" + this.sign2 + "</tr>                </table>              </h6>" + this.sign_btm_name1 + "                          </div>                </div>            </div>        </div>    </body></html>";
    }

    public String resueme7() {
        return "<!DOCTYPE html><html>    <title>NithrA - Resume Template 2</title>    <meta charset='UTF-8'>    <link rel='stylesheet' href='css/8.css'>    <link rel='stylesheet' href='css/font-awesome.min.css'>    <style>        @page { size: A4 portrait;margin: 7.5mm 2.5mm 7.5mm 7.5mm;cover-letter {page-break-after: always;}}    </style>    <body class='w3-templete_background'>        <div class='w3-content' style='max-width:1400px;'>" + this.cover_letter4 + "            <div style='background-color:#" + this.tit_color + ";' class='w3-row t8-background-color t8-header'>                <div class='w3-no-break w3-row w3-container'>                    <h2 class='w3-upper-case t8-profile-name w3-show-inline-block margin-0'>" + this.name + "</h2>                    <h6 class='margin-0'>" + this.phoneno + " | " + this.email + "</h6>                    <h6 class='margin-0'>" + this.address.replaceAll("<p dir=\"ltr\">", "").replaceAll("</p>", "").replaceAll("<br>", "") + "</h6>                </div>            </div>            <div class='w3-row'>" + this.objective8 + this.education8 + this.experience8 + this.project8 + this.skill8 + this.intrest8 + this.indrust8 + this.inplant8 + this.achive8 + this.cocuri8 + this.extra8 + this.strength8 + this.hobbi8 + this.info8 + this.reference8 + this.declar8 + "                <div class='w3-row'>                    <div class='w3-container'>              <h6>                <table>                    <tr><td width='7%'>Date</td><td width='1%'> : </td><td width='85.7%'>" + this.date + "</td><td>Signature,</td></tr>                    <tr><td width='7%'>Place</td><td  width='1%'> : </td><td width='82%'>" + this.place + "</td>" + this.sign2 + "</tr>                </table>              </h6>" + this.sign_btm_name1 + "                          </div>                </div>            </div>        </div>    </body></html>";
    }

    public String resueme8() {
        return "<!DOCTYPE html><html>    <title>NithrA - Resume Template 2</title>    <meta charset='UTF-8'>    <link rel='stylesheet' href='css/9.css'>    <link rel='stylesheet' href='css/font-awesome.min.css'>    <style>        @page { size: A4 portrait;margin: 7.5mm 2.5mm 7.5mm 7.5mm;cover-letter {page-break-after: always;}}    </style>    <body class='w3-templete_background'>        <div class='w3-content' style='max-width:1400px;'>" + this.cover_letter5 + "            <div class='w3-row t9-background-color t9-header'>                <div style='color:#" + this.tit_color + "' class='w3-no-break w3-row w3-container'>                    <div class='w3-center'>                        <h2 class='w3-upper-case t9-profile-name w3-show-inline-block margin-0'>" + this.name + "</h2>                        <h6 class='margin-0'>" + this.phoneno + " | " + this.email + "</h6>                        <h6 class='margin-0'>" + this.address.replaceAll("<p dir=\"ltr\">", "").replaceAll("</p>", "").replaceAll("<br>", "") + "</h6>                    </div>                </div>            </div>            <div class='w3-row'>" + this.objective9 + this.education9 + this.experience9 + this.project9 + this.skill9 + this.intrest9 + this.indrust9 + this.inplant9 + this.achive9 + this.cocuri9 + this.extra9 + this.strength9 + this.hobbi9 + this.info9 + this.reference9 + this.declar9 + "                <div class='w3-row'>                    <div class='w3-container'>              <h6>                <table>                    <tr><td width='7%'>Date</td><td width='1%'> : </td><td width='85.7%'>" + this.date + "</td><td>Signature,</td></tr>                    <tr><td width='7%'>Place</td><td  width='1%'> : </td><td width='75%'>" + this.place + "</td>" + this.sign2 + "</tr>                </table>              </h6>" + this.sign_btm_name1 + "                         </div>                </div>            </div>        </div>    </body></html>";
    }

    public void createPdfFile() {
        try {
            File createEmptyFile = createEmptyFile();
            new PdfWriter(getApplicationContext(), this.mWebView.createPrintDocumentAdapter(), createEmptyFile).write(new PdfWriter.PdfWriterCallback() {
                /* class nithra.resume.maker.cv.builder.app.Formates.Preview.AnonymousClass6 */

                @Override // nithra.resume.maker.cv.builder.app.PdfWriter.PdfWriterCallback
                public void onWriteFinished() {
                    Preview.this.wait_dialog.dismiss();
                    Toast.makeText(Preview.this.getApplicationContext(), "Resume generated successfully", 0).show();
                    Uri fromFile = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Nithra/ResumeBuilder/" + Preview.this.fileName));
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setFlags(67108864);
                    intent.setDataAndType(fromFile, "application/pdf");
                    try {
                        Preview.this.startActivity(intent);
                    } catch (ActivityNotFoundException unused) {
                        Utils.toast_center(Preview.this, "No Application available to view PDF");
                    }
                }

                @Override // nithra.resume.maker.cv.builder.app.PdfWriter.PdfWriterCallback
                public void onWriteFailed() {
                    System.out.println("dir==   onWriteFailed");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            PrintStream printStream = System.out;
            printStream.println("dir==" + e);
        }
    }

    private File createEmptyFile() throws IOException {
        this.fileName = this.sp.getString(this, "profile_name") + "_" + this.titles.get(this.sp.getInt(this, "key")) + ".pdf";
        File externalBreezyDirectory = getExternalBreezyDirectory();
        PrintStream printStream = System.out;
        printStream.println("dir==" + externalBreezyDirectory);
        File file = new File(externalBreezyDirectory, this.fileName);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }

    public String month_year(String str) {
        try {
            Date parse = new SimpleDateFormat("dd - MM - yyyy").parse(str);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM yyyy");
            PrintStream printStream = System.out;
            printStream.println("Format Date : " + simpleDateFormat.format(parse));
            return simpleDateFormat.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String month_date(String str) {
        try {
            Date parse = new SimpleDateFormat("dd - MM - yyyy").parse(str);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy");
            PrintStream printStream = System.out;
            printStream.println("Format Date : " + simpleDateFormat.format(parse));
            return simpleDateFormat.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void PermissionFun() {
        if (Build.VERSION.SDK_INT < 23) {
            this.sp.putInt(this, "permissiond", 1);
        } else if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            final Dialog dialog = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar);
            dialog.setContentView(R.layout.permission_dialog_layout);
            dialog.setCancelable(false);
            TextView textView = (TextView) dialog.findViewById(R.id.permission_ok);
            TextView textView2 = (TextView) dialog.findViewById(R.id.txt);
            if (this.sp.getInt(this, "permissiond") == 2) {
                textView2.setText("To add photo or signature please enable Storage Permission in app Settings");
            } else {
                textView2.setText("You need to allow the Storage Permission to add photo or signature in your Resume");
            }
            textView.setOnClickListener(new View.OnClickListener() {
                /* class nithra.resume.maker.cv.builder.app.Formates.Preview.AnonymousClass7 */

                @SuppressLint({"NewApi"})
                public void onClick(View view) {
                    if (Preview.this.sp.getInt(Preview.this, "permissiond") == 2) {
                        Intent intent = new Intent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.setData(Uri.fromParts("package", Preview.this.getPackageName(), null));
                        Preview.this.startActivity(intent);
                        dialog.dismiss();
                        return;
                    }
                    Preview.this.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 151);
                    dialog.dismiss();
                }
            });
            dialog.show();
        } else {
            this.sp.putInt(this, "permissiond", 1);
        }
    }

    @Override // android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback, android.support.v4.app.FragmentActivity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 151) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        } else if (iArr.length > 0 && iArr[0] == 0) {
            this.sp.putInt(this, "permissiond", 1);
        } else if (iArr[0] != -1) {
        } else {
            if (!shouldShowRequestPermissionRationale(strArr[0])) {
                this.sp.putInt(this, "permissiond", 2);
            } else if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(strArr[0])) {
                this.sp.putInt(this, "permissiond", 0);
            }
        }
    }


    public void openDialog(boolean z) {
        new AmbilWarnaDialog(this, this.color, z, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            /* class nithra.resume.maker.cv.builder.app.Formates.Preview.AnonymousClass8 */

            @Override // nithra.resume.maker.cv.builder.app.color.AmbilWarnaDialog.OnAmbilWarnaListener
            public void onOk(AmbilWarnaDialog ambilWarnaDialog, int i) {
                Toast.makeText(Preview.this, "ok", 0).show();
                Preview preview = Preview.this;
                preview.color = i;
                preview.displayColor();
                if (Preview.this.color_dialog.isShowing()) {
                    Preview.this.color_dialog.dismiss();
                }
            }

            @Override // nithra.resume.maker.cv.builder.app.color.AmbilWarnaDialog.OnAmbilWarnaListener
            public void onCancel(AmbilWarnaDialog ambilWarnaDialog) {
                Toast.makeText(Preview.this, "cancel", 0).show();
            }
        }).show();
    }


    public void displayColor() {
        String str;
        if (this.sp.getString(this, "type").equals("text")) {
            this.tit_color = String.format("%x", Integer.valueOf(this.color));
            this.tit_color = this.tit_color.substring(2);
        } else {
            this.bg_color = String.format("%x", Integer.valueOf(this.color));
            this.bg_color = this.bg_color.substring(2);
        }
        first_load();
        if (this.sp.getInt(this, "key") == 0) {
            str = resueme();
        } else if (this.sp.getInt(this, "key") == 1) {
            str = resueme1();
        } else if (this.sp.getInt(this, "key") == 2) {
            str = resueme2();
        } else if (this.sp.getInt(this, "key") == 3) {
            str = resueme3();
        } else if (this.sp.getInt(this, "key") == 4) {
            str = resueme4();
        } else if (this.sp.getInt(this, "key") == 5) {
            str = resueme5();
        } else if (this.sp.getInt(this, "key") == 6) {
            str = resueme6();
        } else if (this.sp.getInt(this, "key") == 7) {
            str = resueme7();
        } else if (this.sp.getInt(this, "key") == 8) {
            str = resueme8();
        } else {
            str = "";
        }
        this.mWebView.loadDataWithBaseURL("file:///android_asset/", str, "text/html", "utf-8", null);
    }

    @Override // android.support.v4.app.FragmentActivity
    public void onResume() {
        MainActivity.load_addFromMain(this, this.ads_lay);
        if (new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + "/Nithra/ResumeBuilder/" + this.sp.getString(this, "profile_name") + "_" + this.titles.get(this.sp.getInt(this, "key")) + ".pdf").exists()) {
            this.generat_txt.setText("Regenerate");
        } else {
            this.generat_txt.setText("Generate");
        }
        super.onResume();
    }

    private void Showcase() {
        ShowcaseConfig showcaseConfig = new ShowcaseConfig();
        showcaseConfig.setDelay(500);
        MaterialShowcaseSequence materialShowcaseSequence = new MaterialShowcaseSequence(this, SHOWCASE_ID);
        materialShowcaseSequence.setConfig(showcaseConfig);
        materialShowcaseSequence.addSequenceItem(new MaterialShowcaseView.Builder(this).setTarget(this.save).setDismissText("OK").setContentText("Click here to Generate your Resume").withRectangleShape().setDismissTextColor(getResources().getColor(R.color.red)).setMaskColour(R.color.colorAccent).setDismissStyle(Typeface.createFromAsset(getAssets(), "Chunkfive.otf")).build());
        materialShowcaseSequence.start();
    }
}
