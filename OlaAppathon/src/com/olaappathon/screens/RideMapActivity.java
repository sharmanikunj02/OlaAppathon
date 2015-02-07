package com.olaappathon.screens;

import java.util.HashMap;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.olaappathon.app.R;
import com.olaappathon.helper.Place;
import com.olaappathon.main.OlaAppathon;

/**
 * The Class PersonalGuardianControlDialog.
 */
public class RideMapActivity extends FragmentActivity implements LocationListener {

	/** The my map. */
	private static GoogleMap myMap;

	/** The marker options. */
	private static MarkerOptions markerOptions;

	/** The fragment. */
	private SupportMapFragment fragment;

	/** The my circle. */
	private Circle myCircle;

	/** The lat lng. */
	private static LatLng latLng = new LatLng(0, 0);

	/** The location manager. */
	public LocationManager locationManager;

	LatLng mLocation = null;

	HashMap<String, Place> mHMReference = new HashMap<String, Place>();

	Button leftButton;
	Button rightButton;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ride_map);
		leftButton = (Button) findViewById(R.id.left_button);
		rightButton = (Button) findViewById(R.id.right_button);
		locationManager = (LocationManager) getSystemService(RideMapActivity.LOCATION_SERVICE);
		try {
			MapsInitializer.initialize(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		fragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
		if (fragment == null) {
			fragment = SupportMapFragment.newInstance();
			fm.beginTransaction().replace(R.id.map, fragment).commit();
		}
		markerOptions = new MarkerOptions().position(latLng);
		myMap = fragment.getMap();
		myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		myMap.setMyLocationEnabled(true);

		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		// Creating a criteria object to retrieve provider
		Criteria criteria = new Criteria();

		// Getting the name of the best provider
		String provider = locationManager.getBestProvider(criteria, true);

		// Getting Current Location
		Location location = locationManager.getLastKnownLocation(provider);

		if (location != null) {
			onLocationChanged(location);
		}

		locationManager.requestLocationUpdates(provider, 20000, 0, this);
	}

	/**
	 * Drawing marker at latLng with color
	 */
	private Marker drawMarker(LatLng latLng, BitmapDescriptor icon) {
		// Setting the position for the marker
		markerOptions.position(latLng);
		markerOptions.icon(icon);
		// Placing a marker on the touched position
		Marker m = myMap.addMarker(markerOptions);

		return m;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			return false;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	/**
	 * Update map location.
	 * 
	 * @param location
	 *            the location
	 */
	public void updateMapLocation(LatLng location) {
		latLng = location;
		// myMap.clear();
		// myMap.addMarker(markerOptions.position(location));
		myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
	}

	/**
	 * Method createCircle.
	 * 
	 * @param point
	 *            LatLng
	 */
	public void createCircle(LatLng point) {
		if (myCircle != null) {
			myCircle.remove();
		}
		CircleOptions circleOptions = new CircleOptions().center(point) // set//
																		// center
				.radius(500) // set radius in meters
				.fillColor(getResources().getColor(R.color.circle_fill)) // semi-transparent
				.strokeColor(getResources().getColor(R.color.circle_fill)).strokeWidth(5);
		myCircle = myMap.addCircle(circleOptions);
		myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 15));
	}

	@Override
	public void onLocationChanged(Location location) {
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		LatLng latLng = new LatLng(latitude, longitude);
		updateMapLocation(latLng);
	}

	public void onRideNowClick(View v) {
		if (leftButton.getVisibility() == View.INVISIBLE && (!("Confirm Booking").equalsIgnoreCase(rightButton.getText().toString()))) {
			BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.act_nawd_on_f);
			drawMarker(latLng, icon);
			for (int i = 0; i < 3; i++) {
				LatLng localLatLng = new LatLng(latLng.latitude + .000001, latLng.longitude );
				drawMarker(localLatLng, icon);
			}
			rightButton.setText("Confirm Booking");
		} 
		else if(leftButton.getVisibility() == View.VISIBLE){
			OlaAppathon.getInstance().showToast("Booked Cab Successfully");
		}
		else {
			leftButton.setVisibility(View.VISIBLE);
		}
	}

	public void cancelBooking(View v) {
		OlaAppathon.getInstance().showToast("Cancelled Booking !!!");
		finish();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}

}
