// lab 6: WAP that show all the info of client socket using method.
import java.io.*;
import java.net.*;

public class SocketInfoServer {

    public static void main(String[] args) {
        int port = 5000;
        System.out.println("Starting server on port " + port + " ...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server ready. Waiting for a client...");

            // Accept one client (blocking)
            try (Socket client = serverSocket.accept()) {
                System.out.println("\nClient connected!");
                printSocketInfo(client);

                // optional: read a line from client
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String line = in.readLine();
                System.out.println("Message from client: " + line);
            }

            System.out.println("Connection handled. Server exiting.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printSocketInfo(Socket s) {
        System.out.println("===== Socket Info =====");
        try {
            InetAddress remoteAddr = s.getInetAddress();
            InetAddress localAddr  = s.getLocalAddress();

            System.out.println("Remote (client) address: " + (remoteAddr != null ? remoteAddr.getHostAddress() : "null"));
            System.out.println("Remote (client) host name: " + (remoteAddr != null ? remoteAddr.getHostName() : "null"));
            System.out.println("Remote (client) port: " + s.getPort());

            System.out.println("Local address (server side): " + (localAddr != null ? localAddr.getHostAddress() : "null"));
            System.out.println("Local host name: " + (localAddr != null ? localAddr.getHostName() : "null"));
            System.out.println("Local port (server side): " + s.getLocalPort());

            System.out.println("Remote socket address (SocketAddress): " + s.getRemoteSocketAddress());
            System.out.println("Local socket address (SocketAddress): " + s.getLocalSocketAddress());

            System.out.println("isBound(): " + s.isBound());
            System.out.println("isClosed(): " + s.isClosed());
            System.out.println("isConnected(): " + s.isConnected());
            System.out.println("isInputShutdown(): " + s.isInputShutdown());
            System.out.println("isOutputShutdown(): " + s.isOutputShutdown());

            // socket options (may throw SocketException)
            System.out.println("getSoTimeout(): " + s.getSoTimeout());
            System.out.println("getReceiveBufferSize(): " + s.getReceiveBufferSize());
            System.out.println("getSendBufferSize(): " + s.getSendBufferSize());
            System.out.println("getTrafficClass(): " + s.getTrafficClass());
            System.out.println("getTcpNoDelay(): " + s.getTcpNoDelay());
            System.out.println("getKeepAlive(): " + s.getKeepAlive());
            System.out.println("getSoLinger(): " + s.getSoLinger());
        } catch (SocketException se) {
            System.out.println("SocketException while reading options: " + se.getMessage());
        }
        System.out.println("========================\n");
    }
}
