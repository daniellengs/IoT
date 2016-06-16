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

        Intent it = getIntent();

        if (it != null){
            int param = it.getIntExtra(Constants.EXTRA_01, 0);
            Toast.makeText(this, "O parametro foi " + param, Toast.LENGTH_LONG).show();
            finish();
        }

    }

    @Override
    protected void onResume() {
        Log.v(Constants.APP_LOG_TAG, "OnResume");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.v(Constants.APP_LOG_TAG, "OnStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v(Constants.APP_LOG_TAG, "OnDestroy");
        super.onDestroy();
    }

}
