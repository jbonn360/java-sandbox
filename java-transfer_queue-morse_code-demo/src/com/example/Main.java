package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// given
		TransferQueue<String> transferQueue = new LinkedTransferQueue<>();
		ExecutorService exService = Executors.newFixedThreadPool(2);
		
		//runWith1ProducerOnly(transferQueue, exService);
		runWith1ProducerAnd1Consumer(transferQueue, exService);
	}

	public static void runWith1ProducerOnly(TransferQueue<String> transferQueue, ExecutorService exService)
			throws InterruptedException {
		Producer producer = new Producer(transferQueue, "1", 3);

		// when
		exService.execute(producer);

		// then
		exService.awaitTermination(5000, TimeUnit.MILLISECONDS);
		exService.shutdown();

		System.out.printf("producer.numberOfProducedMessages is '%d' and it should be '0'.\n",
				producer.numberOfProducedMessages.intValue());
	}
	
	public static void runWith1ProducerAnd1Consumer(TransferQueue<String> transferQueue, ExecutorService exService)
			throws InterruptedException {
		Producer producer = new Producer(transferQueue, "1", 3);
		Consumer consumer = new Consumer(transferQueue, "1", 3);

		 // when
	    exService.execute(producer);
	    exService.execute(consumer);

	    // then
	    exService.awaitTermination(5000, TimeUnit.MILLISECONDS);
	    exService.shutdown();

		System.out.printf("producer.numberOfProducedMessages is '%d' and it should be '3'.\n",
				producer.numberOfProducedMessages.intValue());
		
		System.out.printf("consumer.numberOfConsumedMessages is '%d' and it should be '3'.\n",
				consumer.numberOfConsumedMessages.intValue());
	}

}
