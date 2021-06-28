package nithra.resume.maker.cv.builder.app.Fragment;

import android.animation.LayoutTransition;
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
import android.widget.NumberPicker;
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

import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAdView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import nithra.resume.maker.cv.builder.app.Activity.Resume_Activity;
import nithra.resume.maker.cv.builder.app.Adapter.Academic_ListAdapter;
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

public class Academic_fragment extends Fragment implements OnStartDragListener, Visible {
    static SQLiteDatabase DB;
    static String SHOWCASE_ID = "Academic";
    static AppCompatEditText academic_cgpa;
    static AppCompatEditText academic_course;
    static AppCompatEditText academic_institute;
    static Button academic_save;
    static AppCompatTextView academic_title;
    static AppCompatEditText academic_year;
    static NativeContentAdView adView_con;
    static NativeContentAdView adView_con1;
    static NativeAppInstallAdView adView_ins;
    static NativeAppInstallAdView adView_ins1;
    static Personal_ListAdapter add_Adapter;
    static CardView add_more_academic;
    static FrameLayout add_natt;
    static boolean ads_type = false;
    static Activity context;
    public static RelativeLayout cycle_view;
    static List<DB_Items> getachieve = new ArrayList();
    static LinearLayout linear;
    static List<DB_Items> more_data = new ArrayList();
    static List<DB_Items> names = new ArrayList();
    static List<Integer> remove_achieve = new ArrayList();
    public static ScrollView scroll;
    static SharedPreference sp = new SharedPreference();
    static List<AppCompatTextView> title_list = new ArrayList();
    static List<AppCompatEditText> value_list = new ArrayList();
    RecyclerView Academic_cycle;
    CardView academic_add_view;
    ImageView academic_edit;
    ExpandableHeightRecyclerView academic_more_recycle;
    ImageView correct;
    LinearLayout edit_view;
    Dialog exit_dialog;
    List<DB_Items> get_values = new ArrayList();
    Academic_ListAdapter mAdapter;
    Add_ListAdapter mAdapter1;
    ItemTouchHelper mItemTouchHelper;
    ItemTouchHelper mItemTouchHelper1;
    AppCompatTextView title;
    AppCompatEditText title_per;
    AppCompatEditText value;
    int year = Calendar.getInstance().get(1);
    LinearLayout year_pic;
    ImageView yr_pic;

    public static int getItem(int i) {
        return 0;
    }

    public static float dpToPixels(int i, Context context2) {
        return ((float) i) * context2.getResources().getDisplayMetrics().density;
    }

    public static void load_academic(int i) {
        String string;
        int i2 = 0;
        scroll.setVisibility(View.VISIBLE);
        cycle_view.setVisibility(View.GONE);
        scroll.fullScroll(33);
        SQLiteDatabase sQLiteDatabase = DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from academic_table where extra_id='" + i + "' and profile_id='" + sp.getString(context, "profile_id") + "'", null);
        rawQuery.moveToFirst();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(rawQuery.getCount());
        Log.e("selectteeee", sb.toString());
        if (rawQuery.getCount() > 0) {
            SQLiteDatabase sQLiteDatabase2 = DB;
            Cursor rawQuery2 = sQLiteDatabase2.rawQuery("Select academic_title from title_table where profile_id='" + sp.getString(context, "profile_id") + "'", null);
            rawQuery2.moveToFirst();
            if (rawQuery2.getCount() > 0 && (string = rawQuery2.getString(rawQuery2.getColumnIndex("academic_title"))) != null) {
                AppCompatTextView appCompatTextView = academic_title;
                appCompatTextView.setText("" + string);
            }
            rawQuery2.close();
            i2 = rawQuery.getInt(rawQuery.getColumnIndex("academic_id"));
            AppCompatEditText appCompatEditText = academic_course;
            appCompatEditText.setText("" + rawQuery.getString(rawQuery.getColumnIndex("course")));
            AppCompatEditText appCompatEditText2 = academic_institute;
            appCompatEditText2.setText("" + rawQuery.getString(rawQuery.getColumnIndex("institute")));
            AppCompatEditText appCompatEditText3 = academic_cgpa;
            appCompatEditText3.setText("" + rawQuery.getString(rawQuery.getColumnIndex("percentage")));
            AppCompatEditText appCompatEditText4 = academic_year;
            appCompatEditText4.setText("" + rawQuery.getString(rawQuery.getColumnIndex("yop")));
            academic_save.setText("UPDATE");
        } else {
            academic_save.setText("SAVE");
        }
        rawQuery.close();
        if (linear.getChildCount() > 0) {
            linear.removeAllViews();
        }
        sp.putInt(context, "ac_id", i2);
        Update_Load(i2, more_data);
    }

    public static void check() {
        String string;
        SQLiteDatabase sQLiteDatabase = DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select academic_title from title_table where profile_id='" + sp.getString(context, "profile_id") + "'", null);
        rawQuery.moveToFirst();
        if (rawQuery.getCount() > 0 && (string = rawQuery.getString(rawQuery.getColumnIndex("academic_title"))) != null) {
            AppCompatTextView appCompatTextView = academic_title;
            appCompatTextView.setText("" + string);
        }
        rawQuery.close();
        SQLiteDatabase sQLiteDatabase2 = DB;
        Cursor rawQuery2 = sQLiteDatabase2.rawQuery("SELECT * FROM academic_table where profile_id='" + sp.getString(context, "profile_id") + "'", null);
        if (rawQuery2.getCount() == 0) {
            scroll.setVisibility(View.VISIBLE);
            cycle_view.setVisibility(View.GONE);
            sp.putInt(context, "ac_id", 0);
            academic_course.setText("");
            academic_institute.setText("");
            academic_cgpa.setText("");
            academic_year.setText("");
            academic_save.setText("SAVE");
            remove_achieve.clear();
            getachieve.clear();
            title_list.clear();
            value_list.clear();
            more_data.clear();
            add_Adapter.notifyDataSetChanged();
        } else if (rawQuery2.moveToFirst()) {
            for (int i = 0; i < rawQuery2.getCount(); i++) {
                if (rawQuery2.getString(rawQuery2.getColumnIndex("course")).trim().equals("")) {
                    scroll.setVisibility(View.VISIBLE);
                    cycle_view.setVisibility(View.GONE);
                    load_academic(rawQuery2.getInt(rawQuery2.getColumnIndex("extra_id")));
                } else {
                    scroll.setVisibility(View.GONE);
                    cycle_view.setVisibility(View.VISIBLE);
                    if (rawQuery2.moveToFirst()) {
                        names.clear();
                        for (int i2 = 0; i2 < rawQuery2.getCount(); i2++) {
                            DB_Items dB_Items = new DB_Items();
                            dB_Items.setAcademic_id(rawQuery2.getInt(rawQuery2.getColumnIndex("academic_id")));
                            dB_Items.setCourse(rawQuery2.getString(rawQuery2.getColumnIndex("course")));
                            dB_Items.setInstitute(rawQuery2.getString(rawQuery2.getColumnIndex("institute")));
                            dB_Items.setPercentage(rawQuery2.getString(rawQuery2.getColumnIndex("percentage")));
                            dB_Items.setYop(rawQuery2.getString(rawQuery2.getColumnIndex("yop")));
                            dB_Items.setExtra_id(rawQuery2.getString(rawQuery2.getColumnIndex("extra_id")));
                            names.add(dB_Items);
                            rawQuery2.moveToNext();
                        }
                        rawQuery2.moveToPosition(i);
                    }
                    if (!sp.getString(context, "status1").equals("add_more")) {
                        if (rawQuery2.getCount() - 1 == i && rawQuery2.getCount() > 0) {
                            Showcase();
                            sp.putString(context, "status1", "add_more");
                        }
                    } else if (!sp.getString(context, "drag").equals("draged") && rawQuery2.getCount() - 1 == i && rawQuery2.getCount() > 1) {
                        drag_dialog();
                    }
                }
                rawQuery2.moveToNext();
            }
        }
        rawQuery2.close();
    }

    public static void Update_Load(int i, List<DB_Items> list) {
        list.clear();
        SQLiteDatabase sQLiteDatabase = DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from extra_table where profile_id='" + sp.getString(context, "profile_id") + "' and table_id='" + i + "' and table_name='academic_table'", null);
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
        add_Adapter.notifyDataSetChanged();
    }

    public static void Update_Load(int i, LinearLayout linearLayout, final List<Integer> list, List<DB_Items> list2, final List<AppCompatTextView> list3, final List<AppCompatEditText> list4) {
        Cursor cursor;
        LinearLayout linearLayout2 = linearLayout;
        List<DB_Items> list5 = list2;
        list.clear();
        list2.clear();
        list3.clear();
        list4.clear();
        SQLiteDatabase sQLiteDatabase = DB;
        ViewGroup viewGroup = null;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from extra_table where profile_id='" + sp.getString(context, "profile_id") + "' and table_id='" + i + "' and table_name='academic_table'", null);
        boolean z = false;
        rawQuery.moveToPosition(0);
        if (rawQuery.getCount() > 0) {
            if (rawQuery.moveToFirst()) {
                for (int i2 = 0; i2 < rawQuery.getCount(); i2++) {
                    DB_Items dB_Items = new DB_Items();
                    dB_Items.setTitle(rawQuery.getString(rawQuery.getColumnIndex("title")));
                    dB_Items.setValue(rawQuery.getString(rawQuery.getColumnIndex("value")));
                    list5.add(dB_Items);
                    rawQuery.moveToNext();
                }
                rawQuery.close();
            }
            int i3 = 0;
            while (i3 < list2.size()) {
                final View inflate = LayoutInflater.from(context).inflate(R.layout.item_add_more, viewGroup, z);
                final AppCompatEditText appCompatEditText = (AppCompatEditText) inflate.findViewById(R.id.extra);
                final AppCompatEditText appCompatEditText2 = (AppCompatEditText) inflate.findViewById(R.id.en_title);
                final AppCompatTextView appCompatTextView = (AppCompatTextView) inflate.findViewById(R.id.title);
                ImageView imageView = (ImageView) inflate.findViewById(R.id.title_edit);
                ImageView imageView2 = (ImageView) inflate.findViewById(R.id.update_title);
                ImageView imageView3 = (ImageView) inflate.findViewById(R.id.del_view);
                final LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.en_value);
                final LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(R.id.name_dia);
                appCompatEditText.setId(i3);
                appCompatTextView.setId(i3);
                int i4 = z ? 1 : 0;
                int i5 = z ? 1 : 0;
                int i6 = z ? 1 : 0;
                ((LinearLayout) inflate.findViewById(R.id.first)).setVisibility(i4);
                appCompatEditText.setText(list5.get(i3).getValue());
                appCompatTextView.setText(list5.get(i3).getTitle());
                linearLayout2.addView(inflate);
                list4.add(appCompatEditText);
                list3.add(appCompatTextView);
                linearLayout2.setLayoutTransition(new LayoutTransition());
                imageView3.setOnClickListener(new View.OnClickListener() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass1 */

                    public void onClick(View view) {
                        ((LinearLayout) inflate.getParent()).removeView(inflate);
                        list.add(Integer.valueOf(appCompatEditText.getId()));
                        list3.remove(appCompatEditText.getId());
                        list4.remove(appCompatTextView.getId());
                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass2 */

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
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass3 */

                    public void onClick(View view) {
                        appCompatTextView.setText(appCompatEditText2.getText().toString());

                        appCompatEditText.setHint("Enter " + appCompatEditText2.getText().toString());
                        linearLayout4.setVisibility(View.GONE);
                        linearLayout3.setVisibility(View.VISIBLE);
                    }
                });
                i3++;
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass4 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass4.AnonymousClass1 */

                    public void run() {
                        cardView.startAnimation(loadAnimation);
                        imageView.startAnimation(loadAnimation);
                    }
                }, 1500);
            }
        });
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass5 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass5.AnonymousClass1 */

                    public void run() {
                        cardView.startAnimation(loadAnimation2);
                        imageView.startAnimation(loadAnimation2);
                    }
                }, 1500);
            }
        });
        ((Button) dialog.findViewById(R.id.enter)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass6 */

            public void onClick(View view) {
                Academic_fragment.sp.putString(Academic_fragment.context, "drag", "draged");
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
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "Chunkfive.otf");
        materialShowcaseSequence.addSequenceItem(new MaterialShowcaseView.Builder(context).setTarget(add_more_academic).setDismissText("OK").setDismissTextColor(R.color.red).setContentText("Click here to add more academic info").setDismissStyle(createFromAsset).withRectangleShape().setDismissTextColor(context.getResources().getColor(R.color.red)).setMaskColour(R.color.colorAccent).setDismissStyle(createFromAsset).build());
        materialShowcaseSequence.start();
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        context = getActivity();
        View inflate = layoutInflater.inflate(R.layout.fragment_academic_view, viewGroup, false);
        this.academic_edit = (ImageView) inflate.findViewById(R.id.academic_edit);
        this.edit_view = (LinearLayout) inflate.findViewById(R.id.edit_view);
        this.correct = (ImageView) inflate.findViewById(R.id.correct);
        this.title_per = (AppCompatEditText) inflate.findViewById(R.id.title_per);
        academic_title = (AppCompatTextView) inflate.findViewById(R.id.academic_title);
        linear = (LinearLayout) inflate.findViewById(R.id.linear);
        this.academic_add_view = (CardView) inflate.findViewById(R.id.academic_add_view);
        scroll = (ScrollView) inflate.findViewById(R.id.scroll);
        academic_course = (AppCompatEditText) inflate.findViewById(R.id.academic_course);
        academic_institute = (AppCompatEditText) inflate.findViewById(R.id.academic_institute);
        academic_cgpa = (AppCompatEditText) inflate.findViewById(R.id.academic_cgpa);
        academic_year = (AppCompatEditText) inflate.findViewById(R.id.academic_year);
        academic_save = (Button) inflate.findViewById(R.id.academic_save);
        this.Academic_cycle = (RecyclerView) inflate.findViewById(R.id.Academic_cycle);
        cycle_view = (RelativeLayout) inflate.findViewById(R.id.cycle_view);
        add_more_academic = (CardView) inflate.findViewById(R.id.add_more_academic);
        this.yr_pic = (ImageView) inflate.findViewById(R.id.yr_pic);
        this.academic_more_recycle = (ExpandableHeightRecyclerView) inflate.findViewById(R.id.academic_more_recycle);
        more_data.clear();
        DB = context.openOrCreateDatabase("RESUME_BUILDER", 0, null);
        this.academic_more_recycle.setExpanded(true);
        this.academic_more_recycle.setHasFixedSize(true);
        this.academic_more_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        add_Adapter = new Personal_ListAdapter(context, this, more_data);
        this.academic_more_recycle.setAdapter(add_Adapter);
        this.mItemTouchHelper1 = new ItemTouchHelper(new SimpleItemTouchHelperCallback(add_Adapter));
        this.mItemTouchHelper1.attachToRecyclerView(this.academic_more_recycle);
        try {
            check();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.Academic_cycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        this.mAdapter = new Academic_ListAdapter(context, this, names);
        this.Academic_cycle.setAdapter(this.mAdapter);
        this.mAdapter.notifyDataSetChanged();
        this.mItemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.mAdapter));
        this.mItemTouchHelper.attachToRecyclerView(this.Academic_cycle);
        academic_year.setOnKeyListener(new View.OnKeyListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass7 */

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 6) {
                    return false;
                }
                Academic_fragment.this.hideSoftKeyboard();
                return false;
            }
        });
        this.title_per.setOnKeyListener(new View.OnKeyListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass8 */

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 6) {
                    return false;
                }
                Academic_fragment.this.hideSoftKeyboard();
                return false;
            }
        });
        add_more_academic.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass9 */

            public void onClick(View view) {
                Resume_Activity.back_flag = 0;
                Academic_fragment.scroll.setVisibility(View.VISIBLE);
                Academic_fragment.cycle_view.setVisibility(View.GONE);
                Academic_fragment.sp.putInt(Academic_fragment.context, "ac_id", 0);
                Academic_fragment.academic_institute.setText("");
                Academic_fragment.academic_cgpa.setText("");
                Academic_fragment.academic_year.setText("");
                Academic_fragment.academic_course.setText("");
                Academic_fragment.academic_course.setCursorVisible(true);
                Academic_fragment.academic_save.setText("SAVE");
                Academic_fragment.remove_achieve.clear();
                Academic_fragment.getachieve.clear();
                Academic_fragment.title_list.clear();
                Academic_fragment.value_list.clear();
                Academic_fragment.more_data.clear();
                Academic_fragment.add_Adapter.notifyDataSetChanged();
                Academic_fragment.scroll.fullScroll(33);
                if (Academic_fragment.linear.getChildCount() > 0) {
                    Academic_fragment.linear.removeAllViews();
                }
            }
        });
        this.academic_add_view.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass10 */

            public void onClick(View view) {
                Academic_fragment.academic_course.getText().toString();
                Academic_fragment.academic_institute.getText().toString();
                Academic_fragment.academic_cgpa.getText().toString();
                Academic_fragment.academic_year.getText().toString();
                Academic_fragment.this.hideSoftKeyboard();
                Academic_fragment.academic_course.clearFocus();
                Academic_fragment.academic_institute.clearFocus();
                Academic_fragment.academic_cgpa.clearFocus();
                Academic_fragment.academic_year.clearFocus();
                Academic_fragment.this.final_save();
                Academic_fragment.this.add_dialog("", "", 0);
            }
        });
        this.edit_view.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass11 */

            public void onClick(View view) {
            }
        });
        this.academic_edit.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass12 */

            public void onClick(View view) {
                Academic_fragment academic_fragment = Academic_fragment.this;
                academic_fragment.showSoftKeyboard(academic_fragment.title_per);
                Academic_fragment.this.title_per.setText(Academic_fragment.academic_title.getText().toString());
                Academic_fragment.this.title_per.setSelection(Academic_fragment.academic_title.getText().toString().length());
                if (Academic_fragment.this.edit_view.isShown()) {
                    Academic_fragment.this.hideSoftKeyboard();
                    Academic_fragment.this.edit_view.setVisibility(View.GONE);
                    return;
                }
                Academic_fragment.this.title_per.clearFocus();
                Academic_fragment.this.title_per.setError(null);
                Academic_fragment academic_fragment2 = Academic_fragment.this;
                academic_fragment2.showSoftKeyboard(academic_fragment2.title_per);
                Academic_fragment.this.edit_view.setVisibility(View.VISIBLE);
            }
        });
        this.correct.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass13 */

            public void onClick(View view) {
                if (Academic_fragment.this.title_per.getText().toString().trim().isEmpty()) {
                    Academic_fragment.this.title_per.setError("Enter your title..");
                    Academic_fragment.this.title_per.requestFocus();
                    return;
                }
                Academic_fragment.academic_title.setText(Academic_fragment.this.title_per.getText().toString());
                SQLiteDatabase sQLiteDatabase = Academic_fragment.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("Select academic_title from title_table where profile_id='" + Academic_fragment.sp.getString(Academic_fragment.context, "profile_id") + "'", null);
                if (rawQuery.getCount() == 0) {
                    SQLiteDatabase sQLiteDatabase2 = Academic_fragment.DB;
                    sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, academic_title) VALUES ('" + Academic_fragment.sp.getString(Academic_fragment.context, "profile_id") + "', '" + Academic_fragment.academic_title.getText().toString().replaceAll("'", "''") + "');");
                } else {
                    SQLiteDatabase sQLiteDatabase3 = Academic_fragment.DB;
                    sQLiteDatabase3.execSQL("UPDATE title_table SET academic_title = '" + Academic_fragment.academic_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + Academic_fragment.sp.getString(Academic_fragment.context, "profile_id") + "';");
                }
                rawQuery.close();
                Academic_fragment.this.edit_view.setVisibility(View.GONE);
                Academic_fragment.this.hideSoftKeyboard();
            }
        });
        academic_save.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass14 */

            public void onClick(View view) {
                String trim = Academic_fragment.academic_course.getText().toString().trim();
                String trim2 = Academic_fragment.academic_institute.getText().toString().trim();
                String trim3 = Academic_fragment.academic_cgpa.getText().toString().trim();
                String trim4 = Academic_fragment.academic_year.getText().toString().trim();
                if (trim.equals("")) {
                    Academic_fragment.academic_course.setError("Enter your course !!!");
                    Academic_fragment.academic_course.requestFocus();
                } else if (trim2.equals("")) {
                    Academic_fragment.academic_institute.setError("Enter your Institute Name !!!");
                    Academic_fragment.academic_institute.requestFocus();
                } else if (trim3.equals("")) {
                    Academic_fragment.academic_cgpa.setError("Enter your CGPA/Percentage !!!");
                    Academic_fragment.academic_cgpa.requestFocus();
                } else if (trim4.equals("")) {
                    Academic_fragment.academic_year.setError("Enter your year of passing !!!");
                    Academic_fragment.academic_year.requestFocus();
                } else {
                    Academic_fragment.this.hideSoftKeyboard();
                    SQLiteDatabase sQLiteDatabase = Academic_fragment.DB;
                    if (sQLiteDatabase.rawQuery("Select profile_id from academic_table where academic_id='" + Academic_fragment.sp.getInt(Academic_fragment.context, "ac_id") + "' and profile_id='" + Academic_fragment.sp.getString(Academic_fragment.context, "profile_id") + "'", null).getCount() == 0) {
                        Utils.toast_center(Academic_fragment.context, "Academic Info Saved Successfully");
                        Academic_fragment.academic_save.setText("UPDATE");
                    } else {
                        Utils.toast_center(Academic_fragment.context, "Academic Info Updated Successfully");
                        Academic_fragment.academic_save.setText("UPDATE");
                    }
                    Academic_fragment.this.final_save();
                    if (Academic_fragment.linear.getChildCount() > 0) {
                        Academic_fragment.linear.removeAllViews();
                    }
                    try {
                        Academic_fragment.check();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Academic_fragment.this.mAdapter.notifyDataSetChanged();
                }
            }
        });
        this.yr_pic.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass15 */

            public void onClick(View view) {
                Academic_fragment.this.hideSoftKeyboard();
                Academic_fragment.this.showYearDialog();
                Academic_fragment.academic_year.setError(null);
            }
        });
        return inflate;
    }

    public void final_save() {
        SQLiteDatabase sQLiteDatabase = DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select academic_title from title_table where profile_id='" + sp.getString(context, "profile_id") + "'", null);
        if (rawQuery.getCount() == 0) {
            SQLiteDatabase sQLiteDatabase2 = DB;
            sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, academic_title) VALUES ('" + sp.getString(context, "profile_id") + "', '" + academic_title.getText().toString().replaceAll("'", "''") + "');");
        } else {
            SQLiteDatabase sQLiteDatabase3 = DB;
            sQLiteDatabase3.execSQL("UPDATE title_table SET academic_title = '" + academic_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + sp.getString(context, "profile_id") + "';");
        }
        rawQuery.close();
        SQLiteDatabase sQLiteDatabase4 = DB;
        Cursor rawQuery2 = sQLiteDatabase4.rawQuery("Select profile_id from academic_table where academic_id='" + sp.getInt(context, "ac_id") + "' and profile_id='" + sp.getString(context, "profile_id") + "'", null);
        int i = 0;
        if (rawQuery2.getCount() == 0) {
            SQLiteDatabase sQLiteDatabase5 = DB;
            Cursor rawQuery3 = sQLiteDatabase5.rawQuery("Select extra_id from academic_table where profile_id='" + sp.getString(context, "profile_id") + "' order by extra_id DESC", null);
            int i2 = 1;
            if (rawQuery3.getCount() != 0) {
                rawQuery3.moveToFirst();
                i2 = 1 + rawQuery3.getInt(rawQuery3.getColumnIndex("extra_id"));
            }
            SQLiteDatabase sQLiteDatabase6 = DB;
            sQLiteDatabase6.execSQL("INSERT INTO academic_table (profile_id, course, institute, percentage, yop, extra_id) VALUES ('" + sp.getString(context, "profile_id") + "', '" + academic_course.getText().toString().replaceAll("'", "''") + "', '" + academic_institute.getText().toString().replaceAll("'", "''") + "', '" + academic_cgpa.getText().toString().replaceAll("'", "''") + "', '" + academic_year.getText().toString().replaceAll("'", "''") + "','" + i2 + "');");
            Cursor rawQuery4 = DB.rawQuery("select academic_id from academic_table", null);
            rawQuery4.moveToLast();
            int i3 = rawQuery4.getInt(0);
            sp.putInt(context, "ac_id", i3);
            rawQuery4.close();
            SQLiteDatabase sQLiteDatabase7 = DB;
            StringBuilder sb = new StringBuilder();
            sb.append("Select table_id from extra_table where profile_id='");
            sb.append(sp.getString(context, "profile_id"));
            sb.append("' and table_id='");
            sb.append(i3);
            sb.append("' and table_name='academic_table'");
            Cursor rawQuery5 = sQLiteDatabase7.rawQuery(sb.toString(), null);
            if (rawQuery5.getCount() == 0) {
                while (i < more_data.size()) {
                    if (!more_data.get(i).getTitle().equals("") && !more_data.get(i).getTitle().equals(null) && !more_data.get(i).getValue().equals("") && !more_data.get(i).getValue().equals(null)) {
                        SQLiteDatabase sQLiteDatabase8 = DB;
                        sQLiteDatabase8.execSQL("INSERT INTO extra_table (profile_id, table_id, table_name, title, value) VALUES ('" + sp.getString(context, "profile_id") + "', '" + i3 + "','academic_table','" + more_data.get(i).getTitle().replaceAll("'", "''") + "','" + more_data.get(i).getValue().replaceAll("'", "''") + "');");
                    }
                    i++;
                }
            }
            rawQuery3.close();
            rawQuery5.close();
        } else {
            SQLiteDatabase sQLiteDatabase9 = DB;
            sQLiteDatabase9.execSQL("UPDATE academic_table SET course = '" + academic_course.getText().toString().replaceAll("'", "''") + "', institute = '" + academic_institute.getText().toString().replaceAll("'", "''") + "', percentage = '" + academic_cgpa.getText().toString().replaceAll("'", "''") + "', yop = '" + academic_year.getText().toString().replaceAll("'", "''") + "' where academic_id='" + sp.getInt(context, "ac_id") + "';");
            SQLiteDatabase sQLiteDatabase10 = DB;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("delete from extra_table where profile_id='");
            sb2.append(sp.getString(context, "profile_id"));
            sb2.append("' and table_id='");
            sb2.append(sp.getInt(context, "ac_id"));
            sb2.append("' and table_name='academic_table';");
            sQLiteDatabase10.execSQL(sb2.toString());
            while (i < more_data.size()) {
                if (!more_data.get(i).getTitle().equals("") && !more_data.get(i).getTitle().equals(null) && !more_data.get(i).getValue().equals("") && !more_data.get(i).getValue().equals(null)) {
                    SQLiteDatabase sQLiteDatabase11 = DB;
                    sQLiteDatabase11.execSQL("INSERT INTO extra_table (profile_id, table_id, table_name, title, value) VALUES ('" + sp.getString(context, "profile_id") + "', '" + sp.getInt(context, "ac_id") + "', 'academic_table','" + more_data.get(i).getTitle().replaceAll("'", "''") + "','" + more_data.get(i).getValue().replaceAll("'", "''") + "');");
                }
                i++;
            }
        }
        rawQuery2.close();
    }

    public void showYearDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.setTitle("Year Picker");
        dialog.setContentView(R.layout.yeardialog);
        ((TextView) dialog.findViewById(R.id.year_text)).setText("" + this.year);
        final NumberPicker numberPicker = (NumberPicker) dialog.findViewById(R.id.numberPicker1);
        numberPicker.setMaxValue(this.year + 50);
        numberPicker.setMinValue(this.year + -50);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setValue(this.year);
        numberPicker.setDescendantFocusability(393216);
        ((Button) dialog.findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass16 */

            public void onClick(View view) {
                Academic_fragment.academic_year.setText(String.valueOf(numberPicker.getValue()));
                Academic_fragment.academic_year.setSelection(String.valueOf(numberPicker.getValue()).length());
                dialog.dismiss();
            }
        });
        ((Button) dialog.findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass17 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
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
        if (this.edit_view.isShown()) {
            this.edit_view.setVisibility(View.GONE);
        } else if (scroll.isShown()) {
            SQLiteDatabase sQLiteDatabase = DB;
            if (sQLiteDatabase.rawQuery("SELECT * FROM academic_table where profile_id='" + sp.getString(context, "profile_id") + "'", null).getCount() == 0) {
                exti_result_dialog();
                return;
            }
            Cursor rawQuery = DB.rawQuery("select * from academic_table", null);
            if (rawQuery.moveToLast()) {
                if (rawQuery.getString(rawQuery.getColumnIndex("course")).trim().equals("")) {
                    SQLiteDatabase sQLiteDatabase2 = DB;
                    sQLiteDatabase2.execSQL("delete from academic_table where profile_id='" + sp.getString(context, "profile_id") + "' and academic_id='" + sp.getInt(context, "ac_id") + "';");
                    SQLiteDatabase sQLiteDatabase3 = DB;
                    sQLiteDatabase3.execSQL("delete from extra_table where profile_id='" + sp.getString(context, "profile_id") + "' and table_id='" + sp.getInt(context, "ac_id") + "' and table_name='academic_table';");
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass18 */

            public void onClick(View view) {
                Academic_fragment.context.finish();
                dialog.dismiss();
            }
        });
        ((TextView) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass19 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public void view_add(final int i, final List<Integer> list, LinearLayout linearLayout, final List<AppCompatTextView> list2, final List<AppCompatEditText> list3) {
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
        appCompatTextView.setText("Title " + (i + 1));
        linearLayout.addView(inflate);
        list3.add(appCompatEditText);
        list2.add(appCompatTextView);
        showSoftKeyboard(appCompatEditText);
        linearLayout.setLayoutTransition(new LayoutTransition());
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass20 */

            public void onClick(View view) {
                ((LinearLayout) inflate.getParent()).removeView(inflate);
                list.add(Integer.valueOf(i));
                ((AppCompatEditText) list3.get(i)).setText("");
                ((AppCompatTextView) list2.get(i)).setText("");
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass21 */

            public void onClick(View view) {
                Academic_fragment.this.showSoftKeyboard(appCompatEditText2);
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass22 */

            public void onClick(View view) {
                Academic_fragment.this.showSoftKeyboard(appCompatEditText);
                appCompatTextView.setText(appCompatEditText2.getText().toString());

                appCompatEditText.setHint("Enter " + appCompatEditText2.getText().toString());
                linearLayout3.setVisibility(View.GONE);
                linearLayout4.setVisibility(View.VISIBLE);
            }
        });
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass23 */

            public void onClick(View view) {
                Academic_fragment.this.title.setError(null);
                Academic_fragment.this.add_new_dialog();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass24 */

            public void onClick(View view) {
                if (Academic_fragment.this.title.getText().toString().trim().equals("")) {
                    Academic_fragment.this.title.setError("Title Required !!!");
                    Academic_fragment.this.title.requestFocus();
                } else if (Academic_fragment.this.value.getText().toString().trim().equals("")) {
                    AppCompatEditText appCompatEditText = Academic_fragment.this.value;
                    appCompatEditText.setError("Enter your " + Academic_fragment.this.title.getText().toString() + " here !!!");
                    Academic_fragment.this.value.requestFocus();
                } else {
                    int i = Academic_fragment.sp.getInt(Academic_fragment.context, "ac_id");
                    if (str.equals("")) {
                        SQLiteDatabase sQLiteDatabase = Academic_fragment.DB;
                        if (sQLiteDatabase.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Academic_fragment.sp.getString(Academic_fragment.context, "profile_id") + "' and  upper(title)='" + Academic_fragment.this.title.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='academic_table' and table_id='" + i + "'", null).getCount() == 0) {
                            DB_Items dB_Items = new DB_Items();
                            dB_Items.setTitle(Academic_fragment.this.title.getText().toString());
                            dB_Items.setValue(Academic_fragment.this.value.getText().toString());
                            Academic_fragment.more_data.add(dB_Items);
                            Academic_fragment.add_Adapter.notifyDataSetChanged();
                            Academic_fragment.this.final_save();
                            Academic_fragment.this.hideSoftKeyboard();
                            dialog.dismiss();
                            Academic_fragment.scroll.fullScroll(130);
                        } else {
                            Academic_fragment.this.title.setError("Already exist !!!");
                            Academic_fragment.this.title.requestFocus();
                        }
                    } else if (!Academic_fragment.this.title.getText().toString().equals(str)) {
                        SQLiteDatabase sQLiteDatabase2 = Academic_fragment.DB;
                        if (sQLiteDatabase2.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Academic_fragment.sp.getString(Academic_fragment.context, "profile_id") + "' and  upper(title)='" + Academic_fragment.this.title.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='academic_table' and table_id='" + i + "'", null).getCount() == 0) {
                            Academic_fragment.more_data.get(i).setTitle(Academic_fragment.this.title.getText().toString());
                            Academic_fragment.more_data.get(i).setValue(Academic_fragment.this.value.getText().toString());
                            Academic_fragment.add_Adapter.notifyDataSetChanged();
                            Academic_fragment.this.final_save();
                            dialog.getWindow().setSoftInputMode(3);
                            Academic_fragment.this.hideSoftKeyboard();
                            dialog.dismiss();
                            Academic_fragment.scroll.fullScroll(130);
                        } else {
                            Academic_fragment.this.title.setError("Already exist !!!");
                            Academic_fragment.this.title.requestFocus();
                        }
                    } else if (!Academic_fragment.this.value.getText().toString().equals(str2)) {
                        Academic_fragment.more_data.get(i).setTitle(Academic_fragment.this.title.getText().toString());
                        Academic_fragment.more_data.get(i).setValue(Academic_fragment.this.value.getText().toString());
                        Academic_fragment.add_Adapter.notifyDataSetChanged();
                        Academic_fragment.this.final_save();
                        dialog.getWindow().setSoftInputMode(3);
                        Academic_fragment.this.hideSoftKeyboard();
                        dialog.dismiss();
                        Academic_fragment.scroll.fullScroll(130);
                    } else {
                        Academic_fragment.add_Adapter.notifyDataSetChanged();
                        Academic_fragment.this.final_save();
                        Academic_fragment.this.hideSoftKeyboard();
                        dialog.dismiss();
                        Academic_fragment.scroll.fullScroll(130);
                    }
                }
                if (!Academic_fragment.sp.getString(Academic_fragment.context, "drag").equals("draged")) {
                    int i2 = Academic_fragment.sp.getInt(Academic_fragment.context, "ac_id");
                    SQLiteDatabase sQLiteDatabase3 = Academic_fragment.DB;
                    Cursor rawQuery = sQLiteDatabase3.rawQuery("Select table_id from extra_table where profile_id='" + Academic_fragment.sp.getString(Academic_fragment.context, "profile_id") + "' and table_id='" + i2 + "' and table_name='academic_table'", null);
                    if (rawQuery.getCount() > 1) {
                        Academic_fragment.drag_dialog();
                    }
                    rawQuery.close();
                }
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass25 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass26 */

            public void onDismiss(DialogInterface dialogInterface) {
                SQLiteDatabase sQLiteDatabase = Academic_fragment.DB;
                if (sQLiteDatabase.rawQuery("Select table_id from extra_table where profile_id='" + Academic_fragment.sp.getString(Academic_fragment.context, "profile_id") + "' and table_id='" + Academic_fragment.sp.getInt(Academic_fragment.context, "ac_id") + "' and table_name='academic_table'", null).getCount() == 0) {
                    SQLiteDatabase sQLiteDatabase2 = Academic_fragment.DB;
                    sQLiteDatabase2.execSQL("delete from academic_table where profile_id='" + Academic_fragment.sp.getString(Academic_fragment.context, "profile_id") + "' and course='';");
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
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from search_table where profile_id='" + sp.getString(context, "profile_id") + "' and table_name='academic_table'", null);
        if (rawQuery.getCount() > 0) {
            this.get_values.clear();
            if (rawQuery.moveToFirst()) {
                for (int i = 0; i < rawQuery.getCount(); i++) {
                    DB_Items dB_Items = new DB_Items();
                    dB_Items.setValue(rawQuery.getString(rawQuery.getColumnIndex("title")));
                    this.get_values.add(dB_Items);
                    rawQuery.moveToNext();
                }
            }
        }
        rawQuery.close();
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
        Collections.sort(this.get_values, new Comparator<DB_Items>() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass27 */

            public int compare(DB_Items dB_Items, DB_Items dB_Items2) {
                return dB_Items.getValue().compareTo(dB_Items2.getValue());
            }
        });
        this.mAdapter1 = new Add_ListAdapter(context, this.get_values);
        recyclerView.setAdapter(this.mAdapter1);
        this.mAdapter1.notifyDataSetChanged();
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass28 */

            public void onClick(View view) {
                new DB_Items().setValue(appCompatTextView.getText().toString());
                int i = Academic_fragment.sp.getInt(Academic_fragment.context, "ac_id");
                if (!appCompatTextView.getText().toString().trim().equals("")) {
                    SQLiteDatabase sQLiteDatabase = Academic_fragment.DB;
                    Cursor rawQuery = sQLiteDatabase.rawQuery("Select upper(title) as title from search_table where profile_id='" + Academic_fragment.sp.getString(Academic_fragment.context, "profile_id") + "' and  upper(title)='" + appCompatTextView.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='academic_table'", null);
                    if (rawQuery.getCount() == 0) {
                        SQLiteDatabase sQLiteDatabase2 = Academic_fragment.DB;
                        sQLiteDatabase2.execSQL("INSERT INTO search_table (profile_id, table_name, title) VALUES ('" + Academic_fragment.sp.getString(Academic_fragment.context, "profile_id") + "','academic_table','" + appCompatTextView.getText().toString().replaceAll("'", "''") + "');");
                        SQLiteDatabase sQLiteDatabase3 = Academic_fragment.DB;
                        Cursor rawQuery2 = sQLiteDatabase3.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Academic_fragment.sp.getString(Academic_fragment.context, "profile_id") + "' and  upper(title)='" + appCompatTextView.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='academic_table' and table_id='" + i + "'", null);
                        if (rawQuery2.getCount() != 0) {
                            Activity activity = Academic_fragment.context;
                            Utils.toast_center(activity, "" + appCompatTextView.getText().toString() + " already added...");
                        } else {
                            Academic_fragment.this.title.setText(appCompatTextView.getText().toString());
                            AppCompatEditText appCompatEditText = Academic_fragment.this.value;
                            appCompatEditText.setHint("Your " + appCompatTextView.getText().toString());
                            Academic_fragment.this.exit_dialog.dismiss();
                        }
                        rawQuery2.close();
                    } else {
                        SQLiteDatabase sQLiteDatabase4 = Academic_fragment.DB;
                        Cursor rawQuery3 = sQLiteDatabase4.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Academic_fragment.sp.getString(Academic_fragment.context, "profile_id") + "' and  upper(title)='" + appCompatTextView.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='academic_table' and table_id='" + i + "'", null);
                        if (rawQuery3.getCount() != 0) {
                            Activity activity2 = Academic_fragment.context;
                            Utils.toast_center(activity2, "" + appCompatTextView.getText().toString() + " already added...");
                        } else {
                            Academic_fragment.this.title.setText(appCompatTextView.getText().toString());
                            AppCompatEditText appCompatEditText2 = Academic_fragment.this.value;
                            appCompatEditText2.setHint("Your " + appCompatTextView.getText().toString());
                            Academic_fragment.this.exit_dialog.dismiss();
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass29 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                Academic_fragment.this.get_values.clear();
                SQLiteDatabase sQLiteDatabase = Academic_fragment.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT title FROM search_table WHERE title LIKE '%" + editable.toString().replaceAll("'", "''") + "%' and profile_id='" + Academic_fragment.sp.getString(Academic_fragment.context, "profile_id") + "' and table_name='academic_table'", null);
                rawQuery.moveToFirst();
                if (rawQuery.getCount() > 0) {
                    for (int i = 0; i < rawQuery.getCount(); i++) {
                        DB_Items dB_Items = new DB_Items();
                        dB_Items.setValue(rawQuery.getString(rawQuery.getColumnIndex("title")));
                        Academic_fragment.this.get_values.add(dB_Items);
                        rawQuery.moveToNext();
                    }
                }
                rawQuery.close();
                Collections.sort(Academic_fragment.this.get_values, new Comparator<DB_Items>() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.AnonymousClass29.AnonymousClass1 */

                    public int compare(DB_Items dB_Items, DB_Items dB_Items2) {
                        return dB_Items.getValue().compareTo(dB_Items2.getValue());
                    }
                });
                Academic_fragment academic_fragment = Academic_fragment.this;
                academic_fragment.mAdapter1 = new Add_ListAdapter(Academic_fragment.context, Academic_fragment.this.get_values);
                recyclerView.setAdapter(Academic_fragment.this.mAdapter1);
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
                /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.Add_ListAdapter.AnonymousClass1 */

                public void onClick(View view) {
                    int i = Add_ListAdapter.this.sp.getInt(Academic_fragment.context, "ac_id");
                    SQLiteDatabase sQLiteDatabase = Academic_fragment.DB;
                    Cursor rawQuery = sQLiteDatabase.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Add_ListAdapter.this.sp.getString(Academic_fragment.context, "profile_id") + "' and  upper(title)='" + ((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue().toUpperCase().replaceAll("'", "''") + "' and table_name='academic_table' and table_id='" + i + "'", null);
                    if (rawQuery.getCount() != 0) {
                        Activity activity = Academic_fragment.context;
                        Utils.toast_center(activity, ((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue() + " already added...");
                    } else {
                        Academic_fragment.this.title.setText(((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue());
                        Academic_fragment.this.value.setError(null);
                        AppCompatEditText appCompatEditText = Academic_fragment.this.value;
                        appCompatEditText.setHint("Your " + ((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue());
                        Academic_fragment.this.exit_dialog.dismiss();
                    }
                    rawQuery.close();
                }
            });
            itemViewHolder.remove.setOnClickListener(new View.OnClickListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.Add_ListAdapter.AnonymousClass2 */

                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Add_ListAdapter.this.mcontext);
                    builder.setMessage("Are you sure want to delete this?").setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.Add_ListAdapter.AnonymousClass2.AnonymousClass2 */

                        public void onClick(DialogInterface dialogInterface, int i) {
                            SQLiteDatabase sQLiteDatabase = Academic_fragment.DB;
                            sQLiteDatabase.execSQL("delete from search_table where profile_id='" + Add_ListAdapter.this.sp.getString(Academic_fragment.context, "profile_id") + "' and table_name='academic_table'and title='" + ((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue().replaceAll("'", "''") + "';");
                            Add_ListAdapter.this.mItems.remove(i);
                            Add_ListAdapter.this.notifyDataSetChanged();
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.Add_ListAdapter.AnonymousClass2.AnonymousClass1 */

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
                /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.Personal_ListAdapter.AnonymousClass1 */

                public void onClick(View view) {
                    for (int i = 0; i < Personal_ListAdapter.this.mItems.size(); i++) {
                        if (((DB_Items) Personal_ListAdapter.this.mItems.get(i)).getTitle().equals(view.getTag().toString())) {
                            Academic_fragment.this.add_dialog(((DB_Items) Personal_ListAdapter.this.mItems.get(i)).getTitle(), ((DB_Items) Personal_ListAdapter.this.mItems.get(i)).getValue(), i);
                            Academic_fragment.this.title.setText(((DB_Items) Personal_ListAdapter.this.mItems.get(i)).getTitle());
                            return;
                        }
                    }
                }
            });
            itemViewHolder.handleView.setOnTouchListener(new View.OnTouchListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.Personal_ListAdapter.AnonymousClass2 */

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (MotionEventCompat.getActionMasked(motionEvent) != 0) {
                        return false;
                    }
                    Personal_ListAdapter.this.mDragStartListener.onStartDrag(itemViewHolder);
                    return false;
                }
            });
            itemViewHolder.remove.setOnClickListener(new View.OnClickListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.Personal_ListAdapter.AnonymousClass3 */

                public void onClick(final View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Personal_ListAdapter.this.mcontext);
                    builder.setMessage("Are you sure want to delete this?").setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.Personal_ListAdapter.AnonymousClass3.AnonymousClass2 */

                        public void onClick(DialogInterface dialogInterface, int i) {
                            for (int i2 = 0; i2 < Personal_ListAdapter.this.mItems.size(); i2++) {
                                if (((DB_Items) Personal_ListAdapter.this.mItems.get(i2)).getTitle().equals(view.getTag().toString())) {
                                    if (Personal_ListAdapter.this.mItems.size() == 1 && Academic_fragment.academic_course.getText().toString().trim().equals("")) {
                                        SQLiteDatabase sQLiteDatabase = Academic_fragment.DB;
                                        sQLiteDatabase.execSQL("delete from academic_table where profile_id='" + Academic_fragment.sp.getString(Personal_ListAdapter.this.mcontext, "profile_id") + "' and course='';");
                                    }
                                    SQLiteDatabase sQLiteDatabase2 = Academic_fragment.DB;
                                    sQLiteDatabase2.execSQL("delete from extra_table where profile_id='" + Academic_fragment.sp.getString(Personal_ListAdapter.this.mcontext, "profile_id") + "' and table_name='academic_table' and table_id='" + Academic_fragment.sp.getInt(Academic_fragment.context, "ac_id") + "' and title='" + ((DB_Items) Personal_ListAdapter.this.mItems.get(i2)).getTitle().replaceAll("'", "''") + "' and value='" + ((DB_Items) Personal_ListAdapter.this.mItems.get(i2)).getValue().replaceAll("'", "''") + "';");
                                    Personal_ListAdapter.this.mItems.remove(i2);
                                    Personal_ListAdapter.this.notifyDataSetChanged();
                                    return;
                                }
                            }
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment.Personal_ListAdapter.AnonymousClass3.AnonymousClass1 */

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
