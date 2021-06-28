package nithra.resume.maker.cv.builder.app.showcase;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface Shape {
    void draw(Canvas canvas, Paint paint, int i, int i2, int i3);

    int getHeight();

    int getWidth();

    void updateTarget(Target target);
}
