package client.cntl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class LoadActionListener implements ActionListener {

    private final JFileChooser fileChooser;
    private final JFrame frame;

    public LoadActionListener(JFrame frame) {
        this.frame = frame;
        fileChooser = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int returnVal = fileChooser.showOpenDialog(frame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // Open the file.
            System.out.println("Opening: " + file.getName() + ".");
        } else {
            System.out.println("Action canceled");
        }
    }
}
