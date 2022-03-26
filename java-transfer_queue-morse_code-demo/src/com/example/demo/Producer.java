package com.example.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {
	private TransferQueue<String> transferQueue;

	private String name;

	private Integer numberOfMessagesToProduce;

	public AtomicInteger numberOfProducedMessages = new AtomicInteger();

	public Producer(TransferQueue<String> queue, String name, Integer numberOfMessagesToProduce) {
		this.transferQueue = queue;
		this.name = name;
		this.numberOfMessagesToProduce = numberOfMessagesToProduce;
	}

	@Override
	public void run() {
		for (int i = 0; i < numberOfMessagesToProduce; i++) {
			try {
				System.out.printf("Producer %s is trying to transfer\n", name);				
				String element = "A" + i;
				boolean added = transferQueue.tryTransfer(element, 4000, TimeUnit.MILLISECONDS);				
				if (added) {
					numberOfProducedMessages.incrementAndGet();
					System.out.printf("Producer %s transferred element %s \n", name, element);
				}else
					System.out.printf("Producer %s transfer timed out\n", name);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
