package nithra.resume.maker.cv.builder.app.Fcm;

import android.content.Context;



import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;


public class App_analytics extends MultiDexApplication {

    @Override
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
}
