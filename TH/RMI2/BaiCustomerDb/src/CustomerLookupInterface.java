import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CustomerLookupInterface extends Remote {
    List<Customer> findCustomerByCode(String customerCode) throws RemoteException;
}
