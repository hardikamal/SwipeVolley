/**
 * 
 */
package com.deardhruv.swipevolley;

import java.net.URLEncoder;

import android.os.Bundle;

/**
 * @author DearDhruv
 * 
 */
public class ServiceURL {

	public static final String	INDENT		= "indent";
	public static final String	IMAGE_URL	= "image_url";
	public static final String	mainURL		= "http://www.json-generator.com/j/cliVQMHeZK?";

	// "http://www.json-generator.com/j/cfdQrdxvma?";
	// http://www.json-generator.com/j/cfxqvbSsCW?indent=4
	@SuppressWarnings("deprecation")
	public static String encodeGETUrl(Bundle parameters) {
		StringBuilder sb = new StringBuilder();

		if (parameters != null && parameters.size() > 0) {
			boolean first = true;
			for (String key : parameters.keySet()) {
				if (key != null) {

					if (first) {
						first = false;
					}
					else {
						sb.append("&");
					}
					String value = "";
					Object object = parameters.get(key);
					if (object != null) {
						value = String.valueOf(object);
					}

					try {
						sb.append(URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8"));
					}
					catch (Exception e) {
						sb.append(URLEncoder.encode(key) + "=" + URLEncoder.encode(value));
					}
				}
			}
		}
		return sb.toString();
	}

	public static String encodeUrl(String url, Bundle mParams) {
		return url + encodeGETUrl(mParams);
	}

}
