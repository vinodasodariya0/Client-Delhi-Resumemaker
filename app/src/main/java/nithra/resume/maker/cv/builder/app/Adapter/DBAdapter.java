package nithra.resume.maker.cv.builder.app.Adapter;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.io.IOException;
import nithra.resume.maker.cv.builder.app.DataBaseHelper;

public class DBAdapter {
    protected static final String TAG = "DataAdapter";
    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper ;

    public DBAdapter(Context context) {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(this.mContext);
    }

    public DBAdapter createDatabase() throws SQLException {
        try {
            this.mDbHelper.createDataBase();
            return this;
        } catch (IOException e) {
            Log.e(TAG, e.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
    }

    public DBAdapter open() throws SQLException {
        try {
            this.mDbHelper.openDataBase();
            this.mDbHelper.close();
            this.mDb = this.mDbHelper.getReadableDatabase();
            return this;
        } catch (SQLException e) {
            Log.e(TAG, "open >>" + e.toString());
            throw e;
        }
    }

    public void close() {
        this.mDbHelper.close();
    }
}
