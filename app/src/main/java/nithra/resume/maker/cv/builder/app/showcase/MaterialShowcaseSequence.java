package nithra.resume.maker.cv.builder.app.showcase;

import android.app.Activity;
import android.view.View;
import java.util.LinkedList;
import java.util.Queue;
import nithra.resume.maker.cv.builder.app.showcase.MaterialShowcaseView;

public class MaterialShowcaseSequence implements IDetachedListener {
    Activity mActivity;
    private ShowcaseConfig mConfig;
    private OnSequenceItemDismissedListener mOnItemDismissedListener;
    private OnSequenceItemShownListener mOnItemShownListener;
    PrefsManager mPrefsManager;
    private int mSequencePosition;
    Queue<MaterialShowcaseView> mShowcaseQueue;
    private boolean mSingleUse;

    public interface OnSequenceItemDismissedListener {
        void onDismiss(MaterialShowcaseView materialShowcaseView, int i);
    }

    public interface OnSequenceItemShownListener {
        void onShow(MaterialShowcaseView materialShowcaseView, int i);
    }

    public MaterialShowcaseSequence(Activity activity) {
        this.mSingleUse = false;
        this.mSequencePosition = 0;
        this.mOnItemShownListener = null;
        this.mOnItemDismissedListener = null;
        this.mActivity = activity;
        this.mShowcaseQueue = new LinkedList();
    }

    public MaterialShowcaseSequence(Activity activity, String str) {
        this(activity);
        singleUse(str);
    }

    public MaterialShowcaseSequence addSequenceItem(View view, String str, String str2) {
        addSequenceItem(view, "", str, str2);
        return this;
    }

    public MaterialShowcaseSequence addSequenceItem(View view, String str, String str2, String str3) {
        MaterialShowcaseView build = new MaterialShowcaseView.Builder(this.mActivity).setTarget(view).setTitleText(str).setDismissText(str3).setContentText(str2).build();
        ShowcaseConfig showcaseConfig = this.mConfig;
        if (showcaseConfig != null) {
            build.setConfig(showcaseConfig);
        }
        this.mShowcaseQueue.add(build);
        return this;
    }

    public MaterialShowcaseSequence addSequenceItem(MaterialShowcaseView materialShowcaseView) {
        this.mShowcaseQueue.add(materialShowcaseView);
        return this;
    }

    public MaterialShowcaseSequence singleUse(String str) {
        this.mSingleUse = true;
        this.mPrefsManager = new PrefsManager(this.mActivity, str);
        return this;
    }

    public void setOnItemShownListener(OnSequenceItemShownListener onSequenceItemShownListener) {
        this.mOnItemShownListener = onSequenceItemShownListener;
    }

    public void setOnItemDismissedListener(OnSequenceItemDismissedListener onSequenceItemDismissedListener) {
        this.mOnItemDismissedListener = onSequenceItemDismissedListener;
    }

    public boolean hasFired() {
        return this.mPrefsManager.getSequenceStatus() == PrefsManager.SEQUENCE_FINISHED;
    }

    public void start() {
        if (this.mSingleUse) {
            if (!hasFired()) {
                this.mSequencePosition = this.mPrefsManager.getSequenceStatus();
                if (this.mSequencePosition > 0) {
                    for (int i = 0; i < this.mSequencePosition; i++) {
                        this.mShowcaseQueue.poll();
                    }
                }
            } else {
                return;
            }
        }
        if (this.mShowcaseQueue.size() > 0) {
            showNextItem();
        }
    }

    private void showNextItem() {
        if (this.mShowcaseQueue.size() > 0 && !this.mActivity.isFinishing()) {
            MaterialShowcaseView remove = this.mShowcaseQueue.remove();
            remove.setDetachedListener(this);
            remove.show(this.mActivity);
            OnSequenceItemShownListener onSequenceItemShownListener = this.mOnItemShownListener;
            if (onSequenceItemShownListener != null) {
                onSequenceItemShownListener.onShow(remove, this.mSequencePosition);
            }
        } else if (this.mSingleUse) {
            this.mPrefsManager.setFired();
        }
    }

    @Override // nithra.resume.maker.cv.builder.app.showcase.IDetachedListener
    public void onShowcaseDetached(MaterialShowcaseView materialShowcaseView, boolean z) {
        materialShowcaseView.setDetachedListener(null);
        if (z) {
            OnSequenceItemDismissedListener onSequenceItemDismissedListener = this.mOnItemDismissedListener;
            if (onSequenceItemDismissedListener != null) {
                onSequenceItemDismissedListener.onDismiss(materialShowcaseView, this.mSequencePosition);
            }
            PrefsManager prefsManager = this.mPrefsManager;
            if (prefsManager != null) {
                this.mSequencePosition++;
                prefsManager.setSequenceStatus(this.mSequencePosition);
            }
            showNextItem();
        }
    }

    public void setConfig(ShowcaseConfig showcaseConfig) {
        this.mConfig = showcaseConfig;
    }
}
