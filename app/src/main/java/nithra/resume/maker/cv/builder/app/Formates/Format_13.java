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
import com.google.dexmaker.dx.io.Opcodes;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
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

public class Format_13 extends AppCompatActivity {
    static String SHOWCASE_ID = "PREVIEW";
    SQLiteDatabase DB;
    String achive;
    String address;
    LinearLayout ads_lay;
    String bg_color;
    String cocuri;
    int color;
    Dialog color_dialog;
    String cover_letter;
    String curricular;
    String date;
    String declar;
    String designation;
    String education;
    String email;
    String experience;
    String explore;
    String extra;
    String fileName;
    TextView generat_txt;
    String hobbi;
    String image;
    String indrust;
    String info;
    String inplant;
    String intrest;
    WebView mWebView;
    String name;
    String objective;
    String personal;
    String phoneno;
    ImageView picker;
    String place;
    String pro_table;
    String project;
    String reference;
    CardView save;
    String sign;
    String sign_btm_name;
    String sign_name;
    String skill;
    SharedPreference sp = new SharedPreference();
    String strength;
    String sub_achive;
    String sub_cocuri;
    String sub_edu;
    String sub_exp;
    String sub_extra;
    String sub_hobbi;
    String sub_indust;
    String sub_inplant;
    String sub_intrest;
    String sub_refer;
    String sub_skill;
    String sub_strength;
    String sub_work;
    String tit_color;
    String title;
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
        ((AppCompatTextView) findViewById(R.id.title_name)).setText("Experienced Resume");
        this.DB = openOrCreateDatabase("RESUME_BUILDER", 0, null);
        this.mWebView.getSettings().setAllowFileAccess(true);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setSupportZoom(true);
        this.mWebView.getSettings().setBuiltInZoomControls(true);
        this.mWebView.getSettings().setDisplayZoomControls(true);
        this.mWebView.getSettings().setLoadWithOverviewMode(true);
        this.mWebView.getSettings().setUseWideViewPort(true);
        this.mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Formates.Format_13.AnonymousClass1 */

            public boolean onLongClick(View view) {
                return true;
            }
        });
        this.color = R.color.colorPrimary;
        Showcase();
        first_load();
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        if (this.sp.getInt(this, "key") == 12) {
            str = resueme();
        } else {
            str = "";
        }
        this.mWebView.loadDataWithBaseURL("file:///android_asset/", str, "text/html", "utf-8", null);
        this.save.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Formates.Format_13.AnonymousClass2 */

            public void onClick(View view) {
                if (Format_13.this.sp.getInt(Format_13.this, "permissiond") == 1) {
                    try {
                        File externalStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                        new File(externalStorageDirectory.getAbsolutePath() + "/Nithra/ResumeBuilder/").mkdirs();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Format_13.this.createPdfFile();
                    Format_13.this.wait_dialog.show();
                    return;
                }
                Format_13.this.PermissionFun();
            }
        });
        this.picker.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Formates.Format_13.AnonymousClass3 */

            public void onClick(View view) {
                if (Format_13.this.sp.getInt(Format_13.this, "key") == 0) {
                    Format_13.this.sp.putString(Format_13.this, "type", "text");
                    Format_13.this.openDialog(false);
                } else if (Format_13.this.sp.getInt(Format_13.this, "key") == 1) {
                    Format_13.this.color_dialog.show();
                } else if (Format_13.this.sp.getInt(Format_13.this, "key") == 2) {
                    Format_13.this.color_dialog.show();
                } else if (Format_13.this.sp.getInt(Format_13.this, "key") == 3) {
                    Format_13.this.sp.putString(Format_13.this, "type", "text");
                    Format_13.this.openDialog(false);
                } else if (Format_13.this.sp.getInt(Format_13.this, "key") == 4) {
                    Format_13.this.sp.putString(Format_13.this, "type", "text");
                    Format_13.this.openDialog(false);
                } else if (Format_13.this.sp.getInt(Format_13.this, "key") == 5) {
                    Format_13.this.sp.putString(Format_13.this, "type", "text");
                    Format_13.this.openDialog(false);
                } else if (Format_13.this.sp.getInt(Format_13.this, "key") == 6) {
                    Format_13.this.sp.putString(Format_13.this, "type", "text");
                    Format_13.this.openDialog(false);
                } else if (Format_13.this.sp.getInt(Format_13.this, "key") == 7) {
                    Format_13.this.sp.putString(Format_13.this, "type", "text");
                    Format_13.this.openDialog(false);
                } else if (Format_13.this.sp.getInt(Format_13.this, "key") == 8) {
                    Format_13.this.sp.putString(Format_13.this, "type", "text");
                    Format_13.this.openDialog(false);
                }
            }
        });
        this.color_dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        this.color_dialog.setContentView(R.layout.dialog_color_view);
        ((LinearLayout) this.color_dialog.findViewById(R.id.text_lay)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Formates.Format_13.AnonymousClass4 */

            public void onClick(View view) {
                Format_13.this.sp.putString(Format_13.this, "type", "text");
                Format_13.this.openDialog(false);
            }
        });
        ((LinearLayout) this.color_dialog.findViewById(R.id.border_lay)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Formates.Format_13.AnonymousClass5 */

            public void onClick(View view) {
                Format_13.this.sp.putString(Format_13.this, "type", "bg");
                Format_13.this.openDialog(false);
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
        String string12;
        String string13;
        String string14;
        this.name = "";
        this.sign_name = "";
        this.sign_btm_name = "";
        this.image = "";
        this.sign = "";
        this.email = "";
        this.phoneno = "";
        this.address = "";
        this.cover_letter = "";
        this.title = "";
        this.personal = "";
        this.pro_table = "";
        this.objective = "";
        this.experience = "";
        this.sub_exp = "";
        this.project = "";
        this.sub_work = "";
        this.education = "";
        this.sub_edu = "";
        this.skill = "";
        this.sub_skill = "";
        this.strength = "";
        this.sub_strength = "";
        this.intrest = "";
        this.sub_intrest = "";
        this.indrust = "";
        this.sub_indust = "";
        this.inplant = "";
        this.sub_inplant = "";
        this.achive = "";
        this.sub_achive = "";
        this.cocuri = "";
        this.sub_cocuri = "";
        this.extra = "";
        this.sub_extra = "";
        this.hobbi = "";
        this.sub_hobbi = "";
        this.declar = "";
        this.date = "";
        this.place = "";
        this.info = "";
        this.explore = "";
        this.curricular = "";
        this.reference = "";
        this.sub_refer = "";
        this.designation = "";
        Cursor rawQuery = this.DB.rawQuery("select * from profile_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            String string15 = rawQuery.getString(rawQuery.getColumnIndex("photo"));
            Log.e("0000901", rawQuery.getString(rawQuery.getColumnIndex("photo")));
            String string16 = rawQuery.getString(rawQuery.getColumnIndex("sign"));
            if (string15.equals("") || string15.equals("null")) {
                this.image = "<img class='w3-profile-image' src='" + "man.png" + "' id='image' alt='Avatar'>";
            } else if (this.sp.getBoolean(this, "photo_check").booleanValue()) {
                this.image = "<img class='w3-profile-image' src='" + string15 + "' id='image' alt='Avatar'>";
            } else {
                this.image = "<img class='w3-profile-image' src='" + "man.png" + "' id='image' alt='Avatar'>";
            }
            if (string16.equals("null") || string16.equals("")) {
                this.sign = "<td colspan='2'>  </td>";
            } else if (this.sp.getBoolean(this, "sign_check").booleanValue()) {
                this.sign = "<td class='t3-signature' colspan='2'><img src='" + string16 + "'></td>";
            } else {
                this.sign = "<td colspan='2'>  </td>";
            }
        } else {
            Log.e("0000901", "man.png");
            this.image = "<div class='w3-quarter t3-profile1 w3-padding'>    <img class='t3-profile-smallimage' src='" + "man.png" + "' id='image' alt='Avatar'></div>";
        }
        rawQuery.close();
        Cursor rawQuery2 = this.DB.rawQuery("select * from personal_info where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery2.getCount() > 0) {
            rawQuery2.moveToFirst();
            this.name = rawQuery2.getString(rawQuery2.getColumnIndex(AppMeasurementSdk.ConditionalUserProperty.NAME));
            this.email = rawQuery2.getString(rawQuery2.getColumnIndex("email"));
            this.phoneno = rawQuery2.getString(rawQuery2.getColumnIndex("phone"));
            this.address = rawQuery2.getString(rawQuery2.getColumnIndex("address"));
            Cursor rawQuery3 = this.DB.rawQuery("Select personal_title from title_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
            rawQuery3.moveToFirst();
            if (rawQuery3.getCount() > 0 && (string14 = rawQuery3.getString(rawQuery3.getColumnIndex("personal_title"))) != null) {
                this.title = string14;
            }
            rawQuery3.close();
            Log.e("addressss", this.address);
            String str = "";
            String str2 = "";
            String str3 = "";
            if (!this.phoneno.equals("")) {
                str = "<td><i class='fa fa-mobile fa-fw w3-border'> </i></td><td>" + this.phoneno + "</td>";
            }
            if (!this.email.equals("")) {
                str2 = "<td><i class='fa fa-envelope fa-fw w3-border'> </i></td><td>" + this.email + "</td>";
            }
            if (!this.address.equals("")) {
                str3 = "<tr><td><i class='fa fa-map-marker fa-fw w3-border'> </i></td><td colspan=3>" + this.address + "</td></tr>";
            }
            Cursor rawQuery4 = this.DB.rawQuery("select *from extra_table where profile_id='" + this.sp.getString(this, "profile_id") + "' and table_name='personal_info'", null);
            if (rawQuery4.getCount() > 0) {
                rawQuery4.moveToFirst();
                for (int i = 0; i < rawQuery4.getCount(); i++) {
                    this.pro_table += "<p class='w3-margin-top-0 w3-margin-bottom-0'><b>" + rawQuery4.getString(rawQuery4.getColumnIndex("title")) + " : </b>" + rawQuery4.getString(rawQuery4.getColumnIndex("value")) + "</p>";
                    rawQuery4.moveToNext();
                }
                this.info = "<div class='w3-no-break w3-container w3-white'>        <h2 class='w3-padding-top w3-capitalize w3-text-templete_color_1'><i class='fa fa-user-circle-o w3-fa w3-margin-right w3-xlarge w3-text-templete_color_1'></i>" + this.title + "</h2>        <div class='w3-container w3-padding-left-50'>" + this.pro_table + "        </div>    </div>";
            }
            rawQuery4.close();
            String str4 = "<table><tr>" + str + str2 + "</tr>" + str3 + "</table>";
            this.personal = "<div class='w3-no-break w3-padding-left-20'>      <p class='w3-large '><i class='fa fa-envelope fa-fw'></i> " + this.email + "</p>                                                     <p class='w3-large'><i class='fa fa-phone fa-fw'></i> " + this.phoneno + "</p>                                                     <p class='w3-large'><i class='fa fa-map-marker fa-fw'></i>" + this.address.replaceAll("<p dir=\"ltr\">", "").replaceAll("</p>", "").replaceAll("<br>", "") + " </p>                                                 </div>";
        }
        rawQuery2.close();
        if (this.sp.getBoolean(this, "sign_name_check").booleanValue()) {
            this.sign_name = "<tr><td width='87%'></td><td class='w3-center'><h6>(" + this.name + ")</h6></td></tr>";
            this.sign_btm_name = "<h6 style='text-align:right;width: 92%;'>( " + this.name + ")</h6>";
        } else {
            this.sign_name = "";
            this.sign_btm_name = "";
        }
        Cursor rawQuery5 = this.DB.rawQuery("select * from cover_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery5.getCount() > 0) {
            rawQuery5.moveToFirst();
            String string17 = rawQuery5.getString(rawQuery5.getColumnIndex("date"));
            String string18 = rawQuery5.getString(rawQuery5.getColumnIndex("address"));
            String string19 = rawQuery5.getString(rawQuery5.getColumnIndex("body"));
            if (this.sp.getBoolean(this, "cover_check").booleanValue()) {
                this.cover_letter = "<div class='w3-row cover-letter-full back-white'>           <img style='background-color:#" + this.tit_color + ";' class='top-img t5-background-color' src='w3images/top.png'>           <div class='w3-row w3-container w3-cover-letter'>               <div class='w3-col w3-container w3-cover-letter-from'>                   <h2 class='w3-upper-case t5-profile-name margin-0'>" + this.name + "</h2>                   <h4 class='w3-upper-case t5-profile-role margin-0'>" + this.designation + "</h4>                   <h6><span>" + this.phoneno + " | </span><span>" + this.email + "</span><br><span>" + this.address.replaceAll("<p dir='ltr'>", "").replaceAll("</p>", "").replaceAll("<br>", "") + "</span></h6>               </div>               <div class='w3-col w3-container w3-cover-letter-to'>" + string18 + "               </div>               <div class='w3-col w3-container w3-cover-letter-date'>                   <h6 class='w3-upper-case margin-0'>" + string17 + "</h6>               </div>               <div class='w3-col w3-container w3-cover-letter-content'>" + string19 + "                   <div class='w3-row'>                       <h6>                           <table>                               <tr><td width='95%'></td><td</td><td>Your's Sincerely,</td></tr>                               <tr><td width='87%'></td>" + this.sign + "</tr>" + this.sign_name + "                           </table>                       </h6>                   </div>               </div>           </div>           <img style='background-color:#" + this.tit_color + ";' class='bottom-img w3-right t5-background-color' src='w3images/bottom.png'>       </div>";
            } else {
                this.cover_letter = "";
            }
        }
        this.name = "<h2 id='name' class='w3-capitalize w3-center'>" + this.name + "</h2>";
        Cursor rawQuery6 = this.DB.rawQuery("select * from objective_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery6.getCount() > 0) {
            rawQuery6.moveToFirst();
            String string20 = rawQuery6.getString(rawQuery6.getColumnIndex("objective"));
            String string21 = rawQuery6.getString(rawQuery6.getColumnIndex("declaration"));
            this.date = rawQuery6.getString(rawQuery6.getColumnIndex("date"));
            this.place = rawQuery6.getString(rawQuery6.getColumnIndex("place"));
            if (!string20.equals("")) {
                this.objective = "<div class='w3-no-break w3-container w3-white'>        <h2 class='w3-padding-top w3-capitalize w3-text-templete_color_1'><i class='fa fa-grav w3-fa w3-margin-right w3-xlarge w3-text-templete_color_1'></i>Objective</h2>        <div class='w3-container w3-padding-left-50'>            <p class=''>" + string20 + "</p>        </div>    </div>";
            }
            if (!string21.equals("")) {
                this.declar = "<div class='w3-container w3-white w3-margin-bottom'>    <h2 class='w3-capitalize w3-text-templete_color_1'><i class='fa fa-pencil w3-fa w3-margin-right w3-xlarge w3-text-templete_color_1'></i>Declaration</h2>    <div class='w3-container w3-padding-left-50'>        <p class=''>" + string21 + "</p>    </div></div>";
            }
        }
        Cursor rawQuery7 = this.DB.rawQuery("select * from work_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery7.getCount() > 0) {
            rawQuery7.moveToFirst();
            String str5 = "";
            Cursor rawQuery8 = this.DB.rawQuery("Select work_title from title_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
            rawQuery8.moveToFirst();
            if (rawQuery8.getCount() > 0 && (string13 = rawQuery8.getString(rawQuery8.getColumnIndex("work_title"))) != null) {
                str5 = string13;
            }
            rawQuery8.close();
            for (int i2 = 0; i2 < rawQuery7.getCount(); i2++) {
                int i3 = rawQuery7.getInt(rawQuery7.getColumnIndex("work_id"));
                String string22 = rawQuery7.getString(rawQuery7.getColumnIndex("date_of_join"));
                String string23 = rawQuery7.getString(rawQuery7.getColumnIndex("end_date"));
                String string24 = rawQuery7.getString(rawQuery7.getColumnIndex("designation"));
                String string25 = rawQuery7.getString(rawQuery7.getColumnIndex("organization"));
                String string26 = rawQuery7.getString(rawQuery7.getColumnIndex("role"));
                this.designation = string24;
                String str6 = "";
                Cursor rawQuery9 = this.DB.rawQuery("Select * from extra_table where profile_id='" + this.sp.getString(this, "profile_id") + "' and table_name='work_table' and table_id='" + i3 + "'", null);
                rawQuery9.moveToFirst();
                if (rawQuery9.getCount() > 0) {
                    for (int i4 = 0; i4 < rawQuery9.getCount(); i4++) {
                        str6 = str6 + "<p class='w3-margin-top-0'><b>" + rawQuery9.getString(rawQuery9.getColumnIndex("title")) + " : </b> " + rawQuery9.getString(rawQuery9.getColumnIndex("value")) + "</p>";
                        rawQuery9.moveToNext();
                    }
                }
                rawQuery9.close();
                this.sub_exp += "<h6 class='w3-margin-bottom-2'><b>" + string25 + "</b></h6>             <p class='w3-margin-top-0 w3-margin-bottom-0'><b>Designation : </b><b>" + string24 + ", " + string22 + " - " + string23 + "</b></p>                        " + ((string26.equals("") || string26.equals(null)) ? "" : "<p class='w3-margin-top-0 w3-margin-bottom-0'><b>Role : </b>" + string26 + "</p>") + str6;
                rawQuery7.moveToNext();
            }
            this.experience = "<div class='w3-container w3-white w3-margin-bottom'>        <h2 class='w3-capitalize w3-text-templete_color_1'><i class='fa fa-suitcase w3-fa w3-margin-right w3-xlarge w3-text-templete_color_1'></i>" + str5 + "</h2>        <div class='w3-container w3-padding-left-50'>" + this.sub_exp + "        </div>    </div>";
        } else {
            this.designation = "Fresher";
        }
        rawQuery7.close();
        Cursor rawQuery10 = this.DB.rawQuery("select * from project_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery10.getCount() > 0) {
            rawQuery10.moveToFirst();
            String str7 = "";
            Cursor rawQuery11 = this.DB.rawQuery("Select project_title from title_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
            rawQuery11.moveToFirst();
            if (rawQuery11.getCount() > 0 && (string12 = rawQuery11.getString(rawQuery11.getColumnIndex("project_title"))) != null) {
                str7 = string12;
            }
            rawQuery11.close();
            for (int i5 = 0; i5 < rawQuery10.getCount(); i5++) {
                int i6 = rawQuery10.getInt(rawQuery10.getColumnIndex("project_id"));
                String string27 = rawQuery10.getString(rawQuery10.getColumnIndex("title"));
                String string28 = rawQuery10.getString(rawQuery10.getColumnIndex("discription"));
                String string29 = rawQuery10.getString(rawQuery10.getColumnIndex("duration"));
                String string30 = rawQuery10.getString(rawQuery10.getColumnIndex("role"));
                String string31 = rawQuery10.getString(rawQuery10.getColumnIndex("team_size"));
                String string32 = rawQuery10.getString(rawQuery10.getColumnIndex("company"));
                String str8 = "";
                if (!string32.trim().equals("")) {
                    string32 = "<p class='w3-margin-top-0 w3-margin-bottom-0'><b>Company / Institute : </b>" + string32 + "</p>";
                }
                if (!string31.equals("")) {
                    str8 = string31.contains("member") ? "<p class='w3-margin-top-0 w3-margin-bottom-0'>" + ("<b>Team Size</b> : " + string31) + "</p>" : "<p class='w3-margin-top-0 w3-margin-bottom-0'>" + ("<b>Team Size : </b>" + string31 + " Members") + "</p>";
                }
                String str9 = "";
                Cursor rawQuery12 = this.DB.rawQuery("Select * from extra_table where profile_id='" + this.sp.getString(this, "profile_id") + "' and table_name='project_table' and table_id='" + i6 + "'", null);
                rawQuery12.moveToFirst();
                if (rawQuery12.getCount() > 0) {
                    for (int i7 = 0; i7 < rawQuery12.getCount(); i7++) {
                        str9 = str9 + "<p class='w3-margin-top-0 w3-margin-bottom-0'><b>" + rawQuery12.getString(rawQuery12.getColumnIndex("title")) + " : </b>" + rawQuery12.getString(rawQuery12.getColumnIndex("value")) + "</p>";
                        rawQuery12.moveToNext();
                    }
                }
                rawQuery12.close();
                this.sub_work += "<h6 class='w3-margin-bottom-2 w3-margin-top-10'><b>" + string27 + "</b></h6>          <p class='w3-margin-top-0 w3-margin-bottom-0'><b>Description : </b>" + string28 + "</p>          <p class='w3-margin-top-0 w3-margin-bottom-0'><b>Duration : </b>" + string29 + " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; " + str8 + "          <p class='w3-margin-top-0 w3-margin-bottom-0'><b>Role : </b>" + string30 + "</p>" + string32 + str9;
                rawQuery10.moveToNext();
            }
            this.project = "<div class='w3-container w3-white w3-margin-bottom'>     <h2 class='w3-capitalize w3-text-templete_color_1'><i class='fa fa-book w3-fa w3-margin-right w3-xlarge w3-text-templete_color_1'></i>" + str7 + "</h2>     <div class='w3-container w3-padding-left-50'>" + this.sub_work + "     </div> </div>";
        }
        rawQuery10.close();
        Cursor rawQuery13 = this.DB.rawQuery("select * from academic_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery13.getCount() > 0) {
            rawQuery13.moveToFirst();
            String str10 = "";
            Cursor rawQuery14 = this.DB.rawQuery("Select academic_title from title_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
            rawQuery14.moveToFirst();
            if (rawQuery14.getCount() > 0 && (string11 = rawQuery14.getString(rawQuery14.getColumnIndex("academic_title"))) != null) {
                str10 = string11;
            }
            rawQuery14.close();
            for (int i8 = 0; i8 < rawQuery13.getCount(); i8++) {
                int i9 = rawQuery13.getInt(rawQuery13.getColumnIndex("academic_id"));
                String string33 = rawQuery13.getString(rawQuery13.getColumnIndex("course"));
                String string34 = rawQuery13.getString(rawQuery13.getColumnIndex("institute"));
                String string35 = rawQuery13.getString(rawQuery13.getColumnIndex("percentage"));
                String string36 = rawQuery13.getString(rawQuery13.getColumnIndex("yop"));
                String str11 = "";
                Cursor rawQuery15 = this.DB.rawQuery("Select * from extra_table where profile_id='" + this.sp.getString(this, "profile_id") + "' and table_name='academic_table' and table_id='" + i9 + "'", null);
                rawQuery15.moveToFirst();
                if (rawQuery15.getCount() > 0) {
                    String str12 = str11;
                    for (int i10 = 0; i10 < rawQuery15.getCount(); i10++) {
                        str12 = str12 + "<p class='w3-margin-top-0 w3-margin-bottom-0'><b>" + rawQuery15.getString(rawQuery15.getColumnIndex("title")) + " : </b>" + rawQuery15.getString(rawQuery15.getColumnIndex("value")) + "</p>";
                        rawQuery15.moveToNext();
                    }
                    str11 = str12;
                }
                rawQuery15.close();
                this.sub_edu += "<h6 class='w3-margin-bottom-2 w3-margin-top-10'><b>" + string33 + "</b></h6>            <p class='w3-margin-top-0 w3-margin-bottom-0'>" + string34 + ", <b>" + string35 + "</b>, <b>" + string36 + "</b></p>" + str11;
                rawQuery13.moveToNext();
            }
            this.education = "<div class='w3-container w3-white w3-margin-bottom'>       <h2 class='w3-capitalize w3-text-templete_color_1'><i class='fa  fa-graduation-cap w3-fa w3-margin-right w3-xlarge w3-text-templete_color_1'></i>" + str10 + "</h2>       <div class='w3-container w3-padding-left-50'>" + this.sub_edu + "       </div>   </div>";
        }
        rawQuery13.close();
        Cursor rawQuery16 = this.DB.rawQuery("select * from skill_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery16.getCount() > 0) {
            rawQuery16.moveToFirst();
            String str13 = "";
            Cursor rawQuery17 = this.DB.rawQuery("Select skill_title from title_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
            rawQuery17.moveToFirst();
            if (rawQuery17.getCount() > 0 && (string10 = rawQuery17.getString(rawQuery17.getColumnIndex("skill_title"))) != null) {
                str13 = string10;
            }
            rawQuery17.close();
            for (int i11 = 0; i11 < rawQuery16.getCount(); i11++) {
                this.sub_skill += "<li>" + rawQuery16.getString(rawQuery16.getColumnIndex("skill")) + "</li>";
                rawQuery16.moveToNext();
            }
            this.skill = "<div class='w3-padding-left-20'>    <p class='w3-large w3-capitalize'><b><i class='fa fa-asterisk fa-fw w3-margin-right'></i>" + str13 + "</b></p>    <ul class='w3-large' style='list-style: none;'>" + this.sub_skill + "    </ul></div>";
        }
        rawQuery16.close();
        Cursor rawQuery18 = this.DB.rawQuery("select * from strength_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery18.getCount() > 0) {
            rawQuery18.moveToFirst();
            String str14 = "";
            Cursor rawQuery19 = this.DB.rawQuery("Select strength_title from title_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
            rawQuery19.moveToFirst();
            if (rawQuery19.getCount() > 0 && (string9 = rawQuery19.getString(rawQuery19.getColumnIndex("strength_title"))) != null) {
                str14 = string9;
            }
            rawQuery19.close();
            for (int i12 = 0; i12 < rawQuery18.getCount(); i12++) {
                this.sub_strength += "<h6 class='font-weight-500'>" + rawQuery18.getString(rawQuery18.getColumnIndex("strength")) + "</h6>";
                rawQuery18.moveToNext();
            }
            this.strength = "<div class='w3-container w3-white w3-margin-bottom'>      <h2 class='w3-capitalize w3-text-templete_color_1'><i class='fa fa-xing w3-fa w3-margin-right w3-xlarge w3-text-templete_color_1'></i>" + str14 + "</h2>      <div class='w3-container w3-padding-left-50'>" + this.sub_strength + "      </div>  </div>";
        }
        rawQuery18.close();
        Cursor rawQuery20 = this.DB.rawQuery("select * from interest_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery20.getCount() > 0) {
            rawQuery20.moveToFirst();
            String str15 = "";
            Cursor rawQuery21 = this.DB.rawQuery("Select interest_title from title_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
            rawQuery21.moveToFirst();
            if (rawQuery21.getCount() > 0 && (string8 = rawQuery21.getString(rawQuery21.getColumnIndex("interest_title"))) != null) {
                str15 = string8;
            }
            rawQuery21.close();
            for (int i13 = 0; i13 < rawQuery20.getCount(); i13++) {
                this.sub_intrest += "<li>" + rawQuery20.getString(rawQuery20.getColumnIndex("interest")) + "</li>";
                rawQuery20.moveToNext();
            }
            this.intrest = "<div class='w3-padding-left-20'>    <p class='w3-large w3-capitalize'><b><i class='fa fa-asterisk fa-fw w3-margin-right'></i>" + str15 + "</b></p>    <ul class='w3-large' style='list-style: none;'>" + this.sub_intrest + "     </ul>     <br> </div>";
        }
        rawQuery20.close();
        Cursor rawQuery22 = this.DB.rawQuery("select * from industrial_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery22.getCount() > 0) {
            rawQuery22.moveToFirst();
            String str16 = "";
            Cursor rawQuery23 = this.DB.rawQuery("Select industrial_title from title_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
            rawQuery23.moveToFirst();
            if (rawQuery23.getCount() > 0 && (string7 = rawQuery23.getString(rawQuery23.getColumnIndex("industrial_title"))) != null) {
                str16 = string7;
            }
            rawQuery23.close();
            for (int i14 = 0; i14 < rawQuery22.getCount(); i14++) {
                this.sub_indust += "<h6 class='font-weight-500'>" + rawQuery22.getString(rawQuery22.getColumnIndex("industrial")) + "</h6>";
                rawQuery22.moveToNext();
            }
            this.indrust = "  <h5><b>" + str16 + " : </b></h5>" + this.sub_indust;
        }
        Cursor rawQuery24 = this.DB.rawQuery("select * from inplant_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery24.getCount() > 0) {
            rawQuery24.moveToFirst();
            String str17 = "";
            Cursor rawQuery25 = this.DB.rawQuery("Select inplant_title from title_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
            rawQuery25.moveToFirst();
            if (rawQuery25.getCount() > 0 && (string6 = rawQuery25.getString(rawQuery25.getColumnIndex("inplant_title"))) != null) {
                str17 = string6;
            }
            rawQuery25.close();
            for (int i15 = 0; i15 < rawQuery24.getCount(); i15++) {
                this.sub_inplant += "<h5 >" + rawQuery24.getString(rawQuery24.getColumnIndex("inplant")) + "</h5>";
                rawQuery24.moveToNext();
            }
            this.inplant = "  <h5><b>" + str17 + " : </b></h5>" + this.sub_inplant;
        }
        if (rawQuery24.getCount() > 0 || rawQuery22.getCount() > 0) {
            this.explore = "<div class='w3-container w3-white w3-margin-bottom'>    <h2 class='w3-capitalize w3-text-templete_color_1'><i class='fa fa-anchor w3-fa w3-margin-right w3-xlarge w3-text-templete_color_1'></i>Industrial Exposure</h2>    <div class='w3-container w3-padding-left-50'>" + this.indrust + this.inplant + "    </div></div>";
        }
        rawQuery24.close();
        rawQuery22.close();
        Cursor rawQuery26 = this.DB.rawQuery("select * from achievement_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery26.getCount() > 0) {
            rawQuery26.moveToFirst();
            String str18 = "";
            Cursor rawQuery27 = this.DB.rawQuery("Select achieve_title from title_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
            rawQuery27.moveToFirst();
            if (rawQuery27.getCount() > 0 && (string5 = rawQuery27.getString(rawQuery27.getColumnIndex("achieve_title"))) != null) {
                str18 = string5;
            }
            rawQuery27.close();
            for (int i16 = 0; i16 < rawQuery26.getCount(); i16++) {
                this.sub_achive += "<h6 class='font-weight-500'>" + rawQuery26.getString(rawQuery26.getColumnIndex("achieve")) + "</h6>";
                rawQuery26.moveToNext();
            }
            this.achive = "<div class='w3-container w3-white w3-margin-bottom'>     <h2 class='w3-capitalize w3-text-templete_color_1'><i class='fa fa-trophy w3-fa w3-margin-right w3-xlarge w3-text-templete_color_1'></i>" + str18 + "</h2>     <div class='w3-container w3-padding-left-50'>" + this.sub_achive + "     </div> </div>";
        }
        rawQuery26.close();
        Cursor rawQuery28 = this.DB.rawQuery("select * from cocurricular_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery28.getCount() > 0) {
            rawQuery28.moveToFirst();
            String str19 = "";
            Cursor rawQuery29 = this.DB.rawQuery("Select cocurricular_title from title_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
            rawQuery29.moveToFirst();
            if (rawQuery29.getCount() > 0 && (string4 = rawQuery29.getString(rawQuery29.getColumnIndex("cocurricular_title"))) != null) {
                str19 = string4;
            }
            rawQuery29.close();
            for (int i17 = 0; i17 < rawQuery28.getCount(); i17++) {
                this.sub_cocuri += "<h6 class='font-weight-500'>" + rawQuery28.getString(rawQuery28.getColumnIndex("cocurricular")) + "</h6>";
                rawQuery28.moveToNext();
            }
            this.cocuri = "<h5><b>" + str19 + " : </b></h5>" + this.sub_cocuri;
        }
        Cursor rawQuery30 = this.DB.rawQuery("select * from extracurricular_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery30.getCount() > 0) {
            rawQuery30.moveToFirst();
            String str20 = "";
            Cursor rawQuery31 = this.DB.rawQuery("Select extracurricular_title from title_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
            rawQuery31.moveToFirst();
            if (rawQuery31.getCount() > 0 && (string3 = rawQuery31.getString(rawQuery31.getColumnIndex("extracurricular_title"))) != null) {
                str20 = string3;
            }
            rawQuery31.close();
            for (int i18 = 0; i18 < rawQuery30.getCount(); i18++) {
                this.sub_extra += "<h6 class='font-weight-500'>" + rawQuery30.getString(rawQuery30.getColumnIndex("extracurricular")) + "</h6>";
                rawQuery30.moveToNext();
            }
            this.extra = " <h5><b>" + str20 + " : </b></h5>" + this.sub_extra;
        }
        if (rawQuery30.getCount() > 0 || rawQuery28.getCount() > 0) {
            this.curricular = "<div class='w3-container w3-white w3-margin-bottom'>        <h2 class='w3-capitalize w3-text-templete_color_1'><i class='fa fa-newspaper-o w3-fa w3-margin-right w3-xlarge w3-text-templete_color_1'></i>Curricular Activities</h2>        <div class='w3-container w3-padding-left-50'>" + this.cocuri + this.extra + "        </div>    </div>";
        }
        rawQuery30.close();
        rawQuery28.close();
        Cursor rawQuery32 = this.DB.rawQuery("select * from hobbies_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery32.getCount() > 0) {
            rawQuery32.moveToFirst();
            String str21 = "";
            Cursor rawQuery33 = this.DB.rawQuery("Select hobbies_title from title_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
            rawQuery33.moveToFirst();
            if (rawQuery33.getCount() > 0 && (string2 = rawQuery33.getString(rawQuery33.getColumnIndex("hobbies_title"))) != null) {
                str21 = string2;
            }
            rawQuery33.close();
            for (int i19 = 0; i19 < rawQuery32.getCount(); i19++) {
                this.sub_hobbi += "<h6 class='font-weight-500'>" + rawQuery32.getString(rawQuery32.getColumnIndex("hobbies")) + "</h6>";
                rawQuery32.moveToNext();
            }
            this.hobbi = " <div class='w3-container w3-white w3-margin-bottom'>     <h2 class='w3-capitalize w3-text-templete_color_1'><i class='fa fa-object-group w3-fa w3-margin-right w3-xlarge w3-text-templete_color_1'></i>" + str21 + "</h2>     <div class='w3-container w3-padding-left-50'>" + this.sub_hobbi + "     </div></div>";
        }
        rawQuery32.close();
        Cursor rawQuery34 = this.DB.rawQuery("select * from reference_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
        if (rawQuery34.getCount() > 0) {
            rawQuery34.moveToFirst();
            String str22 = "";
            Cursor rawQuery35 = this.DB.rawQuery("Select reference_title from title_table where profile_id='" + this.sp.getString(this, "profile_id") + "'", null);
            rawQuery35.moveToFirst();
            if (rawQuery35.getCount() > 0 && (string = rawQuery35.getString(rawQuery35.getColumnIndex("reference_title"))) != null) {
                str22 = string;
            }
            rawQuery35.close();
            for (int i20 = 0; i20 < rawQuery34.getCount(); i20++) {
                int i21 = rawQuery34.getInt(rawQuery34.getColumnIndex("reference_id"));
                String string37 = rawQuery34.getString(rawQuery34.getColumnIndex(AppMeasurementSdk.ConditionalUserProperty.NAME));
                String string38 = rawQuery34.getString(rawQuery34.getColumnIndex("designation"));
                String string39 = rawQuery34.getString(rawQuery34.getColumnIndex("organization"));
                String string40 = rawQuery34.getString(rawQuery34.getColumnIndex("email"));
                String string41 = rawQuery34.getString(rawQuery34.getColumnIndex("phone"));
                String str23 = "";
                Cursor rawQuery36 = this.DB.rawQuery("Select * from extra_table where profile_id='" + this.sp.getString(this, "profile_id") + "' and table_name='reference_table' and table_id='" + i21 + "'", null);
                rawQuery36.moveToFirst();
                if (rawQuery36.getCount() > 0) {
                    String str24 = str23;
                    for (int i22 = 0; i22 < rawQuery36.getCount(); i22++) {
                        str24 = str24 + "<p class='w3-margin-top-0 w3-margin-bottom-0'><b>" + rawQuery36.getString(rawQuery36.getColumnIndex("title")) + " : </b>" + rawQuery36.getString(rawQuery36.getColumnIndex("value")) + "</p>";
                        rawQuery36.moveToNext();
                    }
                    str23 = str24;
                }
                rawQuery36.close();
                this.sub_refer += "<h5 class='w3-margin-bottom-0'><b>" + string37 + "</b></h5>            <p class='w3-margin-top-0 w3-margin-bottom-0'>" + string38 + "</p>            <p class='w3-margin-top-0 w3-margin-bottom-0'>" + string39 + "</p>            <p class='w3-margin-top-0 w3-margin-bottom-0'>" + string40 + "</p>            <p class='w3-margin-top-0 w3-margin-bottom-0'>" + string41 + "</p>" + str23;
                rawQuery34.moveToNext();
            }
            this.reference = "<div class='w3-container w3-white w3-margin-bottom'>       <h2 class='w3-capitalize w3-text-templete_color_1'><i class='fa fa-handshake-o w3-fa w3-margin-right w3-xlarge w3-text-templete_color_1'></i>" + str22 + "</h2>       <div class='w3-container w3-padding-left-50'>" + this.sub_refer + "       </div>   </div>";
        }
        rawQuery34.close();
    }

    public String resueme() {
        return "<!DOCTYPE html><html>    <title>NithrA - Resume Template 13</title>    <meta charset='UTF-8'>    <link rel='stylesheet' href='css/13.css'>    <link rel='stylesheet' href='css/font-awesome.min.css'>    <style>        @page { size: A4 portrait;margin: 1mm; cover-letter {page-break-after: always;}}        html,body,h1,h2,h3,h4,h5,h6 {font-family: 'Arial', sans-serif}    </style>    <body class=''>        <div class='w3-content' style='max-width:1400px;'>            <div class='w3-row w3-padding w3-templete_color_1'>" + this.cover_letter + "  <div class='color-div'>               <div class='w3-third'>                    <div class='w3-display-container w3-img-div w3-padding-24'>" + this.image + this.name + "                    </div>                    <div class='w3-container w3-txt-div'>" + this.personal + this.skill + this.intrest + "                        <div class='w3-no-break'>                            <p class='w3-large w3-templete_color_3 w3-capitalize'><b></b></p>                            <ul class='w3-large' style='list-style: none;'>                                <li></li>                                <li></li>                            </ul>                            <br>                        </div>                        <div class='w3-no-break'>                            <p class='w3-large w3-templete_color_3 w3-capitalize'><b></b></p>                            <ul class='w3-large w3' style='list-style: none;'>                                <li>                                </li>                            </ul>                            <br>                        </div>                        <div class='w3-no-break'>                            <p class='w3-large w3-templete_color_3 w3-capitalize'><b></b></p>                            <ul class='w3-large' style='list-style: none;'>                                <li></li>                                <li></li>                                <li></li>                            </ul>                            <br>                        </div>                    </div><br>                </div>                <div class='w3-twothird w3-templete_color_1'>                    <div class='main-content w3-white'>" + this.objective + this.education + this.experience + this.project + this.explore + this.achive + this.curricular + this.strength + this.hobbi + this.info + this.reference + this.declar + "                    </div>                </div>    </div>                <div class='w3-no-break w3-container w3-white w3-margin-bottom'>                    <div class='w3-container'>                                <h6>                                    <table>                                        <tr><td width='7%'>Date</td><td width='1%'> : </td><td width='84.5%'>" + this.date + "</td><td>Signature,</td></tr>                                        <tr><td width='7%'>Place</td><td  width='1%'> : </td><td width='80%'>" + this.place + "</td>" + this.sign + "</tr>                                                                           </table>                                </h6>" + this.sign_btm_name + "                         </div>                </div>            </div>        </div>    </body></html>";
    }

    public void createPdfFile() {
        try {
            File createEmptyFile = createEmptyFile();
            new PdfWriter(getApplicationContext(), this.mWebView.createPrintDocumentAdapter(), createEmptyFile).write(new PdfWriter.PdfWriterCallback() {
                /* class nithra.resume.maker.cv.builder.app.Formates.Format_13.AnonymousClass6 */

                @Override // nithra.resume.maker.cv.builder.app.PdfWriter.PdfWriterCallback
                public void onWriteFinished() {
                    Format_13.this.wait_dialog.dismiss();
                    Toast.makeText(Format_13.this.getApplicationContext(), "Resume generated successfully", 0).show();
                    Uri fromFile = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Nithra/ResumeBuilder/" + Format_13.this.fileName));
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setFlags(67108864);
                    intent.setDataAndType(fromFile, "application/pdf");
                    try {
                        Format_13.this.startActivity(intent);
                    } catch (ActivityNotFoundException unused) {
                        Utils.toast_center(Format_13.this, "No Application available to view PDF");
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
        this.fileName = this.sp.getString(this, "profile_name") + "_Experienced Resume.pdf";
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
                /* class nithra.resume.maker.cv.builder.app.Formates.Format_13.AnonymousClass7 */

                @SuppressLint({"NewApi"})
                public void onClick(View view) {
                    if (Format_13.this.sp.getInt(Format_13.this, "permissiond") == 2) {
                        Intent intent = new Intent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.setData(Uri.fromParts("package", Format_13.this.getPackageName(), null));
                        Format_13.this.startActivity(intent);
                        dialog.dismiss();
                        return;
                    }
                    Format_13.this.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 151);
                    dialog.dismiss();
                }
            });
            dialog.show();
        } else {
            this.sp.putInt(this, "permissiond", 1);
        }
    }

    @Override // android.support.v4.app.FragmentActivity
    public void onResume() {
        MainActivity.load_addFromMain(this, this.ads_lay);
        if (new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + "/Nithra/ResumeBuilder/" + this.sp.getString(this, "profile_name") + "_Experienced Resume.pdf").exists()) {
            this.generat_txt.setText("Regenerate");
        } else {
            this.generat_txt.setText("Generate");
        }
        super.onResume();
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
            /* class nithra.resume.maker.cv.builder.app.Formates.Format_13.AnonymousClass8 */

            @Override // nithra.resume.maker.cv.builder.app.color.AmbilWarnaDialog.OnAmbilWarnaListener
            public void onOk(AmbilWarnaDialog ambilWarnaDialog, int i) {
                Toast.makeText(Format_13.this, "ok", 0).show();
                Format_13 format_13 = Format_13.this;
                format_13.color = i;
                format_13.displayColor();
                if (Format_13.this.color_dialog.isShowing()) {
                    Format_13.this.color_dialog.dismiss();
                }
            }

            @Override // nithra.resume.maker.cv.builder.app.color.AmbilWarnaDialog.OnAmbilWarnaListener
            public void onCancel(AmbilWarnaDialog ambilWarnaDialog) {
                Toast.makeText(Format_13.this, "cancel", 0).show();
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
        if (this.sp.getInt(this, "key") == 12) {
            str = resueme();
        } else {
            str = "";
        }
        this.mWebView.loadDataWithBaseURL("file:///android_asset/", str, "text/html", "utf-8", null);
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
