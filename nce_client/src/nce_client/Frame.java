package nce_client;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame
{
    public Frame()
    {
        JPanel panel = new Panel();
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, "Center");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize (500, 500);
        setVisible(true);
    }
    
}
