// WAP that prints all arbitrary HTTP headers.
import java.net.*;
import java.io.*;
import java.util.*;

public class HttpHeadersDemo {
    public static void main(String[] args) {
        try {
            // You can replace the URL with any website
            URL url = new URL("https://www.google.com");

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Optional: set request method and timeout
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // Connect to the server
            connection.connect();

            // Get all header fields (returns a Map)
            Map<String, List<String>> headers = connection.getHeaderFields();

            // Print all HTTP headers
            System.out.println("=== HTTP Response Headers ===\n");
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String headerKey = entry.getKey();
                List<String> headerValues = entry.getValue();

                // Some headers (like the status line) have a null key
                if (headerKey == null) {
                    System.out.println(headerValues.get(0));
                } else {
                    System.out.println(headerKey + ": " + String.join(", ", headerValues));
                }
            }

            connection.disconnect();

        } catch (MalformedURLException e) {
            System.out.println("Invalid URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
    }
}
