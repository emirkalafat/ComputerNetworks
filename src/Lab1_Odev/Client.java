package Lab1_Odev;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    Socket client;

    public void Start(String ip, int port) {
        try {
            client = new Socket(ip, port);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        Client c1 = new Client();
        c1.Start("192.168.2.6", 5000);

        try {
            OutputStream clout = c1.client.getOutputStream();
            byte[] mesaj = "Merhabalar Efenim".getBytes();
            clout.write(mesaj.length); // Önce mesajın uzunluğunu gönder
            clout.write(mesaj); // Sonra mesajı gönder
            clout.flush();
            c1.client.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}