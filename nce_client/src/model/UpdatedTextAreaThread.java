package model;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import javax.swing.JTextArea;

public class UpdatedTextAreaThread extends Thread
{
    private final DataInputStream in;
    private final JTextArea textArea;
    
    public UpdatedTextAreaThread(DataInputStream in, JTextArea textArea)
    {
        this.in = in;
        this.textArea = textArea;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try
            {   
                String readUTF = in.readUTF();
                System.out.println("Server says: " + readUTF);
                
                // Update text area with input from server.
                textArea.setText(readUTF);
                
                // Move cursor to the end of the text area.
                int end = textArea.getText().length();
                textArea.setSelectionStart(end);
                textArea.setSelectionEnd(end);
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