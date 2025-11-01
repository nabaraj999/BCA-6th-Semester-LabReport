import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String serverHost = "localhost";
        int serverPort = 9876;

        try {
            // Create a DatagramSocket for the client
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(serverHost);

            System.out.print("Enter message to send to server: ");
            String message = sc.nextLine();
            byte[] sendData = message.getBytes();

            // Create packet to send
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
            System.out.println("Message sent to server.");

            // Prepare to receive server reply
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            // Extract server reply
            String reply = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Server replied: " + reply);

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
