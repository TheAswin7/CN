import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try {
            // Create a server socket at port 2000
            ServerSocket serverSocket = new ServerSocket(2000);
            System.out.println("Server started. Waiting for client...");

            // Accept a client connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            // Input and Output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String inputLine;

            while (true) {
                // Server sends a message first
                System.out.print("Server: ");
                inputLine = stdIn.readLine();
                out.println(inputLine);

                // Stop if server types END
                if ("END".equalsIgnoreCase(inputLine)) {
                    break;
                }

                // Then read client's reply
                String clientMsg = in.readLine();
                System.out.println("Client: " + clientMsg);
            }

            // Close all connections
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
            System.out.println("Chat ended. Server closed.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
