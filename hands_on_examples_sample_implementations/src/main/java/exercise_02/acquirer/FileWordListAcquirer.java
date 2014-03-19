package exercise_02.acquirer;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exercise_02.WordListAcquirer;

public class FileWordListAcquirer implements WordListAcquirer {

  @Override
  public List<String> getWordListFrom(String source) {
    ArrayList<String> result = new ArrayList<String>();
    
    try {
      URL resource = getClass().getClassLoader().getResource(source);
      Scanner scanner = new Scanner(new File(resource.toURI()));
      while (scanner.hasNextLine()){
        String nextLine = scanner.nextLine();
        if (!nextLine.trim().startsWith("#")) {
          result.add(nextLine);
        }
      }
      scanner.close();
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (URISyntaxException e) {
      e.printStackTrace();
    }
    
    return result;
  }

}
