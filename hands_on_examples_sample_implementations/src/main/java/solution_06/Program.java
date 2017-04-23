package solution_06;

import java.util.List;

import tools.FileWordListAcquirer;

public class Program {

	private static List<String> commonWordList = new FileWordListAcquirer().getWordListFrom("1000_most_common_words.txt");
	
	public static void main(String[] args) {
		
		// This prints every word of the wordlist
		System.out.println("Word list:");
		for (String word : commonWordList) {
			System.out.println(word + " (Length: " + word.length() + ")");
		} 
		
		// Now we want to know the word length frequency.
		// Implement the method calculateFrequencyTableFrom so that for every word length the number of words of this length is stored, e.g.:
		// 1 letter: 2 words
		// 2 letters: 12 words
		// 3 letters: 140 words
		// etc.
		System.out.println("---");
		System.out.println("Frequency table:");
		WordLengthFrequencyCounter frequencyCounter = new WordLengthFrequencyCounter();
		frequencyCounter.calculateFrequencyTableFrom(commonWordList);
		frequencyCounter.printFrequencyTable();
	
		
	}
}
