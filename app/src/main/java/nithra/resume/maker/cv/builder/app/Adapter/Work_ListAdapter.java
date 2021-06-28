package nithra.resume.maker.cv.builder.app.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nithra.resume.maker.cv.builder.app.Activity.Resume_Activity;
import nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment;
import nithra.resume.maker.cv.builder.app.Models.DB_Items;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.SharedPreference;
import nithra.resume.maker.cv.builder.app.Touch.ItemTouchHelperAdapter;
import nithra.resume.maker.cv.builder.app.Touch.ItemTouchHelperViewHolder;
import nithra.resume.maker.cv.builder.app.Touch.OnStartDragListener;

public class Work_ListAdapter extends RecyclerView.Adapter<Work_ListAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {
    SQLiteDatabase DB;
    private List<DB_Items> extra_vals = new ArrayList();
    private final OnStartDragListener mDragStartListener;
    private List<DB_Items> mItems = new ArrayList();
    Context mcontext;
    SharedPreference sp = new SharedPreference();

    public Work_ListAdapter(Context context, OnStartDragListener onStartDragListener, List<DB_Items> list) {
        this.mDragStartListener = onStartDragListener;
        this.mItems = list;
        this.mcontext = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_profile_view, viewGroup, false);
        this.DB = this.mcontext.openOrCreateDatabase("RESUME_BUILDER", 0, null);
        return new ItemViewHolder(inflate);
    }

    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int i) {
        itemViewHolder.textView.setText(this.mItems.get(i).getOrganization());
        itemViewHolder.itemView.setTag(this.mItems.get(i).getExtra_id());
        itemViewHolder.remove.setTag(this.mItems.get(i).getExtra_id());
        itemViewHolder.handleView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEventCompat.getActionMasked(motionEvent) != 0) {
                    return true;
                }
                Work_ListAdapter.this.mDragStartListener.onStartDrag(itemViewHolder);
                return true;
            }
        });
        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Resume_Activity.back_flag = 0;
                int parseInt = Integer.parseInt(view.getTag().toString());
                Log.e("kozhii4", String.valueOf(parseInt));
                Work_Exp_fragment.load_academic(parseInt);
            }
        });
        itemViewHolder.remove.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Work_ListAdapter.this.mcontext);
                builder.setMessage("Are you sure want to delete this work detail?").setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {
                        int parseInt = Integer.parseInt(view.getTag().toString());
                        SQLiteDatabase sQLiteDatabase = Work_ListAdapter.this.DB;
                        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from work_table where extra_id='" + parseInt + "' and profile_id='" + Work_ListAdapter.this.sp.getString(Work_ListAdapter.this.mcontext, "profile_id") + "'", null);
                        rawQuery.moveToFirst();
                        int i2 = rawQuery.getInt(rawQuery.getColumnIndex("work_id"));
                        SQLiteDatabase sQLiteDatabase2 = Work_ListAdapter.this.DB;
                        sQLiteDatabase2.execSQL("delete from work_table where profile_id='" + Work_ListAdapter.this.sp.getString(Work_ListAdapter.this.mcontext, "profile_id") + "' and extra_id='" + parseInt + "';");
                        SQLiteDatabase sQLiteDatabase3 = Work_ListAdapter.this.DB;
                        sQLiteDatabase3.execSQL("delete from extra_table where profile_id='" + Work_ListAdapter.this.sp.getString(Work_ListAdapter.this.mcontext, "profile_id") + "' and table_name='work_table' and table_id='" + i2 + "';");
                        rawQuery.close();
                        Work_ListAdapter.this.mItems.remove(i);
                        Work_Exp_fragment.check();
                        Work_ListAdapter.this.notifyDataSetChanged();
                        Toast.makeText(Work_ListAdapter.this.mcontext, "Work detail deleted successfully", 0).show();
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
        this.extra_vals.clear();
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM extra_table where profile_id='" + this.sp.getString(this.mcontext, "profile_id") + "' and table_name='work_table'", null);
        if (rawQuery.moveToFirst()) {
            for (int i3 = 0; i3 < rawQuery.getCount(); i3++) {
                DB_Items dB_Items = new DB_Items();
                dB_Items.setWorkid(rawQuery.getInt(rawQuery.getColumnIndex("table_id")));
                dB_Items.setTitle(rawQuery.getString(rawQuery.getColumnIndex("title")));
                dB_Items.setValue(rawQuery.getString(rawQuery.getColumnIndex("value")));
                this.extra_vals.add(dB_Items);
                rawQuery.moveToNext();
            }
        }
        rawQuery.close();
        SQLiteDatabase sQLiteDatabase2 = this.DB;
        sQLiteDatabase2.execSQL("delete from work_table where profile_id='" + this.sp.getString(this.mcontext, "profile_id") + "';");
        SQLiteDatabase sQLiteDatabase3 = this.DB;
        sQLiteDatabase3.execSQL("delete from extra_table where profile_id='" + this.sp.getString(this.mcontext, "profile_id") + "' and table_name='work_table';");
        for (int i4 = 0; i4 < this.mItems.size(); i4++) {
            SQLiteDatabase sQLiteDatabase4 = this.DB;
            sQLiteDatabase4.execSQL("INSERT INTO work_table (profile_id, organization, designation, emptype, date_of_join, end_date, role, extra_id) VALUES ('" + this.sp.getString(this.mcontext, "profile_id") + "', '" + this.mItems.get(i4).getOrganization().replaceAll("'", "''") + "', '" + this.mItems.get(i4).getDesignation().replaceAll("'", "''") + "', '" + this.mItems.get(i4).getType().replaceAll("'", "''") + "', '" + this.mItems.get(i4).getJoin_date().replaceAll("'", "''") + "', '" + this.mItems.get(i4).getEnd_date().replaceAll("'", "''") + "', '" + this.mItems.get(i4).getRole().replaceAll("'", "''") + "','" + this.mItems.get(i4).getExtra_id() + "');");
            Cursor rawQuery2 = this.DB.rawQuery("select work_id from work_table", null);
            rawQuery2.moveToLast();
            int i5 = rawQuery2.getInt(0);
            for (int i6 = 0; i6 < this.extra_vals.size(); i6++) {
                if (this.mItems.get(i4).getWorkid() == this.extra_vals.get(i6).getWorkid()) {
                    SQLiteDatabase sQLiteDatabase5 = this.DB;
                    sQLiteDatabase5.execSQL("INSERT INTO extra_table (profile_id, table_id, table_name, title, value) VALUES ('" + this.sp.getString(this.mcontext, "profile_id") + "', '" + i5 + "','work_table','" + this.extra_vals.get(i6).getTitle().replaceAll("'", "''") + "','" + this.extra_vals.get(i6).getValue().replaceAll("'", "''") + "');");
                }
            }
            rawQuery2.close();
        }
        Work_Exp_fragment.check();
        return true;
    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
        public final ImageView handleView;
        public final ImageView remove;
        public final TextView textView;

        public ItemViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.named);
            this.handleView = (ImageView) view.findViewById(R.id.down_img);
            this.remove = (ImageView) view.findViewById(R.id.remove);
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
