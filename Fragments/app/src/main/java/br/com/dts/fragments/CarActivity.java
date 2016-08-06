package br.com.dts.fragments;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by diegosouza on 7/28/16.
 */
public class CarActivity extends AppCompatActivity implements OnCarSelectedListener{

    private FragmentManager mFragmentManager;
    private ListCarFragment mListCarFragment;
    private FloatingActionButton mBtnAddCar;

    private Button mBtnTeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();


        mListCarFragment = (ListCarFragment) mFragmentManager.findFragmentById(R.id.fragment_car_list);

        mBtnAddCar = (FloatingActionButton) findViewById(R.id.btn_add_car);
        mBtnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


//        mListCarFragment.setOnCarSelectedListener(new OnCarSelectedListener() {
//
//            @Override
//            public void onCarSelected(Car car) {
//
//                if (isTablet()){
//                    CarDetailFragment fragment = CarDetailFragment.newInstance(car);
//                    FragmentTransaction ft = mFragmentManager.beginTransaction();
//                    ft.replace(R.id.frame_car_detail, fragment, CarDetailFragment.DETAIL_TAG );
//                    ft.commit();
//
//                } else {
//
//                    Intent it = new Intent(CarActivity.this, CarDetailActivity.class);
//                    it.putExtra(CarDetailActivity.CAR_TAG, car);
//                    startActivity(it);
//
//                }
//
//            }
//        });

        mListCarFragment.setOnCarSelectedListener(this);


    }

    private boolean isTablet(){

        return getResources().getBoolean(R.bool.tablet);
    }


    @Override
    public void onCarSelected(Car car) {
        if (isTablet()){
            CarDetailFragment fragment = CarDetailFragment.newInstance(car);
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.replace(R.id.frame_car_detail, fragment, CarDetailFragment.DETAIL_TAG );
            ft.commit();

        } else {

            Intent it = new Intent(CarActivity.this, CarDetailActivity.class);
            it.putExtra(CarDetailActivity.CAR_TAG, car);
            startActivity(it);

        }

    }
}
