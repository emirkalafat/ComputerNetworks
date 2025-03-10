package Lab2_Odev;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    Socket clientSocket;
    InputStream clientIn;
    OutputStream clientOut;

    public void Connect(String host, int port) throws IOException {
        clientSocket = new Socket(host, port);

        clientIn = clientSocket.getInputStream();
        clientOut = clientSocket.getOutputStream();
    }

    public String ReciveMessage() throws IOException {
        int mesajUzunlugu = clientIn.read();
        byte[] buffer = new byte[mesajUzunlugu];

        clientIn.read(buffer, 0, mesajUzunlugu);
        String recivedMessage = new String(buffer);
        return recivedMessage;
    }

    public void SendMessage(String message) throws IOException {
        byte[] mesaj = message.getBytes();
        clientOut.write(mesaj.length); //Önce mesajın uzunluğu
        clientOut.write(mesaj); //Sonra mesajın kendisi
        clientOut.flush();
    }
}
