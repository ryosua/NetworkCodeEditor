package nce_client;

import java.net.*;
import java.io.*;

public class NetworkController
{
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
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF("Hello from " + client.getLocalSocketAddress());

            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("Server says " + in.readUTF());

            //client.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}