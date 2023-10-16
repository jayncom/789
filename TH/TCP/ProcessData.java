package TCP;

import java.sql.*;

public class ProcessData {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sv_db";
        String username = "root";
        String password = "123456";
        return DriverManager.getConnection(url, username, password);
    }

    public String queryDatabase(String studentID) {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("SELECT * FROM students WHERE id = ?")) {

            preparedStatement.setString(1, studentID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String studentName = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String gender = resultSet.getString("gender");
                    String address = resultSet.getString("address");
                    return "Student ID: " + studentID + ", Student Name: " + studentName + ", Age: " + age +
                            ", Gender: " + gender + ", Address: " + address;
                } else {
                    return "Student not found";
                }
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database.");
            e.printStackTrace();
            return "Error occurred";
        }
    }
}
