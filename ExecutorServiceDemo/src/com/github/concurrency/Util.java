package com.github.concurrency;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Util {
	/**
	 * This method connect to site by using {@link HttpURLConnection} object which takes url as 
	 * it's input parameter. If connection is successful then site is running.
	 * If site is running then method returns "Green" as status otherwise it returns "Red".
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getStatus(String url) {
		String result = "";
		try {
			URL siteURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) siteURL
					.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			int code = connection.getResponseCode();
			if (code == 200) {
				result = "Green";
			}
			else {
				result = "Red";
			}
		} catch (Exception e) {
			result = "Red";
		}
		return result;
	}
}
