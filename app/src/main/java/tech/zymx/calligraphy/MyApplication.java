package tech.zymx.calligraphy;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by kevinzhan on 2018/1/19.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
