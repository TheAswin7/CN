import java.net.*;
import java.util.*;

public class ARPClient {
    public static void main(String[] a) throws Exception {
        DatagramSocket s = new DatagramSocket();
        InetAddress host = InetAddress.getByName("localhost");
        Scanner sc = new Scanner(System.in); 
        while (true) {
            System.out.print("Enter IP (or exit): ");
            String ip = sc.nextLine();
            if (ip.equalsIgnoreCase("exit")) break;
            s.send(new DatagramPacket(ip.getBytes(), ip.length(), host, 9999));
            byte[] buf = new byte[512];
            DatagramPacket p = new DatagramPacket(buf, buf.length);
            s.receive(p);
            System.out.println("MAC for " + ip + " : " + new String(p.getData(),0,p.getLength()) + "\n");
        }
        s.close(); sc.close();
    }
}
