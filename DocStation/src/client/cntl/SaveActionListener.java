package client.cntl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveActionListener implements ActionListener {

    private final FileCntl fileCntl;

    public SaveActionListener(FileCntl fileCntl) {
        this.fileCntl = fileCntl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Save button pressed.");
        fileCntl.saveFile();
    }

}
