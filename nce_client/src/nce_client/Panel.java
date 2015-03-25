package nce_client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel extends JPanel
{
    private final MainController mainController;
    
    public Panel(MainController mainController)
    {
        this.mainController = mainController;
        
        final int ROWS = 3;
        final int COLUMNS = 2;
        
        //Create and set the layout.
        GridLayout grid = new GridLayout(ROWS, COLUMNS);
        setLayout(grid);
        
        // Labels
        JLabel userNameLabel = new JLabel("Choose a username:");
        JLabel inviteUsersLabel = new JLabel("Invite Users:");
        JLabel sharedFieldLabel = new JLabel("Shared field:");
        
        // Text fields.
        JTextField userName = new JTextField();
        JTextField inviteUsers = new JTextField();
        JTextField sharedField = new JTextField();
        
        // Add Action Listeners
        sharedField.addActionListener(new SyncFieldListener(mainController, sharedField));
        
        //Add UI elements.
        add(userNameLabel);
        add(userName);
        add(inviteUsersLabel);
        add(inviteUsers);
        add(sharedFieldLabel);
        add(sharedField);
    }
    
    public class SyncFieldListener implements ActionListener
    {
        private final MainController mainController;
        private final NetworkController networkController;
        private final JTextField sharedField;
        
        public SyncFieldListener(MainController mainController, JTextField sharedField)
        {
            this.mainController = mainController;
            this.sharedField = sharedField;
            
            networkController = mainController.getNetworkController();
        }
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            networkController.sendMessage(sharedField.getText());
        }
    }
}