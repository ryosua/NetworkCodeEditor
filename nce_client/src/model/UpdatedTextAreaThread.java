package model;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.UTFDataFormatException;
import javax.swing.JTextArea;

public class UpdatedTextAreaThread implements Runnable
{
    private final DataInputStream in;
    private final JTextArea textArea;
    
    public UpdatedTextAreaThread(DataInputStream in, JTextArea textArea)
    {
        System.out.println("UpdatedTextAreaThread contructed.");
        this.in = in;
        this.textArea = textArea;
    }
    
    @Override
    public void run()
    {
        System.out.println("UpdatedTextAreaThread run called.");
        while(true)
        {
            try
            {   
                System.out.println("UpdatedTextAreaThread looping.");
                String readUTF = in.readUTF();
                System.out.println("Server says: " + readUTF);
                
                // Update text area with input from server.
                textArea.setText(readUTF);
                
                // Move cursor to the end of the text area.
                int end = textArea.getText().length();
                textArea.setSelectionStart(end);
                textArea.setSelectionEnd(end);
            }
            catch(UTFDataFormatException e)
            {
                e.printStackTrace();
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