import java.io.*;
import java.net.*;
import java.util.*;

public class clientUDP {
    public static void main(String[] args) {

        String serverIP = "localhost";
        int port = 1234;

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(serverIP);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhap doan van ban: ");
            String text = scanner.nextLine();

            byte[] sendData = text.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, port);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String receivedText = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Tan suat xuat hien cua tung tu:");
            System.out.println(receivedText);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
