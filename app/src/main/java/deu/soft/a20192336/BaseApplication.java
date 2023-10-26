package deu.soft.a20192336;

import android.app.Application;
import android.content.Context;

import dagger.hilt.android.HiltAndroidApp;

/**
 * Hilt를 사용하기 위한 BaseApplication
 */
@HiltAndroidApp
public class BaseApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
