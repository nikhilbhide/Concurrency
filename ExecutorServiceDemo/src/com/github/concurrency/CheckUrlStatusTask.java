package com.github.concurrency;

import java.net.HttpURLConnection;
import java.util.concurrent.Callable;

/**
 * The task that checks the status of the site, whether site 
 * is running or not.
 * 
 * <p> It checks the status of site by create connection {@link HttpURLConnection} and invoking 
 * simple get api. If connection is successful then site is okay and running.
 * </p>
 * 
 * </p>If site is running then status is green otherwise its red.
 * 
 * @author nikhil.bhide
 *
 */

public class CheckUrlStatusTask implements Callable<String> {
	private String url;
	public CheckUrlStatusTask(String url) {
		this.url = url;
	}

	@Override
	public String call() {
		return "Url " + url + "status is : "+Util.getStatus(url);
	} 
}