/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.cntl;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Eric
 */
public class ChatConnector extends Thread{
    
    private ArrayList<ChatThread> connections = new ArrayList<ChatThread>();
    
    private ServerSocket serverSocket;
    
    public ChatConnector(int port){
        startServer(port);
    }
    
    public void run(){
        acceptClients();
    }
    
    private void startServer(int port){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
     
     private void acceptClients(){
        while(true){
            try {
                System.out.println("Waiting for chats...");
                Socket socket = serverSocket.accept();
                System.out.println("Chat accepted.");
                ChatThread ct = new ChatThread(socket, connections);
                ct.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
