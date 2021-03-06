
package com.android.volley.examples.toolbox.updated;

import java.io.File;

import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.examples.toolbox.MultipartRequest;
import com.android.volley.examples.toolbox.MyVolley;
import com.android.volley.toolbox.JsonObjectRequest;

/**
 * Common class for requesting network query or uploading file.
 * 
 * @author DearDhruv
 */
public class JSONMultipleImgRequestResponse {
	boolean hasMultipleFiles = false;

	public JSONMultipleImgRequestResponse(Context cntx) {
		mContext = cntx;
	}

	@SuppressWarnings("unused")
	private final Context mContext;
	private int reqCode;
	private IJSONParseListener listner;

	private boolean isFile = false;
	private String file_path = "", key = "";
	private Bundle fileBundle;

	public void getResponse(String url, final int requestCode, IJSONParseListener mParseListener) {
		getResponse(url, requestCode, mParseListener, null);
	}

	public void getResponse(String url, final int requestCode, IJSONParseListener mParseListener,
			Bundle params) {
		this.listner = mParseListener;
		this.reqCode = requestCode;

		Response.Listener<JSONObject> sListener = new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				if (listner != null) {
					listner.SuccessResponse(response, reqCode);
				}
			}
		};

		Response.ErrorListener eListener = new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				if (listner != null) {
					listner.ErrorResponse(error, reqCode);
				}
			}
		};

		if (!isFile) {
			JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null,
					sListener, eListener);
			MyVolley.getRequestQueue().add(jsObjRequest);
		} else {
			if (file_path != null) {
				File mFile = new File(file_path);
				MultipartRequest multipartRequest;
				if (hasMultipleFiles) {
					multipartRequest = new MultipartRequest(url, eListener, sListener, fileBundle,
							params);
				} else {
					multipartRequest = new MultipartRequest(url, eListener, sListener, key, mFile,
							params);
				}
				MyVolley.getRequestQueue().add(multipartRequest);
			} else {
				throw new NullPointerException("File path is null");
			}
		}
	}

	/**
	 * @return the isFile
	 */
	public boolean isFile() {
		return isFile;
	}

	/**
	 * @param isFile the File to set
	 */
	public void setFile(String param, String path) {
		hasMultipleFiles = false;
		if (path != null && param != null) {
			key = param;
			file_path = path;
			this.isFile = true;
		}
	}

	public void setFile(Bundle b) {
		hasMultipleFiles = true;
		fileBundle = b;
		this.isFile = true;
		if (fileBundle != null && fileBundle.size() > 0) {
			for (String key : fileBundle.keySet()) {
				if (key != null) {

					String value = "";
					Object object = fileBundle.get(key);
					if (object != null) {
						value = String.valueOf(object);
					}

					try {
						Log.i("System out", "Image path :-" + key);
						Log.i("System out", "Image path :-" + value);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
