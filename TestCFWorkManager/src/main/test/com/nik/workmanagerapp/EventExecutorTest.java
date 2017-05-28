/**
 * 
 */
package com.nik.workmanagerapp;

import org.junit.Test;
import com.nik.cfworkmanagerapp.executor.EventExecutor;

public class EventExecutorTest {

	@Test
	public void test() {
		EventExecutor eventExecutor = new EventExecutor();
		eventExecutor.executeTask();
	}
}