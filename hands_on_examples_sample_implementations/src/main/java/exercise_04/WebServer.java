package exercise_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer implements Runnable {

  InetSocketAddress localAddress;
  private RequestHandler requestHandler;

  public WebServer(String address, int port) {
    localAddress = new InetSocketAddress(address, port);
  }

  public void registerHandler(RequestHandler requestHandler) {
    this.requestHandler = requestHandler;
  }

  public void startServer() {
    new Thread(this).start();
  }

  @Override
  public void run() {
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket();
      serverSocket.bind(localAddress);

      System.out.println("Webserver started on http://" + localAddress.getHostString() + ":" + localAddress.getPort());

      while (true) {
        Socket socket = serverSocket.accept();
        handleRequest(socket);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (serverSocket != null) {
        try {
          serverSocket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private void handleRequest(final Socket socket) {
    Thread thread = new Thread() {
      @Override
      public void run() {
        try {
          String queryString = getQueryString(socket);
          
          System.out.println("Handling request from " + getRemoteAddress(socket) + " for " + queryString);
          
          String result = requestHandler.handle(queryString.substring(1));
          
          writeResultToOutputStream(socket.getOutputStream(), result);
          
          socket.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

    };
    thread.start();
  }
  
  private String getQueryString(Socket socket) throws IOException {
    String request = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
    String[] requestParts = request.split(" ");
    return requestParts[1];
  }
  
  private String getRemoteAddress(Socket socket) {
    return ((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress().getHostAddress();
  }

  private void writeResultToOutputStream(final OutputStream outputStream,
      String result) throws IOException {
    PrintWriter writer = new PrintWriter(outputStream, true);
    writer.println("HTTP/1.0 200");
    writer.println("Content-type: text/html");
    writer.println("Content-length: " + result.length());
    writer.println("");
    writer.println(result);
    writer.close();
  }

  /**
   * Interface for a request handler which handles a request to the server.
   */
  public interface RequestHandler {
    String handle(String query);
  }

}
