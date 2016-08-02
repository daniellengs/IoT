package br.com.dts.webservices.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.dts.webservices.R;
import br.com.dts.webservices.model.Place;
import br.com.dts.webservices.model.PlaceGeosonList;

/**
 * Created by diegosouza on 8/2/16.
 */
public class PlacesAdapter extends BaseAdapter{

    private Context mContext;
    private Place[] mPlaces;

    public PlacesAdapter(Context c, Place[] places){
        mContext = c;
        mPlaces = places;
    }

    @Override
    public int getCount() {
        return (mPlaces != null) ? mPlaces.length : 0;
    }

    @Override
    public Object getItem(int i) {
        return mPlaces[i];
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(mPlaces[i].getId());
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        Place place = mPlaces[i];

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_place, null);

            holder = new ViewHolder();
            holder.imgLogo = (ImageView) convertView.findViewById(R.id.imgLogo);
            holder.txtPlaceName = (TextView) convertView.findViewById(R.id.txtPlaceName);

            convertView.setTag(holder);

        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.imgLogo.setImageResource(R.drawable.icon);
        holder.txtPlaceName.setText(place.getProperties().getPTurist());

        return convertView;
    }

    static class ViewHolder {
        ImageView imgLogo;
        TextView txtPlaceName;
    }
}
