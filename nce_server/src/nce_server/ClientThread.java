package nce_server;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Each client runs it it's own thread.
 */
public class ClientThread extends Thread
{
    private final Socket client;
    
    private final DataInputStream in;
    
    public ClientThread(Socket client)
    {
        this.client = client;
        InputStream inFromClient = null;
        
        try
        {
            inFromClient = client.getInputStream();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        in = new DataInputStream(inFromClient);
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try
            {   
                String readUTF = in.readUTF();
                
                if (readUTF.equals(Protocol.DISCONNECT_MESSAGE))
                {
                    System.out.println("Client logged out.");
                    break;
                }
                else
                {
                    System.out.println("Client says: " + readUTF);
                }
            }
            catch(EOFException e)
            {
                System.out.println("Client quit unexpectantly.");
                break;
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }  
        }
    }
}