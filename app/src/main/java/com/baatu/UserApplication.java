package com.baatu;

import android.app.Application;
import android.content.Context;
/**
 *
 * @author Shailendra This is Application class
 *we are initialize app context
 */
public class UserApplication extends Application {

    private static Context sAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
    }

    public static Context getAppContext() {
        return sAppContext;
    }
}
