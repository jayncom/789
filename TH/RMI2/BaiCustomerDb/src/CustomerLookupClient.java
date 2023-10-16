import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class CustomerLookupClient {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Nhap Ma Khach Hang: ");
            String customerCode = scanner.nextLine();

            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1088);
            CustomerLookupInterface customerLookup = (CustomerLookupInterface) registry.lookup("CustomerLookup");

            List<Customer> customers = customerLookup.findCustomerByCode(customerCode);

            if (customers.isEmpty()) {
                System.out.println("Không tim thay khách hang voi Ma nay " + customerCode);
            } else {
                System.out.println("Thong tin khach hang:");
                for (Customer customer : customers) {
                    System.out.println(customer);
                }
            }
        } catch (Exception e) {
            System.err.println("Loi khi thuc hien client: " + e.toString());
            e.printStackTrace();
        }
    }
}
