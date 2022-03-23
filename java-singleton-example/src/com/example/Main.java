package com.example;

public class Main {
	public static void main (String[] args){
		Singleton singleton1 = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		Singleton singleton3 = Singleton.getInstance();
		
		System.out.println(singleton1.hashCode());
		System.out.println(singleton2.hashCode());
		System.out.println(singleton3.hashCode());
	}
}