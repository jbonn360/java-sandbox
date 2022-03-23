package com.baeldung.javac;

import com.baeldung.javac.Data;
import java.util.Scanner;

//Compile both classes with: (~/javac-example:) javac -d javac-target com/baeldung/javac/Data.java com/baeldung/javac/Main.java javac -d
//Run classes with: (~/javac-example/java-target:) java [-cp {path to java-target}] com.baeldung.javac.Main

public class Main{
        public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Data data = new Data();

        System.out.println("Please enter the first value");
        data.addText(scanner.next());

        System.out.println("Please enter the second value");
        data.addText(scanner.next());
        
        System.out.printf("First value is '%s'\n", data.getTextList().get(0)); 
        System.out.printf("Second value is '%s'\n", data.getTextList().get(1));   
    }
}

