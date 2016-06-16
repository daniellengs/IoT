package br.com.dts.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button mBtn01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(Constants.APP_LOG_TAG, "OnCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn01 = (Button) findViewById(R.id.btn01);

        mBtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Tela2.class);
                //Essa linha adiciona um parametro extra na intent
                //intent.putExtra(Constants.EXTRA_01, 1);
                startActivity(intent);
            }
        });
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
