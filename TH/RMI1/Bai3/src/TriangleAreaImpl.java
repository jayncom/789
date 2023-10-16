import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TriangleAreaImpl extends UnicastRemoteObject implements TriangleAreaInterface {
    protected TriangleAreaImpl() throws RemoteException {
        super();
    }

    @Override
    public double calculateArea(double a, double b, double c) throws RemoteException {
        // Sử dụng công thức Heron để tính diện tích tam giác
        double s = (a + b + c) / 2;
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        return area;
    }
}
