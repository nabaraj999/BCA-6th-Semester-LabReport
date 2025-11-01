import java.io.*;
import java.net.*;

public class SocketInfoClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5000;

        try (Socket sock = new Socket(host, port)) {
            System.out.println("Connected to server " + host + ":" + port);

            // Print client's local socket info (client-side view)
            System.out.println("--- Client-side socket info ---");
            System.out.println("Local address: " + sock.getLocalAddress().getHostAddress());
            System.out.println("Local port: " + sock.getLocalPort());
            System.out.println("Remote address: " + sock.getInetAddress().getHostAddress());
            System.out.println("Remote port: " + sock.getPort());
            System.out.println("-------------------------------");

            // send a line to server
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            out.println("Hello from client!");
            // keep open a little (optional), then close by try-with-resources
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
