package nithra.resume.maker.cv.builder.app.showcase;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class CircleShape implements Shape {
    private boolean adjustToTarget;
    private int radius;

    public CircleShape() {
        this.radius = 200;
        this.adjustToTarget = true;
    }

    public CircleShape(int i) {
        this.radius = 200;
        this.adjustToTarget = true;
        this.radius = i;
    }

    public CircleShape(Rect rect) {
        this(getPreferredRadius(rect));
    }

    public CircleShape(Target target) {
        this(target.getBounds());
    }

    public static int getPreferredRadius(Rect rect) {
        return Math.max(rect.width(), rect.height()) / 2;
    }

    public boolean isAdjustToTarget() {
        return this.adjustToTarget;
    }

    public void setAdjustToTarget(boolean z) {
        this.adjustToTarget = z;
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int i) {
        this.radius = i;
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.Shape
    public void draw(Canvas canvas, Paint paint, int i, int i2, int i3) {
        int i4 = this.radius;
        if (i4 > 0) {
            canvas.drawCircle((float) i, (float) i2, (float) (i4 + i3), paint);
        }
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.Shape
    public void updateTarget(Target target) {
        if (this.adjustToTarget) {
            this.radius = getPreferredRadius(target.getBounds());
        }
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.Shape
    public int getWidth() {
        return this.radius * 2;
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.Shape
    public int getHeight() {
        return this.radius * 2;
    }
}
