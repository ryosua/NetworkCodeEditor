package nce_client;

import java.net.*;
import java.io.*;

public class NetworkController
{
    private DataOutputStream out;
    private DataInputStream in;
            
    public void connectToServer()
    {
        final String serverName = "127.0.0.1";
        final int port = 5001;

        try
        {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);

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
}