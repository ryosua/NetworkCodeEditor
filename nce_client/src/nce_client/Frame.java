package nce_client;
import java.awt.BorderLayout;
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
    }
}