package TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ProcessCLient {
    public static void handleStudentInfo(String studentID) {
        try {
            Socket socket = new Socket("localhost", 8888);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Gửi mã số sinh viên đến máy chủ
            writer.write(studentID);
            writer.newLine();
            writer.flush();

            // Nhận và in tên sinh viên từ máy chủ
            String studentInfo = reader.readLine();
            System.out.println("Student Name: " + studentInfo);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "ProcessClient []";
    }
}
