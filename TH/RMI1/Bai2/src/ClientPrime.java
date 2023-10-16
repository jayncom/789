import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientPrime {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Nhap so can kiem tra: ");
            long numberToCheck = scanner.nextLong();

            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            PrimeCheckInterface primeCheck = (PrimeCheckInterface) registry.lookup("PrimeCheck");

            boolean isPrime = primeCheck.isPrime(numberToCheck);
            if (isPrime) {
                System.out.println(numberToCheck + " la so nguyen to");
            } else {
                System.out.println(numberToCheck + " khong phai la so nguyen to.");
            }
        } catch (Exception e) {
            System.err.println("Loi khi thuc hien client: " + e.toString());
            e.printStackTrace();
        }
    }
}
