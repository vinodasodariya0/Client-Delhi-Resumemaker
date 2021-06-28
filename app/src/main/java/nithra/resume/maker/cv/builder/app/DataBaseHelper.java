package nithra.resume.maker.cv.builder.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "RESUME_BUILDER.db";
    private static String DB_PATH = "";
    private static String TAG = "DataBaseHelper";
    Cursor c;
    private final Context mContext;
    private SQLiteDatabase mDataBase;
    SharedPreferences mPreferences;

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        if (Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        PrintStream printStream = System.out;
        printStream.println("check " + DB_PATH);
        this.mContext = context;
    }

    public Cursor getQry(String str) {
        this.c = getReadableDatabase().rawQuery(str, null);
        return this.c;
    }

    public void executeSql(String str) {
        getReadableDatabase().execSQL(str);
    }

    public void createDataBase() throws IOException {
        if (checkDataBase()) {
            new File(DB_PATH + DB_NAME).delete();
        }
        getReadableDatabase();
        getWritableDatabase();
        close();
        try {
            copyDataBase();
            Log.e(TAG, "createDatabase database created");
        } catch (IOException unused) {
            throw new Error("ErrorCopyingDataBase");
        }
    }

    public boolean checkDataBase() {
        File file = new File(DB_PATH + DB_NAME);
        PrintStream printStream = System.out;
        printStream.println("check val 1 " + file);
        return file.exists();
    }

    public void copyDataBase() throws IOException {
        this.mContext.openOrCreateDatabase(DB_NAME, 0, null).close();
        String file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
        FileInputStream fileInputStream = new FileInputStream(new File(new File(file + "/Nithra/ResumeBuilder/"), "RESUME_BUILDER.db"));
        FileOutputStream fileOutputStream = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read > 0) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileOutputStream.flush();
                fileOutputStream.close();
                fileInputStream.close();
                return;
            }
        }
    }

    public boolean openDataBase() throws SQLException {
        try {
            this.mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        } catch (Exception e) {
            PrintStream printStream = System.out;
            printStream.println(" Error 2e" + e);
        }
        return this.mDataBase != null;
    }

    @Override // java.lang.AutoCloseable
    public synchronized void close() {
        if (this.mDataBase != null) {
            this.mDataBase.close();
        }
        super.close();
    }
}
