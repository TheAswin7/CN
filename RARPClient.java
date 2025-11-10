import java.net.*;
import java.util.*;

public class RARPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress host = InetAddress.getByName("localhost");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter MAC address (or exit): ");
            String mac = sc.nextLine().trim();
            if (mac.equalsIgnoreCase("exit")) break;


            DatagramPacket packet = new DatagramPacket(mac.getBytes(), mac.length(), host, 9999);
            socket.send(packet);

 
            byte[] buf = new byte[512];
            DatagramPacket reply = new DatagramPacket(buf, buf.length);
            socket.receive(reply);

       
            String ip = new String(reply.getData(), 0, reply.getLength());
            System.out.println("IP for " + mac + " : " + ip + "\n");
        }

        socket.close();
        sc.close();
    }
}
