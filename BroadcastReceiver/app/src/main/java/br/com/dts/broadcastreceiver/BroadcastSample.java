package br.com.dts.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by diegosouza on 8/2/16.
 */

public class BroadcastSample extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent it) {
        String action = it.getAction();
        Toast.makeText(context, "Ação: "+ action, Toast.LENGTH_LONG).show();

        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent intent = new Intent(context, MainActivity.class);
            //start a new task process
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
