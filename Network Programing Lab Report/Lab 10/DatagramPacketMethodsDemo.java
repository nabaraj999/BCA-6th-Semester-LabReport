import java.net.*;

public class DatagramPacketMethodsDemo {
    public static void main(String[] args) {
        try {
            // Step 1: Create data to send
            byte[] sendData = "Hello from UDP DatagramPacket!".getBytes();

            // Step 2: Destination address and port
            InetAddress address = InetAddress.getByName("localhost");
            int port = 9876;

            // Step 3: Create DatagramPacket (for sending)
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);

            System.out.println("---- Initial Packet Information ----");
            printPacketInfo(sendPacket);

            // Step 4: Demonstrate all SET methods
            System.out.println("\n---- Using set methods ----");

            // Change destination address
            InetAddress newAddress = InetAddress.getByName("127.0.0.1");
            sendPacket.setAddress(newAddress);
            System.out.println("setAddress(127.0.0.1)");

            // Change port
            sendPacket.setPort(9999);
            System.out.println("setPort(9999)");

            // Change data
            byte[] newData = "Updated UDP Packet Data".getBytes();
            sendPacket.setData(newData);
            System.out.println("setData(byte[])");

            // Change data length
            sendPacket.setLength(10);
            System.out.println("setLength(10)");

            // Change socket address (address + port together)
            SocketAddress socketAddress = new InetSocketAddress("localhost", 8080);
            sendPacket.setSocketAddress(socketAddress);
            System.out.println("setSocketAddress(localhost:8080)");

            // Step 5: Show all GET methods
            System.out.println("\n---- Using get methods ----");
            printPacketInfo(sendPacket);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to print packet information using get methods
    private static void printPacketInfo(DatagramPacket packet) {
        System.out.println("getAddress(): " + packet.getAddress());
        System.out.println("getPort(): " + packet.getPort());
        System.out.println("getSocketAddress(): " + packet.getSocketAddress());
        System.out.println("getOffset(): " + packet.getOffset());
        System.out.println("getLength(): " + packet.getLength());
        System.out.println("getData(): " + new String(packet.getData()));
    }
}
