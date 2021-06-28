package nithra.resume.maker.cv.builder.app.Fragment;

import android.content.Context;


import android.util.AttributeSet;
import android.view.View;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ExpandableHeightRecyclerView extends RecyclerView {
    boolean expanded = false;

    public ExpandableHeightRecyclerView(Context context) {
        super(context);
    }

    public ExpandableHeightRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ExpandableHeightRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    public void setExpanded(boolean z) {
        this.expanded = z;
    }

    @Override // android.support.v7.widget.RecyclerView
    public void onMeasure(int i, int i2) {
        if (isExpanded()) {
            super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(ViewCompat.MEASURED_SIZE_MASK, Integer.MIN_VALUE));
            getLayoutParams().height = getMeasuredHeight();
            return;
        }
        super.onMeasure(i, i2);
    }
}
