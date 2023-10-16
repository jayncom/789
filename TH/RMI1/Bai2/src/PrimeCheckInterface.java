import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrimeCheckInterface extends Remote {
    boolean isPrime(long number) throws RemoteException;
}
