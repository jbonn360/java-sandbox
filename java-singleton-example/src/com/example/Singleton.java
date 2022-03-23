package com.example;

public class Singleton{
	public static Singleton instance;
	
	private void Singleton(){
	
	}
	
	public static Singleton getInstance(){
		if(instance == null)
			instance = new Singleton();
		
		return instance;	
	}
}