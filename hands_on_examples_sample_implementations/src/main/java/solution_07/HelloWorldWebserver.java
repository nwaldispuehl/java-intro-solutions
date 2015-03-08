package solution_07;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import solution_07.WebServer.RequestHandler;

public class HelloWorldWebserver {

  public static void main(String[] args) throws IOException {
    WebServer webServer = new WebServer("localhost", 8080);
    webServer.registerHandler(new RequestHandler() {

      @Override
      public String handle(String query) {
        
        // We get the current date by creating a date object without any arguments whatsoever.
        Date currentDate = new Date();

        // We wrap the string to integer conversion into a so called exception block.
        // If something goes wrong a default value is set.
        int upperRandomLimit;
        try {
          upperRandomLimit = Integer.valueOf(query);
        }
        catch (NumberFormatException e) {
          upperRandomLimit = 10;
        }
        int randomNumber = new Random().nextInt(upperRandomLimit);
        
        return "Current date: " + currentDate + ". Random number in [0, " + upperRandomLimit + "): " + randomNumber;
      }

    });

    webServer.startServer();
  }

}
