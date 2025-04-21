/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab4.client;

import Lab4.server.MsgType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author samet
 */
public class CClient extends Thread {

    Socket csocket;
    OutputStream output;
    InputStream input;


    public void Connect(String ip, int port) throws IOException {
        this.csocket = new Socket(ip, port);//connection
        this.output = this.csocket.getOutputStream();
        this.input = this.csocket.getInputStream();

    }

    public void SendMsg(String msg) throws IOException {

        this.output.write(msg.getBytes());
    }

    public void Listen() throws IOException {
        this.start();
    }

    public void MsgParser(String msg) throws IOException {
        String []tokens = msg.split("#");

        MsgType Msgtype = MsgType.CLIENTIDS;
        switch (Msgtype){
            case MsgType.CLIENTIDS -> {
            }
        }
    }

    public void run() {

        while (this.csocket.isConnected()) {
            try {
                int rsize = this.input.read();
                byte buffer[] = new byte[rsize];
                this.input.read(buffer);
                String rmsg = new String(buffer);
                //System.out.println(rmsg);
                AppMain.lst_msgs_model.addElement(rmsg);

            } catch (IOException ex) {

            }
        }

    }

}
