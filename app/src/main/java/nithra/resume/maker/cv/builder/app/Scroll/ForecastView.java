package nithra.resume.maker.cv.builder.app.Scroll;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;


import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ArrayRes;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;

import nithra.resume.maker.cv.builder.app.R;

public class ForecastView extends LinearLayout {
    public static TextView weatherDescription;
    LinearLayout cardline;
    private int[] currentGradient;
    private ArgbEvaluator evaluator = new ArgbEvaluator();
    private Paint gradientPaint = new Paint(1);
    private ImageView weatherImage;

    public ForecastView(Context context) {
        super(context);
        setWillNotDraw(false);
        setOrientation(1);
        setGravity(1);
        inflate(getContext(), R.layout.view_forecast, this);
        weatherDescription = (TextView) findViewById(R.id.weather_description);
        this.weatherImage = (ImageView) findViewById(R.id.weather_image);
        this.cardline = (LinearLayout) findViewById(R.id.cardline);
        this.weatherImage.setOnClickListener(new OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Scroll.ForecastView.AnonymousClass1 */

            public void onClick(View view) {
            }
        });
    }

    public ForecastView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setWillNotDraw(false);
        setOrientation(1);
        setGravity(1);
        inflate(getContext(), R.layout.view_forecast, this);
        weatherDescription = (TextView) findViewById(R.id.weather_description);
        this.weatherImage = (ImageView) findViewById(R.id.weather_image);
        this.cardline = (LinearLayout) findViewById(R.id.cardline);
        this.weatherImage.setOnClickListener(new OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Scroll.ForecastView.AnonymousClass1 */

            public void onClick(View view) {
            }
        });
    }

    public ForecastView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setWillNotDraw(false);
        setOrientation(VERTICAL);
        setGravity(1);
        inflate(getContext(), R.layout.view_forecast, this);
        weatherDescription = (TextView) findViewById(R.id.weather_description);
        this.weatherImage = (ImageView) findViewById(R.id.weather_image);
        this.cardline = (LinearLayout) findViewById(R.id.cardline);
        this.weatherImage.setOnClickListener(new OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Scroll.ForecastView.AnonymousClass1 */

            public void onClick(View view) {
            }
        });
    }

    @RequiresApi(api = 21)
    public ForecastView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setWillNotDraw(false);
        setOrientation(1);
        setGravity(1);
        inflate(getContext(), R.layout.view_forecast, this);
        weatherDescription = (TextView) findViewById(R.id.weather_description);
        this.weatherImage = (ImageView) findViewById(R.id.weather_image);
        this.cardline = (LinearLayout) findViewById(R.id.cardline);
        this.weatherImage.setOnClickListener(new OnClickListener() {
            /* class nithra.resume.maker.cv.builder.app.Scroll.ForecastView.AnonymousClass1 */

            public void onClick(View view) {
            }
        });
    }

    private void initGradient() {
        float width = ((float) getWidth()) * 0.5f;
        this.gradientPaint.setShader(new LinearGradient(width, 0.0f, width, (float) getHeight(), this.currentGradient, (float[]) null, Shader.TileMode.MIRROR));
    }


    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.currentGradient != null) {
            initGradient();
        }
    }


    public void onDraw(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), this.gradientPaint);
        super.onDraw(canvas);
    }

    public void setForecast(Forecast forecast) {
        forecast.getWeather();
        this.currentGradient = colors(R.array.gradientPeriodicClouds);
        if (!(getWidth() == 0 || getHeight() == 0)) {
            initGradient();
        }
        Glide.with(getContext()).load(Integer.valueOf((int) R.drawable.normal)).into(this.weatherImage);
        invalidate();
        this.weatherImage.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(500).start();
        this.cardline.animate().scaleX(1.0f).scaleY(1.0f).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(500).start();
    }

    public void onScroll(float f, Forecast forecast, Forecast forecast2) {
        this.weatherImage.setScaleX(f);
        this.weatherImage.setScaleY(f);
        this.cardline.setScaleX(f);
        this.cardline.setScaleY(f);
        this.currentGradient = mix(f, colors(R.array.gradientCloudy), colors(R.array.gradientMostlyCloudy));
        initGradient();
        invalidate();
    }

    private int[] mix(float f, int[] iArr, int[] iArr2) {
        return new int[]{((Integer) this.evaluator.evaluate(f, Integer.valueOf(iArr[0]), Integer.valueOf(iArr2[0]))).intValue(), ((Integer) this.evaluator.evaluate(f, Integer.valueOf(iArr[1]), Integer.valueOf(iArr2[1]))).intValue(), ((Integer) this.evaluator.evaluate(f, Integer.valueOf(iArr[2]), Integer.valueOf(iArr2[2]))).intValue()};
    }

    private int[] weatherToGradient(Weather weather) {
        switch (weather) {
            case PERIODIC_CLOUDS:
                return colors(R.array.gradientPeriodicClouds);
            case CLOUDY:
                return colors(R.array.gradientCloudy);
            case MOSTLY_CLOUDY:
                return colors(R.array.gradientMostlyCloudy);
            case PARTLY_CLOUDY:
                return colors(R.array.gradientPartlyCloudy);
            case CLEAR:
                return colors(R.array.gradientClear);
            default:
                throw new IllegalArgumentException();
        }
    }

    private int weatherToIcon(Weather weather) {
        switch (weather) {
            case PERIODIC_CLOUDS:
                return R.drawable.resumeicon;
            case CLOUDY:
                return R.drawable.resumeicon;
            case MOSTLY_CLOUDY:
                return R.drawable.resumeicon;
            case PARTLY_CLOUDY:
                return R.drawable.resumeicon;
            case CLEAR:
                return R.drawable.resumeicon;
            default:
                throw new IllegalArgumentException();
        }
    }

    private int[] colors(@ArrayRes int i) {
        return getContext().getResources().getIntArray(i);
    }
}
