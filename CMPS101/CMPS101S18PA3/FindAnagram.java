
/* ******************************************************/
// CREATED: Sinclair Liang on May 9th, 2018;
// CHANGED: Sinclair Liang on May 9th, 2018: Sorting by code;
// CHANGED: Sinclair Liang on May 10th, 2018: Finished I/O part;
// CHANGED: Sinclair Liang on May 12th, 2018: Adding Comments;
/* ******************************************************/

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;

public class FindAnagram extends anagram {
	public static void main(String[] args) throws IOException {
		int lineNumber = 0;
		Scanner wordlist = new Scanner(new File(args[0]));

		while (wordlist.hasNextLine()) {
			// processing wordlist dictionary file;
			lineNumber++;
			// calculating how many lines it has;
			wordlist.next();
		}

		wordlist.close();
		wordlist = new Scanner(new File(args[0]));
		String[] pre_gram = new String[lineNumber];
		// creat a giant array with the number of lines of slots;

		for (int i = 0; i < lineNumber; i++) {
			// store each line/word into the giant array;
			pre_gram[i] = wordlist.next();
			;
		}

		anagram[] grams = new anagram[lineNumber];
		// creat another array to store anagram type;

		for (int i = 0; i < lineNumber; i++) {
			// store each anagram of those words into the array;
			grams[i] = new anagram(pre_gram[i]);
		}

		Arrays.sort(grams);
		// sort the giant array of anagrams;
		ArrayList<anagram> arrli = new ArrayList<anagram>(lineNumber);
		// create an arraylist;

		for (int i = 0; i < lineNumber; i++) {
			// covert the giant array of anagrams to an arraylist;
			arrli.add(new anagram(pre_gram[i]));
		}

		Collections.sort(arrli);
		// sort the arraylist;

		while (true) {
			int anagram_found = 0;
			Scanner reader = new Scanner(System.in);
			System.out.println("type a string of letters");
			String s_test = reader.next();
			// read in user input;
			anagram test = new anagram(s_test);

			int index = Collections.binarySearch(arrli, test);
			// using Arraylist search to find rhe matching anagram;
			if (index > 0) {
				// if found;
				index = index - 10;
				// Assuming there is no more than 10 anagrams for any given word;
				for (int i = 0; i < 20; i++) {
					if (test.code == arrli.get(index + i).code) {
						if (!test.field_string.equals(arrli.get(index + i).field_string)) {
							System.out.println(arrli.get(index + i).field_string);
							anagram_found++;
						}
					}
				}
				if (anagram_found < 1) {
					// when no anagram is found;
					System.out.println("No other Found.");
				}
			} else {
				// when no anagram is found;
				System.out.println("Not Found.");
			}

			Scanner user_choice = new Scanner(System.in);
			System.out.println("Do another (y/n)?");
			String user_answer = user_choice.next();
			if (user_answer.equals("n")) {
				break;
			}
		}
	}
}