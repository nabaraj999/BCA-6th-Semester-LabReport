import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000; // Make sure your server uses this same port

        try {
            // Connect to server
            Socket socket = new Socket(host, port);
            System.out.println("Connected to server.");

            // Send message to server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("Send me the time");

            // Read response from server
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String time = reader.readLine();
            System.out.println("Server says: " + time);

            // Close everything
            reader.close();
            writer.close();
            socket.close();

        } catch (Exception e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
