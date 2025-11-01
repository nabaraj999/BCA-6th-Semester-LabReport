import java.net.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SecureDaytimeUDPClient {
    private static final String SECRET_KEY = "1234567890123456"; // Must match server key

    public static void main(String[] args) {
        String serverHost = "localhost";
        int serverPort = 9876;

        try {
            DatagramSocket clientSocket = new DatagramSocket();

            byte[] sendData = "TIME_REQUEST".getBytes(); // Simple request
            InetAddress serverAddress = InetAddress.getByName(serverHost);

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
            System.out.println("Request sent to server.");

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            byte[] encryptedData = receivePacket.getData();
            String decryptedMessage = decrypt(encryptedData, SECRET_KEY);
            System.out.println("Decrypted response from server:\n" + decryptedMessage);

            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // AES decryption method
    private static String decrypt(byte[] data, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(data)).trim();
    }
}
