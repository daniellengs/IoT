package br.com.dts.fragments;

import android.support.v4.app.ListFragment;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.dts.fragments.Repository.CarRepository;

public class ListCarFragment extends ListFragment {

    private List<Car> mCarList;

    private OnCarSelectedListener mListener;

    private ArrayAdapter<Car> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mCarList = CarRepository.getInstance().getCarList();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //mAdapter = new CarAdapter(getActivity(), mCarList);

        mAdapter = new ArrayAdapter<Car>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                mCarList);
       // setListAdapter(mAdapter);
        setListAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        notifyListener((Car)l.getItemAtPosition(position));

    }

    public void setOnCarSelectedListener(OnCarSelectedListener listener) {
        mListener = listener;
    }

    public void notifyListener(Car car){

        if (mListener != null) mListener.onCarSelected(car);
    }
}
