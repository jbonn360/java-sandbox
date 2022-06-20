package com.example;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		String str1 = "cow";
		String str2 = "owc";
		System.out.printf("is %s permutation of %s? %b", str1, str2, isPermutation(str1, str2));

	}

	private static boolean isPermutation(String str1, String str2) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		Character cur = null;

		if (str1.length() != str2.length())
			return false;

		for (int i = 0; i < str1.length(); i++) {
			cur = str1.charAt(i);
			if (map.containsKey(cur))
				map.put(cur, map.get(cur) + 1);
			else
				map.put(cur, 1);
		}

		for (int j = 0; j < str2.length(); j++) {
			cur = str2.charAt(j);
			
			if(!map.containsKey(cur) || map.get(cur) < 1)
				return false;
			else
				map.put(cur, map.get(cur)-1);
		}

		return true;
	}

}
