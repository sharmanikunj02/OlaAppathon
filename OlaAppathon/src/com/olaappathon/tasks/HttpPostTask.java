package com.olaappathon.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;

import android.os.AsyncTask;
import android.util.Log;

import com.olaappathon.interfaces.HttpInterface;

/**
 * The Class HttpPostTask.
 */
public class HttpPostTask extends AsyncTask<String, Void, String> {
	
	/** The id. */
	private int id;
	
	/** The http response. */
	protected HttpResponse httpResponse;
	
	/** The post json array. */
	protected JSONArray postJsonArray = null;
	
	/** The http interface. */
	protected HttpInterface httpInterface;

	/**
	 * Instantiates a new http post task.
	 *
	 * @param id the id
	 * @param httpInterface the http interface
	 */
	public HttpPostTask(int id, HttpInterface httpInterface) {
		this.id = id;
		this.httpInterface = httpInterface;
	}

	/**
	 * Sets the json.
	 *
	 * @param json the new json
	 */
	public void setJson(JSONArray json) {
		postJsonArray = json;
	}

	/**
	 * Method doInBackground.
	 * 
	 * @param args
	 *            String[]
	 * @return String
	 */
	@Override
	protected String doInBackground(String... args) {
		String postJsonData = null;
		String url = args[0];
		if (postJsonArray != null) {
			postJsonData = postJsonArray.toString();
		}
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			if (postJsonArray != null) {
				StringEntity se = new StringEntity(postJsonData);
				httpPost.setEntity(se);
				se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
				se.setContentEncoding(new BasicHeader(HTTP.CONTENT_ENCODING, "UTF-8"));
			}
			this.httpResponse = httpClient.execute(httpPost);
			BufferedReader buffer = null;
			InputStream content = this.httpResponse.getEntity().getContent();
			buffer = new BufferedReader(new InputStreamReader(content));
			StringBuffer result = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				result.append(line);
			}
			postJsonArray = null;
			return result.toString();
		} catch (IOException e) {
			Log.i("HttpPostTask", "ioexception" + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method onPostExecute.
	 * 
	 * @param result
	 *            String
	 */
	@Override
	protected void onPostExecute(String result) {
		if (httpInterface != null) {
			httpInterface.onHttpResponse(id, httpResponse, result);
		}

	}
}
