package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientSvTCP {
    public static void main(String[] args) {
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter student ID: ");
            String studentID = userInput.readLine();

            // Xử lý thông tin sinh viên
            ProcessCLient.handleStudentInfo(studentID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
