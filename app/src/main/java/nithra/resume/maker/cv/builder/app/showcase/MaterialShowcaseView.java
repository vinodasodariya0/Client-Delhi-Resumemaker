package nithra.resume.maker.cv.builder.app.showcase;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.showcase.IAnimationFactory;

public class MaterialShowcaseView extends FrameLayout implements View.OnTouchListener, View.OnClickListener {
    private IAnimationFactory mAnimationFactory;
    private Bitmap mBitmap;
    private int mBottomMargin = 0;
    private Canvas mCanvas;
    private int mContentBottomMargin;
    private View mContentBox;
    private TextView mContentTextView;
    private int mContentTopMargin;
    private long mDelayInMillis = 0;
    private IDetachedListener mDetachedListener;
    private TextView mDismissButton;
    private boolean mDismissOnTargetTouch = true;
    private boolean mDismissOnTouch = false;
    private Paint mEraser;
    private long mFadeDurationInMillis = 300;
    private int mGravity;
    private Handler mHandler;
    private UpdateOnGlobalLayout mLayoutListener;
    List<IShowcaseListener> mListeners;
    private int mMaskColour;
    private int mOldHeight;
    private int mOldWidth;
    private PrefsManager mPrefsManager;
    private boolean mRenderOverNav = false;
    private Shape mShape;
    private int mShapePadding = 10;
    private boolean mShouldAnimate = true;
    private boolean mShouldRender = false;
    private boolean mSingleUse = false;
    private Target mTarget;
    private boolean mTargetTouchable = false;
    private TextView mTitleTextView;
    private boolean mUseFadeAnimation = false;
    private boolean mWasDismissed = false;
    private int mXPosition;
    private int mYPosition;

    public MaterialShowcaseView(Context context) {
        super(context);
        init(context);
    }

    public MaterialShowcaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public MaterialShowcaseView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    @TargetApi(21)
    public MaterialShowcaseView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context);
    }

    public static void resetSingleUse(Context context, String str) {
        PrefsManager.resetShowcase(context, str);
    }

    public static void resetAll(Context context) {
        PrefsManager.resetAll(context);
    }

    public static int getSoftButtonsBarSizePort(Activity activity) {
        if (Build.VERSION.SDK_INT < 17) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        int i2 = displayMetrics.heightPixels;
        if (i2 > i) {
            return i2 - i;
        }
        return 0;
    }

    private void init(Context context) {
        setWillNotDraw(false);
        this.mListeners = new ArrayList();
        this.mLayoutListener = new UpdateOnGlobalLayout();
        getViewTreeObserver().addOnGlobalLayoutListener(this.mLayoutListener);
        setOnTouchListener(this);
        this.mMaskColour = Color.parseColor(ShowcaseConfig.DEFAULT_MASK_COLOUR);
        setVisibility(INVISIBLE);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.showcase_content, (ViewGroup) this, true);
        this.mContentBox = inflate.findViewById(R.id.content_box);
        this.mTitleTextView = (TextView) inflate.findViewById(R.id.tv_title);
        this.mContentTextView = (TextView) inflate.findViewById(R.id.tv_content);
        this.mDismissButton = (TextView) inflate.findViewById(R.id.tv_dismiss);
        this.mDismissButton.setOnClickListener(this);
    }


    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mShouldRender) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            if (measuredWidth > 0 && measuredHeight > 0) {
                if (this.mBitmap == null || this.mCanvas == null || this.mOldHeight != measuredHeight || this.mOldWidth != measuredWidth) {
                    Bitmap bitmap = this.mBitmap;
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    this.mBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
                    this.mCanvas = new Canvas(this.mBitmap);
                }
                this.mOldWidth = measuredWidth;
                this.mOldHeight = measuredHeight;
                this.mCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                this.mCanvas.drawColor(this.mMaskColour);
                if (this.mEraser == null) {
                    this.mEraser = new Paint();
                    this.mEraser.setColor(-1);
                    this.mEraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                    this.mEraser.setFlags(1);
                }
                this.mShape.draw(this.mCanvas, this.mEraser, this.mXPosition, this.mYPosition, this.mShapePadding);
                canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, (Paint) null);
            }
        }
    }


    public void onDetachedFromWindow() {
        PrefsManager prefsManager;
        super.onDetachedFromWindow();
        if (!this.mWasDismissed && this.mSingleUse && (prefsManager = this.mPrefsManager) != null) {
            prefsManager.resetShowcase();
        }
        notifyOnDismissed();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.mDismissOnTouch) {
            hide();
        }
        if (!this.mTargetTouchable || !this.mTarget.getBounds().contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return true;
        }
        if (!this.mDismissOnTargetTouch) {
            return false;
        }
        hide();
        return false;
    }



    private void notifyOnDisplayed() {
        List<IShowcaseListener> list = this.mListeners;
        if (list != null) {
            for (IShowcaseListener iShowcaseListener : list) {
                iShowcaseListener.onShowcaseDisplayed(this);
            }
        }
    }

    private void notifyOnDismissed() {
        List<IShowcaseListener> list = this.mListeners;
        if (list != null) {
            for (IShowcaseListener iShowcaseListener : list) {
                iShowcaseListener.onShowcaseDismissed(this);
            }
            this.mListeners.clear();
            this.mListeners = null;
        }
        IDetachedListener iDetachedListener = this.mDetachedListener;
        if (iDetachedListener != null) {
            iDetachedListener.onShowcaseDetached(this, this.mWasDismissed);
        }
    }

    public void onClick(View view) {
        hide();
    }

    public void setTarget(Target target) {
        int i;
        this.mTarget = target;
        updateDismissButton();
        if (this.mTarget != null) {
            if (!this.mRenderOverNav && Build.VERSION.SDK_INT >= 21) {
                this.mBottomMargin = getSoftButtonsBarSizePort((Activity) getContext());
                LayoutParams layoutParams = (LayoutParams) getLayoutParams();
                if (!(layoutParams == null || layoutParams.bottomMargin == (i = this.mBottomMargin))) {
                    layoutParams.bottomMargin = i;
                }
            }
            Point point = this.mTarget.getPoint();
            Rect bounds = this.mTarget.getBounds();
            setPosition(point);
            int measuredHeight = getMeasuredHeight();
            int i2 = measuredHeight / 2;
            int i3 = point.y;
            int max = Math.max(bounds.height(), bounds.width()) / 2;
            Shape shape = this.mShape;
            if (shape != null) {
                shape.updateTarget(this.mTarget);
                max = this.mShape.getHeight() / 2;
            }
            if (i3 > i2) {
                this.mContentTopMargin = 0;
                this.mContentBottomMargin = (measuredHeight - i3) + max + this.mShapePadding;
                this.mGravity = 80;
            } else {
                this.mContentTopMargin = i3 + max + this.mShapePadding;
                this.mContentBottomMargin = 0;
                this.mGravity = 48;
            }
        }
        applyLayoutParams();
    }

    private void applyLayoutParams() {
        View view = this.mContentBox;
        if (view != null && view.getLayoutParams() != null) {
            LayoutParams layoutParams = (LayoutParams) this.mContentBox.getLayoutParams();
            boolean z = false;
            int i = layoutParams.bottomMargin;
            int i2 = this.mContentBottomMargin;
            if (i != i2) {
                layoutParams.bottomMargin = i2;
                z = true;
            }
            int i3 = layoutParams.topMargin;
            int i4 = this.mContentTopMargin;
            if (i3 != i4) {
                layoutParams.topMargin = i4;
                z = true;
            }
            int i5 = layoutParams.gravity;
            int i6 = this.mGravity;
            if (i5 != i6) {
                layoutParams.gravity = i6;
                z = true;
            }
            if (z) {
                this.mContentBox.setLayoutParams(layoutParams);
            }
        }
    }


    public void setPosition(Point point) {
        setPosition(point.x, point.y);
    }


    public void setPosition(int i, int i2) {
        this.mXPosition = i;
        this.mYPosition = i2;
    }



    private void setTitleText(CharSequence charSequence) {
        if (this.mTitleTextView != null && !charSequence.equals("")) {
            this.mContentTextView.setAlpha(0.5f);
            this.mTitleTextView.setText(charSequence);
        }
    }



    private void setContentText(CharSequence charSequence) {
        TextView textView = this.mContentTextView;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }



    private void setDismissText(CharSequence charSequence) {
        TextView textView = this.mDismissButton;
        if (textView != null) {
            textView.setText(charSequence);
            updateDismissButton();
        }
    }



    private void setDismissStyle(Typeface typeface) {
        TextView textView = this.mDismissButton;
        if (textView != null) {
            textView.setTypeface(typeface);
            updateDismissButton();
        }
    }



    private void setTitleTextColor(int i) {
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }



    private void setContentTextColor(int i) {
        TextView textView = this.mContentTextView;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }



    private void setDismissTextColor(int i) {
        TextView textView = this.mDismissButton;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }



    private void setShapePadding(int i) {
        this.mShapePadding = i;
    }



    private void setDismissOnTouch(boolean z) {
        this.mDismissOnTouch = z;
    }

    private void setShouldRender(boolean z) {
        this.mShouldRender = z;
    }



    private void setMaskColour(int i) {
        this.mMaskColour = i;
    }



    private void setDelay(long j) {
        this.mDelayInMillis = j;
    }



    private void setFadeDuration(long j) {
        this.mFadeDurationInMillis = j;
    }



    private void setTargetTouchable(boolean z) {
        this.mTargetTouchable = z;
    }



    private void setDismissOnTargetTouch(boolean z) {
        this.mDismissOnTargetTouch = z;
    }



    private void setUseFadeAnimation(boolean z) {
        this.mUseFadeAnimation = z;
    }

    public void addShowcaseListener(IShowcaseListener iShowcaseListener) {
        List<IShowcaseListener> list = this.mListeners;
        if (list != null) {
            list.add(iShowcaseListener);
        }
    }

    public void removeShowcaseListener(MaterialShowcaseSequence materialShowcaseSequence) {
        List<IShowcaseListener> list = this.mListeners;
        if (list != null) {
            list.remove(materialShowcaseSequence);
        }
    }


    public void setDetachedListener(IDetachedListener iDetachedListener) {
        this.mDetachedListener = iDetachedListener;
    }

    public void setShape(Shape shape) {
        this.mShape = shape;
    }

    public void setAnimationFactory(IAnimationFactory iAnimationFactory) {
        this.mAnimationFactory = iAnimationFactory;
    }

    public void setConfig(ShowcaseConfig showcaseConfig) {
        setDelay(showcaseConfig.getDelay());
        setFadeDuration(showcaseConfig.getFadeDuration());
        setContentTextColor(showcaseConfig.getContentTextColor());
        setDismissTextColor(showcaseConfig.getDismissTextColor());
        setDismissStyle(showcaseConfig.getDismissTextStyle());
        setMaskColour(showcaseConfig.getMaskColor());
        setShape(showcaseConfig.getShape());
        setShapePadding(showcaseConfig.getShapePadding());
        setRenderOverNavigationBar(showcaseConfig.getRenderOverNavigationBar());
    }

    private void updateDismissButton() {
        TextView textView = this.mDismissButton;
        if (textView == null) {
            return;
        }
        if (TextUtils.isEmpty(textView.getText())) {
            this.mDismissButton.setVisibility(View.GONE);
        } else {
            this.mDismissButton.setVisibility(View.VISIBLE);
        }
    }

    public boolean hasFired() {
        return this.mPrefsManager.hasFired();
    }



    private void singleUse(String str) {
        this.mSingleUse = true;
        this.mPrefsManager = new PrefsManager(getContext(), str);
    }

    public void removeFromWindow() {
        if (getParent() != null && (getParent() instanceof ViewGroup)) {
            ((ViewGroup) getParent()).removeView(this);
        }
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mBitmap = null;
        }
        this.mEraser = null;
        this.mAnimationFactory = null;
        this.mCanvas = null;
        this.mHandler = null;
        getViewTreeObserver().removeGlobalOnLayoutListener(this.mLayoutListener);
        this.mLayoutListener = null;
        PrefsManager prefsManager = this.mPrefsManager;
        if (prefsManager != null) {
            prefsManager.close();
        }
        this.mPrefsManager = null;
    }

    public boolean show(Activity activity) {
        if (this.mSingleUse) {
            if (this.mPrefsManager.hasFired()) {
                return false;
            }
            this.mPrefsManager.setFired();
        }
        ((ViewGroup) activity.getWindow().getDecorView()).addView(this);
        setShouldRender(true);
        this.mHandler = new Handler();
        this.mHandler.postDelayed(new Runnable() {
            /* class nithra.resume.maker.cv.builder.app.showcase.MaterialShowcaseView.AnonymousClass1 */

            public void run() {
                if (MaterialShowcaseView.this.mShouldAnimate) {
                    MaterialShowcaseView.this.animateIn();
                    return;
                }
                MaterialShowcaseView.this.setVisibility(View.VISIBLE);
                MaterialShowcaseView.this.notifyOnDisplayed();
            }
        }, this.mDelayInMillis);
        updateDismissButton();
        return true;
    }

    public void hide() {
        this.mWasDismissed = true;
        if (this.mShouldAnimate) {
            animateOut();
        } else {
            removeFromWindow();
        }
    }

    public void animateIn() {
        setVisibility(View.INVISIBLE);
        this.mAnimationFactory.animateInView(this, this.mTarget.getPoint(), this.mFadeDurationInMillis, new IAnimationFactory.AnimationStartListener() {
            /* class nithra.resume.maker.cv.builder.app.showcase.MaterialShowcaseView.AnonymousClass2 */

            @Override // nithra.resume.maker.cv.builder.app.showcase.IAnimationFactory.AnimationStartListener
            public void onAnimationStart() {
                MaterialShowcaseView.this.setVisibility(View.VISIBLE);
                MaterialShowcaseView.this.notifyOnDisplayed();
            }
        });
    }

    public void animateOut() {
        this.mAnimationFactory.animateOutView(this, this.mTarget.getPoint(), this.mFadeDurationInMillis, new IAnimationFactory.AnimationEndListener() {
            /* class nithra.resume.maker.cv.builder.app.showcase.MaterialShowcaseView.AnonymousClass3 */

            @Override // nithra.resume.maker.cv.builder.app.showcase.IAnimationFactory.AnimationEndListener
            public void onAnimationEnd() {
                MaterialShowcaseView.this.setVisibility(View.INVISIBLE);
                MaterialShowcaseView.this.removeFromWindow();
            }
        });
    }

    public void resetSingleUse() {
        PrefsManager prefsManager;
        if (this.mSingleUse && (prefsManager = this.mPrefsManager) != null) {
            prefsManager.resetShowcase();
        }
    }



    private void setRenderOverNavigationBar(boolean z) {
        this.mRenderOverNav = z;
    }

    public static class Builder {
        private static final int CIRCLE_SHAPE = 0;
        private static final int NO_SHAPE = 2;
        private static final int RECTANGLE_SHAPE = 1;
        private final Activity activity;
        private boolean fullWidth = false;
        private int shapeType = 0;
        final MaterialShowcaseView showcaseView;

        public Builder(Activity activity2) {
            this.activity = activity2;
            this.showcaseView = new MaterialShowcaseView(activity2);
        }

        public Builder setTarget(View view) {
            this.showcaseView.setTarget(new ViewTarget(view));
            return this;
        }

        public Builder setTarget(Target target) {
            this.showcaseView.setTarget(target);
            return this;
        }

        public Builder setDismissText(int i) {
            return setDismissText(this.activity.getString(i));
        }

        public Builder setDismissText(CharSequence charSequence) {
            this.showcaseView.setDismissText(charSequence);
            return this;
        }

        public Builder setDismissStyle(Typeface typeface) {
            this.showcaseView.setDismissStyle(typeface);
            return this;
        }

        public Builder setContentText(int i) {
            return setContentText(this.activity.getString(i));
        }

        public Builder setContentText(CharSequence charSequence) {
            this.showcaseView.setContentText(charSequence);
            return this;
        }

        public Builder setTitleText(int i) {
            return setTitleText(this.activity.getString(i));
        }

        public Builder setTitleText(CharSequence charSequence) {
            this.showcaseView.setTitleText(charSequence);
            return this;
        }

        public Builder setTargetTouchable(boolean z) {
            this.showcaseView.setTargetTouchable(z);
            return this;
        }

        public Builder setDismissOnTargetTouch(boolean z) {
            this.showcaseView.setDismissOnTargetTouch(z);
            return this;
        }

        public Builder setDismissOnTouch(boolean z) {
            this.showcaseView.setDismissOnTouch(z);
            return this;
        }

        public Builder setMaskColour(int i) {
            this.showcaseView.setMaskColour(i);
            return this;
        }

        public Builder setTitleTextColor(int i) {
            this.showcaseView.setTitleTextColor(i);
            return this;
        }

        public Builder setContentTextColor(int i) {
            this.showcaseView.setContentTextColor(i);
            return this;
        }

        public Builder setDismissTextColor(int i) {
            this.showcaseView.setDismissTextColor(i);
            return this;
        }

        public Builder setDelay(int i) {
            this.showcaseView.setDelay((long) i);
            return this;
        }

        public Builder setFadeDuration(int i) {
            this.showcaseView.setFadeDuration((long) i);
            return this;
        }

        public Builder setListener(IShowcaseListener iShowcaseListener) {
            this.showcaseView.addShowcaseListener(iShowcaseListener);
            return this;
        }

        public Builder singleUse(String str) {
            this.showcaseView.singleUse(str);
            return this;
        }

        public Builder setShape(Shape shape) {
            this.showcaseView.setShape(shape);
            return this;
        }

        public Builder withCircleShape() {
            this.shapeType = 0;
            return this;
        }

        public Builder withoutShape() {
            this.shapeType = 2;
            return this;
        }

        public Builder setShapePadding(int i) {
            this.showcaseView.setShapePadding(i);
            return this;
        }

        public Builder withRectangleShape() {
            return withRectangleShape(false);
        }

        public Builder withRectangleShape(boolean z) {
            this.shapeType = 1;
            this.fullWidth = z;
            return this;
        }

        public Builder renderOverNavigationBar() {
            this.showcaseView.setRenderOverNavigationBar(true);
            return this;
        }

        public Builder useFadeAnimation() {
            this.showcaseView.setUseFadeAnimation(true);
            return this;
        }

        public MaterialShowcaseView build() {
            if (this.showcaseView.mShape == null) {
                switch (this.shapeType) {
                    case 0:
                        MaterialShowcaseView materialShowcaseView = this.showcaseView;
                        materialShowcaseView.setShape(new CircleShape(materialShowcaseView.mTarget));
                        break;
                    case 1:
                        MaterialShowcaseView materialShowcaseView2 = this.showcaseView;
                        materialShowcaseView2.setShape(new RectangleShape(materialShowcaseView2.mTarget.getBounds(), this.fullWidth));
                        break;
                    case 2:
                        this.showcaseView.setShape(new NoShape());
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported shape type: " + this.shapeType);
                }
            }
            if (this.showcaseView.mAnimationFactory == null) {
                if (Build.VERSION.SDK_INT < 21 || this.showcaseView.mUseFadeAnimation) {
                    this.showcaseView.setAnimationFactory(new FadeAnimationFactory());
                } else {
                    this.showcaseView.setAnimationFactory(new CircularRevealAnimationFactory());
                }
            }
            return this.showcaseView;
        }

        public MaterialShowcaseView show() {
            build().show(this.activity);
            return this.showcaseView;
        }
    }


    public class UpdateOnGlobalLayout implements ViewTreeObserver.OnGlobalLayoutListener {
        private UpdateOnGlobalLayout() {
        }

        public void onGlobalLayout() {
            MaterialShowcaseView materialShowcaseView = MaterialShowcaseView.this;
            materialShowcaseView.setTarget(materialShowcaseView.mTarget);
        }
    }
}
