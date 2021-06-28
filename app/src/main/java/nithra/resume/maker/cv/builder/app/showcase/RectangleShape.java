package nithra.resume.maker.cv.builder.app.showcase;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class RectangleShape implements Shape {
    private boolean adjustToTarget;
    private boolean fullWidth;
    private int height;
    private Rect rect;
    private int width;

    public RectangleShape(int i, int i2) {
        this.fullWidth = false;
        this.width = 0;
        this.height = 0;
        this.adjustToTarget = true;
        this.width = i;
        this.height = i2;
        init();
    }

    public RectangleShape(Rect rect2) {
        this(rect2, false);
    }

    public RectangleShape(Rect rect2, boolean z) {
        this.fullWidth = false;
        this.width = 0;
        this.height = 0;
        this.adjustToTarget = true;
        this.fullWidth = z;
        this.height = rect2.height();
        if (z) {
            this.width = Integer.MAX_VALUE;
        } else {
            this.width = rect2.width();
        }
        init();
    }

    public boolean isAdjustToTarget() {
        return this.adjustToTarget;
    }

    public void setAdjustToTarget(boolean z) {
        this.adjustToTarget = z;
    }

    private void init() {
        int i = this.width;
        int i2 = this.height;
        this.rect = new Rect((-i) / 2, (-i2) / 2, i / 2, i2 / 2);
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.Shape
    public void draw(Canvas canvas, Paint paint, int i, int i2, int i3) {
        if (!this.rect.isEmpty()) {
            canvas.drawRect((float) ((this.rect.left + i) - i3), (float) ((this.rect.top + i2) - i3), (float) (this.rect.right + i + i3), (float) (this.rect.bottom + i2 + i3), paint);
        }
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.Shape
    public void updateTarget(Target target) {
        if (this.adjustToTarget) {
            Rect bounds = target.getBounds();
            this.height = bounds.height();
            if (this.fullWidth) {
                this.width = Integer.MAX_VALUE;
            } else {
                this.width = bounds.width();
            }
            init();
        }
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.Shape
    public int getWidth() {
        return this.width;
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.Shape
    public int getHeight() {
        return this.height;
    }
}
