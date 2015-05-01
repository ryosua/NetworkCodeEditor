package view;

import cntl.LoginCntl;
import cntl.MainCntl;
import cntl.NetworkCntl;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.LoginTask;

public class LoginUI extends JFrame
{
    //Cntl references 
    private final LoginCntl loginCntl;
    private final MainCntl mainCntl;
    private final NetworkCntl networkCntl;
    
    //UI components
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel joinPanel;
    private JPanel joinbtnPanel;
    private JPanel idFieldPanel;
    private JPanel IPFieldPanel;
    
    
    private JLabel usernameLabel;
    private JLabel IPLabel;
    
    private JTextField usernameField;
    private JTextField IPField;
    
    private JButton loginBtn;
    private JButton exitBtn;
    private JButton joinRoomBtn;
    
    
    //contstructor
    public LoginUI(LoginCntl loginCntl)
    {
        this.loginCntl = loginCntl;
        mainCntl = loginCntl.getMainCntl();
        networkCntl = mainCntl.getNetworkCntl();
        
        setSize(400, 150);
        setTitle("Login");
        setLocationRelativeTo(null);
        setFocusable(true);
        setResizable(false);
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    //accessors


    //mutators
    

    //otherMethods
    private void initComponents()
    {
        //init all interface components
        
        //init with layouts
        mainPanel = new JPanel(new BorderLayout());
        northPanel = new JPanel();
        southPanel = new JPanel(new GridLayout(0,2));
        joinPanel = new JPanel();
        joinbtnPanel = new JPanel(new GridLayout(3,1));
        idFieldPanel = new JPanel();
        
        
        
        //init with label text
        usernameLabel = new JLabel("Select a Username: ");
        IPLabel = new JLabel("IP Address: ");
        
        //init with column #
        usernameField = new JTextField(15);
        IPField = new JTextField(20);
        IPField.setSize(1, 10);
        
        //init with label text
        loginBtn = new JButton("Login");
        loginBtn.setSize(20, 5);
        exitBtn = new JButton("Exit");
        joinRoomBtn = new JButton("Join Room");
        
        //set btn listeners
        joinRoomBtn.addActionListener(new LoginBtnListener());
        exitBtn.addActionListener(new ExitBtnListener());
        
        //add components to subpanels panels
        northPanel.add(usernameLabel);
        northPanel.add(usernameField);
        
                        
        joinbtnPanel.add(joinRoomBtn, BorderLayout.NORTH);
          
        southPanel.add(joinPanel);
                       
        //add panels to main
        mainPanel.add(northPanel, BorderLayout.PAGE_START);
        mainPanel.add(joinPanel, BorderLayout.NORTH);
        mainPanel.add(IPField, BorderLayout.CENTER);
        mainPanel.add(IPLabel, BorderLayout.WEST);
        mainPanel.add(joinRoomBtn, BorderLayout.PAGE_END);
        
        this.setJMenuBar(new BasicMenuBar());
        
        //add main to jframe
        this.add(mainPanel);
    }
    
    //actionListeners
    private class LoginBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent evt)
        {
            if(loginCntl.authenticate(usernameField.getText()))
            {
                // Connect to the server in a new thread.
                LoginTask loginTask = new LoginTask(loginCntl, mainCntl, networkCntl);
                loginTask.execute();
            }
        }
    }
        public void actionPerfromed(ActionEvent evt)
        {
            if(loginCntl.authenticate(IPField.getText()))
            {
                //IP actionPerformed
            }
        }
    
    private class ExitBtnListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent evt)
        {
            System.exit(0);
        }
    }
}