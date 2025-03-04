import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println("Merhaba Sunucu!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}