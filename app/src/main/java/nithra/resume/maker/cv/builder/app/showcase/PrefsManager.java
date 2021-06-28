package nithra.resume.maker.cv.builder.app.showcase;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsManager {
    private static final String PREFS_NAME = "material_showcaseview_prefs";
    public static int SEQUENCE_FINISHED = -1;
    public static int SEQUENCE_NEVER_STARTED = 0;
    private static final String STATUS = "status_";
    private Context context;
    private String showcaseID = null;

    public PrefsManager(Context context2, String str) {
        this.context = context2;
        this.showcaseID = str;
    }

    static void resetShowcase(Context context2, String str) {
        SharedPreferences.Editor edit = context2.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putInt(STATUS + str, SEQUENCE_NEVER_STARTED).apply();
    }

    public static void resetAll(Context context2) {
        context2.getSharedPreferences(PREFS_NAME, 0).edit().clear().apply();
    }


    public boolean hasFired() {
        return getSequenceStatus() == SEQUENCE_FINISHED;
    }


    public void setFired() {
        setSequenceStatus(SEQUENCE_FINISHED);
    }


    public int getSequenceStatus() {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getInt(STATUS + this.showcaseID, SEQUENCE_NEVER_STARTED);
    }


    public void setSequenceStatus(int i) {
        SharedPreferences.Editor edit = this.context.getSharedPreferences(PREFS_NAME, 0).edit();
        edit.putInt(STATUS + this.showcaseID, i).apply();
    }

    public void resetShowcase() {
        resetShowcase(this.context, this.showcaseID);
    }

    public void close() {
        this.context = null;
    }
}
