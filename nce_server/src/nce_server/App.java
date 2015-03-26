package nce_server;

import java.io.IOException;

public class App
{
    /**
     * @param args the command line arguments
     */
    public static void main(String [] args)
    {
        System.out.println("Server App Started");
        int port = 5001;
        
        try
        {
            Thread t = new Server(port);
            t.start();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
