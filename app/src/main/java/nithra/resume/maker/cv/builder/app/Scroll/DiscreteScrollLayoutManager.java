package nithra.resume.maker.cv.builder.app.Scroll;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;


import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityRecordCompat;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import nithra.resume.maker.cv.builder.app.Scroll.DSVOrientation;


public class DiscreteScrollLayoutManager extends RecyclerView.LayoutManager {
    private static final int DEFAULT_FLING_THRESHOLD = 2100;
    private static final int DEFAULT_TIME_FOR_ITEM_SETTLE = 300;
    private static final int DEFAULT_TRANSFORM_CLAMP_ITEM_COUNT = 1;
    private static final String EXTRA_POSITION = "extra_position";
    static final int NO_POSITION = -1;
    protected static final float SCROLL_TO_SNAP_TO_ANOTHER_ITEM = 0.6f;
    protected int childHalfHeight;
    protected int childHalfWidth;
    private Context context;
    protected int currentPosition = -1;
    protected int currentScrollState;
    protected Point currentViewCenter = new Point();
    private boolean dataSetChangeShiftedPosition;
    protected SparseArray<View> detachedCache = new SparseArray<>();
    protected int extraLayoutSpace;
    private int flingThreshold = DEFAULT_FLING_THRESHOLD;
    protected boolean isFirstOrEmptyLayout;
    private DiscreteScrollItemTransformer itemTransformer;
    private int offscreenItems;
    private DSVOrientation.Helper orientationHelper;
    protected int pendingPosition = -1;
    protected int pendingScroll;
    protected Point recyclerCenter = new Point();
    private RecyclerViewProxy recyclerViewProxy;
    @NonNull
    private final ScrollStateListener scrollStateListener;
    protected int scrollToChangeCurrent;
    protected int scrolled;
    private boolean shouldSlideOnFling = false;
    private int timeForItemSettle = DEFAULT_TIME_FOR_ITEM_SETTLE;
    private int transformClampItemCount;
    protected Point viewCenterIterator = new Point();
    private int viewHeight;
    private int viewWidth;

    public interface ScrollStateListener {
        void onCurrentViewFirstLayout();

        void onDataSetChangeChangedPosition();

        void onIsBoundReachedFlagChange(boolean z);

        void onScroll(float f);

        void onScrollEnd();

        void onScrollStart();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public DiscreteScrollLayoutManager(@NonNull Context context2, @NonNull ScrollStateListener scrollStateListener2, @NonNull DSVOrientation dSVOrientation) {
        this.context = context2;
        this.scrollStateListener = scrollStateListener2;
        this.orientationHelper = dSVOrientation.createHelper();
        this.recyclerViewProxy = new RecyclerViewProxy(this);
        this.transformClampItemCount = 1;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        boolean z = false;
        if (state.getItemCount() == 0) {
            this.recyclerViewProxy.removeAndRecycleAllViews(recycler);
            this.pendingPosition = -1;
            this.currentPosition = -1;
            this.pendingScroll = 0;
            this.scrolled = 0;
            return;
        }
        ensureValidPosition(state);
        if (!state.isMeasuring()) {
            checkRecyclerViewDimensionsChanged();
        }
        if (!this.isFirstOrEmptyLayout) {
            if (this.recyclerViewProxy.getChildCount() == 0) {
                z = true;
            }
            this.isFirstOrEmptyLayout = z;
            if (this.isFirstOrEmptyLayout) {
                initChildDimensions(recycler);
            }
        }
        updateRecyclerDimensions();
        this.recyclerViewProxy.detachAndScrapAttachedViews(recycler);
        fill(recycler);
        applyItemTransformToChildren();
    }

    private void ensureValidPosition(RecyclerView.State state) {
        int i = this.currentPosition;
        if (i == -1 || i >= state.getItemCount()) {
            this.currentPosition = 0;
        }
    }

    private void checkRecyclerViewDimensionsChanged() {
        if (this.recyclerViewProxy.getWidth() != this.viewWidth || this.recyclerViewProxy.getHeight() != this.viewHeight) {
            this.viewWidth = this.recyclerViewProxy.getWidth();
            this.viewHeight = this.recyclerViewProxy.getHeight();
            this.recyclerViewProxy.removeAllViews();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        if (this.isFirstOrEmptyLayout) {
            this.scrollStateListener.onCurrentViewFirstLayout();
            this.isFirstOrEmptyLayout = false;
        } else if (this.dataSetChangeShiftedPosition) {
            this.scrollStateListener.onDataSetChangeChangedPosition();
            this.dataSetChangeShiftedPosition = false;
        }
    }


    public void initChildDimensions(RecyclerView.Recycler recycler) {
        View measuredChildForAdapterPosition = this.recyclerViewProxy.getMeasuredChildForAdapterPosition(0, recycler);
        int measuredWidthWithMargin = this.recyclerViewProxy.getMeasuredWidthWithMargin(measuredChildForAdapterPosition);
        int measuredHeightWithMargin = this.recyclerViewProxy.getMeasuredHeightWithMargin(measuredChildForAdapterPosition);
        this.childHalfWidth = measuredWidthWithMargin / 2;
        this.childHalfHeight = measuredHeightWithMargin / 2;
        this.scrollToChangeCurrent = this.orientationHelper.getDistanceToChangeCurrent(measuredWidthWithMargin, measuredHeightWithMargin);
        this.extraLayoutSpace = this.scrollToChangeCurrent * this.offscreenItems;
        this.recyclerViewProxy.detachAndScrapView(measuredChildForAdapterPosition, recycler);
    }


    public void updateRecyclerDimensions() {
        this.recyclerCenter.set(this.recyclerViewProxy.getWidth() / 2, this.recyclerViewProxy.getHeight() / 2);
    }


    public void fill(RecyclerView.Recycler recycler) {
        cacheAndDetachAttachedViews();
        this.orientationHelper.setCurrentViewCenter(this.recyclerCenter, this.scrolled, this.currentViewCenter);
        int viewEnd = this.orientationHelper.getViewEnd(this.recyclerViewProxy.getWidth(), this.recyclerViewProxy.getHeight());
        if (isViewVisible(this.currentViewCenter, viewEnd)) {
            layoutView(recycler, this.currentPosition, this.currentViewCenter);
        }
        layoutViews(recycler, Direction.START, viewEnd);
        layoutViews(recycler, Direction.END, viewEnd);
        recycleDetachedViewsAndClearCache(recycler);
    }

    private void layoutViews(RecyclerView.Recycler recycler, Direction direction, int i) {
        int applyTo = direction.applyTo(1);
        int i2 = this.pendingPosition;
        boolean z = i2 == -1 || !direction.sameAs(i2 - this.currentPosition);
        this.viewCenterIterator.set(this.currentViewCenter.x, this.currentViewCenter.y);
        int i3 = this.currentPosition;
        while (true) {
            i3 += applyTo;
            if (isInBounds(i3)) {
                if (i3 == this.pendingPosition) {
                    z = true;
                }
                this.orientationHelper.shiftViewCenter(direction, this.scrollToChangeCurrent, this.viewCenterIterator);
                if (isViewVisible(this.viewCenterIterator, i)) {
                    layoutView(recycler, i3, this.viewCenterIterator);
                } else if (z) {
                    return;
                }
            } else {
                return;
            }
        }
    }


    public void layoutView(RecyclerView.Recycler recycler, int i, Point point) {
        if (i >= 0) {
            View view = this.detachedCache.get(i);
            if (view == null) {
                this.recyclerViewProxy.layoutDecoratedWithMargins(this.recyclerViewProxy.getMeasuredChildForAdapterPosition(i, recycler), point.x - this.childHalfWidth, point.y - this.childHalfHeight, point.x + this.childHalfWidth, point.y + this.childHalfHeight);
                return;
            }
            this.recyclerViewProxy.attachView(view);
            this.detachedCache.remove(i);
        }
    }


    public void cacheAndDetachAttachedViews() {
        this.detachedCache.clear();
        for (int i = 0; i < this.recyclerViewProxy.getChildCount(); i++) {
            View childAt = this.recyclerViewProxy.getChildAt(i);
            this.detachedCache.put(this.recyclerViewProxy.getPosition(childAt), childAt);
        }
        for (int i2 = 0; i2 < this.detachedCache.size(); i2++) {
            this.recyclerViewProxy.detachView(this.detachedCache.valueAt(i2));
        }
    }


    public void recycleDetachedViewsAndClearCache(RecyclerView.Recycler recycler) {
        for (int i = 0; i < this.detachedCache.size(); i++) {
            this.recyclerViewProxy.recycleView(this.detachedCache.valueAt(i), recycler);
        }
        this.detachedCache.clear();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        int i3 = this.currentPosition;
        if (i3 == -1) {
            i3 = 0;
        } else if (i3 >= i) {
            i3 = Math.min(i3 + i2, this.recyclerViewProxy.getItemCount() - 1);
        }
        onNewPosition(i3);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        int i3 = this.currentPosition;
        if (this.recyclerViewProxy.getItemCount() == 0) {
            i3 = -1;
        } else {
            int i4 = this.currentPosition;
            if (i4 >= i) {
                if (i4 < i + i2) {
                    this.currentPosition = -1;
                }
                i3 = Math.max(0, this.currentPosition - i2);
            }
        }
        onNewPosition(i3);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView recyclerView) {
        this.currentPosition = Math.min(Math.max(0, this.currentPosition), this.recyclerViewProxy.getItemCount() - 1);
        this.dataSetChangeShiftedPosition = true;
    }

    private void onNewPosition(int i) {
        if (this.currentPosition != i) {
            this.currentPosition = i;
            this.dataSetChangeShiftedPosition = true;
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(i, recycler);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(i, recycler);
    }


    public int scrollBy(int i, RecyclerView.Recycler recycler) {
        Direction fromDelta;
        int calculateAllowedScrollIn;
        if (this.recyclerViewProxy.getChildCount() == 0 || (calculateAllowedScrollIn = calculateAllowedScrollIn((fromDelta = Direction.fromDelta(i)))) <= 0) {
            return 0;
        }
        int applyTo = fromDelta.applyTo(Math.min(calculateAllowedScrollIn, Math.abs(i)));
        this.scrolled += applyTo;
        int i2 = this.pendingScroll;
        if (i2 != 0) {
            this.pendingScroll = i2 - applyTo;
        }
        this.orientationHelper.offsetChildren(-applyTo, this.recyclerViewProxy);
        if (this.orientationHelper.hasNewBecomeVisible(this)) {
            fill(recycler);
        }
        notifyScroll();
        applyItemTransformToChildren();
        return applyTo;
    }


    public void applyItemTransformToChildren() {
        if (this.itemTransformer != null) {
            int i = this.scrollToChangeCurrent * this.transformClampItemCount;
            for (int i2 = 0; i2 < this.recyclerViewProxy.getChildCount(); i2++) {
                View childAt = this.recyclerViewProxy.getChildAt(i2);
                this.itemTransformer.transformItem(childAt, getCenterRelativePositionOf(childAt, i));
            }
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        if (this.currentPosition != i) {
            this.currentPosition = i;
            this.recyclerViewProxy.requestLayout();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        if (this.currentPosition != i && this.pendingPosition == -1) {
            startSmoothPendingScroll(i);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.orientationHelper.canScrollHorizontally();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.orientationHelper.canScrollVertically();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onScrollStateChanged(int i) {
        int i2 = this.currentScrollState;
        if (i2 == 0 && i2 != i) {
            this.scrollStateListener.onScrollStart();
        }
        if (i == 0) {
            if (onScrollEnd()) {
                this.scrollStateListener.onScrollEnd();
            } else {
                return;
            }
        } else if (i == 1) {
            onDragStart();
        }
        this.currentScrollState = i;
    }

    private boolean onScrollEnd() {
        int i = this.pendingPosition;
        if (i != -1) {
            this.currentPosition = i;
            this.pendingPosition = -1;
            this.scrolled = 0;
        }
        Direction fromDelta = Direction.fromDelta(this.scrolled);
        if (Math.abs(this.scrolled) == this.scrollToChangeCurrent) {
            this.currentPosition += fromDelta.applyTo(1);
            this.scrolled = 0;
        }
        if (isAnotherItemCloserThanCurrent()) {
            this.pendingScroll = getHowMuchIsLeftToScroll(this.scrolled);
        } else {
            this.pendingScroll = -this.scrolled;
        }
        if (this.pendingScroll == 0) {
            return true;
        }
        startSmoothPendingScroll();
        return false;
    }

    private void onDragStart() {
        if (Math.abs(this.scrolled) > this.scrollToChangeCurrent) {
            int i = this.scrolled;
            int i2 = this.scrollToChangeCurrent;
            int i3 = i / i2;
            this.currentPosition += i3;
            this.scrolled = i - (i3 * i2);
        }
        if (isAnotherItemCloserThanCurrent()) {
            this.currentPosition += Direction.fromDelta(this.scrolled).applyTo(1);
            this.scrolled = -getHowMuchIsLeftToScroll(this.scrolled);
        }
        this.pendingPosition = -1;
        this.pendingScroll = 0;
    }

    public void onFling(int i, int i2) {
        int flingVelocity = this.orientationHelper.getFlingVelocity(i, i2);
        boolean z = true;
        int checkNewOnFlingPositionIsInBounds = checkNewOnFlingPositionIsInBounds(this.currentPosition + Direction.fromDelta(flingVelocity).applyTo(this.shouldSlideOnFling ? Math.abs(flingVelocity / this.flingThreshold) : 1));
        if (!(flingVelocity * this.scrolled >= 0) || !isInBounds(checkNewOnFlingPositionIsInBounds)) {
            z = false;
        }
        if (z) {
            startSmoothPendingScroll(checkNewOnFlingPositionIsInBounds);
        } else {
            returnToCurrentPosition();
        }
    }

    public void returnToCurrentPosition() {
        this.pendingScroll = -this.scrolled;
        if (this.pendingScroll != 0) {
            startSmoothPendingScroll();
        }
    }


    public int calculateAllowedScrollIn(Direction direction) {
        int i;
        int i2 = this.pendingScroll;
        if (i2 != 0) {
            return Math.abs(i2);
        }
        boolean z = true;
        int i3 = 0;
        boolean z2 = direction.applyTo(this.scrolled) > 0;
        if (direction == Direction.START && this.currentPosition == 0) {
            if (this.scrolled != 0) {
                z = false;
            }
            if (!z) {
                i3 = Math.abs(this.scrolled);
            }
        } else if (direction == Direction.END && this.currentPosition == this.recyclerViewProxy.getItemCount() - 1) {
            if (this.scrolled != 0) {
                z = false;
            }
            if (!z) {
                i3 = Math.abs(this.scrolled);
            }
        } else {
            if (z2) {
                i = this.scrollToChangeCurrent - Math.abs(this.scrolled);
            } else {
                i = this.scrollToChangeCurrent + Math.abs(this.scrolled);
            }
            i3 = i;
            z = false;
        }
        this.scrollStateListener.onIsBoundReachedFlagChange(z);
        return i3;
    }

    private void startSmoothPendingScroll() {
        DiscreteLinearSmoothScroller discreteLinearSmoothScroller = new DiscreteLinearSmoothScroller(this.context);
        discreteLinearSmoothScroller.setTargetPosition(this.currentPosition);
        this.recyclerViewProxy.startSmoothScroll(discreteLinearSmoothScroller);
    }

    private void startSmoothPendingScroll(int i) {
        int i2 = this.currentPosition;
        if (i2 != i) {
            this.pendingScroll = -this.scrolled;
            this.pendingScroll += Direction.fromDelta(i - i2).applyTo(Math.abs(i - this.currentPosition) * this.scrollToChangeCurrent);
            this.pendingPosition = i;
            startSmoothPendingScroll();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    private int computeScrollOffset(RecyclerView.State state) {
        int computeScrollExtent = computeScrollExtent(state);
        return (this.currentPosition * computeScrollExtent) + ((int) ((((float) this.scrolled) / ((float) this.scrollToChangeCurrent)) * ((float) computeScrollExtent)));
    }

    private int computeScrollExtent(RecyclerView.State state) {
        if (getItemCount() == 0) {
            return 0;
        }
        return (int) (((float) computeScrollRange(state)) / ((float) getItemCount()));
    }

    private int computeScrollRange(RecyclerView.State state) {
        if (getItemCount() == 0) {
            return 0;
        }
        return this.scrollToChangeCurrent * (getItemCount() - 1);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        this.pendingPosition = -1;
        this.pendingScroll = 0;
        this.scrolled = 0;
        this.currentPosition = 0;
        this.recyclerViewProxy.removeAllViews();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        int i = this.pendingPosition;
        if (i != -1) {
            this.currentPosition = i;
        }
        bundle.putInt(EXTRA_POSITION, this.currentPosition);
        return bundle;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        this.currentPosition = ((Bundle) parcelable).getInt(EXTRA_POSITION);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public int getNextPosition() {
        int i = this.scrolled;
        if (i == 0) {
            return this.currentPosition;
        }
        int i2 = this.pendingPosition;
        if (i2 != -1) {
            return i2;
        }
        return this.currentPosition + Direction.fromDelta(i).applyTo(1);
    }

    public void setItemTransformer(DiscreteScrollItemTransformer discreteScrollItemTransformer) {
        this.itemTransformer = discreteScrollItemTransformer;
    }

    public void setTimeForItemSettle(int i) {
        this.timeForItemSettle = i;
    }

    public void setOffscreenItems(int i) {
        this.offscreenItems = i;
        this.extraLayoutSpace = this.scrollToChangeCurrent * i;
        this.recyclerViewProxy.requestLayout();
    }

    public void setTransformClampItemCount(int i) {
        this.transformClampItemCount = i;
        applyItemTransformToChildren();
    }

    public void setOrientation(DSVOrientation dSVOrientation) {
        this.orientationHelper = dSVOrientation.createHelper();
        this.recyclerViewProxy.removeAllViews();
        this.recyclerViewProxy.requestLayout();
    }

    public void setShouldSlideOnFling(boolean z) {
        this.shouldSlideOnFling = z;
    }

    public void setSlideOnFlingThreshold(int i) {
        this.flingThreshold = i;
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (this.recyclerViewProxy.getChildCount() > 0) {
            AccessibilityRecordCompat asRecord = AccessibilityEventCompat.asRecord(accessibilityEvent);
            asRecord.setFromIndex(getPosition(getFirstChild()));
            asRecord.setToIndex(getPosition(getLastChild()));
        }
    }

    private float getCenterRelativePositionOf(View view, int i) {
        return Math.min(Math.max(-1.0f, this.orientationHelper.getDistanceFromCenter(this.recyclerCenter, getDecoratedLeft(view) + this.childHalfWidth, getDecoratedTop(view) + this.childHalfHeight) / ((float) i)), 1.0f);
    }

    private int checkNewOnFlingPositionIsInBounds(int i) {
        int itemCount = this.recyclerViewProxy.getItemCount();
        if (this.currentPosition != 0 && i < 0) {
            return 0;
        }
        int i2 = itemCount - 1;
        return (this.currentPosition == i2 || i < itemCount) ? i : i2;
    }

    private int getHowMuchIsLeftToScroll(int i) {
        return Direction.fromDelta(i).applyTo(this.scrollToChangeCurrent - Math.abs(this.scrolled));
    }

    private boolean isAnotherItemCloserThanCurrent() {
        return ((float) Math.abs(this.scrolled)) >= ((float) this.scrollToChangeCurrent) * SCROLL_TO_SNAP_TO_ANOTHER_ITEM;
    }

    public View getFirstChild() {
        return this.recyclerViewProxy.getChildAt(0);
    }

    public View getLastChild() {
        RecyclerViewProxy recyclerViewProxy2 = this.recyclerViewProxy;
        return recyclerViewProxy2.getChildAt(recyclerViewProxy2.getChildCount() - 1);
    }

    public int getExtraLayoutSpace() {
        return this.extraLayoutSpace;
    }

    private void notifyScroll() {
        int abs = this.pendingPosition != -1 ? Math.abs(this.scrolled + this.pendingScroll) : this.scrollToChangeCurrent;
        this.scrollStateListener.onScroll(-Math.min(Math.max(-1.0f, ((float) this.scrolled) / ((float) abs)), 1.0f));
    }

    private boolean isInBounds(int i) {
        return i >= 0 && i < this.recyclerViewProxy.getItemCount();
    }

    private boolean isViewVisible(Point point, int i) {
        return this.orientationHelper.isViewVisible(point, this.childHalfWidth, this.childHalfHeight, i, this.extraLayoutSpace);
    }


    public void setRecyclerViewProxy(RecyclerViewProxy recyclerViewProxy2) {
        this.recyclerViewProxy = recyclerViewProxy2;
    }


    public void setOrientationHelper(DSVOrientation.Helper helper) {
        this.orientationHelper = helper;
    }


    public class DiscreteLinearSmoothScroller extends LinearSmoothScroller {
        public DiscreteLinearSmoothScroller(Context context) {
            super(context);
        }

        @Override // android.support.v7.widget.LinearSmoothScroller
        public int calculateDxToMakeVisible(View view, int i) {
            return DiscreteScrollLayoutManager.this.orientationHelper.getPendingDx(-DiscreteScrollLayoutManager.this.pendingScroll);
        }

        @Override // android.support.v7.widget.LinearSmoothScroller
        public int calculateDyToMakeVisible(View view, int i) {
            return DiscreteScrollLayoutManager.this.orientationHelper.getPendingDy(-DiscreteScrollLayoutManager.this.pendingScroll);
        }


        @Override // android.support.v7.widget.LinearSmoothScroller
        public int calculateTimeForScrolling(int i) {
            return (int) (Math.max(0.01f, ((float) Math.min(Math.abs(i), DiscreteScrollLayoutManager.this.scrollToChangeCurrent)) / ((float) DiscreteScrollLayoutManager.this.scrollToChangeCurrent)) * ((float) DiscreteScrollLayoutManager.this.timeForItemSettle));
        }

        @Override // android.support.v7.widget.RecyclerView.SmoothScroller
        @Nullable
        public PointF computeScrollVectorForPosition(int i) {
            return new PointF((float) DiscreteScrollLayoutManager.this.orientationHelper.getPendingDx(DiscreteScrollLayoutManager.this.pendingScroll), (float) DiscreteScrollLayoutManager.this.orientationHelper.getPendingDy(DiscreteScrollLayoutManager.this.pendingScroll));
        }
    }
}
