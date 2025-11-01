import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        int port = 9876;
        try {
            // Create a DatagramSocket to listen on the given port
            DatagramSocket serverSocket = new DatagramSocket(port);
            System.out.println("UDP Server is running on port " + port + "...");

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            while (true) {
                // Create a packet to receive data from client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                // Extract data from packet
                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Client says: " + clientMessage);

                // Get client info
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Prepare reply message
                String reply = "Hello Client, message received: " + clientMessage;
                sendData = reply.getBytes();

                // Create packet to send back to client
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                System.out.println("Reply sent to client.\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
