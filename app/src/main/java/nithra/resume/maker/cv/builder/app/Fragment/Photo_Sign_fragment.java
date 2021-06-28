package nithra.resume.maker.cv.builder.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import dalvik.bytecode.Opcodes;
import nithra.resume.maker.cv.builder.app.MainActivity;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.SharedPreference;
import nithra.resume.maker.cv.builder.app.Utils;
import nithra.resume.maker.cv.builder.app.Visible;
import nithra.resume.maker.cv.builder.app.crop_image.CropImage;
import nithra.resume.maker.cv.builder.app.crop_image.CropImageView;

public class Photo_Sign_fragment extends Fragment implements Visible {
    private static int REQUEST_CROP_ICON = 2;
    private static int RESULT_LOAD_IMG = 1;
    static Activity context;
    SQLiteDatabase DB;
    String DIRECTORY = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath() + "/Nithra/ResumeBuilder/");
    String StoredPath = (this.DIRECTORY + this.pic_name + "_sign.png");
    Bitmap bitmap;
    Button btn_get_sign;
    ImageView delete_photo;
    ImageView delete_sign;
    Dialog dialog;
    String fi;
    File fil;
    File file;
    int gallery_sel = 0;
    ImageView image;
    CardView image_card;
    Button mCancel;
    Button mClear;
    LinearLayout mContent;
    Button mGetSign;
    signature mSignature;
    File mypath;
    Button photo_save;
    String pic_name = (getTodaysDate() + "_" + getCurrentTime());
    CardView sign_card;
    ImageView sign_image;
    CardView sign_txt_card;
    SharedPreference sp = new SharedPreference();
    String uniqueId;
    View view;

    public static int getItem(int i) {
        return 0;
    }

    public static float dpToPixels(int i, Context context2) {
        return ((float) i) * context2.getResources().getDisplayMetrics().density;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        context = getActivity();
        View inflate = layoutInflater.inflate(R.layout.fragment_photo_sign, viewGroup, false);
        this.image = (ImageView) inflate.findViewById(R.id.image);
        this.delete_photo = (ImageView) inflate.findViewById(R.id.delete_photo);
        this.image_card = (CardView) inflate.findViewById(R.id.image_card);
        this.photo_save = (Button) inflate.findViewById(R.id.photo_save);
        this.sign_card = (CardView) inflate.findViewById(R.id.sign_card);
        this.sign_image = (ImageView) inflate.findViewById(R.id.sign_image);
        this.sign_txt_card = (CardView) inflate.findViewById(R.id.sign_txt_card);
        this.delete_sign = (ImageView) inflate.findViewById(R.id.delete_sign);
        this.photo_save.setVisibility(View.GONE);
        this.DB = context.openOrCreateDatabase("RESUME_BUILDER", 0, null);
        try {
            File externalStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            new File(externalStorageDirectory.getAbsolutePath() + "/Nithra/ResumeBuilder/").mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        check();
        this.sign_card.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                if (Photo_Sign_fragment.this.sp.getInt(Photo_Sign_fragment.this.getActivity(), "permissiond") == 1) {
                    try {
                        File externalStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                        new File(externalStorageDirectory.getAbsolutePath() + "/Nithra/ResumeBuilder/").mkdirs();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Photo_Sign_fragment.this.dialog_action();
                    return;
                }
                Photo_Sign_fragment.this.PermissionFun();
            }
        });
        this.delete_photo.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Photo_Sign_fragment.context);
                builder.setMessage("Do you want to remove your photo?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialogInterface, int i) {
                        Photo_Sign_fragment.this.sp.putString(Photo_Sign_fragment.context, "picturepath", "");
                        Photo_Sign_fragment.this.delete_photo.setVisibility(View.GONE);
                        SQLiteDatabase sQLiteDatabase = Photo_Sign_fragment.this.DB;
                        sQLiteDatabase.execSQL("UPDATE profile_table SET photo = '" + Photo_Sign_fragment.this.sp.getString(Photo_Sign_fragment.context, "picturepath") + "' where profile_id='" + Photo_Sign_fragment.this.sp.getString(Photo_Sign_fragment.context, "profile_id") + "';");
                        Photo_Sign_fragment.this.image.setImageDrawable(Photo_Sign_fragment.this.getResources().getDrawable(R.drawable.add_image));
                        dialogInterface.cancel();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Photo_Sign_fragment.AnonymousClass2.AnonymousClass2 */

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });
        this.delete_sign.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Photo_Sign_fragment.AnonymousClass3 */

            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Photo_Sign_fragment.context);
                builder.setMessage("Do you want to remove your Sign?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Photo_Sign_fragment.AnonymousClass3.AnonymousClass1 */

                    public void onClick(DialogInterface dialogInterface, int i) {
                        Photo_Sign_fragment.this.sp.putString(Photo_Sign_fragment.context, "signpath", "");
                        Photo_Sign_fragment.this.delete_sign.setVisibility(View.GONE);
                        SQLiteDatabase sQLiteDatabase = Photo_Sign_fragment.this.DB;
                        sQLiteDatabase.execSQL("UPDATE profile_table SET  sign='' where profile_id='" + Photo_Sign_fragment.this.sp.getString(Photo_Sign_fragment.context, "profile_id") + "';");
                        Photo_Sign_fragment.this.sign_txt_card.setVisibility(View.VISIBLE);
                        Photo_Sign_fragment.this.sign_image.setImageDrawable(null);
                        dialogInterface.cancel();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    /* class nithra.resume.maker.cv.builder.app.Fragment.Photo_Sign_fragment.AnonymousClass3.AnonymousClass2 */

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });
        this.image_card.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (Photo_Sign_fragment.this.sp.getInt(Photo_Sign_fragment.this.getActivity(), "permissiond") == 1) {
                    try {
                        File externalStorageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                        new File(externalStorageDirectory.getAbsolutePath() + "/Nithra/ResumeBuilder/").mkdirs();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON).start(Photo_Sign_fragment.context);
                    return;
                }
                Photo_Sign_fragment.this.PermissionFun();
            }
        });
        this.photo_save.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Log.e("000090", Photo_Sign_fragment.this.sp.getString(Photo_Sign_fragment.context, "picturepath"));
                Photo_Sign_fragment.this.hideSoftKeyboard();
                Photo_Sign_fragment.this.update();
                if (!Photo_Sign_fragment.this.sp.getString(Photo_Sign_fragment.context, "picturepath").equals("") && !Photo_Sign_fragment.this.sp.getString(Photo_Sign_fragment.context, "signpath").equals("")) {
                    Utils.toast_center(Photo_Sign_fragment.context, "Photo & sign Added Successfully !!!");
                    Photo_Sign_fragment.this.photo_save.setText("Update");
                } else if (!Photo_Sign_fragment.this.sp.getString(Photo_Sign_fragment.context, "picturepath").equals("")) {
                    Utils.toast_center(Photo_Sign_fragment.context, "Photo Added Successfully !!!");
                    Photo_Sign_fragment.this.photo_save.setText("Update");
                } else if (!Photo_Sign_fragment.this.sp.getString(Photo_Sign_fragment.context, "signpath").equals("")) {
                    Utils.toast_center(Photo_Sign_fragment.context, "Sign Added Successfully !!!");
                    Photo_Sign_fragment.this.photo_save.setText("Update");
                }
            }
        });
        return inflate;
    }

    public void PermissionFun() {
        if (Build.VERSION.SDK_INT < 23) {
            this.sp.putInt(getActivity(), "permissiond", 1);
        } else if (getActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != PackageManager.PERMISSION_GRANTED) {
            final Dialog dialog2 = new Dialog(context, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar);
            dialog2.setContentView(R.layout.permission_dialog_layout);
            dialog2.setCancelable(false);
            TextView textView = (TextView) dialog2.findViewById(R.id.permission_ok);
            TextView textView2 = (TextView) dialog2.findViewById(R.id.txt);
            if (this.sp.getInt(getContext(), "permissiond") == 2) {
                textView2.setText("To add photo or signature please enable Storage Permission in app Settings");
            } else {
                textView2.setText("You need to allow the Storage Permission to add photo or signature in your Resume");
            }
            textView.setOnClickListener(new View.OnClickListener() {
                /* class nithra.resume.maker.cv.builder.app.Fragment.Photo_Sign_fragment.AnonymousClass6 */

                @SuppressLint({"NewApi"})
                public void onClick(View view) {
                    if (Photo_Sign_fragment.this.sp.getInt(Photo_Sign_fragment.context, "permissiond") == 2) {
                        Intent intent = new Intent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.setData(Uri.fromParts("package", Photo_Sign_fragment.context.getPackageName(), null));
                        Photo_Sign_fragment.this.startActivity(intent);
                        dialog2.dismiss();
                        return;
                    }
                    Photo_Sign_fragment.this.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 151);
                    dialog2.dismiss();
                }
            });
            dialog2.show();
        } else {
            this.sp.putInt(getActivity(), "permissiond", 1);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 151) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        } else if (iArr.length > 0 && iArr[0] == 0) {
            this.sp.putInt(getActivity(), "permissiond", 1);
        } else if (iArr[0] != -1) {
        } else {
            if (!shouldShowRequestPermissionRationale(strArr[0])) {
                this.sp.putInt(getActivity(), "permissiond", 2);
            } else if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(strArr[0])) {
                this.sp.putInt(getActivity(), "permissiond", 0);
            }
        }
    }

    public String getTodaysDate() {
        Calendar instance = Calendar.getInstance();
        int i = (instance.get(1) * 10000) + ((instance.get(2) + 1) * 100) + instance.get(5);
        Log.w("DATE:", String.valueOf(i));
        return String.valueOf(i);
    }

    public String getCurrentTime() {
        Calendar instance = Calendar.getInstance();
        int i = (instance.get(11) * 10000) + (instance.get(12) * 100) + instance.get(13);
        Log.w("TIME:", String.valueOf(i));
        return String.valueOf(i);
    }

    public Animation zoomAnim() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.9f, 0.83f, 0.9f, 0.83f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimation.setDuration(500);
        scaleAnimation.setRepeatCount(-1);
        scaleAnimation.setRepeatMode(2);
        return scaleAnimation;
    }

    public void hideSoftKeyboard() {
        if (context.getCurrentFocus() != null) {
            ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showSoftKeyboard(View view2) {
        view2.requestFocus();
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view2, 0);
    }

    public void dialog_action() {
        this.dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        this.dialog.setContentView(R.layout.dialog_signature);
        this.dialog.setCancelable(true);
        this.mContent = (LinearLayout) this.dialog.findViewById(R.id.linearLayout);
        this.mSignature = new signature(context, null);
        this.mSignature.setBackgroundColor(-1);
        this.mContent.addView(this.mSignature, -1, -1);
        this.mClear = (Button) this.dialog.findViewById(R.id.clear);
        this.mGetSign = (Button) this.dialog.findViewById(R.id.getsign);
        this.mGetSign.setEnabled(false);
        this.mCancel = (Button) this.dialog.findViewById(R.id.cancel);
        this.view = this.mContent;
        this.mClear.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Photo_Sign_fragment.AnonymousClass7 */

            public void onClick(View view) {
                Log.v("log_tag", "Panel Cleared");
                Photo_Sign_fragment.this.mSignature.clear();
                Photo_Sign_fragment.this.mGetSign.setEnabled(false);
            }
        });
        this.mGetSign.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Photo_Sign_fragment.AnonymousClass8 */

            public void onClick(View view) {
                Log.v("log_tag", "Panel Saved");
                Photo_Sign_fragment.this.view.setDrawingCacheEnabled(true);
                Photo_Sign_fragment.this.mSignature.save(Photo_Sign_fragment.this.view, Photo_Sign_fragment.this.StoredPath);
                Photo_Sign_fragment.this.sp.putString(Photo_Sign_fragment.context, "signpath", Photo_Sign_fragment.this.StoredPath);
                Photo_Sign_fragment.this.update();
                Photo_Sign_fragment.this.check();
                if (new File(Photo_Sign_fragment.this.StoredPath).exists()) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 3;
                    Bitmap decodeFile = BitmapFactory.decodeFile(Photo_Sign_fragment.this.StoredPath, options);
                    Photo_Sign_fragment.this.sign_image.setBackgroundResource(0);
                    Photo_Sign_fragment.this.sign_image.setImageBitmap(decodeFile);
                    Photo_Sign_fragment.this.delete_sign.setVisibility(View.VISIBLE);
                }
                Photo_Sign_fragment.this.dialog.dismiss();
            }
        });
        this.mCancel.setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Photo_Sign_fragment.AnonymousClass9 */

            public void onClick(View view) {
                Log.v("log_tag", "Panel Canceled");
                Photo_Sign_fragment.this.dialog.dismiss();
            }
        });
        this.dialog.show();
    }

    @Override // nithra.resume.maker.cv.builder.app.Visible
    public void visible() {
        exti_result_dialog();
    }

    public void exti_result_dialog() {
        final Dialog dialog2 = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog2.setContentView(R.layout.dialog_exit);
        MainActivity.load_addFromMain_natt(context, (FrameLayout) dialog2.findViewById(R.id.ads_lay));
        ((TextView) dialog2.findViewById(R.id.yes)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Photo_Sign_fragment.AnonymousClass10 */

            public void onClick(View view) {
                Photo_Sign_fragment.context.finish();
                dialog2.dismiss();
            }
        });
        ((TextView) dialog2.findViewById(R.id.no)).setOnClickListener(new View.OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Fragment.Photo_Sign_fragment.AnonymousClass11 */

            public void onClick(View view) {
                dialog2.dismiss();
            }
        });
        dialog2.setCancelable(false);
        dialog2.setCanceledOnTouchOutside(true);
        dialog2.show();
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

    public void savefile(Uri r5, File r6) {
        String sourceFilename = r5.getPath();
        String AbsolutePath = r6.getAbsolutePath();

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(sourceFilename));
            bos = new BufferedOutputStream(new FileOutputStream(AbsolutePath, false));
            byte[] buf = new byte[1024];
            bis.read(buf);
            do {
                bos.write(buf);
            } while (bis.read(buf) != -1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        check();
        update();
        check();
        super.onResume();
    }

    public void check() {
        SQLiteDatabase sQLiteDatabase = this.DB;
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT photo, sign FROM profile_table where profile_id='" + this.sp.getString(context, "profile_id") + "'", null);
        if (rawQuery.getCount() <= 0) {
            this.image.setBackgroundResource(R.drawable.add_image);
            this.sign_txt_card.setVisibility(View.VISIBLE);
            this.delete_photo.setVisibility(View.GONE);
            this.delete_sign.setVisibility(View.GONE);
            this.photo_save.setText("save");
        } else if (rawQuery.moveToFirst()) {
            String string = rawQuery.getString(rawQuery.getColumnIndex("photo"));
            if (string == null) {
                this.image.setBackgroundResource(R.drawable.add_image);
                this.delete_photo.setVisibility(View.GONE);
            } else if (new File(string).exists()) {
                this.sp.putString(context, "picturepath", string);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 3;
                Bitmap decodeFile = BitmapFactory.decodeFile(string, options);
                this.image.setBackgroundResource(0);
                this.image.setImageBitmap(decodeFile);
                this.photo_save.setText("Update");
                this.delete_photo.setVisibility(View.VISIBLE);
            } else {
                this.sp.putString(context, "picturepath", "");
                this.image.setBackgroundResource(R.drawable.add_image);
                this.delete_photo.setVisibility(View.GONE);
                this.photo_save.setText("SAVE");
            }
            String string2 = rawQuery.getString(rawQuery.getColumnIndex("sign"));
            if (string2 == null) {
                this.sign_txt_card.setVisibility(View.VISIBLE);
                this.delete_sign.setVisibility(View.GONE);
            } else if (new File(string2).exists()) {
                this.sp.putString(context, "signpath", string2);
                BitmapFactory.Options options2 = new BitmapFactory.Options();
                options2.inSampleSize = 3;
                this.sign_image.setImageBitmap(BitmapFactory.decodeFile(string2, options2));
                this.sign_image.setBackgroundResource(0);
                this.delete_sign.setVisibility(View.VISIBLE);
                this.photo_save.setText("Update");
                this.sign_txt_card.setVisibility(View.GONE);
            } else {
                this.sp.putString(context, "signpath", "");
                this.sign_txt_card.setVisibility(View.VISIBLE);
                this.delete_sign.setVisibility(View.GONE);
            }
        }
        rawQuery.close();
    }

    public class signature extends View {
        private static final float HALF_STROKE_WIDTH = 2.5f;
        private static final float STROKE_WIDTH = 5.0f;
        private final RectF dirtyRect = new RectF();
        private float lastTouchX;
        private float lastTouchY;
        private Paint paint = new Paint();
        private Path path = new Path();

        public signature(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.paint.setAntiAlias(true);
            this.paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeJoin(Paint.Join.ROUND);
            this.paint.setStrokeWidth(STROKE_WIDTH);
        }

        public void save(View view, String str) {
            Log.v("log_tag", "Width: " + view.getWidth());
            Log.v("log_tag", "Height: " + view.getHeight());
            if (Photo_Sign_fragment.this.bitmap == null) {
                Photo_Sign_fragment photo_Sign_fragment = Photo_Sign_fragment.this;
                photo_Sign_fragment.bitmap = Bitmap.createBitmap(photo_Sign_fragment.mContent.getWidth(), Photo_Sign_fragment.this.mContent.getHeight(), Bitmap.Config.RGB_565);
            }
            Canvas canvas = new Canvas(Photo_Sign_fragment.this.bitmap);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(str);
                view.draw(canvas);
                Photo_Sign_fragment.this.bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                Log.v("log_tag", e.toString());
            }
        }

        public void clear() {
            this.path.reset();
            invalidate();
        }


        public void onDraw(Canvas canvas) {
            canvas.drawPath(this.path, this.paint);
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            Photo_Sign_fragment.this.mGetSign.setEnabled(true);
            switch (motionEvent.getAction()) {
                case 0:
                    this.path.moveTo(x, y);
                    this.lastTouchX = x;
                    this.lastTouchY = y;
                    return true;
                case 1:
                case 2:
                    resetDirtyRect(x, y);
                    int historySize = motionEvent.getHistorySize();
                    for (int i = 0; i < historySize; i++) {
                        float historicalX = motionEvent.getHistoricalX(i);
                        float historicalY = motionEvent.getHistoricalY(i);
                        expandDirtyRect(historicalX, historicalY);
                        this.path.lineTo(historicalX, historicalY);
                    }
                    this.path.lineTo(x, y);
                    invalidate((int) (this.dirtyRect.left - HALF_STROKE_WIDTH), (int) (this.dirtyRect.top - HALF_STROKE_WIDTH), (int) (this.dirtyRect.right + HALF_STROKE_WIDTH), (int) (this.dirtyRect.bottom + HALF_STROKE_WIDTH));
                    this.lastTouchX = x;
                    this.lastTouchY = y;
                    return true;
                default:
                    debug("Ignored touch event: " + motionEvent.toString());
                    return false;
            }
        }

        private void debug(String str) {
            Log.v("log_tag", str);
        }

        private void expandDirtyRect(float f, float f2) {
            if (f < this.dirtyRect.left) {
                this.dirtyRect.left = f;
            } else if (f > this.dirtyRect.right) {
                this.dirtyRect.right = f;
            }
            if (f2 < this.dirtyRect.top) {
                this.dirtyRect.top = f2;
            } else if (f2 > this.dirtyRect.bottom) {
                this.dirtyRect.bottom = f2;
            }
        }

        private void resetDirtyRect(float f, float f2) {
            this.dirtyRect.left = Math.min(this.lastTouchX, f);
            this.dirtyRect.right = Math.max(this.lastTouchX, f);
            this.dirtyRect.top = Math.min(this.lastTouchY, f2);
            this.dirtyRect.bottom = Math.max(this.lastTouchY, f2);
        }
    }
}
