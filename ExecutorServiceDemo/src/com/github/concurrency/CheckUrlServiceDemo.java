package com.github.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CheckUrlServiceDemo {
	private HashSet<String> defaltUrlSet =  new HashSet<String> (Arrays.asList( "http://crunchify.com", "http://yahoo.com",
			"http://www.ebay.com", "http://google.com",
			"http://www.example.co", "https://paypal.com",
			"http://bing.com/", "http://techcrunch.com/",
			"http://mashable.com/", "http://thenextweb.com/",
			"http://wordpress.com/", "http://wordpress.org/",
			"http://example.com/", "http://sjsu.edu/",
			"http://ebay.co.uk/", "http://google.co.uk/",
			"http://www.wikipedia.org/",
			"http://en.wikipedia.org/wiki/Main_Page"));
	private int averageExecutionNumber = 10;
	
	/**
	 * This method first checks whether user has entered any urls. 
	 * If not then urls from defaultUrlSet are used.
	 * @param urls
	 */
	private void populateUrlSet(String [] urls) {
		if(urls.length!=0) {
			defaltUrlSet.clear();
			for(String url:urls) {
				defaltUrlSet.add(url);
			}
		}
	}

	private void getStatusByExecutorService() {
		ExecutorService service = Executors.newFixedThreadPool(20);
		List<Long> executionTimes = new ArrayList<Long>();
		for(int counter = 1; counter <=averageExecutionNumber; counter++) {
			long startTime = System.currentTimeMillis();
			List<Future<String>> statuses = new ArrayList<>();
			defaltUrlSet.forEach(url -> {
				CheckUrlStatusTask urlStatusTask = new CheckUrlStatusTask(url);
				Future<String> status = service.submit(urlStatusTask);
				statuses.add(status);			
			});

			statuses.forEach(status -> {
				try {
					System.out.println(status.get());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			long endTime = System.currentTimeMillis();
			executionTimes.add(endTime - startTime);
		}
		service.shutdown();
		OptionalDouble averageExecutionTime = executionTimes
											.stream()
											.mapToDouble(executionTime -> executionTime)
											.average();
		System.out.println("Average execution time taken by executor service for "+ averageExecutionNumber + " executions :" +averageExecutionTime.getAsDouble());
	}
	
	private void getStatusBySimpleThreadService() {
		List<Long> executionTimes = new ArrayList<Long>();
		for(int counter = 1; counter <=averageExecutionNumber; counter++) {
			long startTime = System.currentTimeMillis();
			List<Future<String>> statuses = new ArrayList<>();
			defaltUrlSet.forEach(url -> {
				System.out.println("Url " + url + "status is : "+Util.getStatus(url));
			});
			long endTime = System.currentTimeMillis();
			executionTimes.add(endTime - startTime);
		}
		OptionalDouble averageExecutionTime = executionTimes
											.stream()
											.mapToDouble(executionTime -> executionTime)
											.average();
		System.out.println("Average execution time taken by simple thread service for "+ averageExecutionNumber + " executions :" +averageExecutionTime.getAsDouble());
	}
	
	public static void main (String[] args) throws InterruptedException, ExecutionException {
		CheckUrlServiceDemo instance = new CheckUrlServiceDemo();
		instance.populateUrlSet(args);
		instance.getStatusByExecutorService();
		instance.getStatusBySimpleThreadService();
	}
}