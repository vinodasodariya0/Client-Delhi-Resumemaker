package nithra.resume.maker.cv.builder.app.Fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;
import nithra.resume.maker.cv.builder.app.LabelViewHelper;

public class LabelImageView extends TextView {
    LabelViewHelper utils;

    public LabelImageView(Context context) {
        this(context, null);
    }

    public LabelImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LabelImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.utils = new LabelViewHelper(context, attributeSet, i);
    }


    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.utils.onDraw(canvas, getMeasuredWidth(), getMeasuredHeight());
    }

    public int getLabelHeight() {
        return this.utils.getLabelHeight();
    }

    public void setLabelHeight(int i) {
        this.utils.setLabelHeight(this, i);
    }

    public int getLabelDistance() {
        return this.utils.getLabelDistance();
    }

    public void setLabelDistance(int i) {
        this.utils.setLabelDistance(this, i);
    }

    public boolean isLabelEnable() {
        return this.utils.isLabelVisual();
    }

    public void setLabelEnable(boolean z) {
        this.utils.setLabelVisual(this, z);
    }

    public int getLabelOrientation() {
        return this.utils.getLabelOrientation();
    }

    public void setLabelOrientation(int i) {
        this.utils.setLabelOrientation(this, i);
    }

    public int getLabelTextColor() {
        return this.utils.getLabelTextColor();
    }

    public void setLabelTextColor(int i) {
        this.utils.setLabelTextColor(this, i);
    }

    public int getLabelBackgroundColor() {
        return this.utils.getLabelBackgroundColor();
    }

    public void setLabelBackgroundColor(int i) {
        this.utils.setLabelBackgroundColor(this, i);
    }

    public String getLabelText() {
        return this.utils.getLabelText();
    }

    public void setLabelText(String str) {
        this.utils.setLabelText(this, str);
    }

    public int getLabelTextSize() {
        return this.utils.getLabelTextSize();
    }

    public void setLabelTextSize(int i) {
        this.utils.setLabelTextSize(this, i);
    }

    public int getLabelTextStyle() {
        return this.utils.getLabelTextStyle();
    }

    public void setLabelTextStyle(int i) {
        this.utils.setLabelTextStyle(this, i);
    }
}
