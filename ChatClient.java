import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        String hostName = "127.0.0.1"; // change to server IP if on another device
        int portNumber = 2000;

        try {
            // Connect to server
            Socket socket = new Socket(hostName, portNumber);
            System.out.println("Connected to server.");

            // Input and Output streams
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String userInput;

            while (true) {
                // Client sends message first
                System.out.print("Client: ");
                userInput = stdIn.readLine();
                out.println(userInput);

                // Stop if client types END
                if ("END".equalsIgnoreCase(userInput)) {
                    break;
                }

                // Then read server’s reply
                String serverResponse = in.readLine();
                System.out.println("Server: " + serverResponse);
            }

            // Close connections
            in.close();
            out.close();
            socket.close();
            System.out.println("Chat ended. Client closed.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
