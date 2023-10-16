import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerTriangleArea {
    public static void main(String[] args) {
        try {
            TriangleAreaInterface triangleArea = new TriangleAreaImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("TriangleArea", triangleArea);
            System.out.println("Server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
