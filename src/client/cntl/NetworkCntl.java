package client.cntl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkCntl{

    public static final int EDIT_PORT = 5025;
    public static final int CHAT_PORT = 5050;

    private LoginCntl loginCntl;
	
    private Socket editSocket;
    private Socket chatSocket;
    private String message;

    public NetworkCntl(LoginCntl parentCntl) {
        this.loginCntl = parentCntl;
    }
    
    public void connectToServer() {
        try {
            DataCntl dataCntl = DataCntl.getDataCntl();
            editSocket = new Socket(dataCntl.getIPAddress(), EDIT_PORT);
            chatSocket = new Socket(dataCntl.getIPAddress(), CHAT_PORT);
            System.out.println("Connected to server.");
            System.out.println("IP: " + dataCntl.getIPAddress());
          
            readData();
            readMessages();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void sendData() {
       ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(editSocket.getOutputStream());
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
                        in = new ObjectInputStream(editSocket.getInputStream());
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
	
    public void sendMessage(){
        DataOutputStream out;
        try {
            out = new DataOutputStream(chatSocket.getOutputStream());
            out.writeUTF(message);
            System.out.println("SENDING " + DataCntl.getDataCntl().getData().getDocument().getText());
        } catch (IOException ex) {
            Logger.getLogger(NetworkCntl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void readMessages(){
         new Thread(new Runnable(){
            public void run() {
                DataInputStream in;
                while (true) {
                    try {
                        in = new DataInputStream(chatSocket.getInputStream());
                        message = in.readUTF();
                        System.out.println(message);
                        NetworkCntl.this.loginCntl.getEditorCntl().updateChat(message);
                    } catch (IOException ex) {
                        Logger.getLogger(NetworkCntl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }
    
    public void setMessage(String str){
        this.message = str;
    }
}
