import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ComplexOperationImpl extends UnicastRemoteObject implements ComplexOperationInterface {
    protected ComplexOperationImpl() throws RemoteException {
        super();
    }

    @Override
    public Complex add(Complex a, Complex b) throws RemoteException {
        double realSum = a.getReal() + b.getReal();
        double imaginarySum = a.getImaginary() + b.getImaginary();
        return new Complex(realSum, imaginarySum);
    }

    @Override
    public Complex subtract(Complex a, Complex b) throws RemoteException {
        double realDiff = a.getReal() - b.getReal();
        double imaginaryDiff = a.getImaginary() - b.getImaginary();
        return new Complex(realDiff, imaginaryDiff);
    }

    @Override
    public Complex multiply(Complex a, Complex b) throws RemoteException {
        double realProduct = a.getReal() * b.getReal() - a.getImaginary() * b.getImaginary();
        double imaginaryProduct = a.getReal() * b.getImaginary() + a.getImaginary() * b.getReal();
        return new Complex(realProduct, imaginaryProduct);
    }

    @Override
    public Complex divide(Complex a, Complex b) throws RemoteException {
        double denominator = b.getReal() * b.getReal() + b.getImaginary() * b.getImaginary();
        double realQuotient = (a.getReal() * b.getReal() + a.getImaginary() * b.getImaginary()) / denominator;
        double imaginaryQuotient = (a.getImaginary() * b.getReal() - a.getReal() * b.getImaginary()) / denominator;
        return new Complex(realQuotient, imaginaryQuotient);
    }
}
