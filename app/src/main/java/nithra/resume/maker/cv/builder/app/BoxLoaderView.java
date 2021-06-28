package nithra.resume.maker.cv.builder.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class BoxLoaderView extends View {
    private static final int DEFAULT_LOADER_COLOR = -16776961;
    private static final int DEFAULT_SPEED = 10;
    private static final int DEFAULT_STROKE_COLOR = -1;
    private static final int DEFAULT_STROKE_WIDTH = 20;
    private static final int FRAME_RATE = 2;
    private Box box;
    private boolean dirChange = false;
    private Handler handler;
    private int loaderColor;
    private Box outBox;
    private Runnable r = new Runnable() {


        public void run() {
            BoxLoaderView.this.invalidate();
        }
    };
    private int speed;
    private int strokeColor;
    private int strokeWidth;

    public BoxLoaderView(Context context) {
        super(context);
        init(context, null);
    }

    public BoxLoaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.handler = new Handler();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Options, 0, 0);
            this.strokeColor = obtainStyledAttributes.getColor(2, -1);
            this.loaderColor = obtainStyledAttributes.getColor(0, DEFAULT_LOADER_COLOR);
            this.strokeWidth = obtainStyledAttributes.getInt(3, 20);
            this.speed = obtainStyledAttributes.getInt(1, 10);
            obtainStyledAttributes.recycle();
            return;
        }
        this.strokeColor = -1;
        this.loaderColor = DEFAULT_LOADER_COLOR;
        this.strokeWidth = 20;
        this.speed = 10;
    }

    public void setSpeed(int i) {
        this.speed = i;
    }

    public void setStrokeWidth(int i) {
        this.strokeWidth = i;
    }

    public void setStrokeColor(int i) {
        this.strokeColor = i;
    }

    public void setLoaderColor(int i) {
        this.loaderColor = i;
    }


    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.outBox == null) {
            this.outBox = new Box(i, i2, i3, i4, this.strokeColor, 10);
            this.outBox.getPaint().setStrokeWidth((float) this.strokeWidth);
        }
        if (this.box == null) {
            int i5 = this.strokeWidth;
            this.box = new Box(i + i5, i2 + i5, (i3 / 2) - i5, (i4 / 2) - i5, this.loaderColor, 10);
            this.box.setDx(this.speed);
            this.box.setDy(this.speed);
        }
    }


    public void onDraw(Canvas canvas) {
        canvas.drawRect((float) this.outBox.getLeft(), (float) this.outBox.getTop(), (float) this.outBox.getRight(), (float) this.outBox.getBottom(), this.outBox.getPaint());
        this.dirChange = this.box.bounce(canvas, this.strokeWidth);
        rectifyBoundaries(canvas, this.box);
        canvas.drawRect((float) this.box.getLeft(), (float) this.box.getTop(), (float) this.box.getRight(), (float) this.box.getBottom(), this.box.getPaint());
        this.handler.postDelayed(this.r, this.dirChange ? 40 : 2);
    }

    private void rectifyBoundaries(Canvas canvas, Box box2) {
        if (box2.getLeft() < this.strokeWidth) {
            box2.getrect().left = this.strokeWidth;
        }
        if (box2.getTop() < this.strokeWidth) {
            box2.getrect().top = this.strokeWidth;
        }
        if (box2.getRight() > canvas.getWidth() - this.strokeWidth) {
            box2.getrect().right = canvas.getWidth() - this.strokeWidth;
        }
        if (box2.getBottom() > canvas.getHeight() - this.strokeWidth) {
            box2.getrect().bottom = canvas.getHeight() - this.strokeWidth;
        }
    }


    public static class Box {
        private int c;
        private int dir;
        private int dx;
        private int dy;
        private Paint paint = new Paint();
        private int r;
        private Rect rect;

        public Box(int i, int i2, int i3, int i4, int i5, int i6) {
            this.rect = new Rect(i, i2, i3, i4);
            this.c = i5;
            this.r = i6;
            this.paint.setColor(this.c);
            this.dx = 0;
            this.dy = 0;
            this.dir = 0;
        }

        public void setColor(int i) {
            this.c = i;
        }

        public void goTo(int i, int i2, int i3, int i4) {
            Rect rect2 = this.rect;
            rect2.left = i;
            rect2.top = i2;
            rect2.right = i3;
            rect2.bottom = i4;
        }

        public void setDx(int i) {
            this.dx = i;
        }

        public void setDy(int i) {
            this.dy = i;
        }

        public int getLeft() {
            return this.rect.left;
        }

        public int getTop() {
            return this.rect.top;
        }

        public int getRight() {
            return this.rect.right;
        }

        public int getBottom() {
            return this.rect.bottom;
        }

        public int getRadius() {
            return this.r;
        }

        public Paint getPaint() {
            return this.paint;
        }

        public void increaseRight() {
            this.rect.right += this.dx;
        }

        public void decreaseRight() {
            this.rect.right -= this.dx;
        }

        public void increaseLeft() {
            this.rect.left += this.dx;
        }

        public void decreaseLeft() {
            this.rect.left -= this.dx;
        }

        public void increaseTop() {
            this.rect.top += this.dy;
        }

        public void decreaseTop() {
            this.rect.top -= this.dy;
        }

        public void increaseBottom() {
            this.rect.bottom += this.dy;
        }

        public void decreaseBottom() {
            this.rect.bottom -= this.dy;
        }

        public Rect getrect() {
            return this.rect;
        }

        public boolean bounce(Canvas canvas, int i) {
            switch (this.dir) {
                case 0:
                    if (this.rect.right < canvas.getWidth() - i) {
                        increaseRight();
                        break;
                    } else {
                        increaseLeft();
                        if (this.rect.left > canvas.getWidth() / 2) {
                            this.dir++;
                            return true;
                        }
                    }
                    break;
                case 1:
                    if (this.rect.bottom < canvas.getHeight() - i) {
                        increaseBottom();
                        break;
                    } else {
                        increaseTop();
                        if (this.rect.top > canvas.getHeight() / 2) {
                            this.dir++;
                            return true;
                        }
                    }
                    break;
                case 2:
                    if (this.rect.left > i) {
                        decreaseLeft();
                        break;
                    } else {
                        decreaseRight();
                        if (this.rect.right < canvas.getWidth() / 2) {
                            this.dir++;
                            return true;
                        }
                    }
                    break;
                case 3:
                    if (this.rect.top > i) {
                        decreaseTop();
                        break;
                    } else {
                        decreaseBottom();
                        if (this.rect.bottom < canvas.getHeight() / 2) {
                            this.dir = 0;
                            return true;
                        }
                    }
                    break;
            }
            return false;
        }
    }
}
