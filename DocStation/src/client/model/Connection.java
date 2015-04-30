package client.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection {
    
    private Socket socket;
    
    public Connection(Socket socket){
        this.socket = socket;
    }
    
    public Socket getSocket(){
        return socket;
    }
}
