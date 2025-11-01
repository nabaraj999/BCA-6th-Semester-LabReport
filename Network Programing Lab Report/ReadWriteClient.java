// WAP that shows read and write both in client and server.
import java.io.*;
import java.net.*;

public class ReadWriteClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(host, port)) {
            System.out.println("Connected to server.");

            // Create input and output streams
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Take message from user
            System.out.print("Enter message to send to server: ");
            String message = userInput.readLine();

            // Send to server
            out.println(message);

            // Read reply from server
            String reply = in.readLine();
            System.out.println("Server replied: " + reply);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
