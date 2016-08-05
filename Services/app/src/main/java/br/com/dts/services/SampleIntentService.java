package br.com.dts.services;

import android.app.IntentService;
import android.content.Intent;


public class SampleIntentService extends IntentService {

    public SampleIntentService() {
        super("SampleIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {

        }
    }

}
