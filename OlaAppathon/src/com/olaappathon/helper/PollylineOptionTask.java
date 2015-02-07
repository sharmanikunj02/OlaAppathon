package com.olaappathon.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.olaappathon.interfaces.ParserPollylineInterface;
import com.olaappathon.main.OlaAppathon;

// TODO: Auto-generated Javadoc
/**
 *  A class to parse the Google Places in JSON format.
 */
public class PollylineOptionTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{
	
	/** The polly line interface. */
	ParserPollylineInterface pollyLineInterface;
	
	
	/**
	 * Instantiates a new pollyline option task.
	 *
	 * @param object the object
	 */
	public PollylineOptionTask(ParserPollylineInterface object) {
		this.pollyLineInterface =object;
	}
	// Parsing the data in non-ui thread    	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
	 */
	@Override
	protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
		
		JSONObject jObject;	
		List<List<HashMap<String, String>>> routes = null;			           
        
        try{
        	jObject = new JSONObject(jsonData[0]);
        	DirectionsJSONParser parser = new DirectionsJSONParser();
        	
        	// Starts parsing data
        	routes = parser.parse(jObject);    
        }catch(Exception e){
        	e.printStackTrace();
        }
        return routes;
	}
	
	// Executes in UI thread, after the parsing process
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(List<List<HashMap<String, String>>> result) {
		ArrayList<LatLng> points = null;
		PolylineOptions lineOptions = null;
		MarkerOptions markerOptions = new MarkerOptions();
		String distance = "";
		String duration = "";
		
		
		
		if(result.size()<1){
			Toast.makeText(OlaAppathon.getContext(), "No Points", Toast.LENGTH_SHORT).show();
			return;
		}
			
		
		// Traversing through all the routes
		for(int i=0;i<result.size();i++){
			points = new ArrayList<LatLng>();
			lineOptions = new PolylineOptions();
			
			// Fetching i-th route
			List<HashMap<String, String>> path = result.get(i);
			
			// Fetching all the points in i-th route
			for(int j=0;j<path.size();j++){
				HashMap<String,String> point = path.get(j);	
				
				if(j==0){	// Get distance from the list
					distance = (String)point.get("distance");						
					continue;
				}else if(j==1){ // Get duration from the list
					duration = (String)point.get("duration");
					continue;
				}
				
				double lat = Double.parseDouble(point.get("lat"));
				double lng = Double.parseDouble(point.get("lng"));
				LatLng position = new LatLng(lat, lng);	
				
				points.add(position);						
			}
			
			// Adding all the points in the route to LineOptions
			lineOptions.addAll(points);
			lineOptions.width(5);
			lineOptions.color(Color.RED);	
			
		}
		
		OlaAppathon.getInstance().showToast("Distance:"+distance + ", Duration:"+duration);
		//call class method
		pollyLineInterface.pollyLinePoints(lineOptions);
	}			
}   
