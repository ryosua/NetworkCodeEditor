package listener;

import cntl.NetworkCntl;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;

public class SyncFieldListener implements KeyListener
{
    private final NetworkCntl networkController;
    private final JTextField sharedField;

    public SyncFieldListener(NetworkCntl networkController, JTextField sharedField)
    {
        this.networkController = networkController;
        this.sharedField = sharedField;
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
        networkController.sendMessage(sharedField.getText());
    }
}