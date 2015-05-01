package client.cntl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class LoadActionListener implements ActionListener {

    private final JFileChooser fileChooser;
    private final JFrame frame;
    private final JTextArea mainTextArea;

    public LoadActionListener(JFrame frame, JTextArea mainTextArea) {
        this.frame = frame;
        this.mainTextArea = mainTextArea;
        
        fileChooser = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int returnVal = fileChooser.showOpenDialog(frame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Open the file.
            System.out.println("Opening: " + file.getName() + ".");
            loadFile(file, mainTextArea);
        } else {
            System.out.println("Action canceled");
        }
    }
    
    /**
     * Loads the file chosen into the text area.
     */
    private void loadFile(File file, JTextArea mainTextArea) {
        ArrayList<String> lines = new ArrayList<String>();
        
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
}
