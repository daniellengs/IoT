package br.com.dts.fragments;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by diegosouza on 7/28/16.
 */
public class CarDetailActivity extends AppCompatActivity {

    public static final String CAR_TAG = "car";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        Car car = (Car)getIntent().getSerializableExtra(CAR_TAG);

        CarDetailFragment fragment = CarDetailFragment.newInstance(car);

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_car_detail,fragment, CarDetailFragment.DETAIL_TAG);

        ft.commit();
    }
}
