package nithra.resume.maker.cv.builder.app.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;




import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import nithra.resume.maker.cv.builder.app.Adapter.CustomAdapter;
import nithra.resume.maker.cv.builder.app.MainActivity;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.SharedPreference;
import nithra.resume.maker.cv.builder.app.Visible;

public class Pdf_fragment extends Fragment implements Visible {
    static Activity context;
    public static LinearLayout no_file;
    public static RecyclerView recyclerView;
    SQLiteDatabase DB;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    List<String> list = new ArrayList();
    File path;
    List<String> pdf_names = new ArrayList();
    List<String> pdf_paths = new ArrayList();
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
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        View inflate = layoutInflater.inflate(R.layout.fragment_pdf_view, viewGroup, false);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerView);
        no_file = (LinearLayout) inflate.findViewById(R.id.no_file);
        this.DB = context.openOrCreateDatabase("RESUME_BUILDER", 0, null);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Nithra/ResumeBuilder");
        searchFolderRecursive1(this.path);
        if (this.pdf_names.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            no_file.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            no_file.setVisibility(View.VISIBLE);
        }
        this.adapter = new CustomAdapter(this.pdf_names, this.pdf_paths, context);
        recyclerView.setAdapter(this.adapter);
        return inflate;
    }

    private void loadRecyclerViewItem() {
        for (int i = 1; i <= 5; i++) {
            this.list.add("Sample " + i);
        }
        this.adapter = new CustomAdapter(this.pdf_names, this.pdf_paths, context);
        recyclerView.setAdapter(this.adapter);
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
            /* class nithra.resume.maker.cv.builder.app.Fragment.Pdf_fragment.AnonymousClass1 */

            public void onClick(View view) {
                Pdf_fragment.context.finish();
                dialog.dismiss();
            }
        });
        ((TextView) dialog.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Pdf_fragment.AnonymousClass2 */

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public void searchFolderRecursive1(File file) {
        if (!(file == null || file.listFiles() == null)) {
            this.pdf_paths.clear();
            this.pdf_names.clear();
            File[] listFiles = file.listFiles();
            Arrays.sort(listFiles, new Comparator<File>() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Pdf_fragment.AnonymousClass3 */

                public int compare(File file, File file2) {
                    return Long.valueOf(file2.lastModified()).compareTo(Long.valueOf(file.lastModified()));
                }
            });
            for (File file2 : listFiles) {
                if (file2.getName().contains(".pdf")) {
                    file2.getPath();
                    this.pdf_names.add(file2.getName());
                    this.pdf_paths.add(file2.getPath());
                }
                System.out.println(file2.getName());
            }
        }
    }
}
