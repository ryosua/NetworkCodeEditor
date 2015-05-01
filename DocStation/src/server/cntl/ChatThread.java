/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.cntl;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
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
public class ChatThread extends Thread{
    private ArrayList<ChatThread> connections = new ArrayList<ChatThread>();
    
    private Socket socket;
    
    private String message;
    
    public ChatThread(Socket socket, ArrayList<ChatThread> connections){
        this.socket = socket;
        this.connections = connections;
        connections.add(this);
    }
    
    public void run(){
        System.out.println("Listening on new ChatThread.");
        DataInputStream in;
        while(true){
            try {
                in = new DataInputStream(socket.getInputStream());
                message = in.readUTF();
                broadcastObjectToList(connections);
            } catch (EOFException ex) {
                System.out.println("A user logged out of the chat.");
                break;
            }
              catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
     public void broadcastObjectToList(ArrayList<ChatThread> connections){
        DataOutputStream out;
        for (ChatThread ct : connections) {
                try {
                    out = new DataOutputStream(ct.getSocket().getOutputStream());
                    out.writeUTF(message);
                    System.out.println("Updated data sent to chat.");
                } catch (EOFException ex) {
                    System.out.println("A user logged out of the chat.");
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        }
    }
    
    public Socket getSocket(){
        return socket;
    }
}
