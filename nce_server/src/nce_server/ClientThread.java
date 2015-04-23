package nce_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Vector;

/**
 * Each client runs it it's own thread.
 */
public class ClientThread extends Thread
{
    private final Vector<Thread> clients;
    private final Socket client;
    private final SharedDocument document;
    
    private DataInputStream in;
    private DataOutputStream out;
    
    private boolean canStart = false;
    
    public ClientThread(Socket client, SharedDocument document, Vector<Thread> clients)
    {
        this.client = client;
        this.document = document;
        
        this.clients = clients;
        
        // Add this client to the list of clients who will recieve broadcasts.
        clients.add(this);
        
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
                    
                    // Save changes from client to the document.
                    document.setText(readUTF);
                    
                    // Broadcast changes.
                    broadCastDocument(document, clients);
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
     * Broadcasts a change to just the client corresponding to the ClientThread
     * instance.
     */
    public void broadCastChange(SharedDocument document)
    {
        try
        {
            if (out != null)
            {
                out.writeUTF(document.getText());
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
     * The server can not be started if unless this method returns true. If it
     * returns false it indicates that there was an error with the connection to
     * the client.
     * @return if the ClientThread can be started.
     */
    public boolean canStart()
    {
        return canStart;
    }
    
    /**
     * Broadcasts a document to all clients in the room.
     * @param document the document to broadcast
     * @param clients the clients to receive the broadcast
     */
    private void broadCastDocument(SharedDocument document, Vector<Thread> clients)
    {
        for(Thread t: clients)
        {
            ClientThread ct = (ClientThread)t;
            ct.broadCastChange(document);
        }
    }
}