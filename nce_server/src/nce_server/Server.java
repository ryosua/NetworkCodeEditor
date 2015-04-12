package nce_server;

import java.net.*;
import java.io.*;
import java.util.Vector;

public class Server extends Thread
{
    private final ServerSocket serverSocket;
    private final SharedDocument document;
    private final Vector<Thread> clients;

    public Server(int port) throws IOException
    {
        document = new SharedDocument();
        clients = new Vector<Thread>();
        serverSocket = new ServerSocket(port);
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
                
                Thread clientThread = new ClientThread(client, document, clients);
               
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