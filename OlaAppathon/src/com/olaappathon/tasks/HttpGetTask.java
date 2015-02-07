package com.olaappathon.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;
import android.util.Log;

import com.olaappathon.interfaces.HttpInterface;
import com.olaappathon.main.OlaAppathon;

/**
 * The Class HttpGetTask
 */
public class HttpGetTask extends AsyncTask<String, Void, String> {
	private static final String TAG = HttpGetTask.class.getSimpleName();
	protected int id;
	protected HttpInterface httpInterface;
	protected HttpResponse httpResponse;
	public static String ERROR_HTTP_TIMEOUT_EXCEPTION = "ERROR_HTTP_TIMEOUT_EXCEPTION";
	public static String ERROR_NO_NETWORK_CONNECTION = "ERROR_NO_NETWORK_CONNECTION";
	public static String ERROR_UNAUTHORIZED = "UNAUTHORIZED";
	public int mTimeOutValue = 60000;

	/**
	 * Constructor for HttpGetTask.
	 * 
	 * @param idint
	 * @param httpInterface
	 *            HttpInterface
	 */
	public HttpGetTask(int id, HttpInterface httpInterface) {
		this.id = id;
		this.httpInterface = httpInterface;
	}

	/**
	 * doInBackground
	 */
	@Override
	protected String doInBackground(String... args) {
		if (!OlaAppathon.checkNetworkConnection()) {
			return ERROR_NO_NETWORK_CONNECTION;
		}
		StringBuffer result = null;

		String url = args[0];
		DefaultHttpClient client = new DefaultHttpClient();
		HttpParams mHttpParams = client.getParams();
		HttpConnectionParams.setConnectionTimeout(mHttpParams, mTimeOutValue);
		HttpConnectionParams.setSoTimeout(mHttpParams, mTimeOutValue);
		BufferedReader buffer = null;
		HttpGet httpGet = new HttpGet(url);
		try {
			Log.d(TAG, "url=" + url);
			this.httpResponse = client.execute(httpGet);
			InputStream content = httpResponse.getEntity().getContent();
			buffer = new BufferedReader(new InputStreamReader(content));
			result = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				result.append(line);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return ERROR_HTTP_TIMEOUT_EXCEPTION;
		} 
		String result_str = result.toString();
		return result_str;
	}

	/**
	 * Method onPostExecute.
	 * 
	 * @param result
	 *            String
	 */
	@Override
	protected void onPostExecute(String result) {
		if (result == null)
			return;
		if (httpInterface != null) {
			if (result.contains(ERROR_HTTP_TIMEOUT_EXCEPTION)) {
				httpInterface.onHttpResponse(0, httpResponse, result);
			} else {
				httpInterface.onHttpResponse(id, httpResponse, result);
			}
		}

	}

	/**
	 * Method getResponseFromConnection.
	 * 
	 * @param cnx
	 *            HttpURLConnection
	 * @return String
	 * @throws IOException
	 */
	protected static String getResponseFromConnection(HttpURLConnection cnx) throws IOException {
		String line = null;
		StringBuilder responseBuilder = null;
		BufferedReader inPut = null;
		String response = null;
		try {
			if (cnx.getResponseCode() == HttpURLConnection.HTTP_OK || cnx.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
				inPut = new BufferedReader(new InputStreamReader(cnx.getInputStream()));
			} else {
				inPut = new BufferedReader(new InputStreamReader(cnx.getErrorStream()));
			}
			responseBuilder = new StringBuilder();
			while ((line = inPut.readLine()) != null) {
				responseBuilder.append(line);
			}
			response = responseBuilder.toString();
		} finally {
			if (inPut != null) {
				try {
					inPut.close();
					inPut = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// Log.d(TAG, "Result:" + response);

		return response;
	}
}
