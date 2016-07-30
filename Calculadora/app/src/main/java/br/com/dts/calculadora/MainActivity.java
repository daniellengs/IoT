package br.com.dts.calculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mEdt01;
    private EditText mEdt02;
    private Button mBtnSoma;
    private Button mBtbSub;
    private Button mBtnMult;
    private Button mBtnDiv;

    public static final String RESULT_OP = "resultado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdt01 = (EditText) findViewById(R.id.edt_01);
        mEdt02 = (EditText) findViewById(R.id.edt_02);

        mBtnSoma = (Button) findViewById(R.id.btn_soma);
        mBtbSub = (Button) findViewById(R.id.btn_sub);
        mBtnMult = (Button) findViewById(R.id.btn_mult);
        mBtnDiv = (Button) findViewById(R.id.btn_div);

        mBtnSoma.setOnClickListener(this);
        mBtbSub.setOnClickListener(this);
        mBtnMult.setOnClickListener(this);
        mBtnDiv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        double valor1 = Double.parseDouble(mEdt01.getText().toString());
        double valor2 = Double.parseDouble(mEdt02.getText().toString());
        double resultado = 0;

        switch (view.getId()){
            case R.id.btn_soma:
                resultado = valor1 + valor2;
                break;
            case R.id.btn_sub:
                resultado = valor1 - valor2;
                break;
            case R.id.btn_mult:
                resultado = valor1 * valor2;
                break;
            case R.id.btn_div:
                resultado = valor1 / valor2;
                break;
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(RESULT_OP, resultado);
        startActivity(intent);
    }
}
