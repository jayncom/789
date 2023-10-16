import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductImpl extends UnicastRemoteObject implements ProductInterface {
    private Connection connection;

    public ProductImpl() throws RemoteException {
        super();
        // Khởi tạo kết nối đến MySQL database
        initializeDatabaseConnection();
    }

    private void initializeDatabaseConnection() {
        String url = "jdbc:mysql://localhost:3306/product_db";
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
    public Product findProductByCode(String productCode) throws RemoteException {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM Product_Data WHERE productCode = ?");
            preparedStatement.setString(1, productCode);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("productId"));
                product.setProductCode(resultSet.getString("productCode"));
                product.setProductName(resultSet.getString("productName"));
                product.setUnit(resultSet.getString("unit"));
                product.setPrice(resultSet.getDouble("price"));
                products.add(product);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}
