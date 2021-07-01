package nithra.resume.maker.cv.builder.app.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;




import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.List;
import nithra.resume.maker.cv.builder.app.Fragment.Pdf_fragment;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.Utils;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<String> list;
    private Context mCtx;
    private List<String> path;

    public CustomAdapter(List<String> list2, List<String> list3, Context context) {
        this.list = list2;
        this.path = list3;
        this.mCtx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pdf_view, viewGroup, false);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        return new ViewHolder(inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.textViewHead.setText(this.list.get(i));
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + "/Nithra/ResumeBuilder/" + this.list.get(i));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        PrintStream printStream = System.out;
        printStream.println("-------" + simpleDateFormat.format(Long.valueOf(file.lastModified())));
        viewHolder.textViewDesc.setText(simpleDateFormat.format(Long.valueOf(file.lastModified())));
        viewHolder.rename.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                CustomAdapter customAdapter = CustomAdapter.this;
                customAdapter.rename_dialog((String) customAdapter.list.get(i), i);
            }
        });
        viewHolder.share.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                File file = new File((String) CustomAdapter.this.path.get(i));
                Uri parse = Uri.parse("file://" + file);
                try {
                    Intent intent = new Intent("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.SUBJECT", "Shared via Resume Builder");
                    intent.putExtra("android.intent.extra.TEXT", "Download Free Resume Builder app to create the best professional resume for your career. Click this link to Download :   \n\n https://goo.gl/7pjw6E");
                    intent.putExtra("android.intent.extra.STREAM", parse);
                    intent.setType("application/pdf");
                    CustomAdapter.this.mCtx.startActivity(Intent.createChooser(intent, "Share pdf File"));
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                    Utils.toast_center(CustomAdapter.this.mCtx, "Try again...");
                }
            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CustomAdapter.this.mCtx);
                builder.setMessage("Do you want delete this Resume?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int ii) {
                        new File((String) CustomAdapter.this.path.get(i)).delete();
                        CustomAdapter.this.list.remove(i);
                        CustomAdapter.this.path.remove(i);
                        if (CustomAdapter.this.list.size() > 0) {
                            Pdf_fragment.recyclerView.setVisibility(View.VISIBLE);
                            Pdf_fragment.no_file.setVisibility(View.GONE);
                        } else {
                            Pdf_fragment.recyclerView.setVisibility(View.GONE);
                            Pdf_fragment.no_file.setVisibility(View.VISIBLE);
                        }
                        CustomAdapter.this.notifyDataSetChanged();
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Uri fromFile = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Nithra/ResumeBuilder/", (String) CustomAdapter.this.list.get(i)));
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setFlags(67108864);
                intent.setDataAndType(fromFile, "application/pdf");
                try {
                    CustomAdapter.this.mCtx.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    Utils.toast_center(CustomAdapter.this.mCtx, "No Application available to view PDF");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public void rename_dialog(String str, final int i) {
        final Dialog dialog = new Dialog(this.mCtx, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.save_dial);
        final EditText editText = (EditText) dialog.findViewById(R.id.edit1);
        final TextView textView = (TextView) dialog.findViewById(R.id.saveit);
        ((TextView) dialog.findViewById(R.id.cp_accent_title)).setText("File Rename");
        editText.setText(str.replace(".pdf", ""));
        textView.setText("File path : Nithra/ResumeBuilder/" + str);
        editText.setSelection(editText.getText().toString().length());
        editText.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {

                textView.setText("File path : Nithra/ResumeBuilder/" + editable.toString() + ".pdf");
            }
        });
        ((TextView) dialog.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        ((TextView) dialog.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (editText.getText().toString().trim().equals("")) {
                    editText.setError("Enter file name !!!");
                    editText.requestFocus();
                    return;
                }
                String obj = editText.getText().toString();
                if (!obj.contains(".pdf")) {
                    obj = obj + ".pdf";
                }
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Nithra/ResumeBuilder/" + obj);
                File file2 = new File((String) CustomAdapter.this.path.get(i));
                if (file.exists()) {
                    try {
                        throw new IOException("File already exists!");
                    } catch (IOException e) {
                        e.printStackTrace();
                        editText.setError("File already exists!!!");
                    }
                } else if (!file2.renameTo(file)) {
                    System.out.println("Couldn't rename file!");
                } else {
                    System.out.println("File renamed successfully!");
                    CustomAdapter.this.list.set(i, obj);
                    CustomAdapter.this.path.set(i, String.valueOf(file));
                    CustomAdapter.this.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void add_dialog(String str, final int i) {
        final Dialog dialog = new Dialog(this.mCtx, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_add_view);
        final AppCompatEditText appCompatEditText = (AppCompatEditText) dialog.findViewById(R.id.value);
        ((AppCompatTextView) dialog.findViewById(R.id.title)).setVisibility(View.GONE);
        appCompatEditText.setText(str.replace(".pdf", ""));
        appCompatEditText.setSelection(appCompatEditText.getText().toString().length());
        appCompatEditText.setKeyListener(DigitsKeyListener.getInstance("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 "));
        ((ImageView) dialog.findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (appCompatEditText.getText().toString().trim().equals("")) {
                    appCompatEditText.setError("Enter file name !!!");
                    appCompatEditText.requestFocus();
                    return;
                }
                String obj = appCompatEditText.getText().toString();
                if (!obj.contains(".pdf")) {
                    obj = obj + ".pdf";
                }
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Nithra/ResumeBuilder/" + obj);
                File file2 = new File((String) CustomAdapter.this.path.get(i));
                if (file.exists()) {
                    try {
                        throw new IOException("File already exists!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (!file2.renameTo(file)) {
                    System.out.println("Couldn't rename file!");
                    return;
                }
                System.out.println("File renamed successfully!");
                CustomAdapter.this.list.set(i, obj);
                CustomAdapter.this.path.set(i, String.valueOf(file));
                CustomAdapter.this.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        ((ImageView) dialog.findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView delete;
        public CardView rename;
        public CardView share;
        public TextView textViewDesc;
        public TextView textViewHead;

        public ViewHolder(View view) {
            super(view);
            this.textViewHead = (TextView) view.findViewById(R.id.textViewHead);
            this.textViewDesc = (TextView) view.findViewById(R.id.textViewDesc);
            this.rename = (CardView) view.findViewById(R.id.rename);
            this.share = (CardView) view.findViewById(R.id.share);
            this.delete = (CardView) view.findViewById(R.id.delete);
        }
    }
}
