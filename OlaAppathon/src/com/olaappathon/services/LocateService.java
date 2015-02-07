package com.olaappathon.services;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.olaappathon.app.R;
import com.olaappathon.main.OlaAppathon;
import com.olaappathon.main.Platform;
import com.olaappathon.system.DateTimeUtilities;

/**
 * The Class LocateService.
 */
public class LocateService extends Service implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener,
		LocationListener {
	// public static final String TAG = LocateService.class.getSimpleName();
	/** The Constant ACTION_LOCATE_DEVICE_NETWORK. */
	public static final String ACTION_LOCATE_DEVICE_NETWORK = "network";

	/** The Constant ACTION_LOCATE_DEVICE_GPS. */
	public static final String ACTION_LOCATE_DEVICE_GPS = "gps";


	/** The location client. */
	private LocationClient locationClient;

	/** The locate device request. */
	private LocationRequest locateDeviceRequest;

	/** The binder. */
	private final IBinder binder = new LocateServiceBinder();

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		if (Platform.isGooglePlayServicesAvailable(this)) {
			locationClient = new LocationClient(this, this, this);
			locationClient.connect();
		} else {
			handleNoGooglePlayServices();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onDestroy()
	 */
	@Override
	public void onDestroy() {
		if (locationClient != null) {
			locationClient.removeLocationUpdates(this);
			locationClient.disconnect();
			locationClient = null;
		}
		super.onDestroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	/**
	 * Action Methods.
	 * 
	 * @param intent
	 *            the intent
	 * @param flags
	 *            the flags
	 * @param startId
	 *            the start id
	 * @return the int
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (Platform.isGooglePlayServicesAvailable(this)) {
			String action = (intent == null ? null : intent.getAction());

			if (ACTION_LOCATE_DEVICE_NETWORK.equals(action)) {
				handleLocateDevice(intent, ACTION_LOCATE_DEVICE_NETWORK);
			} else if (ACTION_LOCATE_DEVICE_GPS.equals(action)) {
				handleLocateDevice(intent, ACTION_LOCATE_DEVICE_GPS);
			}
		}
		return START_STICKY;
	}

	/**
	 * Handle locate device.
	 * 
	 * @param intent
	 *            the intent
	 * @param type
	 *            the type
	 */
	private void handleLocateDevice(Intent intent, String type) {
		locateDeviceRequest = LocationRequest.create();
		// Per request from Reji disabling network, GPS only
		// if ("network".equalsIgnoreCase(type)) {
		// locateDeviceRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
		// } else {
		locateDeviceRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		// }
		locateDeviceRequest.setInterval(DateTimeUtilities.ONEMINUTE);
		onConnected(null);
	}

	/**
	 * Handle no google play services.
	 */
	private void handleNoGooglePlayServices() {
		OlaAppathon.getInstance().showAlertAndNavigateToGooglePlayServices(this, this.getString(R.string.message_install_Google_play_services));
	}

	/**
	 * Google Play Services.
	 * 
	 * @param result
	 *            the result
	 */
	@Override
	public void onConnectionFailed(ConnectionResult result) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks#onConnected(android.os.Bundle)
	 */
	@Override
	public void onConnected(Bundle connectionHint) {
		if ((locationClient != null) && locationClient.isConnected()) {
			// Location request preference order is Locate Device then Remote Tracking.
			if (locateDeviceRequest != null) {
				locationClient.requestLocationUpdates(locateDeviceRequest, this);
			} else {
				locationClient.removeLocationUpdates(this);
				stopSelf();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks#onDisconnected()
	 */
	@Override
	public void onDisconnected() {
	}

	/**
	 * Location Listener.
	 * 
	 * @param location
	 *            the location
	 */
	@Override
	public void onLocationChanged(Location location) {

		// After we have done a locate either stop or resume tracking.
		if (locateDeviceRequest != null) {
			locateDeviceRequest = null;
			onConnected(null);
		}
	}

	/**
	 * Service Binder.
	 */
	private class LocateServiceBinder extends Binder {

		/**
		 * Gets the service.
		 * 
		 * @return the service
		 */
		public LocateService getService() {
			return LocateService.this;
		}
	}

}
