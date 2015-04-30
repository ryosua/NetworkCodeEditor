/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cntl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Eric
 */
public class ServerConnector extends Thread{
    
    private ArrayList<ConnectionThread> connections = new ArrayList<ConnectionThread>();
    
    private ServerSocket serverSocket;
    
    public ServerConnector(){
        startServer();
    }
    
    public void run(){
        acceptClients();
    }
    
    private void startServer(){
        final int PORT = 5025;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
     
     private void acceptClients(){
        while(true){
            try {
                System.out.println("Waiting for clients...");
                Socket socket = serverSocket.accept();
                System.out.println("Client accepted.");
                ConnectionThread ct = new ConnectionThread(socket, connections);
                ct.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
