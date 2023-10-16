import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class ProductClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1066);
            ProductInterface stub = (ProductInterface) registry.lookup("ProductLookup");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhap ma code de tim san pham: ");
            String productCode = scanner.nextLine();

            Product products = stub.findProductByCode(productCode);

            if (products.isEmpty()) {
                System.out.println("Khong tim thay san pham nao có ma: " + productCode);
            } else {
                System.out.println("Thong tin san pham co ma " + productCode + ":");
                for (Product product : products) {
                    System.out.println(product.toString());
                }
            }
        } catch (Exception e) {
            System.err.println("ProductClient exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
