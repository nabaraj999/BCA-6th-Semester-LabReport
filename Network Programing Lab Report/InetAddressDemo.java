import java.net.*;

public class InetAddressDemo {
    public static void main(String[] args) {
        try {
            // Get the local host address
            InetAddress localHost = InetAddress.getLocalHost();

            // Display details using InetAddress methods
            System.out.println("Host Name: " + localHost.getHostName());
            System.out.println("Canonical Host Name: " + localHost.getCanonicalHostName());
            System.out.println("IP Address: " + localHost.getHostAddress());

        } catch (UnknownHostException e) {
            System.out.println("Error retrieving localhost information: " + e.getMessage());
        }
    }
}
