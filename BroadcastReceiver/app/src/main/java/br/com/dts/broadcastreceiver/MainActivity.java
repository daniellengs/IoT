package br.com.dts.broadcastreceiver;


import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by diegosouza on 8/2/16.
 */
public class MainActivity extends AppCompatActivity {

    private MyReceiver mReceiver;

    public static final String BROADCAST_ACTION =
            "br.com.dts.broadcastreceiver.BROADCAST_ACTION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReceiver = new MyReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Unregister the local broadcats when activity is going to foreground
        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(mReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Register receiver when activity is being created or returning from foreground
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver, intentFilter);
    }
}
