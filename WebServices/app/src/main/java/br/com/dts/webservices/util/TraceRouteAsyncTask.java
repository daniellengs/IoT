/** 
* COPYRIGHT (c) 2013, MOTOROLA. ALL RIGHTS RESERVED
* 
* REVISION HISTORY
*
* DATA          CORE ID       CR NUMBER      COMMENTS
* ============================================================
* 12/06/2013    BWCH73        47545         Initial creation
*/
package br.com.dts.webservices.util;

import java.util.Locale;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import br.com.dts.webservices.R;
import br.com.dts.webservices.TraceRouteActivity;
import br.com.dts.webservices.model.Route;


public class TraceRouteAsyncTask extends AsyncTask<Double, Void, Void> {

	private ProgressDialog mDialog;
	private GoogleMap mMapView;
	private Context mContext;
	private Route mRoute;
	private final int POLILYNE_WIDTH = 6;
	private final String MAP_URL = "http://maps.googleapis.com/maps/api/"
			+ "directions/json?origin=%f,%f&" + "destination=%f,%f&"
			+ "sensor=true&mode=driving";
	private double sourceLatitude;
	private double sourceLongitude;
	private double destinationLatitude;
	private double destinationLongitude;
	private Handler mHandler;
	

	public TraceRouteAsyncTask(Context ctx, GoogleMap map, Handler handler) {
		mMapView = map;
		mContext = ctx;
		mHandler = handler;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		mDialog = ProgressDialog.show(mContext, "Aguarde", "Carregendo daods");
	}

	@Override
	protected Void doInBackground(Double... params) {
		
		sourceLatitude = params[0];
		sourceLongitude = params[1];
		destinationLatitude = params[2];
		destinationLongitude = params[3];

		mRoute = getDirections(new LatLng(sourceLatitude, sourceLongitude), new LatLng(
				destinationLatitude, destinationLongitude));
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		
		if (mRoute != null && !mRoute.getPoints().isEmpty()){
			PolylineOptions options = new PolylineOptions().width(POLILYNE_WIDTH)
					.color(Color.BLUE).visible(true);

			for (LatLng latlng : mRoute.getPoints()) {
				options.add(latlng);
			}

			mMapView.addPolyline(options);
			addMarkers();
		} else {
			if (mHandler != null) {
				Message msg = new Message();
				msg.what = TraceRouteActivity.ERROR_MESSAGE;
				mHandler.sendMessage(msg);
			}
		}
		
		cancelDialog();
	}
	
	@Override
	protected void onCancelled() {
		cancelDialog();
		super.onCancelled();
	}

	private Route getDirections(final LatLng origin, final LatLng destine) {

		String urlRota = String.format(Locale.US, MAP_URL, origin.latitude,
				origin.longitude, destine.latitude, destine.longitude);

		GoogleMapsParser parser;
		parser = new GoogleMapsParser(urlRota);
		return parser.parse();
	}
	
	private void addMarkers(){
		mMapView.addMarker(new MarkerOptions()
								.position(new LatLng(sourceLatitude, sourceLongitude))
								);
		mMapView.addMarker(new MarkerOptions()
		.position(new LatLng(destinationLatitude, destinationLongitude))
		);
	}
	
	private void cancelDialog(){
		try{
			if (mDialog != null && mDialog.isShowing()) {
				mDialog.dismiss();
				mDialog = null;
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}