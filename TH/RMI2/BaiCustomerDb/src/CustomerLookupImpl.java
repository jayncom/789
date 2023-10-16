import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerLookupImpl extends UnicastRemoteObject implements CustomerLookupInterface {
    private Connection connection;

    public CustomerLookupImpl() throws RemoteException {
        super();
        // Khởi tạo kết nối đến MySQL database
        initializeDatabaseConnection();
    }

    private void initializeDatabaseConnection() {
        String url = "jdbc:mysql://localhost:3306/customer_db";
        String username = "root";
        String password = "123456";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> findCustomerByCode(String customerCode) throws RemoteException {
        List<Customer> customers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE code = ?");
            preparedStatement.setString(1, customerCode);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setCode(resultSet.getString("code"));
                customer.setName(resultSet.getString("name"));
                customer.setAddress(resultSet.getString("address"));
                customers.add(customer);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}
