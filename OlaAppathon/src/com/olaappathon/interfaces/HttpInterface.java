package com.olaappathon.interfaces;

import org.apache.http.HttpResponse;

/**
 * The Interface
 */
public interface HttpInterface
{
	/**
	 * Method onHttpResponse.
	 * @param id int
	 * @param response HttpResponse
	 * @param result String
	 */
	public void onHttpResponse(int id, HttpResponse response, String result);
}
