package nithra.resume.maker.cv.builder.app.Fragment;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nithra.resume.maker.cv.builder.app.Adapter.Skill_ListAdapter;
import nithra.resume.maker.cv.builder.app.MainActivity;
import nithra.resume.maker.cv.builder.app.Models.DB_Items;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.SharedPreference;
import nithra.resume.maker.cv.builder.app.Touch.OnStartDragListener;
import nithra.resume.maker.cv.builder.app.Touch.SimpleItemTouchHelperCallback;
import nithra.resume.maker.cv.builder.app.Utils;
import nithra.resume.maker.cv.builder.app.Visible;

public class Skills_fragment extends Fragment implements Visible, OnStartDragListener {
    SQLiteDatabase DB;
    Activity context;
    Skill_ListAdapter hobby_adapter;
    CardView hobby_add;
    ImageView hobby_edit;
    CardView hobby_edit_view;
    LinearLayout hobby_linear;
    List<DB_Items> hobby_more_data = new ArrayList();
    ExpandableHeightRecyclerView hobby_more_recycle;
    AppCompatTextView hobby_title;
    ImageView hobby_update;
    AppCompatEditText hobby_update_text;
    int i = 0;
    Skill_ListAdapter intrest_adapter;
    CardView intrest_add;
    ImageView intrest_edit;
    CardView intrest_edit_view;
    LinearLayout intrest_linear;
    List<DB_Items> intrest_more_data = new ArrayList();
    ExpandableHeightRecyclerView intrest_more_recycle;
    AppCompatTextView intrest_title;
    ImageView intrest_update;
    AppCompatEditText intrest_update_text;
    int j = 0;
    int k = 0;
    int l = 0;
    LinearLayout linear;
    int m = 0;
    ItemTouchHelper mItemTouchHelper;
    ItemTouchHelper mItemTouchHelper1;
    ItemTouchHelper mItemTouchHelper2;
    ItemTouchHelper mItemTouchHelper3;
    ScrollView scroll;
    Skill_ListAdapter skill_adapter;
    CardView skill_add;
    ImageView skill_edit;
    CardView skill_edit_view;
    LinearLayout skill_linear;
    List<DB_Items> skill_more_data = new ArrayList();
    ExpandableHeightRecyclerView skill_more_recycle;
    Button skill_save;
    AppCompatTextView skill_title;
    ImageView skill_update;
    AppCompatEditText skill_update_text;
    SharedPreference sp = new SharedPreference();
    Skill_ListAdapter strength_adapter;
    CardView strength_add;
    ImageView strength_edit;
    CardView strength_edit_view;
    LinearLayout strength_linear;
    List<DB_Items> strength_more_data = new ArrayList();
    ExpandableHeightRecyclerView strength_more_recycle;
    AppCompatTextView strength_title;
    ImageView strength_update;
    AppCompatEditText strength_update_text;
    AppCompatTextView title;

    public static int getItem(int i2) {
        return 0;
    }

    public static float dpToPixels(int i2, Context context2) {
        return ((float) i2) * context2.getResources().getDisplayMetrics().density;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.context = getActivity();
        View inflate = layoutInflater.inflate(R.layout.fragment_skills, viewGroup, false);
        this.linear = (LinearLayout) inflate.findViewById(R.id.linear);
        this.intrest_linear = (LinearLayout) inflate.findViewById(R.id.intrest_linear);
        this.intrest_add = (CardView) inflate.findViewById(R.id.intrest_add);
        this.scroll = (ScrollView) inflate.findViewById(R.id.scroll);
        this.intrest_edit = (ImageView) inflate.findViewById(R.id.intrest_edit);
        this.intrest_edit_view = (CardView) inflate.findViewById(R.id.intrest_edit_view);
        this.intrest_update_text = (AppCompatEditText) inflate.findViewById(R.id.intrest_update_text);
        this.intrest_update = (ImageView) inflate.findViewById(R.id.intrest_update);
        this.intrest_title = (AppCompatTextView) inflate.findViewById(R.id.intrest_title);
        this.skill_edit = (ImageView) inflate.findViewById(R.id.skill_edit);
        this.skill_edit_view = (CardView) inflate.findViewById(R.id.skill_edit_view);
        this.skill_update_text = (AppCompatEditText) inflate.findViewById(R.id.skill_update_text);
        this.skill_update = (ImageView) inflate.findViewById(R.id.skill_update);
        this.skill_title = (AppCompatTextView) inflate.findViewById(R.id.skill_title);
        this.strength_edit = (ImageView) inflate.findViewById(R.id.strength_edit);
        this.strength_edit_view = (CardView) inflate.findViewById(R.id.strength_edit_view);
        this.strength_update = (ImageView) inflate.findViewById(R.id.strength_update);
        this.strength_update_text = (AppCompatEditText) inflate.findViewById(R.id.strength_update_text);
        this.strength_title = (AppCompatTextView) inflate.findViewById(R.id.strength_title);
        this.hobby_edit = (ImageView) inflate.findViewById(R.id.hobby_edit);
        this.hobby_edit_view = (CardView) inflate.findViewById(R.id.hobby_edit_view);
        this.hobby_update = (ImageView) inflate.findViewById(R.id.hobby_update);
        this.hobby_update_text = (AppCompatEditText) inflate.findViewById(R.id.hobby_update_text);
        this.hobby_title = (AppCompatTextView) inflate.findViewById(R.id.hobby_title);
        this.skill_linear = (LinearLayout) inflate.findViewById(R.id.skill_linear);
        this.strength_linear = (LinearLayout) inflate.findViewById(R.id.strength_linear);
        this.hobby_linear = (LinearLayout) inflate.findViewById(R.id.hobby_linear);
        this.skill_add = (CardView) inflate.findViewById(R.id.skill_add);
        this.strength_add = (CardView) inflate.findViewById(R.id.strength_add);
        this.hobby_add = (CardView) inflate.findViewById(R.id.hobby_add);
        this.skill_save = (Button) inflate.findViewById(R.id.skill_save);
        this.intrest_more_recycle = (ExpandableHeightRecyclerView) inflate.findViewById(R.id.intrest_more_recycle);
        this.skill_more_recycle = (ExpandableHeightRecyclerView) inflate.findViewById(R.id.skill_more_recycle);
        this.strength_more_recycle = (ExpandableHeightRecyclerView) inflate.findViewById(R.id.strength_more_recycle);
        this.hobby_more_recycle = (ExpandableHeightRecyclerView) inflate.findViewById(R.id.hobby_more_recycle);
        this.DB = this.context.openOrCreateDatabase("RESUME_BUILDER", 0, null);
        this.intrest_more_recycle.setExpanded(true);
        this.skill_more_recycle.setExpanded(true);
        this.strength_more_recycle.setExpanded(true);
        this.hobby_more_recycle.setExpanded(true);
        this.intrest_more_data.clear();
        this.intrest_more_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        this.intrest_adapter = new Skill_ListAdapter(this.context, this, this.intrest_more_data, "interest_table", "interest", this.intrest_title);
        this.intrest_more_recycle.setAdapter(this.intrest_adapter);
        this.mItemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.intrest_adapter));
        this.mItemTouchHelper.attachToRecyclerView(this.intrest_more_recycle);
        this.intrest_more_recycle.setHasFixedSize(true);
        this.skill_more_data.clear();
        this.skill_more_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        this.skill_adapter = new Skill_ListAdapter(this.context, this, this.skill_more_data, "skill_table", "skill", this.skill_title);
        this.skill_more_recycle.setAdapter(this.skill_adapter);
        this.mItemTouchHelper1 = new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.skill_adapter));
        this.mItemTouchHelper1.attachToRecyclerView(this.skill_more_recycle);
        this.skill_more_recycle.setHasFixedSize(true);
        this.strength_more_data.clear();
        this.strength_more_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        this.strength_adapter = new Skill_ListAdapter(this.context, this, this.strength_more_data, "strength_table", "strength", this.strength_title);
        this.strength_more_recycle.setAdapter(this.strength_adapter);
        this.mItemTouchHelper2 = new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.strength_adapter));
        this.mItemTouchHelper2.attachToRecyclerView(this.strength_more_recycle);
        this.strength_more_recycle.setHasFixedSize(true);
        this.hobby_more_data.clear();
        this.hobby_more_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        this.hobby_adapter = new Skill_ListAdapter(this.context, this, this.hobby_more_data, "hobbies_table", "hobbies", this.hobby_title);
        this.hobby_more_recycle.setAdapter(this.hobby_adapter);
        this.mItemTouchHelper3 = new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.hobby_adapter));
        this.mItemTouchHelper3.attachToRecyclerView(this.hobby_more_recycle);
        this.hobby_more_recycle.setHasFixedSize(true);
        this.sp.putString(this.context, "save", "first");
        Update_Load("interest_table", "interest", this.intrest_title, this.intrest_more_data, this.intrest_adapter);
        Update_Load("skill_table", "skill", this.skill_title, this.skill_more_data, this.skill_adapter);
        Update_Load("strength_table", "strength", this.strength_title, this.strength_more_data, this.strength_adapter);
        Update_Load("hobbies_table", "hobbies", this.hobby_title, this.hobby_more_data, this.hobby_adapter);
        this.skill_save.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass1 */

            public void onClick(View view) {
                Skills_fragment skills_fragment = Skills_fragment.this;
                skills_fragment.final_save("interest_table", "interest", skills_fragment.intrest_title, Skills_fragment.this.intrest_more_data);
                Skills_fragment skills_fragment2 = Skills_fragment.this;
                skills_fragment2.final_save("skill_table", "skill", skills_fragment2.skill_title, Skills_fragment.this.skill_more_data);
                Skills_fragment skills_fragment3 = Skills_fragment.this;
                skills_fragment3.final_save("strength_table", "strength", skills_fragment3.strength_title, Skills_fragment.this.strength_more_data);
                Skills_fragment skills_fragment4 = Skills_fragment.this;
                skills_fragment4.final_save("hobbies_table", "hobbies", skills_fragment4.hobby_title, Skills_fragment.this.hobby_more_data);
                Skills_fragment.this.hideSoftKeyboard();
                if (Skills_fragment.this.sp.getString(Skills_fragment.this.context, "save").equals("first")) {
                    SQLiteDatabase sQLiteDatabase = Skills_fragment.this.DB;
                    Cursor rawQuery = sQLiteDatabase.rawQuery("Select profile_id from interest_table where profile_id='" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "'", null);
                    SQLiteDatabase sQLiteDatabase2 = Skills_fragment.this.DB;
                    Cursor rawQuery2 = sQLiteDatabase2.rawQuery("Select profile_id from skill_table where profile_id='" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "'", null);
                    SQLiteDatabase sQLiteDatabase3 = Skills_fragment.this.DB;
                    Cursor rawQuery3 = sQLiteDatabase3.rawQuery("Select profile_id from strength_table where profile_id='" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "'", null);
                    SQLiteDatabase sQLiteDatabase4 = Skills_fragment.this.DB;
                    Cursor rawQuery4 = sQLiteDatabase4.rawQuery("Select profile_id from hobbies_table where profile_id='" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "'", null);
                    if (rawQuery.getCount() > 0) {
                        Skills_fragment.this.sp.putString(Skills_fragment.this.context, "save", "two");
                        Utils.toast_center(Skills_fragment.this.context, "Skills Saved Successfully");
                        Skills_fragment.this.skill_save.setText("UPDATE");
                    } else if (rawQuery2.getCount() > 0) {
                        Skills_fragment.this.sp.putString(Skills_fragment.this.context, "save", "two");
                        Utils.toast_center(Skills_fragment.this.context, "Skills Saved Successfully");
                        Skills_fragment.this.skill_save.setText("UPDATE");
                    } else if (rawQuery3.getCount() > 0) {
                        Skills_fragment.this.sp.putString(Skills_fragment.this.context, "save", "two");
                        Utils.toast_center(Skills_fragment.this.context, "Skills Saved Successfully");
                        Skills_fragment.this.skill_save.setText("UPDATE");
                    } else if (rawQuery4.getCount() > 0) {
                        Skills_fragment.this.sp.putString(Skills_fragment.this.context, "save", "two");
                        Utils.toast_center(Skills_fragment.this.context, "Skills Saved Successfully");
                        Skills_fragment.this.skill_save.setText("UPDATE");
                    } else {
                        Skills_fragment.this.skill_save.setText("SAVE");
                    }
                    rawQuery.close();
                    rawQuery2.close();
                    rawQuery3.close();
                    rawQuery4.close();
                    return;
                }
                Skills_fragment.this.sp.putString(Skills_fragment.this.context, "save", "two");
                Utils.toast_center(Skills_fragment.this.context, "Skills Updated Successfully");
                Skills_fragment.this.skill_save.setText("UPDATE");
            }
        });
        this.intrest_add.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass2 */

            public void onClick(View view) {
                Skills_fragment skills_fragment = Skills_fragment.this;
                skills_fragment.final_save("interest_table", "interest", skills_fragment.intrest_title, Skills_fragment.this.intrest_more_data);
                Skills_fragment.this.hideSoftKeyboard();
                Skills_fragment skills_fragment2 = Skills_fragment.this;
                skills_fragment2.add_dialog(skills_fragment2.intrest_more_data, Skills_fragment.this.intrest_adapter, "interest_table", "interest", Skills_fragment.this.intrest_title);
            }
        });
        this.skill_add.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass3 */

            public void onClick(View view) {
                Skills_fragment skills_fragment = Skills_fragment.this;
                skills_fragment.final_save("skill_table", "skill", skills_fragment.skill_title, Skills_fragment.this.skill_more_data);
                Skills_fragment.this.hideSoftKeyboard();
                Skills_fragment skills_fragment2 = Skills_fragment.this;
                skills_fragment2.add_dialog(skills_fragment2.skill_more_data, Skills_fragment.this.skill_adapter, "skill_table", "skill", Skills_fragment.this.skill_title);
            }
        });
        this.strength_add.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass4 */

            public void onClick(View view) {
                Skills_fragment skills_fragment = Skills_fragment.this;
                skills_fragment.final_save("strength_table", "strength", skills_fragment.strength_title, Skills_fragment.this.strength_more_data);
                Skills_fragment.this.hideSoftKeyboard();
                Skills_fragment skills_fragment2 = Skills_fragment.this;
                skills_fragment2.add_dialog(skills_fragment2.strength_more_data, Skills_fragment.this.strength_adapter, "strength_table", "strength", Skills_fragment.this.strength_title);
            }
        });
        this.hobby_add.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass5 */

            public void onClick(View view) {
                Skills_fragment skills_fragment = Skills_fragment.this;
                skills_fragment.final_save("hobbies_table", "hobbies", skills_fragment.hobby_title, Skills_fragment.this.hobby_more_data);
                Skills_fragment.this.hideSoftKeyboard();
                Skills_fragment skills_fragment2 = Skills_fragment.this;
                skills_fragment2.add_dialog(skills_fragment2.hobby_more_data, Skills_fragment.this.hobby_adapter, "hobbies_table", "hobbies", Skills_fragment.this.hobby_title);
            }
        });
        this.intrest_edit_view.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass6 */

            public void onClick(View view) {
            }
        });
        this.hobby_edit_view.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass7 */

            public void onClick(View view) {
            }
        });
        this.strength_edit_view.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass8 */

            public void onClick(View view) {
            }
        });
        this.skill_edit_view.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass9 */

            public void onClick(View view) {
            }
        });
        this.intrest_edit.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass10 */

            public void onClick(View view) {
                if (Skills_fragment.this.intrest_edit_view.isShown()) {
                    Skills_fragment.this.intrest_edit_view.setVisibility(View.GONE);
                    Skills_fragment.this.hideSoftKeyboard();
                    return;
                }
                Skills_fragment.this.intrest_update_text.clearFocus();
                Skills_fragment.this.intrest_update_text.setError(null);
                Skills_fragment.this.intrest_update_text.setText(Skills_fragment.this.intrest_title.getText().toString());
                Skills_fragment.this.intrest_update_text.setSelection(Skills_fragment.this.intrest_title.getText().toString().length());
                Skills_fragment skills_fragment = Skills_fragment.this;
                skills_fragment.showSoftKeyboard(skills_fragment.intrest_update_text);
                Skills_fragment.this.intrest_edit_view.setVisibility(View.VISIBLE);
                Skills_fragment.this.hobby_edit_view.setVisibility(View.GONE);
                Skills_fragment.this.strength_edit_view.setVisibility(View.GONE);
                Skills_fragment.this.skill_edit_view.setVisibility(View.GONE);
            }
        });
        this.skill_edit.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass11 */

            public void onClick(View view) {
                if (Skills_fragment.this.skill_edit_view.isShown()) {
                    Skills_fragment.this.skill_edit_view.setVisibility(View.GONE);
                    Skills_fragment.this.hideSoftKeyboard();
                    return;
                }
                Skills_fragment.this.skill_update_text.clearFocus();
                Skills_fragment.this.skill_update_text.setError(null);
                Skills_fragment.this.skill_update_text.setText(Skills_fragment.this.skill_title.getText().toString());
                Skills_fragment.this.skill_update_text.setSelection(Skills_fragment.this.skill_title.getText().toString().length());
                Skills_fragment skills_fragment = Skills_fragment.this;
                skills_fragment.showSoftKeyboard(skills_fragment.skill_update_text);
                Skills_fragment.this.skill_edit_view.setVisibility(View.VISIBLE);
                Skills_fragment.this.hobby_edit_view.setVisibility(View.GONE);
                Skills_fragment.this.intrest_edit_view.setVisibility(View.GONE);
                Skills_fragment.this.strength_edit_view.setVisibility(View.GONE);
            }
        });
        this.strength_edit.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass12 */

            public void onClick(View view) {
                if (Skills_fragment.this.strength_edit_view.isShown()) {
                    Skills_fragment.this.strength_edit_view.setVisibility(View.GONE);
                    Skills_fragment.this.hideSoftKeyboard();
                    return;
                }
                Skills_fragment.this.strength_update_text.clearFocus();
                Skills_fragment.this.strength_update_text.setError(null);
                Skills_fragment.this.strength_update_text.setText(Skills_fragment.this.strength_title.getText().toString());
                Skills_fragment.this.strength_update_text.setSelection(Skills_fragment.this.strength_title.getText().toString().length());
                Skills_fragment skills_fragment = Skills_fragment.this;
                skills_fragment.showSoftKeyboard(skills_fragment.strength_update_text);
                Skills_fragment.this.strength_edit_view.setVisibility(View.VISIBLE);
                Skills_fragment.this.hobby_edit_view.setVisibility(View.GONE);
                Skills_fragment.this.intrest_edit_view.setVisibility(View.GONE);
                Skills_fragment.this.skill_edit_view.setVisibility(View.GONE);
            }
        });
        this.hobby_edit.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass13 */

            public void onClick(View view) {
                if (Skills_fragment.this.hobby_edit_view.isShown()) {
                    Skills_fragment.this.hobby_edit_view.setVisibility(View.GONE);
                    Skills_fragment.this.hideSoftKeyboard();
                    return;
                }
                Skills_fragment.this.hobby_update_text.clearFocus();
                Skills_fragment.this.hobby_update_text.setError(null);
                Skills_fragment.this.hobby_update_text.setText(Skills_fragment.this.hobby_title.getText().toString());
                Skills_fragment.this.hobby_update_text.setSelection(Skills_fragment.this.hobby_title.getText().toString().length());
                Skills_fragment skills_fragment = Skills_fragment.this;
                skills_fragment.showSoftKeyboard(skills_fragment.hobby_update_text);
                Skills_fragment.this.hobby_edit_view.setVisibility(View.VISIBLE);
                Skills_fragment.this.intrest_edit_view.setVisibility(View.GONE);
                Skills_fragment.this.strength_edit_view.setVisibility(View.GONE);
                Skills_fragment.this.skill_edit_view.setVisibility(View.GONE);
            }
        });
        this.hobby_update.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass14 */

            public void onClick(View view) {
                if (Skills_fragment.this.hobby_update_text.getText().toString().isEmpty()) {
                    Skills_fragment.this.hobby_update_text.setError("Enter your title..");
                    Skills_fragment.this.hobby_update_text.requestFocus();
                    return;
                }
                Skills_fragment.this.hobby_title.setText(Skills_fragment.this.hobby_update_text.getText().toString());
                SQLiteDatabase sQLiteDatabase = Skills_fragment.this.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("Select hobbies_title from title_table where profile_id='" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "'", null);
                if (rawQuery.getCount() == 0) {
                    SQLiteDatabase sQLiteDatabase2 = Skills_fragment.this.DB;
                    sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, hobbies_title) VALUES ('" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "', '" + Skills_fragment.this.hobby_title.getText().toString().replaceAll("'", "''") + "');");
                } else {
                    SQLiteDatabase sQLiteDatabase3 = Skills_fragment.this.DB;
                    sQLiteDatabase3.execSQL("UPDATE title_table SET hobbies_title = '" + Skills_fragment.this.hobby_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "';");
                }
                rawQuery.close();
                Skills_fragment.this.hobby_edit_view.setVisibility(View.GONE);
                Skills_fragment.this.hideSoftKeyboard();
            }
        });
        this.hobby_update_text.setOnKeyListener(new View.OnKeyListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass15 */

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 6) {
                    return false;
                }
                Skills_fragment.this.hideSoftKeyboard();
                return false;
            }
        });
        this.strength_update.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass16 */

            public void onClick(View view) {
                if (Skills_fragment.this.strength_update_text.getText().toString().isEmpty()) {
                    Skills_fragment.this.strength_update_text.setError("Enter your title..");
                    Skills_fragment.this.strength_update_text.requestFocus();
                    return;
                }
                Skills_fragment.this.strength_title.setText(Skills_fragment.this.strength_update_text.getText().toString());
                SQLiteDatabase sQLiteDatabase = Skills_fragment.this.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("Select strength_title from title_table where profile_id='" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "'", null);
                if (rawQuery.getCount() == 0) {
                    SQLiteDatabase sQLiteDatabase2 = Skills_fragment.this.DB;
                    sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, strength_title) VALUES ('" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "', '" + Skills_fragment.this.strength_title.getText().toString().replaceAll("'", "''") + "');");
                } else {
                    SQLiteDatabase sQLiteDatabase3 = Skills_fragment.this.DB;
                    sQLiteDatabase3.execSQL("UPDATE title_table SET strength_title = '" + Skills_fragment.this.strength_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "';");
                }
                rawQuery.close();
                Skills_fragment.this.strength_edit_view.setVisibility(View.GONE);
                Skills_fragment.this.hideSoftKeyboard();
            }
        });
        this.strength_update_text.setOnKeyListener(new View.OnKeyListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass17 */

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 6) {
                    return false;
                }
                Skills_fragment.this.hideSoftKeyboard();
                return false;
            }
        });
        this.skill_update.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass18 */

            public void onClick(View view) {
                if (Skills_fragment.this.skill_update_text.getText().toString().isEmpty()) {
                    Skills_fragment.this.skill_update_text.setError("Enter your title..");
                    Skills_fragment.this.skill_update_text.requestFocus();
                    return;
                }
                Skills_fragment.this.skill_title.setText(Skills_fragment.this.skill_update_text.getText().toString());
                SQLiteDatabase sQLiteDatabase = Skills_fragment.this.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("Select skill_title from title_table where profile_id='" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "'", null);
                if (rawQuery.getCount() == 0) {
                    SQLiteDatabase sQLiteDatabase2 = Skills_fragment.this.DB;
                    sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, skill_title) VALUES ('" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "', '" + Skills_fragment.this.skill_title.getText().toString().replaceAll("'", "''") + "');");
                } else {
                    SQLiteDatabase sQLiteDatabase3 = Skills_fragment.this.DB;
                    sQLiteDatabase3.execSQL("UPDATE title_table SET skill_title = '" + Skills_fragment.this.skill_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "';");
                }
                rawQuery.close();
                Skills_fragment.this.skill_edit_view.setVisibility(View.GONE);
                Skills_fragment.this.hideSoftKeyboard();
            }
        });
        this.skill_update_text.setOnKeyListener(new View.OnKeyListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass19 */

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 6) {
                    return false;
                }
                Skills_fragment.this.hideSoftKeyboard();
                return false;
            }
        });
        this.intrest_update.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass20 */

            public void onClick(View view) {
                if (Skills_fragment.this.intrest_update_text.getText().toString().isEmpty()) {
                    Skills_fragment.this.intrest_update_text.setError("Enter your title..");
                    Skills_fragment.this.intrest_update_text.requestFocus();
                    return;
                }
                Skills_fragment.this.intrest_title.setText(Skills_fragment.this.intrest_update_text.getText().toString());
                SQLiteDatabase sQLiteDatabase = Skills_fragment.this.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("Select interest_title from title_table where profile_id='" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "'", null);
                if (rawQuery.getCount() == 0) {
                    SQLiteDatabase sQLiteDatabase2 = Skills_fragment.this.DB;
                    sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, interest_title) VALUES ('" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "', '" + Skills_fragment.this.intrest_title.getText().toString().replaceAll("'", "''") + "');");
                } else {
                    SQLiteDatabase sQLiteDatabase3 = Skills_fragment.this.DB;
                    sQLiteDatabase3.execSQL("UPDATE title_table SET interest_title = '" + Skills_fragment.this.intrest_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "';");
                }
                rawQuery.close();
                Skills_fragment.this.intrest_edit_view.setVisibility(View.GONE);
                Skills_fragment.this.hideSoftKeyboard();
            }
        });
        this.intrest_update_text.setOnKeyListener(new View.OnKeyListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass21 */

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 6) {
                    return false;
                }
                Skills_fragment.this.hideSoftKeyboard();
                return false;
            }
        });
        return inflate;
    }

    public void hideSoftKeyboard() {
        if (this.context.getCurrentFocus() != null) {
            ((InputMethodManager) this.context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.context.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            ((InputMethodManager) this.context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 1);
        }
    }

    public void final_save(List<String> list, String str, String str2, AppCompatTextView appCompatTextView, AppCompatEditText appCompatEditText, List<AppCompatEditText> list2, List<Integer> list3) {
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select profile_id from " + str + " where profile_id='" + this.sp.getString(this.context, "profile_id") + "'", null);
        if (rawQuery.getCount() == 0) {
            SQLiteDatabase sQLiteDatabase2 = this.DB;
            sQLiteDatabase2.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(this.context, "profile_id") + "', '" + appCompatEditText.getText().toString().replaceAll("'", "''") + "');");
            for (int i2 = 0; i2 < list2.size(); i2++) {
                if (!list2.get(i2).getText().toString().equals("") && !list2.get(i2).getText().toString().equals(null)) {
                    SQLiteDatabase sQLiteDatabase3 = this.DB;
                    sQLiteDatabase3.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(this.context, "profile_id") + "', '" + list2.get(i2).getText().toString().replaceAll("'", "''") + "');");
                }
            }
            if (str2.equals("hobbies")) {
                Toast.makeText(this.context, "Saved Successfully", Toast.LENGTH_SHORT).show();
                this.skill_save.setText("UPDATE");
            }
        } else {
            if (list3.size() > 0) {
                for (int i3 = 0; i3 < list3.size(); i3++) {
                    list.remove(list3.get(i3));
                    list2.get(list3.get(i3).intValue()).setText("");
                    if (i3 == list3.size() - 1) {
                        SQLiteDatabase sQLiteDatabase4 = this.DB;
                        sQLiteDatabase4.execSQL("delete from " + str + " where profile_id='" + this.sp.getString(this.context, "profile_id") + "';");
                        SQLiteDatabase sQLiteDatabase5 = this.DB;
                        sQLiteDatabase5.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(this.context, "profile_id") + "', '" + appCompatEditText.getText().toString().replaceAll("'", "''") + "');");
                        for (int i4 = 0; i4 < list2.size(); i4++) {
                            if (!list2.get(i4).getText().toString().equals("") && !list2.get(i4).getText().toString().equals(null)) {
                                SQLiteDatabase sQLiteDatabase6 = this.DB;
                                sQLiteDatabase6.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(this.context, "profile_id") + "', '" + list2.get(i4).getText().toString().replaceAll("'", "''") + "');");
                            }
                        }
                    }
                }
            } else {
                SQLiteDatabase sQLiteDatabase7 = this.DB;
                sQLiteDatabase7.execSQL("delete from " + str + " where profile_id='" + this.sp.getString(this.context, "profile_id") + "';");
                SQLiteDatabase sQLiteDatabase8 = this.DB;
                sQLiteDatabase8.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(this.context, "profile_id") + "', '" + appCompatEditText.getText().toString().replaceAll("'", "''") + "');");
                for (int i5 = 0; i5 < list2.size(); i5++) {
                    if (!list2.get(i5).getText().toString().equals("") && !list2.get(i5).getText().toString().equals(null)) {
                        SQLiteDatabase sQLiteDatabase9 = this.DB;
                        sQLiteDatabase9.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(this.context, "profile_id") + "', '" + list2.get(i5).getText().toString().replaceAll("'", "''") + "');");
                    }
                }
            }
            if (str2.equals("hobbies")) {
                Toast.makeText(this.context, "Updated Successfully", Toast.LENGTH_SHORT).show();
                this.skill_save.setText("UPDATE");
            }
        }
        rawQuery.close();
    }

    public void final_save(String str, String str2, AppCompatTextView appCompatTextView, List<DB_Items> list) {
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select " + str2 + "_title from title_table where profile_id='" + this.sp.getString(this.context, "profile_id") + "'", null);
        if (rawQuery.getCount() == 0) {
            SQLiteDatabase sQLiteDatabase2 = this.DB;
            sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, " + str2 + "_title) VALUES ('" + this.sp.getString(this.context, "profile_id") + "', '" + appCompatTextView.getText().toString().replaceAll("'", "''") + "');");
        } else {
            SQLiteDatabase sQLiteDatabase3 = this.DB;
            sQLiteDatabase3.execSQL("UPDATE title_table SET " + str2 + "_title = '" + appCompatTextView.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + this.sp.getString(this.context, "profile_id") + "';");
        }
        rawQuery.close();
        SQLiteDatabase sQLiteDatabase4 = this.DB;
        Cursor rawQuery2 = sQLiteDatabase4.rawQuery("Select profile_id from " + str + " where profile_id='" + this.sp.getString(this.context, "profile_id") + "'", null);
        int i2 = 0;
        if (rawQuery2.getCount() == 0) {
            while (i2 < list.size()) {
                if (!list.get(i2).getValue().equals("") && !list.get(i2).getValue().equals(null)) {
                    SQLiteDatabase sQLiteDatabase5 = this.DB;
                    sQLiteDatabase5.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(this.context, "profile_id") + "', '" + list.get(i2).getValue().replaceAll("'", "''") + "');");
                }
                i2++;
            }
        } else {
            SQLiteDatabase sQLiteDatabase6 = this.DB;
            sQLiteDatabase6.execSQL("delete from " + str + " where profile_id='" + this.sp.getString(this.context, "profile_id") + "';");
            while (i2 < list.size()) {
                if (!list.get(i2).getValue().equals("") && !list.get(i2).getValue().equals(null)) {
                    SQLiteDatabase sQLiteDatabase7 = this.DB;
                    sQLiteDatabase7.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(this.context, "profile_id") + "', '" + list.get(i2).getValue().replaceAll("'", "''") + "');");
                }
                i2++;
            }
        }
        rawQuery2.close();
    }

    public void view_add(final int i2, final List<Integer> list, LinearLayout linearLayout, AppCompatTextView appCompatTextView, final List<AppCompatEditText> list2) {
        final View inflate = LayoutInflater.from(this.context).inflate(R.layout.item_add_more, (ViewGroup) null, false);
        AppCompatEditText appCompatEditText = (AppCompatEditText) inflate.findViewById(R.id.extra);
        AppCompatEditText appCompatEditText2 = (AppCompatEditText) inflate.findViewById(R.id.en_title);
        ((LinearLayout) inflate.findViewById(R.id.first)).setVisibility(View.GONE);
        appCompatEditText.setHint(appCompatTextView.getText().toString() + " " + (i2 + 2));
        StringBuilder sb = new StringBuilder();
        sb.append("Title ");
        sb.append(i2 + 1);
        ((AppCompatTextView) inflate.findViewById(R.id.title)).setText(sb.toString());
        linearLayout.addView(inflate);
        list2.add(appCompatEditText);
        showSoftKeyboard(appCompatEditText);
        linearLayout.setLayoutTransition(new LayoutTransition());
        ((ImageView) inflate.findViewById(R.id.del_view)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass22 */

            public void onClick(View view) {
                ((LinearLayout) inflate.getParent()).removeView(inflate);
                list.add(Integer.valueOf(i2));
                ((AppCompatEditText) list2.get(i2)).setText("");
            }
        });
    }

    public void Update_Load(int i2, LinearLayout linearLayout, final List<Integer> list, List<String> list2, String str, String str2, AppCompatTextView appCompatTextView, AppCompatEditText appCompatEditText, List<AppCompatEditText> list3) {
        list.clear();
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from " + str + " where profile_id='" + this.sp.getString(this.context, "profile_id") + "'", null);
        rawQuery.moveToPosition(0);
        if (rawQuery.getCount() > 0) {
            appCompatEditText.setText("" + rawQuery.getString(rawQuery.getColumnIndex(str2)));
            if (rawQuery.getCount() > 1) {
                list2.clear();
                SQLiteDatabase sQLiteDatabase2 = this.DB;
                Cursor rawQuery2 = sQLiteDatabase2.rawQuery("Select * from " + str + " where profile_id='" + this.sp.getString(this.context, "profile_id") + "'", null);
                rawQuery2.moveToPosition(1);
                do {
                    list2.add(rawQuery2.getString(rawQuery2.getColumnIndexOrThrow(str2)));
                } while (rawQuery2.moveToNext());
                rawQuery2.close();
                if (i2 == 1) {
                    this.i = list2.size();
                } else if (i2 == 2) {
                    this.j = list2.size();
                } else if (i2 == 3) {
                    this.k = list2.size();
                } else if (i2 == 4) {
                    this.l = list2.size();
                }
                this.i = list2.size();
                for (int i3 = 0; i3 < list2.size(); i3++) {
                    final View inflate = LayoutInflater.from(this.context).inflate(R.layout.item_add_more, (ViewGroup) null, false);
                    final AppCompatEditText appCompatEditText2 = (AppCompatEditText) inflate.findViewById(R.id.extra);
                    appCompatEditText2.setId(i3);
                    ((LinearLayout) inflate.findViewById(R.id.first)).setVisibility(View.GONE);
                    appCompatEditText2.setText(list2.get(i3));
                    ((AppCompatTextView) inflate.findViewById(R.id.title)).setText("Title " + i3);
                    linearLayout.addView(inflate);
                    list3.add(appCompatEditText2);
                    linearLayout.setLayoutTransition(new LayoutTransition());
                    ((ImageView) inflate.findViewById(R.id.del_view)).setOnClickListener(new View.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass23 */

                        public void onClick(View view) {
                            ((LinearLayout) inflate.getParent()).removeView(inflate);
                            list.add(Integer.valueOf(appCompatEditText2.getId()));
                        }
                    });
                }
            }
        }
        rawQuery.close();
    }

    public void Update_Load(String str, String str2, AppCompatTextView appCompatTextView, List<DB_Items> list, Skill_ListAdapter skill_ListAdapter) {
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select " + str2 + "_title from title_table where profile_id='" + this.sp.getString(this.context, "profile_id") + "'", null);
        rawQuery.moveToFirst();
        if (rawQuery.getCount() > 0) {
            String string = rawQuery.getString(rawQuery.getColumnIndex("" + str2 + "_title"));
            if (string != null) {
                appCompatTextView.setText("" + string);
            }
        }
        rawQuery.close();
        SQLiteDatabase sQLiteDatabase2 = this.DB;
        Cursor rawQuery2 = sQLiteDatabase2.rawQuery("Select * from " + str + " where profile_id='" + this.sp.getString(this.context, "profile_id") + "'", null);
        rawQuery2.moveToPosition(0);
        if (rawQuery2.getCount() > 0) {
            list.clear();
            SQLiteDatabase sQLiteDatabase3 = this.DB;
            Cursor rawQuery3 = sQLiteDatabase3.rawQuery("Select * from " + str + " where profile_id='" + this.sp.getString(this.context, "profile_id") + "'", null);
            rawQuery3.moveToFirst();
            do {
                for (int i2 = 0; i2 < rawQuery3.getCount(); i2++) {
                    DB_Items dB_Items = new DB_Items();
                    dB_Items.setValue(rawQuery3.getString(rawQuery3.getColumnIndex(str2)));
                    list.add(dB_Items);
                    rawQuery3.moveToNext();
                }
                skill_ListAdapter.notifyDataSetChanged();
            } while (rawQuery3.moveToNext());
            rawQuery3.close();
            this.sp.putString(this.context, "save", "two");
            this.skill_save.setText("UPDATE");
        }
        rawQuery2.close();
    }

    @Override // nithra.resume.maker.cv.builder.app.Visible
    public void visible() {
        if (this.intrest_edit_view.isShown()) {
            this.intrest_edit_view.setVisibility(View.GONE);
        } else if (this.skill_edit_view.isShown()) {
            this.skill_edit_view.setVisibility(View.GONE);
        } else if (this.strength_edit_view.isShown()) {
            this.strength_edit_view.setVisibility(View.GONE);
        } else if (this.hobby_edit_view.isShown()) {
            this.hobby_edit_view.setVisibility(View.GONE);
        } else {
            exti_result_dialog();
        }
    }

    public void exti_result_dialog() {
        final Dialog dialog = new Dialog(this.context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_exit);
        MainActivity.load_addFromMain_natt(this.context, (FrameLayout) dialog.findViewById(R.id.ads_lay));
        ((TextView) dialog.findViewById(R.id.yes)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass24 */

            public void onClick(View view) {
                Skills_fragment.this.context.finish();
                dialog.dismiss();
            }
        });
        ((TextView) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass25 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override // nithra.resume.maker.cv.builder.app.Touch.OnStartDragListener
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        this.mItemTouchHelper.startDrag(viewHolder);
        this.mItemTouchHelper1.startDrag(viewHolder);
        this.mItemTouchHelper2.startDrag(viewHolder);
        this.mItemTouchHelper3.startDrag(viewHolder);
    }

    public void add_dialog(final List<DB_Items> list, final Skill_ListAdapter skill_ListAdapter, final String str, final String str2, final AppCompatTextView appCompatTextView) {
        final Dialog dialog = new Dialog(this.context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_add_view);
        this.title = (AppCompatTextView) dialog.findViewById(R.id.title);
        final AppCompatEditText appCompatEditText = (AppCompatEditText) dialog.findViewById(R.id.value);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.ok);
        ImageView imageView2 = (ImageView) dialog.findViewById(R.id.cancel);
        this.title.setVisibility(View.GONE);
        this.title.setText("");
        appCompatEditText.setText("");
        appCompatEditText.setHint("Enter your " + appCompatTextView.getText().toString() + "...");
        showSoftKeyboard(appCompatEditText);
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass26 */

            public void onClick(View view) {
                if (appCompatEditText.getText().toString().trim().equals("")) {

                    appCompatEditText.setError("Enter your " + appCompatTextView.getText().toString() + " !!!");
                    appCompatEditText.requestFocus();
                } else {
                    SQLiteDatabase sQLiteDatabase = Skills_fragment.this.DB;
                    if (sQLiteDatabase.rawQuery("Select upper(" + str2 + ") as " + str2 + " from " + str + " where profile_id='" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "' and  upper(" + str2 + ")='" + appCompatEditText.getText().toString().toUpperCase().replaceAll("'", "''") + "'", null).getCount() == 0) {
                        DB_Items dB_Items = new DB_Items();
                        dB_Items.setValue(appCompatEditText.getText().toString());
                        list.add(dB_Items);
                        skill_ListAdapter.notifyDataSetChanged();
                        Skills_fragment.this.final_save(str, str2, appCompatTextView, list);
                        Skills_fragment.this.hideSoftKeyboard();
                        dialog.dismiss();
                    } else {
                        appCompatEditText.setError("Already exist !!!");
                        appCompatEditText.requestFocus();
                    }
                }
                if (!Skills_fragment.this.sp.getString(Skills_fragment.this.context, "drag").equals("draged")) {
                    SQLiteDatabase sQLiteDatabase2 = Skills_fragment.this.DB;
                    Cursor rawQuery = sQLiteDatabase2.rawQuery("Select profile_id from " + str + " where profile_id='" + Skills_fragment.this.sp.getString(Skills_fragment.this.context, "profile_id") + "'", null);
                    if (rawQuery.getCount() > 1) {
                        Skills_fragment.this.drag_dialog();
                    }
                    rawQuery.close();
                }
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass27 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public void drag_dialog() {
        final Dialog dialog = new Dialog(this.context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass28 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass28.AnonymousClass1 */

                    public void run() {
                        cardView.startAnimation(loadAnimation);
                        imageView.startAnimation(loadAnimation);
                    }
                }, 1500);
            }
        });
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass29 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass29.AnonymousClass1 */

                    public void run() {
                        cardView.startAnimation(loadAnimation2);
                        imageView.startAnimation(loadAnimation2);
                    }
                }, 1500);
            }
        });
        ((Button) dialog.findViewById(R.id.enter)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment.AnonymousClass30 */

            public void onClick(View view) {
                Skills_fragment.this.sp.putString(Skills_fragment.this.context, "drag", "draged");
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
}
