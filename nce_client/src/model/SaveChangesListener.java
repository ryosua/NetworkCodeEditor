package model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextArea;

/**
 * Saves changes from a text area to a document on key released.
 */
public class SaveChangesListener implements KeyListener
{
    private final SharedDocument document;
    private final JTextArea textArea;
    
    public SaveChangesListener(SharedDocument document, JTextArea textArea)
    {
        this.document = document;
        this.textArea = textArea;
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
       document.setText(textArea.getText());
       document.setHasNewEdits(true);
    }
}