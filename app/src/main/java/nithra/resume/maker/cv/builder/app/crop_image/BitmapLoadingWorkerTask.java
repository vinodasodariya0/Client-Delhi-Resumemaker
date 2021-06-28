package nithra.resume.maker.cv.builder.app.crop_image;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import java.lang.ref.WeakReference;
import nithra.resume.maker.cv.builder.app.crop_image.BitmapUtils;


public final class BitmapLoadingWorkerTask extends AsyncTask<Void, Void, BitmapLoadingWorkerTask.Result> {
    private final Context mContext;
    private final WeakReference<CropImageView> mCropImageViewReference;
    private final int mHeight;
    private final Uri mUri;
    private final int mWidth;

    public BitmapLoadingWorkerTask(CropImageView cropImageView, Uri uri) {
        this.mUri = uri;
        this.mCropImageViewReference = new WeakReference<>(cropImageView);
        this.mContext = cropImageView.getContext();
        DisplayMetrics displayMetrics = cropImageView.getResources().getDisplayMetrics();
        double d = displayMetrics.density > 1.0f ? (double) (1.0f / displayMetrics.density) : 1.0d;
        double d2 = (double) displayMetrics.widthPixels;
        Double.isNaN(d2);
        this.mWidth = (int) (d2 * d);
        double d3 = (double) displayMetrics.heightPixels;
        Double.isNaN(d3);
        this.mHeight = (int) (d3 * d);
    }

    public Uri getUri() {
        return this.mUri;
    }


    public Result doInBackground(Void... voidArr) {
        try {
            if (isCancelled()) {
                return null;
            }
            BitmapUtils.BitmapSampled decodeSampledBitmap = BitmapUtils.decodeSampledBitmap(this.mContext, this.mUri, this.mWidth, this.mHeight);
            if (isCancelled()) {
                return null;
            }
            BitmapUtils.RotateBitmapResult rotateBitmapByExif = BitmapUtils.rotateBitmapByExif(decodeSampledBitmap.bitmap, this.mContext, this.mUri);
            return new Result(this.mUri, rotateBitmapByExif.bitmap, decodeSampledBitmap.sampleSize, rotateBitmapByExif.degrees);
        } catch (Exception e) {
            return new Result(this.mUri, e);
        }
    }


    public void onPostExecute(Result result) {
        CropImageView cropImageView;
        if (result != null) {
            boolean z = false;
            if (!isCancelled() && (cropImageView = this.mCropImageViewReference.get()) != null) {
                z = true;
                cropImageView.onSetImageUriAsyncComplete(result);
            }
            if (!z && result.bitmap != null) {
                result.bitmap.recycle();
            }
        }
    }

    public static final class Result {
        public final Bitmap bitmap;
        public final int degreesRotated;
        public final Exception error;
        public final int loadSampleSize;
        public final Uri uri;

        Result(Uri uri2, Bitmap bitmap2, int i, int i2) {
            this.uri = uri2;
            this.bitmap = bitmap2;
            this.loadSampleSize = i;
            this.degreesRotated = i2;
            this.error = null;
        }

        Result(Uri uri2, Exception exc) {
            this.uri = uri2;
            this.bitmap = null;
            this.loadSampleSize = 0;
            this.degreesRotated = 0;
            this.error = exc;
        }
    }
}
