package nithra.resume.maker.cv.builder.app.showcase;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.Point;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import nithra.resume.maker.cv.builder.app.showcase.IAnimationFactory;

public class CircularRevealAnimationFactory implements IAnimationFactory {
    private static final String ALPHA = "alpha";
    private static final float INVISIBLE = 0.0f;
    private static final float VISIBLE = 1.0f;
    private final AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();

    @Override // nithra.resume.maker.cv.builder.app.showcase.IAnimationFactory
    @TargetApi(21)
    public void animateInView(View view, Point point, long j, final AnimationStartListener animationStartListener) {
        Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view, point.x, point.y, 0.0f, (float) (view.getWidth() > view.getHeight() ? view.getWidth() : view.getHeight()));
        createCircularReveal.setDuration(j).addListener(new Animator.AnimatorListener() {
            /* class nithra.resume.maker.cv.builder.app.showcase.CircularRevealAnimationFactory.AnonymousClass1 */

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
        createCircularReveal.start();
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.IAnimationFactory
    @TargetApi(21)
    public void animateOutView(View view, Point point, long j, final AnimationEndListener animationEndListener) {
        Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view, point.x, point.y, (float) (view.getWidth() > view.getHeight() ? view.getWidth() : view.getHeight()), 0.0f);
        createCircularReveal.setDuration(j).addListener(new Animator.AnimatorListener() {
            /* class nithra.resume.maker.cv.builder.app.showcase.CircularRevealAnimationFactory.AnonymousClass2 */

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
        createCircularReveal.start();
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.IAnimationFactory
    public void animateTargetToPoint(MaterialShowcaseView materialShowcaseView, Point point) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofInt(materialShowcaseView, "showcaseX", point.x), ObjectAnimator.ofInt(materialShowcaseView, "showcaseY", point.y));
        animatorSet.setInterpolator(this.interpolator);
        animatorSet.start();
    }
}
