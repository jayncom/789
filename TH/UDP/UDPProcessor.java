import java.io.*;
import java.net.*;
import java.util.*;

public class UDPProcessor implements Runnable {
    private String receivedData;
    private DatagramSocket socket;
    private InetAddress clientAddress;
    private int clientPort;

    public UDPProcessor(DatagramSocket socket, String receivedData, InetAddress clientAddress, int clientPort) {
        this.socket = socket;
        this.receivedData = receivedData;
        this.clientAddress = clientAddress;
        this.clientPort = clientPort;
    }

    @Override
    public void run() {
        // Handle UDP packet processing here
        // You can use receivedData to access the UDP data

        // Example: Process the received text
        String processedText = processText(receivedData);

        // Prepare a response
        try {
            // Convert the response to bytes and send it to the client
            byte[] sendData = processedText.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            socket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hàm để xử lí đoạn văn bản và trả về kết quả
    private String processText(String text) {
        // Xử lí đoạn văn bản ở đây
        // Ví dụ: Đếm tần suất từ

        String[] words = text.split("\\s+");
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        // Chuẩn bị kết quả
        StringBuilder response = new StringBuilder();
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            response.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        return response.toString();
    }
}
