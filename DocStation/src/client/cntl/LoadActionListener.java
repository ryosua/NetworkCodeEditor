package client.cntl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class LoadActionListener implements ActionListener {

    private final JFileChooser fileChooser;
    private final FileCntl fileCntl;
    private final JFrame frame;
    private final JTextArea mainTextArea;

    public LoadActionListener(FileCntl fileCntl, JFrame frame, JTextArea mainTextArea) {
        this.fileCntl = fileCntl;
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
            fileCntl.loadFile(file, mainTextArea);
        } else {
            System.out.println("Action canceled");
        }
    }
}
