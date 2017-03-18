package br.com.dts.activity;

import android.util.Log;

/**
 * Created by diegosouza on 9/5/16.
 */
public final class Logger {

    private Logger(){

    }

    public static void v(String message){
         if (BuildConfig.DEBUG) {
             Log.v(Constants.APP_LOG_TAG, message);
         }
    }
}
