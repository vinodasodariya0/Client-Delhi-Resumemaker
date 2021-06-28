package nithra.resume.maker.cv.builder.app.Fragment;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;

import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import nithra.resume.maker.cv.builder.app.Activity.Resume_Activity;
import nithra.resume.maker.cv.builder.app.Adapter.Work_ListAdapter;
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

public class Work_Exp_fragment extends Fragment implements OnStartDragListener, Visible {
    static SQLiteDatabase DB;
    static String SHOWCASE_ID = "Academic";
    static Personal_ListAdapter add_Adapter;
    static CardView add_more_work;
    static Activity context;
    static RelativeLayout cycle_view;
    static List<DB_Items> get_values = new ArrayList();
    static List<DB_Items> getwork = new ArrayList();
    static int i = 0;
    static LinearLayout linear;
    static List<DB_Items> more_data = new ArrayList();
    static List<DB_Items> names = new ArrayList();
    static List<Integer> remove_work = new ArrayList();
    static ScrollView scroll;
    static SharedPreference sp = new SharedPreference();
    static List<AppCompatTextView> title_list = new ArrayList();
    static String type;
    static List<AppCompatEditText> value_list = new ArrayList();
    static CheckBox work_cur_employee;
    static AppCompatEditText work_desig;
    static AppCompatEditText work_end_date;
    static LinearLayout work_end_date_linear;
    static AppCompatEditText work_organ;
    static CheckBox work_prev_employee;
    static AppCompatEditText work_role;
    static Button work_save;
    static AppCompatEditText work_start_date;
    static AppCompatTextView work_title;
    DatePickerDialog.OnDateSetListener date1;
    DatePickerDialog.OnDateSetListener date2;
    Dialog exit_dialog;
    String insert_type;
    Work_ListAdapter mAdapter;
    Add_ListAdapter mAdapter1;
    ItemTouchHelper mItemTouchHelper;
    ItemTouchHelper mItemTouchHelper1;
    Calendar myCalendar;
    Calendar myCalendar1;
    AppCompatTextView title;
    AppCompatEditText value;
    CardView work_add_view;
    RecyclerView work_cycle;
    ImageView work_edit;
    LinearLayout work_edit_view;
    ExpandableHeightRecyclerView work_more_recycle;
    LinearLayout work_start_date_linear;
    ImageView work_update;
    AppCompatEditText work_update_title;

    public static int getItem(int i2) {
        return 0;
    }

    public static float dpToPixels(int i2, Context context2) {
        return ((float) i2) * context2.getResources().getDisplayMetrics().density;
    }

    public static void hideSoftKeyboard(Activity activity) {
        ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void load_academic(int i2) {
        int i3;
        String string;
        scroll.setVisibility(View.VISIBLE);
        cycle_view.setVisibility(View.GONE);
        scroll.fullScroll(33);
        SQLiteDatabase sQLiteDatabase = DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select work_title from title_table where profile_id='" + sp.getString(context, "profile_id") + "'", null);
        rawQuery.moveToFirst();
        if (rawQuery.getCount() > 0 && (string = rawQuery.getString(rawQuery.getColumnIndex("work_title"))) != null) {
            AppCompatTextView appCompatTextView = work_title;
            appCompatTextView.setText("" + string);
        }
        rawQuery.close();
        SQLiteDatabase sQLiteDatabase2 = DB;
        Cursor rawQuery2 = sQLiteDatabase2.rawQuery("Select * from work_table where extra_id='" + i2 + "' and profile_id='" + sp.getString(context, "profile_id") + "'", null);
        rawQuery2.moveToFirst();
        if (rawQuery2.getCount() > 0) {
            i3 = rawQuery2.getInt(rawQuery2.getColumnIndex("work_id"));
            AppCompatEditText appCompatEditText = work_organ;
            appCompatEditText.setText("" + rawQuery2.getString(rawQuery2.getColumnIndex("organization")));
            AppCompatEditText appCompatEditText2 = work_desig;
            appCompatEditText2.setText("" + rawQuery2.getString(rawQuery2.getColumnIndex("designation")));
            type = "" + rawQuery2.getString(rawQuery2.getColumnIndex("emptype"));
            if (type.equals("previous")) {
                work_prev_employee.setChecked(true);
                work_cur_employee.setChecked(false);
                work_end_date_linear.setEnabled(true);
            } else {
                work_cur_employee.setChecked(true);
                work_prev_employee.setChecked(false);
                work_end_date_linear.setEnabled(false);
            }
            AppCompatEditText appCompatEditText3 = work_start_date;
            appCompatEditText3.setText("" + rawQuery2.getString(rawQuery2.getColumnIndex("date_of_join")));
            AppCompatEditText appCompatEditText4 = work_end_date;
            appCompatEditText4.setText("" + rawQuery2.getString(rawQuery2.getColumnIndex("end_date")));
            AppCompatEditText appCompatEditText5 = work_role;
            appCompatEditText5.setText("" + rawQuery2.getString(rawQuery2.getColumnIndex("role")));
            work_save.setText("UPDATE");
        } else {
            work_save.setText("SAVE");
            i3 = 0;
        }
        rawQuery2.close();
        i = 0;
        if (linear.getChildCount() > 0) {
            linear.removeAllViews();
        }
        sp.putInt(context, "work_id", i3);
        Update_Load(i3, more_data);
    }

    public static void check() {
        String string;
        SQLiteDatabase sQLiteDatabase = DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select work_title from title_table where profile_id='" + sp.getString(context, "profile_id") + "'", null);
        rawQuery.moveToFirst();
        if (rawQuery.getCount() > 0 && (string = rawQuery.getString(rawQuery.getColumnIndex("work_title"))) != null) {
            AppCompatTextView appCompatTextView = work_title;
            appCompatTextView.setText("" + string);
        }
        rawQuery.close();
        SQLiteDatabase sQLiteDatabase2 = DB;
        Cursor rawQuery2 = sQLiteDatabase2.rawQuery("SELECT * FROM work_table where profile_id='" + sp.getString(context, "profile_id") + "'", null);
        if (rawQuery2.getCount() == 0) {
            scroll.setVisibility(View.VISIBLE);
            cycle_view.setVisibility(View.GONE);
            sp.putInt(context, "work_id", 0);
            work_organ.setText("");
            work_desig.setText("");
            work_start_date.setText("");
            work_end_date.setText("");
            work_role.setText("");
            work_prev_employee.setChecked(true);
            work_cur_employee.setChecked(false);
            work_save.setText("SAVE");
            remove_work.clear();
            getwork.clear();
            title_list.clear();
            value_list.clear();
            more_data.clear();
            add_Adapter.notifyDataSetChanged();
        } else if (rawQuery2.moveToFirst()) {
            for (int i2 = 0; i2 < rawQuery2.getCount(); i2++) {
                if (rawQuery2.getString(rawQuery2.getColumnIndex("organization")).trim().equals("")) {
                    scroll.setVisibility(View.VISIBLE);
                    cycle_view.setVisibility(View.GONE);
                    load_academic(rawQuery2.getInt(rawQuery2.getColumnIndex("extra_id")));
                } else {
                    scroll.setVisibility(View.GONE);
                    cycle_view.setVisibility(View.VISIBLE);
                    if (rawQuery2.moveToFirst()) {
                        names.clear();
                        for (int i3 = 0; i3 < rawQuery2.getCount(); i3++) {
                            DB_Items dB_Items = new DB_Items();
                            dB_Items.setWorkid(rawQuery2.getInt(rawQuery2.getColumnIndex("work_id")));
                            dB_Items.setOrganization(rawQuery2.getString(rawQuery2.getColumnIndex("organization")));
                            dB_Items.setDesignation(rawQuery2.getString(rawQuery2.getColumnIndex("designation")));
                            dB_Items.setType(rawQuery2.getString(rawQuery2.getColumnIndex("emptype")));
                            dB_Items.setJoin_date(rawQuery2.getString(rawQuery2.getColumnIndex("date_of_join")));
                            dB_Items.setEnd_date(rawQuery2.getString(rawQuery2.getColumnIndex("end_date")));
                            dB_Items.setRole(rawQuery2.getString(rawQuery2.getColumnIndex("role")));
                            dB_Items.setExtra_id(rawQuery2.getString(rawQuery2.getColumnIndex("extra_id")));
                            names.add(dB_Items);
                            rawQuery2.moveToNext();
                        }
                        rawQuery2.moveToPosition(i2);
                    }
                    if (!sp.getString(context, "status1").equals("add_more")) {
                        if (rawQuery2.getCount() - 1 == i2 && rawQuery2.getCount() > 0) {
                            Showcase();
                            sp.putString(context, "status1", "add_more");
                        }
                    } else if (!sp.getString(context, "drag").equals("draged") && rawQuery2.getCount() - 1 == i2 && rawQuery2.getCount() > 1) {
                        drag_dialog();
                    }
                }
                rawQuery2.moveToNext();
            }
        }
        rawQuery2.close();
    }

    public static void Update_Load(int i2, List<DB_Items> list) {
        list.clear();
        SQLiteDatabase sQLiteDatabase = DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from extra_table where profile_id='" + sp.getString(context, "profile_id") + "' and table_id='" + i2 + "' and table_name='work_table'", null);
        rawQuery.moveToPosition(0);
        if (rawQuery.getCount() > 0 && rawQuery.moveToFirst()) {
            for (int i3 = 0; i3 < rawQuery.getCount(); i3++) {
                DB_Items dB_Items = new DB_Items();
                dB_Items.setTitle(rawQuery.getString(rawQuery.getColumnIndex("title")));
                dB_Items.setValue(rawQuery.getString(rawQuery.getColumnIndex("value")));
                list.add(dB_Items);
                rawQuery.moveToNext();
            }
        }
        rawQuery.close();
        add_Adapter.notifyDataSetChanged();
    }

    public static void Update_Load(int i2, LinearLayout linearLayout, final List<Integer> list, List<DB_Items> list2, final List<AppCompatTextView> list3, final List<AppCompatEditText> list4) {
        Cursor cursor;
        LinearLayout linearLayout2 = linearLayout;
        List<DB_Items> list5 = list2;
        list.clear();
        list2.clear();
        list3.clear();
        list4.clear();
        SQLiteDatabase sQLiteDatabase = DB;
        ViewGroup viewGroup = null;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from extra_table where profile_id='" + sp.getString(context, "profile_id") + "' and table_id='" + i2 + "' and table_name='work_table'", null);
        boolean z = false;
        rawQuery.moveToPosition(0);
        if (rawQuery.getCount() > 0) {
            if (rawQuery.moveToFirst()) {
                for (int i3 = 0; i3 < rawQuery.getCount(); i3++) {
                    DB_Items dB_Items = new DB_Items();
                    dB_Items.setTitle(rawQuery.getString(rawQuery.getColumnIndex("title")));
                    dB_Items.setValue(rawQuery.getString(rawQuery.getColumnIndex("value")));
                    list5.add(dB_Items);
                    rawQuery.moveToNext();
                }
                rawQuery.close();
            }
            i = list2.size();
            int i4 = 0;
            while (i4 < list2.size()) {
                final View inflate = LayoutInflater.from(context).inflate(R.layout.item_add_more, viewGroup, z);
                final AppCompatEditText appCompatEditText = (AppCompatEditText) inflate.findViewById(R.id.extra);
                final AppCompatEditText appCompatEditText2 = (AppCompatEditText) inflate.findViewById(R.id.en_title);
                final AppCompatTextView appCompatTextView = (AppCompatTextView) inflate.findViewById(R.id.title);
                ImageView imageView = (ImageView) inflate.findViewById(R.id.title_edit);
                ImageView imageView2 = (ImageView) inflate.findViewById(R.id.update_title);
                ImageView imageView3 = (ImageView) inflate.findViewById(R.id.del_view);
                final LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.en_value);
                final LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(R.id.name_dia);
                appCompatEditText.setId(i4);
                appCompatTextView.setId(i4);
                int i5 = z ? 1 : 0;
                int i6 = z ? 1 : 0;
                int i7 = z ? 1 : 0;
                ((LinearLayout) inflate.findViewById(R.id.first)).setVisibility(i5);
                appCompatEditText.setText(list5.get(i4).getValue());
                appCompatTextView.setText(list5.get(i4).getTitle());
                linearLayout2.addView(inflate);
                list4.add(appCompatEditText);
                list3.add(appCompatTextView);
                linearLayout2.setLayoutTransition(new LayoutTransition());
                imageView3.setOnClickListener(new View.OnClickListener() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass1 */

                    public void onClick(View view) {
                        ((LinearLayout) inflate.getParent()).removeView(inflate);
                        list.add(Integer.valueOf(appCompatEditText.getId()));
                        list3.remove(appCompatEditText.getId());
                        list4.remove(appCompatTextView.getId());
                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass2 */

                    public void onClick(View view) {
                        appCompatEditText2.setText("");
                        if (linearLayout4.isShown()) {
                            linearLayout4.setVisibility(View.GONE);
                            linearLayout3.setVisibility(View.VISIBLE);
                            return;
                        }
                        linearLayout4.setVisibility(View.VISIBLE);
                        linearLayout3.setVisibility(View.GONE);
                    }
                });
                imageView2.setOnClickListener(new View.OnClickListener() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass3 */

                    public void onClick(View view) {
                        appCompatTextView.setText(appCompatEditText2.getText().toString());

                        appCompatEditText.setHint("Enter " + appCompatEditText2.getText().toString());
                        linearLayout4.setVisibility(View.GONE);
                        linearLayout3.setVisibility(View.VISIBLE);
                    }
                });
                i4++;
                rawQuery = rawQuery;
                linearLayout2 = linearLayout;
                list5 = list2;
                z = false;
                viewGroup = null;
            }
            cursor = rawQuery;
        } else {
            cursor = rawQuery;
        }
        cursor.close();
    }

    public static void drag_dialog() {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_guid);
        final Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.move1);
        final CardView cardView = (CardView) dialog.findViewById(R.id.card1);
        CardView cardView2 = (CardView) dialog.findViewById(R.id.card2);
        final ImageView imageView = (ImageView) dialog.findViewById(R.id.drag);
        imageView.setAnimation(zoomAnim());
        final Animation loadAnimation2 = AnimationUtils.loadAnimation(context, R.anim.move);
        cardView.startAnimation(loadAnimation2);
        imageView.startAnimation(loadAnimation2);
        loadAnimation2.setAnimationListener(new Animation.AnimationListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass4 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass4.AnonymousClass1 */

                    public void run() {
                        cardView.startAnimation(loadAnimation);
                        imageView.startAnimation(loadAnimation);
                    }
                }, 1500);
            }
        });
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass5 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass5.AnonymousClass1 */

                    public void run() {
                        cardView.startAnimation(loadAnimation2);
                        imageView.startAnimation(loadAnimation2);
                    }
                }, 1500);
            }
        });
        ((Button) dialog.findViewById(R.id.enter)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass6 */

            public void onClick(View view) {
                Work_Exp_fragment.sp.putString(Work_Exp_fragment.context, "drag", "draged");
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    public static Animation zoomAnim() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.9f, 0.83f, 0.9f, 0.83f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimation.setDuration(500);
        scaleAnimation.setRepeatCount(-1);
        scaleAnimation.setRepeatMode(2);
        return scaleAnimation;
    }

    private static void Showcase() {
        ShowcaseConfig showcaseConfig = new ShowcaseConfig();
        showcaseConfig.setDelay(500);
        MaterialShowcaseSequence materialShowcaseSequence = new MaterialShowcaseSequence(context, SHOWCASE_ID);
        materialShowcaseSequence.setConfig(showcaseConfig);
        materialShowcaseSequence.addSequenceItem(new MaterialShowcaseView.Builder(context).setTarget(add_more_work).setDismissText("OK").setContentText("Click here to add more work experience").withRectangleShape().setDismissTextColor(context.getResources().getColor(R.color.red)).setMaskColour(R.color.colorAccent).setDismissStyle(Typeface.createFromAsset(context.getAssets(), "Chunkfive.otf")).build());
        materialShowcaseSequence.start();
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        context = getActivity();
        View inflate = layoutInflater.inflate(R.layout.fragment_work_exp, viewGroup, false);
        this.work_edit = (ImageView) inflate.findViewById(R.id.work_edit);
        this.work_edit_view = (LinearLayout) inflate.findViewById(R.id.work_edit_view);
        this.work_update = (ImageView) inflate.findViewById(R.id.work_update);
        this.work_update_title = (AppCompatEditText) inflate.findViewById(R.id.work_update_title);
        work_title = (AppCompatTextView) inflate.findViewById(R.id.work_title);
        linear = (LinearLayout) inflate.findViewById(R.id.linear);
        this.work_add_view = (CardView) inflate.findViewById(R.id.work_add_view);
        scroll = (ScrollView) inflate.findViewById(R.id.scroll);
        work_organ = (AppCompatEditText) inflate.findViewById(R.id.work_organ);
        work_desig = (AppCompatEditText) inflate.findViewById(R.id.work_desig);
        work_start_date = (AppCompatEditText) inflate.findViewById(R.id.work_start_date);
        work_end_date = (AppCompatEditText) inflate.findViewById(R.id.work_end_date);
        work_role = (AppCompatEditText) inflate.findViewById(R.id.work_role);
        work_prev_employee = (CheckBox) inflate.findViewById(R.id.work_prev_employee);
        work_cur_employee = (CheckBox) inflate.findViewById(R.id.work_cur_employee);
        work_save = (Button) inflate.findViewById(R.id.work_save);
        this.work_start_date_linear = (LinearLayout) inflate.findViewById(R.id.work_start_date_linear);
        work_end_date_linear = (LinearLayout) inflate.findViewById(R.id.work_end_date_linear);
        add_more_work = (CardView) inflate.findViewById(R.id.add_more_work);
        this.work_cycle = (RecyclerView) inflate.findViewById(R.id.work_cycle);
        this.work_more_recycle = (ExpandableHeightRecyclerView) inflate.findViewById(R.id.work_more_recycle);
        cycle_view = (RelativeLayout) inflate.findViewById(R.id.cycle_view);
        DB = context.openOrCreateDatabase("RESUME_BUILDER", 0, null);
        this.work_more_recycle.setExpanded(true);
        more_data.clear();
        this.work_more_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        add_Adapter = new Personal_ListAdapter(context, this, more_data);
        this.work_more_recycle.setAdapter(add_Adapter);
        this.mItemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(add_Adapter));
        this.mItemTouchHelper.attachToRecyclerView(this.work_more_recycle);
        this.work_more_recycle.setHasFixedSize(true);
        try {
            check();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.work_cycle.setHasFixedSize(true);
        work_prev_employee.setChecked(true);
        work_end_date.setClickable(false);
        if (work_prev_employee.isChecked()) {
            work_cur_employee.setChecked(false);
        } else if (work_cur_employee.isChecked()) {
            work_prev_employee.setChecked(false);
        }
        this.myCalendar = Calendar.getInstance();
        this.myCalendar1 = Calendar.getInstance();
        this.date1 = new DatePickerDialog.OnDateSetListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass7 */

            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Work_Exp_fragment.this.myCalendar.set(1, i);
                Work_Exp_fragment.this.myCalendar.set(2, i2);
                Work_Exp_fragment.this.myCalendar.set(5, i3);
                Work_Exp_fragment.work_start_date.setText(new SimpleDateFormat("dd - MM - yyyy").format(Work_Exp_fragment.this.myCalendar.getTime()));
            }
        };
        this.date2 = new DatePickerDialog.OnDateSetListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass8 */

            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Work_Exp_fragment.this.myCalendar1.set(1, i);
                Work_Exp_fragment.this.myCalendar1.set(2, i2);
                Work_Exp_fragment.this.myCalendar1.set(5, i3);
                Work_Exp_fragment.work_end_date.setText(new SimpleDateFormat("dd - MM - yyyy").format(Work_Exp_fragment.this.myCalendar1.getTime()));
            }
        };
        this.work_cycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        this.mAdapter = new Work_ListAdapter(context, this, names);
        this.work_cycle.setAdapter(this.mAdapter);
        this.mAdapter.notifyDataSetChanged();
        this.mItemTouchHelper1 = new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.mAdapter));
        this.mItemTouchHelper1.attachToRecyclerView(this.work_cycle);
        add_more_work.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass9 */

            public void onClick(View view) {
                Resume_Activity.back_flag = 0;
                Work_Exp_fragment.scroll.setVisibility(View.VISIBLE);
                Work_Exp_fragment.cycle_view.setVisibility(View.GONE);
                Work_Exp_fragment.sp.putInt(Work_Exp_fragment.context, "work_id", 0);
                Work_Exp_fragment.work_organ.setText("");
                Work_Exp_fragment.work_desig.setText("");
                Work_Exp_fragment.work_start_date.setText("");
                Work_Exp_fragment.work_end_date.setText("");
                Work_Exp_fragment.work_role.setText("");
                Work_Exp_fragment.work_prev_employee.setChecked(true);
                Work_Exp_fragment.work_cur_employee.setChecked(false);
                Work_Exp_fragment.work_save.setText("SAVE");
                Work_Exp_fragment.remove_work.clear();
                Work_Exp_fragment.getwork.clear();
                Work_Exp_fragment.title_list.clear();
                Work_Exp_fragment.value_list.clear();
                Work_Exp_fragment.more_data.clear();
                Work_Exp_fragment.add_Adapter.notifyDataSetChanged();
                Work_Exp_fragment.scroll.fullScroll(33);
                Work_Exp_fragment.i = 0;
                if (Work_Exp_fragment.linear.getChildCount() > 0) {
                    Work_Exp_fragment.linear.removeAllViews();
                }
            }
        });
        work_prev_employee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass10 */

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    Work_Exp_fragment.work_cur_employee.setChecked(false);
                    Work_Exp_fragment.work_end_date.setText("");
                    Work_Exp_fragment.work_end_date_linear.setEnabled(true);
                    Work_Exp_fragment.work_end_date.setClickable(true);
                    return;
                }
                Work_Exp_fragment.work_cur_employee.setChecked(true);
                Work_Exp_fragment.work_end_date.setText("Still date");
                Work_Exp_fragment.work_end_date_linear.setEnabled(false);
                Work_Exp_fragment.work_end_date.setClickable(false);
            }
        });
        work_cur_employee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass11 */

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    Work_Exp_fragment.work_prev_employee.setChecked(false);
                    Work_Exp_fragment.work_end_date.setText("Still date");
                    Work_Exp_fragment.work_end_date_linear.setEnabled(false);
                    Work_Exp_fragment.work_end_date.setClickable(false);
                    return;
                }
                Work_Exp_fragment.work_prev_employee.setChecked(true);
                Work_Exp_fragment.work_end_date.setText("");
                Work_Exp_fragment.work_end_date_linear.setEnabled(true);
                Work_Exp_fragment.work_end_date.setClickable(true);
            }
        });
        this.work_edit_view.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass12 */

            public void onClick(View view) {
            }
        });
        this.work_edit.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass13 */

            public void onClick(View view) {
                Work_Exp_fragment work_Exp_fragment = Work_Exp_fragment.this;
                work_Exp_fragment.showSoftKeyboard(work_Exp_fragment.work_update_title);
                Work_Exp_fragment.this.work_update_title.setText(Work_Exp_fragment.work_title.getText().toString());
                Work_Exp_fragment.this.work_update_title.setSelection(Work_Exp_fragment.work_title.getText().toString().length());
                if (Work_Exp_fragment.this.work_edit_view.isShown()) {
                    Work_Exp_fragment.this.hideSoftKeyboard();
                    Work_Exp_fragment.this.work_edit_view.setVisibility(View.GONE);
                    return;
                }
                Work_Exp_fragment.this.work_update_title.clearFocus();
                Work_Exp_fragment.this.work_update_title.setError(null);
                Work_Exp_fragment work_Exp_fragment2 = Work_Exp_fragment.this;
                work_Exp_fragment2.showSoftKeyboard(work_Exp_fragment2.work_update_title);
                Work_Exp_fragment.this.work_edit_view.setVisibility(View.VISIBLE);
            }
        });
        this.work_update_title.setOnKeyListener(new View.OnKeyListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass14 */

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 6) {
                    return false;
                }
                Work_Exp_fragment.this.hideSoftKeyboard();
                return false;
            }
        });
        this.work_update.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass15 */

            public void onClick(View view) {
                if (Work_Exp_fragment.this.work_update_title.getText().toString().trim().isEmpty()) {
                    Work_Exp_fragment.this.work_update_title.setError("Enter your title..");
                    Work_Exp_fragment.this.work_update_title.requestFocus();
                    return;
                }
                Work_Exp_fragment.work_title.setText(Work_Exp_fragment.this.work_update_title.getText().toString());
                SQLiteDatabase sQLiteDatabase = Work_Exp_fragment.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("Select work_title from title_table where profile_id='" + Work_Exp_fragment.sp.getString(Work_Exp_fragment.context, "profile_id") + "'", null);
                if (rawQuery.getCount() == 0) {
                    SQLiteDatabase sQLiteDatabase2 = Work_Exp_fragment.DB;
                    sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, work_title) VALUES ('" + Work_Exp_fragment.sp.getString(Work_Exp_fragment.context, "profile_id") + "', '" + Work_Exp_fragment.work_title.getText().toString().replaceAll("'", "''") + "');");
                } else {
                    SQLiteDatabase sQLiteDatabase3 = Work_Exp_fragment.DB;
                    sQLiteDatabase3.execSQL("UPDATE title_table SET work_title = '" + Work_Exp_fragment.work_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + Work_Exp_fragment.sp.getString(Work_Exp_fragment.context, "profile_id") + "';");
                }
                rawQuery.close();
                Work_Exp_fragment.this.work_edit_view.setVisibility(View.GONE);
                Work_Exp_fragment.this.hideSoftKeyboard();
            }
        });
        this.work_add_view.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass16 */

            public void onClick(View view) {
                Work_Exp_fragment.work_start_date.setError(null);
                Work_Exp_fragment.work_end_date.setError(null);
                if (Work_Exp_fragment.work_prev_employee.isChecked()) {
                    Work_Exp_fragment.this.insert_type = "previous";
                } else if (Work_Exp_fragment.work_cur_employee.isChecked()) {
                    Work_Exp_fragment.this.insert_type = "current";
                }
                Work_Exp_fragment.this.final_save();
                Work_Exp_fragment.this.hideSoftKeyboard();
                Work_Exp_fragment.this.add_dialog("", "", 0);
            }
        });
        work_save.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass17 */

            public void onClick(View view) {
                String obj = Work_Exp_fragment.work_organ.getText().toString();
                String obj2 = Work_Exp_fragment.work_desig.getText().toString();
                String obj3 = Work_Exp_fragment.work_start_date.getText().toString();
                String obj4 = Work_Exp_fragment.work_end_date.getText().toString();
                Work_Exp_fragment.work_role.getText().toString();
                if (obj.trim().equals("")) {
                    Work_Exp_fragment.work_end_date.setError(null);
                    Work_Exp_fragment.work_start_date.setError(null);
                    Work_Exp_fragment.work_organ.setError("Enter Your Organization Name !!!");
                    Work_Exp_fragment.work_organ.requestFocus();
                } else if (obj2.trim().equals("")) {
                    Work_Exp_fragment.work_end_date.setError(null);
                    Work_Exp_fragment.work_start_date.setError(null);
                    Work_Exp_fragment.work_desig.setError("Enter Your Designation !!!");
                    Work_Exp_fragment.work_desig.requestFocus();
                } else if (obj3.trim().equals("")) {
                    Work_Exp_fragment.work_start_date.setError("Enter your Joining Date !!!");
                    Work_Exp_fragment.work_start_date.requestFocus();
                } else if (obj4.trim().equals("")) {
                    Work_Exp_fragment.work_start_date.setError(null);
                    Work_Exp_fragment.work_end_date.setError("Enter your Relieved Date !!!");
                    Work_Exp_fragment.work_end_date.requestFocus();
                } else {
                    Work_Exp_fragment.work_start_date.setError(null);
                    Work_Exp_fragment.work_end_date.setError(null);
                    if (Work_Exp_fragment.work_prev_employee.isChecked()) {
                        Work_Exp_fragment.this.insert_type = "previous";
                    } else if (Work_Exp_fragment.work_cur_employee.isChecked()) {
                        Work_Exp_fragment.this.insert_type = "current";
                    }
                    Work_Exp_fragment.this.hideSoftKeyboard();
                    SQLiteDatabase sQLiteDatabase = Work_Exp_fragment.DB;
                    if (sQLiteDatabase.rawQuery("Select profile_id from work_table where work_id='" + Work_Exp_fragment.sp.getInt(Work_Exp_fragment.context, "work_id") + "' and profile_id='" + Work_Exp_fragment.sp.getString(Work_Exp_fragment.context, "profile_id") + "'", null).getCount() == 0) {
                        Utils.toast_center(Work_Exp_fragment.context, "Work Experience Saved Successfully");
                        Work_Exp_fragment.work_save.setText("UPDATE");
                    } else {
                        Utils.toast_center(Work_Exp_fragment.context, "Work Experience Updated Successfully");
                        Work_Exp_fragment.work_save.setText("UPDATE");
                    }
                    Work_Exp_fragment.this.final_save();
                    if (Work_Exp_fragment.linear.getChildCount() > 0) {
                        Work_Exp_fragment.linear.removeAllViews();
                    }
                    try {
                        Work_Exp_fragment.check();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Work_Exp_fragment.this.mAdapter.notifyDataSetChanged();
                }
            }
        });
        return inflate;
    }

    public void view_add(final int i2, final List<Integer> list, LinearLayout linearLayout, final List<AppCompatTextView> list2, final List<AppCompatEditText> list3) {
        final View inflate = LayoutInflater.from(context).inflate(R.layout.item_add_more, (ViewGroup) null, false);
        final AppCompatEditText appCompatEditText = (AppCompatEditText) inflate.findViewById(R.id.extra);
        final AppCompatEditText appCompatEditText2 = (AppCompatEditText) inflate.findViewById(R.id.en_title);
        final AppCompatTextView appCompatTextView = (AppCompatTextView) inflate.findViewById(R.id.title);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.del_view);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.title_edit);
        ImageView imageView3 = (ImageView) inflate.findViewById(R.id.update_title);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.first);
        final LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.name_dia);
        final LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(R.id.en_value);
        appCompatTextView.setText("Title " + (i2 + 1));
        linearLayout.addView(inflate);
        list3.add(appCompatEditText);
        list2.add(appCompatTextView);
        showSoftKeyboard(appCompatEditText);
        linearLayout.setLayoutTransition(new LayoutTransition());
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass18 */

            public void onClick(View view) {
                ((LinearLayout) inflate.getParent()).removeView(inflate);
                list.add(Integer.valueOf(i2));
                ((AppCompatEditText) list3.get(i2)).setText("");
                ((AppCompatTextView) list2.get(i2)).setText("");
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass19 */

            public void onClick(View view) {
                Work_Exp_fragment.this.showSoftKeyboard(appCompatEditText2);
                appCompatEditText2.setText("");
                if (linearLayout3.isShown()) {
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.VISIBLE);
                    return;
                }
                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.GONE);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass20 */

            public void onClick(View view) {
                Work_Exp_fragment.this.showSoftKeyboard(appCompatEditText);
                appCompatTextView.setText(appCompatEditText2.getText().toString());

                appCompatEditText.setHint("Enter " + appCompatEditText2.getText().toString());
                linearLayout3.setVisibility(View.GONE);
                linearLayout4.setVisibility(View.VISIBLE);
            }
        });
    }

    public void final_save() {
        SQLiteDatabase sQLiteDatabase = DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select work_title from title_table where profile_id='" + sp.getString(context, "profile_id") + "'", null);
        if (rawQuery.getCount() == 0) {
            SQLiteDatabase sQLiteDatabase2 = DB;
            sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, work_title) VALUES ('" + sp.getString(context, "profile_id") + "', '" + work_title.getText().toString().replaceAll("'", "''") + "');");
        } else {
            SQLiteDatabase sQLiteDatabase3 = DB;
            sQLiteDatabase3.execSQL("UPDATE title_table SET work_title = '" + work_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + sp.getString(context, "profile_id") + "';");
        }
        rawQuery.close();
        SQLiteDatabase sQLiteDatabase4 = DB;
        Cursor rawQuery2 = sQLiteDatabase4.rawQuery("Select profile_id from work_table where work_id='" + sp.getInt(context, "work_id") + "' and profile_id='" + sp.getString(context, "profile_id") + "'", null);
        int i2 = 0;
        if (rawQuery2.getCount() == 0) {
            SQLiteDatabase sQLiteDatabase5 = DB;
            Cursor rawQuery3 = sQLiteDatabase5.rawQuery("Select extra_id from work_table where profile_id='" + sp.getString(context, "profile_id") + "' order by extra_id DESC", null);
            int i3 = 1;
            if (rawQuery3.getCount() != 0) {
                rawQuery3.moveToFirst();
                i3 = 1 + rawQuery3.getInt(rawQuery3.getColumnIndex("extra_id"));
            }
            SQLiteDatabase sQLiteDatabase6 = DB;
            sQLiteDatabase6.execSQL("INSERT INTO work_table (profile_id, organization, designation, emptype, date_of_join, end_date, role, extra_id) VALUES ('" + sp.getString(context, "profile_id") + "', '" + work_organ.getText().toString().replaceAll("'", "''") + "', '" + work_desig.getText().toString().replaceAll("'", "''") + "', '" + this.insert_type + "', '" + work_start_date.getText().toString().replaceAll("'", "''") + "', '" + work_end_date.getText().toString().replaceAll("'", "''") + "', '" + work_role.getText().toString().replaceAll("'", "''") + "','" + i3 + "');");
            Cursor rawQuery4 = DB.rawQuery("select work_id from work_table", null);
            rawQuery4.moveToLast();
            int i4 = rawQuery4.getInt(0);
            sp.putInt(context, "work_id", i4);
            rawQuery4.close();
            SQLiteDatabase sQLiteDatabase7 = DB;
            StringBuilder sb = new StringBuilder();
            sb.append("Select table_id from extra_table where profile_id='");
            sb.append(sp.getString(context, "profile_id"));
            sb.append("' and table_id='");
            sb.append(i4);
            sb.append("' and table_name='work_table'");
            Cursor rawQuery5 = sQLiteDatabase7.rawQuery(sb.toString(), null);
            if (rawQuery5.getCount() == 0) {
                while (i2 < more_data.size()) {
                    if (!more_data.get(i2).getTitle().equals("") && !more_data.get(i2).getTitle().equals(null) && !more_data.get(i2).getValue().equals("") && !more_data.get(i2).getValue().equals(null)) {
                        SQLiteDatabase sQLiteDatabase8 = DB;
                        sQLiteDatabase8.execSQL("INSERT INTO extra_table (profile_id, table_id, table_name, title, value) VALUES ('" + sp.getString(context, "profile_id") + "', '" + i4 + "','work_table','" + more_data.get(i2).getTitle().replaceAll("'", "''") + "','" + more_data.get(i2).getValue().replaceAll("'", "''") + "');");
                    }
                    i2++;
                }
            }
            rawQuery5.close();
            rawQuery3.close();
        } else {
            SQLiteDatabase sQLiteDatabase9 = DB;
            sQLiteDatabase9.execSQL("UPDATE work_table SET organization = '" + work_organ.getText().toString().replaceAll("'", "''") + "', designation = '" + work_desig.getText().toString().replaceAll("'", "''") + "', emptype = '" + this.insert_type + "', date_of_join = '" + work_start_date.getText().toString().replaceAll("'", "''") + "', end_date = '" + work_end_date.getText().toString().replaceAll("'", "''") + "', role = '" + work_role.getText().toString().replaceAll("'", "''") + "' WHERE work_id = '" + sp.getInt(context, "work_id") + "';");
            SQLiteDatabase sQLiteDatabase10 = DB;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("delete from extra_table where profile_id='");
            sb2.append(sp.getString(context, "profile_id"));
            sb2.append("' and table_id='");
            sb2.append(sp.getInt(context, "work_id"));
            sb2.append("' and table_name='work_table';");
            sQLiteDatabase10.execSQL(sb2.toString());
            while (i2 < more_data.size()) {
                if (!more_data.get(i2).getTitle().equals("") && !more_data.get(i2).getTitle().equals(null) && !more_data.get(i2).getValue().equals("") && !more_data.get(i2).getValue().equals(null)) {
                    SQLiteDatabase sQLiteDatabase11 = DB;
                    sQLiteDatabase11.execSQL("INSERT INTO extra_table (profile_id, table_id, table_name, title, value) VALUES ('" + sp.getString(context, "profile_id") + "', '" + sp.getInt(context, "work_id") + "','work_table','" + more_data.get(i2).getTitle().replaceAll("'", "''") + "','" + more_data.get(i2).getValue().replaceAll("'", "''") + "');");
                }
                i2++;
            }
        }
        rawQuery2.close();
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

    @Override // nithra.resume.maker.cv.builder.app.Touch.OnStartDragListener
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        this.mItemTouchHelper.startDrag(viewHolder);
        this.mItemTouchHelper1.startDrag(viewHolder);
    }

    @Override // nithra.resume.maker.cv.builder.app.Visible
    public void visible() {
        if (this.work_edit_view.isShown()) {
            this.work_edit_view.setVisibility(View.GONE);
        } else if (scroll.isShown()) {
            SQLiteDatabase sQLiteDatabase = DB;
            if (sQLiteDatabase.rawQuery("SELECT * FROM work_table where profile_id='" + sp.getString(context, "profile_id") + "'", null).getCount() == 0) {
                exti_result_dialog();
                return;
            }
            Cursor rawQuery = DB.rawQuery("select * from work_table", null);
            if (rawQuery.moveToLast()) {
                if (rawQuery.getString(rawQuery.getColumnIndex("organization")).trim().equals("")) {
                    SQLiteDatabase sQLiteDatabase2 = DB;
                    sQLiteDatabase2.execSQL("delete from work_table where profile_id='" + sp.getString(context, "profile_id") + "' and work_id='" + sp.getInt(context, "work_id") + "';");
                    SQLiteDatabase sQLiteDatabase3 = DB;
                    sQLiteDatabase3.execSQL("delete from extra_table where profile_id='" + sp.getString(context, "profile_id") + "' and table_id='" + sp.getInt(context, "work_id") + "' and table_name='work_table';");
                    check();
                    scroll.setVisibility(View.GONE);
                    cycle_view.setVisibility(View.VISIBLE);
                } else {
                    scroll.setVisibility(View.GONE);
                    cycle_view.setVisibility(View.VISIBLE);
                }
            }
            check();
            rawQuery.close();
        } else {
            exti_result_dialog();
        }
    }

    public void exti_result_dialog() {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_exit);
        MainActivity.load_addFromMain_natt(context, (FrameLayout) dialog.findViewById(R.id.ads_lay));
        ((TextView) dialog.findViewById(R.id.yes)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass21 */

            public void onClick(View view) {
                dialog.dismiss();
                Work_Exp_fragment.context.finish();
            }
        });
        ((TextView) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass22 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public void add_dialog(final String str, final String str2, final int i2) {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_add_view);
        this.title = (AppCompatTextView) dialog.findViewById(R.id.title);
        this.value = (AppCompatEditText) dialog.findViewById(R.id.value);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.ok);
        ImageView imageView2 = (ImageView) dialog.findViewById(R.id.cancel);
        this.title.setText(str);
        this.value.setText(str2);
        this.title.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass23 */

            public void onClick(View view) {
                Work_Exp_fragment.this.title.setError(null);
                Work_Exp_fragment.this.add_new_dialog();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass24 */

            public void onClick(View view) {
                if (Work_Exp_fragment.this.title.getText().toString().trim().equals("")) {
                    Work_Exp_fragment.this.title.setError("Title Required !!!");
                    Work_Exp_fragment.this.title.requestFocus();
                } else if (Work_Exp_fragment.this.value.getText().toString().trim().equals("")) {
                    AppCompatEditText appCompatEditText = Work_Exp_fragment.this.value;
                    appCompatEditText.setError("Enter your " + Work_Exp_fragment.this.title.getText().toString() + " here !!!");
                    Work_Exp_fragment.this.value.requestFocus();
                } else {
                    int i = Work_Exp_fragment.sp.getInt(Work_Exp_fragment.context, "work_id");
                    if (str.equals("")) {
                        SQLiteDatabase sQLiteDatabase = Work_Exp_fragment.DB;
                        if (sQLiteDatabase.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Work_Exp_fragment.sp.getString(Work_Exp_fragment.context, "profile_id") + "' and  upper(title)='" + Work_Exp_fragment.this.title.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='work_table' and table_id='" + i + "'", null).getCount() == 0) {
                            DB_Items dB_Items = new DB_Items();
                            dB_Items.setTitle(Work_Exp_fragment.this.title.getText().toString());
                            dB_Items.setValue(Work_Exp_fragment.this.value.getText().toString());
                            Work_Exp_fragment.more_data.add(dB_Items);
                            Work_Exp_fragment.add_Adapter.notifyDataSetChanged();
                            Work_Exp_fragment.this.final_save();
                            dialog.getWindow().setSoftInputMode(3);
                            Work_Exp_fragment.this.hideSoftKeyboard();
                            dialog.dismiss();
                        } else {
                            Work_Exp_fragment.this.title.setError("Already exist !!!");
                            Work_Exp_fragment.this.title.requestFocus();
                        }
                    } else if (!Work_Exp_fragment.this.title.getText().toString().equals(str)) {
                        SQLiteDatabase sQLiteDatabase2 = Work_Exp_fragment.DB;
                        if (sQLiteDatabase2.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Work_Exp_fragment.sp.getString(Work_Exp_fragment.context, "profile_id") + "' and  upper(title)='" + Work_Exp_fragment.this.title.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='work_table' and table_id='" + i + "'", null).getCount() == 0) {
                            Work_Exp_fragment.more_data.get(i2).setTitle(Work_Exp_fragment.this.title.getText().toString());
                            Work_Exp_fragment.more_data.get(i2).setValue(Work_Exp_fragment.this.value.getText().toString());
                            Work_Exp_fragment.add_Adapter.notifyDataSetChanged();
                            Work_Exp_fragment.this.final_save();
                            dialog.getWindow().setSoftInputMode(3);
                            Work_Exp_fragment.this.hideSoftKeyboard();
                            dialog.dismiss();
                        } else {
                            Work_Exp_fragment.this.title.setError("Already exist !!!");
                            Work_Exp_fragment.this.title.requestFocus();
                        }
                    } else if (!Work_Exp_fragment.this.value.getText().toString().equals(str2)) {
                        Work_Exp_fragment.more_data.get(i2).setTitle(Work_Exp_fragment.this.title.getText().toString());
                        Work_Exp_fragment.more_data.get(i2).setValue(Work_Exp_fragment.this.value.getText().toString());
                        Work_Exp_fragment.add_Adapter.notifyDataSetChanged();
                        Work_Exp_fragment.this.final_save();
                        dialog.getWindow().setSoftInputMode(3);
                        Work_Exp_fragment.this.hideSoftKeyboard();
                        dialog.dismiss();
                        Work_Exp_fragment.scroll.fullScroll(130);
                    } else {
                        Work_Exp_fragment.add_Adapter.notifyDataSetChanged();
                        Work_Exp_fragment.this.final_save();
                        Work_Exp_fragment.this.hideSoftKeyboard();
                        dialog.dismiss();
                        Work_Exp_fragment.scroll.fullScroll(130);
                    }
                }
                if (!Work_Exp_fragment.sp.getString(Work_Exp_fragment.context, "drag").equals("draged")) {
                    int i2 = Work_Exp_fragment.sp.getInt(Work_Exp_fragment.context, "work_id");
                    SQLiteDatabase sQLiteDatabase3 = Work_Exp_fragment.DB;
                    Cursor rawQuery = sQLiteDatabase3.rawQuery("Select table_id from extra_table where profile_id='" + Work_Exp_fragment.sp.getString(Work_Exp_fragment.context, "profile_id") + "' and table_id='" + i2 + "' and table_name='work_table'", null);
                    if (rawQuery.getCount() > 1) {
                        Work_Exp_fragment.drag_dialog();
                    }
                    rawQuery.close();
                }
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass25 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass26 */

            public void onDismiss(DialogInterface dialogInterface) {
                SQLiteDatabase sQLiteDatabase = Work_Exp_fragment.DB;
                if (sQLiteDatabase.rawQuery("Select table_id from extra_table where profile_id='" + Work_Exp_fragment.sp.getString(Work_Exp_fragment.context, "profile_id") + "' and table_id='" + Work_Exp_fragment.sp.getInt(Work_Exp_fragment.context, "work_id") + "' and table_name='work_table'", null).getCount() == 0) {
                    SQLiteDatabase sQLiteDatabase2 = Work_Exp_fragment.DB;
                    sQLiteDatabase2.execSQL("delete from work_table where profile_id='" + Work_Exp_fragment.sp.getString(Work_Exp_fragment.context, "profile_id") + "' and organization='';");
                }
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
        SQLiteDatabase sQLiteDatabase = DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from search_table where profile_id='" + sp.getString(context, "profile_id") + "' and table_name='work_table'", null);
        if (rawQuery.getCount() > 0) {
            get_values.clear();
            if (rawQuery.moveToFirst()) {
                for (int i2 = 0; i2 < rawQuery.getCount(); i2++) {
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass27 */

            public int compare(DB_Items dB_Items, DB_Items dB_Items2) {
                return dB_Items.getValue().compareTo(dB_Items2.getValue());
            }
        });
        this.mAdapter1 = new Add_ListAdapter(context, get_values);
        recyclerView.setAdapter(this.mAdapter1);
        this.mAdapter1.notifyDataSetChanged();
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass28 */

            public void onClick(View view) {
                new DB_Items().setValue(appCompatTextView.getText().toString());
                int i = Work_Exp_fragment.sp.getInt(Work_Exp_fragment.context, "work_id");
                if (!appCompatTextView.getText().toString().trim().equals("")) {
                    SQLiteDatabase sQLiteDatabase = Work_Exp_fragment.DB;
                    Cursor rawQuery = sQLiteDatabase.rawQuery("Select upper(title) as title from search_table where profile_id='" + Work_Exp_fragment.sp.getString(Work_Exp_fragment.context, "profile_id") + "' and  upper(title)='" + appCompatTextView.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='work_table'", null);
                    if (rawQuery.getCount() == 0) {
                        SQLiteDatabase sQLiteDatabase2 = Work_Exp_fragment.DB;
                        sQLiteDatabase2.execSQL("INSERT INTO search_table (profile_id, table_name, title) VALUES ('" + Work_Exp_fragment.sp.getString(Work_Exp_fragment.context, "profile_id") + "','work_table','" + appCompatTextView.getText().toString().replaceAll("'", "''") + "');");
                        SQLiteDatabase sQLiteDatabase3 = Work_Exp_fragment.DB;
                        Cursor rawQuery2 = sQLiteDatabase3.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Work_Exp_fragment.sp.getString(Work_Exp_fragment.context, "profile_id") + "' and  upper(title)='" + appCompatTextView.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='work_table' and table_id='" + i + "'", null);
                        if (rawQuery2.getCount() != 0) {
                            Activity activity = Work_Exp_fragment.context;
                            Utils.toast_center(activity, "" + appCompatTextView.getText().toString() + " already added...");
                        } else {
                            Work_Exp_fragment.this.title.setText(appCompatTextView.getText().toString());
                            AppCompatEditText appCompatEditText = Work_Exp_fragment.this.value;
                            appCompatEditText.setHint("Your " + appCompatTextView.getText().toString());
                            Work_Exp_fragment.this.exit_dialog.dismiss();
                        }
                        rawQuery2.close();
                    } else {
                        SQLiteDatabase sQLiteDatabase4 = Work_Exp_fragment.DB;
                        Cursor rawQuery3 = sQLiteDatabase4.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Work_Exp_fragment.sp.getString(Work_Exp_fragment.context, "profile_id") + "' and  upper(title)='" + appCompatTextView.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='work_table' and table_id='" + i + "'", null);
                        if (rawQuery3.getCount() != 0) {
                            Activity activity2 = Work_Exp_fragment.context;
                            Utils.toast_center(activity2, "" + appCompatTextView.getText().toString() + " already added...");
                        } else {
                            Work_Exp_fragment.this.title.setText(appCompatTextView.getText().toString());
                            AppCompatEditText appCompatEditText2 = Work_Exp_fragment.this.value;
                            appCompatEditText2.setHint("Your " + appCompatTextView.getText().toString());
                            Work_Exp_fragment.this.exit_dialog.dismiss();
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass29 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                Work_Exp_fragment.get_values.clear();
                SQLiteDatabase sQLiteDatabase = Work_Exp_fragment.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT title FROM search_table WHERE title LIKE '%" + editable.toString().replaceAll("'", "''") + "%' and profile_id='" + Work_Exp_fragment.sp.getString(Work_Exp_fragment.context, "profile_id") + "' and table_name='work_table'", null);
                rawQuery.moveToFirst();
                if (rawQuery.getCount() > 0) {
                    for (int i = 0; i < rawQuery.getCount(); i++) {
                        DB_Items dB_Items = new DB_Items();
                        dB_Items.setValue(rawQuery.getString(rawQuery.getColumnIndex("title")));
                        Work_Exp_fragment.get_values.add(dB_Items);
                        rawQuery.moveToNext();
                    }
                }
                rawQuery.close();
                Collections.sort(Work_Exp_fragment.get_values, new Comparator<DB_Items>() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.AnonymousClass29.AnonymousClass1 */

                    public int compare(DB_Items dB_Items, DB_Items dB_Items2) {
                        return dB_Items.getValue().compareTo(dB_Items2.getValue());
                    }
                });
                Work_Exp_fragment work_Exp_fragment = Work_Exp_fragment.this;
                work_Exp_fragment.mAdapter1 = new Add_ListAdapter(Work_Exp_fragment.context, Work_Exp_fragment.get_values);
                recyclerView.setAdapter(Work_Exp_fragment.this.mAdapter1);
                appCompatTextView.setText(editable);
            }
        });
        this.exit_dialog.setCancelable(false);
        this.exit_dialog.setCanceledOnTouchOutside(true);
        this.exit_dialog.show();
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
                /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.Add_ListAdapter.AnonymousClass1 */

                public void onClick(View view) {
                    int i = Add_ListAdapter.this.sp.getInt(Work_Exp_fragment.context, "work_id");
                    SQLiteDatabase sQLiteDatabase = Work_Exp_fragment.DB;
                    Cursor rawQuery = sQLiteDatabase.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Add_ListAdapter.this.sp.getString(Work_Exp_fragment.context, "profile_id") + "' and  upper(title)='" + ((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue().toUpperCase().replaceAll("'", "''") + "' and table_name='work_table' and table_id='" + i + "'", null);
                    if (rawQuery.getCount() != 0) {
                        Activity activity = Work_Exp_fragment.context;
                        Utils.toast_center(activity, "" + ((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue() + " already added...");
                    } else {
                        Work_Exp_fragment.this.title.setText(((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue());
                        Work_Exp_fragment.this.value.setError(null);
                        AppCompatEditText appCompatEditText = Work_Exp_fragment.this.value;
                        appCompatEditText.setHint("Your " + ((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue());
                        Work_Exp_fragment.this.exit_dialog.dismiss();
                    }
                    rawQuery.close();
                }
            });
            itemViewHolder.remove.setOnClickListener(new View.OnClickListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.Add_ListAdapter.AnonymousClass2 */

                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Add_ListAdapter.this.mcontext);
                    builder.setMessage("Are you sure want to delete this?").setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.Add_ListAdapter.AnonymousClass2.AnonymousClass2 */

                        public void onClick(DialogInterface dialogInterface, int i) {
                            SQLiteDatabase sQLiteDatabase = Work_Exp_fragment.DB;
                            sQLiteDatabase.execSQL("delete from search_table where profile_id='" + Add_ListAdapter.this.sp.getString(Work_Exp_fragment.context, "profile_id") + "' and table_name='work_table' and title='" + ((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue().replaceAll("'", "''") + "';");
                            Add_ListAdapter.this.mItems.remove(i);
                            Add_ListAdapter.this.notifyDataSetChanged();
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.Add_ListAdapter.AnonymousClass2.AnonymousClass1 */

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
                /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.Personal_ListAdapter.AnonymousClass1 */

                public void onClick(View view) {
                    for (int i = 0; i < Personal_ListAdapter.this.mItems.size(); i++) {
                        if (((DB_Items) Personal_ListAdapter.this.mItems.get(i)).getTitle().equals(view.getTag().toString())) {
                            Work_Exp_fragment.this.add_dialog(((DB_Items) Personal_ListAdapter.this.mItems.get(i)).getTitle(), ((DB_Items) Personal_ListAdapter.this.mItems.get(i)).getValue(), i);
                            Work_Exp_fragment.this.title.setText(((DB_Items) Personal_ListAdapter.this.mItems.get(i)).getTitle());
                            return;
                        }
                    }
                }
            });
            itemViewHolder.handleView.setOnTouchListener(new View.OnTouchListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.Personal_ListAdapter.AnonymousClass2 */

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (MotionEventCompat.getActionMasked(motionEvent) != 0) {
                        return false;
                    }
                    Personal_ListAdapter.this.mDragStartListener.onStartDrag(itemViewHolder);
                    return false;
                }
            });
            itemViewHolder.remove.setOnClickListener(new View.OnClickListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.Personal_ListAdapter.AnonymousClass3 */

                public void onClick(final View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Personal_ListAdapter.this.mcontext);
                    builder.setMessage("Are you sure want to delete this?").setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.Personal_ListAdapter.AnonymousClass3.AnonymousClass2 */

                        public void onClick(DialogInterface dialogInterface, int i) {
                            for (int i2 = 0; i2 < Personal_ListAdapter.this.mItems.size(); i2++) {
                                if (((DB_Items) Personal_ListAdapter.this.mItems.get(i2)).getTitle().equals(view.getTag().toString())) {
                                    if (Personal_ListAdapter.this.mItems.size() == 1 && Work_Exp_fragment.work_organ.getText().toString().equals("")) {
                                        SQLiteDatabase sQLiteDatabase = Work_Exp_fragment.DB;
                                        sQLiteDatabase.execSQL("delete from work_table where profile_id='" + Work_Exp_fragment.sp.getString(Personal_ListAdapter.this.mcontext, "profile_id") + "' and organization='';");
                                    }
                                    SQLiteDatabase sQLiteDatabase2 = Work_Exp_fragment.DB;
                                    sQLiteDatabase2.execSQL("delete from extra_table where profile_id='" + Work_Exp_fragment.sp.getString(Personal_ListAdapter.this.mcontext, "profile_id") + "' and table_name='work_table' and table_id='" + Work_Exp_fragment.sp.getInt(Work_Exp_fragment.context, "work_id") + "' and title='" + ((DB_Items) Personal_ListAdapter.this.mItems.get(i2)).getTitle().replaceAll("'", "''") + "' and value='" + ((DB_Items) Personal_ListAdapter.this.mItems.get(i2)).getValue().replaceAll("'", "''") + "';");
                                    Personal_ListAdapter.this.mItems.remove(i2);
                                    Personal_ListAdapter.this.notifyDataSetChanged();
                                    return;
                                }
                            }
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment.Personal_ListAdapter.AnonymousClass3.AnonymousClass1 */

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
