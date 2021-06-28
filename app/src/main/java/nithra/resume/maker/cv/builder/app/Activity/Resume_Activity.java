package nithra.resume.maker.cv.builder.app.Activity;

import android.os.Bundle;
import android.os.StrictMode;


import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import java.util.List;
import nithra.resume.maker.cv.builder.app.Fragment.Academic_fragment;
import nithra.resume.maker.cv.builder.app.Fragment.Achievement_fragment;
import nithra.resume.maker.cv.builder.app.Fragment.Cover_Letter_fragment;
import nithra.resume.maker.cv.builder.app.Fragment.Objective_fragment;
import nithra.resume.maker.cv.builder.app.Fragment.Pdf_fragment;
import nithra.resume.maker.cv.builder.app.Fragment.Personal_info_fragment;
import nithra.resume.maker.cv.builder.app.Fragment.Photo_Sign_fragment;
import nithra.resume.maker.cv.builder.app.Fragment.Preview_fragment;
import nithra.resume.maker.cv.builder.app.Fragment.Project_Details_fragment;
import nithra.resume.maker.cv.builder.app.Fragment.Reference_fragment;
import nithra.resume.maker.cv.builder.app.Fragment.Skills_fragment;
import nithra.resume.maker.cv.builder.app.Fragment.Work_Exp_fragment;
import nithra.resume.maker.cv.builder.app.R;
import nithra.resume.maker.cv.builder.app.Scroll.DiscreteScrollView;
import nithra.resume.maker.cv.builder.app.Scroll.Forecast;
import nithra.resume.maker.cv.builder.app.Scroll.ForecastAdapter;
import nithra.resume.maker.cv.builder.app.Scroll.WeatherStation;
import nithra.resume.maker.cv.builder.app.Visible;

public class Resume_Activity extends AppCompatActivity {
    public static int back_flag;
    int Fr_Change;
    LinearLayout ads_lay;
    DiscreteScrollView all_items;
    FrameLayout container;
    private List<Forecast> forecasts;
    Fragment fragment;
    Fragment replaced;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        setContentView(R.layout.activity_resume);
        this.container = (FrameLayout) findViewById(R.id.container);
        this.all_items = (DiscreteScrollView) findViewById(R.id.all_items);
        this.ads_lay = (LinearLayout) findViewById(R.id.ads_lay);
        adds(this.ads_lay);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        this.fragment = new Personal_info_fragment();
        beginTransaction.add(R.id.container, this.fragment);
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
        this.forecasts = WeatherStation.get().getForecasts();
        this.all_items.setSlideOnFling(true);
        this.all_items.setAdapter(new ForecastAdapter(this.forecasts));
        this.all_items.scrollToPosition(0);
        this.all_items.setItemTransitionTimeMillis(100);
        this.all_items.setScaleX(0.8f);
        this.all_items.setScaleY(0.8f);
        this.all_items.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<ForecastAdapter.ViewHolder>() {

            public void onCurrentItemChanged(@Nullable ForecastAdapter.ViewHolder viewHolder, int i) {
                if (viewHolder != null) {
                    viewHolder.showText();
                    Resume_Activity.this.container.removeAllViews();
                    Resume_Activity resume_Activity = Resume_Activity.this;
                    resume_Activity.Fr_Change = i;
                    resume_Activity.refresh();
                }
            }
        });
        this.all_items.addScrollStateChangeListener(new DiscreteScrollView.ScrollStateChangeListener<ForecastAdapter.ViewHolder>() {

            public void onScrollEnd(@NonNull ForecastAdapter.ViewHolder viewHolder, int i) {
            }

            public void onScrollStart(@NonNull ForecastAdapter.ViewHolder viewHolder, int i) {
                viewHolder.hideText();
            }

            public void onScroll(float f, int i, int i2, @Nullable ForecastAdapter.ViewHolder viewHolder, @Nullable ForecastAdapter.ViewHolder viewHolder2) {
                Forecast forecast = (Forecast) Resume_Activity.this.forecasts.get(i);
                if (i2 >= 0 && i2 < Resume_Activity.this.all_items.getAdapter().getItemCount()) {
                    Forecast forecast2 = (Forecast) Resume_Activity.this.forecasts.get(i2);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Visible visible;
        if (i != 4) {
            return false;
        }
        int i2 = this.Fr_Change;
        if (i2 == 0) {
            Visible visible2 = (Visible) this.replaced;
            if (visible2 != null) {
                visible2.visible();
            }
        } else if (i2 == 1) {
            Visible visible3 = (Visible) this.replaced;
            if (visible3 != null) {
                visible3.visible();
            }
        } else if (i2 == 2) {
            Visible visible4 = (Visible) this.replaced;
            if (visible4 != null) {
                visible4.visible();
            }
        } else if (i2 == 3) {
            Visible visible5 = (Visible) this.replaced;
            if (visible5 != null) {
                visible5.visible();
            }
        } else if (i2 == 4) {
            Visible visible6 = (Visible) this.replaced;
            if (visible6 != null) {
                visible6.visible();
            }
        } else if (i2 == 5) {
            Visible visible7 = (Visible) this.replaced;
            if (visible7 != null) {
                visible7.visible();
            }
        } else if (i2 == 6) {
            Visible visible8 = (Visible) this.replaced;
            if (visible8 != null) {
                visible8.visible();
            }
        } else if (i2 == 7) {
            Visible visible9 = (Visible) this.replaced;
            if (visible9 != null) {
                visible9.visible();
            }
        } else if (i2 == 8) {
            Visible visible10 = (Visible) this.replaced;
            if (visible10 != null) {
                visible10.visible();
            }
        } else if (i2 == 9) {
            Visible visible11 = (Visible) this.replaced;
            if (visible11 != null) {
                visible11.visible();
            }
        } else if (i2 == 10) {
            Visible visible12 = (Visible) this.replaced;
            if (visible12 != null) {
                visible12.visible();
            }
        } else if (i2 == 11 && (visible = (Visible) this.replaced) != null) {
            visible.visible();
        }
        return true;
    }

    public Animation zoomAnim() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.9f, 0.83f, 0.9f, 0.83f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimation.setDuration(500);
        scaleAnimation.setRepeatCount(-1);
        scaleAnimation.setRepeatMode(2);
        return scaleAnimation;
    }

    public void refresh() {
        try {
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.container)).commit();
            if (this.Fr_Change == 0) {
                this.replaced = new Personal_info_fragment();
            } else if (this.Fr_Change == 1) {
                this.replaced = new Academic_fragment();
            } else if (this.Fr_Change == 2) {
                this.replaced = new Work_Exp_fragment();
            } else if (this.Fr_Change == 3) {
                this.replaced = new Project_Details_fragment();
            } else if (this.Fr_Change == 4) {
                this.replaced = new Skills_fragment();
            } else if (this.Fr_Change == 5) {
                this.replaced = new Achievement_fragment();
            } else if (this.Fr_Change == 6) {
                this.replaced = new Reference_fragment();
            } else if (this.Fr_Change == 7) {
                this.replaced = new Objective_fragment();
            } else if (this.Fr_Change == 8) {
                this.replaced = new Cover_Letter_fragment();
            } else if (this.Fr_Change == 9) {
                this.replaced = new Photo_Sign_fragment();
            } else if (this.Fr_Change == 10) {
                this.replaced = new Preview_fragment();
            } else if (this.Fr_Change == 11) {
                this.replaced = new Pdf_fragment();
            }
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.add(R.id.container, this.replaced);
            beginTransaction.addToBackStack(null);
            beginTransaction.commit();
        } catch (Exception unused) {
        }
    }

    public void adds(final LinearLayout linearLayout) {
        AdView adView = new AdView(this);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        adView.setAdSize(AdSize.SMART_BANNER);
        try {
            linearLayout.removeAllViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
        linearLayout.addView(adView);
        adView.setAdListener(new AdListener() {
            /* class nithra.resume.maker.cv.builder.app.Activity.Resume_Activity.AnonymousClass3 */

            @Override // com.google.android.gms.ads.AdListener
            public void onAdLoaded() {
                linearLayout.setVisibility(View.VISIBLE);
            }
        });
        adView.loadAd(new AdRequest.Builder().build());
    }
}
