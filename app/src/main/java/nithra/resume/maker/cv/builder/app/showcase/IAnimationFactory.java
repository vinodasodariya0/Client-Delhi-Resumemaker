package nithra.resume.maker.cv.builder.app.showcase;

import android.graphics.Point;
import android.view.View;

public interface IAnimationFactory {

    public interface AnimationEndListener {
        void onAnimationEnd();
    }

    public interface AnimationStartListener {
        void onAnimationStart();
    }

    void animateInView(View view, Point point, long j, AnimationStartListener animationStartListener);

    void animateOutView(View view, Point point, long j, AnimationEndListener animationEndListener);

    void animateTargetToPoint(MaterialShowcaseView materialShowcaseView, Point point);
}
