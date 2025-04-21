package Lab6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
    DatagramSocket socket;

    public UDPClient() throws SocketException {
        this.socket = new DatagramSocket();
    }

    public void Send(String msg) throws IOException {
        DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), InetAddress.getByName("localhost"), 5000);
        this.socket.send(packet);
    }

    public String Receive() throws IOException {
        while (true) {
            byte buffer[] = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, 1024);

            this.socket.receive(packet);

            System.out.println(packet.getAddress() + ":" + packet.getPort());
            System.out.println((new String(packet.getData()).trim()));
        }
    }

    public static void main(String[] args) {
        try {
            UDPClient udpClient = new UDPClient();
            udpClient.Send("Hello World");
            udpClient.Receive();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

