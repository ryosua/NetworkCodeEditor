package client.model;

import java.net.Socket;

public class Connection {
    
    private Socket socket;
    
    public Connection(Socket socket){
        this.socket = socket;
    }
    
    public Socket getSocket(){
        return socket;
    }
}
