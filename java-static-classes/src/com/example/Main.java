package com.example;

public class Main {

	public static void main(String[] args) {
		MyStaticClass staticInstance = new MyStaticClass();
		
		System.out.println("-------------Static Class Test-----------");		
		System.out.println(MyStaticClass.myStaticField);		
		MyStaticClass.myStaticField = "Test";		
		System.out.println(MyStaticClass.myStaticField);
		
		System.out.println("\n-------------Instance Test-----------");		
		System.out.println(staticInstance.myStaticField);		
		staticInstance.myStaticField = "Hello World";		
		System.out.println(staticInstance.myStaticField);
		
		System.out.println("\n-------------Static Class Test (pt.2)-----------");		
		System.out.println(MyStaticClass.myStaticField);
	}

}
