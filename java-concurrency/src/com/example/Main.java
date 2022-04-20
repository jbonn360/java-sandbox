package com.example;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	
	 private static Object s = new Object();
	 private static int count = 0;

	public static void main(String[] args) {
		//execute();
		
		ExecutorService executor = Executors.newFixedThreadPool(10);		
		executor.submit(new Task(executor, 0));
		
		//System.out.println(Thread.activeCount());
	}
	
	public static void execute() {
		Executor executor = new Invoker();
		
		for(int i = 0; i < 1000; i++) {
			System.out.print(i + " -> ");
			executor.execute(() -> {
				System.out.print("a|");
				for(int j = 0; j < 100000000; j ++) {
					System.out.print("");
				}
			});
			System.out.println(" b ");
		}
		
		
	}

}
