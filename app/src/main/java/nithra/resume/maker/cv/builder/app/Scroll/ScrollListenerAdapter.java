package nithra.resume.maker.cv.builder.app.Scroll;






import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import nithra.resume.maker.cv.builder.app.Scroll.DiscreteScrollView;

public class ScrollListenerAdapter<T extends RecyclerView.ViewHolder> implements DiscreteScrollView.ScrollStateChangeListener<T> {
    private DiscreteScrollView.ScrollListener<T> adaptee;

    @Override // nithra.resume.maker.cv.builder.app.Scroll.DiscreteScrollView.ScrollStateChangeListener
    public void onScrollEnd(@NonNull T t, int i) {
    }

    @Override // nithra.resume.maker.cv.builder.app.Scroll.DiscreteScrollView.ScrollStateChangeListener
    public void onScrollStart(@NonNull T t, int i) {
    }

    public ScrollListenerAdapter(@NonNull DiscreteScrollView.ScrollListener<T> scrollListener) {
        this.adaptee = scrollListener;
    }

    @Override // nithra.resume.maker.cv.builder.app.Scroll.DiscreteScrollView.ScrollStateChangeListener
    public void onScroll(float f, int i, int i2, @Nullable T t, @Nullable T t2) {
        this.adaptee.onScroll(f, i, i2, t, t2);
    }

    public boolean equals(Object obj) {
        if (obj instanceof ScrollListenerAdapter) {
            return this.adaptee.equals(((ScrollListenerAdapter) obj).adaptee);
        }
        return super.equals(obj);
    }
}
