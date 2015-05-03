package server.cntl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerConnector extends Thread{
    
    private ArrayList<ConnectionThread> connections = new ArrayList<ConnectionThread>();
    
    private ServerSocket serverSocket;
    
    public ServerConnector(int port){
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
