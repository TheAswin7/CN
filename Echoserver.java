import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        try {
            // Create server socket on port 9999
            ServerSocket server = new ServerSocket(9999);
            System.out.println("Server started. Waiting for client...");

            // Wait for a client to connect
            Socket socket = server.accept();
            System.out.println("Client connected.");

            // Setup input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Welcome! Type 'bye' to exit.");

            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("Client: " + msg);
                out.println("Got: " + msg);
                if (msg.equalsIgnoreCase("bye")) break;
            }

            // Close connections
            socket.close();
            server.close();
            System.out.println("Server closed.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
