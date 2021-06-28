package nithra.resume.maker.cv.builder.app.Scroll;

import android.content.Context;
import android.content.res.TypedArray;




import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.Scroll.DiscreteScrollLayoutManager;

public class DiscreteScrollView extends RecyclerView {
    private static final int DEFAULT_ORIENTATION = DSVOrientation.HORIZONTAL.ordinal();
    public static final int NO_POSITION = -1;
    private boolean isOverScrollEnabled;
    private DiscreteScrollLayoutManager layoutManager;
    private List<OnItemChangedListener> onItemChangedListeners;
    private List<ScrollStateChangeListener> scrollStateChangeListeners;

    public interface OnItemChangedListener<T extends RecyclerView.ViewHolder> {
        void onCurrentItemChanged(@Nullable T t, int i);
    }

    public interface ScrollListener<T extends RecyclerView.ViewHolder> {
        void onScroll(float f, int i, int i2, @Nullable T t, @Nullable T t2);
    }

    public interface ScrollStateChangeListener<T extends RecyclerView.ViewHolder> {
        void onScroll(float f, int i, int i2, @Nullable T t, @Nullable T t2);

        void onScrollEnd(@NonNull T t, int i);

        void onScrollStart(@NonNull T t, int i);
    }

    public DiscreteScrollView(Context context) {
        super(context);
        init(null);
    }

    public DiscreteScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public DiscreteScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        this.scrollStateChangeListeners = new ArrayList();
        this.onItemChangedListeners = new ArrayList();
        int i = DEFAULT_ORIENTATION;
        boolean z = false;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.DiscreteScrollView);
            i = obtainStyledAttributes.getInt(0, DEFAULT_ORIENTATION);
            obtainStyledAttributes.recycle();
        }
        if (getOverScrollMode() != 2) {
            z = true;
        }
        this.isOverScrollEnabled = z;
        this.layoutManager = new DiscreteScrollLayoutManager(getContext(), new ScrollStateListener(), DSVOrientation.values()[i]);
        setLayoutManager(this.layoutManager);
    }

    @Override // android.support.v7.widget.RecyclerView
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager2) {
        if (layoutManager2 instanceof DiscreteScrollLayoutManager) {
            super.setLayoutManager(layoutManager2);
            return;
        }
        throw new IllegalArgumentException(getContext().getString(R.string.action_settings));
    }

    @Override // android.support.v7.widget.RecyclerView
    public boolean fling(int i, int i2) {
        boolean fling = super.fling(i, i2);
        if (fling) {
            this.layoutManager.onFling(i, i2);
        } else {
            this.layoutManager.returnToCurrentPosition();
        }
        return fling;
    }

    @Nullable
    public RecyclerView.ViewHolder getViewHolder(int i) {
        View findViewByPosition = this.layoutManager.findViewByPosition(i);
        if (findViewByPosition != null) {
            return getChildViewHolder(findViewByPosition);
        }
        return null;
    }

    public int getCurrentItem() {
        return this.layoutManager.getCurrentPosition();
    }

    public void setItemTransformer(DiscreteScrollItemTransformer discreteScrollItemTransformer) {
        this.layoutManager.setItemTransformer(discreteScrollItemTransformer);
    }

    public void setItemTransitionTimeMillis(@IntRange(from = 10) int i) {
        this.layoutManager.setTimeForItemSettle(i);
    }

    public void setSlideOnFling(boolean z) {
        this.layoutManager.setShouldSlideOnFling(z);
    }

    public void setSlideOnFlingThreshold(int i) {
        this.layoutManager.setSlideOnFlingThreshold(i);
    }

    public void setOrientation(DSVOrientation dSVOrientation) {
        this.layoutManager.setOrientation(dSVOrientation);
    }

    public void setOffscreenItems(int i) {
        this.layoutManager.setOffscreenItems(i);
    }

    public void setClampTransformProgressAfter(@IntRange(from = 1) int i) {
        if (i > 1) {
            this.layoutManager.setTransformClampItemCount(i);
            return;
        }
        throw new IllegalArgumentException("must be >= 1");
    }

    public void setOverScrollEnabled(boolean z) {
        this.isOverScrollEnabled = z;
        setOverScrollMode(2);
    }

    public void addScrollStateChangeListener(@NonNull ScrollStateChangeListener<?> scrollStateChangeListener) {
        this.scrollStateChangeListeners.add(scrollStateChangeListener);
    }

    public void addScrollListener(@NonNull ScrollListener<?> scrollListener) {
        addScrollStateChangeListener(new ScrollListenerAdapter(scrollListener));
    }

    public void addOnItemChangedListener(@NonNull OnItemChangedListener<?> onItemChangedListener) {
        this.onItemChangedListeners.add(onItemChangedListener);
    }

    public void removeScrollStateChangeListener(@NonNull ScrollStateChangeListener<?> scrollStateChangeListener) {
        this.scrollStateChangeListeners.remove(scrollStateChangeListener);
    }

    public void removeScrollListener(@NonNull ScrollListener<?> scrollListener) {
        removeScrollStateChangeListener(new ScrollListenerAdapter(scrollListener));
    }

    public void removeItemChangedListener(@NonNull OnItemChangedListener<?> onItemChangedListener) {
        this.onItemChangedListeners.remove(onItemChangedListener);
    }


    
    private void notifyScrollStart(RecyclerView.ViewHolder viewHolder, int i) {
        for (ScrollStateChangeListener scrollStateChangeListener : this.scrollStateChangeListeners) {
            scrollStateChangeListener.onScrollStart(viewHolder, i);
        }
    }


    
    private void notifyScrollEnd(RecyclerView.ViewHolder viewHolder, int i) {
        for (ScrollStateChangeListener scrollStateChangeListener : this.scrollStateChangeListeners) {
            scrollStateChangeListener.onScrollEnd(viewHolder, i);
        }
    }


    
    private void notifyScroll(float f, int i, int i2, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        for (ScrollStateChangeListener scrollStateChangeListener : this.scrollStateChangeListeners) {
            scrollStateChangeListener.onScroll(f, i, i2, viewHolder, viewHolder2);
        }
    }


    
    private void notifyCurrentItemChanged(RecyclerView.ViewHolder viewHolder, int i) {
        for (OnItemChangedListener onItemChangedListener : this.onItemChangedListeners) {
            onItemChangedListener.onCurrentItemChanged(viewHolder, i);
        }
    }

    
    private void notifyCurrentItemChanged() {
        if (!this.onItemChangedListeners.isEmpty()) {
            int currentPosition = this.layoutManager.getCurrentPosition();
            notifyCurrentItemChanged(getViewHolder(currentPosition), currentPosition);
        }
    }


    public class ScrollStateListener implements DiscreteScrollLayoutManager.ScrollStateListener {
        private ScrollStateListener() {
        }

        @Override // nithra.resume.maker.cv.builder.app.Scroll.DiscreteScrollLayoutManager.ScrollStateListener
        public void onIsBoundReachedFlagChange(boolean z) {
            if (DiscreteScrollView.this.isOverScrollEnabled) {
                DiscreteScrollView.this.setOverScrollMode(z ? 0 : 2);
            }
        }

        @Override // nithra.resume.maker.cv.builder.app.Scroll.DiscreteScrollLayoutManager.ScrollStateListener
        public void onScrollStart() {
            int currentPosition;
            RecyclerView.ViewHolder viewHolder;
            if (!DiscreteScrollView.this.scrollStateChangeListeners.isEmpty() && (viewHolder = DiscreteScrollView.this.getViewHolder((currentPosition = DiscreteScrollView.this.layoutManager.getCurrentPosition()))) != null) {
                DiscreteScrollView.this.notifyScrollStart(viewHolder, currentPosition);
            }
        }

        @Override // nithra.resume.maker.cv.builder.app.Scroll.DiscreteScrollLayoutManager.ScrollStateListener
        public void onScrollEnd() {
            int currentPosition;
            RecyclerView.ViewHolder viewHolder;
            if ((!DiscreteScrollView.this.onItemChangedListeners.isEmpty() || !DiscreteScrollView.this.scrollStateChangeListeners.isEmpty()) && (viewHolder = DiscreteScrollView.this.getViewHolder((currentPosition = DiscreteScrollView.this.layoutManager.getCurrentPosition()))) != null) {
                DiscreteScrollView.this.notifyScrollEnd(viewHolder, currentPosition);
                DiscreteScrollView.this.notifyCurrentItemChanged(viewHolder, currentPosition);
            }
        }

        @Override // nithra.resume.maker.cv.builder.app.Scroll.DiscreteScrollLayoutManager.ScrollStateListener
        public void onScroll(float f) {
            int currentItem;
            int nextPosition;
            if (!DiscreteScrollView.this.scrollStateChangeListeners.isEmpty() && (currentItem = DiscreteScrollView.this.getCurrentItem()) != (nextPosition = DiscreteScrollView.this.layoutManager.getNextPosition())) {
                DiscreteScrollView discreteScrollView = DiscreteScrollView.this;
                discreteScrollView.notifyScroll(f, currentItem, nextPosition, discreteScrollView.getViewHolder(currentItem), DiscreteScrollView.this.getViewHolder(nextPosition));
            }
        }

        @Override // nithra.resume.maker.cv.builder.app.Scroll.DiscreteScrollLayoutManager.ScrollStateListener
        public void onCurrentViewFirstLayout() {
            DiscreteScrollView.this.post(new Runnable() {
                /* class nithra.resume.maker.cv.builder.app.Scroll.DiscreteScrollView.ScrollStateListener.AnonymousClass1 */

                public void run() {
                    DiscreteScrollView.this.notifyCurrentItemChanged();
                }
            });
        }

        @Override // nithra.resume.maker.cv.builder.app.Scroll.DiscreteScrollLayoutManager.ScrollStateListener
        public void onDataSetChangeChangedPosition() {
            DiscreteScrollView.this.notifyCurrentItemChanged();
        }
    }
}
