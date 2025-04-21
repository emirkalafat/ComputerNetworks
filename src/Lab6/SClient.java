package Lab6;

import java.net.InetAddress;

public class SClient {
    InetAddress ip;
    int port;

    int id;

    public static int clientID = 0;

    public SClient(InetAddress ip, int port) {
        this.ip = ip;
        this.port = port;
        this.id = SClient.clientID;
        SClient.clientID++;
    }
}
