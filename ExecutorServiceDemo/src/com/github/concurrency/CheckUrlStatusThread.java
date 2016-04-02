package com.github.concurrency;

/**
 * The thread that checks the status of the site, whether site 
 * is running or not.
 * 
 * <p> It invokes getStatus method implemented in {@link Util}.
 *  If connection is successful then site is okay and running.
 * </p>
 * 
 * @author nikhil.bhide
 */

public class CheckUrlStatusThread implements Runnable {
	private String url;
	
	public CheckUrlStatusThread(String url) {
		this.url = url;
	}

	@Override
	public void run() {
		String status = Util.getStatus(url);
		System.out.println("Url " + url + "status is : "+status);
	} 
}