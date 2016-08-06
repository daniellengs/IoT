package br.com.dts.notifications;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {
    private TextView mTextView;

    private boolean isBigNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTextView = new TextView(this);
        mTextView.setText("Chegou o evento da " + getNotificationType() + " notificação");

        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        isBigNotification = (getIntent().getBooleanExtra(MainActivity.IS_BIG_NOTIFICATION, false) == true);

        if (isBigNotification){
            manager.cancel(MainActivity.BIG_NOTIFICATION_ID);
        } else {
            manager.cancel(MainActivity.SIMPLE_NOTIFICATION_ID);
        }



        setContentView(mTextView);
    }

    private String getNotificationType(){


        return getIntent().getBooleanExtra(MainActivity.IS_BIG_NOTIFICATION, false) == true ? "Big" : "Simple";
    }
}
