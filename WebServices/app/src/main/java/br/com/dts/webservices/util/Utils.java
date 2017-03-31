package br.com.dts.webservices.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import br.com.dts.webservices.app.App;

/**
 * Created by diegosouza on 8/2/16.
 */
public final class Utils {

    public static final String sURL = "http://dados.recife.pe.gov.br/dataset/eeee4ac5-d0e0-490b-aac0-490a6de74e07/resource/8d05eb77-a83d-4e9b-b5b6-6c3bd71e736d/download/pontosturisticosciclfxs.geojson";

    private Utils(){

    }

    public static boolean hasIntenetConnection(){
        ConnectivityManager cm = (ConnectivityManager)
                App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info != null && info.isConnected());

    }
}
