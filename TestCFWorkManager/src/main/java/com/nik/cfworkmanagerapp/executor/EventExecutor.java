package com.nik.cfworkmanagerapp.executor;

import java.util.concurrent.CompletableFuture;
import org.springframework.core.task.TaskExecutor;
import com.nik.cfworkmanagerapp.spring.SpringApplicationContext;

public class EventExecutor {
	public CompletableFuture<String> executeTask() {
		TaskExecutor cfExecutor = (TaskExecutor) SpringApplicationContext.getBean("cfTaskExecutor");		
		return CompletableFuture.supplyAsync(()-> getData(), cfExecutor);
	}

	private String getData()  {
		return "event complete";
	}
}