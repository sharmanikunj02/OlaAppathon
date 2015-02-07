package com.olaappathon.screens;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.olaappathin.services.LocateService;
import com.olaappathon.app.R;
import com.olaappathon.main.OlaAppathon;

/**
 * The Class PersonalGuardianControlDialog.
 */
public class OlaMapActivity extends FragmentActivity implements OnMapClickListener , LocationListener{

	/** The my map. */
	private static GoogleMap myMap;

	/** The marker options. */
	private static MarkerOptions markerOptions;

	/** The fragment. */
	private SupportMapFragment fragment;

	/** The lat lng. */
	private static LatLng latLng = new LatLng(0, 0);

	/** The location manager. */
	public LocationManager locationManager;

	/** The location text. */
	private static TextView locationText;

	/** The geocoder. */
	private static Geocoder geocoder;
	
	private List<LatLng> routePoints;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ola_zone_map);
		locationManager = (LocationManager) getSystemService(OlaMapActivity.LOCATION_SERVICE);
		geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
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
		myMap.setOnMapClickListener(this);
		updateMapLocation(latLng);
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
		myMap.clear();
		myMap.addMarker(markerOptions.position(location));
//		myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
		sendBatteryInfo();
	}

	private void sendBatteryInfo() {
		Context context = OlaAppathon.getContext();
		Intent intent = new Intent(context, LocateService.class);
				intent.setAction(LocateService.ACTION_LOCATE_DEVICE_NETWORK);
				OlaAppathon.getContext().startService(intent);
	}
	
	/**
	 * Gets the address.
	 * 
	 * @param latitude
	 *            the latitude
	 * @param longitude
	 *            the longitude
	 * @return the address
	 */
	private String getAddress(double latitude, double longitude) {
		StringBuilder result = new StringBuilder();
		try {
			List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
			if (addresses.size() > 0) {
				Address address = addresses.get(0);
				result.append(OlaAppathon.getContext().getString(R.string.common_your_location_is, address.getThoroughfare(), address.getLocality(),
						address.getCountryName()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

}
