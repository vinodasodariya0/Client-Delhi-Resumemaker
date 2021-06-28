package nithra.resume.maker.cv.builder.app.showcase;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import nithra.resume.maker.cv.builder.app.showcase.IAnimationFactory;

public class FadeAnimationFactory implements IAnimationFactory {
    private static final String ALPHA = "alpha";
    private static final float INVISIBLE = 0.0f;
    private static final float VISIBLE = 1.0f;
    private final AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();

    @Override // nithra.resume.maker.cv.builder.app.showcase.IAnimationFactory
    public void animateInView(View view, Point point, long j, final AnimationStartListener animationStartListener) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, ALPHA, 0.0f, 1.0f);
        ofFloat.setDuration(j).addListener(new Animator.AnimatorListener() {
            /* class nithra.resume.maker.cv.builder.app.showcase.FadeAnimationFactory.AnonymousClass1 */

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                animationStartListener.onAnimationStart();
            }
        });
        ofFloat.start();
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.IAnimationFactory
    public void animateOutView(View view, Point point, long j, final AnimationEndListener animationEndListener) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, ALPHA, 0.0f);
        ofFloat.setDuration(j).addListener(new Animator.AnimatorListener() {
            /* class nithra.resume.maker.cv.builder.app.showcase.FadeAnimationFactory.AnonymousClass2 */

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                animationEndListener.onAnimationEnd();
            }
        });
        ofFloat.start();
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.IAnimationFactory
    public void animateTargetToPoint(MaterialShowcaseView materialShowcaseView, Point point) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofInt(materialShowcaseView, "showcaseX", point.x), ObjectAnimator.ofInt(materialShowcaseView, "showcaseY", point.y));
        animatorSet.setInterpolator(this.interpolator);
        animatorSet.start();
    }
}
