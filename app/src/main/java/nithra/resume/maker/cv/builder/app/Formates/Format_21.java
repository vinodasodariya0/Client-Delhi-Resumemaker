package nithra.resume.maker.cv.builder.app.Formates;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;



import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

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


public class Format_21 extends AppCompatActivity {
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
    String exp = ("<!DOCTYPE html><html><head><style>th,td {  border: 1px solid black;}table.d {  table-layout: fixed;  width: 100%;  }</style></head><body><h2  align=\"center\">பயணத் திட்டம்</h2><table class='d'>  <tr>    <th align=\"left\">பயணத்தின் பெயர் :</th>    <th align=\"right\">பயணத்தின் வகை :</th>  </tr></table><table class=\"d\";border: 1px solid black;>  <tr>    <th align=\"left\">Place</th>    <th align=\"left\">Date</th>    <th align=\"left\">Start Time</th>    <th align=\"left\">End Time</th>    <th align=\"left\">Hints</th>  </tr>" + this.designation + "</table></body></html>");
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
        ((AppCompatTextView) findViewById(R.id.title_name)).setText("Format - " + (this.sp.getInt(this, "key") + 1));
        this.DB = openOrCreateDatabase("RESUME_BUILDER", 0, null);
        this.mWebView.getSettings().setAllowFileAccess(true);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setSupportZoom(true);
        this.mWebView.getSettings().setBuiltInZoomControls(true);
        this.mWebView.getSettings().setDisplayZoomControls(true);
        this.mWebView.getSettings().setLoadWithOverviewMode(true);
        this.mWebView.getSettings().setUseWideViewPort(true);
        this.mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Formates.Format_21.AnonymousClass1 */

            public boolean onLongClick(View view) {
                return true;
            }
        });
        this.color = R.color.colorPrimary;
        Showcase();
        first_load();
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
        if (this.sp.getInt(this, "key") == 19) {
            str = report();
        } else {
            str = "";
        }
        this.mWebView.loadDataWithBaseURL("file:///android_asset/", str, "text/html", "utf-8", null);
        this.save.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Formates.Format_21.AnonymousClass2 */

            public void onClick(View view) {
                if (Format_21.this.sp.getInt(Format_21.this, "permissiond") == 1) {
                    try {
                        File externalStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                        new File(externalStorageDirectory.getAbsolutePath() + "/Nithra/ResumeBuilder/").mkdirs();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Format_21.this.createPdfFile();
                    Format_21.this.wait_dialog.show();
                    return;
                }
                Format_21.this.PermissionFun();
            }
        });
        this.picker.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Formates.Format_21.AnonymousClass3 */

            public void onClick(View view) {
                if (Format_21.this.sp.getInt(Format_21.this, "key") == 0) {
                    Format_21.this.sp.putString(Format_21.this, "type", "text");
                    Format_21.this.openDialog(false);
                } else if (Format_21.this.sp.getInt(Format_21.this, "key") == 1) {
                    Format_21.this.color_dialog.show();
                } else if (Format_21.this.sp.getInt(Format_21.this, "key") == 2) {
                    Format_21.this.color_dialog.show();
                } else if (Format_21.this.sp.getInt(Format_21.this, "key") == 3) {
                    Format_21.this.sp.putString(Format_21.this, "type", "text");
                    Format_21.this.openDialog(false);
                } else if (Format_21.this.sp.getInt(Format_21.this, "key") == 4) {
                    Format_21.this.sp.putString(Format_21.this, "type", "text");
                    Format_21.this.openDialog(false);
                } else if (Format_21.this.sp.getInt(Format_21.this, "key") == 5) {
                    Format_21.this.sp.putString(Format_21.this, "type", "text");
                    Format_21.this.openDialog(false);
                } else if (Format_21.this.sp.getInt(Format_21.this, "key") == 6) {
                    Format_21.this.sp.putString(Format_21.this, "type", "text");
                    Format_21.this.openDialog(false);
                } else if (Format_21.this.sp.getInt(Format_21.this, "key") == 7) {
                    Format_21.this.sp.putString(Format_21.this, "type", "text");
                    Format_21.this.openDialog(false);
                } else if (Format_21.this.sp.getInt(Format_21.this, "key") == 8) {
                    Format_21.this.sp.putString(Format_21.this, "type", "text");
                    Format_21.this.openDialog(false);
                }
            }
        });
        this.color_dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        this.color_dialog.setContentView(R.layout.dialog_color_view);
        ((LinearLayout) this.color_dialog.findViewById(R.id.text_lay)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Formates.Format_21.AnonymousClass4 */

            public void onClick(View view) {
                Format_21.this.sp.putString(Format_21.this, "type", "text");
                Format_21.this.openDialog(false);
            }
        });
        ((LinearLayout) this.color_dialog.findViewById(R.id.border_lay)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Formates.Format_21.AnonymousClass5 */

            public void onClick(View view) {
                Format_21.this.sp.putString(Format_21.this, "type", "bg");
                Format_21.this.openDialog(false);
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
        this.sign_name = "";
        this.sign_btm_name = "";
        this.name = "";
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
        for (int i = 0; i < 5; i++) {
            this.designation += "  <tr>    <td align='center'>Alfreds Futterkiste</td>    <td align='center'>Maria Anders</td>    <td align='center'>Germany</td>    <td align='center'>Germany</td>    <td align='center'>Germany</td>  </tr>";
        }
    }

    public String report() {
        return "<!DOCTYPE html><html><head><style>th,td {  border: 1px solid black;}th.f { border: 0px solid black; }table.e {  table-layout: fixed;  width: 100%;}table.d {  table-layout: fixed;  width: 100%;  border-collapse: collapse;  border: 1px solid black;}</style></head><body><h2  align=\"center\">பயணத் திட்டம்</h2><table class='e'>  <tr>    <th class='f'; align=\"left\">பயணத்தின் பெயர் :</th>    <th class='f'; align=\"right\">பயணத்தின் வகை :</th>  </tr></table> <br><table class='d'>  <tr>    <th>Place</th>    <th>Date</th>    <th>Start Date</th>    <th>End Date</th>    <th>Hints</th>  </tr>" + this.designation + "</table></body></html>";
    }

    public void createPdfFile() {
        try {
            File createEmptyFile = createEmptyFile();
            new PdfWriter(getApplicationContext(), this.mWebView.createPrintDocumentAdapter(), createEmptyFile).write(new PdfWriter.PdfWriterCallback() {
                /* class nithra.resume.maker.cv.builder.app.Formates.Format_21.AnonymousClass6 */

                @Override // nithra.resume.maker.cv.builder.app.PdfWriter.PdfWriterCallback
                public void onWriteFinished() {
                    Format_21.this.wait_dialog.dismiss();
                    Toast.makeText(Format_21.this.getApplicationContext(), "Resume generated successfully", 0).show();
                    Uri fromFile = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Nithra/ResumeBuilder/" + Format_21.this.fileName));
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setFlags(67108864);
                    intent.setDataAndType(fromFile, "application/pdf");
                    try {
                        Format_21.this.startActivity(intent);
                    } catch (ActivityNotFoundException unused) {
                        Utils.toast_center(Format_21.this, "No Application available to view PDF");
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
        this.fileName = this.sp.getString(this, "profile_name") + "_format_" + (this.sp.getInt(this, "key") + 1) + ".pdf";
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
                /* class nithra.resume.maker.cv.builder.app.Formates.Format_21.AnonymousClass7 */

                @SuppressLint({"NewApi"})
                public void onClick(View view) {
                    if (Format_21.this.sp.getInt(Format_21.this, "permissiond") == 2) {
                        Intent intent = new Intent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.setData(Uri.fromParts("package", Format_21.this.getPackageName(), null));
                        Format_21.this.startActivity(intent);
                        dialog.dismiss();
                        return;
                    }
                    Format_21.this.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 151);
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
            /* class nithra.resume.maker.cv.builder.app.Formates.Format_21.AnonymousClass8 */

            @Override // nithra.resume.maker.cv.builder.app.color.AmbilWarnaDialog.OnAmbilWarnaListener
            public void onOk(AmbilWarnaDialog ambilWarnaDialog, int i) {
                Toast.makeText(Format_21.this, "ok", 0).show();
                Format_21 format_21 = Format_21.this;
                format_21.color = i;
                format_21.displayColor();
                if (Format_21.this.color_dialog.isShowing()) {
                    Format_21.this.color_dialog.dismiss();
                }
            }

            @Override // nithra.resume.maker.cv.builder.app.color.AmbilWarnaDialog.OnAmbilWarnaListener
            public void onCancel(AmbilWarnaDialog ambilWarnaDialog) {
                Toast.makeText(Format_21.this, "cancel", 0).show();
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
        if (this.sp.getInt(this, "key") == 19) {
            str = report();
        } else {
            str = "";
        }
        this.mWebView.loadDataWithBaseURL("file:///android_asset/", str, "text/html", "utf-8", null);
    }

    @Override // android.support.v4.app.FragmentActivity
    public void onResume() {
        MainActivity.load_addFromMain(this, this.ads_lay);
        if (new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + "/Nithra/ResumeBuilder/" + this.sp.getString(this, "profile_name") + "_format_" + (this.sp.getInt(this, "key") + 1) + ".pdf").exists()) {
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
