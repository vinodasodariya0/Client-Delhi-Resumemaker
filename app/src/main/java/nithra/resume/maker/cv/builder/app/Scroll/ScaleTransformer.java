package nithra.resume.maker.cv.builder.app.Scroll;

import android.view.View;

import androidx.annotation.FloatRange;

public class ScaleTransformer implements DiscreteScrollItemTransformer {
    private float maxMinDiff = 0.2f;
    private float minScale = 0.8f;
    private Pivot pivotX = Pivot.X.CENTER.create();
    private Pivot pivotY = Pivot.Y.CENTER.create();

    @Override // nithra.resume.maker.cv.builder.app.Scroll.DiscreteScrollItemTransformer
    public void transformItem(View view, float f) {
        this.pivotX.setOn(view);
        this.pivotY.setOn(view);
        float abs = this.minScale + (this.maxMinDiff * (1.0f - Math.abs(f)));
        view.setScaleX(abs);
        view.setScaleY(abs);
    }

    public static class Builder {
        private float maxScale = 1.0f;
        private ScaleTransformer transformer = new ScaleTransformer();

        public Builder setMinScale(@FloatRange(from = 0.01d) float f) {
            this.transformer.minScale = f;
            return this;
        }

        public Builder setMaxScale(@FloatRange(from = 0.01d) float f) {
            this.maxScale = f;
            return this;
        }

        public Builder setPivotX(Pivot.X x) {
            return setPivotX(x.create());
        }

        public Builder setPivotX(Pivot pivot) {
            assertAxis(pivot, 0);
            this.transformer.pivotX = pivot;
            return this;
        }

        public Builder setPivotY(Pivot.Y y) {
            return setPivotY(y.create());
        }

        public Builder setPivotY(Pivot pivot) {
            assertAxis(pivot, 1);
            this.transformer.pivotY = pivot;
            return this;
        }

        public ScaleTransformer build() {
            ScaleTransformer scaleTransformer = this.transformer;
            scaleTransformer.maxMinDiff = this.maxScale - scaleTransformer.minScale;
            return this.transformer;
        }

        private void assertAxis(Pivot pivot, int i) {
            if (pivot.getAxis() != i) {
                throw new IllegalArgumentException("You passed a Pivot for wrong axis.");
            }
        }
    }
}
