package client.cntl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JTextArea;

/**
 * Loads and saves files.
 */
public class FileCntl {
    
    private File loadedFile;
       
    /**
     * Loads the file chosen into the text area.
     * @param file the file to load from
     * @param mainTextArea the text area to load text into
     */
    public void loadFile(File file, JTextArea mainTextArea) {
        ArrayList<String> lines = new ArrayList<String>();
        
        // Save the loadedFile for saving later.
        loadedFile = file;
        
        try {
            // Copy content from file.
            Scanner in = new Scanner(file);
            while(in.hasNext()) {
               lines.add(in.nextLine());
            }
            
            // Copy conent from file to text area.
            String fileContents = "";
            for(String line: lines) {
                fileContents += line + "\n";
            }
            mainTextArea.setText(fileContents);
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void saveFile() {
        
    }
}
