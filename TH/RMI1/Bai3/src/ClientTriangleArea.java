import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientTriangleArea {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Nhap canh a: ");
            double sideA = scanner.nextDouble();

            System.out.print("Nhap canh b: ");
            double sideB = scanner.nextDouble();

            System.out.print("Nhap canh c: ");
            double sideC = scanner.nextDouble();

            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            TriangleAreaInterface triangleArea = (TriangleAreaInterface) registry.lookup("TriangleArea");

            double area = triangleArea.calculateArea(sideA, sideB, sideC);
            System.out.println("Dien Tich Tam Giac: " + area);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
