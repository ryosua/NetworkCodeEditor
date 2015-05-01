package client.cntl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;

public class LoadActionListener implements ActionListener {

    private final FileCntl fileCntl;

    public LoadActionListener(FileCntl fileCntl) {
        this.fileCntl = fileCntl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File file = fileCntl.getFileFromChooser(JFileChooser.OPEN_DIALOG);
   
        if (file != null) {
            fileCntl.loadFile(file);
           
        } else {
             System.out.println("Action canceled");
        }
    }
}
