package com.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		final Integer[] integers = { 1, 1, 5, 2, 9, 2, 5, 90, 33, 87 };
		final String[] strings = { "tommy", "pinochio", "gertrude", "diana", "rex" };

		print("=========== Starting Collections ===========");
		print("'integers' is an Integer[] with value: " + printArray(integers));
		print("'strings' is a String[] with value: " + printArray(strings));

		chill(3);

		print("\n=========== java.util.Queue ===========");
		print("===== Blocking Queue =====");
		print("Forces threads to wait when attempting a retrieval or deletion based on the current state.");
		print("A thread waits on the Queue to be non-empty when attempting a retrieval,");
		print("or for it to become empty when adding a new element.");

		chill(3);

		final BlockingQueue<String> stringBlockingQueue = new ArrayBlockingQueue<String>(strings.length);

		System.out.print("\nAdding values to queue: ");
		chill(3);

		for (int i = 0; i < strings.length; i++) {
			chill(0.5);

			stringBlockingQueue.offer(strings[i]);
			System.out.print(strings[i]);
			chill(0.5);

			if (i < strings.length - 1) {
				System.out.print(" | ");
				chill(0.5);
			}
			;
		}

		System.out.print("\nPolling the queue: ");
		printQueue(stringBlockingQueue);
		chill(3);

		print("\n===== Priority Blocking Queue =====");
		print("Priority queues ensure that the elements are sorted via their individual priority,");
		print("based on their value. Elements in this queue must be of a type that implements the ");
		print("Comparable interface. In this case, we're using a PriorityBlockingQueue that also");
		print("includes Blocking Queue behaviour.");
		
		PriorityBlockingQueue<String> stringPriorityBlockingQueue = new PriorityBlockingQueue<String>();

		stringPriorityBlockingQueue.addAll(Arrays.asList(strings));

		System.out.print("\nPolling the queue: ");
		printQueue(stringPriorityBlockingQueue);
		chill(3);

		print("\n=========== java.util.Set ===========");
		final Set<Integer> integerSortedSet = new TreeSet<Integer>(Arrays.asList(integers));

		print("'integerSortedSet' is a TreeSet<Integer>; it automatically sorts items: " + integerSortedSet);
		print("TreeSet#descendingSet outputs the items in reverse order: "
				+ ((TreeSet<Integer>) integerSortedSet).descendingSet());

		final Set<String> stringSortedSet = new TreeSet<String>(Arrays.asList(strings));
		print("\n'stringSortedSet' is a TreeSet<String>; it also sorts the items (alphabetically in this case): "
				+ stringSortedSet);
		print("The TreeSet does not tolerate null values.");

		chill(3);

		print("\n=========== java.util.Map ===========");
		final Map integerHashMap = new HashMap<String, Integer>();
	}

	private static void print(String toPrint) {
		System.out.println(toPrint);
		chill(3);
	}

	private static <T> String printArray(T[] array) {
		final StringBuilder sb = new StringBuilder();

		sb.append("[");
		Arrays.stream(array).map(obj -> obj == null ? "null" : obj).forEach(i -> sb.append(String.format("%s, ", i)));
		sb.replace(sb.length() - 2, sb.length(), "]");

		return sb.toString();
	}

	private static <T> void printQueue(Queue<T> queue) {

		// peeking retrieves the next element in the queue without removing it
		while (queue.peek() != null) {
			chill(0.5);

			// polling retrieves the next element in the queue AND removes it
			System.out.print(queue.poll());
			chill(0.5);

			if (queue.peek() != null) {
				System.out.print(" | ");
				chill(0.5);
			}
		}
		print("");
	}

	private static void chill(double seconds) {
		final long millis = Math.round(seconds * 1000);

		try {
			Thread.sleep(millis);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
