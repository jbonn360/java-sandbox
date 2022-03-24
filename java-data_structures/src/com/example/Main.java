package com.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		final Integer[] integers = { 1, 1, 5, 2, 9, 2, 5, 90, 33, 87 };
		final String[] strings = { "tommy", "pinochio", "gertrude", "diana", "rex" };
		
		print("=========== Starting Collections ===========");		
		
		print("'integers' is an Integer[] with value: " + printArray(integers));		
		print("'strings' is a String[] with value: " + printArray(strings));
		
		print("\n=========== java.util.Set ===========");		
		final Set<Integer> integerSortedSet = new TreeSet<Integer>(Arrays.asList(integers));
		
		print("'integerSortedSet' is a TreeSet<Integer>; it automatically sorts items: " + integerSortedSet);
		print("TreeSet#descendingSet outputs the items in reverse order: " + ((TreeSet<Integer>) integerSortedSet).descendingSet());
		
		final Set<String> stringSortedSet = new TreeSet<String>(Arrays.asList(strings));
		print("\n'stringSortedSet' is a TreeSet<String>; it also sorts the items (alphabetically in this case): " + stringSortedSet);
		print("The TreeSet does not tolerate null values.");
		
		print("\n=========== java.util.Map ===========");	
		final Map integerHashMap = new HashMap<String, Integer>();
		
	}

	private static void print(String toPrint) {
		System.out.println(toPrint);
	}

	private static <T> String printArray(T[] array) {		
		final StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		Arrays.stream(array).map(obj -> obj == null ? "null" : obj).forEach(i -> sb.append(String.format("%s, ", i)));
		sb.replace(sb.length() - 2, sb.length(), "]");
		
		return sb.toString();
	}
}
