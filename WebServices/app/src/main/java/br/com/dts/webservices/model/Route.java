/** 
* COPYRIGHT (c) 2013, MOTOROLA. ALL RIGHTS RESERVED
* 
* REVISION HISTORY
*
* DATA          CORE ID       CR NUMBER      COMMENTS
* ============================================================
* 12/06/2013    BWCH73        47545         Initial creation
*/
package br.com.dts.webservices.model;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by diegosouza on 8/3/16.
 */
public class Route {
	private final List<LatLng> mPoints;
	private String mPolyline;

	public Route() {
		mPoints = new ArrayList<LatLng>();
	}

	public void addPoints(final List<LatLng> points) {
		this.mPoints.addAll(points);
	}

	public List<LatLng> getPoints() {
		return mPoints;
	}

	public void setPolyline(String polyline) {
		this.mPolyline = polyline;
	}

	public String getPolyline() {
		return mPolyline;
	}
}
