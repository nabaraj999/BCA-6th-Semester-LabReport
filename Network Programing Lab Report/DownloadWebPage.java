// LAb 4 :WAP that downloads Webpage with URL Connection class
import java.io.*;
import java.net.*;

public class DownloadWebPage {
    public static void main(String[] args) {
        BufferedReader reader = null;

        try {
            // You can change this URL to any website
            URL url = new URL("https://www.google.com");

            // Open a connection to the URL
            URLConnection connection = url.openConnection();

            // Optional: set a user-agent (some servers block requests without it)
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Open a reader to read the input stream (web page content)
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            System.out.println("=== Web Page Content ===\n");

            // Read and print each line of the webpage
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (MalformedURLException e) {
            System.out.println("Invalid URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading from website: " + e.getMessage());
        } finally {
            // Close the reader safely
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Error closing stream: " + e.getMessage());
            }
        }
    }
}
