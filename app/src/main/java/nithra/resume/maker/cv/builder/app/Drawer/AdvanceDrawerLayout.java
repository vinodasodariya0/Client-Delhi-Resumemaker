package nithra.resume.maker.cv.builder.app.Drawer;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.ColorInt;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

public class AdvanceDrawerLayout extends DrawerLayout {
    private static final String TAG = "AdvanceDrawerLayout";
    private float defaultDrawerElevation;
    private int defaultScrimColor = -1728053248;
    public View drawerView;
    private FrameLayout frameLayout;
    HashMap<Integer, Setting> settings = new HashMap<>();

    public AdvanceDrawerLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public AdvanceDrawerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public AdvanceDrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        this.defaultDrawerElevation = getDrawerElevation();
        addDrawerListener(new DrawerLayout.DrawerListener() {


            @Override
            public void onDrawerClosed(View view) {
            }

            @Override
            public void onDrawerOpened(View view) {
            }

            @Override
            public void onDrawerStateChanged(int i) {
            }

            @Override
            public void onDrawerSlide(View view, float f) {
                AdvanceDrawerLayout advanceDrawerLayout = AdvanceDrawerLayout.this;
                advanceDrawerLayout.drawerView = view;
                advanceDrawerLayout.updateSlideOffset(view, f);
            }
        });
        this.frameLayout = new FrameLayout(context);
        super.addView(this.frameLayout);
    }

    @Override
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        view.setLayoutParams(layoutParams);
        addView(view);
    }

    public void addView(View view) {
        if (view instanceof NavigationView) {
            super.addView(view);
            return;
        }
        CardView cardView = new CardView(getContext());
        cardView.setRadius(0.0f);
        cardView.addView(view);
        cardView.setCardElevation(0.0f);
        this.frameLayout.addView(cardView);
    }

    public void setViewScale(int i, float f) {
        Setting setting;
        int drawerViewAbsoluteGravity = getDrawerViewAbsoluteGravity(i);
        if (!this.settings.containsKey(Integer.valueOf(drawerViewAbsoluteGravity))) {
            setting = createSetting();
            this.settings.put(Integer.valueOf(drawerViewAbsoluteGravity), setting);
        } else {
            setting = this.settings.get(Integer.valueOf(drawerViewAbsoluteGravity));
        }
        setting.percentage = f;
        setting.scrimColor = 0;
        setting.drawerElevation = 0.0f;
    }

    public void setViewElevation(int i, float f) {
        Setting setting;
        int drawerViewAbsoluteGravity = getDrawerViewAbsoluteGravity(i);
        if (!this.settings.containsKey(Integer.valueOf(drawerViewAbsoluteGravity))) {
            setting = createSetting();
            this.settings.put(Integer.valueOf(drawerViewAbsoluteGravity), setting);
        } else {
            setting = this.settings.get(Integer.valueOf(drawerViewAbsoluteGravity));
        }
        setting.scrimColor = 0;
        setting.drawerElevation = 0.0f;
        setting.elevation = f;
    }

    public void setViewScrimColor(int i, int i2) {
        Setting setting;
        int drawerViewAbsoluteGravity = getDrawerViewAbsoluteGravity(i);
        if (!this.settings.containsKey(Integer.valueOf(drawerViewAbsoluteGravity))) {
            setting = createSetting();
            this.settings.put(Integer.valueOf(drawerViewAbsoluteGravity), setting);
        } else {
            setting = this.settings.get(Integer.valueOf(drawerViewAbsoluteGravity));
        }
        setting.scrimColor = i2;
    }

    public void setDrawerElevation(int i, float f) {
        Setting setting;
        int drawerViewAbsoluteGravity = getDrawerViewAbsoluteGravity(i);
        if (!this.settings.containsKey(Integer.valueOf(drawerViewAbsoluteGravity))) {
            setting = createSetting();
            this.settings.put(Integer.valueOf(drawerViewAbsoluteGravity), setting);
        } else {
            setting = this.settings.get(Integer.valueOf(drawerViewAbsoluteGravity));
        }
        setting.elevation = 0.0f;
        setting.drawerElevation = f;
    }

    public void setRadius(int i, float f) {
        Setting setting;
        int drawerViewAbsoluteGravity = getDrawerViewAbsoluteGravity(i);
        if (!this.settings.containsKey(Integer.valueOf(drawerViewAbsoluteGravity))) {
            setting = createSetting();
            this.settings.put(Integer.valueOf(drawerViewAbsoluteGravity), setting);
        } else {
            setting = this.settings.get(Integer.valueOf(drawerViewAbsoluteGravity));
        }
        setting.radius = f;
    }

    public Setting getSetting(int i) {
        return this.settings.get(Integer.valueOf(getDrawerViewAbsoluteGravity(i)));
    }

    @Override
    public void setDrawerElevation(float f) {
        this.defaultDrawerElevation = f;
        super.setDrawerElevation(f);
    }

    @Override
    public void setScrimColor(@ColorInt int i) {
        this.defaultScrimColor = i;
        super.setScrimColor(i);
    }

    public void useCustomBehavior(int i) {
        int drawerViewAbsoluteGravity = getDrawerViewAbsoluteGravity(i);
        if (!this.settings.containsKey(Integer.valueOf(drawerViewAbsoluteGravity))) {
            this.settings.put(Integer.valueOf(drawerViewAbsoluteGravity), createSetting());
        }
    }

    public void removeCustomBehavior(int i) {
        this.settings.remove(Integer.valueOf(getDrawerViewAbsoluteGravity(i)));
    }

    @Override
    public void openDrawer(final View view, boolean z) {
        super.openDrawer(view, z);
        post(new Runnable() {

            public void run() {
                AdvanceDrawerLayout advanceDrawerLayout = AdvanceDrawerLayout.this;

                advanceDrawerLayout.updateSlideOffset(view, advanceDrawerLayout.isDrawerOpen(view) ? 1.0f : 0.0f);
            }
        });
    }



    private void updateSlideOffset(View view, float f) {
        int drawerViewAbsoluteGravity = getDrawerViewAbsoluteGravity(GravityCompat.START);
        int drawerViewAbsoluteGravity2 = getDrawerViewAbsoluteGravity(view);
        for (int i = 0; i < this.frameLayout.getChildCount(); i++) {
            CardView cardView = (CardView) this.frameLayout.getChildAt(i);
            Setting setting = this.settings.get(Integer.valueOf(drawerViewAbsoluteGravity2));
            if (setting != null) {
                cardView.setRadius((float) ((int) (setting.radius * f)));
                super.setScrimColor(setting.scrimColor);
                super.setDrawerElevation(setting.drawerElevation);
                ViewCompat.setScaleY(cardView, 1.0f - ((1.0f - setting.percentage) * f));
                cardView.setCardElevation(setting.elevation * f);
                float f2 = setting.elevation;
                boolean z = drawerViewAbsoluteGravity2 == drawerViewAbsoluteGravity;
                updateSlideOffset(cardView, setting, z ? ((float) view.getWidth()) + f2 : ((float) (-view.getWidth())) - f2, f, z);
            } else {
                super.setScrimColor(this.defaultScrimColor);
                super.setDrawerElevation(this.defaultDrawerElevation);
            }
        }
    }


    public void updateSlideOffset(CardView cardView, Setting setting, float f, float f2, boolean z) {
        ViewCompat.setX(cardView, f * f2);
    }


    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        View view = this.drawerView;
        if (view != null) {
            updateSlideOffset(view, isDrawerOpen(view) ? 1.0f : 0.0f);
        }
    }


    public int getDrawerViewAbsoluteGravity(int i) {
        return GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(this)) & 7;
    }



    public int getDrawerViewAbsoluteGravity(View view) {
        return getDrawerViewAbsoluteGravity(((DrawerLayout.LayoutParams) view.getLayoutParams()).gravity);
    }


    public Setting createSetting() {
        return new Setting();
    }


    public class Setting {
        float drawerElevation = AdvanceDrawerLayout.this.defaultDrawerElevation;
        float elevation = 0.0f;
        float percentage = 1.0f;
        float radius;
        int scrimColor = AdvanceDrawerLayout.this.defaultScrimColor;

        Setting() {
        }

        public float getDrawerElevation() {
            return this.drawerElevation;
        }

        public float getElevation() {
            return this.elevation;
        }

        public float getPercentage() {
            return this.percentage;
        }

        public float getRadius() {
            return this.radius;
        }

        public int getScrimColor() {
            return this.scrimColor;
        }
    }
}
