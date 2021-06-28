package nithra.resume.maker.cv.builder.app;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    public static final String PREFS_NAME = "level";
    SharedPreferences.Editor editor;
    SharedPreferences prefrence;

    public void putString(Context context, String str, String str2) {
        this.prefrence = context.getSharedPreferences("level", 0);
        this.editor = this.prefrence.edit();
        this.editor.putString(str, str2);
        this.editor.commit();
    }

    public String getString(Context context, String str) {
        this.prefrence = context.getSharedPreferences("level", 0);
        return this.prefrence.getString(str, "");
    }

    public void removeString(Context context, String str) {
        this.prefrence = context.getSharedPreferences("level", 0);
        this.editor = this.prefrence.edit();
        this.editor.remove(str);
        this.editor.commit();
    }

    public int putInt(Context context, String str, int i) {
        this.prefrence = context.getSharedPreferences("level", 0);
        this.editor = this.prefrence.edit();
        this.editor.putInt(str, i);
        this.editor.commit();
        return i;
    }

    public int getInt(Context context, String str) {
        this.prefrence = context.getSharedPreferences("level", 0);
        return this.prefrence.getInt(str, 0);
    }

    public void removeInt(Context context, String str) {
        this.prefrence = context.getSharedPreferences("level", 0);
        this.editor = this.prefrence.edit();
        this.editor.remove(str);
        this.editor.commit();
    }

    public void putBoolean(Context context, String str, Boolean bool) {
        this.prefrence = context.getSharedPreferences("level", 0);
        this.editor = this.prefrence.edit();
        this.editor.putBoolean(str, bool.booleanValue());
        this.editor.commit();
    }

    public Boolean getBoolean(Context context, String str) {
        this.prefrence = context.getSharedPreferences("level", 0);
        return Boolean.valueOf(this.prefrence.getBoolean(str, false));
    }

    public void removeBoolean(Context context, String str) {
        this.prefrence = context.getSharedPreferences("level", 0);
        this.editor = this.prefrence.edit();
        this.editor.remove(str);
        this.editor.commit();
    }

    public void clearSharedPreference(Context context) {
        this.prefrence = context.getSharedPreferences("level", 0);
        this.editor = this.prefrence.edit();
        this.editor.clear();
        this.editor.commit();
    }
}
