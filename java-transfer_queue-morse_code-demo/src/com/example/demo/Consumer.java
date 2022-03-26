package com.example.demo;

import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable {
	private TransferQueue<String> transferQueue;

	private String name;

	private int numberOfMessagesToConsume;

	public AtomicInteger numberOfConsumedMessages = new AtomicInteger();
	
	public Consumer(TransferQueue<String> queue, String name, int numberOfMessagesToConsume) {
		this.transferQueue = queue;
		this.name = name;
		this.numberOfMessagesToConsume = numberOfMessagesToConsume;
	}

	@Override
	public void run() {
		for (int i = 0; i < numberOfMessagesToConsume; i++) {
			try {
				System.out.printf("Consumer %s is waiting to receive element\n", name);		
				String element = transferQueue.take();
				
				System.out.printf("Consumer %s received element %s\n", name, element);	
				longProcessing(element);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void longProcessing(String element) throws InterruptedException {
		numberOfConsumedMessages.incrementAndGet();
		Thread.sleep(500);
	}

}
