/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cntl;

import cntl.ServerDataCntl;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import model.Data;

/**
 *
 * @author Eric
 */
public class ConnectionThread extends Thread{
    
    private ArrayList<ConnectionThread> connections = new ArrayList<ConnectionThread>();
    
    private Socket socket;
    
    public ConnectionThread(Socket socket, ArrayList<ConnectionThread> connections){
        this.socket = socket;
        this.connections = connections;
        connections.add(this);
    }
    
    public void run(){
        System.out.println("Listening on new ConnectionThread.");
        Data input;
        ObjectInputStream in;
        while(true){
            try {
            in = new ObjectInputStream(socket.getInputStream());
            ServerDataCntl.getDataCntl().setData((Data) in.readObject());
            broadcastObjectToList(connections);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
    
     public void broadcastObjectToList(ArrayList<ConnectionThread> connections){
        ObjectOutputStream out;
        for (ConnectionThread ct : connections) {
            if(ct != this){
                try {
                    out = new ObjectOutputStream(ct.getSocket().getOutputStream());
                    out.writeObject(ServerDataCntl.getDataCntl().getData());
                    System.out.println("Updated data sent to client.");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public Socket getSocket(){
        return socket;
    }
}
