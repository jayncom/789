package TCP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ClientSvFormTCP {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);
        frame.setLayout(new FlowLayout());

        JLabel idLabel = new JLabel("Enter student ID:");
        JTextField idField = new JTextField(10);
        JButton submitButton = new JButton("Submit");
        JTextArea resultTextArea = new JTextArea(5, 20);

        frame.add(idLabel);
        frame.add(idField);
        frame.add(submitButton);
        frame.add(resultTextArea);

        frame.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentID = idField.getText();
                if (!studentID.isEmpty()) {
                    try {
                        Socket socket = new Socket("localhost", 1234);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                        // Gửi mã số sinh viên đến máy chủ
                        writer.write(studentID);
                        writer.newLine();
                        writer.flush();

                        // Nhận và hiển thị tên sinh viên từ máy chủ
                        String studentInfo = reader.readLine();
                        resultTextArea.setText("Student Name: " + studentInfo);

                        socket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
