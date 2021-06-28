package nithra.resume.maker.cv.builder.app.showcase;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

public class ViewTarget implements Target {
    private final View mView;

    public ViewTarget(View view) {
        this.mView = view;
    }

    public ViewTarget(int i, Activity activity) {
        this.mView = activity.findViewById(i);
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.Target
    public Point getPoint() {
        int[] iArr = new int[2];
        this.mView.getLocationInWindow(iArr);
        return new Point(iArr[0] + (this.mView.getWidth() / 2), iArr[1] + (this.mView.getHeight() / 2));
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.Target
    public Rect getBounds() {
        int[] iArr = new int[2];
        this.mView.getLocationInWindow(iArr);
        return new Rect(iArr[0], iArr[1], iArr[0] + this.mView.getMeasuredWidth(), iArr[1] + this.mView.getMeasuredHeight());
    }
}
