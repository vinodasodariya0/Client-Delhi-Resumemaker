package nithra.resume.maker.cv.builder.app.Fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;



import android.text.Html;
import android.util.Log;
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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import nithra.resume.maker.cv.builder.app.MainActivity;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.SharedPreference;
import nithra.resume.maker.cv.builder.app.Utils;
import nithra.resume.maker.cv.builder.app.Visible;

public class Cover_Letter_fragment extends Fragment implements Visible {
    static Activity context;
    SQLiteDatabase DB;
    AppCompatEditText cover_address;
    AppCompatEditText cover_body;
    AppCompatEditText cover_date;
    LinearLayout cover_linear;
    Button cover_save;
    DatePickerDialog.OnDateSetListener date1;
    CardView mr_cover;
    Calendar myCalendar = Calendar.getInstance();
    SharedPreference sp = new SharedPreference();

    public static int getItem(int i) {
        return 0;
    }

    public static float dpToPixels(int i, Context context2) {
        return ((float) i) * context2.getResources().getDisplayMetrics().density;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        context = getActivity();
        View inflate = layoutInflater.inflate(R.layout.fragment_cover_letter, viewGroup, false);
        this.cover_address = (AppCompatEditText) inflate.findViewById(R.id.cover_address);
        this.cover_body = (AppCompatEditText) inflate.findViewById(R.id.cover_body);
        this.cover_date = (AppCompatEditText) inflate.findViewById(R.id.cover_date);
        this.cover_save = (Button) inflate.findViewById(R.id.cover_save);
        this.cover_linear = (LinearLayout) inflate.findViewById(R.id.cover_linear);
        this.mr_cover = (CardView) inflate.findViewById(R.id.mr_cover);
        this.DB = context.openOrCreateDatabase("RESUME_BUILDER", 0, null);
        String format = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        this.cover_body.setText((CharSequence) Arrays.asList(getResources().getStringArray(R.array.cover)).get(2));
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from cover_table where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
        rawQuery.moveToFirst();
        if (rawQuery.getCount() > 0) {
            AppCompatEditText appCompatEditText = this.cover_address;
            appCompatEditText.setText("" + Html.fromHtml(rawQuery.getString(rawQuery.getColumnIndex("address"))).toString());
            AppCompatEditText appCompatEditText2 = this.cover_body;
            appCompatEditText2.setText("" + Html.fromHtml(rawQuery.getString(rawQuery.getColumnIndex("body"))).toString());
            AppCompatEditText appCompatEditText3 = this.cover_date;
            appCompatEditText3.setText("" + rawQuery.getString(rawQuery.getColumnIndex("date")));
            this.cover_save.setText("UPDATE");
            Log.e("***********", rawQuery.getString(rawQuery.getColumnIndex("body")));
        } else {
            AppCompatEditText appCompatEditText4 = this.cover_date;
            appCompatEditText4.setText("" + format);
            this.cover_address.setText("George Gilbert\nXYZ Company\n87 Delaware Road\nHatfield, CA 08065.");
            Cursor rawQuery2 = this.DB.rawQuery("Select * from cover_body_table", null);
            rawQuery2.moveToFirst();
            if (rawQuery2.getCount() > 0) {
                AppCompatEditText appCompatEditText5 = this.cover_body;
                appCompatEditText5.setText("" + Html.fromHtml(rawQuery2.getString(rawQuery2.getColumnIndex("cover_body"))).toString());
            } else {
                this.DB.execSQL("INSERT INTO cover_body_table (cover_body) VALUES ('<p dir=\"ltr\">Dear Mr. Gilbert,<br>\n&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; I am writing to apply for the Developer position advertised in the New York Times. As requested, I am enclosing a completed job application, my certification, and resume. </p>\n<p dir=\"ltr\">The opportunity is very interesting, and I believe that my strong technical skills, experience, and education will make me an ideal candidate for this position.The key strengths that I have matches exactly for this position include:</p>\n<p dir=\"ltr\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &#8226; I have successfully designed and developed end-user applications<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &#8226; I strive for continued excellence for the success of the organization<br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &#8226; I provide exceptional contributions to satisfy all customers<br>\n <br>\nWith a BS degree in Computer Science Programming, I have a good knowledge of the life cycle of a software development project. I also have experience in learning and excelling at new technologies as needed.</p>\n<p dir=\"ltr\">Kindly review my resume for additional information to know about my experience. </p>\n<p dir=\"ltr\">I can be reached anytime via email at john.mcdonaldson@example.com or my mobile at 222-222-2222. </p>\n<p dir=\"ltr\">Thank you for your time and consideration. I look forward to speaking with you about this employment opportunity.</p>');");
                this.DB.execSQL("INSERT INTO cover_body_table (cover_body) VALUES ('<p dir=\"ltr\">Dear Mr. Anderson, <br>\n&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; I&#8217;m writing in response to your recently advertised position for an IT Analyst role. I am very interested in this opportunity with XXX Solutions and believe that my qualifications, education, and professional experience would make me a strong candidate for the position. </p>\n<p dir=\"ltr\">I am a highly organized, thorough and motivated professional with over three years experience in an IT role. I am currently employed with Office Tech, a leading company in Brisbane.</p>\n<p dir=\"ltr\">As I am a fast adaptive professional, I have mastered most of tools and methodologies for analysis and information systems development in different platforms including all published in your advertisement like UML, Enterprise Architect, USE CASE, etc. </p>\n<p dir=\"ltr\">Enclosed is my resume that more fully details my background and work experience, and how they relate to your position. </p>\n<p dir=\"ltr\">Job for your company would be the progress in my professional career and I hope I will have the opportunity to present myself on the job interview. </p>\n<p dir=\"ltr\">I can be reached anytime through my mobile at 555-555-5555 or mail at margret.chan@email.com. </p>\n<p dir=\"ltr\">Thank you for your time and consideration. I look forward to being able to discuss the position with you further.</p>');");
                this.DB.execSQL("INSERT INTO cover_body_table (cover_body) VALUES ('<p dir=\"ltr\">Dear Ms.Janet, <br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; l write in response to your ad seeking an Administrative Assistant at Life Style Professional. As a highly competent Administrative Assistant, I would bring a resourceful, flexible, and hard-working attitude to this role. </p>\n<p dir=\"ltr\">ln my current position, I maintain an exceedingly functional office environment while providing administrative support for a fast-paced production company. I have a capability for problem-solving and work well independently and little oversight. I respond to requests colleagues and clients in a timely manner and an adept at prioritizing multiple ongoing projects. </p>\n<p dir=\"ltr\">Additionally, I am adept at: </p>\n<p dir=\"ltr\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &#8226; Handling correspondence, reporting, and documents with efficiency and accuracy. <br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &#8226; Creating reports and writing documents that support business needs. <br>\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &#8226; Managing day-to-day activities and travel arrangement for professionals. </p>\n<p dir=\"ltr\">I am a self-starter and excel at inventory management, meeting support, and event planning. I also work well with Microsoft Word, Excel, and PowerPoint, and adapt quickly to new programs. </p>\n<p dir=\"ltr\">I hope that my experience at Life Style Professional would excel me to give good administrative support and organization to your firm. </p>\n<p dir=\"ltr\">I have attached my resume and references along with this letter. Kindly contact me at your earliest convenience so we can discuss the position and your needs in detail. </p>\n<p dir=\"ltr\">Thank you for time and consideration.</p>');");
                Cursor rawQuery3 = this.DB.rawQuery("Select * from cover_body_table", null);
                rawQuery3.moveToFirst();
                if (rawQuery3.getCount() > 0) {
                    AppCompatEditText appCompatEditText6 = this.cover_body;
                    appCompatEditText6.setText("" + Html.fromHtml(rawQuery3.getString(rawQuery3.getColumnIndex("cover_body"))).toString());
                }
                rawQuery3.close();
            }
            rawQuery2.close();
            this.cover_save.setText("SAVE");
        }
        rawQuery.close();
        this.date1 = new DatePickerDialog.OnDateSetListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Cover_Letter_fragment.AnonymousClass1 */

            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Cover_Letter_fragment.this.myCalendar.set(1, i);
                Cover_Letter_fragment.this.myCalendar.set(2, i2);
                Cover_Letter_fragment.this.myCalendar.set(5, i3);
                new SimpleDateFormat("dd - MM - yyyy").format(Cover_Letter_fragment.this.myCalendar.getTime());
                String format = DateFormat.getDateInstance().format(Cover_Letter_fragment.this.myCalendar.getTime());
                Cover_Letter_fragment.this.cover_date.setText(format);
                Cover_Letter_fragment.this.cover_date.setSelection(format.length());
                Cover_Letter_fragment.this.cover_date.setError(null);
            }
        };
        this.mr_cover.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Cover_Letter_fragment.AnonymousClass2 */

            public void onClick(View view) {
                Cover_Letter_fragment.this.Cover_dialog();
            }
        });
        this.cover_linear.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Cover_Letter_fragment.AnonymousClass3 */

            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Cover_Letter_fragment.context, Cover_Letter_fragment.this.date1, Cover_Letter_fragment.this.myCalendar.get(1), Cover_Letter_fragment.this.myCalendar.get(2), Cover_Letter_fragment.this.myCalendar.get(5));
                Calendar instance = Calendar.getInstance();
                instance.set(2018, 0, 1);
                datePickerDialog.getDatePicker().setMinDate(instance.getTimeInMillis() - 1000);
                datePickerDialog.show();
            }
        });
        this.cover_save.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Cover_Letter_fragment.AnonymousClass4 */

            public void onClick(View view) {
                String trim = Cover_Letter_fragment.this.cover_date.getText().toString().trim();
                String trim2 = Cover_Letter_fragment.this.cover_address.getText().toString().trim();
                String trim3 = Cover_Letter_fragment.this.cover_body.getText().toString().trim();
                if (trim.equals("")) {
                    Cover_Letter_fragment.this.cover_date.setError("Enter date !!!");
                    Cover_Letter_fragment.this.cover_date.requestFocus();
                } else if (trim2.equals("")) {
                    Cover_Letter_fragment.this.cover_address.setError("Enter Address To !!!");
                    Cover_Letter_fragment.this.cover_address.requestFocus();
                } else if (trim3.equals("")) {
                    Cover_Letter_fragment.this.cover_body.setError("Type your content here... !!!");
                    Cover_Letter_fragment.this.cover_body.requestFocus();
                } else {
                    String html = Html.toHtml(Cover_Letter_fragment.this.cover_body.getText());
                    Log.e("***********", html);
                    Cover_Letter_fragment.this.hideSoftKeyboard();
                    SQLiteDatabase sQLiteDatabase = Cover_Letter_fragment.this.DB;
                    if (sQLiteDatabase.rawQuery("Select profile_id from cover_table where profile_id='" + Cover_Letter_fragment.this.sp.getString(Cover_Letter_fragment.context, "profile_id") + "'", null).getCount() == 0) {
                        SQLiteDatabase sQLiteDatabase2 = Cover_Letter_fragment.this.DB;
                        sQLiteDatabase2.execSQL("INSERT INTO cover_table (profile_id, date, address, body) VALUES ('" + Cover_Letter_fragment.this.sp.getString(Cover_Letter_fragment.context, "profile_id") + "', '" + Cover_Letter_fragment.this.cover_date.getText().toString().replaceAll("'", "''") + "', '" + Html.toHtml(Cover_Letter_fragment.this.cover_address.getText()).replaceAll("'", "''") + "', '" + html.replaceAll("'", "''") + "');");
                        Utils.toast_center(Cover_Letter_fragment.context, "Saved Successfully");
                        Cover_Letter_fragment.this.cover_save.setText("UPDATE");
                        return;
                    }
                    SQLiteDatabase sQLiteDatabase3 = Cover_Letter_fragment.this.DB;
                    sQLiteDatabase3.execSQL("UPDATE cover_table SET date = '" + Cover_Letter_fragment.this.cover_date.getText().toString().replaceAll("'", "''") + "', address = '" + Html.toHtml(Cover_Letter_fragment.this.cover_address.getText()).replaceAll("'", "''") + "', body = '" + html.replaceAll("'", "''") + "' WHERE profile_id = '" + Cover_Letter_fragment.this.sp.getString(Cover_Letter_fragment.context, "profile_id") + "';");
                    Utils.toast_center(Cover_Letter_fragment.context, "Updated Successfully");
                    Cover_Letter_fragment.this.cover_save.setText("UPDATE");
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Cover_Letter_fragment.AnonymousClass5 */

            public void onClick(View view) {
                Cover_Letter_fragment.context.finish();
                dialog.dismiss();
            }
        });
        ((TextView) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Cover_Letter_fragment.AnonymousClass6 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public void Cover_dialog() {
        ArrayList arrayList = new ArrayList();
        final Dialog dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_objective);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Cover_Letter_fragment.AnonymousClass7 */

            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        ListView listView = (ListView) dialog.findViewById(R.id.obj_list);
        arrayList.clear();
        arrayList.add(0, "Cover Letter Format - 1");
        arrayList.add(1, "Cover Letter Format - 2");
        arrayList.add(2, "Cover Letter Format - 3");
        listView.setAdapter((ListAdapter) new nithra.resume.maker.cv.builder.app.Adapter.ListAdapter(context, R.layout.dialog_objective, arrayList));
        ((TabLayout) dialog.findViewById(R.id.tabs)).setVisibility(View.GONE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Cover_Letter_fragment.AnonymousClass8 */

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Cover_Letter_fragment.this.cover_body.setText("");
                Cursor rawQuery = Cover_Letter_fragment.this.DB.rawQuery("Select * from cover_body_table", null);
                rawQuery.moveToPosition(i);
                if (rawQuery.getCount() > 0) {
                    AppCompatEditText appCompatEditText = Cover_Letter_fragment.this.cover_body;
                    appCompatEditText.setText("" + Html.fromHtml(rawQuery.getString(rawQuery.getColumnIndex("cover_body"))).toString());
                    Cover_Letter_fragment.this.cover_body.setError(null);
                }
                rawQuery.close();
                dialog.dismiss();
            }
        });
        ((ImageView) dialog.findViewById(R.id.closefile)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Cover_Letter_fragment.AnonymousClass9 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Cover_Letter_fragment.AnonymousClass10 */

            public void onDismiss(DialogInterface dialogInterface) {
                Cover_Letter_fragment.this.getFragmentManager().beginTransaction().detach(Cover_Letter_fragment.this).attach(Cover_Letter_fragment.this).commit();
            }
        });
    }
}
