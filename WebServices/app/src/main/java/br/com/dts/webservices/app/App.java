package br.com.dts.webservices.app;

import android.app.Application;
import android.content.Context;


/**
 * Created by diegosouza on 8/2/16.
 */
public class App extends Application {

    private static Context mContext = null;
    @Override
    public void onCreate() {
        super.onCreate();
        setContext(getApplicationContext());

    }

    private static void setContext(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }

}
