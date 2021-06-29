package nithra.resume.maker.cv.builder.app.Fcm;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import nithra.resume.maker.cv.builder.app.MainActivity;
import nithra.resume.maker.cv.builder.app.SharedPreference;
import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MessagingService";
    String bmmm;
    int iddd;
    int isclose = 0;
    String msgg;
    SQLiteDatabase myDB;
    private NotificationHelper noti;
    SharedPreference sharedPreference;
    String titt;

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {



        String str = TAG;
        Log.e(str, "From: " + remoteMessage.getFrom());
        this.myDB = openOrCreateDatabase("RESUME_BUILDER", 0, null);
        this.noti = new NotificationHelper(this);
        SQLiteDatabase sQLiteDatabase = this.myDB;
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + "noti_cal" + " (id integer NOT NULL PRIMARY KEY AUTOINCREMENT,title VARCHAR,message VARCHAR,date VARCHAR,time VARCHAR,isclose INT(4),isshow INT(4) default 0,type VARCHAR,bm VARCHAR,ntype VARCHAR,url VARCHAR);");
        this.sharedPreference = new SharedPreference();
        if (remoteMessage.getData().size() > 0) {
            try {
                Log.e("Data Payload: ", remoteMessage.getData().toString());
                JSONObject jSONObject = new JSONObject(remoteMessage.getData());
                Log.e("JSON_OBJECT", jSONObject.toString());
                handleDataMessage(jSONObject);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("Exception: ", e.getMessage());
            }
        }
    }

    private void handleDataMessage(JSONObject jSONObject) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        try {
            String string = jSONObject.getString("message");
            String string2 = jSONObject.getString("title");
            String string3 = jSONObject.getString("date");
            String string4 = jSONObject.getString("time");
            String string5 = jSONObject.getString("type");
            String string6 = jSONObject.getString("bm");
            String string7 = jSONObject.getString("ntype");
            String string8 = jSONObject.getString(ImagesContract.URL);
            String string9 = jSONObject.getString("pac");
            int currentTimeMillis = (int) System.currentTimeMillis();
            if (!this.sharedPreference.getString(getApplicationContext(), "old_msg").equals(string) || !this.sharedPreference.getString(getApplicationContext(), "old_tit").equals(string2)) {
                this.sharedPreference.putString(getApplicationContext(), "old_msg", string);
                this.sharedPreference.putString(getApplicationContext(), "old_tit", string2);
                try {
                    string5.equals("s");
                } catch (Exception unused) {
                    string5 = "s";
                }
                try {
                    string9.equals("s");
                } catch (Exception unused2) {
                    string9 = "no";
                }
                try {
                    string6.equals("s");
                } catch (Exception unused3) {
                    string6 = "no";
                }
                try {
                    string7.equals("s");
                } catch (Exception unused4) {
                    string7 = "no";
                }
                try {
                    string2 = URLDecoder.decode(string2, Key.STRING_CHARSET_NAME);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (string5.equals("s")) {
                    try {
                        str10 = URLDecoder.decode(string6, Key.STRING_CHARSET_NAME);
                    } catch (UnsupportedEncodingException e2) {
                        e2.printStackTrace();
                        str10 = string6;
                    }
                    SQLiteDatabase sQLiteDatabase = this.myDB;
                    sQLiteDatabase.execSQL("INSERT INTO noti_cal(title,message,date,time,isclose,type,bm,ntype,url) values ('" + string2 + "','" + string + "','" + string3 + "','" + string4 + "','" + this.isclose + "','s','" + str10 + "','" + string7 + "','" + string8 + "');");
                    this.sharedPreference.putInt(this, "typee", 0);
                    Cursor rawQuery = this.myDB.rawQuery("select id from noti_cal", null);
                    rawQuery.moveToLast();
                    this.iddd = rawQuery.getInt(0);
                    this.myDB.close();
                    if (this.sharedPreference.getBoolean(this, "notificationstatus").booleanValue()) {
                        return;
                    }
                    if (string7.equals("bt")) {
                        this.noti.Notification_custom(this.iddd, string2, string, string8, "bt", str10, ST_Activity.class);
                    } else if (string7.equals("bi")) {
                        this.noti.Notification_custom(this.iddd, string2, string, string8, "bi", str10, ST_Activity.class);
                    } else {
                        this.noti.Notification_bm(this.iddd, string2, string, string8, "bt", str10, ST_Activity.class);
                    }
                } else if (string5.equals("h")) {
                    this.msgg = string;
                    this.titt = string2;
                    try {
                        str9 = URLDecoder.decode(string6, Key.STRING_CHARSET_NAME);
                    } catch (UnsupportedEncodingException e3) {
                        e3.printStackTrace();
                        str9 = string6;
                    }
                    this.bmmm = str9;
                    if (this.sharedPreference.getBoolean(this, "notificationstatus").booleanValue()) {
                        return;
                    }
                    if (string7.equals("bt")) {
                        this.noti.Notification_custom(0, string2, string, string8, "bt", str9, MainActivity.class);
                    } else if (string7.equals("bi")) {
                        this.noti.Notification_custom(0, string2, string, string8, "bi", str9, MainActivity.class);
                    }
                } else if (string5.equals("st")) {
                    this.msgg = string;
                    this.titt = string2;
                    try {
                        str8 = URLDecoder.decode(string6, Key.STRING_CHARSET_NAME);
                    } catch (UnsupportedEncodingException e4) {
                        e4.printStackTrace();
                        str8 = string6;
                    }
                    this.bmmm = str8;
                    SQLiteDatabase sQLiteDatabase2 = this.myDB;
                    sQLiteDatabase2.execSQL("INSERT INTO noti_cal(title,message,date,time,isclose,type,bm,ntype,url) values ('" + string2 + "','" + string + "','" + string3 + "','" + string4 + "','" + this.isclose + "','s','" + str8 + "','" + string7 + "','" + string8 + "');");
                    this.sharedPreference.putInt(this, "typee", 0);
                    Cursor rawQuery2 = this.myDB.rawQuery("select id from noti_cal", null);
                    rawQuery2.moveToLast();
                    this.iddd = rawQuery2.getInt(0);
                    this.myDB.rawQuery("select id from noti_cal where isclose = '0'", null).getCount();
                    this.myDB.close();
                    if (this.sharedPreference.getBoolean(this, "notificationstatus").booleanValue()) {
                        return;
                    }
                    if (string7.equals("bt")) {
                        this.noti.Notification_bm(this.iddd, string2, string, string8, "bt", str8, ST_Activity.class);
                    } else if (string7.equals("bi")) {
                        this.noti.Notification_bm(this.iddd, string2, string, string8, "bi", str8, ST_Activity.class);
                    } else {
                        this.noti.Notification_bm(this.iddd, string2, string, string8, "bt", str8, ST_Activity.class);
                    }
                } else if (string5.equals("w")) {
                    this.msgg = string;
                    this.titt = string2;
                    try {
                        str7 = URLDecoder.decode(string6, Key.STRING_CHARSET_NAME);
                    } catch (UnsupportedEncodingException e5) {
                        e5.printStackTrace();
                        str7 = string6;
                    }
                    this.bmmm = str7;
                    SQLiteDatabase sQLiteDatabase3 = this.myDB;
                    sQLiteDatabase3.execSQL("INSERT INTO noti_cal(title,message,date,time,isclose,type,bm,ntype,url) values ('" + string2 + "','" + string + "','" + string3 + "','" + string4 + "','" + this.isclose + "','w','" + str7 + "','" + string7 + "','" + string8 + "');");
                    this.sharedPreference.putInt(this, "typee", 0);
                    Cursor rawQuery3 = this.myDB.rawQuery("select id from noti_cal", null);
                    rawQuery3.moveToLast();
                    this.iddd = rawQuery3.getInt(0);
                    this.myDB.rawQuery("select id from noti_cal where isclose = '0'", null).getCount();
                    this.myDB.close();
                    if (this.sharedPreference.getBoolean(this, "notificationstatus").booleanValue()) {
                        return;
                    }
                    if (string7.equals("bt")) {
                        this.noti.Notification_custom(this.iddd, string2, string, string8, "bt", str7, ST_Activity.class);
                    } else if (string7.equals("bi")) {
                        this.noti.Notification_custom(this.iddd, string2, string, string8, "bi", str7, ST_Activity.class);
                    } else {
                        this.noti.Notification_bm(this.iddd, string2, string, string8, "bt", str7, ST_Activity.class);
                    }
                } else if (string5.equals("ns")) {
                    SQLiteDatabase sQLiteDatabase4 = this.myDB;
                    sQLiteDatabase4.execSQL("INSERT INTO noti_cal(title,message,date,time,isclose,type,bm,ntype,url) values ('" + string2 + "','" + string + "','" + string3 + "','" + string4 + "','" + this.isclose + "','ns','" + string6 + "','" + string7 + "','" + string8 + "');");
                    Cursor rawQuery4 = this.myDB.rawQuery("select id from noti_cal", null);
                    rawQuery4.moveToLast();
                    this.iddd = rawQuery4.getInt(0);
                    try {
                        string6 = URLDecoder.decode(string6, Key.STRING_CHARSET_NAME);
                    } catch (UnsupportedEncodingException e6) {
                        e6.printStackTrace();
                    }
                    this.bmmm = string6;
                    this.myDB.rawQuery("select id from noti_cal where isclose = '0'", null).getCount();
                    this.myDB.close();
                } else if (string5.equals("ins")) {
                    try {
                        str6 = URLDecoder.decode(string6, Key.STRING_CHARSET_NAME);
                    } catch (UnsupportedEncodingException e7) {
                        e7.printStackTrace();
                        str6 = string6;
                    }
                    if (!this.sharedPreference.getBoolean(this, "notificationstatus").booleanValue()) {
                        this.noti.Notification1(currentTimeMillis, string2, string, string8, "bi", str6, ST_Activity.class);
                    }
                } else if (string5.equals("ap")) {
                    if (!appInstalledOrNot(string9)) {
                        this.msgg = string;
                        this.titt = string2;
                        if (string7.equals("n")) {
                            try {
                                str5 = URLDecoder.decode(string6, Key.STRING_CHARSET_NAME);
                            } catch (UnsupportedEncodingException e8) {
                                e8.printStackTrace();
                                str5 = string6;
                            }
                            this.noti.Notification1(currentTimeMillis, string2, string, string8, "bt", str5, ST_Activity.class);
                        } else if (string7.equals("bt")) {
                            try {
                                str4 = URLDecoder.decode(string6, Key.STRING_CHARSET_NAME);
                            } catch (UnsupportedEncodingException e9) {
                                e9.printStackTrace();
                                str4 = string6;
                            }
                            this.noti.Notification1(currentTimeMillis, string2, string, string8, "bt", str4, ST_Activity.class);
                        } else if (string7.equals("bi")) {
                            try {
                                str3 = URLDecoder.decode(string6, Key.STRING_CHARSET_NAME);
                            } catch (UnsupportedEncodingException e10) {
                                e10.printStackTrace();
                                str3 = string6;
                            }
                            this.noti.Notification1(currentTimeMillis, string2, string, string8, "bi", str3, ST_Activity.class);
                        } else if (string7.equals("w")) {
                            try {
                                str2 = URLDecoder.decode(string6, Key.STRING_CHARSET_NAME);
                            } catch (UnsupportedEncodingException e11) {
                                e11.printStackTrace();
                                str2 = string6;
                            }
                            this.noti.Notification1(currentTimeMillis, string2, string, string8, "bi", str2, ST_Activity.class);
                        }
                    }
                } else if (string5.equals("u")) {
                    this.sharedPreference.putInt(this, "gcmvcode", Integer.parseInt(string));
                    this.sharedPreference.putInt(this, "isvupdate", 1);
                } else if (string5.equals("rao")) {
                    this.msgg = string;
                    this.titt = string2;
                    this.bmmm = string6;
                    try {
                        str = URLDecoder.decode(string6, Key.STRING_CHARSET_NAME);
                    } catch (UnsupportedEncodingException e12) {
                        e12.printStackTrace();
                        str = string6;
                    }
                    if (this.sharedPreference.getBoolean(this, "notificationstatus").booleanValue()) {
                        return;
                    }
                    if (string7.equals("bt")) {
                        this.noti.Notification_custom1(string5, 0, string2, string, string8, "bt", str, MainActivity.class);
                    } else if (string7.equals("bi")) {
                        this.noti.Notification_custom1(string5, 0, string2, string, string8, "bi", str, MainActivity.class);
                    }
                }
            }
        } catch (JSONException e13) {
            e13.printStackTrace();
        }
    }

    private boolean appInstalledOrNot(String str) {
        try {
            getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
