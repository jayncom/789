import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerPrime {
    public static void main(String[] args) {
        try {
            PrimeCheckInterface primeCheck = new PrimeCheckImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("PrimeCheck", primeCheck);
            System.out.println("Server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
