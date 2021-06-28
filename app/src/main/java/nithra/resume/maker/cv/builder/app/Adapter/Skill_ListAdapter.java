package nithra.resume.maker.cv.builder.app.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;




import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nithra.resume.maker.cv.builder.app.Models.DB_Items;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.SharedPreference;
import nithra.resume.maker.cv.builder.app.Touch.ItemTouchHelperAdapter;
import nithra.resume.maker.cv.builder.app.Touch.ItemTouchHelperViewHolder;
import nithra.resume.maker.cv.builder.app.Touch.OnStartDragListener;

public class Skill_ListAdapter extends RecyclerView.Adapter<Skill_ListAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {
    SQLiteDatabase DB;
    String col_name;
    private final OnStartDragListener mDragStartListener;
    private List<DB_Items> mItems = new ArrayList();
    Context mcontext;
    AppCompatTextView mmain_title;
    SharedPreference sp = new SharedPreference();
    String table_name;
    int to_position;

    public Skill_ListAdapter(Context context, OnStartDragListener onStartDragListener, List<DB_Items> list, String str, String str2, AppCompatTextView appCompatTextView) {
        this.mDragStartListener = onStartDragListener;
        this.mItems = list;
        this.mcontext = context;
        this.table_name = str;
        this.col_name = str2;
        this.mmain_title = appCompatTextView;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_more_data_view, viewGroup, false);
        this.DB = this.mcontext.openOrCreateDatabase("RESUME_BUILDER", 0, null);
        return new ItemViewHolder(inflate);
    }

    public void onBindViewHolder(final ItemViewHolder itemViewHolder, int i) {
        itemViewHolder.textView.setText(this.mItems.get(i).getValue());
        AppCompatTextView appCompatTextView = itemViewHolder.textView;
        appCompatTextView.setHint("Enter Skill " + (i + 1));
        View view = itemViewHolder.itemView;
        view.setTag("" + this.mItems.get(i).getValue());
        ImageView imageView = itemViewHolder.remove;
        imageView.setTag("" + this.mItems.get(i).getValue());
        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                for (int i = 0; i < Skill_ListAdapter.this.mItems.size(); i++) {
                    if (((DB_Items) Skill_ListAdapter.this.mItems.get(i)).getValue().equals(view.getTag().toString())) {
                        Skill_ListAdapter skill_ListAdapter = Skill_ListAdapter.this;
                        skill_ListAdapter.add_dialog(((DB_Items) skill_ListAdapter.mItems.get(i)).getValue(), i);
                        return;
                    }
                }
            }
        });
        itemViewHolder.handleView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEventCompat.getActionMasked(motionEvent) != 0) {
                    return false;
                }
                Skill_ListAdapter.this.mDragStartListener.onStartDrag(itemViewHolder);
                return false;
            }
        });
        itemViewHolder.remove.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Skill_ListAdapter.this.mcontext);
                builder.setMessage("Do you want delete this " + Skill_ListAdapter.this.mmain_title.getText().toString() + "?").setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int i2 = 0; i2 < Skill_ListAdapter.this.mItems.size(); i2++) {
                            if (((DB_Items) Skill_ListAdapter.this.mItems.get(i2)).getValue().equals(view.getTag().toString())) {
                                SQLiteDatabase sQLiteDatabase = Skill_ListAdapter.this.DB;
                                sQLiteDatabase.execSQL("delete from " + Skill_ListAdapter.this.table_name + " where profile_id='" + Skill_ListAdapter.this.sp.getString(Skill_ListAdapter.this.mcontext, "profile_id") + "' and " + Skill_ListAdapter.this.col_name + "='" + ((DB_Items) Skill_ListAdapter.this.mItems.get(i2)).getValue() + "';");
                                Skill_ListAdapter.this.mItems.remove(i2);
                                Skill_ListAdapter.this.notifyDataSetChanged();
                                return;
                            }
                        }
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public void onItemDismiss(int i) {
        this.mItems.remove(i);
        notifyItemRemoved(i);
    }

    @Override
    public boolean onItemMove(int i, int i2) {
        Collections.swap(this.mItems, i, i2);
        notifyItemMoved(i, i2);
        this.to_position = i2;
        return true;
    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    public void add_dialog(final String str, final int i) {
        final Dialog dialog = new Dialog(this.mcontext, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_add_view);
        final AppCompatEditText appCompatEditText = (AppCompatEditText) dialog.findViewById(R.id.value);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.ok);
        ImageView imageView2 = (ImageView) dialog.findViewById(R.id.cancel);
        ((AppCompatTextView) dialog.findViewById(R.id.title)).setVisibility(View.GONE);
        appCompatEditText.setText(this.mItems.get(i).getValue());
        appCompatEditText.setHint("Enter your " + this.mmain_title.getText().toString() + "...");
        appCompatEditText.setSelection(this.mItems.get(i).getValue().length());
        imageView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (appCompatEditText.getText().toString().trim().equals("")) {

                    appCompatEditText.setError("Enter your " + Skill_ListAdapter.this.mmain_title.getText().toString() + " !!!");
                    appCompatEditText.requestFocus();
                } else if (appCompatEditText.getText().toString().equals(str)) {
                    Skill_ListAdapter.this.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    SQLiteDatabase sQLiteDatabase = Skill_ListAdapter.this.DB;
                    if (sQLiteDatabase.rawQuery("Select upper(" + Skill_ListAdapter.this.col_name + ") as " + Skill_ListAdapter.this.col_name + " from " + Skill_ListAdapter.this.table_name + " where profile_id='" + Skill_ListAdapter.this.sp.getString(Skill_ListAdapter.this.mcontext, "profile_id") + "' and  upper(" + Skill_ListAdapter.this.col_name + ")='" + appCompatEditText.getText().toString().toUpperCase().replaceAll("'", "''") + "'", null).getCount() == 0) {
                        ((DB_Items) Skill_ListAdapter.this.mItems.get(i)).setValue(appCompatEditText.getText().toString());
                        Skill_ListAdapter.this.notifyDataSetChanged();
                        Skill_ListAdapter skill_ListAdapter = Skill_ListAdapter.this;
                        skill_ListAdapter.final_save(skill_ListAdapter.table_name, Skill_ListAdapter.this.col_name, Skill_ListAdapter.this.mItems);
                        dialog.dismiss();
                        return;
                    }
                    appCompatEditText.setError("Already exist !!!");
                    appCompatEditText.requestFocus();
                }
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public void final_save(String str, String str2, List<DB_Items> list) {
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select profile_id from " + str + " where profile_id='" + this.sp.getString(this.mcontext, "profile_id") + "'", null);
        int i = 0;
        if (rawQuery.getCount() == 0) {
            while (i < list.size()) {
                if (!list.get(i).getValue().trim().equals("") && !list.get(i).getValue().equals(null)) {
                    SQLiteDatabase sQLiteDatabase2 = this.DB;
                    sQLiteDatabase2.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(this.mcontext, "profile_id") + "','" + list.get(i).getValue().replaceAll("'", "''") + "');");
                }
                i++;
            }
        } else {
            Log.e("valla", str);
            SQLiteDatabase sQLiteDatabase3 = this.DB;
            sQLiteDatabase3.execSQL("delete from " + str + " where profile_id='" + this.sp.getString(this.mcontext, "profile_id") + "';");
            while (i < list.size()) {
                if (!list.get(i).getValue().trim().equals("") && !list.get(i).getValue().equals(null)) {
                    SQLiteDatabase sQLiteDatabase4 = this.DB;
                    sQLiteDatabase4.execSQL("INSERT INTO " + str + " (profile_id, " + str2 + ") VALUES ('" + this.sp.getString(this.mcontext, "profile_id") + "','" + list.get(i).getValue().replaceAll("'", "''") + "');");
                }
                i++;
            }
        }
        rawQuery.close();
    }

    public void showSoftKeyboard(View view) {
        view.requestFocus();
        ((InputMethodManager) this.mcontext.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
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
            this.extra_title.setVisibility(View.GONE);
        }

        @Override
        public void onItemSelected() {
            this.itemView.setBackgroundColor(0);
        }

        @Override
        public void onItemClear() {
            this.itemView.setBackgroundColor(0);
        }
    }
}
