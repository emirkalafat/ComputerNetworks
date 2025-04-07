package Lab4.server;


import java.io.IO;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server extends Thread {

    //public class Server implements Runnable {
    int clientID;
    ServerSocket server;
    ArrayList<SClient> clients;

    public Server(int port) throws IOException {
        this.server = new ServerSocket(port);
        this.clients = new ArrayList<>();
        this.clientID = 0;
    }

    public void Start() {

        this.start();
    }

    public void SendClientIDs() throws IOException {
        String msg = " " + MsgType.CLIENTIDS.toString() + "#";
        for (SClient c : clients) {
            msg += c.id + ",";
        }
        this.SentMsgAll(msg.getBytes());
    }

    public void SentMsgAll(byte[] msg) throws IOException {
        for (SClient client : clients) {
            client.SendMsg(msg);
        }
    }


    @Override
    public void run() {
        try {
            while (!this.server.isClosed()) {
                Socket acceptedSoket = this.server.accept();
                SClient nclient = new SClient(acceptedSoket, this);
                this.clients.add(nclient);
                nclient.Listen();

                SendClientIDs();

                //nclient.SendMsg(" welcome".getBytes());


            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
