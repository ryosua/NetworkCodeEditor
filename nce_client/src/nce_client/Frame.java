package nce_client;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame
{
    private final MainController mainController;
    
    public Frame(MainController mainController)
    {
        this.mainController = mainController;
        
        // Construct the panel.
        JPanel panel = new Panel(this.mainController);
        
        //Setupthe frame.
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, "Center");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        
        addWindowListener(new LogoutWindowAdapter());
    }
    
    /**
     * A window adapter that logs the user out when they close the frame.
     */
    private class LogoutWindowAdapter extends WindowAdapter
    {
        @Override
        public void windowClosing(WindowEvent winEvt)
        {
            mainController.getNetworkController().disconnectFromServer();
            System.exit(0);
        }
    }
}