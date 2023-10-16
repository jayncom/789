import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ProductInterface extends Remote {
    // List<Product> findProductByCode(String productCode) throws RemoteException;
    Product findProductByCode(String productCode) throws RemoteException;
}
