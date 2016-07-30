package br.com.dts.calculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        double resultado = intent.getDoubleExtra(MainActivity.RESULT_OP, 0);

        TextView tv = new TextView(this);
        tv.setText(Double.toString(resultado));

        setContentView(tv);
    }
}
