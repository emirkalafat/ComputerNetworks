package Lab6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Scanner;

public class UDPServer extends Thread {
    DatagramSocket socket;

    HashMap<String, SClient> clientMap;

    public UDPServer(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
        clientMap = new HashMap<>();
    }

    public void Send(String msg) throws IOException {
        for (SClient c : clientMap.values()) {
            DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.length(), c.ip, c.port);
            this.socket.send(packet);
        }
    }

    public void run() {
        while (true) {
            byte buffer[] = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, 1024);
            try {
                this.socket.receive(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            clientMap.put(
                    packet.getAddress() + ":" + packet.getPort(),
                    new SClient(packet.getAddress(), packet.getPort())
            );
            System.out.println(packet.getAddress() + ":" + packet.getPort());
            System.out.println((new String(packet.getData()).trim()));
        }
    }

    public void Listen() throws IOException {
        this.start();
    }

    public static void main(String[] args) {
        try {
            UDPServer udpServer = new UDPServer(5000);
            udpServer.Listen();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Please enter your message:");
                udpServer.Send(scanner.next());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
