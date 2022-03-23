package com.example;

public class Main {	
	// When we create a String variable the JVM looks for a pre-existing object with the same value 
	// in a special corner of the memory known as the String Pool. If it finds one with exactly the 
	// same value, it just adds a reference to the location of the pre-existing value in the Pool 
	// instead of allocating (and thus wasting) a new place in memory to store exactly the same value.
	public static void main(String[] args) {
		String test1 = "a";
		String test2 = "a";
		String test3 = new String("a");
		
		System.out.println("\n------------ Hash Codes -------------\n");
		
		System.out.println(test1.hashCode() + " <---- Hash codes are just the hashed value of the string.");
		System.out.println(test2.hashCode() + "       They're not indicators of memory location references.");
		System.out.println(test3.hashCode());
		
		System.out.println("\n--------------------------------\n");
		
		System.out.println(System.identityHashCode(test1) + " <---- System#identityHashCode is good indicator of memory location reference.");
		System.out.println(System.identityHashCode(test2) + " <---- Reference is the same due to same value and thus String Pooling.");
		System.out.println(System.identityHashCode(test3) + " <---- Reference is different. Strings created with 'new' keyword are never pooled.");
		
		System.out.println("\n--------------------------------\n");
		
		if(test1 == test2)			
			System.out.println("test1 is equal to test2");
		else
			System.out.println("test1 is NOT equal to test2");
		
		if(test2 == test3)			
			System.out.println("test2 is equal to test3");
		else
			System.out.println("test2 is NOT equal to test3" + " <---- '==' Operator depends on memory reference being the same.");
		
		if(test2.equals(test3))
			System.out.println("test2's value is equal to test3's value");
		else
			System.out.println("test2's value is NOT equal to test3's value");
		
		System.out.println("\n--------------------------------\n");
		
		test3 = test3.intern();
		System.out.println(System.identityHashCode(test3) + " <---- Reference was changed since the string has been manually interned.");
		
			
	}
}
