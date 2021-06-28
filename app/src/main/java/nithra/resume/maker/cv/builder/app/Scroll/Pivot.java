package nithra.resume.maker.cv.builder.app.Scroll;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Pivot {
    public static final int AXIS_X = 0;
    public static final int AXIS_Y = 1;
    private static final int PIVOT_CENTER = -1;
    private static final int PIVOT_MAX = -2;
    private int axis;
    private int pivotPoint;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Axis {
    }

    public enum X {
        LEFT {
            @Override // nithra.resume.maker.cv.builder.app.Scroll.Pivot.X
            public Pivot create() {
                return new Pivot(0, 0);
            }
        },
        CENTER {
            @Override // nithra.resume.maker.cv.builder.app.Scroll.Pivot.X
            public Pivot create() {
                return new Pivot(0, -1);
            }
        },
        RIGHT {
            @Override // nithra.resume.maker.cv.builder.app.Scroll.Pivot.X
            public Pivot create() {
                return new Pivot(0, -2);
            }
        };

        public abstract Pivot create();
    }

    public enum Y {
        TOP {
            @Override // nithra.resume.maker.cv.builder.app.Scroll.Pivot.Y
            public Pivot create() {
                return new Pivot(1, 0);
            }
        },
        CENTER {
            @Override // nithra.resume.maker.cv.builder.app.Scroll.Pivot.Y
            public Pivot create() {
                return new Pivot(1, -1);
            }
        },
        BOTTOM {
            @Override // nithra.resume.maker.cv.builder.app.Scroll.Pivot.Y
            public Pivot create() {
                return new Pivot(1, -2);
            }
        };

        public abstract Pivot create();
    }

    public Pivot(int i, int i2) {
        this.axis = i;
        this.pivotPoint = i2;
    }

    public void setOn(View view) {
        int i = this.axis;
        if (i == 0) {
            int i2 = this.pivotPoint;
            switch (i2) {
                case -2:
                    view.setPivotX((float) view.getWidth());
                    return;
                case -1:
                    view.setPivotX(((float) view.getWidth()) * 0.5f);
                    return;
                default:
                    view.setPivotX((float) i2);
                    return;
            }
        } else if (i == 1) {
            int i3 = this.pivotPoint;
            switch (i3) {
                case -2:
                    view.setPivotY((float) view.getHeight());
                    return;
                case -1:
                    view.setPivotY(((float) view.getHeight()) * 0.5f);
                    return;
                default:
                    view.setPivotY((float) i3);
                    return;
            }
        }
    }

    public int getAxis() {
        return this.axis;
    }
}
