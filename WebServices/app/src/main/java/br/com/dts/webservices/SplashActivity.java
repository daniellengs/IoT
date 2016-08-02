package br.com.dts.webservices;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView mImageView;
    private static final int TIMEOUT = 3 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mImageView = new ImageView(this);
        mImageView.setImageResource(R.drawable.icon);

        setContentView(mImageView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, ListPlacesActivity.class));
                finish();
            }
        }, TIMEOUT);
    }
}
