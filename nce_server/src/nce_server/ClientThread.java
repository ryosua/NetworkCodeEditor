package nce_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Each client runs it it's own thread.
 */
public class ClientThread extends Thread
{
    private final Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    
    private boolean canStart = false;
    
    public ClientThread(Socket client)
    {
        this.client = client;
        InputStream inFromClient = null;
        OutputStream outToClient = null;
        
        try
        {
            inFromClient = client.getInputStream();
            outToClient = client.getOutputStream();
            
            in = new DataInputStream(inFromClient);
            out = new DataOutputStream(outToClient);
            
            canStart = true;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run()
    {
        if (canStart == false)
        {
            throw new IllegalStateException("You may not start the server"
                    + "without checking canStart() .");
        }
        
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
    
    /**
     * The server can not be started if unless this method returns true. If it
     * returns false it indicates that there was an error with the connection to
     * the client.
     * @return if the ClientThread can be started.
     */
    public boolean canStart()
    {
        return canStart;
    }
}