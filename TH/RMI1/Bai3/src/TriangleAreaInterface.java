import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TriangleAreaInterface extends Remote {
    double calculateArea(double a, double b, double c) throws RemoteException;
}
