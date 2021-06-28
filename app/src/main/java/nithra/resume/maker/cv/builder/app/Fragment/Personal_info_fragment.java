package nithra.resume.maker.cv.builder.app.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;







import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.measurement.api.AppMeasurementSdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import nithra.resume.maker.cv.builder.app.MainActivity;
import nithra.resume.maker.cv.builder.app.Models.DB_Items;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.SharedPreference;
import nithra.resume.maker.cv.builder.app.Touch.ItemTouchHelperAdapter;
import nithra.resume.maker.cv.builder.app.Touch.ItemTouchHelperViewHolder;
import nithra.resume.maker.cv.builder.app.Touch.OnStartDragListener;
import nithra.resume.maker.cv.builder.app.Touch.SimpleItemTouchHelperCallback;
import nithra.resume.maker.cv.builder.app.Utils;
import nithra.resume.maker.cv.builder.app.Visible;
import nithra.resume.maker.cv.builder.app.showcase.MaterialShowcaseSequence;
import nithra.resume.maker.cv.builder.app.showcase.MaterialShowcaseView;
import nithra.resume.maker.cv.builder.app.showcase.ShowcaseConfig;

public class Personal_info_fragment extends Fragment implements OnStartDragListener, Visible {
    static Activity context;
    static List<DB_Items> get_values = new ArrayList();
    static Add_ListAdapter mAdapter1;
    SQLiteDatabase DB;
    String SHOWCASE_ID = "Personal";
    List<String> Values = new ArrayList();
    CardView add_view;
    ImageView correct;
    LinearLayout edit;
    Dialog exit_dialog;
    LinearLayout linear;
    Personal_ListAdapter mAdapter;
    ItemTouchHelper mItemTouchHelper;
    List<DB_Items> more_data = new ArrayList();
    ExpandableHeightRecyclerView more_recycle;
    ImageView name_edit;
    AppCompatEditText personal_address;
    AppCompatEditText personal_email;
    AppCompatEditText personal_lang;
    AppCompatEditText personal_marital;
    AppCompatEditText personal_name;
    AppCompatEditText personal_phone;
    Button personal_save;
    AppCompatTextView personal_title;
    ScrollView scroll;
    SharedPreference sp = new SharedPreference();
    int table_id;
    AppCompatTextView title;
    AppCompatEditText title_per;
    LinearLayout top;
    AppCompatEditText value;

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        String string;
        context = getActivity();
        getActivity().getWindow().setSoftInputMode(3);
        View inflate = layoutInflater.inflate(R.layout.fragment_personalview, viewGroup, false);
        this.name_edit = (ImageView) inflate.findViewById(R.id.name_edit);
        this.edit = (LinearLayout) inflate.findViewById(R.id.edit);
        this.correct = (ImageView) inflate.findViewById(R.id.correct);
        this.title_per = (AppCompatEditText) inflate.findViewById(R.id.title_per);
        this.personal_title = (AppCompatTextView) inflate.findViewById(R.id.personal_title);
        this.linear = (LinearLayout) inflate.findViewById(R.id.linear);
        this.add_view = (CardView) inflate.findViewById(R.id.add_view);
        this.scroll = (ScrollView) inflate.findViewById(R.id.scroll);
        this.personal_save = (Button) inflate.findViewById(R.id.personal_save);
        this.personal_name = (AppCompatEditText) inflate.findViewById(R.id.personal_name);
        this.personal_address = (AppCompatEditText) inflate.findViewById(R.id.personal_address);
        this.personal_email = (AppCompatEditText) inflate.findViewById(R.id.personal_email);
        this.personal_phone = (AppCompatEditText) inflate.findViewById(R.id.personal_phone);
        this.personal_lang = (AppCompatEditText) inflate.findViewById(R.id.personal_lang);
        this.personal_marital = (AppCompatEditText) inflate.findViewById(R.id.personal_marital);
        this.more_recycle = (ExpandableHeightRecyclerView) inflate.findViewById(R.id.more_recycle);
        this.top = (LinearLayout) inflate.findViewById(R.id.top);
        this.more_recycle.setHasFixedSize(true);
        this.more_data.clear();
        this.more_recycle.setExpanded(true);
        this.more_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        this.mAdapter = new Personal_ListAdapter(context, this, this.more_data);
        this.more_recycle.setAdapter(this.mAdapter);
        this.mItemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.mAdapter));
        this.mItemTouchHelper.attachToRecyclerView(this.more_recycle);
        this.DB = context.openOrCreateDatabase("RESUME_BUILDER", 0, null);
        this.Values.add(0, "Father Name");
        this.Values.add(1, "Mother Name");
        this.Values.add(2, "Date of Birth");
        this.Values.add(3, "Gender");
        this.Values.add(4, "Marital Status");
        this.Values.add(5, "Language Known");
        this.Values.add(6, "Nationality");
        this.Values.add(7, "Country");
        this.Values.add(8, "Postcode");
        this.Values.add(9, "Pin code");
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select personal_title from title_table where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
        rawQuery.moveToFirst();
        if (rawQuery.getCount() > 0 && (string = rawQuery.getString(rawQuery.getColumnIndex("personal_title"))) != null) {
            AppCompatTextView appCompatTextView = this.personal_title;
            appCompatTextView.setText("" + string);
        }
        rawQuery.close();
        SQLiteDatabase sQLiteDatabase2 = this.DB;
        Cursor rawQuery2 = sQLiteDatabase2.rawQuery("Select * from personal_info where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
        rawQuery2.moveToFirst();
        Log.e("ProfileTablecursor", "" + rawQuery2.getCount());
        if (rawQuery2.getCount() > 0) {
            String string2 = rawQuery2.getString(rawQuery2.getColumnIndex(AppMeasurementSdk.ConditionalUserProperty.NAME));
            String string3 = rawQuery2.getString(rawQuery2.getColumnIndex("email"));
            String string4 = rawQuery2.getString(rawQuery2.getColumnIndex("phone"));
            String obj = Html.fromHtml(rawQuery2.getString(rawQuery2.getColumnIndex("address"))).toString();
            String string5 = rawQuery2.getString(rawQuery2.getColumnIndex("language"));
            String string6 = rawQuery2.getString(rawQuery2.getColumnIndex("maritalstatus"));
            if (string2 != null) {
                AppCompatEditText appCompatEditText = this.personal_name;
                appCompatEditText.setText("" + string2);
            }
            if (string3 != null) {
                AppCompatEditText appCompatEditText2 = this.personal_email;
                appCompatEditText2.setText("" + string3);
            }
            if (string4 != null) {
                AppCompatEditText appCompatEditText3 = this.personal_phone;
                appCompatEditText3.setText("" + string4);
            }
            if (!obj.isEmpty()) {
                AppCompatEditText appCompatEditText4 = this.personal_address;
                appCompatEditText4.setText("" + Html.fromHtml(rawQuery2.getString(rawQuery2.getColumnIndex("address"))).toString());
                Log.e("*******", Html.fromHtml(rawQuery2.getString(rawQuery2.getColumnIndex("address"))).toString());
                this.personal_save.setText("UPDATE");
            } else {
                this.personal_save.setText("SAVE");
            }
            if (string5 != null) {
                AppCompatEditText appCompatEditText5 = this.personal_lang;
                appCompatEditText5.setText("" + string5);
            }
            if (string6 != null) {
                AppCompatEditText appCompatEditText6 = this.personal_marital;
                appCompatEditText6.setText("" + string6);
            }
            this.table_id = rawQuery2.getInt(rawQuery2.getColumnIndex("personal_id"));
            this.sp.putInt(context, "personal_id", this.table_id);
            Log.e("personaliddd", "" + this.table_id);
            Update_Load(this.table_id, this.more_data);
        } else {
            this.personal_save.setText("SAVE");
        }
        SQLiteDatabase sQLiteDatabase3 = this.DB;
        Cursor rawQuery3 = sQLiteDatabase3.rawQuery("Select * from search_table where profile_id='" + this.sp.getString(context, "profile_id") + "' and table_name='personal_info'", null);
        rawQuery3.moveToFirst();
        Log.e("ProfileTablecursor", "" + rawQuery3.getCount());
        if (rawQuery3.getCount() == 0) {
            for (int i = 0; i < this.Values.size(); i++) {
                SQLiteDatabase sQLiteDatabase4 = this.DB;
                sQLiteDatabase4.execSQL("INSERT INTO search_table (profile_id, table_name, title) VALUES ('" + this.sp.getString(context, "profile_id") + "','personal_info','" + this.Values.get(i).replaceAll("'", "''") + "');");
            }
        }
        rawQuery3.close();
        this.edit.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass1 */

            public void onClick(View view) {
            }
        });
        this.name_edit.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass2 */

            public void onClick(View view) {
                Personal_info_fragment personal_info_fragment = Personal_info_fragment.this;
                personal_info_fragment.showSoftKeyboard(personal_info_fragment.title_per);
                Personal_info_fragment.this.title_per.setText(Personal_info_fragment.this.personal_title.getText().toString());
                Personal_info_fragment.this.title_per.setSelection(Personal_info_fragment.this.personal_title.getText().toString().length());
                if (Personal_info_fragment.this.edit.isShown()) {
                    Personal_info_fragment.this.hideSoftKeyboard();
                    Personal_info_fragment.this.edit.setVisibility(View.GONE);
                    return;
                }
                Personal_info_fragment.this.title_per.clearFocus();
                Personal_info_fragment.this.title_per.setError(null);
                Personal_info_fragment personal_info_fragment2 = Personal_info_fragment.this;
                personal_info_fragment2.showSoftKeyboard(personal_info_fragment2.title_per);
                Personal_info_fragment.this.edit.setVisibility(View.VISIBLE);
            }
        });
        this.title_per.setOnKeyListener(new View.OnKeyListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass3 */

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 6) {
                    return false;
                }
                Personal_info_fragment.this.hideSoftKeyboard();
                return false;
            }
        });
        this.correct.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass4 */

            public void onClick(View view) {
                if (Personal_info_fragment.this.title_per.getText().toString().isEmpty()) {
                    Personal_info_fragment.this.title_per.setError("Enter your title..");
                    Personal_info_fragment.this.title_per.requestFocus();
                    return;
                }
                Personal_info_fragment.this.personal_title.setText(Personal_info_fragment.this.title_per.getText().toString());
                SQLiteDatabase sQLiteDatabase = Personal_info_fragment.this.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("Select personal_title from title_table where profile_id='" + Personal_info_fragment.this.sp.getString(Personal_info_fragment.context, "profile_id") + "'", null);
                if (rawQuery.getCount() == 0) {
                    SQLiteDatabase sQLiteDatabase2 = Personal_info_fragment.this.DB;
                    sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, personal_title) VALUES ('" + Personal_info_fragment.this.sp.getString(Personal_info_fragment.context, "profile_id") + "', '" + Personal_info_fragment.this.personal_title.getText().toString().replaceAll("'", "''") + "');");
                } else {
                    SQLiteDatabase sQLiteDatabase3 = Personal_info_fragment.this.DB;
                    sQLiteDatabase3.execSQL("UPDATE title_table SET personal_title = '" + Personal_info_fragment.this.personal_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + Personal_info_fragment.this.sp.getString(Personal_info_fragment.context, "profile_id") + "';");
                }
                rawQuery.close();
                Personal_info_fragment.this.hideSoftKeyboard();
                Personal_info_fragment.this.edit.setVisibility(View.GONE);
            }
        });
        this.add_view.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass5 */

            public void onClick(View view) {
                Personal_info_fragment.this.hideSoftKeyboard();
                Personal_info_fragment.this.personal_name.clearFocus();
                Personal_info_fragment.this.personal_email.clearFocus();
                Personal_info_fragment.this.personal_phone.clearFocus();
                Personal_info_fragment.this.personal_address.clearFocus();
                Personal_info_fragment.this.personal_lang.clearFocus();
                Personal_info_fragment.this.personal_marital.clearFocus();
                Personal_info_fragment.this.final_save();
                Personal_info_fragment.this.add_dialog("", "", 0);
            }
        });
        this.personal_save.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass6 */

            public void onClick(View view) {
                String trim = Personal_info_fragment.this.personal_name.getText().toString().trim();
                String trim2 = Personal_info_fragment.this.personal_email.getText().toString().trim();
                String trim3 = Personal_info_fragment.this.personal_phone.getText().toString().trim();
                String trim4 = Personal_info_fragment.this.personal_address.getText().toString().trim();
                Log.e("adresss", trim4);
                if (trim.equals("")) {
                    Personal_info_fragment.this.personal_name.setError("Enter Your Name!!!");
                    Personal_info_fragment.this.personal_name.requestFocus();
                } else if (trim2.equals("")) {
                    Personal_info_fragment.this.personal_email.setError("Enter Your Email Address!!!");
                    Personal_info_fragment.this.personal_email.requestFocus();
                } else if (trim3.equals("")) {
                    Personal_info_fragment.this.personal_phone.setError("Enter Phone Number!!!");
                    Personal_info_fragment.this.personal_phone.requestFocus();
                } else if (trim4.equals("")) {
                    Personal_info_fragment.this.personal_address.setError("Enter Your Address!!!");
                    Personal_info_fragment.this.personal_address.requestFocus();
                } else {
                    Personal_info_fragment.this.personal_save.requestFocus();
                    Personal_info_fragment.this.hideSoftKeyboard();
                    SharedPreference sharedPreference = Personal_info_fragment.this.sp;
                    Activity activity = Personal_info_fragment.context;
                    if (!sharedPreference.getString(activity, "toast_check" + Personal_info_fragment.this.sp.getString(Personal_info_fragment.context, "profile_id")).equals("no")) {
                        Utils.toast_center(Personal_info_fragment.context, "Personal Info Saved Successfully");
                        SharedPreference sharedPreference2 = Personal_info_fragment.this.sp;
                        Activity activity2 = Personal_info_fragment.context;
                        sharedPreference2.putString(activity2, "toast_check" + Personal_info_fragment.this.sp.getString(Personal_info_fragment.context, "profile_id"), "no");
                    } else {
                        Utils.toast_center(Personal_info_fragment.context, "Personal Info Updated Successfully");
                    }
                    Personal_info_fragment.this.final_save();
                    Personal_info_fragment.this.personal_save.setText("UPDATE");
                }
            }
        });
        if (!this.sp.getString(context, NotificationCompat.CATEGORY_STATUS).equals("install")) {
            new Handler().postDelayed(new Runnable() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass7 */

                public void run() {
                    Personal_info_fragment.this.scroll.fullScroll(130);
                    Personal_info_fragment.this.sp.putInt(Personal_info_fragment.context, "count", Personal_info_fragment.this.sp.getInt(Personal_info_fragment.context, "count") + 1);
                    if (Personal_info_fragment.this.sp.getInt(Personal_info_fragment.context, "count") == 2) {
                        Personal_info_fragment.this.sp.putString(Personal_info_fragment.context, NotificationCompat.CATEGORY_STATUS, "install");
                        Personal_info_fragment.this.Showcase();
                    }
                }
            }, 50);
        }
        return inflate;
    }

    public void Update_Load(int i, List<DB_Items> list) {
        list.clear();
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from extra_table where profile_id='" + this.sp.getString(context, "profile_id") + "' and table_id='" + i + "' and table_name='personal_info'", null);
        rawQuery.moveToPosition(0);
        if (rawQuery.getCount() > 0 && rawQuery.moveToFirst()) {
            for (int i2 = 0; i2 < rawQuery.getCount(); i2++) {
                DB_Items dB_Items = new DB_Items();
                dB_Items.setTitle(rawQuery.getString(rawQuery.getColumnIndex("title")));
                dB_Items.setValue(rawQuery.getString(rawQuery.getColumnIndex("value")));
                list.add(dB_Items);
                rawQuery.moveToNext();
            }
        }
        rawQuery.close();
        this.mAdapter.notifyDataSetChanged();
    }

    public void hideSoftKeyboard() {
        if (context.getCurrentFocus() != null) {
            ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showSoftKeyboard(View view) {
        view.requestFocus();
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
    }

    @Override // nithra.resume.maker.cv.builder.app.Visible
    public void visible() {
        if (this.edit.isShown()) {
            this.edit.setVisibility(View.GONE);
        } else {
            exti_result_dialog();
        }
    }

    public void exti_result_dialog() {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_exit);
        MainActivity.load_addFromMain_natt(context, (FrameLayout) dialog.findViewById(R.id.ads_lay));
        ((TextView) dialog.findViewById(R.id.yes)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass8 */

            public void onClick(View view) {
                Personal_info_fragment.context.finish();
                dialog.dismiss();
            }
        });
        ((TextView) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass9 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public void add_dialog(final String str, final String str2, final int i) {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_add_view);
        this.title = (AppCompatTextView) dialog.findViewById(R.id.title);
        this.value = (AppCompatEditText) dialog.findViewById(R.id.value);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.ok);
        ImageView imageView2 = (ImageView) dialog.findViewById(R.id.cancel);
        this.title.setText(str);
        this.value.setText(str2);
        this.title.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass10 */

            public void onClick(View view) {
                Personal_info_fragment.this.title.setError(null);
                Personal_info_fragment.this.add_new_dialog();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass11 */

            public void onClick(View view) {
                if (Personal_info_fragment.this.title.getText().toString().trim().equals("")) {
                    Personal_info_fragment.this.title.setError("Title Required !!!");
                    Personal_info_fragment.this.title.requestFocus();
                } else if (Personal_info_fragment.this.value.getText().toString().trim().equals("")) {
                    AppCompatEditText appCompatEditText = Personal_info_fragment.this.value;
                    appCompatEditText.setError("Enter your " + Personal_info_fragment.this.title.getText().toString() + " here !!!");
                    Personal_info_fragment.this.value.requestFocus();
                } else if (str.equals("")) {
                    Personal_info_fragment personal_info_fragment = Personal_info_fragment.this;
                    personal_info_fragment.table_id = personal_info_fragment.sp.getInt(Personal_info_fragment.context, "personal_id");
                    SQLiteDatabase sQLiteDatabase = Personal_info_fragment.this.DB;
                    if (sQLiteDatabase.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Personal_info_fragment.this.sp.getString(Personal_info_fragment.context, "profile_id") + "' and  upper(title)='" + Personal_info_fragment.this.title.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='personal_info' and table_id='" + Personal_info_fragment.this.table_id + "'", null).getCount() == 0) {
                        DB_Items dB_Items = new DB_Items();
                        dB_Items.setTitle(Personal_info_fragment.this.title.getText().toString());
                        dB_Items.setValue(Personal_info_fragment.this.value.getText().toString());
                        Personal_info_fragment.this.more_data.add(dB_Items);
                        Personal_info_fragment.this.mAdapter.notifyDataSetChanged();
                        Personal_info_fragment.this.final_save();
                        dialog.getWindow().setSoftInputMode(3);
                        dialog.dismiss();
                        Personal_info_fragment.this.scroll.fullScroll(130);
                    } else {
                        Personal_info_fragment.this.title.setError("Already exist !!!");
                        Personal_info_fragment.this.title.requestFocus();
                    }
                } else if (!Personal_info_fragment.this.title.getText().toString().equals(str)) {
                    SQLiteDatabase sQLiteDatabase2 = Personal_info_fragment.this.DB;
                    if (sQLiteDatabase2.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Personal_info_fragment.this.sp.getString(Personal_info_fragment.context, "profile_id") + "' and  upper(title)='" + Personal_info_fragment.this.title.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='personal_info' and table_id='" + Personal_info_fragment.this.table_id + "'", null).getCount() == 0) {
                        Personal_info_fragment.this.more_data.get(i).setTitle(Personal_info_fragment.this.title.getText().toString());
                        Personal_info_fragment.this.more_data.get(i).setValue(Personal_info_fragment.this.value.getText().toString());
                        Personal_info_fragment.this.mAdapter.notifyDataSetChanged();
                        Personal_info_fragment.this.final_save();
                        dialog.getWindow().setSoftInputMode(3);
                        Personal_info_fragment.this.hideSoftKeyboard();
                        dialog.dismiss();
                        Personal_info_fragment.this.scroll.fullScroll(130);
                    } else {
                        Personal_info_fragment.this.title.setError("Already exist !!!");
                        Personal_info_fragment.this.title.requestFocus();
                    }
                } else if (!Personal_info_fragment.this.value.getText().toString().equals(str2)) {
                    Personal_info_fragment.this.more_data.get(i).setTitle(Personal_info_fragment.this.title.getText().toString());
                    Personal_info_fragment.this.more_data.get(i).setValue(Personal_info_fragment.this.value.getText().toString());
                    Personal_info_fragment.this.mAdapter.notifyDataSetChanged();
                    Personal_info_fragment.this.final_save();
                    dialog.getWindow().setSoftInputMode(3);
                    Personal_info_fragment.this.hideSoftKeyboard();
                    dialog.dismiss();
                    Personal_info_fragment.this.scroll.fullScroll(130);
                } else {
                    Personal_info_fragment.this.mAdapter.notifyDataSetChanged();
                    Personal_info_fragment.this.final_save();
                    Personal_info_fragment.this.hideSoftKeyboard();
                    dialog.dismiss();
                    Personal_info_fragment.this.scroll.fullScroll(130);
                }
                if (!Personal_info_fragment.this.sp.getString(Personal_info_fragment.context, "drag").equals("draged")) {
                    SQLiteDatabase sQLiteDatabase3 = Personal_info_fragment.this.DB;
                    Cursor rawQuery = sQLiteDatabase3.rawQuery("Select table_id from extra_table where profile_id='" + Personal_info_fragment.this.sp.getString(Personal_info_fragment.context, "profile_id") + "' and table_id='" + Personal_info_fragment.this.table_id + "' and table_name='personal_info'", null);
                    if (rawQuery.getCount() > 1) {
                        Personal_info_fragment.this.drag_dialog();
                    }
                    rawQuery.close();
                }
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass12 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public void add_new_dialog() {
        this.exit_dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        this.exit_dialog.setContentView(R.layout.dialog_add_new);
        final AppCompatEditText appCompatEditText = (AppCompatEditText) this.exit_dialog.findViewById(R.id.search_view);
        final RecyclerView recyclerView = (RecyclerView) this.exit_dialog.findViewById(R.id.search_list);
        LinearLayout linearLayout = (LinearLayout) this.exit_dialog.findViewById(R.id.add_linear);
        final AppCompatTextView appCompatTextView = (AppCompatTextView) this.exit_dialog.findViewById(R.id.add_new_text);
        ImageView imageView = (ImageView) this.exit_dialog.findViewById(R.id.add_new);
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from search_table where profile_id='" + this.sp.getString(context, "profile_id") + "' and table_name='personal_info'", null);
        if (rawQuery.getCount() > 0) {
            get_values.clear();
            if (rawQuery.moveToFirst()) {
                for (int i = 0; i < rawQuery.getCount(); i++) {
                    DB_Items dB_Items = new DB_Items();
                    dB_Items.setValue(rawQuery.getString(rawQuery.getColumnIndex("title")));
                    get_values.add(dB_Items);
                    rawQuery.moveToNext();
                }
            }
        }
        rawQuery.close();
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
        Collections.sort(get_values, new Comparator<DB_Items>() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass13 */

            public int compare(DB_Items dB_Items, DB_Items dB_Items2) {
                return dB_Items.getValue().compareTo(dB_Items2.getValue());
            }
        });
        mAdapter1 = new Add_ListAdapter(context, get_values);
        recyclerView.setAdapter(mAdapter1);
        mAdapter1.notifyDataSetChanged();
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass14 */

            public void onClick(View view) {
                new DB_Items().setValue(appCompatTextView.getText().toString());
                if (!appCompatTextView.getText().toString().trim().equals("")) {
                    SQLiteDatabase sQLiteDatabase = Personal_info_fragment.this.DB;
                    Cursor rawQuery = sQLiteDatabase.rawQuery("Select upper(title) as title from search_table where profile_id='" + Personal_info_fragment.this.sp.getString(Personal_info_fragment.context, "profile_id") + "' and  upper(title)='" + appCompatTextView.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='personal_info'", null);
                    if (rawQuery.getCount() == 0) {
                        SQLiteDatabase sQLiteDatabase2 = Personal_info_fragment.this.DB;
                        sQLiteDatabase2.execSQL("INSERT INTO search_table (profile_id, table_name, title) VALUES ('" + Personal_info_fragment.this.sp.getString(Personal_info_fragment.context, "profile_id") + "','personal_info','" + appCompatTextView.getText().toString().replaceAll("'", "''") + "');");
                        SQLiteDatabase sQLiteDatabase3 = Personal_info_fragment.this.DB;
                        Cursor rawQuery2 = sQLiteDatabase3.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Personal_info_fragment.this.sp.getString(Personal_info_fragment.context, "profile_id") + "' and  upper(title)='" + appCompatTextView.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='personal_info' and table_id='" + Personal_info_fragment.this.table_id + "'", null);
                        if (rawQuery2.getCount() != 0) {
                            Activity activity = Personal_info_fragment.context;
                            Utils.toast_center(activity, "" + appCompatTextView.getText().toString() + " already added...");
                        } else {
                            Personal_info_fragment.this.title.setText(appCompatTextView.getText().toString());
                            AppCompatEditText appCompatEditText = Personal_info_fragment.this.value;
                            appCompatEditText.setHint("Your " + appCompatTextView.getText().toString());
                            Personal_info_fragment.this.exit_dialog.dismiss();
                        }
                        rawQuery2.close();
                    } else {
                        SQLiteDatabase sQLiteDatabase4 = Personal_info_fragment.this.DB;
                        Cursor rawQuery3 = sQLiteDatabase4.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Personal_info_fragment.this.sp.getString(Personal_info_fragment.context, "profile_id") + "' and  upper(title)='" + appCompatTextView.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='personal_info' and table_id='" + Personal_info_fragment.this.table_id + "'", null);
                        if (rawQuery3.getCount() != 0) {
                            Activity activity2 = Personal_info_fragment.context;
                            Utils.toast_center(activity2, "" + appCompatTextView.getText().toString() + " already added...");
                        } else {
                            Personal_info_fragment.this.title.setText(appCompatTextView.getText().toString());
                            AppCompatEditText appCompatEditText2 = Personal_info_fragment.this.value;
                            appCompatEditText2.setHint("Your " + appCompatTextView.getText().toString());
                            Personal_info_fragment.this.exit_dialog.dismiss();
                        }
                        rawQuery3.close();
                    }
                    rawQuery.close();
                    return;
                }
                appCompatEditText.setError("Enter your title here...");
                appCompatEditText.requestFocus();
            }
        });
        appCompatEditText.setHint("Type your Title....");
        appCompatEditText.addTextChangedListener(new TextWatcher() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass15 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                Personal_info_fragment.get_values.clear();
                SQLiteDatabase sQLiteDatabase = Personal_info_fragment.this.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT title FROM search_table WHERE title LIKE '%" + editable.toString().replaceAll("'", "''") + "%' and profile_id='" + Personal_info_fragment.this.sp.getString(Personal_info_fragment.context, "profile_id") + "' and table_name='personal_info'", null);
                rawQuery.moveToFirst();
                if (rawQuery.getCount() > 0) {
                    for (int i = 0; i < rawQuery.getCount(); i++) {
                        DB_Items dB_Items = new DB_Items();
                        dB_Items.setValue(rawQuery.getString(rawQuery.getColumnIndex("title")));
                        Personal_info_fragment.get_values.add(dB_Items);
                        rawQuery.moveToNext();
                    }
                }
                rawQuery.close();
                Collections.sort(Personal_info_fragment.get_values, new Comparator<DB_Items>() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass15.AnonymousClass1 */

                    public int compare(DB_Items dB_Items, DB_Items dB_Items2) {
                        return dB_Items.getValue().compareTo(dB_Items2.getValue());
                    }
                });
                Personal_info_fragment.mAdapter1 = new Add_ListAdapter(Personal_info_fragment.context, Personal_info_fragment.get_values);
                recyclerView.setAdapter(Personal_info_fragment.mAdapter1);
                appCompatTextView.setText(editable);
            }
        });
        this.exit_dialog.setCancelable(false);
        this.exit_dialog.setCanceledOnTouchOutside(true);
        this.exit_dialog.show();
    }


    
    private void final_save() {
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select personal_title from title_table where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
        if (rawQuery.getCount() == 0) {
            SQLiteDatabase sQLiteDatabase2 = this.DB;
            sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, personal_title) VALUES ('" + this.sp.getString(context, "profile_id") + "', '" + this.personal_title.getText().toString().replaceAll("'", "''") + "');");
        } else {
            SQLiteDatabase sQLiteDatabase3 = this.DB;
            sQLiteDatabase3.execSQL("UPDATE title_table SET personal_title = '" + this.personal_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + this.sp.getString(context, "profile_id") + "';");
        }
        rawQuery.close();
        SQLiteDatabase sQLiteDatabase4 = this.DB;
        int count = sQLiteDatabase4.rawQuery("Select profile_id from personal_info where profile_id='" + this.sp.getString(context, "profile_id") + "'", null).getCount();
        int i = 0;
        if (count == 0) {
            SQLiteDatabase sQLiteDatabase5 = this.DB;
            sQLiteDatabase5.execSQL("INSERT INTO personal_info (profile_id, name, address, email, phone,language,maritalstatus) VALUES ('" + this.sp.getString(context, "profile_id") + "', '" + this.personal_name.getText().toString().replaceAll("'", "''") + "', '" + Html.toHtml(this.personal_address.getText()).replaceAll("'", "''") + "', '" + this.personal_email.getText().toString().replaceAll("'", "''") + "', '" + this.personal_phone.getText().toString().replaceAll("'", "''") + "', '" + this.personal_lang.getText().toString().replaceAll("'", "''") + "', '" + this.personal_marital.getText().toString().replaceAll("'", "''") + "');");
            Cursor rawQuery2 = this.DB.rawQuery("select personal_id from personal_info", null);
            rawQuery2.moveToLast();
            int i2 = rawQuery2.getInt(0);
            rawQuery2.close();
            this.sp.putInt(context, "personal_id", i2);
            SQLiteDatabase sQLiteDatabase6 = this.DB;
            StringBuilder sb = new StringBuilder();
            sb.append("Select table_id from extra_table where profile_id='");
            sb.append(this.sp.getString(context, "profile_id"));
            sb.append("' and table_id='");
            sb.append(i2);
            sb.append("' and table_name='personal_info'");
            Cursor rawQuery3 = sQLiteDatabase6.rawQuery(sb.toString(), null);
            if (rawQuery3.getCount() == 0) {
                while (i < this.more_data.size()) {
                    if (!this.more_data.get(i).getTitle().equals("") && !this.more_data.get(i).getTitle().equals(null) && !this.more_data.get(i).getValue().equals("") && !this.more_data.get(i).getValue().equals(null)) {
                        SQLiteDatabase sQLiteDatabase7 = this.DB;
                        sQLiteDatabase7.execSQL("INSERT INTO extra_table (profile_id, table_id, table_name, title, value) VALUES ('" + this.sp.getString(context, "profile_id") + "', '" + i2 + "','personal_info','" + this.more_data.get(i).getTitle().replaceAll("'", "''") + "','" + this.more_data.get(i).getValue().replaceAll("'", "''") + "');");
                    }
                    i++;
                }
            }
            rawQuery3.close();
            return;
        }
        SQLiteDatabase sQLiteDatabase8 = this.DB;
        sQLiteDatabase8.execSQL("UPDATE personal_info SET name = '" + this.personal_name.getText().toString().replaceAll("'", "''") + "', address = '" + Html.toHtml(this.personal_address.getText()).replaceAll("'", "''") + "', email = '" + this.personal_email.getText().toString().replaceAll("'", "''") + "', phone = '" + this.personal_phone.getText().toString().replaceAll("'", "''") + "', language = '" + this.personal_lang.getText().toString().replaceAll("'", "''") + "', maritalstatus = '" + this.personal_marital.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + this.sp.getString(context, "profile_id") + "';");
        SQLiteDatabase sQLiteDatabase9 = this.DB;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("delete from extra_table where profile_id='");
        sb2.append(this.sp.getString(context, "profile_id"));
        sb2.append("' and table_id='");
        sb2.append(this.table_id);
        sb2.append("' and table_name='personal_info';");
        sQLiteDatabase9.execSQL(sb2.toString());
        while (i < this.more_data.size()) {
            if (!this.more_data.get(i).getTitle().equals("") && !this.more_data.get(i).getTitle().equals(null) && !this.more_data.get(i).getValue().equals("") && !this.more_data.get(i).getValue().equals(null)) {
                SQLiteDatabase sQLiteDatabase10 = this.DB;
                sQLiteDatabase10.execSQL("INSERT INTO extra_table (profile_id, table_id, table_name, title, value) VALUES ('" + this.sp.getString(context, "profile_id") + "', '" + this.table_id + "','personal_info','" + this.more_data.get(i).getTitle().replaceAll("'", "''") + "','" + this.more_data.get(i).getValue().replaceAll("'", "''") + "');");
            }
            i++;
        }
    }

    @Override // nithra.resume.maker.cv.builder.app.Touch.OnStartDragListener
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        this.mItemTouchHelper.startDrag(viewHolder);
    }

    public void drag_dialog() {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_guid);
        final Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.move1);
        final CardView cardView = (CardView) dialog.findViewById(R.id.card1);
        CardView cardView2 = (CardView) dialog.findViewById(R.id.card2);
        final ImageView imageView = (ImageView) dialog.findViewById(R.id.drag);
        imageView.setAnimation(zoomAnim());
        final Animation loadAnimation2 = AnimationUtils.loadAnimation(getContext(), R.anim.move);
        cardView.startAnimation(loadAnimation2);
        imageView.startAnimation(loadAnimation2);
        loadAnimation2.setAnimationListener(new Animation.AnimationListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass16 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass16.AnonymousClass1 */

                    public void run() {
                        cardView.startAnimation(loadAnimation);
                        imageView.startAnimation(loadAnimation);
                    }
                }, 1500);
            }
        });
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass17 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass17.AnonymousClass1 */

                    public void run() {
                        cardView.startAnimation(loadAnimation2);
                        imageView.startAnimation(loadAnimation2);
                    }
                }, 1500);
            }
        });
        ((Button) dialog.findViewById(R.id.enter)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass18 */

            public void onClick(View view) {
                Personal_info_fragment.this.sp.putString(Personal_info_fragment.context, "drag", "draged");
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    public Animation zoomAnim() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.9f, 0.83f, 0.9f, 0.83f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimation.setDuration(500);
        scaleAnimation.setRepeatCount(-1);
        scaleAnimation.setRepeatMode(2);
        return scaleAnimation;
    }


    
    private void Showcase() {
        ShowcaseConfig showcaseConfig = new ShowcaseConfig();
        showcaseConfig.setDelay(500);
        MaterialShowcaseSequence materialShowcaseSequence = new MaterialShowcaseSequence(context, this.SHOWCASE_ID);
        materialShowcaseSequence.setOnItemShownListener(new MaterialShowcaseSequence.OnSequenceItemShownListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass19 */

            @Override // nithra.resume.maker.cv.builder.app.showcase.MaterialShowcaseSequence.OnSequenceItemShownListener
            public void onShow(MaterialShowcaseView materialShowcaseView, int i) {
            }
        });
        materialShowcaseSequence.setConfig(showcaseConfig);
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "Chunkfive.otf");
        materialShowcaseSequence.addSequenceItem(new MaterialShowcaseView.Builder(context).setTarget(this.name_edit).setDismissText("OK").setContentText("Click here to edit title").withCircleShape().setDismissTextColor(context.getResources().getColor(R.color.red)).setMaskColour(R.color.colorAccent).setDismissStyle(createFromAsset).build());
        materialShowcaseSequence.addSequenceItem(new MaterialShowcaseView.Builder(context).setTarget(this.add_view).setDismissText("OK").setContentText("Click here to add more details").withRectangleShape().setDismissTextColor(context.getResources().getColor(R.color.red)).setMaskColour(R.color.colorAccent).setDismissStyle(createFromAsset).build());
        materialShowcaseSequence.setOnItemDismissedListener(new MaterialShowcaseSequence.OnSequenceItemDismissedListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass20 */

            @Override // nithra.resume.maker.cv.builder.app.showcase.MaterialShowcaseSequence.OnSequenceItemDismissedListener
            public void onDismiss(MaterialShowcaseView materialShowcaseView, int i) {
                Personal_info_fragment.this.sp.putInt(Personal_info_fragment.context, "dismiss", Personal_info_fragment.this.sp.getInt(Personal_info_fragment.context, "dismiss") + 1);
                if (Personal_info_fragment.this.sp.getInt(Personal_info_fragment.context, "dismiss") == 2) {
                    new Handler().postDelayed(new Runnable() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.AnonymousClass20.AnonymousClass1 */

                        public void run() {
                            Personal_info_fragment.this.scroll.fullScroll(33);
                        }
                    }, 50);
                }
            }
        });
        materialShowcaseSequence.start();
    }

    public class Add_ListAdapter extends RecyclerView.Adapter<Add_ListAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {
        private List<DB_Items> mItems = new ArrayList();
        Context mcontext;
        SharedPreference sp = new SharedPreference();
        int to_position;

        public Add_ListAdapter(Context context, List<DB_Items> list) {
            this.mItems = list;
            this.mcontext = context;
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_spinner_view, viewGroup, false));
        }

        public void onBindViewHolder(ItemViewHolder itemViewHolder, final int i) {
            itemViewHolder.textView.setText(this.mItems.get(i).getValue());
            itemViewHolder.textView.setOnClickListener(new View.OnClickListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.Add_ListAdapter.AnonymousClass1 */

                public void onClick(View view) {
                    SQLiteDatabase sQLiteDatabase = Personal_info_fragment.this.DB;
                    Cursor rawQuery = sQLiteDatabase.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Add_ListAdapter.this.sp.getString(Personal_info_fragment.context, "profile_id") + "' and  upper(title)='" + ((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue().toUpperCase().replaceAll("'", "''") + "' and table_name='personal_info' and table_id='" + Personal_info_fragment.this.table_id + "'", null);
                    if (rawQuery.getCount() != 0) {
                        Activity activity = Personal_info_fragment.context;
                        Utils.toast_center(activity, ((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue() + " already added...");
                    } else {
                        Personal_info_fragment.this.title.setText(((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue());
                        Personal_info_fragment.this.value.setError(null);
                        AppCompatEditText appCompatEditText = Personal_info_fragment.this.value;
                        appCompatEditText.setHint("Your " + ((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue());
                        Personal_info_fragment.this.exit_dialog.dismiss();
                    }
                    rawQuery.close();
                }
            });
            itemViewHolder.remove.setOnClickListener(new View.OnClickListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.Add_ListAdapter.AnonymousClass2 */

                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Add_ListAdapter.this.mcontext);
                    builder.setMessage("Are you sure want to delete this?").setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.Add_ListAdapter.AnonymousClass2.AnonymousClass2 */

                        public void onClick(DialogInterface dialogInterface, int i) {
                            SQLiteDatabase sQLiteDatabase = Personal_info_fragment.this.DB;
                            sQLiteDatabase.execSQL("delete from search_table where profile_id='" + Add_ListAdapter.this.sp.getString(Personal_info_fragment.context, "profile_id") + "' and table_name='personal_info'and title='" + ((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue().replaceAll("'", "''") + "';");
                            Add_ListAdapter.this.mItems.remove(i);
                            Add_ListAdapter.this.notifyDataSetChanged();
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.Add_ListAdapter.AnonymousClass2.AnonymousClass1 */

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.create().show();
                }
            });
        }

        @Override // nithra.resume.maker.cv.builder.app.Touch.ItemTouchHelperAdapter
        public void onItemDismiss(int i) {
            this.mItems.remove(i);
            notifyItemRemoved(i);
        }

        @Override // nithra.resume.maker.cv.builder.app.Touch.ItemTouchHelperAdapter
        public boolean onItemMove(int i, int i2) {
            Collections.swap(this.mItems, i, i2);
            notifyItemMoved(i, i2);
            this.to_position = i2;
            return true;
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mItems.size();
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
            public final ImageView remove;
            public final AppCompatTextView textView;

            public ItemViewHolder(View view) {
                super(view);
                this.textView = (AppCompatTextView) view.findViewById(R.id.named);
                this.remove = (ImageView) view.findViewById(R.id.remove);
            }

            @Override // nithra.resume.maker.cv.builder.app.Touch.ItemTouchHelperViewHolder
            public void onItemSelected() {
                this.itemView.setBackgroundColor(0);
            }

            @Override // nithra.resume.maker.cv.builder.app.Touch.ItemTouchHelperViewHolder
            public void onItemClear() {
                this.itemView.setBackgroundColor(0);
            }
        }
    }

    public class Personal_ListAdapter extends RecyclerView.Adapter<Personal_ListAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {
        private final OnStartDragListener mDragStartListener;
        private List<DB_Items> mItems = new ArrayList();
        Context mcontext;
        int to_position;

        public Personal_ListAdapter(Context context, OnStartDragListener onStartDragListener, List<DB_Items> list) {
            this.mDragStartListener = onStartDragListener;
            this.mItems = list;
            this.mcontext = context;
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_more_data_view, viewGroup, false));
        }

        public void onBindViewHolder(final ItemViewHolder itemViewHolder, int i) {
            itemViewHolder.textView.setText(this.mItems.get(i).getValue());
            itemViewHolder.extra_title.setText(this.mItems.get(i).getTitle());
            AppCompatTextView appCompatTextView = itemViewHolder.textView;
            appCompatTextView.setHint("Enter " + this.mItems.get(i).getTitle());
            View view = itemViewHolder.itemView;
            view.setTag("" + this.mItems.get(i).getTitle());
            ImageView imageView = itemViewHolder.remove;
            imageView.setTag("" + this.mItems.get(i).getTitle());
            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.Personal_ListAdapter.AnonymousClass1 */

                public void onClick(View view) {
                    for (int i = 0; i < Personal_ListAdapter.this.mItems.size(); i++) {
                        if (((DB_Items) Personal_ListAdapter.this.mItems.get(i)).getTitle().equals(view.getTag().toString())) {
                            Personal_info_fragment.this.add_dialog(((DB_Items) Personal_ListAdapter.this.mItems.get(i)).getTitle(), ((DB_Items) Personal_ListAdapter.this.mItems.get(i)).getValue(), i);
                            Personal_info_fragment.this.title.setText(((DB_Items) Personal_ListAdapter.this.mItems.get(i)).getTitle());
                            return;
                        }
                    }
                }
            });
            itemViewHolder.handleView.setOnTouchListener(new View.OnTouchListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.Personal_ListAdapter.AnonymousClass2 */

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (MotionEventCompat.getActionMasked(motionEvent) != 0) {
                        return false;
                    }
                    Personal_ListAdapter.this.mDragStartListener.onStartDrag(itemViewHolder);
                    return false;
                }
            });
            itemViewHolder.remove.setOnClickListener(new View.OnClickListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.Personal_ListAdapter.AnonymousClass3 */

                public void onClick(final View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Personal_ListAdapter.this.mcontext);
                    builder.setMessage("Are you sure want to delete this?").setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.Personal_ListAdapter.AnonymousClass3.AnonymousClass2 */

                        public void onClick(DialogInterface dialogInterface, int i) {
                            for (int i2 = 0; i2 < Personal_ListAdapter.this.mItems.size(); i2++) {
                                if (((DB_Items) Personal_ListAdapter.this.mItems.get(i2)).getTitle().equals(view.getTag().toString())) {
                                    SQLiteDatabase sQLiteDatabase = Personal_info_fragment.this.DB;
                                    sQLiteDatabase.execSQL("delete from extra_table where profile_id='" + Personal_info_fragment.this.sp.getString(Personal_ListAdapter.this.mcontext, "profile_id") + "' and table_name='personal_info' and table_id='" + Personal_info_fragment.this.sp.getInt(Personal_info_fragment.context, "personal_id") + "' and title='" + ((DB_Items) Personal_ListAdapter.this.mItems.get(i2)).getTitle().replaceAll("'", "''") + "' and value='" + ((DB_Items) Personal_ListAdapter.this.mItems.get(i2)).getValue().replaceAll("'", "''") + "';");
                                    Personal_ListAdapter.this.mItems.remove(i2);
                                    Personal_ListAdapter.this.notifyDataSetChanged();
                                    return;
                                }
                            }
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment.Personal_ListAdapter.AnonymousClass3.AnonymousClass1 */

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.create().show();
                }
            });
        }

        @Override // nithra.resume.maker.cv.builder.app.Touch.ItemTouchHelperAdapter
        public void onItemDismiss(int i) {
            this.mItems.remove(i);
            notifyItemRemoved(i);
        }

        @Override // nithra.resume.maker.cv.builder.app.Touch.ItemTouchHelperAdapter
        public boolean onItemMove(int i, int i2) {
            Collections.swap(this.mItems, i, i2);
            notifyItemMoved(i, i2);
            this.to_position = i2;
            return true;
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mItems.size();
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
            public final AppCompatTextView extra_title;
            public final ImageView handleView;
            public final ImageView remove;
            public final AppCompatTextView textView;

            public ItemViewHolder(View view) {
                super(view);
                this.textView = (AppCompatTextView) view.findViewById(R.id.named);
                this.handleView = (ImageView) view.findViewById(R.id.down_img);
                this.remove = (ImageView) view.findViewById(R.id.remove);
                this.extra_title = (AppCompatTextView) view.findViewById(R.id.extra_title);
            }

            @Override // nithra.resume.maker.cv.builder.app.Touch.ItemTouchHelperViewHolder
            public void onItemSelected() {
                this.itemView.setBackgroundColor(0);
            }

            @Override // nithra.resume.maker.cv.builder.app.Touch.ItemTouchHelperViewHolder
            public void onItemClear() {
                this.itemView.setBackgroundColor(0);
            }
        }
    }
}
