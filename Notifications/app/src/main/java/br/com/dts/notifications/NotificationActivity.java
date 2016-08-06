package br.com.dts.notifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTextView = new TextView(this);
        mTextView.setText("Chegou o evento da " + getNotificationType() + " notificação");

        setContentView(mTextView);
    }

    private String getNotificationType(){
        return getIntent().getBooleanExtra(MainActivity.NOTIFICATION_TYPE_TAG, false) == true ? "Big" : "Simple";
    }
}
