package br.com.dts.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class Tela2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        Log.v(Constants.APP_LOG_TAG, "oncreate - Tela 2");

        Intent it = getIntent();

        if (it != null){
            int param = it.getIntExtra(Constants.EXTRA_01, 0);
            Toast.makeText(this, "O parametro foi " + param, Toast.LENGTH_LONG).show();
            //finish();
        }

    }

    @Override
    protected void onResume() {
        Log.v(Constants.APP_LOG_TAG, "OnResume - Tela 2");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.v(Constants.APP_LOG_TAG, "OnStop - Tela 2");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v(Constants.APP_LOG_TAG, "OnDestroy - Tela 2");
        super.onDestroy();
    }

}
