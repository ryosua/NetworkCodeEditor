package nce_server;

import java.net.*;
import java.io.*;
public class SimpleServer extends Thread
{
    private ServerSocket serverSocket;

    public SimpleServer(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        //serverSocket.setSoTimeout(10000);
    }
    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("Waiting for client on port "
                + serverSocket.getLocalPort() + "...");
                Socket client = serverSocket.accept();
                System.out.println("Just connected to "
                + client.getRemoteSocketAddress());
                client.close();
            }
            catch(SocketTimeoutException s)
            {
                System.out.println("Socket timed out!");
                break;
            }
            catch(IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
}