package nithra.resume.maker.cv.builder.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import nithra.resume.maker.cv.builder.app.Activity.Resume_Activity;
import nithra.resume.maker.cv.builder.app.Models.DB_Items;
import nithra.resume.maker.cv.builder.app.showcase.MaterialShowcaseSequence;
import nithra.resume.maker.cv.builder.app.showcase.MaterialShowcaseView;
import nithra.resume.maker.cv.builder.app.showcase.ShowcaseConfig;

public class Profile_Adapter extends RecyclerView.Adapter<Profile_Adapter.ViewHolder> {

    static String SHOWCASE_ID = "PERSONAL_VIEW";
    static ArrayList<DB_Items> alName;
    Context context;
    SQLiteDatabase myDB;
    SharedPreference sp = new SharedPreference();

    public Profile_Adapter(Context context2, ArrayList<DB_Items> arrayList) {
        this.context = context2;
        alName = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_profile, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.named.setText(alName.get(i).getName());
        String photo = alName.get(i).getPhoto();
        if (photo.equals("null")) {
            viewHolder.profile_img.setImageBitmap(null);
            viewHolder.profile_img.setBackgroundResource(R.drawable.resumeicon);
        } else if (new File(photo).exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            Bitmap decodeFile = BitmapFactory.decodeFile(photo, options);
            viewHolder.profile_img.setBackgroundResource(0);
            viewHolder.profile_img.setImageBitmap(decodeFile);
        } else {
            viewHolder.profile_img.setImageBitmap(null);
            viewHolder.profile_img.setBackgroundResource(R.drawable.resumeicon);
        }
        viewHolder.setClickListener(new ItemClickListener() {

            @Override // nithra.resume.maker.cv.builder.app.Profile_Adapter.ItemClickListener
            public void onClick(View view, int i, boolean z) {
                if (z) {
                    Context context = Profile_Adapter.this.context;
                    Toast.makeText(context, "#" + i + " - " + Profile_Adapter.alName.get(i) + " (Long click)", 0).show();
                    return;
                }
                Context context2 = Profile_Adapter.this.context;
                Toast.makeText(context2, "#" + i + " - " + Profile_Adapter.alName.get(i), 0).show();
            }
        });
        viewHolder.copy.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Profile_Adapter.this.sp.putString(Profile_Adapter.this.context, "copy", "yes");
                SharedPreference sharedPreference = Profile_Adapter.this.sp;
                Context context = Profile_Adapter.this.context;
                sharedPreference.putString(context, "copy_profile_id", "" + Profile_Adapter.alName.get(i).getProfile_id());
                AppCompatEditText appCompatEditText = MainActivity.en_name;
                appCompatEditText.setText("Copy of " + Profile_Adapter.alName.get(i).getName());
                MainActivity.en_name.setError(null);
                MainActivity.add_profile.setVisibility(View.VISIBLE);
                MainActivity.profile_recycle.setVisibility(View.GONE);
                MainActivity.new_add.setVisibility(View.GONE);
            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Profile_Adapter.this.context);
                builder.setMessage("Are you sure want to delete this profile?").setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int ii) {
                        SQLiteDatabase sQLiteDatabase = Profile_Adapter.this.myDB;
                        sQLiteDatabase.execSQL("delete from profile_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase2 = Profile_Adapter.this.myDB;
                        sQLiteDatabase2.execSQL("delete from academic_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase3 = Profile_Adapter.this.myDB;
                        sQLiteDatabase3.execSQL("delete from achievement_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase4 = Profile_Adapter.this.myDB;
                        sQLiteDatabase4.execSQL("delete from cocurricular_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase5 = Profile_Adapter.this.myDB;
                        sQLiteDatabase5.execSQL("delete from cover_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase6 = Profile_Adapter.this.myDB;
                        sQLiteDatabase6.execSQL("delete from extracurricular_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase7 = Profile_Adapter.this.myDB;
                        sQLiteDatabase7.execSQL("delete from hobbies_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase8 = Profile_Adapter.this.myDB;
                        sQLiteDatabase8.execSQL("delete from industrial_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase9 = Profile_Adapter.this.myDB;
                        sQLiteDatabase9.execSQL("delete from inplant_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase10 = Profile_Adapter.this.myDB;
                        sQLiteDatabase10.execSQL("delete from interest_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase11 = Profile_Adapter.this.myDB;
                        sQLiteDatabase11.execSQL("delete from objective_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase12 = Profile_Adapter.this.myDB;
                        sQLiteDatabase12.execSQL("delete from objmain_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase13 = Profile_Adapter.this.myDB;
                        sQLiteDatabase13.execSQL("delete from personal_info where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase14 = Profile_Adapter.this.myDB;
                        sQLiteDatabase14.execSQL("delete from project_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase15 = Profile_Adapter.this.myDB;
                        sQLiteDatabase15.execSQL("delete from reference_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase16 = Profile_Adapter.this.myDB;
                        sQLiteDatabase16.execSQL("delete from skill_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase17 = Profile_Adapter.this.myDB;
                        sQLiteDatabase17.execSQL("delete from strength_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase18 = Profile_Adapter.this.myDB;
                        sQLiteDatabase18.execSQL("delete from work_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase19 = Profile_Adapter.this.myDB;
                        sQLiteDatabase19.execSQL("delete from extra_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        SQLiteDatabase sQLiteDatabase20 = Profile_Adapter.this.myDB;
                        sQLiteDatabase20.execSQL("delete from search_table where profile_id ='" + Profile_Adapter.alName.get(i).getProfile_id() + "'");
                        StringBuilder sb = new StringBuilder();
                        sb.append("dfjkn");
                        sb.append(i);
                        String sb2 = sb.toString();
                        Log.e(sb2, "" + Profile_Adapter.alName.get(i).getPhoto());
                        Profile_Adapter.alName.remove(i);
                        Profile_Adapter.this.notifyDataSetChanged();
                        MainActivity.add_new.setVisibility(View.VISIBLE);
                        MainActivity.add_new_text.setVisibility(View.VISIBLE);
                        if (Profile_Adapter.alName.size() > 0) {
                            MainActivity.add_profile.setVisibility(View.GONE);
                            MainActivity.profile_recycle.setVisibility(View.VISIBLE);
                            MainActivity.new_add.setVisibility(View.VISIBLE);
                        } else {
                            Profile_Adapter.this.sp.putString(Profile_Adapter.this.context, "copy", "no");
                            MainActivity.en_name.setText("");
                            MainActivity.en_name.setError(null);
                            MainActivity.add_profile.setVisibility(View.VISIBLE);
                            MainActivity.profile_recycle.setVisibility(View.GONE);
                            MainActivity.new_add.setVisibility(View.GONE);
                        }
                        Utils.toast_center(Profile_Adapter.this.context, "Profile deleted successfully");
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    /* class nithra.resume.maker.cv.builder.app.Profile_Adapter.AnonymousClass3.AnonymousClass1 */

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return alName.size();
    }

    public void Showcase(ImageView imageView) {
        ShowcaseConfig showcaseConfig = new ShowcaseConfig();
        showcaseConfig.setDelay(500);
        MaterialShowcaseSequence materialShowcaseSequence = new MaterialShowcaseSequence((Activity) this.context, SHOWCASE_ID);
        materialShowcaseSequence.setConfig(showcaseConfig);
        materialShowcaseSequence.addSequenceItem(new MaterialShowcaseView.Builder((Activity) this.context).setTarget(imageView).setDismissText("OK").setDismissTextColor(this.context.getResources().getColor(R.color.red)).setDismissStyle(Typeface.createFromAsset(this.context.getAssets(), "Chunkfive.otf")).setContentText("Click here to make your resume").withRectangleShape().setDismissOnTargetTouch(false).setDismissOnTouch(false).setMaskColour(R.color.colorAccent).build());
        materialShowcaseSequence.start();
    }


    public static abstract class ItemClickListener {

        public abstract void onClick(View view, int i, boolean z);

        private ItemClickListener() {
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ItemClickListener clickListener;
        ImageView copy;
        ImageView delete;
        ImageView go;
        public TextView named;
        ImageView profile_img;

        public ViewHolder(final View view) {
            super(view);
            this.named = (TextView) view.findViewById(R.id.v_name);
            this.profile_img = (ImageView) view.findViewById(R.id.profile_img);
            this.go = (ImageView) view.findViewById(R.id.go);
            this.delete = (ImageView) view.findViewById(R.id.delete);
            this.copy = (ImageView) view.findViewById(R.id.copy);
            Profile_Adapter.this.myDB = Profile_Adapter.this.context.openOrCreateDatabase("RESUME_BUILDER", 0, null);
            view.setClickable(true);
            view.setOnClickListener(new View.OnClickListener() {
                /* class nithra.resume.maker.cv.builder.app.Profile_Adapter.ViewHolder.AnonymousClass1 */

                public void onClick(View view) {
                    SharedPreference sharedPreference = Profile_Adapter.this.sp;
                    Context context = Profile_Adapter.this.context;
                    sharedPreference.putString(context, "profile_id", "" + Profile_Adapter.alName.get(ViewHolder.this.getAdapterPosition()).getProfile_id());
                    SharedPreference sharedPreference2 = Profile_Adapter.this.sp;
                    Context context2 = Profile_Adapter.this.context;
                    sharedPreference2.putString(context2, "profile_name", "" + Profile_Adapter.alName.get(ViewHolder.this.getAdapterPosition()).getName());
                    Profile_Adapter.this.context.startActivity(new Intent(Profile_Adapter.this.context, Resume_Activity.class));
                    view.setClickable(false);
                }
            });
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        public void onClick(View view) {
            this.clickListener.onClick(view, getPosition(), false);
        }

        public boolean onLongClick(View view) {
            this.clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }
}
