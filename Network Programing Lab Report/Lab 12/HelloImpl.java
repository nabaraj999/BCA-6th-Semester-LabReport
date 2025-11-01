import java.rmi.*;
import java.rmi.server.*;

public class HelloImpl extends UnicastRemoteObject implements Hello {
    // Constructor must throw RemoteException
    protected HelloImpl() throws RemoteException {
        super();
    }

    // Implement the remote method
    public String sayHello() throws RemoteException {
        return "Hello, this message is from the RMI Server!";
    }

    // Main method to bind the object to RMI registry
    public static void main(String[] args) {
        try {
            // Create an instance of the remote object
            HelloImpl obj = new HelloImpl();

            // Bind the object to the registry with a name
            Naming.rebind("rmi://localhost:5000/HelloService", obj);

            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            System.out.println("Server Exception: " + e);
        }
    }
}
