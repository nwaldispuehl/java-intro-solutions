package solution_06;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import solution_06.acquirer.WebsiteWordListAcquirer;

class WebDataCrawler {

  private static WebsiteWordListAcquirer websiteWordListAcquirer = new WebsiteWordListAcquirer();

  public static void main(String[] args) {

    List<String> cnnWordList = websiteWordListAcquirer.getWordListFrom("http://www.cnn.com/");
    System.out.println("'CNN' words count: " + cnnWordList.size());

    List<String> guardianWordList = websiteWordListAcquirer.getWordListFrom("http://www.theguardian.com/");
    System.out.println("'The Guardian' words count: " + guardianWordList.size());

    List<String> independentWordList = websiteWordListAcquirer.getWordListFrom("http://www.independent.co.uk/");
    System.out.println("'The Independent' words count: " + independentWordList.size());

    // We're preparing a map for our distribution list:
    Map<String, Integer> distributionMap = new HashMap<String, Integer>();

    // Then we're adding the actual words to the map
    for (String word : cnnWordList) {
    	
    	// If there is no entry yet, we initialize one:
    	if (!distributionMap.containsKey(word)) {
    		distributionMap.put(word, 0);
    	}
    	
    	// Then, we get the entry of this word and put it in again, but with its value incremented by 1.
    	distributionMap.put(word, distributionMap.get(word) + 1);
    }
    
    // The same for the other two lists:
    for (String word : guardianWordList) {
    	if (!distributionMap.containsKey(word)) {
    		distributionMap.put(word, 0);
    	}
    	distributionMap.put(word, distributionMap.get(word) + 1);
    }
    
    for (String word : independentWordList) {
    	if (!distributionMap.containsKey(word)) {
    		distributionMap.put(word, 0);
    	}
    	distributionMap.put(word, distributionMap.get(word) + 1);
    }
    
    // This is just to sort the map:
    TreeMap<String, Integer> sortedMap = sortMapByOccurrences(distributionMap);
    System.out.println(sortedMap);
  }

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  private static TreeMap<String, Integer> sortMapByOccurrences(
      Map<String, Integer> map) {
    TreeMap<String, Integer> result = new TreeMap<String, Integer>(
        new ValueComparator(map));
    result.putAll(map);
    return result;
  }

  static class ValueComparator implements Comparator<String> {

    private Map<String, Integer> map;

    public ValueComparator(Map<String, Integer> map) {
      this.map = map;
    }

    @Override
    public int compare(String o1, String o2) {
      Integer v1 = map.get(o1);
      Integer v2 = map.get(o2);

      if (v1 <= v2) {
        return 1;
      } else {
        return -1;
      }
    }

  }

}