package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BasicMenuBar extends JMenuBar
{
    //menus
    private JMenu clientMenu;

    //items
    private JMenuItem exitItem;
    
    //contstructor
    public BasicMenuBar()
    {
        initComponents();
    }
    
    //accessors
    public JMenu getClientMenu()
    {
        return clientMenu;
    }
    
    //mutators
    

    //otherMethods
    private void initComponents()
    {
        //init menus
        clientMenu = new JMenu("NCE_Client");
        
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ExitItemListener());
        
        clientMenu.add(exitItem);
        
        this.add(clientMenu);
    }
    
    public class ExitItemListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            System.exit(0);
        }
    }
}
