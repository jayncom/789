import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClientApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("UDP Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea inputTextArea = new JTextArea(5, 20);
        JTextArea outputTextArea = new JTextArea(5, 20);
        outputTextArea.setEditable(false);

        JButton sendButton = new JButton("Send to Server");

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputText = inputTextArea.getText();
                sendToServer(inputText, outputTextArea);
            }
        });

        panel.add(new JScrollPane(inputTextArea), BorderLayout.NORTH);
        panel.add(sendButton, BorderLayout.CENTER);
        panel.add(new JScrollPane(outputTextArea), BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private static void sendToServer(String inputText, JTextArea outputTextArea) {
        try {
            InetAddress serverAddress = InetAddress.getByName("localhost"); // Đặt địa chỉ IP của máy chủ ở đây
            int serverPort = 1234; // Đặt cổng máy chủ ở đây
    
            DatagramSocket socket = new DatagramSocket();
            byte[] sendData = inputText.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            socket.send(sendPacket);
    
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
    
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
    
            // Xóa dữ liệu cũ trước khi đặt dữ liệu mới
            outputTextArea.setText("");
            
            outputTextArea.setText(response);
    
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
