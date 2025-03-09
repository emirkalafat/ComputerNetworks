package Lab1_Odev;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket server;

    public void Start(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Lab1Odev.Server started");

            while (!server.isClosed()) {
                Socket client = server.accept();

                System.out.println("Address: " + client.getInetAddress());
                System.out.println("Port: " + client.getPort());

                try {
                    InputStream clientIn = client.getInputStream();
                    while (client.isConnected()) {
                        int mesajUzunlugu = clientIn.read();
                        if (mesajUzunlugu == -1)
                            break;

                        byte buffer[] = new byte[mesajUzunlugu];
                        clientIn.read(buffer, 0, mesajUzunlugu);
                        System.out.println("Alınan mesaj: " + new String(buffer));
                    }
                } catch (IOException e) {
                    System.out.println("Lab1Odev.Client bağlantısı kapandı");
                } finally {
                    client.close();
                }
            }
        } catch (IOException e) {
            System.out.println("Lab1Odev.Server hatası: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server s1 = new Server();
        s1.Start(5000);
    }

}