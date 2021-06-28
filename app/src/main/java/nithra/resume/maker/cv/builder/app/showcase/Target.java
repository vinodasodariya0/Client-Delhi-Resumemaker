package nithra.resume.maker.cv.builder.app.showcase;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.dexmaker.dx.io.Opcodes;

public interface Target {
    public static final Target NONE = new Target() {
        /* class nithra.resume.maker.cv.builder.app.showcase.Target.AnonymousClass1 */

        @Override // nithra.resume.maker.cv.builder.app.showcase.Target
        public Point getPoint() {
            return new Point(1000000, 1000000);
        }

        @Override // nithra.resume.maker.cv.builder.app.showcase.Target
        public Rect getBounds() {
            Point point = getPoint();
            return new Rect(point.x - 190, point.y - 190, point.x + Opcodes.DIV_LONG_2ADDR, point.y + Opcodes.DIV_LONG_2ADDR);
        }
    };

    Rect getBounds();

    Point getPoint();
}
