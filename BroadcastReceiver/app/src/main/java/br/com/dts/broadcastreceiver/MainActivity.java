package br.com.dts.broadcastreceiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by diegosouza on 8/2/16.
 */
public class MainActivity extends AppCompatActivity {

    private InternalBroadcast mReceiver;
    private Button mBtnLocalBroadcast;
    private Button mBtnBroadcast;

    public static final String BROADCAST_ACTION =
            "br.com.dts.broadcastreceiver.BROADCAST_ACTION_SECOND";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnLocalBroadcast = (Button)findViewById(R.id.btn_broadcast_local);
        mBtnBroadcast = (Button)findViewById(R.id.btn_broadcast);

        mBtnLocalBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalBroadcastManager.getInstance(MainActivity.this)
                        .sendBroadcast(new Intent(BROADCAST_ACTION));
                //sendBroadcast(new Intent(BROADCAST_ACTION));
            }
        });

        mBtnBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBroadcast(new Intent("br.com.dts.broadcastreceiver.BROADCAST_GENERAL"));
            }
        });

        mReceiver = new InternalBroadcast();
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

    private class InternalBroadcast extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(BROADCAST_ACTION)) {
                Toast.makeText(context, "Broadcast funcionou", Toast.LENGTH_LONG).show();
            }
        }
    }
}
