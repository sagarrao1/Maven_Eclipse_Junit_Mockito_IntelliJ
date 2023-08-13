package com.learining.JunitMockito;

public class StringHelper {
	
	/*
	 * "ABCD" => "BCD" "AABB" => "BB" "BABA" => "BBA"
	 */	
	
	public String replaceAinFirst2charactes(String str) {		
		if (str.length() <= 2) {		
			return str.replaceAll("A", "");			
		}		
		
		String first2chars=str.substring(0,2);
		String remainingStrwithout2chars= str.substring(2);
		
		return first2chars.replaceAll("A", "")+remainingStrwithout2chars;
	}
	
	//compares first and last 2 chars are same or not
	// "AB" => true
	// "ABA" => false
	// "ABAB" => true
	//  "AAA"  => true
	//  "ABC" => false
	
	public boolean areFirstAndLast2CharsSame(String str) {
				
//		System.out.println("str lenth: " +str.length());
		if (str.length() <= 1) {
			return false;
		}
		
		if (str.length() == 2) {
			return true;
		}
		String first2chars= str.substring(0,2);		
		String last2chars= str.substring(str.length()-2);		
//		System.out.println("last2chars : "+last2chars);
		return first2chars.equals(last2chars);
	}
	
}
