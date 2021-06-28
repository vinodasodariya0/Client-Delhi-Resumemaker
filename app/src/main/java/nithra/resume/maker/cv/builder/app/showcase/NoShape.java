package nithra.resume.maker.cv.builder.app.showcase;

import android.graphics.Canvas;
import android.graphics.Paint;

public class NoShape implements Shape {
    @Override // nithra.resume.maker.cv.builder.app.showcase.Shape
    public void draw(Canvas canvas, Paint paint, int i, int i2, int i3) {
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.Shape
    public int getHeight() {
        return 0;
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.Shape
    public int getWidth() {
        return 0;
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.Shape
    public void updateTarget(Target target) {
    }
}
