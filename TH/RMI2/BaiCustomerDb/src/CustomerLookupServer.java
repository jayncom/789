import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CustomerLookupServer {
    public static void main(String[] args) {
        try {
            CustomerLookupInterface customerLookup = new CustomerLookupImpl();
            Registry registry = LocateRegistry.createRegistry(1088);
            registry.rebind("CustomerLookup", customerLookup);
            System.out.println("Server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
