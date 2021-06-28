package nithra.resume.maker.cv.builder.app.showcase;

import android.graphics.Color;
import android.graphics.Typeface;

public class ShowcaseConfig {
    public static final long DEFAULT_DELAY = 0;
    public static final long DEFAULT_FADE_TIME = 300;
    public static final String DEFAULT_MASK_COLOUR = "#dd335075";
    public static final Shape DEFAULT_SHAPE = new CircleShape();
    public static final int DEFAULT_SHAPE_PADDING = 10;
    private int mContentTextColor = Color.parseColor("#ffffff");
    private long mDelay = 0;
    private int mDismissTextColor = Color.parseColor("#ffffff");
    private Typeface mDismissTextStyle = Typeface.DEFAULT_BOLD;
    private long mFadeDuration = 300;
    private int mMaskColour = Color.parseColor(DEFAULT_MASK_COLOUR);
    private Shape mShape = DEFAULT_SHAPE;
    private int mShapePadding = 10;
    private boolean renderOverNav = false;

    public long getDelay() {
        return this.mDelay;
    }

    public void setDelay(long j) {
        this.mDelay = j;
    }

    public int getMaskColor() {
        return this.mMaskColour;
    }

    public void setMaskColor(int i) {
        this.mMaskColour = i;
    }

    public int getContentTextColor() {
        return this.mContentTextColor;
    }

    public void setContentTextColor(int i) {
        this.mContentTextColor = i;
    }

    public int getDismissTextColor() {
        return this.mDismissTextColor;
    }

    public void setDismissTextColor(int i) {
        this.mDismissTextColor = i;
    }

    public Typeface getDismissTextStyle() {
        return this.mDismissTextStyle;
    }

    public void setDismissTextStyle(Typeface typeface) {
        this.mDismissTextStyle = typeface;
    }

    public long getFadeDuration() {
        return this.mFadeDuration;
    }

    public void setFadeDuration(long j) {
        this.mFadeDuration = j;
    }

    public Shape getShape() {
        return this.mShape;
    }

    public void setShape(Shape shape) {
        this.mShape = shape;
    }

    public int getShapePadding() {
        return this.mShapePadding;
    }

    public void setShapePadding(int i) {
        this.mShapePadding = i;
    }

    public boolean getRenderOverNavigationBar() {
        return this.renderOverNav;
    }

    public void setRenderOverNavigationBar(boolean z) {
        this.renderOverNav = z;
    }
}
