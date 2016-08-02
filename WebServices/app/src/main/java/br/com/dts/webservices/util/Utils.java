package br.com.dts.webservices.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import br.com.dts.webservices.app.App;

/**
 * Created by diegosouza on 8/2/16.
 */
public final class Utils {

    private Utils(){

    }

    public static boolean hasIntenetConnection(){
        ConnectivityManager cm = (ConnectivityManager)
                App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected());

    }
}
