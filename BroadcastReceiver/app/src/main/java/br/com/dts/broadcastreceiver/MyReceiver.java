package br.com.dts.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by diegosouza on 8/2/16.
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

       // if (intent.getAction().equals(MainActivity.BROADCAST_ACTION)) {
            Toast.makeText(context, "Broadcast de sistemacfuncionou", Toast.LENGTH_LONG).show();
      //  }
    }
}
