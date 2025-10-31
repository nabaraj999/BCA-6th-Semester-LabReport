//WAP that lists out all the network interfaces and use the method of Network
// Interface Class to print the characteristics of all interfaces. 
import java.net.*;
import java.util.*;

public class NetworkInterfaceDemo {
    public static void main(String[] args) {
        try {
            // Get all network interfaces on the system
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            if (interfaces == null) {
                System.out.println("No network interfaces found.");
                return;
            }

            System.out.println("=== Network Interfaces Information ===\n");

            // Iterate through all interfaces
            while (interfaces.hasMoreElements()) {
                NetworkInterface netIf = interfaces.nextElement();

                System.out.println("Interface Name: " + netIf.getName());
                System.out.println("Display Name  : " + netIf.getDisplayName());
                System.out.println("Up?           : " + netIf.isUp());
                System.out.println("Loopback?     : " + netIf.isLoopback());
                System.out.println("Virtual?      : " + netIf.isVirtual());
                System.out.println("Supports Multicast? : " + netIf.supportsMulticast());
                System.out.println("Point to Point?     : " + netIf.isPointToPoint());
                System.out.println("MTU (Max Transfer Unit): " + netIf.getMTU());

                // Display hardware (MAC) address
                byte[] mac = netIf.getHardwareAddress();
                if (mac != null) {
                    System.out.print("MAC Address   : ");
                    for (int i = 0; i < mac.length; i++) {
                        System.out.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
                    }
                    System.out.println();
                } else {
                    System.out.println("MAC Address   : Not Available");
                }

                // Display associated IP addresses
                Enumeration<InetAddress> inetAddresses = netIf.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress addr = inetAddresses.nextElement();
                    System.out.println("IP Address    : " + addr.getHostAddress());
                }

                System.out.println("--------------------------------------------------\n");
            }

        } catch (SocketException e) {
            System.out.println("Error retrieving network interface information: " + e.getMessage());
        }
    }
}
