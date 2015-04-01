package cntl;

import java.net.*;
import java.io.*;
import misc.Protocol;

public class NetworkCntl
{ 
    private DataInputStream in;
    private DataOutputStream out;
    private boolean connectedToServer;
    
    public NetworkCntl()
    {
        connectedToServer = false;
    }
     
    /**
     * 
     * @return true if the connection was successful
     */
    public boolean connectToServer()
    {
        if (connectedToServer == true)
        {
            throw new IllegalStateException("You may not connect to the server"
                    + " if you have already connected.");
        }
        
        final String SERVER_NAME = "127.0.0.1";
        final int PORT = 5001;
        
        try
        {
            System.out.println("Connecting to " + SERVER_NAME + " on port " + PORT);
            Socket client = new Socket(SERVER_NAME, PORT);
            
            connectedToServer = true;

            System.out.println("Just connected to " + client.getRemoteSocketAddress());

            OutputStream outToServer = client.getOutputStream();
            out = new DataOutputStream(outToServer);
            
            sendMessage("Hello from " + client.getLocalSocketAddress());

            InputStream inFromServer = client.getInputStream();
            in = new DataInputStream(inFromServer);
            System.out.println("Server says " + in.readUTF());
        }
        catch(IOException e)
        {
            System.out.println("Could not connect to the server.");
        }
        
        return connectedToServer;
    }
    
    public void sendMessage(String message)
    {
        if (connectedToServer == false)
        {
            throw new IllegalStateException("You may not send a message without"
                    + " connecting to the server first.");
        }
        
        try
        {
            if (out != null)
            {
                out.writeUTF(message);
            }
            else
            {
                System.out.println("No connection");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void disconnectFromServer()
    {
        if (connectedToServer == false)
        {
            throw new IllegalStateException("You may disconnect from the server"
                    + " if you are not connected to it.");
        }
        
        try
        {
            if (out != null)
            {
                out.writeUTF(Protocol.DISCONNECT_MESSAGE);
            }
            else
            {
                System.out.println("No connection");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @return false for disconnected or true for connected
     */
    public boolean getConnectionStatus()
    {
        return connectedToServer;
    }
}