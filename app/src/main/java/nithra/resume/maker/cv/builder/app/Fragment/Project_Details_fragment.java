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


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import nithra.resume.maker.cv.builder.app.Activity.Resume_Activity;
import nithra.resume.maker.cv.builder.app.Adapter.Project_ListAdapter;
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

public class Project_Details_fragment extends Fragment implements OnStartDragListener, Visible {
    static SQLiteDatabase DB;
    static String SHOWCASE_ID = "Academic";
    static Personal_ListAdapter add_Adapter;
    static CardView add_more_project;
    static Activity context;
    static RelativeLayout cycle_view;
    static List<DB_Items> getproject = new ArrayList();
    static LinearLayout linear;
    static ArrayList<DB_Items> more_data = new ArrayList<>();
    static ArrayList<DB_Items> names = new ArrayList<>();
    static AppCompatEditText project_cmpny;
    static AppCompatEditText project_description;
    static AppCompatEditText project_duration;
    static ImageView project_edit;
    static AppCompatTextView project_head_title;
    static AppCompatEditText project_role;
    static Button project_save;
    static AppCompatEditText project_teamsize;
    static AppCompatEditText project_title;
    static List<Integer> remove_project = new ArrayList();
    static ScrollView scroll;
    static SharedPreference sp = new SharedPreference();
    static List<AppCompatTextView> title_list = new ArrayList();
    static List<AppCompatEditText> value_list = new ArrayList();
    Dialog exit_dialog;
    ArrayList<DB_Items> get_values = new ArrayList<>();
    Project_ListAdapter mAdapter;
    Add_ListAdapter mAdapter1;
    ItemTouchHelper mItemTouchHelper;
    ItemTouchHelper mItemTouchHelper1;
    CardView project_add_view;
    RecyclerView project_cycle;
    LinearLayout project_edit_view;
    ExpandableHeightRecyclerView project_more_recycle;
    ImageView project_update;
    AppCompatEditText project_update_title;
    AppCompatTextView title;
    AppCompatEditText value;

    public static int getItem(int i) {
        return i;
    }

    public static float dpToPixels(int i, Context context2) {
        return ((float) i) * context2.getResources().getDisplayMetrics().density;
    }

    public static void load_project(int i) {
        String string;
        int i2 = 0;
        scroll.setVisibility(View.VISIBLE);
        cycle_view.setVisibility(View.GONE);
        scroll.fullScroll(33);
        SQLiteDatabase sQLiteDatabase = DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select project_title from title_table where profile_id='" + sp.getString(context, "profile_id") + "'", null);
        rawQuery.moveToFirst();
        if (rawQuery.getCount() > 0 && (string = rawQuery.getString(rawQuery.getColumnIndex("project_title"))) != null) {
            AppCompatTextView appCompatTextView = project_head_title;
            appCompatTextView.setText("" + string);
        }
        rawQuery.close();
        SQLiteDatabase sQLiteDatabase2 = DB;
        Cursor rawQuery2 = sQLiteDatabase2.rawQuery("Select * from project_table where extra_id='" + i + "' and profile_id='" + sp.getString(context, "profile_id") + "'", null);
        rawQuery2.moveToFirst();
        if (rawQuery2.getCount() > 0) {
            i2 = rawQuery2.getInt(rawQuery2.getColumnIndex("project_id"));
            AppCompatEditText appCompatEditText = project_title;
            appCompatEditText.setText("" + rawQuery2.getString(rawQuery2.getColumnIndex("title")));
            AppCompatEditText appCompatEditText2 = project_description;
            appCompatEditText2.setText("" + rawQuery2.getString(rawQuery2.getColumnIndex("discription")));
            AppCompatEditText appCompatEditText3 = project_duration;
            appCompatEditText3.setText("" + rawQuery2.getString(rawQuery2.getColumnIndex("duration")));
            AppCompatEditText appCompatEditText4 = project_role;
            appCompatEditText4.setText("" + rawQuery2.getString(rawQuery2.getColumnIndex("role")));
            AppCompatEditText appCompatEditText5 = project_cmpny;
            appCompatEditText5.setText("" + rawQuery2.getString(rawQuery2.getColumnIndex("company")));
            AppCompatEditText appCompatEditText6 = project_teamsize;
            appCompatEditText6.setText("" + rawQuery2.getString(rawQuery2.getColumnIndex("team_size")));
            project_save.setText("UPDATE");
        } else {
            project_save.setText("SAVE");
        }
        rawQuery2.close();
        if (linear.getChildCount() > 0) {
            linear.removeAllViews();
        }
        sp.putInt(context, "project_id", i2);
        Update_Load(i2, more_data);
    }

    public static void Update_Load(int i, List<DB_Items> list) {
        list.clear();
        SQLiteDatabase sQLiteDatabase = DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from extra_table where profile_id='" + sp.getString(context, "profile_id") + "' and table_id='" + i + "' and table_name='project_table'", null);
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
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from extra_table where profile_id='" + sp.getString(context, "profile_id") + "' and table_id='" + i + "' and table_name='project_table'", null);
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
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass1 */

                    public void onClick(View view) {
                        ((LinearLayout) inflate.getParent()).removeView(inflate);
                        list.add(Integer.valueOf(appCompatEditText.getId()));
                        list3.remove(appCompatEditText.getId());
                        list4.remove(appCompatTextView.getId());
                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass2 */

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
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass3 */

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

    public static void check() {
        String string;
        SQLiteDatabase sQLiteDatabase = DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select project_title from title_table where profile_id='" + sp.getString(context, "profile_id") + "'", null);
        rawQuery.moveToFirst();
        if (rawQuery.getCount() > 0 && (string = rawQuery.getString(rawQuery.getColumnIndex("project_title"))) != null) {
            AppCompatTextView appCompatTextView = project_head_title;
            appCompatTextView.setText("" + string);
        }
        rawQuery.close();
        SQLiteDatabase sQLiteDatabase2 = DB;
        Cursor rawQuery2 = sQLiteDatabase2.rawQuery("SELECT * FROM project_table where profile_id='" + sp.getString(context, "profile_id") + "'", null);
        if (rawQuery2.getCount() == 0) {
            scroll.setVisibility(View.VISIBLE);
            cycle_view.setVisibility(View.GONE);
            sp.putInt(context, "project_id", 0);
            project_title.setText("");
            project_description.setText("");
            project_duration.setText("");
            project_role.setText("");
            project_teamsize.setText("");
            project_cmpny.setText("");
            project_save.setText("SAVE");
            project_edit.setClickable(true);
            remove_project.clear();
            getproject.clear();
            title_list.clear();
            value_list.clear();
            more_data.clear();
            add_Adapter.notifyDataSetChanged();
        } else if (rawQuery2.moveToFirst()) {
            for (int i = 0; i < rawQuery2.getCount(); i++) {
                if (rawQuery2.getString(rawQuery2.getColumnIndex("title")).trim().equals("")) {
                    scroll.setVisibility(View.VISIBLE);
                    cycle_view.setVisibility(View.GONE);
                    load_project(rawQuery2.getInt(rawQuery2.getColumnIndex("extra_id")));
                } else {
                    scroll.setVisibility(View.GONE);
                    cycle_view.setVisibility(View.VISIBLE);
                    if (rawQuery2.moveToFirst()) {
                        names.clear();
                        for (int i2 = 0; i2 < rawQuery2.getCount(); i2++) {
                            DB_Items dB_Items = new DB_Items();
                            dB_Items.setProject_id(rawQuery2.getInt(rawQuery2.getColumnIndex("project_id")));
                            dB_Items.setPro_title(rawQuery2.getString(rawQuery2.getColumnIndex("title")));
                            dB_Items.setDiscription(rawQuery2.getString(rawQuery2.getColumnIndex("discription")));
                            dB_Items.setDuration(rawQuery2.getString(rawQuery2.getColumnIndex("duration")));
                            dB_Items.setRole(rawQuery2.getString(rawQuery2.getColumnIndex("role")));
                            dB_Items.setInstitute(rawQuery2.getString(rawQuery2.getColumnIndex("company")));
                            dB_Items.setT_size(rawQuery2.getString(rawQuery2.getColumnIndex("team_size")));
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass4 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass4.AnonymousClass1 */

                    public void run() {
                        cardView.startAnimation(loadAnimation);
                        imageView.startAnimation(loadAnimation);
                    }
                }, 1500);
            }
        });
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass5 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass5.AnonymousClass1 */

                    public void run() {
                        cardView.startAnimation(loadAnimation2);
                        imageView.startAnimation(loadAnimation2);
                    }
                }, 1500);
            }
        });
        ((Button) dialog.findViewById(R.id.enter)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass6 */

            public void onClick(View view) {
                Project_Details_fragment.sp.putString(Project_Details_fragment.context, "drag", "draged");
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
        Typeface.createFromAsset(context.getAssets(), "Chunkfive.otf");
        materialShowcaseSequence.addSequenceItem(new MaterialShowcaseView.Builder(context).setTarget(add_more_project).setDismissText("OK").setContentText("Click here to add more project details").withRectangleShape().setDismissTextColor(context.getResources().getColor(R.color.red)).setMaskColour(R.color.colorAccent).build());
        materialShowcaseSequence.start();
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        context = getActivity();
        View inflate = layoutInflater.inflate(R.layout.fragment_project_details, viewGroup, false);
        project_edit = (ImageView) inflate.findViewById(R.id.project_edit);
        this.project_edit_view = (LinearLayout) inflate.findViewById(R.id.project_edit_view);
        this.project_update = (ImageView) inflate.findViewById(R.id.project_update);
        this.project_update_title = (AppCompatEditText) inflate.findViewById(R.id.project_update_title);
        project_head_title = (AppCompatTextView) inflate.findViewById(R.id.project_head_title);
        linear = (LinearLayout) inflate.findViewById(R.id.linear);
        this.project_add_view = (CardView) inflate.findViewById(R.id.project_add_view);
        scroll = (ScrollView) inflate.findViewById(R.id.scroll);
        project_save = (Button) inflate.findViewById(R.id.project_save);
        project_title = (AppCompatEditText) inflate.findViewById(R.id.project_title);
        project_description = (AppCompatEditText) inflate.findViewById(R.id.project_description);
        project_duration = (AppCompatEditText) inflate.findViewById(R.id.project_duration);
        project_role = (AppCompatEditText) inflate.findViewById(R.id.project_role);
        project_teamsize = (AppCompatEditText) inflate.findViewById(R.id.project_teamsize);
        project_cmpny = (AppCompatEditText) inflate.findViewById(R.id.project_cmpny);
        this.project_cycle = (RecyclerView) inflate.findViewById(R.id.project_cycle);
        this.project_more_recycle = (ExpandableHeightRecyclerView) inflate.findViewById(R.id.project_more_recycle);
        cycle_view = (RelativeLayout) inflate.findViewById(R.id.cycle_view);
        add_more_project = (CardView) inflate.findViewById(R.id.add_more_project);
        DB = context.openOrCreateDatabase("RESUME_BUILDER", 0, null);
        more_data.clear();
        this.project_more_recycle.setExpanded(true);
        this.project_more_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        add_Adapter = new Personal_ListAdapter(context, this, more_data);
        this.project_more_recycle.setAdapter(add_Adapter);
        this.mItemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(add_Adapter));
        this.mItemTouchHelper.attachToRecyclerView(this.project_more_recycle);
        this.project_more_recycle.setHasFixedSize(true);
        this.project_cycle.setHasFixedSize(true);
        inflate.setOnKeyListener(new View.OnKeyListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass7 */

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == 4) {
                    if (Project_Details_fragment.cycle_view.isShown()) {
                        Project_Details_fragment.scroll.setVisibility(View.VISIBLE);
                        Project_Details_fragment.cycle_view.setVisibility(View.GONE);
                    } else {
                        Project_Details_fragment.context.finish();
                    }
                }
                return false;
            }
        });
        try {
            check();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.project_cycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        this.mAdapter = new Project_ListAdapter(context, this, names);
        this.project_cycle.setAdapter(this.mAdapter);
        this.mAdapter.notifyDataSetChanged();
        this.mItemTouchHelper1 = new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.mAdapter));
        this.mItemTouchHelper1.attachToRecyclerView(this.project_cycle);
        project_teamsize.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass8 */

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                ((InputMethodManager) textView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(textView.getWindowToken(), 0);
                return true;
            }
        });
        add_more_project.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass9 */

            public void onClick(View view) {
                Resume_Activity.back_flag = 0;
                Project_Details_fragment.scroll.setVisibility(View.VISIBLE);
                Project_Details_fragment.cycle_view.setVisibility(View.GONE);
                Project_Details_fragment.sp.putInt(Project_Details_fragment.context, "project_id", 0);
                Project_Details_fragment.project_title.setText("");
                Project_Details_fragment.project_description.setText("");
                Project_Details_fragment.project_duration.setText("");
                Project_Details_fragment.project_role.setText("");
                Project_Details_fragment.project_teamsize.setText("");
                Project_Details_fragment.project_cmpny.setText("");
                Project_Details_fragment.project_save.setText("SAVE");
                Project_Details_fragment.project_edit.setClickable(true);
                Project_Details_fragment.remove_project.clear();
                Project_Details_fragment.getproject.clear();
                Project_Details_fragment.title_list.clear();
                Project_Details_fragment.value_list.clear();
                Project_Details_fragment.more_data.clear();
                Project_Details_fragment.add_Adapter.notifyDataSetChanged();
                Project_Details_fragment.scroll.fullScroll(33);
                if (Project_Details_fragment.linear.getChildCount() > 0) {
                    Project_Details_fragment.linear.removeAllViews();
                }
            }
        });
        this.project_add_view.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass10 */

            public void onClick(View view) {
                Project_Details_fragment.this.final_save();
                Project_Details_fragment.this.hideSoftKeyboard();
                Project_Details_fragment.this.add_dialog("", "", 0);
            }
        });
        this.project_edit_view.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass11 */

            public void onClick(View view) {
            }
        });
        project_edit.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass12 */

            public void onClick(View view) {
                Project_Details_fragment project_Details_fragment = Project_Details_fragment.this;
                project_Details_fragment.showSoftKeyboard(project_Details_fragment.project_update_title);
                Project_Details_fragment.this.project_update_title.setText(Project_Details_fragment.project_head_title.getText().toString());
                Project_Details_fragment.this.project_update_title.setSelection(Project_Details_fragment.project_head_title.getText().toString().length());
                if (Project_Details_fragment.this.project_edit_view.isShown()) {
                    Project_Details_fragment.this.hideSoftKeyboard();
                    Project_Details_fragment.this.project_edit_view.setVisibility(View.GONE);
                    return;
                }
                Project_Details_fragment.this.project_update_title.clearFocus();
                Project_Details_fragment.this.project_update_title.setError(null);
                Project_Details_fragment project_Details_fragment2 = Project_Details_fragment.this;
                project_Details_fragment2.showSoftKeyboard(project_Details_fragment2.project_update_title);
                Project_Details_fragment.this.project_edit_view.setVisibility(View.VISIBLE);
            }
        });
        this.project_update_title.setOnKeyListener(new View.OnKeyListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass13 */

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 6) {
                    return false;
                }
                Project_Details_fragment.this.hideSoftKeyboard();
                return false;
            }
        });
        this.project_update.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass14 */

            public void onClick(View view) {
                if (Project_Details_fragment.this.project_update_title.getText().toString().trim().isEmpty()) {
                    Project_Details_fragment.this.project_update_title.setError("Enter your title..");
                    Project_Details_fragment.this.project_update_title.requestFocus();
                    return;
                }
                Project_Details_fragment.project_head_title.setText(Project_Details_fragment.this.project_update_title.getText().toString());
                SQLiteDatabase sQLiteDatabase = Project_Details_fragment.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("Select project_title from title_table where profile_id='" + Project_Details_fragment.sp.getString(Project_Details_fragment.context, "profile_id") + "'", null);
                if (rawQuery.getCount() == 0) {
                    SQLiteDatabase sQLiteDatabase2 = Project_Details_fragment.DB;
                    sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, project_title) VALUES ('" + Project_Details_fragment.sp.getString(Project_Details_fragment.context, "profile_id") + "', '" + Project_Details_fragment.project_head_title.getText().toString().replaceAll("'", "''") + "');");
                } else {
                    SQLiteDatabase sQLiteDatabase3 = Project_Details_fragment.DB;
                    sQLiteDatabase3.execSQL("UPDATE title_table SET project_title = '" + Project_Details_fragment.project_head_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + Project_Details_fragment.sp.getString(Project_Details_fragment.context, "profile_id") + "';");
                }
                rawQuery.close();
                Project_Details_fragment.this.project_edit_view.setVisibility(View.GONE);
                Project_Details_fragment.this.hideSoftKeyboard();
            }
        });
        project_save.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass15 */

            public void onClick(View view) {
                String obj = Project_Details_fragment.project_title.getText().toString();
                String obj2 = Project_Details_fragment.project_description.getText().toString();
                String obj3 = Project_Details_fragment.project_duration.getText().toString();
                String obj4 = Project_Details_fragment.project_role.getText().toString();
                if (obj.trim().equals("")) {
                    Project_Details_fragment.project_title.setError("Enter Your Project Title !!!");
                    Project_Details_fragment.project_title.requestFocus();
                } else if (obj2.trim().equals("")) {
                    Project_Details_fragment.project_description.setError("Enter your project description !!!");
                    Project_Details_fragment.project_description.requestFocus();
                } else if (obj3.trim().equals("")) {
                    Project_Details_fragment.project_duration.setError("Enter your project duration!!!");
                    Project_Details_fragment.project_duration.requestFocus();
                } else if (obj4.trim().equals("")) {
                    Project_Details_fragment.project_role.setError("Enter your role in project !!!");
                    Project_Details_fragment.project_role.requestFocus();
                } else {
                    Project_Details_fragment.this.hideSoftKeyboard();
                    SQLiteDatabase sQLiteDatabase = Project_Details_fragment.DB;
                    if (sQLiteDatabase.rawQuery("Select profile_id from project_table where project_id='" + Project_Details_fragment.sp.getInt(Project_Details_fragment.context, "project_id") + "'and profile_id='" + Project_Details_fragment.sp.getString(Project_Details_fragment.context, "profile_id") + "'", null).getCount() == 0) {
                        Project_Details_fragment.project_save.setText("UPDATE");
                    } else {
                        Utils.toast_center(Project_Details_fragment.context, "Project Details Updated Successfully");
                        Project_Details_fragment.project_save.setText("UPDATE");
                    }
                    Project_Details_fragment.this.final_save();
                    Project_Details_fragment.project_edit.setClickable(false);
                    if (Project_Details_fragment.linear.getChildCount() > 0) {
                        Project_Details_fragment.linear.removeAllViews();
                    }
                    try {
                        Project_Details_fragment.check();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Project_Details_fragment.this.mAdapter.notifyDataSetChanged();
                }
            }
        });
        return inflate;
    }

    public void final_save() {
        SQLiteDatabase sQLiteDatabase = DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select project_title from title_table where profile_id='" + sp.getString(context, "profile_id") + "'", null);
        if (rawQuery.getCount() == 0) {
            SQLiteDatabase sQLiteDatabase2 = DB;
            sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, project_title) VALUES ('" + sp.getString(context, "profile_id") + "', '" + project_head_title.getText().toString().replaceAll("'", "''") + "');");
        } else {
            SQLiteDatabase sQLiteDatabase3 = DB;
            sQLiteDatabase3.execSQL("UPDATE title_table SET project_title = '" + project_head_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + sp.getString(context, "profile_id") + "';");
        }
        rawQuery.close();
        SQLiteDatabase sQLiteDatabase4 = DB;
        Cursor rawQuery2 = sQLiteDatabase4.rawQuery("Select profile_id from project_table where project_id='" + sp.getInt(context, "project_id") + "'and profile_id='" + sp.getString(context, "profile_id") + "'", null);
        int i = 0;
        if (rawQuery2.getCount() == 0) {
            SQLiteDatabase sQLiteDatabase5 = DB;
            Cursor rawQuery3 = sQLiteDatabase5.rawQuery("Select extra_id from project_table where profile_id='" + sp.getString(context, "profile_id") + "' order by extra_id DESC", null);
            int i2 = 1;
            if (rawQuery3.getCount() != 0) {
                rawQuery3.moveToFirst();
                i2 = 1 + rawQuery3.getInt(rawQuery3.getColumnIndex("extra_id"));
            }
            SQLiteDatabase sQLiteDatabase6 = DB;
            sQLiteDatabase6.execSQL("INSERT INTO project_table (profile_id, title, discription, duration, role, company, team_size, extra_id) VALUES ('" + sp.getString(context, "profile_id") + "', '" + project_title.getText().toString().replaceAll("'", "''") + "', '" + project_description.getText().toString().replaceAll("'", "''") + "', '" + project_duration.getText().toString().replaceAll("'", "''") + "', '" + project_role.getText().toString().replaceAll("'", "''") + "', '" + project_cmpny.getText().toString().replaceAll("'", "''") + "', '" + project_teamsize.getText().toString().replaceAll("'", "''") + "','" + i2 + "');");
            Cursor rawQuery4 = DB.rawQuery("select project_id from project_table", null);
            rawQuery4.moveToLast();
            int i3 = rawQuery4.getInt(0);
            sp.putInt(context, "project_id", i3);
            rawQuery4.close();
            SQLiteDatabase sQLiteDatabase7 = DB;
            StringBuilder sb = new StringBuilder();
            sb.append("Select table_id from extra_table where profile_id='");
            sb.append(sp.getString(context, "profile_id"));
            sb.append("' and table_id='");
            sb.append(i3);
            sb.append("' and table_name='project_table'");
            Cursor rawQuery5 = sQLiteDatabase7.rawQuery(sb.toString(), null);
            if (rawQuery5.getCount() == 0) {
                while (i < more_data.size()) {
                    if (!more_data.get(i).getTitle().equals("") && !more_data.get(i).getTitle().equals(null) && !more_data.get(i).getValue().equals("") && !more_data.get(i).getValue().equals(null)) {
                        SQLiteDatabase sQLiteDatabase8 = DB;
                        sQLiteDatabase8.execSQL("INSERT INTO extra_table (profile_id, table_id, table_name, title, value) VALUES ('" + sp.getString(context, "profile_id") + "', '" + i3 + "','project_table','" + more_data.get(i).getTitle().replaceAll("'", "''") + "','" + more_data.get(i).getValue().replaceAll("'", "''") + "');");
                    }
                    i++;
                }
            }
            rawQuery3.close();
            rawQuery5.close();
        } else {
            SQLiteDatabase sQLiteDatabase9 = DB;
            sQLiteDatabase9.execSQL("UPDATE project_table SET title = '" + project_title.getText().toString().replaceAll("'", "''") + "', discription = '" + project_description.getText().toString().replaceAll("'", "''") + "', duration = '" + project_duration.getText().toString().replaceAll("'", "''") + "', role = '" + project_role.getText().toString().replaceAll("'", "''") + "', company = '" + project_cmpny.getText().toString().replaceAll("'", "''") + "', team_size = '" + project_teamsize.getText().toString().replaceAll("'", "''") + "' WHERE project_id = '" + sp.getInt(context, "project_id") + "';");
            SQLiteDatabase sQLiteDatabase10 = DB;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("delete from extra_table where profile_id='");
            sb2.append(sp.getString(context, "profile_id"));
            sb2.append("' and table_id='");
            sb2.append(sp.getInt(context, "project_id"));
            sb2.append("' and table_name='project_table';");
            sQLiteDatabase10.execSQL(sb2.toString());
            while (i < more_data.size()) {
                if (!more_data.get(i).getTitle().equals("") && !more_data.get(i).getTitle().equals(null) && !more_data.get(i).getValue().equals("") && !more_data.get(i).getValue().equals(null)) {
                    SQLiteDatabase sQLiteDatabase11 = DB;
                    sQLiteDatabase11.execSQL("INSERT INTO extra_table (profile_id, table_id, table_name, title, value) VALUES ('" + sp.getString(context, "profile_id") + "', '" + sp.getInt(context, "project_id") + "','project_table','" + more_data.get(i).getTitle().replaceAll("'", "''") + "','" + more_data.get(i).getValue().replaceAll("'", "''") + "');");
                }
                i++;
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass16 */

            public void onClick(View view) {
                ((LinearLayout) inflate.getParent()).removeView(inflate);
                list.add(Integer.valueOf(i));
                ((AppCompatEditText) list3.get(i)).setText("");
                ((AppCompatTextView) list2.get(i)).setText("");
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass17 */

            public void onClick(View view) {
                Project_Details_fragment.this.showSoftKeyboard(appCompatEditText2);
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass18 */

            public void onClick(View view) {
                Project_Details_fragment.this.showSoftKeyboard(appCompatEditText);
                appCompatTextView.setText(appCompatEditText2.getText().toString());

                appCompatEditText.setHint("Enter " + appCompatEditText2.getText().toString());
                linearLayout3.setVisibility(View.GONE);
                linearLayout4.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override // nithra.resume.maker.cv.builder.app.Touch.OnStartDragListener
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        this.mItemTouchHelper.startDrag(viewHolder);
        this.mItemTouchHelper1.startDrag(viewHolder);
    }

    @Override // nithra.resume.maker.cv.builder.app.Visible
    public void visible() {
        if (this.project_edit_view.isShown()) {
            this.project_edit_view.setVisibility(View.GONE);
        } else if (scroll.isShown()) {
            SQLiteDatabase sQLiteDatabase = DB;
            if (sQLiteDatabase.rawQuery("SELECT * FROM project_table where profile_id='" + sp.getString(context, "profile_id") + "'", null).getCount() == 0) {
                exti_result_dialog();
                return;
            }
            Cursor rawQuery = DB.rawQuery("select * from project_table", null);
            if (rawQuery.moveToLast()) {
                if (rawQuery.getString(rawQuery.getColumnIndex("title")).trim().equals("")) {
                    SQLiteDatabase sQLiteDatabase2 = DB;
                    sQLiteDatabase2.execSQL("delete from project_table where profile_id='" + sp.getString(context, "profile_id") + "' and project_id='" + sp.getInt(context, "project_id") + "';");
                    SQLiteDatabase sQLiteDatabase3 = DB;
                    sQLiteDatabase3.execSQL("delete from extra_table where profile_id='" + sp.getString(context, "profile_id") + "' and table_id='" + sp.getInt(context, "project_id") + "' and table_name='project_table';");
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass19 */

            public void onClick(View view) {
                Project_Details_fragment.context.finish();
                dialog.dismiss();
            }
        });
        ((TextView) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass20 */

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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass21 */

            public void onClick(View view) {
                Project_Details_fragment.this.title.setError(null);
                Project_Details_fragment.this.add_new_dialog();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass22 */

            public void onClick(View view) {
                if (Project_Details_fragment.this.title.getText().toString().trim().equals("")) {
                    Project_Details_fragment.this.title.setError("Title Required !!!");
                    Project_Details_fragment.this.title.requestFocus();
                } else if (Project_Details_fragment.this.value.getText().toString().trim().equals("")) {
                    AppCompatEditText appCompatEditText = Project_Details_fragment.this.value;
                    appCompatEditText.setError("Enter your " + Project_Details_fragment.this.title.getText().toString() + " here !!!");
                    Project_Details_fragment.this.value.requestFocus();
                } else {
                    int i = Project_Details_fragment.sp.getInt(Project_Details_fragment.context, "project_id");
                    if (str.equals("")) {
                        SQLiteDatabase sQLiteDatabase = Project_Details_fragment.DB;
                        if (sQLiteDatabase.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Project_Details_fragment.sp.getString(Project_Details_fragment.context, "profile_id") + "' and  upper(title)='" + Project_Details_fragment.this.title.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='project_table' and table_id='" + i + "'", null).getCount() == 0) {
                            DB_Items dB_Items = new DB_Items();
                            dB_Items.setTitle(Project_Details_fragment.this.title.getText().toString());
                            dB_Items.setValue(Project_Details_fragment.this.value.getText().toString());
                            Project_Details_fragment.more_data.add(dB_Items);
                            Project_Details_fragment.add_Adapter.notifyDataSetChanged();
                            Project_Details_fragment.this.final_save();
                            Project_Details_fragment.this.hideSoftKeyboard();
                            dialog.dismiss();
                        } else {
                            Project_Details_fragment.this.title.setError("Already exist !!!");
                            Project_Details_fragment.this.title.requestFocus();
                        }
                    } else if (!Project_Details_fragment.this.title.getText().toString().equals(str)) {
                        SQLiteDatabase sQLiteDatabase2 = Project_Details_fragment.DB;
                        if (sQLiteDatabase2.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Project_Details_fragment.sp.getString(Project_Details_fragment.context, "profile_id") + "' and  upper(title)='" + Project_Details_fragment.this.title.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='project_table' and table_id='" + i + "'", null).getCount() == 0) {
                            Project_Details_fragment.more_data.get(i).setTitle(Project_Details_fragment.this.title.getText().toString());
                            Project_Details_fragment.more_data.get(i).setValue(Project_Details_fragment.this.value.getText().toString());
                            Project_Details_fragment.add_Adapter.notifyDataSetChanged();
                            Project_Details_fragment.this.final_save();
                            dialog.getWindow().setSoftInputMode(3);
                            Project_Details_fragment.this.hideSoftKeyboard();
                            dialog.dismiss();
                        } else {
                            Project_Details_fragment.this.title.setError("Already exist !!!");
                            Project_Details_fragment.this.title.requestFocus();
                        }
                    } else if (!Project_Details_fragment.this.value.getText().toString().equals(str2)) {
                        Project_Details_fragment.more_data.get(i).setTitle(Project_Details_fragment.this.title.getText().toString());
                        Project_Details_fragment.more_data.get(i).setValue(Project_Details_fragment.this.value.getText().toString());
                        Project_Details_fragment.add_Adapter.notifyDataSetChanged();
                        Project_Details_fragment.this.final_save();
                        dialog.getWindow().setSoftInputMode(3);
                        Project_Details_fragment.this.hideSoftKeyboard();
                        dialog.dismiss();
                        Project_Details_fragment.scroll.fullScroll(130);
                    } else {
                        Project_Details_fragment.add_Adapter.notifyDataSetChanged();
                        Project_Details_fragment.this.final_save();
                        dialog.getWindow().setSoftInputMode(3);
                        Project_Details_fragment.this.hideSoftKeyboard();
                        dialog.dismiss();
                        Project_Details_fragment.scroll.fullScroll(130);
                    }
                }
                if (!Project_Details_fragment.sp.getString(Project_Details_fragment.context, "drag").equals("draged")) {
                    int i2 = Project_Details_fragment.sp.getInt(Project_Details_fragment.context, "project_id");
                    SQLiteDatabase sQLiteDatabase3 = Project_Details_fragment.DB;
                    Cursor rawQuery = sQLiteDatabase3.rawQuery("Select table_id from extra_table where profile_id='" + Project_Details_fragment.sp.getString(Project_Details_fragment.context, "profile_id") + "' and table_id='" + i2 + "' and table_name='project_table'", null);
                    if (rawQuery.getCount() > 1) {
                        Project_Details_fragment.drag_dialog();
                    }
                    rawQuery.close();
                }
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass23 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass24 */

            public void onDismiss(DialogInterface dialogInterface) {
                SQLiteDatabase sQLiteDatabase = Project_Details_fragment.DB;
                if (sQLiteDatabase.rawQuery("Select table_id from extra_table where profile_id='" + Project_Details_fragment.sp.getString(Project_Details_fragment.context, "profile_id") + "' and table_id='" + Project_Details_fragment.sp.getInt(Project_Details_fragment.context, "project_id") + "' and table_name='project_table'", null).getCount() == 0) {
                    SQLiteDatabase sQLiteDatabase2 = Project_Details_fragment.DB;
                    sQLiteDatabase2.execSQL("delete from project_table where profile_id='" + Project_Details_fragment.sp.getString(Project_Details_fragment.context, "profile_id") + "' and title = '';");
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
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from search_table where profile_id='" + sp.getString(context, "profile_id") + "' and table_name='project_table'", null);
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass25 */

            public int compare(DB_Items dB_Items, DB_Items dB_Items2) {
                return dB_Items.getValue().compareTo(dB_Items2.getValue());
            }
        });
        this.mAdapter1 = new Add_ListAdapter(context, this.get_values);
        recyclerView.setAdapter(this.mAdapter1);
        this.mAdapter1.notifyDataSetChanged();
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass26 */

            public void onClick(View view) {
                new DB_Items().setValue(appCompatTextView.getText().toString());
                int i = Project_Details_fragment.sp.getInt(Project_Details_fragment.context, "project_id");
                if (!appCompatTextView.getText().toString().trim().equals("")) {
                    SQLiteDatabase sQLiteDatabase = Project_Details_fragment.DB;
                    Cursor rawQuery = sQLiteDatabase.rawQuery("Select upper(title) as title from search_table where profile_id='" + Project_Details_fragment.sp.getString(Project_Details_fragment.context, "profile_id") + "' and  upper(title)='" + appCompatTextView.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='project_table'", null);
                    if (rawQuery.getCount() == 0) {
                        SQLiteDatabase sQLiteDatabase2 = Project_Details_fragment.DB;
                        sQLiteDatabase2.execSQL("INSERT INTO search_table (profile_id, table_name, title) VALUES ('" + Project_Details_fragment.sp.getString(Project_Details_fragment.context, "profile_id") + "','project_table','" + appCompatTextView.getText().toString().replaceAll("'", "''") + "');");
                        SQLiteDatabase sQLiteDatabase3 = Project_Details_fragment.DB;
                        Cursor rawQuery2 = sQLiteDatabase3.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Project_Details_fragment.sp.getString(Project_Details_fragment.context, "profile_id") + "' and  upper(title)='" + appCompatTextView.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='project_table' and table_id='" + i + "'", null);
                        if (rawQuery2.getCount() != 0) {
                            Activity activity = Project_Details_fragment.context;
                            Utils.toast_center(activity, "" + appCompatTextView.getText().toString() + " already added...");
                        } else {
                            Project_Details_fragment.this.title.setText(appCompatTextView.getText().toString());
                            AppCompatEditText appCompatEditText = Project_Details_fragment.this.value;
                            appCompatEditText.setHint("Your " + appCompatTextView.getText().toString());
                            Project_Details_fragment.this.exit_dialog.dismiss();
                        }
                        rawQuery2.close();
                    } else {
                        SQLiteDatabase sQLiteDatabase4 = Project_Details_fragment.DB;
                        Cursor rawQuery3 = sQLiteDatabase4.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Project_Details_fragment.sp.getString(Project_Details_fragment.context, "profile_id") + "' and  upper(title)='" + appCompatTextView.getText().toString().toUpperCase().replaceAll("'", "''") + "' and table_name='project_table' and table_id='" + i + "'", null);
                        if (rawQuery3.getCount() != 0) {
                            Activity activity2 = Project_Details_fragment.context;
                            Utils.toast_center(activity2, "" + appCompatTextView.getText().toString() + " already added...");
                        } else {
                            Project_Details_fragment.this.title.setText(appCompatTextView.getText().toString());
                            AppCompatEditText appCompatEditText2 = Project_Details_fragment.this.value;
                            appCompatEditText2.setHint("Your " + appCompatTextView.getText().toString());
                            Project_Details_fragment.this.exit_dialog.dismiss();
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass27 */

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                Project_Details_fragment.this.get_values.clear();
                SQLiteDatabase sQLiteDatabase = Project_Details_fragment.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT title FROM search_table WHERE title LIKE '%" + editable.toString().replaceAll("'", "''") + "%' and profile_id='" + Project_Details_fragment.sp.getString(Project_Details_fragment.context, "profile_id") + "' and table_name='project_table'", null);
                rawQuery.moveToFirst();
                if (rawQuery.getCount() > 0) {
                    for (int i = 0; i < rawQuery.getCount(); i++) {
                        DB_Items dB_Items = new DB_Items();
                        dB_Items.setValue(rawQuery.getString(rawQuery.getColumnIndex("title")));
                        Project_Details_fragment.this.get_values.add(dB_Items);
                        rawQuery.moveToNext();
                    }
                }
                rawQuery.close();
                Collections.sort(Project_Details_fragment.this.get_values, new Comparator<DB_Items>() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.AnonymousClass27.AnonymousClass1 */

                    public int compare(DB_Items dB_Items, DB_Items dB_Items2) {
                        return dB_Items.getValue().compareTo(dB_Items2.getValue());
                    }
                });
                Project_Details_fragment project_Details_fragment = Project_Details_fragment.this;
                project_Details_fragment.mAdapter1 = new Add_ListAdapter(Project_Details_fragment.context, Project_Details_fragment.this.get_values);
                recyclerView.setAdapter(Project_Details_fragment.this.mAdapter1);
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
                /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.Add_ListAdapter.AnonymousClass1 */

                public void onClick(View view) {
                    int i = Add_ListAdapter.this.sp.getInt(Project_Details_fragment.context, "project_id");
                    SQLiteDatabase sQLiteDatabase = Project_Details_fragment.DB;
                    Cursor rawQuery = sQLiteDatabase.rawQuery("Select upper(title) as title from extra_table where profile_id='" + Add_ListAdapter.this.sp.getString(Project_Details_fragment.context, "profile_id") + "' and  upper(title)='" + ((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue().toUpperCase().replaceAll("'", "''") + "' and table_name='project_table' and table_id='" + i + "'", null);
                    if (rawQuery.getCount() != 0) {
                        Activity activity = Project_Details_fragment.context;
                        Utils.toast_center(activity, "" + ((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue() + " already added...");
                    } else {
                        Project_Details_fragment.this.title.setText(((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue());
                        Project_Details_fragment.this.value.setError(null);
                        AppCompatEditText appCompatEditText = Project_Details_fragment.this.value;
                        appCompatEditText.setHint("Your " + ((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue());
                        Project_Details_fragment.this.exit_dialog.dismiss();
                    }
                    rawQuery.close();
                }
            });
            itemViewHolder.remove.setOnClickListener(new View.OnClickListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.Add_ListAdapter.AnonymousClass2 */

                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Add_ListAdapter.this.mcontext);
                    builder.setMessage("Are you sure want to delete this?").setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.Add_ListAdapter.AnonymousClass2.AnonymousClass2 */

                        public void onClick(DialogInterface dialogInterface, int i) {
                            SQLiteDatabase sQLiteDatabase = Project_Details_fragment.DB;
                            sQLiteDatabase.execSQL("delete from search_table where profile_id='" + Add_ListAdapter.this.sp.getString(Project_Details_fragment.context, "profile_id") + "' and table_name='project_table' and title='" + ((DB_Items) Add_ListAdapter.this.mItems.get(i)).getValue().replaceAll("'", "''") + "';");
                            Add_ListAdapter.this.mItems.remove(i);
                            Add_ListAdapter.this.notifyDataSetChanged();
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.Add_ListAdapter.AnonymousClass2.AnonymousClass1 */

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
                /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.Personal_ListAdapter.AnonymousClass1 */

                public void onClick(View view) {
                    for (int i = 0; i < Personal_ListAdapter.this.mItems.size(); i++) {
                        if (((DB_Items) Personal_ListAdapter.this.mItems.get(i)).getTitle().equals(view.getTag().toString())) {
                            Project_Details_fragment.this.add_dialog(((DB_Items) Personal_ListAdapter.this.mItems.get(i)).getTitle(), ((DB_Items) Personal_ListAdapter.this.mItems.get(i)).getValue(), i);
                            Project_Details_fragment.this.title.setText(((DB_Items) Personal_ListAdapter.this.mItems.get(i)).getTitle());
                            return;
                        }
                    }
                }
            });
            itemViewHolder.handleView.setOnTouchListener(new View.OnTouchListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.Personal_ListAdapter.AnonymousClass2 */

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (MotionEventCompat.getActionMasked(motionEvent) != 0) {
                        return false;
                    }
                    Personal_ListAdapter.this.mDragStartListener.onStartDrag(itemViewHolder);
                    return false;
                }
            });
            itemViewHolder.remove.setOnClickListener(new View.OnClickListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.Personal_ListAdapter.AnonymousClass3 */

                public void onClick(final View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Personal_ListAdapter.this.mcontext);
                    builder.setMessage("Are you sure want to delete this?").setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.Personal_ListAdapter.AnonymousClass3.AnonymousClass2 */

                        public void onClick(DialogInterface dialogInterface, int i) {
                            for (int i2 = 0; i2 < Personal_ListAdapter.this.mItems.size(); i2++) {
                                if (((DB_Items) Personal_ListAdapter.this.mItems.get(i2)).getTitle().equals(view.getTag().toString())) {
                                    if (Personal_ListAdapter.this.mItems.size() == 1 && Project_Details_fragment.project_title.getText().toString().equals("")) {
                                        SQLiteDatabase sQLiteDatabase = Project_Details_fragment.DB;
                                        sQLiteDatabase.execSQL("delete from project_table where profile_id='" + Project_Details_fragment.sp.getString(Personal_ListAdapter.this.mcontext, "profile_id") + "' and title = '';");
                                    }
                                    SQLiteDatabase sQLiteDatabase2 = Project_Details_fragment.DB;
                                    sQLiteDatabase2.execSQL("delete from extra_table where profile_id='" + Project_Details_fragment.sp.getString(Personal_ListAdapter.this.mcontext, "profile_id") + "' and table_name='project_table' and table_id='" + Project_Details_fragment.sp.getInt(Project_Details_fragment.context, "project_id") + "' and title='" + ((DB_Items) Personal_ListAdapter.this.mItems.get(i2)).getTitle().replaceAll("'", "''") + "' and value='" + ((DB_Items) Personal_ListAdapter.this.mItems.get(i2)).getValue().replaceAll("'", "''") + "';");
                                    Personal_ListAdapter.this.mItems.remove(i2);
                                    Personal_ListAdapter.this.notifyDataSetChanged();
                                    return;
                                }
                            }
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment.Personal_ListAdapter.AnonymousClass3.AnonymousClass1 */

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
