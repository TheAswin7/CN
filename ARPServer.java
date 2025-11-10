import java.net.*;
import java.util.*;

public class ARPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket(9999);
        Map<String,String> table = new HashMap<>();
        table.put("192.168.1.1","AA:BB:CC:DD:EE:01");
        table.put("192.168.1.2","AA:BB:CC:DD:EE:02");
        System.out.println("ARP Server started...");
        byte[] buf = new byte[512];
        while (true) {
            DatagramPacket p = new DatagramPacket(buf, buf.length);
            s.receive(p);
            String ip = new String(p.getData(),0,p.getLength());
            String mac = table.computeIfAbsent(ip, k -> random());
            s.send(new DatagramPacket(mac.getBytes(), mac.length(), p.getAddress(), p.getPort()));
            System.out.println(ip + " -> " + mac);
        }
    }
    static String random() {
        Random r = new Random();
        return String.format("%02X:%02X:%02X:%02X:%02X:%02X",
            r.nextInt(256),r.nextInt(256),r.nextInt(256),
            r.nextInt(256),r.nextInt(256),r.nextInt(256));
    }
}
