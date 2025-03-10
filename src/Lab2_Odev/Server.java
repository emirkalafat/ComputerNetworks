package Lab2_Odev;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    ServerSocket serverSocket;
    Socket clientSocket;
    InputStream inputStream;
    OutputStream outputStream;

    public void Connect(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");

            while (!serverSocket.isClosed()) {
                clientSocket = serverSocket.accept();
                System.out.println("Client connected");

                System.out.println("Address" + clientSocket.getInetAddress());
                System.out.println("Port" + clientSocket.getPort());

                try {
                    inputStream = clientSocket.getInputStream();
                    outputStream = clientSocket.getOutputStream();

                    while (clientSocket.isConnected()) {
                        String message = ReciveMessage();

                        String result = CalculateResult(message);

                        String alinanBuyukMesaj = ConvertString(result);

                        SendMessage(alinanBuyukMesaj);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SendMessage(String message) throws IOException {
        byte[] mesaj = message.getBytes();
        outputStream.write(mesaj.length);
        outputStream.write(mesaj);
        outputStream.flush();
    }

    private String ConvertString(String alinanMesaj) {
        return alinanMesaj.toUpperCase();
    }

    public String CalculateResult(String message) {
        int result = 0;
        String[] mesaj = message.split(":");

        System.out.println(Arrays.toString(mesaj));

        switch (mesaj[1]) {
            case "+":
                result = Integer.parseInt(mesaj[0].trim()) + Integer.parseInt(mesaj[2].trim());
                break;
            case "-":
                result = Integer.parseInt(mesaj[0].trim()) - Integer.parseInt(mesaj[2].trim());
                break;
            case "*":
                result = Integer.parseInt(mesaj[0].trim()) * Integer.parseInt(mesaj[2].trim());
                break;
            case "/":
                result = Integer.parseInt(mesaj[0].trim()) / Integer.parseInt(mesaj[2].trim());
                break;
            default:
                break;
        }

        System.out.println(result);
        return String.valueOf(result);
    }

    public String ReciveMessage() throws IOException {
        int mesajUzunblugu = inputStream.read();
        byte[] buffer = new byte[mesajUzunblugu];
        inputStream.read(buffer, 0, mesajUzunblugu);
        String alinanMesaj = new String(buffer);
        return alinanMesaj;
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.Connect(5000);
    }
}
