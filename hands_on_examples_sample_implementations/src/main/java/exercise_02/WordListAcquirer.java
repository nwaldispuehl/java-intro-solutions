package exercise_02;

import java.util.List;

/**
 * Interface for a class which acquires a word list from some source.
 *
 */
public interface WordListAcquirer {

  /**
   * Acquires a word list from a source.
   * 
   * @param source the source where the word list is to be found; e.g. a filename.
   * @return a list of words found at this source. If nothing has been found, an empty list is returned.
   */
  List<String> getWordListFrom(String source);
  
}
