package nithra.resume.maker.cv.builder.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class LabelViewHelper {
    private static final int DEFAULT_BACKGROUND_COLOR = -1624781376;
    private static final int DEFAULT_DISTANCE = 40;
    private static final int DEFAULT_HEIGHT = 20;
    private static final int DEFAULT_ORIENTATION = 1;
    private static final int DEFAULT_STROKE_COLOR = -1;
    private static final int DEFAULT_STROKE_WIDTH = 1;
    private static final int DEFAULT_TEXT_COLOR = -1;
    private static final int DEFAULT_TEXT_SIZE = 14;
    private static final int DEFAULT_TEXT_STYLE = 0;
    private static final int LEFT_BOTTOM = 3;
    private static final int LEFT_TOP = 1;
    private static final int RIGHT_BOTTOM = 4;
    private static final int RIGHT_TOP = 2;
    private int alpha;
    private int backgroundColor;
    private Context context;
    private int distance;
    private int height;
    private int orientation;
    private Paint rectPaint = new Paint();
    private Path rectPath;
    private Paint rectStrokePaint;
    private int strokeColor;
    private int strokeWidth;
    private String text;
    private Rect textBound;
    private int textColor;
    private Paint textPaint;
    private Path textPath;
    private int textSize;
    private int textStyle;
    private boolean visual;

    public LabelViewHelper(Context context2, AttributeSet attributeSet, int i) {
        this.context = context2;
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R.styleable.LabelView, i, 0);
        this.distance = obtainStyledAttributes.getDimensionPixelSize(1, dip2Px(40.0f));
        this.height = obtainStyledAttributes.getDimensionPixelSize(2, dip2Px(20.0f));
        this.strokeWidth = obtainStyledAttributes.getDimensionPixelSize(5, dip2Px(1.0f));
        this.text = obtainStyledAttributes.getString(6);
        this.backgroundColor = obtainStyledAttributes.getColor(0, DEFAULT_BACKGROUND_COLOR);
        this.strokeColor = obtainStyledAttributes.getColor(4, -1);
        this.textSize = obtainStyledAttributes.getDimensionPixelSize(8, dip2Px(14.0f));
        this.textStyle = obtainStyledAttributes.getInt(9, 0);
        this.textColor = obtainStyledAttributes.getColor(7, -1);
        this.visual = obtainStyledAttributes.getBoolean(10, true);
        this.orientation = obtainStyledAttributes.getInteger(3, 1);
        obtainStyledAttributes.recycle();
        this.rectPaint.setDither(true);
        this.rectPaint.setAntiAlias(true);
        this.rectPaint.setStyle(Paint.Style.FILL);
        this.rectStrokePaint = new Paint();
        this.rectStrokePaint.setDither(true);
        this.rectStrokePaint.setAntiAlias(true);
        this.rectStrokePaint.setStyle(Paint.Style.STROKE);
        this.rectPath = new Path();
        this.rectPath.reset();
        this.textPath = new Path();
        this.textPath.reset();
        this.textPaint = new Paint();
        this.textPaint.setDither(true);
        this.textPaint.setAntiAlias(true);
        this.textPaint.setStrokeJoin(Paint.Join.ROUND);
        this.textPaint.setStrokeCap(Paint.Cap.SQUARE);
        this.textBound = new Rect();
    }

    public void onDraw(Canvas canvas, int i, int i2) {
        if (this.visual && this.text != null) {
            float f = (float) (this.distance + (this.height / 2));
            calcOffset(i, i2);
            this.rectPaint.setColor(this.backgroundColor);
            int i3 = this.alpha;
            if (i3 != 0) {
                this.rectPaint.setAlpha(i3);
            }
            this.rectStrokePaint.setColor(this.strokeColor);
            this.rectStrokePaint.setStrokeWidth((float) this.strokeWidth);
            canvas.drawPath(this.rectPath, this.rectPaint);
            canvas.drawPath(this.rectPath, this.rectStrokePaint);
            this.textPaint.setTextSize((float) this.textSize);
            this.textPaint.setColor(this.textColor);
            Paint paint = this.textPaint;
            String str = this.text;
            paint.getTextBounds(str, 0, str.length(), this.textBound);
            this.textPaint.setTypeface(Typeface.defaultFromStyle(this.textStyle));
            float width = ((f * 1.4142135f) / 2.0f) - ((float) (this.textBound.width() / 2));
            canvas.drawTextOnPath(this.text, this.textPath, width < 0.0f ? 0.0f : width, (float) (this.textBound.height() / 2), this.textPaint);
        }
    }

    private void calcOffset(int i, int i2) {
        int i3 = this.distance;
        int i4 = this.height;
        float f = (float) ((i - i3) - i4);
        float f2 = (float) i;
        float f3 = (float) ((i2 - i3) - i4);
        float f4 = (float) i2;
        float f5 = (float) (i4 / 2);
        switch (this.orientation) {
            case 1:
                this.rectPath.reset();
                this.rectPath.moveTo(0.0f, (float) this.distance);
                this.rectPath.lineTo((float) this.distance, 0.0f);
                this.rectPath.lineTo((float) (this.distance + this.height), 0.0f);
                this.rectPath.lineTo(0.0f, (float) (this.distance + this.height));
                this.rectPath.close();
                this.textPath.reset();
                this.textPath.moveTo(0.0f, ((float) this.distance) + f5);
                this.textPath.lineTo(((float) this.distance) + f5, 0.0f);
                this.textPath.close();
                return;
            case 2:
                this.rectPath.reset();
                this.rectPath.moveTo(f, 0.0f);
                this.rectPath.lineTo(((float) this.height) + f, 0.0f);
                this.rectPath.lineTo(f2, (float) this.distance);
                this.rectPath.lineTo(f2, (float) (this.distance + this.height));
                this.rectPath.close();
                this.textPath.reset();
                this.textPath.moveTo(f + f5, 0.0f);
                this.textPath.lineTo(f2, ((float) this.distance) + f5);
                this.textPath.close();
                return;
            case 3:
                this.rectPath.reset();
                this.rectPath.moveTo(0.0f, f3);
                this.rectPath.lineTo((float) (this.distance + this.height), f4);
                this.rectPath.lineTo((float) this.distance, f4);
                this.rectPath.lineTo(0.0f, ((float) this.height) + f3);
                this.rectPath.close();
                this.textPath.reset();
                this.textPath.moveTo(0.0f, f3 + f5);
                this.textPath.lineTo(((float) this.distance) + f5, f4);
                this.textPath.close();
                return;
            case 4:
                this.rectPath.reset();
                this.rectPath.moveTo(f, f4);
                this.rectPath.lineTo(f2, f3);
                this.rectPath.lineTo(f2, ((float) this.height) + f3);
                this.rectPath.lineTo(((float) this.height) + f, f4);
                this.rectPath.close();
                this.textPath.reset();
                this.textPath.moveTo(f + f5, f4);
                this.textPath.lineTo(f2, f3 + f5);
                this.textPath.close();
                return;
            default:
                return;
        }
    }

    private int dip2Px(float f) {
        return (int) ((f * this.context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private int px2Dip(float f) {
        return (int) ((f / this.context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setLabelHeight(View view, int i) {
        float f = (float) i;
        if (this.height != dip2Px(f)) {
            this.height = dip2Px(f);
            view.invalidate();
        }
    }

    public int getLabelHeight() {
        return px2Dip((float) this.height);
    }

    public void setLabelDistance(View view, int i) {
        float f = (float) i;
        if (this.distance != dip2Px(f)) {
            this.distance = dip2Px(f);
            view.invalidate();
        }
    }

    public int getLabelStrokeWidth() {
        return px2Dip((float) this.strokeWidth);
    }

    public void setLabelStrokeWidth(View view, int i) {
        float f = (float) i;
        if (this.strokeWidth != dip2Px(f)) {
            this.strokeWidth = dip2Px(f);
            view.invalidate();
        }
    }

    public int getLabelDistance() {
        return px2Dip((float) this.distance);
    }

    public boolean isLabelVisual() {
        return this.visual;
    }

    public void setLabelVisual(View view, boolean z) {
        if (this.visual != z) {
            this.visual = z;
            view.invalidate();
        }
    }

    public int getLabelOrientation() {
        return this.orientation;
    }

    public void setLabelOrientation(View view, int i) {
        if (this.orientation != i && i <= 4 && i >= 1) {
            this.orientation = i;
            view.invalidate();
        }
    }

    public int getLabelTextColor() {
        return this.textColor;
    }

    public void setLabelTextColor(View view, int i) {
        if (this.textColor != i) {
            this.textColor = i;
            view.invalidate();
        }
    }

    public int getLabelBackgroundColor() {
        return this.backgroundColor;
    }

    public void setLabelBackgroundColor(View view, int i) {
        if (this.backgroundColor != i) {
            this.backgroundColor = i;
            view.invalidate();
        }
    }

    public int getLabelStrokeColor() {
        return this.strokeColor;
    }

    public void setLabelStrokeColor(View view, int i) {
        if (this.strokeColor != i) {
            this.strokeColor = i;
            view.invalidate();
        }
    }

    public void setLabelBackgroundAlpha(View view, int i) {
        if (this.alpha != i) {
            this.alpha = i;
            view.invalidate();
        }
    }

    public String getLabelText() {
        return this.text;
    }

    public void setLabelText(View view, String str) {
        String str2 = this.text;
        if (str2 == null || !str2.equals(str)) {
            this.text = str;
            view.invalidate();
        }
    }

    public int getLabelTextSize() {
        return px2Dip((float) this.textSize);
    }

    public void setLabelTextSize(View view, int i) {
        if (this.textSize != i) {
            this.textSize = i;
            view.invalidate();
        }
    }

    public int getLabelTextStyle() {
        return this.textStyle;
    }

    public void setLabelTextStyle(View view, int i) {
        if (this.textStyle != i) {
            this.textStyle = i;
            view.invalidate();
        }
    }
}
