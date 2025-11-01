// Lab 5: WAP to create a simple client and server where the client sends a message to server and server reads that message
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            // Create a server socket listening on port 5000
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server is waiting for a client...");

            // Accept client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            // Get input stream from client
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read message from client
            String message = in.readLine();
            System.out.println("Message from client: " + message);

            // Close connections
            in.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
