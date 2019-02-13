
/* ******************************************************/
// CREATED: Sinclair Liang on May 7th, 2018;
// CHANGED: Sinclair Liang on May 12th, 2018: Adding Comments;
/* ******************************************************/

import java.lang.*;
import java.util.Arrays;
import java.lang.Math;

class anagram implements Comparable<anagram> {
	public String field_string;
	public int code;

	// --------------------------------------------------------
	// Constructors
	// --------------------------------------------------------

	public anagram() {
		// default constructor;
		field_string = "";
		code = 0;
	}

	public anagram(String s) {
		// constructor for string type;
		String s1 = s.toLowerCase();
		// convert all letters to lowercased;
		char tempArray[] = s1.toCharArray();
		// initiate an empty char array for sorting later;
		Arrays.sort(tempArray);
		// sort
		String s2 = new String(tempArray);
		field_string = s1;
		code = s2.hashCode();
		// take the hashcode for that sorted lowercased string to be the code;
	}

	public anagram(char[] array) {
		// parameter: a char array;
		// returns: the anagram type of it;
		field_string = String.valueOf(array);
		String s1 = field_string.toLowerCase();
		char tempArray[] = s1.toCharArray();
		Arrays.sort(tempArray);
		String s2 = new String(tempArray);
		code = s2.hashCode();
		// code = an.code;
	}

	// --------------------------------------------------------
	// methods
	// --------------------------------------------------------
	public static void print(anagram z) {
		// parameter: anagram type;
		// returns: null;
		// what it does: print the string field of that anagram;
		System.out.print(z.field_string);
	}

	boolean compare(anagram B) {
		// parameter: anagram type;
		// returns: a boolean value to indicate wether it is the anagram with that word;
		if (this.code == B.code) {
			// comparing by the codes;
			return true;
		}
		return false;
	}

	String word_part(anagram A) {
		// parameter: anagram type;
		// returns: the string field of that anagram;
		return A.field_string;
	}

	public static int hashing(String s) {
		// another method for generating codes I thought of;
		String s1 = s.toLowerCase();
		int code = 0;
		for (int i = 0; i < s1.length(); i++) {
			char c = s1.charAt(i);
			code += (int) c;
		}
		return code;
	}

	@Override
	public int compareTo(anagram comparesto) {
		// overwting the compareTo method for comparsion;
		// comparing by the codes;
		if (this.code < comparesto.code) {
			return -1;
		} else if (this.code > comparesto.code) {
			return 1;
		} else {
			return 0;
		}
	}

}
