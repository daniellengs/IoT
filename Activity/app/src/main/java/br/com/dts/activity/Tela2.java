package br.com.dts.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class Tela2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        Intent it = getIntent();

        if (it != null){
            int param = it.getIntExtra("EXTRA01", 0);
            Toast.makeText(this, "O parametro foi " + param, Toast.LENGTH_LONG).show();
        }

    }

}
