package com.olaappathon.screens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.olaappathon.app.R;
import com.olaappathon.dialog.PlaceDialogFragment;
import com.olaappathon.helper.CustomAutoCompleteTextView;
import com.olaappathon.helper.ImageJSONParser;
import com.olaappathon.helper.Place;
import com.olaappathon.helper.PlaceJSONParser;
import com.olaappathon.helper.PollylineOptionTask;
import com.olaappathon.interfaces.ParserPollylineInterface;
import com.olaappathon.main.OlaAppathon;

/**
 * The Class PersonalGuardianControlDialog.
 */
public class OlaMapActivity extends FragmentActivity implements OnMapClickListener, LocationListener {

	/** The my map. */
	private static GoogleMap myMap;

	/** The marker options. */
	private static MarkerOptions markerOptions;

	// Spinner in which the location `s are stored
	Spinner mSprPlaceType;

	/** The fragment. */
	private SupportMapFragment fragment;

	/** The my circle. */
	private Circle myCircle;

	/** The lat lng. */
	private static LatLng latLng = new LatLng(0, 0);

	/** The location manager. */
	public LocationManager locationManager;

	/** The location text. */
	private static TextView locationText;

	/** The geocoder. */
	private static Geocoder geocoder;
	// The location at which user touches the Google Map
	LatLng mLocation = null;

	// Calling for Autosearch text
	// start
	PlacesTask placesTask;
	PlaceSummaryAdapterTask placeSummaryAdapterTask;
	// End
	private List<LatLng> routePoints;
	CustomAutoCompleteTextView autoSearchText;
	ArrayList<LatLng> markerPoints;
	private String browserKey = "key=AIzaSyAYI9zP6dw5bS7YFv3uV29y5qmjQk0CMps";
	// Specifies the drawMarker() to draw the marker with default color
	private static final float UNDEFINED_COLOR = -1;
	// Stores near by places
	Place[] mPlaces = null;

	// A String array containing place types sent to Google Place service
	String[] mPlaceType = null;

	// A String array containing place types displayed to user
	String[] mPlaceTypeName = null;
	HashMap<String, Place> mHMReference = new HashMap<String, Place>();
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ola_zone_map);

		// Array of place types
		mPlaceType = getResources().getStringArray(R.array.place_type);

		// Array of place type names
		mPlaceTypeName = getResources().getStringArray(R.array.place_type_name);

		// Creating an array adapter with an array of Place types
		// to populate the spinner
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, mPlaceTypeName);

		// Getting reference to the Spinner
		mSprPlaceType = (Spinner) findViewById(R.id.spr_place_type);

		// Setting adapter on Spinner to set place types
		mSprPlaceType.setAdapter(adapter);

		// Initializing
		markerPoints = new ArrayList<LatLng>();

		autoSearchText = (CustomAutoCompleteTextView) findViewById(R.id.customEdit);
		autoSearchText.setThreshold(1);
		autoSearchText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// placesTask = new PlacesTask();
				// placesTask.execute(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});
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
		// Marker click listener
		myMap.setOnMarkerClickListener(markerClick);
		
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

		/**
	 * 
	 * ***/
		// Handling screen rotation
		if (savedInstanceState != null) {

			// Removes all the existing links from marker id to place object
			mHMReference.clear();

			// If near by places are already saved
			if (savedInstanceState.containsKey("places")) {

				// Retrieving the array of place objects
				mPlaces = (Place[]) savedInstanceState.getParcelableArray("places");

				// Traversing through each near by place object
				for (int i = 0; i < mPlaces.length; i++) {

					// Getting latitude and longitude of the i-th place
					LatLng point = new LatLng(Double.parseDouble(mPlaces[i].mLat), Double.parseDouble(mPlaces[i].mLng));

					// Drawing the marker corresponding to the i-th place
					Marker m = drawMarker(point, BitmapDescriptorFactory.HUE_VIOLET);

					// Linkng i-th place and its marker id
					mHMReference.put(m.getId(), mPlaces[i]);
				}
			}

			// If a touched location is already saved
			if (savedInstanceState.containsKey("location")) {

				// Retrieving the touched location and setting in member variable
				mLocation = (LatLng) savedInstanceState.getParcelable("location");

				// Drawing a marker at the touched location
				drawMarker(mLocation, BitmapDescriptorFactory.HUE_GREEN);
			}
		}
	}

	/** The marker click. */
	OnMarkerClickListener markerClick = new OnMarkerClickListener() {
		@Override
		public boolean onMarkerClick(Marker marker) {
			if (!mHMReference.containsKey(marker.getId()))
				return false;
			Place place = mHMReference.get(marker.getId());
			DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);

			// Creating a dialog fragment to display the photo
			PlaceDialogFragment dialogFragment = new PlaceDialogFragment(place, dm);

			// Getting a reference to Fragment Manager
			FragmentManager fm = getSupportFragmentManager();

			// Starting Fragment Transaction
			FragmentTransaction ft = fm.beginTransaction();

			// Adding the dialog fragment to the transaction
			ft.add(dialogFragment, "TAG");

			// Committing the fragment transaction
			ft.commit();

			return false;
		}
	};

	/**
	 * Drawing marker at latLng with color
	 */
	private Marker drawMarker(LatLng latLng, float color) {
		// Setting the position for the marker
		markerOptions.position(latLng);
		markerOptions.icon(BitmapDescriptorFactory.defaultMarker(color));
		// Placing a marker on the touched position
		Marker m = myMap.addMarker(markerOptions);

		return m;
	}

	private String getDirectionsUrl(LatLng origin, LatLng dest) {
		String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
		String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
		String sensor = "sensor=false";
		String parameters = str_origin + "&" + str_dest + "&" + sensor;
		String output = "json";
		String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
		return url;
	}

	private class DownloadTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... url) {
			String data = "";
			try {
				// Fetching the data from web service
				data = downloadUrl(url[0]);
			} catch (Exception e) {
				Log.d("Background Task", e.toString());
			}
			return data;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			PollylineOptionTask pollylineOptionTask = new PollylineOptionTask(new ParserPollylineInterface() {
				@Override
				public void pollyLinePoints(PolylineOptions option) {
					myMap.addPolyline(option);
					startImageDownloadingTask(option.getPoints());
				}
			});
			pollylineOptionTask.execute(result);
		}

		protected void startImageDownloadingTask(final List<LatLng> points) {
			final int size = points.size();			
/*			Handler handler = new Handler();
			for (i = 0; i<size-3 ;i++) {
			    handler.postDelayed(new Runnable() {			    	
			         @Override
			         public void run() {
			        	 getNearestPlace(points.get(i));
							myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(points.get(i), 15));					         }
			         }, 1000);
			    createCircle(points.get(i));
			    } 
*/	
			getNearestPlace(points.get(size / 2));
			createCircle(points.get(size / 2));
		}
	}

	/** A method to download json data from url */
	private String downloadUrl(String strUrl) throws IOException {
		String data = "";
		InputStream iStream = null;
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(strUrl);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.connect();
			iStream = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
			StringBuffer sb = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			data = sb.toString();
			br.close();

		} catch (Exception e) {
			Log.d("Exception while downloading url", e.toString());
		} finally {
			iStream.close();
			urlConnection.disconnect();
		}
		return data;
	}

	public void getNearestPlace(LatLng latLng) {

		int selectedPosition = mSprPlaceType.getSelectedItemPosition();
		String type = mPlaceType[selectedPosition];
		// mGoogleMap.clear();

		// Center point of circle
		drawMarker(latLng, BitmapDescriptorFactory.HUE_VIOLET);
		StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
		sb.append("location=" + latLng.latitude + "," + latLng.longitude);
		sb.append("&radius=250");
		if (!type.equalsIgnoreCase("all")) {
			sb.append("&types=" + type);
		}
		sb.append("&sensor=true&");
		sb.append(browserKey);
		ImageDownloadTask imageTask = new ImageDownloadTask();
		imageTask.execute(sb.toString());

	}

	/** A class, to download Google Places */
	private class ImageDownloadTask extends AsyncTask<String, Integer, String> {

		String data = null;

		// Invoked by execute() method of this object
		@Override
		protected String doInBackground(String... url) {
			try {
				data = downloadUrl(url[0]);
				if (data == null)
					return "";
			} catch (Exception e) {
				Log.d("Background Task", e.toString());
			}
			return data;
		}

		// Executed after the complete execution of doInBackground() method
		@Override
		protected void onPostExecute(String result) {
			ParserTask parserTask = new ParserTask();

			// Start parsing the Google places in JSON format
			// Invokes the "doInBackground()" method of ParserTask
			parserTask.execute(result);
		}
	}

	// Fetches all places from GooglePlaces AutoComplete Web Service
	private class PlacesTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... place) {
			// For storing data from web service
			String data = "";
			String input = "";

			try {
				input = "input=" + URLEncoder.encode(place[0], "utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}

			// place type to be searched
			String types = "types=geocode";

			// Sensor enabled
			String sensor = "sensor=false";

			// Building the parameters to the web service
			String parameters = input + "&" + types + "&" + sensor + "&" + browserKey;

			// Output format
			String output = "json";

			// Building the url to the web service
			String url = "https://maps.googleapis.com/maps/api/place/autocomplete/" + output + "?" + parameters;

			try {
				// Fetching the data from web service in background
				data = downloadUrl(url);
			} catch (Exception e) {
				Log.d("Background Task", e.toString());
			}
			return data;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			// Creating PlaceSummaryAdapterTask
			placeSummaryAdapterTask = new PlaceSummaryAdapterTask();

			// Starting Parsing the JSON string returned by Web Service
			placeSummaryAdapterTask.execute(result);
		}
	}

	/** A class to parse the Google Places in JSON format */
	private class PlaceSummaryAdapterTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {

		JSONObject jObject;

		@Override
		protected List<HashMap<String, String>> doInBackground(String... jsonData) {

			List<HashMap<String, String>> places = null;

			PlaceJSONParser placeJsonParser = new PlaceJSONParser();

			try {
				jObject = new JSONObject(jsonData[0]);

				// Getting the parsed data as a List construct
				places = placeJsonParser.parse(jObject);

			} catch (Exception e) {
				Log.d("Exception", e.toString());
			}
			return places;
		}

		@Override
		protected void onPostExecute(List<HashMap<String, String>> result) {

			String[] from = new String[] { "description" };
			int[] to = new int[] { android.R.id.text1 };

			// Creating a SimpleAdapter for the AutoCompleteTextView
			SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), result, android.R.layout.simple_list_item_1, from, to);

			// Setting the adapter
			autoSearchText.setAdapter(adapter);
		}
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

	/*
	 * private void StartLocationTracking() { Context context = OlaAppathon.getContext(); Intent intent = new Intent(context, LocateService.class);
	 * intent.setAction(LocateService.ACTION_LOCATE_DEVICE_NETWORK); OlaAppathon.getContext().startService(intent); }
	 */
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
	public void onMapClick(LatLng point) {

		// Already two locations
		if (markerPoints.size() > 1) {
			markerPoints.clear();
			myMap.clear();
		}

		// Adding new item to the ArrayList
		markerPoints.add(point);

		// Creating MarkerOptions
		MarkerOptions options = new MarkerOptions();

		// Setting the position of the marker
		options.position(point);

		/**
		 * For the start location, the color of marker is GREEN and for the end location, the color of marker is RED.
		 */
		if (markerPoints.size() == 1) {
			options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
		} else if (markerPoints.size() == 2) {
			options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
		}

		// Add new marker to the Google Map Android API V2
		myMap.addMarker(options);

		// Checks, whether start and end locations are captured
		if (markerPoints.size() >= 2) {
			LatLng origin = markerPoints.get(0);
			LatLng dest = markerPoints.get(1);

			// Getting URL to the Google Directions API
			String url = getDirectionsUrl(origin, dest);

			DownloadTask downloadTask = new DownloadTask();

			// Start downloading json data from Google Directions API
			downloadTask.execute(url);
		}

	}

	/** A class to parse the Google Places in JSON format */
	private class ParserTask extends AsyncTask<String, Integer, Place[]> {

		JSONObject jObject;

		// Invoked by execute() method of this object
		@Override
		protected Place[] doInBackground(String... jsonData) {

			Place[] places = null;
			ImageJSONParser placeJsonParser = new ImageJSONParser();
			try {
				jObject = new JSONObject(jsonData[0]);
				/** Getting the parsed data as a List construct */
				places = placeJsonParser.parse(jObject);

			} catch (Exception e) {
				Log.d("Exception", e.toString());
			}
			return places;
		}

		// Executed after the complete execution of doInBackground() method
		@Override
		protected void onPostExecute(Place[] places) {

			mPlaces = places;

			for (int i = 0; i < places.length; i++) {
				Place place = places[i];

				// Getting latitude of the place
				double lat = Double.parseDouble(place.mLat);

				// Getting longitude of the place
				double lng = Double.parseDouble(place.mLng);

				LatLng latLng = new LatLng(lat, lng);

				Marker m = drawMarker(latLng, BitmapDescriptorFactory.HUE_VIOLET);

				// Adding place reference to HashMap with marker id as HashMap key
				// to get its reference in infowindow click event listener
				mHMReference.put(m.getId(), place);
			}
		}
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
