package client.cntl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JTextArea;

/**
 * Loads and saves files.
 */
public class FileCntl {
    
    private JTextArea mainTextArea;
    private File loadedFile;
    
    public FileCntl(JTextArea mainTextArea) {
        this.mainTextArea = mainTextArea;
    }
       
    /**
     * Loads the file chosen into the text area.
     * @param file the file to load from
     * @param mainTextArea the text area to load text into
     */
    public void loadFile(File file, JTextArea mainTextArea) {
        ArrayList<String> lines = new ArrayList<>();
        
        // Save the loadedFile for saving later.
        loadedFile = file;
        
        Scanner in = null;
        try {
            // Copy content from file.
            in = new Scanner(file);
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
        finally {
            in.close();
        }
    }
    
    public void saveFile() {
        PrintWriter printwriter = null;
        try {
            printwriter = new PrintWriter(loadedFile);
            printwriter.print(mainTextArea.getText());
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            printwriter.close();
        }  
    }
}
