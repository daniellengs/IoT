package br.com.dts.layouts.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.dts.layouts.model.Carro;

/**
 * Created by diegosouza on 3/30/17.
 */

public class CarrosAdapter extends BaseAdapter{

    private List<Carro> mCarroList;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
