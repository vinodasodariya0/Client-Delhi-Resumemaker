package nithra.resume.maker.cv.builder.app.Fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import nithra.resume.maker.cv.builder.app.MainActivity;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.SharedPreference;
import nithra.resume.maker.cv.builder.app.Utils;
import nithra.resume.maker.cv.builder.app.Visible;

public class Objective_fragment extends Fragment implements Visible {
    static Activity context;
    SQLiteDatabase DB;
    DatePickerDialog.OnDateSetListener date1;
    AppCompatEditText declaration;
    LinearLayout ex_declar;
    LinearLayout ex_obj;
    Calendar myCalendar = Calendar.getInstance();
    AppCompatEditText objective;
    AppCompatEditText objective_date;
    AppCompatEditText objective_place;
    Button objective_save;
    ImageView sel_date;
    SharedPreference sp = new SharedPreference();
    LinearLayout top;

    public static int getItem(int i) {
        return 0;
    }

    public static float dpToPixels(int i, Context context2) {
        return ((float) i) * context2.getResources().getDisplayMetrics().density;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        context = getActivity();
        View inflate = layoutInflater.inflate(R.layout.fragment_objective, viewGroup, false);
        this.objective = (AppCompatEditText) inflate.findViewById(R.id.objective);
        this.objective_place = (AppCompatEditText) inflate.findViewById(R.id.objective_place);
        this.declaration = (AppCompatEditText) inflate.findViewById(R.id.declaration);
        this.objective_date = (AppCompatEditText) inflate.findViewById(R.id.objective_date);
        this.objective_save = (Button) inflate.findViewById(R.id.objective_save);
        this.sel_date = (ImageView) inflate.findViewById(R.id.sel_date);
        this.top = (LinearLayout) inflate.findViewById(R.id.top);
        this.ex_obj = (LinearLayout) inflate.findViewById(R.id.ex_obj);
        this.ex_declar = (LinearLayout) inflate.findViewById(R.id.ex_declar);
        this.DB = context.openOrCreateDatabase("RESUME_BUILDER", 0, null);
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from objective_table where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
        rawQuery.moveToFirst();
        Log.e("ProfileTablecursor", "" + rawQuery.getCount());
        if (rawQuery.getCount() > 0) {
            AppCompatEditText appCompatEditText = this.objective;
            appCompatEditText.setText("" + rawQuery.getString(rawQuery.getColumnIndex("objective")));
            AppCompatEditText appCompatEditText2 = this.objective_place;
            appCompatEditText2.setText("" + rawQuery.getString(rawQuery.getColumnIndex("place")));
            AppCompatEditText appCompatEditText3 = this.declaration;
            appCompatEditText3.setText("" + rawQuery.getString(rawQuery.getColumnIndex("declaration")));
            AppCompatEditText appCompatEditText4 = this.objective_date;
            appCompatEditText4.setText("" + rawQuery.getString(rawQuery.getColumnIndex("date")));
            this.objective_save.setText("UPDATE");
        } else {
            this.objective_save.setText("SAVE");
        }
        this.date1 = new DatePickerDialog.OnDateSetListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Objective_fragment.AnonymousClass1 */

            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Objective_fragment.this.myCalendar.set(1, i);
                Objective_fragment.this.myCalendar.set(2, i2);
                Objective_fragment.this.myCalendar.set(5, i3);
                ((SimpleDateFormat) DateFormat.getDateFormat(Objective_fragment.context)).toLocalizedPattern();
                new SimpleDateFormat("dd - MM - yyyy").format(Objective_fragment.this.myCalendar.getTime());
                String format = java.text.DateFormat.getDateInstance().format(Objective_fragment.this.myCalendar.getTime());
                Objective_fragment.this.objective_date.setText(format);
                Objective_fragment.this.objective_date.setSelection(format.length());
            }
        };
        this.sel_date.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Objective_fragment.AnonymousClass2 */

            public void onClick(View view) {
                new DatePickerDialog(Objective_fragment.context, Objective_fragment.this.date1, Objective_fragment.this.myCalendar.get(1), Objective_fragment.this.myCalendar.get(2), Objective_fragment.this.myCalendar.get(5)).show();
            }
        });
        this.objective_place.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Objective_fragment.AnonymousClass3 */

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                ((InputMethodManager) textView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(textView.getWindowToken(), 0);
                return true;
            }
        });
        this.ex_obj.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Objective_fragment.AnonymousClass4 */

            public void onClick(View view) {
                Objective_fragment.this.Objective_dialog(1);
            }
        });
        this.ex_declar.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Objective_fragment.AnonymousClass5 */

            public void onClick(View view) {
                Objective_fragment.this.Objective_dialog(2);
            }
        });
        this.objective_save.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Objective_fragment.AnonymousClass6 */

            public void onClick(View view) {
                String trim = Objective_fragment.this.objective.getText().toString().trim();
                Objective_fragment.this.objective_place.getText().toString().trim();
                String trim2 = Objective_fragment.this.declaration.getText().toString().trim();
                Objective_fragment.this.objective_date.getText().toString().trim();
                if (trim.isEmpty()) {
                    Objective_fragment.this.objective.setError("Enter the objective !!!");
                    Objective_fragment.this.objective.requestFocus();
                } else if (trim2.equals("")) {
                    Objective_fragment.this.declaration.setError("Enter the declaration !!!");
                    Objective_fragment.this.declaration.requestFocus();
                } else {
                    Objective_fragment.this.hideSoftKeyboard();
                    SQLiteDatabase sQLiteDatabase = Objective_fragment.this.DB;
                    if (sQLiteDatabase.rawQuery("Select profile_id from objective_table where profile_id='" + Objective_fragment.this.sp.getString(Objective_fragment.context, "profile_id") + "'", null).getCount() == 0) {
                        SQLiteDatabase sQLiteDatabase2 = Objective_fragment.this.DB;
                        sQLiteDatabase2.execSQL("INSERT INTO objective_table (profile_id, objective, date, place, declaration) VALUES ('" + Objective_fragment.this.sp.getString(Objective_fragment.context, "profile_id") + "', '" + Objective_fragment.this.objective.getText().toString().replaceAll("'", "''") + "', '" + Objective_fragment.this.objective_date.getText().toString().replaceAll("'", "''") + "', '" + Objective_fragment.this.objective_place.getText().toString().replaceAll("'", "''") + "', '" + Objective_fragment.this.declaration.getText().toString().replaceAll("'", "''") + "');");
                        Utils.toast_center(Objective_fragment.context, "Saved Successfully");
                        Objective_fragment.this.objective_save.setText("UPDATE");
                        return;
                    }
                    SQLiteDatabase sQLiteDatabase3 = Objective_fragment.this.DB;
                    sQLiteDatabase3.execSQL("UPDATE objective_table SET objective = '" + Objective_fragment.this.objective.getText().toString().replaceAll("'", "''") + "', date = '" + Objective_fragment.this.objective_date.getText().toString().replaceAll("'", "''") + "', place = '" + Objective_fragment.this.objective_place.getText().toString().replaceAll("'", "''") + "', declaration = '" + Objective_fragment.this.declaration.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + Objective_fragment.this.sp.getString(Objective_fragment.context, "profile_id") + "';");
                    Utils.toast_center(Objective_fragment.context, "Updated Successfully");
                    Objective_fragment.this.objective_save.setText("UPDATE");
                }
            }
        });
        return inflate;
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
        exti_result_dialog();
    }

    public void exti_result_dialog() {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_exit);
        MainActivity.load_addFromMain_natt(context, (FrameLayout) dialog.findViewById(R.id.ads_lay));
        ((TextView) dialog.findViewById(R.id.yes)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Objective_fragment.AnonymousClass7 */

            public void onClick(View view) {
                Objective_fragment.context.finish();
                dialog.dismiss();
            }
        });
        ((TextView) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Objective_fragment.AnonymousClass8 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public void Objective_dialog(final int i) {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_objective);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Objective_fragment.AnonymousClass9 */

            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        final ListView listView = (ListView) dialog.findViewById(R.id.obj_list);
        final TabLayout tabLayout = (TabLayout) dialog.findViewById(R.id.tabs);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.closefile);
        if (i == 1) {
            listView.setAdapter((ListAdapter) new nithra.resume.maker.cv.builder.app.Adapter.ListAdapter(context, R.layout.dialog_objective, Arrays.asList(getResources().getStringArray(R.array.fresh))));
            tabLayout.setVisibility(View.VISIBLE);
        } else {
            listView.setAdapter((ListAdapter) new nithra.resume.maker.cv.builder.app.Adapter.ListAdapter(context, R.layout.dialog_objective, Arrays.asList(getResources().getStringArray(R.array.declar))));
            tabLayout.setVisibility(View.GONE);
        }
        tabLayout.addTab(tabLayout.newTab().setText("FRESHER"));
        tabLayout.addTab(tabLayout.newTab().setText("EXPERIENCED"));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Objective_fragment.AnonymousClass10 */

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 1) {
                    Objective_fragment.this.objective.setText("");
                    if (tabLayout.getSelectedTabPosition() == 0) {
                        Objective_fragment.this.objective.setText((CharSequence) Arrays.asList(Objective_fragment.this.getResources().getStringArray(R.array.fresh)).get(i));
                    } else {
                        Objective_fragment.this.objective.setText((CharSequence) Arrays.asList(Objective_fragment.this.getResources().getStringArray(R.array.exp)).get(i));
                    }
                    Objective_fragment.this.objective.setError(null);
                } else {
                    Objective_fragment.this.declaration.setText("");
                    Objective_fragment.this.declaration.setText((CharSequence) Arrays.asList(Objective_fragment.this.getResources().getStringArray(R.array.declar)).get(i));
                    Objective_fragment.this.declaration.setError(null);
                }
                dialog.dismiss();
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Objective_fragment.AnonymousClass11 */

            @Override // android.support.design.widget.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override // android.support.design.widget.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override // android.support.design.widget.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                if (tabLayout.getSelectedTabPosition() == 0) {
                    listView.setAdapter((ListAdapter) new nithra.resume.maker.cv.builder.app.Adapter.ListAdapter(Objective_fragment.context, R.layout.dialog_objective, Arrays.asList(Objective_fragment.this.getResources().getStringArray(R.array.fresh))));
                } else if (tabLayout.getSelectedTabPosition() == 1) {
                    listView.setAdapter((ListAdapter) new nithra.resume.maker.cv.builder.app.Adapter.ListAdapter(Objective_fragment.context, R.layout.dialog_objective, Arrays.asList(Objective_fragment.this.getResources().getStringArray(R.array.exp))));
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Objective_fragment.AnonymousClass12 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Objective_fragment.AnonymousClass13 */

            public void onDismiss(DialogInterface dialogInterface) {
                Objective_fragment.this.getFragmentManager().beginTransaction().detach(Objective_fragment.this).attach(Objective_fragment.this).commit();
            }
        });
    }
}
