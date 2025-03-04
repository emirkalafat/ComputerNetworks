import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Sunucu başlatıldı. İstemci bekleniyor...");

            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(
                         new InputStreamReader(clientSocket.getInputStream()))) {

                String message = in.readLine();
                System.out.println("İstemci mesajı: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}