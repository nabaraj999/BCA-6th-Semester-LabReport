import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SecureDaytimeUDPServer {
    private static final String SECRET_KEY = "1234567890123456"; // 16-byte AES key

    public static void main(String[] args) {
        int port = 9876;
        try {
            DatagramSocket serverSocket = new DatagramSocket(port);
            System.out.println("Secure UDP Daytime Server running on port " + port + "...");

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            while (true) {
                // Receive request from client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Request received from " + clientAddress + ":" + clientPort);

                // Get current time
                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                String message = "Secure Date & Time: " + time;

                // Encrypt the message
                sendData = encrypt(message, SECRET_KEY);

                // Send encrypted response
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                System.out.println("Encrypted time sent to client.\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // AES encryption method
    private static byte[] encrypt(String data, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data.getBytes());
    }
}
