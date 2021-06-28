package nithra.resume.maker.cv.builder.app.Fcm;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;




import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import nithra.resume.maker.cv.builder.app.Fcm.TextDrawable;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.SharedPreference;
import nithra.resume.maker.cv.builder.app.Utils;

public class Noti_Fragment extends AppCompatActivity {
    int[] Id;
    private Menu _menu = null;
    CustomAdapter adapter;
    LinearLayout ads_lay;
    int[] ads_view;
    String[] bm;
    boolean[] checkBoxState;
    String checkk_val = "";
    Boolean chk_all = false;
    Boolean chk_val = false;
    int chkd_val = 0;
    LayoutInflater inflater;
    int[] isclose;
    int[] ismarkk;
    ListView listView;
    Boolean long_val = false;
    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    String[] message;
    String[] msgTime;
    String[] msgType;
    SQLiteDatabase myDB;
    String[] ntype;
    ArrayList<HashMap<String, Object>> players;
    SharedPreference sharedPreference;
    String tablenew = "noti_cal";
    String[] title;
    RelativeLayout txtNoNotification;
    String[] urll;
    int val = 0;

    public static String getFriendlyTime(Date date, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        long time = (Calendar.getInstance().getTime().getTime() - date.getTime()) / 1000;
        if (time >= 60) {
            long j = time % 60;
        }
        long j2 = time / 60;
        long j3 = j2 >= 60 ? j2 % 60 : j2;
        long j4 = j2 / 60;
        long j5 = j4 >= 24 ? j4 % 24 : j4;
        long j6 = j4 / 24;
        long j7 = j6 >= 30 ? j6 % 30 : j6;
        long j8 = j6 / 30;
        long j9 = j8 >= 12 ? j8 % 12 : j8;
        long j10 = j8 / 12;
        if (j10 > 0) {
            if (j10 == 1) {
                stringBuffer.append("one year ago");
            } else {
                stringBuffer.append(j10 + "years ago");
            }
        } else if (j9 > 0) {
            if (j9 == 1) {
                stringBuffer.append("one month ago");
            } else {
                stringBuffer.append(j9 + " months ago");
            }
        } else if (j7 > 0) {
            if (j7 == 1) {
                stringBuffer.append(" before one day");
            } else {
                stringBuffer.append(j7 + " before one day");
            }
        } else if (j5 > 0) {
            stringBuffer.append(" Today" + time_convert(str));
        } else if (j3 > 0) {
            stringBuffer.append(" Today" + time_convert(str));
        } else {
            stringBuffer.append(" Today" + time_convert(str));
        }
        return stringBuffer.toString();
    }

    public static String time_convert(String str) {
        String[] split = str.replaceAll(" ", "").replaceAll("am", "").replaceAll("pm", "").split("\\:");
        return "" + Utils.am_pm1(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }


    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.SupportActivity, android.support.v4.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.noti_view);
        this.inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.sharedPreference = new SharedPreference();
        this.myDB = openOrCreateDatabase("RESUME_BUILDER", 0, null);
        SQLiteDatabase sQLiteDatabase = this.myDB;
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + this.tablenew + " (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,message VARCHAR,date VARCHAR,time VARCHAR,isclose INT(4),isshow INT(4) default 0,type VARCHAR,bm VARCHAR,ntype VARCHAR,url VARCHAR);");
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                Noti_Fragment.this.finish();
            }
        });
        toolbar.setTitle("Notification");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notification");
        this.txtNoNotification = (RelativeLayout) findViewById(R.id.txtNoNotification);
        this.ads_lay = (LinearLayout) findViewById(R.id.ads_lay);
        this.listView = (ListView) findViewById(R.id.listView1);
        setada();
        adds(this.ads_lay);
    }

    public void delet_fun(final String str, final int i) {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.nodate_dia);
        dialog.getWindow().setLayout(-1, -1);
        Button button = (Button) dialog.findViewById(R.id.btnSet);
        Button button2 = (Button) dialog.findViewById(R.id.btnok);
        AppCompatTextView appCompatTextView = (AppCompatTextView) dialog.findViewById(R.id.editText1);
        button.setText("YES");
        button2.setText("NO");
        ((AppCompatTextView) dialog.findViewById(R.id.head_txt)).setVisibility(View.GONE);
        if (i == 0) {
            appCompatTextView.setText("Do you want delete selected items?");
        } else {
            appCompatTextView.setText("Do you want delete selected items?");
        }
        button.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                SharedPreference sharedPreference = Noti_Fragment.this.sharedPreference;
                Noti_Fragment noti_Fragment = Noti_Fragment.this;
                sharedPreference.removeString(noti_Fragment, "imgURL" + str);
                if (i == 0) {
                    SQLiteDatabase sQLiteDatabase = Noti_Fragment.this.myDB;
                    sQLiteDatabase.execSQL("delete from noti_cal where " + str.substring(4) + "");
                } else {
                    Noti_Fragment.this.myDB.execSQL("delete from noti_cal ");
                }
                MenuItem findItem = Noti_Fragment.this._menu.findItem(R.id.action_delete);
                MenuItem findItem2 = Noti_Fragment.this._menu.findItem(R.id.action_refresh);
                MenuItem findItem3 = Noti_Fragment.this._menu.findItem(R.id.action_no);
                MenuItem findItem4 = Noti_Fragment.this._menu.findItem(R.id.action_all);
                findItem.setVisible(false);
                findItem3.setVisible(false);
                findItem4.setVisible(false);
                findItem2.setVisible(true);
                Noti_Fragment.this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                Noti_Fragment.this.getSupportActionBar().setDisplayShowHomeEnabled(false);
                Noti_Fragment noti_Fragment2 = Noti_Fragment.this;
                noti_Fragment2.checkk_val = "";
                noti_Fragment2.chk_val = false;
                Noti_Fragment.this.long_val = false;
                Noti_Fragment.this.chk_all = false;
                Noti_Fragment noti_Fragment3 = Noti_Fragment.this;
                noti_Fragment3.chkd_val = 0;
                noti_Fragment3.setada();
                dialog.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void setada() {
        SQLiteDatabase sQLiteDatabase = this.myDB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("select * from " + this.tablenew + " order by id desc ", null);
        if (rawQuery.getCount() == 0) {
            this.txtNoNotification.setVisibility(View.VISIBLE);
            this.listView.setVisibility(View.GONE);
            this.val = 1;
        } else {
            this.val = 0;
            this.txtNoNotification.setVisibility(View.GONE);
            this.players = new ArrayList<>();
            this.Id = new int[rawQuery.getCount()];
            this.ismarkk = new int[rawQuery.getCount()];
            this.isclose = new int[rawQuery.getCount()];
            this.title = new String[rawQuery.getCount()];
            this.message = new String[rawQuery.getCount()];
            this.msgType = new String[rawQuery.getCount()];
            this.msgTime = new String[rawQuery.getCount()];
            this.bm = new String[rawQuery.getCount()];
            this.urll = new String[rawQuery.getCount()];
            this.ntype = new String[rawQuery.getCount()];
            this.ads_view = new int[rawQuery.getCount()];
            for (int i = 0; i < rawQuery.getCount(); i++) {
                rawQuery.moveToPosition(i);
                this.Id[i] = rawQuery.getInt(rawQuery.getColumnIndex("id"));
                this.ismarkk[i] = 0;
                this.title[i] = rawQuery.getString(rawQuery.getColumnIndex("title"));
                this.message[i] = rawQuery.getString(rawQuery.getColumnIndex("message"));
                this.msgType[i] = rawQuery.getString(rawQuery.getColumnIndex("type"));
                this.isclose[i] = rawQuery.getInt(rawQuery.getColumnIndex("isclose"));
                this.bm[i] = rawQuery.getString(rawQuery.getColumnIndex("bm"));
                this.urll[i] = rawQuery.getString(rawQuery.getColumnIndex(ImagesContract.URL));
                this.ntype[i] = rawQuery.getString(rawQuery.getColumnIndex("ntype"));
                String[] strArr = this.msgTime;
                strArr[i] = rawQuery.getString(rawQuery.getColumnIndex("date")) + "," + rawQuery.getString(rawQuery.getColumnIndex("time"));
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("idd", Integer.valueOf(this.Id[i]));
                hashMap.put("title", this.title[i]);
                hashMap.put("isclose", Integer.valueOf(this.isclose[i]));
                hashMap.put("msgTime", this.msgTime[i]);
                hashMap.put("message", this.message[i]);
                hashMap.put("bm", this.bm[i]);
                hashMap.put("msgType", this.msgType[i]);
                hashMap.put("ntype", this.ntype[i]);
                hashMap.put("urll", this.urll[i]);
                hashMap.put("ismarkk", Integer.valueOf(this.ismarkk[i]));
                this.players.add(hashMap);
            }
            this.checkBoxState = new boolean[this.players.size()];
            this.adapter = new CustomAdapter(this, R.layout.notify_item, this.players);
            this.listView.setAdapter((ListAdapter) this.adapter);
        }
        rawQuery.close();
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
            /* class nithra.resume.maker.cv.builder.app.Fcm.Noti_Fragment.AnonymousClass4 */

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                linearLayout.setVisibility(View.VISIBLE);
            }
        });
        adView.loadAd(new AdRequest.Builder().build());
    }

    public String convert_str(String str) {
        String str2;
        String str3;
        String[] split = str.split(":");
        int parseInt = Integer.parseInt(split[0]);
        String str4 = split[1];
        if (parseInt >= 12) {
            parseInt -= 12;
            str2 = "PM";
        } else {
            str2 = "AM";
        }
        if (parseInt == 0) {
            parseInt = 12;
        }
        String.valueOf(parseInt);
        if (String.valueOf(parseInt).length() == 1) {
            str3 = "0" + parseInt;
        } else {
            str3 = String.valueOf(parseInt);
        }
        return str3 + ":" + str4 + " " + str2;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this._menu = menu;
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                MenuItem findItem = this._menu.findItem(R.id.action_delete);
                MenuItem findItem2 = this._menu.findItem(R.id.action_refresh);
                MenuItem findItem3 = this._menu.findItem(R.id.action_all);
                MenuItem findItem4 = this._menu.findItem(R.id.action_no);
                findItem.setVisible(false);
                findItem3.setVisible(false);
                findItem4.setVisible(false);
                findItem2.setVisible(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
                this.checkk_val = "";
                this.chk_val = false;
                this.long_val = false;
                this.chkd_val = 0;
                this.chk_all = false;
                if (this.chk_val.booleanValue()) {
                    this.checkBoxState = new boolean[this.players.size()];
                    int i = 0;
                    while (i < this.players.size()) {
                        if (this.long_val.booleanValue()) {
                            this.checkBoxState[i] = this.chkd_val == i;
                        } else {
                            this.checkBoxState[i] = this.chk_all.booleanValue();
                        }
                        i++;
                    }
                } else {
                    this.checkBoxState = new boolean[this.players.size()];
                }
                this.adapter.notifyDataSetChanged();
                return true;
            case R.id.action_all /*{ENCODED_INT: 2131296295}*/:
                MenuItem findItem5 = this._menu.findItem(R.id.action_delete);
                MenuItem findItem6 = this._menu.findItem(R.id.action_refresh);
                MenuItem findItem7 = this._menu.findItem(R.id.action_all);
                MenuItem findItem8 = this._menu.findItem(R.id.action_no);
                findItem5.setVisible(true);
                findItem8.setVisible(true);
                findItem7.setVisible(false);
                findItem6.setVisible(false);
                this.checkk_val = "";
                for (int i2 = 0; i2 < this.Id.length; i2++) {
                    this.checkk_val += " or id='" + this.Id[i2] + "'";
                }
                this.chk_all = true;
                this.chk_val = true;
                this.long_val = false;
                this.checkBoxState = new boolean[this.players.size()];
                for (int i3 = 0; i3 < this.players.size(); i3++) {
                    this.checkBoxState[i3] = true;
                }
                this.adapter.notifyDataSetChanged();
                return true;
            case R.id.action_delete /*{ENCODED_INT: 2131296305}*/:
                if (!this.checkk_val.equals("")) {
                    if (this.chk_all.booleanValue()) {
                        delet_fun(this.checkk_val, 1);
                    } else {
                        delet_fun(this.checkk_val, 0);
                    }
                }
                return true;
            case R.id.action_no /*{ENCODED_INT: 2131296313}*/:
                MenuItem findItem9 = this._menu.findItem(R.id.action_delete);
                MenuItem findItem10 = this._menu.findItem(R.id.action_refresh);
                MenuItem findItem11 = this._menu.findItem(R.id.action_no);
                MenuItem findItem12 = this._menu.findItem(R.id.action_all);
                findItem9.setVisible(true);
                findItem12.setVisible(true);
                findItem11.setVisible(false);
                findItem10.setVisible(false);
                this.checkk_val = "";
                this.chk_all = false;
                this.chk_val = true;
                this.long_val = false;
                this.checkBoxState = new boolean[this.players.size()];
                for (int i4 = 0; i4 < this.players.size(); i4++) {
                    this.checkBoxState[i4] = false;
                }
                this.adapter.notifyDataSetChanged();
                return true;
            case R.id.action_refresh /*{ENCODED_INT: 2131296314}*/:
                if (this.val == 0) {
                    menuItem.setVisible(false);
                    MenuItem findItem13 = this._menu.findItem(R.id.action_delete);
                    MenuItem findItem14 = this._menu.findItem(R.id.action_all);
                    MenuItem findItem15 = this._menu.findItem(R.id.action_no);
                    findItem13.setVisible(true);
                    findItem14.setVisible(true);
                    findItem15.setVisible(false);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setDisplayShowHomeEnabled(true);
                    this.checkk_val = "";
                    this.chk_val = true;
                    this.long_val = false;
                    this.chkd_val = 0;
                    if (this.chk_val.booleanValue()) {
                        this.checkBoxState = new boolean[this.players.size()];
                        int i5 = 0;
                        while (i5 < this.players.size()) {
                            if (this.long_val.booleanValue()) {
                                this.checkBoxState[i5] = this.chkd_val == i5;
                            } else {
                                this.checkBoxState[i5] = this.chk_all.booleanValue();
                            }
                            i5++;
                        }
                    } else {
                        this.checkBoxState = new boolean[this.players.size()];
                    }
                    this.adapter.notifyDataSetChanged();
                }
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.chk_val.booleanValue()) {
            MenuItem findItem = this._menu.findItem(R.id.action_delete);
            MenuItem findItem2 = this._menu.findItem(R.id.action_refresh);
            MenuItem findItem3 = this._menu.findItem(R.id.action_all);
            MenuItem findItem4 = this._menu.findItem(R.id.action_no);
            findItem.setVisible(false);
            findItem3.setVisible(false);
            findItem4.setVisible(false);
            findItem2.setVisible(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            this.checkk_val = "";
            this.chk_val = false;
            this.long_val = false;
            this.chkd_val = 0;
            this.chk_all = false;
            if (this.chk_val.booleanValue()) {
                this.checkBoxState = new boolean[this.players.size()];
                int i2 = 0;
                while (i2 < this.players.size()) {
                    if (this.long_val.booleanValue()) {
                        this.checkBoxState[i2] = this.chkd_val == i2;
                    } else {
                        this.checkBoxState[i2] = this.chk_all.booleanValue();
                    }
                    i2++;
                }
            } else {
                this.checkBoxState = new boolean[this.players.size()];
            }
            this.adapter.notifyDataSetChanged();
        } else {
            finish();
        }
        return true;
    }


    public class CustomAdapter extends ArrayAdapter<HashMap<String, Object>> {
        ViewHolder viewHolder;

        public CustomAdapter(Context context, int i, ArrayList<HashMap<String, Object>> arrayList) {
            super(context, i, arrayList);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = Noti_Fragment.this.inflater.inflate(R.layout.notify_item, (ViewGroup) null);
                this.viewHolder = new ViewHolder();
                this.viewHolder.textView1 = (TextView) view.findViewById(R.id.textView1);
                this.viewHolder.time_txt = (TextView) view.findViewById(R.id.time_txt);
                this.viewHolder.cunt = (ImageView) view.findViewById(R.id.cunt);
                this.viewHolder.chbk = (AppCompatCheckBox) view.findViewById(R.id.checkk);
                view.setTag(this.viewHolder);
            } else {
                this.viewHolder = (ViewHolder) view.getTag();
            }
            if (!Noti_Fragment.this.chk_val.booleanValue()) {
                this.viewHolder.chbk.setVisibility(View.GONE);
            } else {
                this.viewHolder.chbk.setVisibility(View.VISIBLE);
            }
            int randomColor = Noti_Fragment.this.mColorGenerator.getRandomColor();
            TextDrawable.IShapeBuilder builder = TextDrawable.builder();
            this.viewHolder.cunt.setImageDrawable(builder.buildRoundRect("" + (i + 1), randomColor, 15));
            this.viewHolder.chbk.setChecked(Noti_Fragment.this.checkBoxState[i]);
            TextView textView = this.viewHolder.textView1;
            textView.setText("" + Noti_Fragment.this.players.get(i).get("bm"));
            String[] split = Noti_Fragment.this.players.get(i).get("msgTime").toString().split(",");
            String valueOf = String.valueOf(Noti_Fragment.this.convert_str(split[1]));
            Log.e("timeeee", split[1] + "hai" + valueOf);
            TextView textView2 = this.viewHolder.time_txt;
            textView2.setText("" + split[0] + "     " + valueOf);
            this.viewHolder.chbk.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    Noti_Fragment.this.chk_all = false;
                    MenuItem findItem = Noti_Fragment.this._menu.findItem(R.id.action_delete);
                    MenuItem findItem2 = Noti_Fragment.this._menu.findItem(R.id.action_refresh);
                    MenuItem findItem3 = Noti_Fragment.this._menu.findItem(R.id.action_all);
                    MenuItem findItem4 = Noti_Fragment.this._menu.findItem(R.id.action_no);
                    findItem.setVisible(true);
                    findItem4.setVisible(false);
                    findItem3.setVisible(true);
                    findItem2.setVisible(false);
                    if (((CheckBox) view).isChecked()) {
                        Noti_Fragment.this.checkBoxState[i] = true;
                        StringBuilder sb = new StringBuilder();
                        Noti_Fragment noti_Fragment = Noti_Fragment.this;
                        sb.append(noti_Fragment.checkk_val);
                        sb.append(" or id='");
                        sb.append(Noti_Fragment.this.players.get(i).get("idd"));
                        sb.append("'");
                        noti_Fragment.checkk_val = sb.toString();
                        return;
                    }
                    Noti_Fragment.this.checkBoxState[i] = false;
                    Noti_Fragment noti_Fragment2 = Noti_Fragment.this;
                    String str = Noti_Fragment.this.checkk_val;
                    noti_Fragment2.checkk_val = str.replace(" or id='" + Noti_Fragment.this.players.get(i).get("idd") + "'", "");
                }
            });
            if (Noti_Fragment.this.isclose[i] == 1) {
                view.setBackgroundColor(Color.parseColor("#FFFFFF"));
            } else {
                view.setBackgroundColor(Color.parseColor("#B2DFDB"));
            }
            view.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    Noti_Fragment.this.chk_all = false;
                    if (CustomAdapter.this.viewHolder.chbk.getVisibility() == View.VISIBLE) {
                        MenuItem findItem = Noti_Fragment.this._menu.findItem(R.id.action_delete);
                        MenuItem findItem2 = Noti_Fragment.this._menu.findItem(R.id.action_refresh);
                        MenuItem findItem3 = Noti_Fragment.this._menu.findItem(R.id.action_all);
                        MenuItem findItem4 = Noti_Fragment.this._menu.findItem(R.id.action_no);
                        findItem.setVisible(true);
                        findItem4.setVisible(false);
                        findItem3.setVisible(true);
                        findItem2.setVisible(false);
                        AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox) view.findViewById(R.id.checkk);
                        if (!appCompatCheckBox.isChecked()) {
                            appCompatCheckBox.setChecked(true);
                            Noti_Fragment.this.checkBoxState[i] = true;
                            StringBuilder sb = new StringBuilder();
                            Noti_Fragment noti_Fragment = Noti_Fragment.this;
                            sb.append(noti_Fragment.checkk_val);
                            sb.append(" or id='");
                            sb.append(Noti_Fragment.this.players.get(i).get("idd"));
                            sb.append("'");
                            noti_Fragment.checkk_val = sb.toString();
                            return;
                        }
                        appCompatCheckBox.setChecked(false);
                        Noti_Fragment.this.checkBoxState[i] = false;
                        Noti_Fragment noti_Fragment2 = Noti_Fragment.this;
                        String str = Noti_Fragment.this.checkk_val;
                        noti_Fragment2.checkk_val = str.replace(" or id='" + Noti_Fragment.this.players.get(i).get("idd") + "'", "");
                        return;
                    }
                    Noti_Fragment.this.isclose[i] = 1;
                    SQLiteDatabase sQLiteDatabase = Noti_Fragment.this.myDB;
                    sQLiteDatabase.execSQL("update " + Noti_Fragment.this.tablenew + " set isclose='1' where id='" + Noti_Fragment.this.players.get(i).get("idd") + "'");
                    Noti_Fragment.this.adapter.notifyDataSetChanged();
                    Intent intent = new Intent(Noti_Fragment.this, ST_Activity.class);
                    intent.putExtra("message", (String) Noti_Fragment.this.players.get(i).get("message"));
                    intent.putExtra("title", (String) Noti_Fragment.this.players.get(i).get("bm"));
                    intent.putExtra("idd", ((Integer) Noti_Fragment.this.players.get(i).get("idd")).intValue());
                    intent.putExtra("Noti_add", 0);
                    Noti_Fragment.this.startActivity(intent);
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {

                public boolean onLongClick(View view) {
                    MenuItem findItem = Noti_Fragment.this._menu.findItem(R.id.action_delete);
                    MenuItem findItem2 = Noti_Fragment.this._menu.findItem(R.id.action_refresh);
                    MenuItem findItem3 = Noti_Fragment.this._menu.findItem(R.id.action_all);
                    MenuItem findItem4 = Noti_Fragment.this._menu.findItem(R.id.action_no);
                    findItem.setVisible(true);
                    findItem3.setVisible(true);
                    findItem2.setVisible(false);
                    findItem4.setVisible(false);
                    Noti_Fragment.this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    Noti_Fragment.this.getSupportActionBar().setDisplayShowHomeEnabled(true);
                    StringBuilder sb = new StringBuilder();
                    Noti_Fragment noti_Fragment = Noti_Fragment.this;
                    sb.append(noti_Fragment.checkk_val);
                    sb.append(" or id='");
                    sb.append(Noti_Fragment.this.players.get(i).get("idd"));
                    sb.append("'");
                    noti_Fragment.checkk_val = sb.toString();
                    Noti_Fragment.this.chk_val = true;
                    Noti_Fragment.this.long_val = true;
                    Noti_Fragment.this.chk_all = false;
                    Noti_Fragment.this.chkd_val = i;
                    Noti_Fragment.this.checkBoxState[i] = true;
                    if (Noti_Fragment.this.chk_val.booleanValue()) {
                        Noti_Fragment.this.checkBoxState = new boolean[Noti_Fragment.this.players.size()];
                        int i = 0;
                        while (i < Noti_Fragment.this.players.size()) {
                            if (Noti_Fragment.this.long_val.booleanValue()) {
                                Noti_Fragment.this.checkBoxState[i] = Noti_Fragment.this.chkd_val == i;
                            } else {
                                Noti_Fragment.this.checkBoxState[i] = Noti_Fragment.this.chk_all.booleanValue();
                            }
                            i++;
                        }
                    } else {
                        Noti_Fragment.this.checkBoxState = new boolean[Noti_Fragment.this.players.size()];
                    }
                    Noti_Fragment.this.adapter.notifyDataSetChanged();
                    return false;
                }
            });
            return view;
        }

        private class ViewHolder {
            AppCompatCheckBox chbk;
            ImageView cunt;
            TextView textView1;
            TextView time_txt;

            private ViewHolder() {
            }
        }
    }
}
