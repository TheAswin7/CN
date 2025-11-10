import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        try {
            // Connect to the server
            Socket socket = new Socket("127.0.0.1", 9999);
            System.out.println("Connected to server.");

            // Setup streams
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String msg;
            System.out.println(in.readLine()); // Welcome message

            while (true) {
                System.out.print("You: ");
                msg = userInput.readLine();
                out.println(msg);

                if (msg.equalsIgnoreCase("bye")) break;
                System.out.println("Server: " + in.readLine());
            }

            socket.close();
            System.out.println("Connection closed.");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
