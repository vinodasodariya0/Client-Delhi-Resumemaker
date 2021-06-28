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

public class Achievement_fragment extends Fragment implements Visible, OnStartDragListener {
    static Activity context;
    SQLiteDatabase DB;
    Skill_ListAdapter achieve_adapter;
    CardView achieve_add;
    ImageView achieve_edit;
    CardView achieve_edit_view;
    LinearLayout achieve_linear;
    List<DB_Items> achieve_more_data = new ArrayList();
    ExpandableHeightRecyclerView achieve_more_recycle;
    Button achieve_save;
    AppCompatEditText achieve_text;
    AppCompatTextView achieve_title;
    ImageView achieve_update;
    AppCompatEditText achieve_update_text;
    Skill_ListAdapter cocurricular_adapter;
    CardView cocurricular_add;
    ImageView cocurricular_edit;
    CardView cocurricular_edit_view;
    LinearLayout cocurricular_linear;
    List<DB_Items> cocurricular_more_data = new ArrayList();
    ExpandableHeightRecyclerView cocurricular_more_recycle;
    AppCompatEditText cocurricular_text;
    AppCompatTextView cocurricular_title;
    ImageView cocurricular_update;
    AppCompatEditText cocurricular_update_text;
    ImageView correct;
    LinearLayout edit;
    Skill_ListAdapter excurricular_adapter;
    CardView excurricular_add;
    ImageView excurricular_edit;
    CardView excurricular_edit_view;
    LinearLayout excurricular_linear;
    List<DB_Items> excurricular_more_data = new ArrayList();
    ExpandableHeightRecyclerView excurricular_more_recycle;
    AppCompatEditText excurricular_text;
    AppCompatTextView excurricular_title;
    ImageView excurricular_update;
    AppCompatEditText excurricular_update_text;
    int i = 0;
    Skill_ListAdapter indust_adapter;
    CardView indust_add;
    ImageView indust_edit;
    CardView indust_edit_view;
    LinearLayout indust_linear;
    List<DB_Items> indust_more_data = new ArrayList();
    ExpandableHeightRecyclerView indust_more_recycle;
    AppCompatEditText indust_text;
    AppCompatTextView indust_title;
    ImageView indust_update;
    AppCompatEditText indust_update_text;
    Skill_ListAdapter inplant_adapter;
    CardView inplant_add;
    ImageView inplant_edit;
    CardView inplant_edit_view;
    LinearLayout inplant_linear;
    List<DB_Items> inplant_more_data = new ArrayList();
    ExpandableHeightRecyclerView inplant_more_recycle;
    AppCompatEditText inplant_text;
    AppCompatTextView inplant_title;
    ImageView inplant_update;
    AppCompatEditText inplant_update_text;
    int j = 0;
    int k = 0;
    int l = 0;
    LinearLayout linear;
    int m = 0;
    ItemTouchHelper mItemTouchHelper;
    ItemTouchHelper mItemTouchHelper1;
    ItemTouchHelper mItemTouchHelper2;
    ItemTouchHelper mItemTouchHelper3;
    ItemTouchHelper mItemTouchHelper4;
    int n = 0;
    ScrollView scroll;
    SharedPreference sp = new SharedPreference();
    AppCompatTextView title;
    AppCompatEditText title_per;
    AppCompatTextView view_title;

    public static int getItem(int i2) {
        return 0;
    }

    public static float dpToPixels(int i2, Context context2) {
        return ((float) i2) * context2.getResources().getDisplayMetrics().density;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        context = getActivity();
        View inflate = layoutInflater.inflate(R.layout.fragment_achievement, viewGroup, false);
        this.edit = (LinearLayout) inflate.findViewById(R.id.edit);
        this.correct = (ImageView) inflate.findViewById(R.id.correct);
        this.title_per = (AppCompatEditText) inflate.findViewById(R.id.title_per);
        this.view_title = (AppCompatTextView) inflate.findViewById(R.id.view_title);
        this.linear = (LinearLayout) inflate.findViewById(R.id.linear);
        this.achieve_linear = (LinearLayout) inflate.findViewById(R.id.achieve_linear);
        this.achieve_add = (CardView) inflate.findViewById(R.id.achieve_add);
        this.scroll = (ScrollView) inflate.findViewById(R.id.scroll);
        this.achieve_text = (AppCompatEditText) inflate.findViewById(R.id.achieve_text);
        this.achieve_edit = (ImageView) inflate.findViewById(R.id.achieve_edit);
        this.achieve_edit_view = (CardView) inflate.findViewById(R.id.achieve_edit_view);
        this.achieve_update_text = (AppCompatEditText) inflate.findViewById(R.id.achieve_update_text);
        this.achieve_update = (ImageView) inflate.findViewById(R.id.achieve_update);
        this.achieve_title = (AppCompatTextView) inflate.findViewById(R.id.achieve_title);
        this.indust_text = (AppCompatEditText) inflate.findViewById(R.id.indust_text);
        this.indust_edit = (ImageView) inflate.findViewById(R.id.indust_edit);
        this.indust_edit_view = (CardView) inflate.findViewById(R.id.indust_edit_view);
        this.indust_update_text = (AppCompatEditText) inflate.findViewById(R.id.indust_update_text);
        this.indust_update = (ImageView) inflate.findViewById(R.id.indust_update);
        this.indust_title = (AppCompatTextView) inflate.findViewById(R.id.indust_title);
        this.inplant_text = (AppCompatEditText) inflate.findViewById(R.id.inplant_text);
        this.inplant_edit = (ImageView) inflate.findViewById(R.id.inplant_edit);
        this.inplant_edit_view = (CardView) inflate.findViewById(R.id.inplant_edit_view);
        this.inplant_update = (ImageView) inflate.findViewById(R.id.inplant_update);
        this.inplant_update_text = (AppCompatEditText) inflate.findViewById(R.id.inplant_update_text);
        this.inplant_title = (AppCompatTextView) inflate.findViewById(R.id.inplant_title);
        this.cocurricular_text = (AppCompatEditText) inflate.findViewById(R.id.cocurricular_text);
        this.cocurricular_edit = (ImageView) inflate.findViewById(R.id.cocurricular_edit);
        this.cocurricular_edit_view = (CardView) inflate.findViewById(R.id.cocurricular_edit_view);
        this.cocurricular_update = (ImageView) inflate.findViewById(R.id.cocurricular_update);
        this.cocurricular_update_text = (AppCompatEditText) inflate.findViewById(R.id.cocurricular_update_text);
        this.cocurricular_title = (AppCompatTextView) inflate.findViewById(R.id.cocurricular_title);
        this.excurricular_text = (AppCompatEditText) inflate.findViewById(R.id.excurricular_text);
        this.excurricular_edit = (ImageView) inflate.findViewById(R.id.excurricular_edit);
        this.excurricular_edit_view = (CardView) inflate.findViewById(R.id.excurricular_edit_view);
        this.excurricular_update = (ImageView) inflate.findViewById(R.id.excurricular_update);
        this.excurricular_update_text = (AppCompatEditText) inflate.findViewById(R.id.excurricular_update_text);
        this.excurricular_title = (AppCompatTextView) inflate.findViewById(R.id.excurricular_title);
        this.indust_linear = (LinearLayout) inflate.findViewById(R.id.indust_linear);
        this.inplant_linear = (LinearLayout) inflate.findViewById(R.id.inplant_linear);
        this.cocurricular_linear = (LinearLayout) inflate.findViewById(R.id.cocurricular_linear);
        this.excurricular_linear = (LinearLayout) inflate.findViewById(R.id.excurricular_linear);
        this.indust_add = (CardView) inflate.findViewById(R.id.indust_add);
        this.inplant_add = (CardView) inflate.findViewById(R.id.inplant_add);
        this.cocurricular_add = (CardView) inflate.findViewById(R.id.cocurricular_add);
        this.excurricular_add = (CardView) inflate.findViewById(R.id.excurricular_add);
        this.achieve_save = (Button) inflate.findViewById(R.id.achieve_save);
        this.achieve_more_recycle = (ExpandableHeightRecyclerView) inflate.findViewById(R.id.achieve_more_recycle);
        this.indust_more_recycle = (ExpandableHeightRecyclerView) inflate.findViewById(R.id.indust_more_recycle);
        this.inplant_more_recycle = (ExpandableHeightRecyclerView) inflate.findViewById(R.id.inplant_more_recycle);
        this.cocurricular_more_recycle = (ExpandableHeightRecyclerView) inflate.findViewById(R.id.cocurricular_more_recycle);
        this.excurricular_more_recycle = (ExpandableHeightRecyclerView) inflate.findViewById(R.id.excurricular_more_recycle);
        AppCompatEditText appCompatEditText = this.achieve_text;
        appCompatEditText.setHint(this.achieve_title.getText().toString() + " 1");
        AppCompatEditText appCompatEditText2 = this.indust_text;
        appCompatEditText2.setHint(this.indust_title.getText().toString() + " 1");
        AppCompatEditText appCompatEditText3 = this.inplant_text;
        appCompatEditText3.setHint(this.inplant_title.getText().toString() + " 1");
        AppCompatEditText appCompatEditText4 = this.cocurricular_text;
        appCompatEditText4.setHint(this.cocurricular_title.getText().toString() + " 1");
        AppCompatEditText appCompatEditText5 = this.excurricular_text;
        appCompatEditText5.setHint(this.excurricular_title.getText().toString() + " 1");
        this.achieve_more_recycle.setExpanded(true);
        this.indust_more_recycle.setExpanded(true);
        this.inplant_more_recycle.setExpanded(true);
        this.cocurricular_more_recycle.setExpanded(true);
        this.excurricular_more_recycle.setExpanded(true);
        this.DB = context.openOrCreateDatabase("RESUME_BUILDER", 0, null);
        this.achieve_more_data.clear();
        this.achieve_more_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        this.achieve_adapter = new Skill_ListAdapter(context, this, this.achieve_more_data, "achievement_table", "achieve", this.achieve_title);
        this.achieve_more_recycle.setAdapter(this.achieve_adapter);
        this.mItemTouchHelper = new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.achieve_adapter));
        this.mItemTouchHelper.attachToRecyclerView(this.achieve_more_recycle);
        this.achieve_more_recycle.setHasFixedSize(true);
        this.indust_more_data.clear();
        this.indust_more_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        this.indust_adapter = new Skill_ListAdapter(context, this, this.indust_more_data, "industrial_table", "industrial", this.indust_title);
        this.indust_more_recycle.setAdapter(this.indust_adapter);
        this.mItemTouchHelper1 = new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.indust_adapter));
        this.mItemTouchHelper1.attachToRecyclerView(this.indust_more_recycle);
        this.indust_more_recycle.setHasFixedSize(true);
        this.inplant_more_data.clear();
        this.inplant_more_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        this.inplant_adapter = new Skill_ListAdapter(context, this, this.inplant_more_data, "inplant_table", "inplant", this.inplant_title);
        this.inplant_more_recycle.setAdapter(this.inplant_adapter);
        this.mItemTouchHelper2 = new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.inplant_adapter));
        this.mItemTouchHelper2.attachToRecyclerView(this.inplant_more_recycle);
        this.inplant_more_recycle.setHasFixedSize(true);
        this.cocurricular_more_data.clear();
        this.cocurricular_more_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        this.cocurricular_adapter = new Skill_ListAdapter(context, this, this.cocurricular_more_data, "cocurricular_table", "cocurricular", this.cocurricular_title);
        this.cocurricular_more_recycle.setAdapter(this.cocurricular_adapter);
        this.mItemTouchHelper3 = new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.cocurricular_adapter));
        this.mItemTouchHelper3.attachToRecyclerView(this.cocurricular_more_recycle);
        this.cocurricular_more_recycle.setHasFixedSize(true);
        this.excurricular_more_data.clear();
        this.excurricular_more_recycle.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        this.excurricular_adapter = new Skill_ListAdapter(context, this, this.excurricular_more_data, "extracurricular_table", "extracurricular", this.excurricular_title);
        this.excurricular_more_recycle.setAdapter(this.excurricular_adapter);
        this.mItemTouchHelper4 = new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.excurricular_adapter));
        this.mItemTouchHelper4.attachToRecyclerView(this.excurricular_more_recycle);
        this.excurricular_more_recycle.setHasFixedSize(true);
        this.sp.putString(context, "save", "first");
        Update_Load("achievement_table", "achieve", this.achieve_title, this.achieve_text, this.achieve_more_data, this.achieve_adapter);
        Update_Load("industrial_table", "industrial", this.indust_title, this.indust_text, this.indust_more_data, this.indust_adapter);
        Update_Load("inplant_table", "inplant", this.inplant_title, this.inplant_text, this.inplant_more_data, this.inplant_adapter);
        Update_Load("cocurricular_table", "cocurricular", this.cocurricular_title, this.cocurricular_text, this.cocurricular_more_data, this.cocurricular_adapter);
        Update_Load("extracurricular_table", "extracurricular", this.excurricular_title, this.excurricular_text, this.excurricular_more_data, this.excurricular_adapter);
        this.achieve_save.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass1 */

            public void onClick(View view) {
                Achievement_fragment.this.hideSoftKeyboard();
                Achievement_fragment achievement_fragment = Achievement_fragment.this;
                achievement_fragment.final_save("achievement_table", "achieve", achievement_fragment.achieve_title, Achievement_fragment.this.achieve_text, Achievement_fragment.this.achieve_more_data);
                Achievement_fragment achievement_fragment2 = Achievement_fragment.this;
                achievement_fragment2.final_save("industrial_table", "industrial", achievement_fragment2.indust_title, Achievement_fragment.this.indust_text, Achievement_fragment.this.indust_more_data);
                Achievement_fragment achievement_fragment3 = Achievement_fragment.this;
                achievement_fragment3.final_save("inplant_table", "inplant", achievement_fragment3.inplant_title, Achievement_fragment.this.inplant_text, Achievement_fragment.this.inplant_more_data);
                Achievement_fragment achievement_fragment4 = Achievement_fragment.this;
                achievement_fragment4.final_save("cocurricular_table", "cocurricular", achievement_fragment4.cocurricular_title, Achievement_fragment.this.cocurricular_text, Achievement_fragment.this.cocurricular_more_data);
                Achievement_fragment achievement_fragment5 = Achievement_fragment.this;
                achievement_fragment5.final_save("extracurricular_table", "extracurricular", achievement_fragment5.excurricular_title, Achievement_fragment.this.excurricular_text, Achievement_fragment.this.excurricular_more_data);
                Achievement_fragment.this.hideSoftKeyboard();
                if (Achievement_fragment.this.sp.getString(Achievement_fragment.context, "save").equals("first")) {
                    SQLiteDatabase sQLiteDatabase = Achievement_fragment.this.DB;
                    Cursor rawQuery = sQLiteDatabase.rawQuery("Select profile_id from achievement_table where profile_id='" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "'", null);
                    SQLiteDatabase sQLiteDatabase2 = Achievement_fragment.this.DB;
                    Cursor rawQuery2 = sQLiteDatabase2.rawQuery("Select profile_id from industrial_table where profile_id='" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "'", null);
                    SQLiteDatabase sQLiteDatabase3 = Achievement_fragment.this.DB;
                    Cursor rawQuery3 = sQLiteDatabase3.rawQuery("Select profile_id from inplant_table where profile_id='" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "'", null);
                    SQLiteDatabase sQLiteDatabase4 = Achievement_fragment.this.DB;
                    Cursor rawQuery4 = sQLiteDatabase4.rawQuery("Select profile_id from cocurricular_table where profile_id='" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "'", null);
                    SQLiteDatabase sQLiteDatabase5 = Achievement_fragment.this.DB;
                    Cursor rawQuery5 = sQLiteDatabase5.rawQuery("Select profile_id from extracurricular_table where profile_id='" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "'", null);
                    if (rawQuery.getCount() > 0) {
                        Achievement_fragment.this.sp.putString(Achievement_fragment.context, "save", "two");
                        Utils.toast_center(Achievement_fragment.context, "Achievements Saved Successfully");
                        Achievement_fragment.this.achieve_save.setText("UPDATE");
                    } else if (rawQuery.getCount() > 0) {
                        Achievement_fragment.this.sp.putString(Achievement_fragment.context, "save", "two");
                        Utils.toast_center(Achievement_fragment.context, "Achievements Saved Successfully");
                        Achievement_fragment.this.achieve_save.setText("UPDATE");
                    } else if (rawQuery2.getCount() > 0) {
                        Achievement_fragment.this.sp.putString(Achievement_fragment.context, "save", "two");
                        Utils.toast_center(Achievement_fragment.context, "Achievements Saved Successfully");
                        Achievement_fragment.this.achieve_save.setText("UPDATE");
                    } else if (rawQuery3.getCount() > 0) {
                        Achievement_fragment.this.sp.putString(Achievement_fragment.context, "save", "two");
                        Utils.toast_center(Achievement_fragment.context, "Achievements Saved Successfully");
                        Achievement_fragment.this.achieve_save.setText("UPDATE");
                    } else if (rawQuery4.getCount() > 0) {
                        Achievement_fragment.this.sp.putString(Achievement_fragment.context, "save", "two");
                        Utils.toast_center(Achievement_fragment.context, "Achievements Saved Successfully");
                        Achievement_fragment.this.achieve_save.setText("UPDATE");
                    } else if (rawQuery5.getCount() > 0) {
                        Achievement_fragment.this.sp.putString(Achievement_fragment.context, "save", "two");
                        Utils.toast_center(Achievement_fragment.context, "Achievements Saved Successfully");
                        Achievement_fragment.this.achieve_save.setText("UPDATE");
                    } else {
                        Achievement_fragment.this.achieve_save.setText("SAVE");
                    }
                    rawQuery.close();
                    rawQuery2.close();
                    rawQuery3.close();
                    rawQuery4.close();
                    rawQuery5.close();
                    return;
                }
                Achievement_fragment.this.sp.putString(Achievement_fragment.context, "save", "two");
                Utils.toast_center(Achievement_fragment.context, "Achievements Updated Successfully");
                Achievement_fragment.this.achieve_save.setText("UPDATE");
            }
        });
        this.achieve_add.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass2 */

            public void onClick(View view) {
                Achievement_fragment achievement_fragment = Achievement_fragment.this;
                achievement_fragment.final_save("achievement_table", "achieve", achievement_fragment.achieve_title, Achievement_fragment.this.achieve_text, Achievement_fragment.this.achieve_more_data);
                Achievement_fragment.this.hideSoftKeyboard();
                Achievement_fragment achievement_fragment2 = Achievement_fragment.this;
                achievement_fragment2.add_dialog(achievement_fragment2.achieve_more_data, Achievement_fragment.this.achieve_adapter, "achievement_table", "achieve", Achievement_fragment.this.achieve_title, Achievement_fragment.this.achieve_text);
            }
        });
        this.indust_add.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass3 */

            public void onClick(View view) {
                Achievement_fragment achievement_fragment = Achievement_fragment.this;
                achievement_fragment.final_save("industrial_table", "industrial", achievement_fragment.indust_title, Achievement_fragment.this.indust_text, Achievement_fragment.this.indust_more_data);
                Achievement_fragment.this.hideSoftKeyboard();
                Achievement_fragment achievement_fragment2 = Achievement_fragment.this;
                achievement_fragment2.add_dialog(achievement_fragment2.indust_more_data, Achievement_fragment.this.indust_adapter, "industrial_table", "industrial", Achievement_fragment.this.indust_title, Achievement_fragment.this.indust_text);
            }
        });
        this.inplant_add.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass4 */

            public void onClick(View view) {
                Achievement_fragment achievement_fragment = Achievement_fragment.this;
                achievement_fragment.final_save("inplant_table", "inplant", achievement_fragment.inplant_title, Achievement_fragment.this.inplant_text, Achievement_fragment.this.inplant_more_data);
                Achievement_fragment.this.hideSoftKeyboard();
                Achievement_fragment achievement_fragment2 = Achievement_fragment.this;
                achievement_fragment2.add_dialog(achievement_fragment2.inplant_more_data, Achievement_fragment.this.inplant_adapter, "inplant_table", "inplant", Achievement_fragment.this.inplant_title, Achievement_fragment.this.inplant_text);
            }
        });
        this.cocurricular_add.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass5 */

            public void onClick(View view) {
                Achievement_fragment achievement_fragment = Achievement_fragment.this;
                achievement_fragment.final_save("cocurricular_table", "cocurricular", achievement_fragment.cocurricular_title, Achievement_fragment.this.cocurricular_text, Achievement_fragment.this.cocurricular_more_data);
                Achievement_fragment.this.hideSoftKeyboard();
                Achievement_fragment achievement_fragment2 = Achievement_fragment.this;
                achievement_fragment2.add_dialog(achievement_fragment2.cocurricular_more_data, Achievement_fragment.this.cocurricular_adapter, "cocurricular_table", "cocurricular", Achievement_fragment.this.cocurricular_title, Achievement_fragment.this.cocurricular_text);
            }
        });
        this.excurricular_add.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass6 */

            public void onClick(View view) {
                Achievement_fragment achievement_fragment = Achievement_fragment.this;
                achievement_fragment.final_save("extracurricular_table", "extracurricular", achievement_fragment.excurricular_title, Achievement_fragment.this.excurricular_text, Achievement_fragment.this.excurricular_more_data);
                Achievement_fragment.this.hideSoftKeyboard();
                Achievement_fragment achievement_fragment2 = Achievement_fragment.this;
                achievement_fragment2.add_dialog(achievement_fragment2.excurricular_more_data, Achievement_fragment.this.excurricular_adapter, "extracurricular_table", "extracurricular", Achievement_fragment.this.excurricular_title, Achievement_fragment.this.excurricular_text);
            }
        });
        this.achieve_edit_view.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass7 */

            public void onClick(View view) {
            }
        });
        this.indust_edit_view.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass8 */

            public void onClick(View view) {
            }
        });
        this.inplant_edit_view.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass9 */

            public void onClick(View view) {
            }
        });
        this.cocurricular_edit_view.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass10 */

            public void onClick(View view) {
            }
        });
        this.excurricular_edit_view.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass11 */

            public void onClick(View view) {
            }
        });
        this.achieve_edit.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass12 */

            public void onClick(View view) {
                if (Achievement_fragment.this.achieve_edit_view.isShown()) {
                    Achievement_fragment.this.achieve_edit_view.setVisibility(View.GONE);
                    Achievement_fragment.this.hideSoftKeyboard();
                    return;
                }
                Achievement_fragment.this.achieve_update_text.clearFocus();
                Achievement_fragment.this.achieve_update_text.setError(null);
                Achievement_fragment.this.achieve_update_text.setText(Achievement_fragment.this.achieve_title.getText().toString());
                Achievement_fragment.this.achieve_update_text.setSelection(Achievement_fragment.this.achieve_title.getText().toString().length());
                Achievement_fragment achievement_fragment = Achievement_fragment.this;
                achievement_fragment.showSoftKeyboard(achievement_fragment.achieve_update_text);
                Achievement_fragment.this.achieve_edit_view.setVisibility(View.VISIBLE);
                Achievement_fragment.this.cocurricular_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.inplant_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.indust_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.excurricular_edit_view.setVisibility(View.GONE);
            }
        });
        this.indust_edit.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass13 */

            public void onClick(View view) {
                if (Achievement_fragment.this.indust_edit_view.isShown()) {
                    Achievement_fragment.this.indust_edit_view.setVisibility(View.GONE);
                    Achievement_fragment.this.hideSoftKeyboard();
                    return;
                }
                Achievement_fragment.this.indust_update_text.clearFocus();
                Achievement_fragment.this.indust_update_text.setError(null);
                Achievement_fragment.this.indust_update_text.setText(Achievement_fragment.this.indust_title.getText().toString());
                Achievement_fragment.this.indust_update_text.setSelection(Achievement_fragment.this.indust_title.getText().toString().length());
                Achievement_fragment achievement_fragment = Achievement_fragment.this;
                achievement_fragment.showSoftKeyboard(achievement_fragment.indust_update_text);
                Achievement_fragment.this.indust_edit_view.setVisibility(View.VISIBLE);
                Achievement_fragment.this.cocurricular_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.excurricular_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.achieve_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.inplant_edit_view.setVisibility(View.GONE);
            }
        });
        this.inplant_edit.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass14 */

            public void onClick(View view) {
                if (Achievement_fragment.this.inplant_edit_view.isShown()) {
                    Achievement_fragment.this.inplant_edit_view.setVisibility(View.GONE);
                    Achievement_fragment.this.hideSoftKeyboard();
                    return;
                }
                Achievement_fragment.this.inplant_update_text.clearFocus();
                Achievement_fragment.this.inplant_update_text.setError(null);
                Achievement_fragment.this.inplant_update_text.setText(Achievement_fragment.this.inplant_title.getText().toString());
                Achievement_fragment.this.inplant_update_text.setSelection(Achievement_fragment.this.inplant_title.getText().toString().length());
                Achievement_fragment achievement_fragment = Achievement_fragment.this;
                achievement_fragment.showSoftKeyboard(achievement_fragment.inplant_update_text);
                Achievement_fragment.this.inplant_edit_view.setVisibility(View.VISIBLE);
                Achievement_fragment.this.cocurricular_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.excurricular_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.achieve_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.indust_edit_view.setVisibility(View.GONE);
            }
        });
        this.cocurricular_edit.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass15 */

            public void onClick(View view) {
                if (Achievement_fragment.this.cocurricular_edit_view.isShown()) {
                    Achievement_fragment.this.cocurricular_edit_view.setVisibility(View.GONE);
                    Achievement_fragment.this.hideSoftKeyboard();
                    return;
                }
                Achievement_fragment.this.cocurricular_update_text.clearFocus();
                Achievement_fragment.this.cocurricular_update_text.setError(null);
                Achievement_fragment.this.cocurricular_update_text.setText(Achievement_fragment.this.cocurricular_title.getText().toString());
                Achievement_fragment.this.cocurricular_update_text.setSelection(Achievement_fragment.this.cocurricular_title.getText().toString().length());
                Achievement_fragment achievement_fragment = Achievement_fragment.this;
                achievement_fragment.showSoftKeyboard(achievement_fragment.cocurricular_update_text);
                Achievement_fragment.this.cocurricular_edit_view.setVisibility(View.VISIBLE);
                Achievement_fragment.this.achieve_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.inplant_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.indust_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.excurricular_edit_view.setVisibility(View.GONE);
            }
        });
        this.excurricular_edit.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass16 */

            public void onClick(View view) {
                if (Achievement_fragment.this.excurricular_edit_view.isShown()) {
                    Achievement_fragment.this.excurricular_edit_view.setVisibility(View.GONE);
                    Achievement_fragment.this.hideSoftKeyboard();
                    return;
                }
                Achievement_fragment.this.excurricular_update_text.clearFocus();
                Achievement_fragment.this.excurricular_update_text.setError(null);
                Achievement_fragment.this.excurricular_update_text.setText(Achievement_fragment.this.excurricular_title.getText().toString());
                Achievement_fragment.this.excurricular_update_text.setSelection(Achievement_fragment.this.excurricular_title.getText().toString().length());
                Achievement_fragment achievement_fragment = Achievement_fragment.this;
                achievement_fragment.showSoftKeyboard(achievement_fragment.excurricular_update_text);
                Achievement_fragment.this.excurricular_edit_view.setVisibility(View.VISIBLE);
                Achievement_fragment.this.cocurricular_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.achieve_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.inplant_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.indust_edit_view.setVisibility(View.GONE);
            }
        });
        this.excurricular_update_text.setOnKeyListener(new View.OnKeyListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass17 */

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 6) {
                    return false;
                }
                Achievement_fragment.this.hideSoftKeyboard();
                return false;
            }
        });
        this.excurricular_update.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass18 */

            public void onClick(View view) {
                if (Achievement_fragment.this.excurricular_update_text.getText().toString().isEmpty()) {
                    Achievement_fragment.this.excurricular_update_text.setError("Enter your title..");
                    Achievement_fragment.this.excurricular_update_text.requestFocus();
                    return;
                }
                AppCompatEditText appCompatEditText = Achievement_fragment.this.excurricular_text;
                appCompatEditText.setHint(Achievement_fragment.this.excurricular_update_text.getText().toString() + " " + 1);
                Achievement_fragment.this.excurricular_title.setText(Achievement_fragment.this.excurricular_update_text.getText().toString());
                SQLiteDatabase sQLiteDatabase = Achievement_fragment.this.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("Select extracurricular_title from title_table where profile_id='" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "'", null);
                if (rawQuery.getCount() == 0) {
                    SQLiteDatabase sQLiteDatabase2 = Achievement_fragment.this.DB;
                    sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, extracurricular_title) VALUES ('" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "', '" + Achievement_fragment.this.excurricular_title.getText().toString().replaceAll("'", "''") + "');");
                } else {
                    SQLiteDatabase sQLiteDatabase3 = Achievement_fragment.this.DB;
                    sQLiteDatabase3.execSQL("UPDATE title_table SET extracurricular_title = '" + Achievement_fragment.this.excurricular_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "';");
                }
                rawQuery.close();
                Achievement_fragment.this.excurricular_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.hideSoftKeyboard();
            }
        });
        this.cocurricular_update_text.setOnKeyListener(new View.OnKeyListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass19 */

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 6) {
                    return false;
                }
                Achievement_fragment.this.hideSoftKeyboard();
                return false;
            }
        });
        this.cocurricular_update.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass20 */

            public void onClick(View view) {
                if (Achievement_fragment.this.cocurricular_update_text.getText().toString().isEmpty()) {
                    Achievement_fragment.this.cocurricular_update_text.setError("Enter your title..");
                    Achievement_fragment.this.cocurricular_update_text.requestFocus();
                    return;
                }
                AppCompatEditText appCompatEditText = Achievement_fragment.this.cocurricular_text;
                appCompatEditText.setHint(Achievement_fragment.this.cocurricular_update_text.getText().toString() + " " + 1);
                Achievement_fragment.this.cocurricular_title.setText(Achievement_fragment.this.cocurricular_update_text.getText().toString());
                SQLiteDatabase sQLiteDatabase = Achievement_fragment.this.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("Select cocurricular_title from title_table where profile_id='" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "'", null);
                if (rawQuery.getCount() == 0) {
                    SQLiteDatabase sQLiteDatabase2 = Achievement_fragment.this.DB;
                    sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, cocurricular_title) VALUES ('" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "', '" + Achievement_fragment.this.cocurricular_title.getText().toString().replaceAll("'", "''") + "');");
                } else {
                    SQLiteDatabase sQLiteDatabase3 = Achievement_fragment.this.DB;
                    sQLiteDatabase3.execSQL("UPDATE title_table SET cocurricular_title = '" + Achievement_fragment.this.cocurricular_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "';");
                }
                rawQuery.close();
                Achievement_fragment.this.cocurricular_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.hideSoftKeyboard();
            }
        });
        this.inplant_update_text.setOnKeyListener(new View.OnKeyListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass21 */

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 6) {
                    return false;
                }
                Achievement_fragment.this.hideSoftKeyboard();
                return false;
            }
        });
        this.inplant_update.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass22 */

            public void onClick(View view) {
                if (Achievement_fragment.this.inplant_update_text.getText().toString().isEmpty()) {
                    Achievement_fragment.this.inplant_update_text.setError("Enter your title..");
                    Achievement_fragment.this.inplant_update_text.requestFocus();
                    return;
                }
                AppCompatEditText appCompatEditText = Achievement_fragment.this.inplant_text;
                appCompatEditText.setHint(Achievement_fragment.this.inplant_update_text.getText().toString() + " " + 1);
                Achievement_fragment.this.inplant_title.setText(Achievement_fragment.this.inplant_update_text.getText().toString());
                SQLiteDatabase sQLiteDatabase = Achievement_fragment.this.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("Select inplant_title from title_table where profile_id='" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "'", null);
                if (rawQuery.getCount() == 0) {
                    SQLiteDatabase sQLiteDatabase2 = Achievement_fragment.this.DB;
                    sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, inplant_title) VALUES ('" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "', '" + Achievement_fragment.this.inplant_title.getText().toString().replaceAll("'", "''") + "');");
                } else {
                    SQLiteDatabase sQLiteDatabase3 = Achievement_fragment.this.DB;
                    sQLiteDatabase3.execSQL("UPDATE title_table SET inplant_title = '" + Achievement_fragment.this.inplant_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "';");
                }
                rawQuery.close();
                Achievement_fragment.this.inplant_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.hideSoftKeyboard();
            }
        });
        this.indust_update_text.setOnKeyListener(new View.OnKeyListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass23 */

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 6) {
                    return false;
                }
                Achievement_fragment.this.hideSoftKeyboard();
                return false;
            }
        });
        this.indust_update.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass24 */

            public void onClick(View view) {
                if (Achievement_fragment.this.indust_update_text.getText().toString().isEmpty()) {
                    Achievement_fragment.this.indust_update_text.setError("Enter your title..");
                    Achievement_fragment.this.indust_update_text.requestFocus();
                    return;
                }
                AppCompatEditText appCompatEditText = Achievement_fragment.this.indust_text;
                appCompatEditText.setHint(Achievement_fragment.this.indust_update_text.getText().toString() + " " + 1);
                Achievement_fragment.this.indust_title.setText(Achievement_fragment.this.indust_update_text.getText().toString());
                SQLiteDatabase sQLiteDatabase = Achievement_fragment.this.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("Select industrial_title from title_table where profile_id='" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "'", null);
                if (rawQuery.getCount() == 0) {
                    SQLiteDatabase sQLiteDatabase2 = Achievement_fragment.this.DB;
                    sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, industrial_title) VALUES ('" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "', '" + Achievement_fragment.this.indust_title.getText().toString().replaceAll("'", "''") + "');");
                } else {
                    SQLiteDatabase sQLiteDatabase3 = Achievement_fragment.this.DB;
                    sQLiteDatabase3.execSQL("UPDATE title_table SET industrial_title = '" + Achievement_fragment.this.indust_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "';");
                }
                rawQuery.close();
                Achievement_fragment.this.indust_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.hideSoftKeyboard();
            }
        });
        this.achieve_update_text.setOnKeyListener(new View.OnKeyListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass25 */

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent == null || keyEvent.getKeyCode() != 66) && i != 6) {
                    return false;
                }
                Achievement_fragment.this.hideSoftKeyboard();
                return false;
            }
        });
        this.achieve_update.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass26 */

            public void onClick(View view) {
                if (Achievement_fragment.this.achieve_update_text.getText().toString().isEmpty()) {
                    Achievement_fragment.this.achieve_update_text.setError("Enter your title..");
                    Achievement_fragment.this.achieve_update_text.requestFocus();
                    return;
                }
                AppCompatEditText appCompatEditText = Achievement_fragment.this.achieve_text;
                appCompatEditText.setHint(Achievement_fragment.this.achieve_update_text.getText().toString() + " " + 1);
                Achievement_fragment.this.achieve_title.setText(Achievement_fragment.this.achieve_update_text.getText().toString());
                SQLiteDatabase sQLiteDatabase = Achievement_fragment.this.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("Select achieve_title from title_table where profile_id='" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "'", null);
                if (rawQuery.getCount() == 0) {
                    SQLiteDatabase sQLiteDatabase2 = Achievement_fragment.this.DB;
                    sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, achieve_title) VALUES ('" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "', '" + Achievement_fragment.this.achieve_title.getText().toString().replaceAll("'", "''") + "');");
                } else {
                    SQLiteDatabase sQLiteDatabase3 = Achievement_fragment.this.DB;
                    sQLiteDatabase3.execSQL("UPDATE title_table SET achieve_title = '" + Achievement_fragment.this.achieve_title.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "';");
                }
                rawQuery.close();
                Achievement_fragment.this.achieve_edit_view.setVisibility(View.GONE);
                Achievement_fragment.this.hideSoftKeyboard();
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
        if (view.requestFocus()) {
            ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 1);
        }
    }

    public void final_save(List<String> list, String str, String str2, AppCompatTextView appCompatTextView, AppCompatEditText appCompatEditText, List<AppCompatEditText> list2, List<Integer> list3) {
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select profile_id from " + str + " where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
        int i2 = 0;
        if (rawQuery.getCount() == 0) {
            SQLiteDatabase sQLiteDatabase2 = this.DB;
            sQLiteDatabase2.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(context, "profile_id") + "', '" + appCompatEditText.getText().toString().replaceAll("'", "''") + "');");
            while (i2 < list2.size()) {
                if (!list2.get(i2).getText().toString().equals("") && !list2.get(i2).getText().toString().equals(null)) {
                    SQLiteDatabase sQLiteDatabase3 = this.DB;
                    sQLiteDatabase3.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(context, "profile_id") + "', '" + list2.get(i2).getText().toString().replaceAll("'", "''") + "');");
                }
                i2++;
            }
            Utils.toast_center(context, "Saved Successfully");
            this.achieve_save.setText("UPDATE");
        } else {
            if (list3.size() > 0) {
                for (int i3 = 0; i3 < list3.size(); i3++) {
                    list.remove(list3.get(i3));
                    list2.get(list3.get(i3).intValue()).setText("");
                    if (i3 == list3.size() - 1) {
                        SQLiteDatabase sQLiteDatabase4 = this.DB;
                        sQLiteDatabase4.execSQL("delete from " + str + " where profile_id='" + this.sp.getString(context, "profile_id") + "';");
                        SQLiteDatabase sQLiteDatabase5 = this.DB;
                        sQLiteDatabase5.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(context, "profile_id") + "', '" + appCompatEditText.getText().toString().replaceAll("'", "''") + "');");
                        for (int i4 = 0; i4 < list2.size(); i4++) {
                            if (!list2.get(i4).getText().toString().equals("") && !list2.get(i4).getText().toString().equals(null)) {
                                SQLiteDatabase sQLiteDatabase6 = this.DB;
                                sQLiteDatabase6.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(context, "profile_id") + "', '" + list2.get(i4).getText().toString().replaceAll("'", "''") + "');");
                            }
                        }
                    }
                }
            } else {
                SQLiteDatabase sQLiteDatabase7 = this.DB;
                sQLiteDatabase7.execSQL("delete from " + str + " where profile_id='" + this.sp.getString(context, "profile_id") + "';");
                SQLiteDatabase sQLiteDatabase8 = this.DB;
                sQLiteDatabase8.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(context, "profile_id") + "', '" + appCompatEditText.getText().toString().replaceAll("'", "''") + "');");
                while (i2 < list2.size()) {
                    if (!list2.get(i2).getText().toString().equals("") && !list2.get(i2).getText().toString().equals(null)) {
                        SQLiteDatabase sQLiteDatabase9 = this.DB;
                        sQLiteDatabase9.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(context, "profile_id") + "', '" + list2.get(i2).getText().toString().replaceAll("'", "''") + "');");
                    }
                    i2++;
                }
            }
            Utils.toast_center(context, "Updated Successfully");
            this.achieve_save.setText("UPDATE");
        }
        rawQuery.close();
    }

    public void final_save(String str, String str2, AppCompatTextView appCompatTextView, AppCompatEditText appCompatEditText, List<DB_Items> list) {
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select " + str2 + "_title from title_table where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
        if (rawQuery.getCount() == 0) {
            SQLiteDatabase sQLiteDatabase2 = this.DB;
            sQLiteDatabase2.execSQL("INSERT INTO title_table (profile_id, " + str2 + "_title) VALUES ('" + this.sp.getString(context, "profile_id") + "', '" + appCompatTextView.getText().toString().replaceAll("'", "''") + "');");
        } else {
            SQLiteDatabase sQLiteDatabase3 = this.DB;
            sQLiteDatabase3.execSQL("UPDATE title_table SET " + str2 + "_title = '" + appCompatTextView.getText().toString().replaceAll("'", "''") + "' WHERE profile_id = '" + this.sp.getString(context, "profile_id") + "';");
        }
        rawQuery.close();
        SQLiteDatabase sQLiteDatabase4 = this.DB;
        Cursor rawQuery2 = sQLiteDatabase4.rawQuery("Select profile_id from " + str + " where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
        int i2 = 0;
        if (rawQuery2.getCount() == 0) {
            while (i2 < list.size()) {
                if (!list.get(i2).getValue().equals("") && !list.get(i2).getValue().equals(null)) {
                    SQLiteDatabase sQLiteDatabase5 = this.DB;
                    sQLiteDatabase5.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(context, "profile_id") + "', '" + list.get(i2).getValue().replaceAll("'", "''") + "');");
                }
                i2++;
            }
        } else {
            SQLiteDatabase sQLiteDatabase6 = this.DB;
            sQLiteDatabase6.execSQL("delete from " + str + " where profile_id='" + this.sp.getString(context, "profile_id") + "';");
            while (i2 < list.size()) {
                if (!list.get(i2).getValue().equals("") && !list.get(i2).getValue().equals(null)) {
                    SQLiteDatabase sQLiteDatabase7 = this.DB;
                    sQLiteDatabase7.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(context, "profile_id") + "', '" + list.get(i2).getValue().replaceAll("'", "''") + "');");
                }
                i2++;
            }
        }
        rawQuery2.close();
    }

    public void view_add(final int i2, final List<Integer> list, LinearLayout linearLayout, AppCompatTextView appCompatTextView, final List<AppCompatEditText> list2) {
        final View inflate = LayoutInflater.from(context).inflate(R.layout.item_add_more, (ViewGroup) null, false);
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass27 */

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
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from " + str + " where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
        rawQuery.moveToPosition(0);
        if (rawQuery.getCount() > 0) {
            appCompatEditText.setText("" + rawQuery.getString(rawQuery.getColumnIndex(str2)));
            if (rawQuery.getCount() > 1) {
                list2.clear();
                SQLiteDatabase sQLiteDatabase2 = this.DB;
                Cursor rawQuery2 = sQLiteDatabase2.rawQuery("Select * from " + str + " where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
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
                } else if (i2 == 5) {
                    this.n = list2.size();
                }
                for (int i3 = 0; i3 < list2.size(); i3++) {
                    final View inflate = LayoutInflater.from(context).inflate(R.layout.item_add_more, (ViewGroup) null, false);
                    final AppCompatEditText appCompatEditText2 = (AppCompatEditText) inflate.findViewById(R.id.extra);
                    appCompatEditText2.setId(i3);
                    ((LinearLayout) inflate.findViewById(R.id.first)).setVisibility(View.GONE);
                    appCompatEditText2.setText(list2.get(i3));
                    ((AppCompatTextView) inflate.findViewById(R.id.title)).setText("Title " + i3);
                    linearLayout.addView(inflate);
                    list3.add(appCompatEditText2);
                    linearLayout.setLayoutTransition(new LayoutTransition());
                    ((ImageView) inflate.findViewById(R.id.del_view)).setOnClickListener(new View.OnClickListener() {
                        /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass28 */

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

    public void Update_Load(String str, String str2, AppCompatTextView appCompatTextView, AppCompatEditText appCompatEditText, List<DB_Items> list, Skill_ListAdapter skill_ListAdapter) {
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select " + str2 + "_title from title_table where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
        rawQuery.moveToFirst();
        if (rawQuery.getCount() > 0) {
            String string = rawQuery.getString(rawQuery.getColumnIndex("" + str2 + "_title"));
            if (string != null) {
                appCompatTextView.setText("" + string);
            }
        }
        rawQuery.close();
        SQLiteDatabase sQLiteDatabase2 = this.DB;
        Cursor rawQuery2 = sQLiteDatabase2.rawQuery("Select * from " + str + " where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
        rawQuery2.moveToPosition(0);
        if (rawQuery2.getCount() > 0) {
            appCompatEditText.setText("" + rawQuery2.getString(rawQuery2.getColumnIndex(str2)));
            if (rawQuery2.getCount() > 0) {
                list.clear();
                SQLiteDatabase sQLiteDatabase3 = this.DB;
                Cursor rawQuery3 = sQLiteDatabase3.rawQuery("Select * from " + str + " where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
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
            }
            this.achieve_save.setText("UPDATE");
            this.sp.putString(context, "save", "two");
        }
        rawQuery2.close();
    }

    @Override // nithra.resume.maker.cv.builder.app.Visible
    public void visible() {
        if (this.achieve_edit_view.isShown()) {
            this.achieve_edit_view.setVisibility(View.GONE);
        } else if (this.indust_edit_view.isShown()) {
            this.indust_edit_view.setVisibility(View.GONE);
        } else if (this.inplant_edit_view.isShown()) {
            this.inplant_edit_view.setVisibility(View.GONE);
        } else if (this.cocurricular_edit_view.isShown()) {
            this.cocurricular_edit_view.setVisibility(View.GONE);
        } else if (this.excurricular_edit_view.isShown()) {
            this.excurricular_edit_view.setVisibility(View.GONE);
        } else {
            exti_result_dialog();
        }
    }

    public void exti_result_dialog() {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_exit);
        MainActivity.load_addFromMain_natt(context, (FrameLayout) dialog.findViewById(R.id.ads_lay));
        ((TextView) dialog.findViewById(R.id.yes)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass29 */

            public void onClick(View view) {
                Achievement_fragment.context.finish();
                dialog.dismiss();
            }
        });
        ((TextView) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass30 */

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
        this.mItemTouchHelper4.startDrag(viewHolder);
    }

    public void add_dialog(final List<DB_Items> list, final Skill_ListAdapter skill_ListAdapter, final String str, final String str2, final AppCompatTextView appCompatTextView, final AppCompatEditText appCompatEditText) {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_add_view);
        this.title = (AppCompatTextView) dialog.findViewById(R.id.title);
        final AppCompatEditText appCompatEditText2 = (AppCompatEditText) dialog.findViewById(R.id.value);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.ok);
        ImageView imageView2 = (ImageView) dialog.findViewById(R.id.cancel);
        this.title.setVisibility(View.GONE);
        this.title.setText("");
        appCompatEditText2.setText("");
        showSoftKeyboard(appCompatEditText2);
        if (str2.equals("industrial")) {
            appCompatEditText2.setHint("Enter Company Details and Duration...");
        } else if (str2.equals("inplant")) {
            appCompatEditText2.setHint("Enter Company Details and Duration...");
        } else {
            appCompatEditText2.setHint("Enter your " + appCompatTextView.getText().toString() + "...");
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass31 */

            public void onClick(View view) {
                if (appCompatEditText2.getText().toString().trim().equals("")) {
                    if (str2.equals("industrial")) {
                        appCompatEditText2.setError("Enter Company Details and Duration !!!");
                    } else if (str2.equals("inplant")) {
                        appCompatEditText2.setError("Enter Company Details and Duration !!!");
                    } else {
                        AppCompatEditText appCompatEditText = appCompatEditText2;
                        appCompatEditText.setError("Enter your " + appCompatTextView.getText().toString() + " !!!");
                    }
                    appCompatEditText2.requestFocus();
                } else {
                    SQLiteDatabase sQLiteDatabase = Achievement_fragment.this.DB;
                    Cursor rawQuery = sQLiteDatabase.rawQuery("Select upper(" + str2 + ") as " + str2 + " from " + str + " where profile_id='" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "' and  upper(" + str2 + ")='" + appCompatEditText2.getText().toString().toUpperCase().replaceAll("'", "''") + "'", null);
                    if (rawQuery.getCount() == 0) {
                        DB_Items dB_Items = new DB_Items();
                        dB_Items.setValue(appCompatEditText2.getText().toString());
                        list.add(dB_Items);
                        skill_ListAdapter.notifyDataSetChanged();
                        Achievement_fragment.this.final_save(str, str2, appCompatTextView, appCompatEditText, list);
                        Achievement_fragment.this.hideSoftKeyboard();
                        dialog.dismiss();
                    } else {
                        appCompatEditText2.setError("Already exist !!!");
                        appCompatEditText2.requestFocus();
                    }
                    rawQuery.close();
                }
                if (!Achievement_fragment.this.sp.getString(Achievement_fragment.context, "drag").equals("draged")) {
                    SQLiteDatabase sQLiteDatabase2 = Achievement_fragment.this.DB;
                    Cursor rawQuery2 = sQLiteDatabase2.rawQuery("Select profile_id from " + str + " where profile_id='" + Achievement_fragment.this.sp.getString(Achievement_fragment.context, "profile_id") + "'", null);
                    if (rawQuery2.getCount() > 1) {
                        Achievement_fragment.this.drag_dialog();
                    }
                    rawQuery2.close();
                }
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass32 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass33 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass33.AnonymousClass1 */

                    public void run() {
                        cardView.startAnimation(loadAnimation);
                        imageView.startAnimation(loadAnimation);
                    }
                }, 1500);
            }
        });
        loadAnimation.setAnimationListener(new Animation.AnimationListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass34 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass34.AnonymousClass1 */

                    public void run() {
                        cardView.startAnimation(loadAnimation2);
                        imageView.startAnimation(loadAnimation2);
                    }
                }, 1500);
            }
        });
        ((Button) dialog.findViewById(R.id.enter)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment.AnonymousClass35 */

            public void onClick(View view) {
                Achievement_fragment.this.sp.putString(Achievement_fragment.context, "drag", "draged");
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
