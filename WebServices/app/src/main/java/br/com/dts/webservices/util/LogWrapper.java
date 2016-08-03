package br.com.dts.webservices.util;

import android.content.pm.ApplicationInfo;
import android.util.Log;

import br.com.dts.webservices.app.App;

/**
 * Created by diegosouza on 8/3/16.
 */
public final class LogWrapper {

    private LogWrapper(){

    }

    public static final String LOG_TAG = "WebServices";

    public static final boolean DEBUG = (App.getContext().getApplicationInfo().flags
            & ApplicationInfo.FLAG_DEBUGGABLE) != 0;

    public static final void log(String message){
        if (DEBUG) Log.v(LOG_TAG, message);
    }

}
