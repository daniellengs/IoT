package br.com.dts.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 */
public class CarDetailFragment extends Fragment {

    private Car mCar;

    private TextView mTxtMarca;
    private TextView mTxtModelo;

    public static final String DETAIL_TAG = "CAR_DETAIL_FRAGMENT";

    public CarDetailFragment() {
        // Required empty public constructor
    }

    public static CarDetailFragment newInstance(Car car){
        Bundle params = new Bundle();
        params.putSerializable("car", car);

        CarDetailFragment carDetailFragment = new CarDetailFragment();
        carDetailFragment.setArguments(params);
        return carDetailFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mCar = (Car) getArguments().getSerializable("car");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View fragmentLayout =  inflater.inflate(R.layout.car_details, container, false);

        mTxtMarca = (TextView) fragmentLayout.findViewById(R.id.txt_car_marca);

        mTxtModelo = (TextView) fragmentLayout.findViewById(R.id.txt_car_model);

        if (mCar != null) {
            mTxtMarca.setText(mCar.marca);
            mTxtModelo.setText(mCar.modelo);
        }

        return fragmentLayout;
    }

}
