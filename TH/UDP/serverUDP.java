import java.io.*;
import java.net.*;
import java.util.*;

public class serverUDP {

    public static void main(String[] args) throws IOException {
        int udpPort = 1234;
        DatagramSocket serverSocket = new DatagramSocket(udpPort);
        byte[] receiveData = new byte[1024];
        System.out.println("UDP Server is running on port " + udpPort);

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket); // Nhận gói tin UDP
        
            // Xử lý gói tin UDP trong một luồng riêng
            String receivedText = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received UDP packet: " + receivedText);
        
            // Khởi tạo một luồng mới để xử lý gói tin sử dụng UDPProcessor
            Thread processThread = new Thread(new UDPProcessor(serverSocket, receivedText, receivePacket.getAddress(), receivePacket.getPort()));
            processThread.start();
        }
    }
}
