package listener;

import cntl.NetworkCntl;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextArea;

public class SyncFieldListener implements KeyListener
{
    private final NetworkCntl networkController;
    private final JTextArea sharedTextArea;

    public SyncFieldListener(NetworkCntl networkController, JTextArea sharedTextArea)
    {
        this.networkController = networkController;
        this.sharedTextArea = sharedTextArea;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        // Do nothing.
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        // Do nothing.
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        networkController.sendMessage(sharedTextArea.getText());
    }
}