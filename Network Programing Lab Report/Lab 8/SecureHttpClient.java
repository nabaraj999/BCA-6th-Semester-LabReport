import javax.net.ssl.*;
import java.io.*;
import java.net.*;

public class SecureHttpClient {
    public static void main(String[] args) {
        String host = "example.com";  // You can change this to any HTTPS website
        int port = 443;               // Default HTTPS port

        try {
            // Create SSL socket factory
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            // Create secure socket
            SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
            System.out.println("Connected securely to " + host + " on port " + port);

            // Start SSL handshake (optional, but good practice)
            socket.startHandshake();
            System.out.println("SSL Handshake completed successfully.");

            // Prepare HTTP GET request
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())), true);

            // Simple HTTP request (must end with \r\n\r\n)
            out.println("GET / HTTP/1.1");
            out.println("Host: " + host);
            out.println("Connection: close");
            out.println(); // empty line indicates end of headers
            out.flush();

            // Read and display the response
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            System.out.println("\n----- Secure HTTP Response -----");
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            // Close everything
            in.close();
            out.close();
            socket.close();
            System.out.println("\nConnection closed securely.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
