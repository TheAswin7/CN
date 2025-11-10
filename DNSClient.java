import java.net.*;
import java.util.Scanner;

public class DNSClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress server = InetAddress.getByName("localhost");
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter domain name: ");
        String domain = sc.nextLine();

        // Send domain to server
        byte[] sendData = domain.getBytes();
        DatagramPacket request = new DatagramPacket(sendData, sendData.length, server, 9876);
        socket.send(request);

        // Receive IP from server
        byte[] buffer = new byte[1024];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        socket.receive(reply);

        String response = new String(reply.getData(), 0, reply.getLength());
        System.out.println("IP Address: " + response);

        socket.close();
        sc.close();
    }
}
