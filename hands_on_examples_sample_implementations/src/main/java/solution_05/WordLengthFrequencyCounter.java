package solution_05;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class WordLengthFrequencyCounter {
	
	private Map<Integer, Integer> frequencyTable = new HashMap<>();
	
	public Map<Integer, Integer> getFrequencyTable() {
		return frequencyTable;
	}

	public void printFrequencyTable() {
		for (Entry<Integer, Integer> entry : new TreeMap<Integer, Integer>(frequencyTable).entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
	
	public void calculateFrequencyTableFrom(List<String> wordList) {
		for (String word : wordList) {
			Integer wordLength = word.length();
			
			if (frequencyTable.containsKey(wordLength)) {
				
				Integer value = frequencyTable.get(wordLength);
				frequencyTable.put(wordLength, value + 1);
			}
			else {
				frequencyTable.put(wordLength, 1);
			}
			
		}
	}

	
}
