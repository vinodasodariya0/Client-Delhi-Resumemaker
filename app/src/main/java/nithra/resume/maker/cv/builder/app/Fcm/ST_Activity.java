package nithra.resume.maker.cv.builder.app.Fcm;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;




import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import nithra.resume.maker.cv.builder.app.MainActivity;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.SharedPreference;
import nithra.resume.maker.cv.builder.app.Utils;

public class ST_Activity extends AppCompatActivity {
    LinearLayout addview;
    Bitmap bitmap;
    AppCompatButton btn_close;
    WebView content_view;
    InterstitialAd interstitialAd_noti;
    List<ResolveInfo> listApp;
    String message;
    SQLiteDatabase myDB;
    PackageManager pManager;
    NestedScrollView scrool;
    SharedPreference sharedPreference;
    int show_ads;
    int show_id;
    String str_title = "";
    String tablenew = "noti_cal";
    String title;

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.SupportActivity, android.support.v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        getWindow().setFlags(1024, 1024);
        setRequestedOrientation(1);
        super.onCreate(bundle);
        setContentView(R.layout.st_lay);
        this.sharedPreference = new SharedPreference();
        this.myDB = openOrCreateDatabase("RESUME_BUILDER", 0, null);
        this.pManager = getPackageManager();
        this.myDB.execSQL("CREATE TABLE IF NOT EXISTS " + this.tablenew + " (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,message VARCHAR,date VARCHAR,time VARCHAR,isclose INT(4),isshow INT(4) default 0,type VARCHAR,bm VARCHAR,ntype VARCHAR,url VARCHAR);");
        this.interstitialAd_noti = new InterstitialAd(this);
        this.interstitialAd_noti.setAdUnitId("ca-app-pub-4267540560263635/5831684489");
        this.interstitialAd_noti.loadAd(new AdRequest.Builder().build());
        this.content_view = (WebView) findViewById(R.id.web);
        this.scrool = (NestedScrollView) findViewById(R.id.scrool);
        this.addview = (LinearLayout) findViewById(R.id.ads_lay);
        adds(this.addview);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int i = extras.getInt("idd");
            int i2 = extras.getInt("Noti_add");
            this.title = extras.getString("title");
            this.message = extras.getString("message");
            this.show_id = i;
            this.show_ads = i2;
            this.sharedPreference.putInt(getApplicationContext(), "Noti_add", this.show_ads);
            this.str_title = this.title;
        }
        this.myDB.execSQL("update " + this.tablenew + " set isclose='1' where id='" + this.show_id + "'");
        this.content_view.setOnLongClickListener(new View.OnLongClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fcm.ST_Activity.AnonymousClass1 */

            public boolean onLongClick(View view) {
                return true;
            }
        });
        ((TextView) findViewById(R.id.sticky)).setText(this.str_title);
        this.content_view.getSettings().setJavaScriptEnabled(true);
        String str = "<!DOCTYPE html> <html><head> </head> <body ><br>" + this.message + "</body></html>";
        String str2 = this.message;
        if (str2 == null) {
            this.content_view.loadDataWithBaseURL("", str, "text/html", "utf-8", null);
        } else if (str2.length() <= 4) {
            this.content_view.loadDataWithBaseURL("", str, "text/html", "utf-8", null);
        } else if (this.message.substring(0, 4).equals("http")) {
            this.content_view.loadUrl(this.message);
        } else {
            this.content_view.loadDataWithBaseURL("", str, "text/html", "utf-8", null);
        }
        this.content_view.setWebViewClient(new WebViewClient() {

            public void onReceivedError(WebView webView, int i, String str, String str2) {
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                try {
                    Utils.custom_tabs(ST_Activity.this, str);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return true;
                }
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                try {
                    Utils.mProgress(ST_Activity.this, "Loading...", true).show();
                } catch (Exception unused) {
                }
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
        this.btn_close = (AppCompatButton) findViewById(R.id.btn_close);
        this.btn_close.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (ST_Activity.this.sharedPreference.getInt(ST_Activity.this, "Noti_add") == 1) {
                    if (ST_Activity.this.interstitialAd_noti.isLoaded()) {
                        ST_Activity.this.interstitialAd_noti.show();
                        ST_Activity.this.interstitialAd_noti.setAdListener(new AdListener() {
                            /* class nithra.resume.maker.cv.builder.app.Fcm.ST_Activity.AnonymousClass3.AnonymousClass1 */

                            @Override // com.google.android.gms.ads.AdListener
                            public void onAdClosed() {
                                Intent intent = new Intent(ST_Activity.this, MainActivity.class);
                                ST_Activity.this.finish();
                                ST_Activity.this.startActivity(intent);
                            }
                        });
                        return;
                    }
                    ST_Activity.this.sharedPreference.putInt(ST_Activity.this.getApplicationContext(), "Noti_add", 0);
                    Intent intent = new Intent(ST_Activity.this, MainActivity.class);
                    ST_Activity.this.finish();
                    ST_Activity.this.startActivity(intent);
                } else if (ST_Activity.this.interstitialAd_noti.isLoaded()) {
                    ST_Activity.this.interstitialAd_noti.show();
                    ST_Activity.this.interstitialAd_noti.setAdListener(new AdListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fcm.ST_Activity.AnonymousClass3.AnonymousClass2 */

                        @Override // com.google.android.gms.ads.AdListener
                        public void onAdClosed() {
                            ST_Activity.this.finish();
                        }
                    });
                } else {
                    ST_Activity.this.finish();
                }
            }
        });
        ((FloatingActionButton) findViewById(R.id.fab_share)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                final String obj = Html.fromHtml(ST_Activity.this.message).toString();
                final Dialog dialog = new Dialog(ST_Activity.this, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
                dialog.setContentView(R.layout.share_dialog);
                ListView listView = (ListView) dialog.findViewById(R.id.share_list);
                ST_Activity sT_Activity = ST_Activity.this;
                sT_Activity.listApp = sT_Activity.showAllShareApp();
                for (int i = 0; i < ST_Activity.this.listApp.size(); i++) {
                    if (ST_Activity.this.listApp.get(i).activityInfo.packageName.equals("com.whatsapp")) {
                        ST_Activity.this.listApp.remove(i);
                        ST_Activity.this.listApp.add(0, ST_Activity.this.listApp.get(i));
                    }
                }
                if (ST_Activity.this.listApp != null) {
                    listView.setAdapter((ListAdapter) new MyAdapter());
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fcm.ST_Activity.AnonymousClass4.AnonymousClass1 */

                        @Override // android.widget.AdapterView.OnItemClickListener
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                            ST_Activity.this.share(ST_Activity.this.listApp.get(i), obj);
                            dialog.dismiss();
                        }
                    });
                }
                dialog.show();
            }
        });
    }



    private void share(ResolveInfo resolveInfo, String str) {
        if (resolveInfo.activityInfo.packageName.equals("com.whatsapp")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            intent.setType("text/*");
            intent.putExtra("android.intent.extra.SUBJECT", this.str_title);
            Uri parse = Uri.parse("whatsapp://send?text=Take a look at \"Resume Builder\" https://goo.gl/iGUBbf\n\n" + str + " \nTo create a best professional resume reflecting your skills and abilities, click here to download.\n\n https://goo.gl/iGUBbf");
            intent.setAction("android.intent.action.VIEW");
            intent.setData(parse);
            intent.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
            startActivity(intent);
            return;
        }
        Intent intent2 = new Intent();
        intent2.setAction("android.intent.action.SEND");
        intent2.putExtra("android.intent.extra.SUBJECT", this.str_title);
        intent2.putExtra("android.intent.extra.TEXT", "Take a look at \"Resume Builder\"https://goo.gl/iGUBbf\n\n" + str + " \nTo create a best professional resume reflecting your skills and abilities, click here to download. \n\n https://goo.gl/iGUBbf");
        intent2.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
        intent2.setType("text/*");
        startActivity(intent2);
    }

    @Override // android.support.v4.app.FragmentActivity
    public void onBackPressed() {
        super.onBackPressed();
        if (this.sharedPreference.getInt(this, "Noti_add") == 1) {
            if (this.interstitialAd_noti.isLoaded()) {
                this.interstitialAd_noti.show();
                this.interstitialAd_noti.setAdListener(new AdListener() {
                    /* class nithra.resume.maker.cv.builder.app.Fcm.ST_Activity.AnonymousClass5 */

                    @Override // com.google.android.gms.ads.AdListener
                    public void onAdClosed() {
                        Intent intent = new Intent(ST_Activity.this, MainActivity.class);
                        ST_Activity.this.finish();
                        ST_Activity.this.startActivity(intent);
                    }
                });
                return;
            }
            this.sharedPreference.putInt(getApplicationContext(), "Noti_add", 0);
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);
        } else if (this.interstitialAd_noti.isLoaded()) {
            this.interstitialAd_noti.show();
            this.interstitialAd_noti.setAdListener(new AdListener() {
                /* class nithra.resume.maker.cv.builder.app.Fcm.ST_Activity.AnonymousClass6 */

                @Override // com.google.android.gms.ads.AdListener
                public void onAdClosed() {
                    ST_Activity.this.finish();
                }
            });
        } else {
            finish();
        }
    }

    public void adds(final LinearLayout linearLayout) {
        AdView adView = new AdView(this);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        adView.setAdSize(AdSize.SMART_BANNER);
        try {
            linearLayout.removeAllViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
        linearLayout.addView(adView);
        adView.setAdListener(new AdListener() {
            /* class nithra.resume.maker.cv.builder.app.Fcm.ST_Activity.AnonymousClass7 */

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                linearLayout.setVisibility(View.VISIBLE);
            }
        });
        adView.loadAd(new AdRequest.Builder().build());
    }



    private List<ResolveInfo> showAllShareApp() {
        new ArrayList();
        Intent intent = new Intent("android.intent.action.SEND", (Uri) null);
        intent.putExtra("android.intent.extra.TEXT", "This is my text to send.");
        intent.setType("text/plain");
        return getPackageManager().queryIntentActivities(intent, 0);
    }

    static class ViewHolder {
        ImageView ivLogo;
        TextView tvAppName;
        TextView tvPackageName;

        ViewHolder() {
        }
    }

    class MyAdapter extends BaseAdapter {
        PackageManager pm;

        public long getItemId(int i) {
            return 0;
        }

        public MyAdapter() {
            this.pm = ST_Activity.this.getPackageManager();
        }

        public int getCount() {
            return ST_Activity.this.listApp.size();
        }

        public Object getItem(int i) {
            return ST_Activity.this.listApp.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = LayoutInflater.from(ST_Activity.this).inflate(R.layout.layout_share_app, viewGroup, false);
                viewHolder.ivLogo = (ImageView) view2.findViewById(R.id.iv_logo);
                viewHolder.tvAppName = (TextView) view2.findViewById(R.id.tv_app_name);
                viewHolder.tvPackageName = (TextView) view2.findViewById(R.id.tv_app_package_name);
                view2.setTag(viewHolder);
            } else {
                view2 = view;
                viewHolder = (ViewHolder) view.getTag();
            }
            ResolveInfo resolveInfo = ST_Activity.this.listApp.get(i);
            viewHolder.ivLogo.setImageDrawable(resolveInfo.loadIcon(this.pm));
            viewHolder.tvAppName.setText(resolveInfo.loadLabel(this.pm));
            viewHolder.tvPackageName.setText(resolveInfo.activityInfo.packageName);
            return view2;
        }
    }
}
