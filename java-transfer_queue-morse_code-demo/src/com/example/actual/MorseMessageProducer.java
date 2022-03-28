package com.example.actual;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class MorseMessageProducer {
	private TransferQueue<Boolean> transferQueue;
	
	public MorseMessageProducer(TransferQueue<Boolean> transferQueue) {
		this.transferQueue = transferQueue;
	}

	//signalType true = morse light was turned on
	//signlaType false = morse light was turned off
	public void sendSignal(boolean signalType) {
		try {
			boolean added = transferQueue.tryTransfer(signalType, 4000, TimeUnit.MILLISECONDS);
			
			if(!added)
				System.out.println("Error, element transfer timed out.");

//			if (added)
//				System.out.printf("Producer transferred signal %b\n", signalType);
//			else
//				System.out.println("Producer transfer timed out");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
