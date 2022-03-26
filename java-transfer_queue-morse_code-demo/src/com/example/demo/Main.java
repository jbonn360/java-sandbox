package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class Main {

	// Demo guide available here: https://www.baeldung.com/java-transfer-queue

	public static void main(String[] args) throws InterruptedException {
		// given
		TransferQueue<String> transferQueue = new LinkedTransferQueue<>();
		ExecutorService exService = Executors.newFixedThreadPool(2);

		// runWith1ProducerOnly(transferQueue, exService);
		//runWith1ProducerAnd1Consumer(transferQueue, exService);
		runWithmanyProducersAndManyConsumers(transferQueue, exService);
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

	public static void runWithmanyProducersAndManyConsumers(TransferQueue<String> transferQueue,
			ExecutorService exService) throws InterruptedException {
		exService = Executors.newFixedThreadPool(3);
		
	    Producer producer1 = new Producer(transferQueue, "1", 3);
	    Producer producer2 = new Producer(transferQueue, "2", 3);
	    Consumer consumer1 = new Consumer(transferQueue, "1", 3);
	    Consumer consumer2 = new Consumer(transferQueue, "2", 3);

		// when
	    exService.execute(producer1);
	    exService.execute(producer2);
	    exService.execute(consumer1);
	    exService.execute(consumer2);;

		// then
		exService.awaitTermination(10_000, TimeUnit.MILLISECONDS);
		exService.shutdown();

		System.out.printf("producer1.numberOfProducedMessages is '%d' and it should be '3'.\n",
				producer1.numberOfProducedMessages.intValue());

		System.out.printf("producer2.numberOfConsumedMessages is '%d' and it should be '3'.\n",
				producer2.numberOfProducedMessages.intValue());
		
		System.out.printf("consumer1.numberOfConsumedMessages is '%d' and it should be '3'.\n",
				consumer1.numberOfConsumedMessages.intValue());
		
		System.out.printf("consumer2.numberOfConsumedMessages is '%d' and it should be '3'.\n",
				consumer2.numberOfConsumedMessages.intValue());
	}

}
