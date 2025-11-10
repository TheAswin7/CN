import java.net.*;

public class DNSServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9876);
        byte[] buffer = new byte[1024];
        System.out.println("DNS Server is running...");

        while (true) {
            // Receive domain name from client
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request);
            String domain = new String(request.getData(), 0, request.getLength());
            System.out.println("Request received for: " + domain);

            // Find IP address
            String ip;
            try {
                InetAddress address = InetAddress.getByName(domain);
                ip = address.getHostAddress();
            } catch (Exception e) {
                ip = "Domain not found";
            }

            // Send IP back to client
            byte[] sendData = ip.getBytes();
            InetAddress clientAddress = request.getAddress();
            int clientPort = request.getPort();
            DatagramPacket reply = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            socket.send(reply);
        }
    }
}
