package com.example;

import java.util.concurrent.ExecutorService;

public class Task implements Runnable {	
	@Override
	public void run() {
		System.out.println("Thread is running");
	}
	
	public Task(ExecutorService service, int count) {
		System.out.println("Running thread " + count);
		run();
		
		service.submit(new Task(service, count + 1));
	}

}
