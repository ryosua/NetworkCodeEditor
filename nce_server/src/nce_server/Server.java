package nce_server;

import java.net.*;
import java.io.*;
public class Server extends Thread
{
    private final ServerSocket serverSocket;

    public Server(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        //serverSocket.setSoTimeout(10000);
    }
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("Waiting for client on port "
                + serverSocket.getLocalPort() + "...");
                
                Socket client = serverSocket.accept();
                
                Thread clientThread = new ClientThread(client);
                
                if (((ClientThread)clientThread).canStart())
                {
                    clientThread.start();
                }
                
                System.out.println("Just connected to "
                + client.getRemoteSocketAddress());
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