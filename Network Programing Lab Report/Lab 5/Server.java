// Lab 5: WAP to create a simple client and server where the client sends a message to server and server reads that message
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {
    public static void main(String[] args) {
        int port = 5000; // Use a safe custom port

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            // Accept client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            // Read message from client
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String clientMessage = reader.readLine();
            System.out.println("Message from client: " + clientMessage);

            // Send current time as response
            LocalDateTime now = LocalDateTime.now();
            String currentTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("Current Time: " + currentTime);

            System.out.println("Time sent to client successfully.");

            // Close resources
            reader.close();
            writer.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
