package nithra.resume.maker.cv.builder.app.Scroll;

import android.graphics.Color;


import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.List;
import nithra.resume.maker.cv.builder.app.R;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {
    private List<Forecast> data;
    private RecyclerView parentRecycler;

    public ForecastAdapter(List<Forecast> list) {
        this.data = list;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.parentRecycler = recyclerView;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_city_card, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        int color = ContextCompat.getColor(viewHolder.itemView.getContext(), R.color.trans);
        Forecast forecast = this.data.get(i);
        Glide.with(viewHolder.itemView.getContext()).load(Integer.valueOf(forecast.getCityIcon())).listener( new TintOnLoad(viewHolder.imageView, color)).into(viewHolder.imageView);
        viewHolder.textView.setText(forecast.getCityName());
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }

    public Animation zoomAnim() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.9f, 0.83f, 0.9f, 0.83f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimation.setDuration(500);
        scaleAnimation.setRepeatCount(-1);
        scaleAnimation.setRepeatMode(2);
        return scaleAnimation;
    }


    public static class TintOnLoad implements RequestListener {
        private ImageView imageView;
        private int tintColor;



        public TintOnLoad(ImageView imageView2, int i) {
            this.imageView = imageView2;
            this.tintColor = i;
        }




        @Override
        public boolean onLoadFailed(@Nullable  GlideException e, Object model, Target target, boolean isFirstResource) {
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
            this.imageView.setColorFilter(this.tintColor);
            return false;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private CardView linear_card;
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.city_image);
            this.textView = (TextView) view.findViewById(R.id.city_name);
            this.linear_card = (CardView) view.findViewById(R.id.linear_card);
            view.findViewById(R.id.container).setOnClickListener(this);
        }

        public void showText() {
            this.linear_card.setBackgroundResource(R.color.simpletooltip_arrow);
            ((View) this.imageView.getParent()).getHeight();
            this.textView.getHeight();
            this.imageView.getHeight();
            ImageView imageView2 = this.imageView;
            imageView2.setPivotX(((float) imageView2.getWidth()) * 0.5f);
            this.imageView.setPivotY(0.0f);
            this.linear_card.setAnimation(ForecastAdapter.this.zoomAnim());
            this.textView.setTextColor(Color.parseColor("#ffffff"));
            this.imageView.setColorFilter(Color.parseColor("#ffffff"));
        }

        public void hideText() {
            this.linear_card.setBackgroundResource(R.color.white);
            ImageView imageView2 = this.imageView;
            imageView2.setColorFilter(ContextCompat.getColor(imageView2.getContext(), R.color.trans));
            this.textView.setVisibility(View.VISIBLE);
            this.linear_card.setAnimation(null);
            this.textView.setTextColor(Color.parseColor("#000000"));
            this.imageView.setColorFilter(Color.parseColor("#000000"));
        }

        public void onClick(View view) {
            ForecastAdapter.this.parentRecycler.smoothScrollToPosition(getAdapterPosition());
        }
    }
}
