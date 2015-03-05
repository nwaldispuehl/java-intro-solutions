package solution_06.acquirer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import solution_06.WordListAcquirer;

public class WebsiteWordListAcquirer implements WordListAcquirer {

  private List<String> commonWordList = new FileWordListAcquirer().getWordListFrom("1000_most_common_words.txt");
  
  private String[] unwantedStringParts = new String[]{"\t", "%", "px", "(", ")", "=", ";", "{", "}", "<", ">", "[", "]", "...", "°", "\"", "'", "+", "*", "_", "#", "no-repeat", "solid", "scroll", "function", "type", "text", "article", "bottom", "link", "video", "click", "grab", "node", "var", "RSS", "Helvetica", "Arial", "Sans", "film", "feed", "dotted", "removal", "responsecount", "search", "background"};
  
  @Override
  public List<String> getWordListFrom(String url) {
    
    // Set to true if you want duplicates being filtered.
    boolean filterDuplicates = false;
    
    String rawWebsiteContent = new URLConnectionReader().getText(url);
    String contentWithRemovedTags = removeTags(rawWebsiteContent);
    String filteredContent = filterUnwantedCharacters(contentWithRemovedTags);
    List<String> wordList = convertToWordList(filteredContent);
    
    if(filterDuplicates) { 
      wordList = removeDuplicates(wordList); 
    }
    
    List<String> filteredWordList = filterUnwantedWords(wordList);
    
    return filteredWordList;
  }

  /**
   * Removes all parts from a text which is enclosed in tag brackets: <...>. 
   * 
   */
  private String removeTags(String input) {
    return input.replaceAll("<(.*?)>", "");
  }
  

  /**
   * Takes out all characters we globally don't need. They are replaced with spaces to prevent accidental word merging.
   */
  private String filterUnwantedCharacters(String content) {
    content = content.replaceAll("«", " ");
    content = content.replaceAll("»", " ");
    content = content.replaceAll("‘", " ");
    content = content.replaceAll("\\?", " ");
    content = content.replaceAll("!", " ");
    content = content.replaceAll(",", " ");
    return content;
  }
  
  /**
   * Converts the provided input to a list of string. Splits the input string along the spaces.
   */
  private List<String> convertToWordList(String input) {
   List<String> result = new ArrayList<String>();
   
    for (String s : input.split(" ")) {
      String trimmed = s.trim();
      
      if (!trimmed.isEmpty()) {
        result.add(trimmed);
      }
    }
    
    return result;
  }

  /**
   * Eliminates duplicate words.
   */
  private List<String> removeDuplicates(List<String> wordList) {
    Set<String> set = new HashSet<String>();
    set.addAll(wordList);
    return new ArrayList<String>(Arrays.asList(set.toArray(new String[0])));
  }

  /**
   * Filters out things we do not want to keep in our wordlist, e.g. HTML-Artefacts etc.
   * 
   * @param wordList
   * @return
   */
  private List<String> filterUnwantedWords(List<String> wordList) {
    List<String> result = new ArrayList<String>();
    for (String word : wordList) {
      if (isAcceptable(word)) {
        result.add(removeRemainingDots(word));
      }
    }
    return result;
  }
  
  /**
   * Returns true if the provided word fits our needs, respectively false if it does not. 
   */
  private boolean isAcceptable(String word) {
     boolean acceptable = true;
     
     if (word.length() <= 2 || 16 < word.length()) {
       return false;
     }
     
     if (word.startsWith(".")) {
       return false;
     }
     
     if (word.contains(":")) {
       return false;
     }
     
     for (String unwantedString : unwantedStringParts) {
       if (word.toLowerCase().contains(unwantedString.toLowerCase())) {
         return false;
       }
     }
     
     for (String commonWord : commonWordList) {
       if (commonWord.equals(word.toLowerCase())) {
         return false;
       }
     }
     
     return acceptable;
  }

  private String removeRemainingDots(String word) {
    return word.replaceAll("^\\.", "").replaceAll("\\.$", "");
  }



  

  
  

  
  /**
   * Taken from here: http://stackoverflow.com/questions/4328711/read-url-to-string-in-few-lines-of-java-code/4328733#4328733
   */
  class URLConnectionReader {
      public String getText(String url)  {
        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            connection.setRequestProperty("User-Agent", "Sample crawler of the Java introduction course example. Sorry for the traffic, my friend! :)");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    
            StringBuilder response = new StringBuilder();
            String inputLine;
    
            while ((inputLine = in.readLine()) != null) 
                response.append(inputLine);
    
            in.close();
    
            return response.toString();
        }
        catch(Exception e) {
          e.printStackTrace();
        }
        return null;
      }

  }
  
}
