package client.cntl;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;

public class FileSelectedListener extends MouseAdapter {

    private final FileCntl fileCntl;

    public FileSelectedListener(FileCntl fileCntl) {
        this.fileCntl = fileCntl;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Print the item clicked on.
        JList list = (JList) e.getSource();
        int index = list.locationToIndex(e.getPoint());
        if (index >= 0) {
            // Open a new dialog with selected recipient.
            Object o = list.getModel().getElementAt(index);
            String filename  = (String)o;
            
            System.out.println(filename);
            
            //Load file.
            fileCntl.loadFile(fileCntl.getFile(filename), false);
        }
    }
}
