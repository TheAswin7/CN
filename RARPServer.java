import java.net.*;
import java.util.*;

public class RARPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9999); // Server port
        Map<String, String> table = new HashMap<>();

        // Predefined MAC → IP table
        table.put("AA:BB:CC:DD:EE:01", "192.168.1.1");
        table.put("AA:BB:CC:DD:EE:02", "192.168.1.2");
        table.put("AA:BB:CC:DD:EE:03", "192.168.1.3");

        System.out.println("RARP Server started on port 9999...");
        byte[] buf = new byte[512];

        while (true) {
            // Receive MAC address from client
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String mac = new String(packet.getData(), 0, packet.getLength()).trim().toUpperCase();

            // Find IP for this MAC (or generate a fake one if not found)
            String ip = table.computeIfAbsent(mac, k -> randomIP());

            // Send the IP back to the client
            DatagramPacket reply = new DatagramPacket(ip.getBytes(), ip.length(),
                    packet.getAddress(), packet.getPort());
            socket.send(reply);

            // Log the mapping
            System.out.println("Request: " + mac + " -> " + ip);
        }
    }

    // Generate a random IP address (just for demo)
    static String randomIP() {
        Random r = new Random();
        return String.format("192.168.%d.%d", r.nextInt(256), r.nextInt(256));
    }
}
