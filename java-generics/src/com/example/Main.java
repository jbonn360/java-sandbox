package com.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
	// from https://www.baeldung.com/java-generics
	public static void main(String[] args) {
		SuperClass superClass = new SuperClass();
		SubClass subClass = new SubClass();

		doSomethingMore(subClass);

		List<SuperClass> superList = new ArrayList<SuperClass>();
		List<SubClass> subList = new ArrayList<SubClass>();

		superList.add(new SubClass());

		// this does not work (to prevent possible conflicts that can happen
		// if we add heterogeneous types to the same collection).
		// that's why we need the wildcard (?) generic symbol
		useThisList(subList);
		useThisListThatWorks(subList);
		
		doSomethingLess(subList);
		doSomethingLess(superList);
		
		// this won't work because of the lower bounding effect of the super keyword
		doSomethingLess(new ArrayList<SubSubClass>());
	}

	public static <T> void doSomething(T input) {
		System.out.println(input);
	}

	// this is called an upper bound
	public static <T extends SubInterface> T doSomethingMore(T input) {
		System.out.println(input.sayYourName());

		return input;
	}
	
	//this is called a lower bound
	public static List<? super SubClass> doSomethingLess(List<? super SubClass> list) {
		return list;
	}

	public static <T extends SubClass & SubInterface & AnotherInterface> List<T> doManyThings(List<T> input) {
		return input;
	}

	public static List<? extends SuperClass> nowDoADifferentThing(List<? extends SubClass> input) {
		return input;
	}

	public static void useThisList(List<SuperClass> list) {

	}

	public static void useThisListThatWorks(List<? extends SuperClass> list) {

	}
}
