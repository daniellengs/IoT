package br.com.dts.fragments.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.dts.fragments.Car;

/**
 * Created by diegosouza on 8/5/16.
 */
public class CarAdapter extends BaseAdapter {

    private List<Car> carList;
    private Context mContext;

    public CarAdapter(Context c, List<Car> list) {
        mContext = c;
        carList = list;
    }

    @Override
    public int getCount() {
        return carList.size();
    }

    @Override
    public Object getItem(int position) {
        return carList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Car car = carList.get(position);

        TextView tv = new TextView(mContext);

        tv.setText(car.modelo);

        tv.setTag(car);

        return tv;
    }
}
