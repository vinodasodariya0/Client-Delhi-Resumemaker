package nithra.resume.maker.cv.builder.app.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nithra.resume.maker.cv.builder.app.Formates.Format_10;
import nithra.resume.maker.cv.builder.app.Formates.Format_11;
import nithra.resume.maker.cv.builder.app.Formates.Format_12;
import nithra.resume.maker.cv.builder.app.Formates.Format_13;
import nithra.resume.maker.cv.builder.app.Formates.Format_14;
import nithra.resume.maker.cv.builder.app.Formates.Format_15;
import nithra.resume.maker.cv.builder.app.Formates.Format_16;
import nithra.resume.maker.cv.builder.app.Formates.Format_17;
import nithra.resume.maker.cv.builder.app.Formates.Format_18;
import nithra.resume.maker.cv.builder.app.Formates.Format_19;
import nithra.resume.maker.cv.builder.app.Formates.Format_20;
import nithra.resume.maker.cv.builder.app.Formates.Preview;
import nithra.resume.maker.cv.builder.app.MainActivity;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.SharedPreference;
import nithra.resume.maker.cv.builder.app.Utils;
import nithra.resume.maker.cv.builder.app.Visible;

public class Preview_fragment extends Fragment implements Visible {
    static Activity context;
    SQLiteDatabase DB;
    CustomGrid adapter1;
    String image1 = null;
    List<String> list = new ArrayList();
    String name = null;
    String sign1 = null;
    SharedPreference sp = new SharedPreference();
    GridView templates;

    public static int getItem(int i) {
        return 0;
    }

    public static float dpToPixels(int i, Context context2) {
        return ((float) i) * context2.getResources().getDisplayMetrics().density;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        context = getActivity();
        View inflate = layoutInflater.inflate(R.layout.fragment_preview, viewGroup, false);
        this.templates = (GridView) inflate.findViewById(R.id.templates);
        this.DB = context.openOrCreateDatabase("RESUME_BUILDER", 0, null);
        this.list.clear();
        this.list.add("Formal Resume");
        this.list.add("Expert Resume");
        this.list.add("Innovative Resume");
        this.list.add("Professional Resume");
        this.list.add("Traditional Resume");
        this.list.add("Simple Resume");
        this.list.add("Effective Resume");
        this.list.add("Classic Resume");
        this.list.add("Graduate Resume");
        this.list.add("Functional Resume");
        this.list.add("Perfect Resume");
        this.list.add("Creative Resume");
        this.list.add("Experienced Resume");
        this.list.add("Basic Resume");
        this.list.add("Smart Resume");
        this.list.add("Modern Resume");
        this.list.add("Spick and Span Resume");
        this.list.add("Modish Resume");
        this.list.add("Fresher Resume");
        this.list.add("Technical Resume");
        this.adapter1 = new CustomGrid(context, this.list);
        this.templates.setAdapter((ListAdapter) this.adapter1);
        return inflate;
    }

    @Override // nithra.resume.maker.cv.builder.app.Visible
    public void visible() {
        exti_result_dialog();
    }

    public void update() {
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT photo FROM profile_table where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
        if (rawQuery.getCount() > 0) {
            SQLiteDatabase sQLiteDatabase2 = this.DB;
            sQLiteDatabase2.execSQL("UPDATE profile_table SET photo = '" + this.sp.getString(context, "picturepath") + "', sign='" + this.sp.getString(context, "signpath") + "' where profile_id='" + this.sp.getString(context, "profile_id") + "';");
        } else {
            SQLiteDatabase sQLiteDatabase3 = this.DB;
            sQLiteDatabase3.execSQL("INSERT INTO profile_table (photo, sign) VALUES ('" + this.sp.getString(context, "picturepath") + "','" + this.sp.getString(context, "signpath") + "');");
        }
        rawQuery.close();
    }

    public void photo_check(final int i) {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_check);
        CardView cardView = (CardView) dialog.findViewById(R.id.preview);
        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(R.id.photo);
        LinearLayout linearLayout2 = (LinearLayout) dialog.findViewById(R.id.sign);
        LinearLayout linearLayout3 = (LinearLayout) dialog.findViewById(R.id.sign_name);
        LinearLayout linearLayout4 = (LinearLayout) dialog.findViewById(R.id.cover);
        final CheckBox checkBox = (CheckBox) dialog.findViewById(R.id.photo_check);
        final CheckBox checkBox2 = (CheckBox) dialog.findViewById(R.id.sign_check);
        final CheckBox checkBox3 = (CheckBox) dialog.findViewById(R.id.sign_name_check);
        final CheckBox checkBox4 = (CheckBox) dialog.findViewById(R.id.cover_check);
        if (i < 4) {
            linearLayout.setVisibility(View.VISIBLE);
        } else if (i > 3 && i < 9) {
            linearLayout.setVisibility(View.GONE);
        } else if (i == 18) {
            linearLayout.setVisibility(View.GONE);
        } else {
            linearLayout.setVisibility(View.VISIBLE);
        }
        checkBox.setClickable(false);
        checkBox2.setClickable(false);
        checkBox3.setClickable(false);
        checkBox4.setClickable(false);
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("select * from profile_table where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            this.image1 = rawQuery.getString(rawQuery.getColumnIndex("photo"));
            Log.e("0000901", rawQuery.getString(rawQuery.getColumnIndex("photo")));
            this.sign1 = rawQuery.getString(rawQuery.getColumnIndex("sign"));
            if (this.image1.equals("") || this.image1.equals("null")) {
                checkBox.setChecked(false);
            } else {
                checkBox.setChecked(true);
            }
            if (this.sign1.equals("") || this.sign1.equals("null")) {
                checkBox2.setChecked(false);
            } else {
                checkBox2.setChecked(true);
            }
        } else {
            checkBox.setChecked(false);
            checkBox2.setChecked(false);
            checkBox3.setChecked(false);
        }
        rawQuery.close();
        SQLiteDatabase sQLiteDatabase2 = this.DB;
        Cursor rawQuery2 = sQLiteDatabase2.rawQuery("select name from personal_info where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
        if (rawQuery2.getCount() > 0) {
            rawQuery2.moveToFirst();
            this.name = rawQuery2.getString(rawQuery2.getColumnIndex(AppMeasurementSdk.ConditionalUserProperty.NAME));
            if (this.name.equals("") || this.name.equals("null")) {
                checkBox3.setChecked(false);
            } else {
                checkBox3.setChecked(true);
            }
        } else {
            this.name = "";
            checkBox3.setChecked(false);
        }
        rawQuery2.close();
        if (checkBox.isChecked()) {
            this.sp.putBoolean(context, "photo_check", true);
        } else {
            this.sp.putBoolean(context, "photo_check", false);
        }
        if (checkBox2.isChecked()) {
            this.sp.putBoolean(context, "sign_check", true);
        } else {
            this.sp.putBoolean(context, "sign_check", false);
        }
        if (checkBox4.isChecked()) {
            this.sp.putBoolean(context, "cover_check", true);
        } else {
            this.sp.putBoolean(context, "cover_check", false);
        }
        if (checkBox3.isChecked()) {
            this.sp.putBoolean(context, "sign_name_check", true);
        } else {
            this.sp.putBoolean(context, "sign_name_check", false);
        }
        linearLayout.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Preview_fragment.AnonymousClass1 */

            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    checkBox.setChecked(false);
                    Preview_fragment.this.sp.putBoolean(Preview_fragment.context, "photo_check", false);
                } else if (Preview_fragment.this.image1.equals("") || Preview_fragment.this.image1.equals("null")) {
                    Utils.toast_center(Preview_fragment.context, "You have not uploaded your photo...");
                } else {
                    checkBox.setChecked(true);
                    Preview_fragment.this.sp.putBoolean(Preview_fragment.context, "photo_check", true);
                }
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Preview_fragment.AnonymousClass2 */

            public void onClick(View view) {
                if (checkBox2.isChecked()) {
                    checkBox2.setChecked(false);
                    Preview_fragment.this.sp.putBoolean(Preview_fragment.context, "sign_check", false);
                } else if (Preview_fragment.this.sign1.equals("") || Preview_fragment.this.sign1.equals("null")) {
                    Utils.toast_center(Preview_fragment.context, "You have not penned your signature...");
                } else {
                    checkBox2.setChecked(true);
                    Preview_fragment.this.sp.putBoolean(Preview_fragment.context, "sign_check", true);
                }
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Preview_fragment.AnonymousClass3 */

            public void onClick(View view) {
                if (checkBox3.isChecked()) {
                    checkBox3.setChecked(false);
                    Preview_fragment.this.sp.putBoolean(Preview_fragment.context, "sign_name_check", false);
                } else if (Preview_fragment.this.name.equals("") || Preview_fragment.this.name.equals("null")) {
                    Utils.toast_center(Preview_fragment.context, "You have not entered your name...");
                } else {
                    checkBox3.setChecked(true);
                    Preview_fragment.this.sp.putBoolean(Preview_fragment.context, "sign_name_check", true);
                }
            }
        });
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Preview_fragment.AnonymousClass4 */

            public void onClick(View view) {
                if (checkBox4.isChecked()) {
                    checkBox4.setChecked(false);
                    Preview_fragment.this.sp.putBoolean(Preview_fragment.context, "cover_check", false);
                    return;
                }
                SQLiteDatabase sQLiteDatabase = Preview_fragment.this.DB;
                Cursor rawQuery = sQLiteDatabase.rawQuery("select * from cover_table where profile_id='" + Preview_fragment.this.sp.getString(Preview_fragment.context, "profile_id") + "'", null);
                if (rawQuery.getCount() > 0) {
                    checkBox4.setChecked(true);
                    Preview_fragment.this.sp.putBoolean(Preview_fragment.context, "cover_check", true);
                } else {
                    checkBox4.setChecked(false);
                    Utils.toast_center(Preview_fragment.context, "You have not included the cover letter...");
                }
                rawQuery.close();
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Preview_fragment.AnonymousClass5 */

            public void onClick(View view) {
                Preview_fragment.this.sp.putInt(Preview_fragment.context, "mark_position", i);

                if (i == 9) {
                    Preview_fragment.this.startActivity(new Intent(Preview_fragment.context, Format_10.class));
                } else if (i == 10) {
                    Preview_fragment.this.startActivity(new Intent(Preview_fragment.context, Format_11.class));
                } else if (i == 11) {
                    Preview_fragment.this.startActivity(new Intent(Preview_fragment.context, Format_12.class));
                } else if (i == 12) {
                    Preview_fragment.this.startActivity(new Intent(Preview_fragment.context, Format_13.class));
                } else if (i == 13) {
                    Preview_fragment.this.startActivity(new Intent(Preview_fragment.context, Format_14.class));
                } else if (i == 14) {
                    Preview_fragment.this.startActivity(new Intent(Preview_fragment.context, Format_15.class));
                } else if (i == 15) {
                    Preview_fragment.this.startActivity(new Intent(Preview_fragment.context, Format_16.class));
                } else if (i == 16) {
                    Preview_fragment.this.startActivity(new Intent(Preview_fragment.context, Format_17.class));
                } else if (i == 17) {
                    Preview_fragment.this.startActivity(new Intent(Preview_fragment.context, Format_18.class));
                } else if (i == 18) {
                    Preview_fragment.this.startActivity(new Intent(Preview_fragment.context, Format_19.class));
                } else if (i == 19) {
                    Preview_fragment.this.startActivity(new Intent(Preview_fragment.context, Format_20.class));
                } else {
                    Preview_fragment.this.startActivity(new Intent(Preview_fragment.context, Preview.class));
                }
                Preview_fragment.this.adapter1.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public void exti_result_dialog() {
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_exit);
        MainActivity.load_addFromMain_natt(context, (FrameLayout) dialog.findViewById(R.id.ads_lay));
        ((TextView) dialog.findViewById(R.id.yes)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Preview_fragment.AnonymousClass6 */

            public void onClick(View view) {
                Preview_fragment.context.finish();
                dialog.dismiss();
            }
        });
        ((TextView) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Preview_fragment.AnonymousClass7 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public void zoom_dialog(int i) {
        Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.zoom_image);
        ((ViewPager) dialog.findViewById(R.id.viewPager)).setAdapter(new MyCustomPagerAdapter(context, 1, i));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {
        HashMap<String, Integer> mIdMap = new HashMap<>();

        public boolean hasStableIds() {
            return true;
        }

        public StableArrayAdapter(Context context, int i, List<String> list) {
            super(context, i, list);
            for (int i2 = 0; i2 < list.size(); i2++) {
                this.mIdMap.put(list.get(i2), Integer.valueOf(i2));
            }
        }

        public long getItemId(int i) {
            return (long) this.mIdMap.get((String) getItem(i)).intValue();
        }
    }

    public class CustomGrid extends BaseAdapter {
        Context context;
        private LayoutInflater inflater = null;
        List<String> name;

        public long getItemId(int i) {
            return (long) i;
        }

        public CustomGrid(Context context2, List<String> list) {
            this.name = list;
            this.context = context2;
            this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return this.name.size();
        }

        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            Holder holder = new Holder();
            View inflate = this.inflater.inflate(R.layout.grid_single, (ViewGroup) null);
            holder.tv = (TextView) inflate.findViewById(R.id.format);
            holder.crd_prw = (CardView) inflate.findViewById(R.id.crd_prw);
            holder.bg_card = (CardView) inflate.findViewById(R.id.bg_card);
            holder.format_img = (ImageView) inflate.findViewById(R.id.format_img);
            holder.select_resume = (ImageView) inflate.findViewById(R.id.select_resume);
            if (i == 0) {
                holder.format_img.setBackgroundResource(R.drawable.one);
            } else if (i == 1) {
                holder.format_img.setBackgroundResource(R.drawable.two);
            } else if (i == 2) {
                holder.format_img.setBackgroundResource(R.drawable.three);
            } else if (i == 3) {
                holder.format_img.setBackgroundResource(R.drawable.four);
            } else if (i == 4) {
                holder.format_img.setBackgroundResource(R.drawable.five);
            } else if (i == 5) {
                holder.format_img.setBackgroundResource(R.drawable.six);
            } else if (i == 6) {
                holder.format_img.setBackgroundResource(R.drawable.seven);
            } else if (i == 7) {
                holder.format_img.setBackgroundResource(R.drawable.eight);
            } else if (i == 8) {
                holder.format_img.setBackgroundResource(R.drawable.nine);
            } else if (i == 9) {
                holder.format_img.setBackgroundResource(R.drawable.ten);
            } else if (i == 10) {
                holder.format_img.setBackgroundResource(R.drawable.eleven);
            } else if (i == 11) {
                holder.format_img.setBackgroundResource(R.drawable.towel);
            } else if (i == 12) {
                holder.format_img.setBackgroundResource(R.drawable.thirteen);
            } else if (i == 13) {
                holder.format_img.setBackgroundResource(R.drawable.fourteen);
            } else if (i == 14) {
                holder.format_img.setBackgroundResource(R.drawable.fifteen);
            } else if (i == 15) {
                holder.format_img.setBackgroundResource(R.drawable.sixteen);
            } else if (i == 16) {
                holder.format_img.setBackgroundResource(R.drawable.seventeen);
            } else if (i == 17) {
                holder.format_img.setBackgroundResource(R.drawable.eightteen);
            } else if (i == 18) {
                holder.format_img.setBackgroundResource(R.drawable.nineteen);
            } else if (i == 19) {
                holder.format_img.setBackgroundResource(R.drawable.townty);
            }
            holder.tv.setText(this.name.get(i));
            if (Preview_fragment.this.sp.getInt(this.context, "mark_position") == i) {
                holder.select_resume.setVisibility(View.VISIBLE);
            } else {
                holder.select_resume.setVisibility(View.GONE);
            }
            holder.crd_prw.setOnClickListener(new View.OnClickListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Preview_fragment.CustomGrid.AnonymousClass1 */

                public void onClick(View view) {
                    Preview_fragment.this.sp.putInt(CustomGrid.this.context, "key", i);
                    Preview_fragment.this.photo_check(i);
                }
            });
            holder.format_img.setOnClickListener(new View.OnClickListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Preview_fragment.CustomGrid.AnonymousClass2 */

                public void onClick(View view) {
                    Preview_fragment.this.zoom_dialog(i);
                }
            });
            holder.bg_card.setOnClickListener(new View.OnClickListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Preview_fragment.CustomGrid.AnonymousClass3 */

                public void onClick(View view) {
                    String str = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + "/Nithra/ResumeBuilder/" + Preview_fragment.this.sp.getString(CustomGrid.this.context, "profile_name") + "_" + CustomGrid.this.name.get(i) + ".pdf";
                    Log.e("kozhii7", str);
                    File file = new File(str);
                    if (file.exists()) {
                        Uri fromFile = Uri.fromFile(file);
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setFlags(67108864);
                        intent.setDataAndType(fromFile, "application/pdf");
                        try {
                            Preview_fragment.this.startActivity(intent);
                        } catch (ActivityNotFoundException unused) {
                            Utils.toast_center(CustomGrid.this.context, "No Application available to view PDF");
                        }
                    } else {
                        Utils.toast_center(CustomGrid.this.context, "No resume found, Generate your resume to view it");
                    }
                }
            });
            return inflate;
        }

        public class Holder {
            CardView bg_card;
            CardView crd_prw;
            ImageView format_img;
            ImageView select_resume;
            TextView tv;

            public Holder() {
            }
        }
    }

    public class MyCustomPagerAdapter extends PagerAdapter {
        Context context;
        int count;
        LayoutInflater layoutInflater;
        int name;
        int[] resume_clr1 = {R.drawable.eleven};
        int[] resume_clr10 = {R.drawable.townty};
        int[] resume_clr2 = {R.drawable.towel};
        int[] resume_clr3 = {R.drawable.thirteen};
        int[] resume_clr4 = {R.drawable.fourteen};
        int[] resume_clr5 = {R.drawable.fifteen};
        int[] resume_clr6 = {R.drawable.sixteen};
        int[] resume_clr7 = {R.drawable.seventeen};
        int[] resume_clr8 = {R.drawable.eightteen};
        int[] resume_clr9 = {R.drawable.nineteen};
        int[] resume_nrml1 = {R.drawable.one};
        int[] resume_nrml10 = {R.drawable.ten};
        int[] resume_nrml2 = {R.drawable.two};
        int[] resume_nrml3 = {R.drawable.three};
        int[] resume_nrml4 = {R.drawable.four};
        int[] resume_nrml5 = {R.drawable.five};
        int[] resume_nrml6 = {R.drawable.six};
        int[] resume_nrml7 = {R.drawable.seven};
        int[] resume_nrml8 = {R.drawable.eight};
        int[] resume_nrml9 = {R.drawable.nine};

        @Override // android.support.v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public MyCustomPagerAdapter(Context context2, int i, int i2) {
            this.context = context2;
            this.count = i;
            this.name = i2;
            this.layoutInflater = (LayoutInflater) context2.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getCount() {
            return this.count;
        }

        @Override // android.support.v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View inflate = this.layoutInflater.inflate(R.layout.view_pdf_item, viewGroup, false);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.zoom_view);
            int i2 = this.name;
            if (i2 == 0) {
                imageView.setImageResource(this.resume_nrml1[i]);
            } else if (i2 == 1) {
                imageView.setImageResource(this.resume_nrml2[i]);
            } else if (i2 == 2) {
                imageView.setImageResource(this.resume_nrml3[i]);
            } else if (i2 == 3) {
                imageView.setImageResource(this.resume_nrml4[i]);
            } else if (i2 == 4) {
                imageView.setImageResource(this.resume_nrml5[i]);
            } else if (i2 == 5) {
                imageView.setImageResource(this.resume_nrml6[i]);
            } else if (i2 == 6) {
                imageView.setImageResource(this.resume_nrml7[i]);
            } else if (i2 == 7) {
                imageView.setImageResource(this.resume_nrml8[i]);
            } else if (i2 == 8) {
                imageView.setImageResource(this.resume_nrml9[i]);
            } else if (i2 == 9) {
                imageView.setImageResource(this.resume_nrml10[i]);
            } else if (i2 == 10) {
                imageView.setImageResource(this.resume_clr1[i]);
            } else if (i2 == 11) {
                imageView.setImageResource(this.resume_clr2[i]);
            } else if (i2 == 12) {
                imageView.setImageResource(this.resume_clr3[i]);
            } else if (i2 == 13) {
                imageView.setImageResource(this.resume_clr4[i]);
            } else if (i2 == 14) {
                imageView.setImageResource(this.resume_clr5[i]);
            } else if (i2 == 15) {
                imageView.setImageResource(this.resume_clr6[i]);
            } else if (i2 == 16) {
                imageView.setImageResource(this.resume_clr7[i]);
            } else if (i2 == 17) {
                imageView.setImageResource(this.resume_clr8[i]);
            } else if (i2 == 18) {
                imageView.setImageResource(this.resume_clr9[i]);
            } else if (i2 == 19) {
                imageView.setImageResource(this.resume_clr10[i]);
            }
            viewGroup.addView(inflate);
            return inflate;
        }

        @Override // android.support.v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((LinearLayout) obj);
        }
    }
}
