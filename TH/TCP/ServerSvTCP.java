package TCP;

import java.io.*;
import java.net.*;

public class ServerSvTCP {
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(clientSocket.getOutputStream()))) {

                    String studentID = reader.readLine();

                    // Process data using ProcessData class
                    ProcessData processData = new ProcessData();
                    String studentInfo = processData.queryDatabase(studentID);

                    writer.write(studentInfo);
                    writer.newLine();
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    clientSocket.close();
                    serverSocket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
