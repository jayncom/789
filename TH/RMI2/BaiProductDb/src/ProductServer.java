import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ProductServer {
    public static void main(String[] args) {
        try {
            ProductImpl productImpl = new ProductImpl();

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.createRegistry(1066);
            registry.rebind("ProductLookup", productImpl);

            System.out.println("Product server is ready.");
        } catch (Exception e) {
            System.err.println("ProductServer exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
