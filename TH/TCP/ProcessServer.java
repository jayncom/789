package TCP;

import java.sql.*;

public class ProcessServer {
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
                    return "Thông tin sinh viên - Mã sinh viên: " + studentID + ", Tên: " + studentName + ", Tuổi: "
                            + age +
                            ", Giới tính: " + gender + ", Địa chỉ: " + address;
                } else {
                    return "Không tìm thấy thông tin sinh viên";
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối đến cơ sở dữ liệu.");
            e.printStackTrace();
            return "Đã xảy ra lỗi";
        }
    }

    public void addStudent(String studentID, String studentName, int age, String gender, String address) {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO students (id, name, age, gender, address) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, studentID);
            preparedStatement.setString(2, studentName);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, address);

            preparedStatement.executeUpdate();
            System.out.println("Đã thêm sinh viên vào cơ sở dữ liệu.");
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm sinh viên vào cơ sở dữ liệu.");
            e.printStackTrace();
        }
    }

    public void deleteStudent(String studentID) {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("DELETE FROM students WHERE id = ?")) {

            preparedStatement.setString(1, studentID);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Sinh viên đã được xóa khỏi cơ sở dữ liệu.");
            } else {
                System.out.println("Không tìm thấy sinh viên để xóa.");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa sinh viên từ cơ sở dữ liệu.");
            e.printStackTrace();
        }
    }

    public void updateStudent(String studentID, String newName, int newAge, String newGender, String newAddress) {
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE students SET name = ?, age = ?, gender = ?, address = ? WHERE id = ?")) {

            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, newAge);
            preparedStatement.setString(3, newGender);
            preparedStatement.setString(4, newAddress);
            preparedStatement.setString(5, studentID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Thông tin sinh viên đã được cập nhật.");
            } else {
                System.out.println("Không tìm thấy sinh viên để cập nhật.");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật thông tin sinh viên.");
            e.printStackTrace();
        }
    }
}
