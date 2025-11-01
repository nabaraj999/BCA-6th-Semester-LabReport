import java.rmi.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Lookup the remote object from RMI registry
            Hello stub = (Hello) Naming.lookup("rmi://localhost:5000/HelloService");

            // Call the remote method
            String response = stub.sayHello();
            System.out.println("Response from server: " + response);
        } catch (Exception e) {
            System.out.println("Client Exception: " + e);
        }
    }
}
