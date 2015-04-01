package nce_client;

import java.net.*;
import java.io.*;

public class NetworkController
{ 
    private DataInputStream in;
    private DataOutputStream out;
     
    public void connectToServer()
    {
        final String SERVER_NAME = "127.0.0.1";
        final int PORT = 5001;

        try
        {
            System.out.println("Connecting to " + SERVER_NAME + " on port " + PORT);
            Socket client = new Socket(SERVER_NAME, PORT);

            System.out.println("Just connected to " + client.getRemoteSocketAddress());

            OutputStream outToServer = client.getOutputStream();
            out = new DataOutputStream(outToServer);
            
            sendMessage("Hello from " + client.getLocalSocketAddress());

            InputStream inFromServer = client.getInputStream();
            in = new DataInputStream(inFromServer);
            System.out.println("Server says " + in.readUTF());

            //client.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void sendMessage(String message)
    {
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
}