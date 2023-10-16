import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PrimeCheckImpl extends UnicastRemoteObject implements PrimeCheckInterface {
    protected PrimeCheckImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean isPrime(long number) throws RemoteException {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        long maxDivisor = (long) Math.sqrt(number);
        for (long i = 3; i <= maxDivisor; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
