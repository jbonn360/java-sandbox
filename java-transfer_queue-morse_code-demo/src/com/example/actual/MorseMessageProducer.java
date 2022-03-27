package com.example.actual;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class MorseMessageProducer {
	private TransferQueue<Boolean> transferQueue;
	
	public MorseMessageProducer(TransferQueue<Boolean> transferQueue) {
		this.transferQueue = transferQueue;
	}

	public void sendSignal() {
		try {
			System.out.printf("Producer is trying to transfer\n");

			boolean added = transferQueue.tryTransfer(true, 4000, TimeUnit.MILLISECONDS);

			if (added)
				System.out.println("Producer transferred signal");
			else
				System.out.println("Producer transfer timed out");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
