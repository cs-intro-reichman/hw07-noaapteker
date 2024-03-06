
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// Your code goes here
		String newString;
		if (str.length() == 1) {
			return "";
		}
		newString = str.substring(1);
		return  newString;
	}

	public static int levenshtein(String word1, String word2) {
		// Your code goes here
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		int lev;
		int lenWord1 = word1.length();
		int lenWord2 = word2.length();
		if (lenWord2 == 0) {
			return lenWord1;
		}
		if (lenWord1 == 0) {
			return lenWord2;
		}
		if (word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(tail(word1),tail(word2));
		}
		return 1 + Math.min(Math.min(levenshtein(tail(word1),word2),levenshtein(word1,tail(word2))),levenshtein(tail(word1),tail(word2)));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		// Your code here
		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here
		int min = levenshtein(word,dictionary[0]);
		String str = "";
		for (int i = 0; i < dictionary.length; i++) {
			if (levenshtein(word,dictionary[i]) < min) {
				min = levenshtein(word,dictionary[i]);
				str = dictionary[i];
			}
		}
		if (min <= threshold) {
			return str;
		}
		return word;
	}
}
