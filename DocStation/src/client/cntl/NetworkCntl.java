/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.cntl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import client.model.Connection;
import client.model.Data;
import client.model.Document;
import client.view.EditorUI;

/**
 *
 * @author Eric
 */
public class NetworkCntl{

    public static final String SERVER_NAME = "127.0.0.1";
    public static final int PORT = 5025;

    private LoginCntl loginCntl;
    
    private Socket socket;

    public NetworkCntl(LoginCntl parentCntl) {
        this.loginCntl = parentCntl;
        connectToServer();
        readData();
    }

    private void connectToServer() {
        try {
            socket = new Socket(SERVER_NAME, PORT);
            System.out.println("Connected to server.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void sendData() {
       ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            DataCntl.getDataCntl().write(out);
            System.out.println("SENDING " + DataCntl.getDataCntl().getData().getDocument().getText());
        } catch (IOException ex) {
            Logger.getLogger(NetworkCntl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void readData(){
        new Thread(new Runnable(){
            public void run() {
                ObjectInputStream in;
                while (true) {
                    try {
                        in = new ObjectInputStream(socket.getInputStream());
                        DataCntl.getDataCntl().read(in);
                        System.out.println(DataCntl.getDataCntl().getData().getDocument().getText());
                        NetworkCntl.this.loginCntl.getEditorCntl().updateText();
                    } catch (IOException ex) {
                        Logger.getLogger(NetworkCntl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }
}
