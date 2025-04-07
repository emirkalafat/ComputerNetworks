/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab4.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author samet
 */
public class SClient extends Thread {
    int id;
    Socket csokcet;
    OutputStream output;
    InputStream input;
    Server server;

    public SClient(Socket acceptedSocket, Server server) throws IOException {
        this.csokcet = acceptedSocket;
        this.server = server;
        this.output = csokcet.getOutputStream();
        this.input = csokcet.getInputStream();
        this.id = server.clientID;
        server.clientID++;
    }

    public void Listen() throws IOException {
        this.start();
    }

    public void SendMsg(byte[] msg) throws IOException {
        this.output.write(msg);
    }

    @Override
    public void run() {

        try {
            while (this.csokcet.isConnected()) {
                int rbyte = this.input.read();
                byte buffer[] = new byte[rbyte];
                this.input.read(buffer);//blocking
                String rmsg = new String(buffer);
                System.out.println(this.csokcet.getInetAddress()
                        + ":" + this.csokcet.getPort()
                        + "->" + rmsg);
                this.SendMsg(" received".getBytes());
            }

        } catch (IOException ex) {
            this.server.clients.remove(this);
        }

    }
}
