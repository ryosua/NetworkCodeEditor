package client.view;

import client.cntl.DataCntl;
import client.cntl.LoginCntl;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.User;

public class LoginUI extends JFrame{
    
    private LoginCntl loginCntl;
    
    //UI components
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    
    private JLabel usernameLabel;
    
    private JTextField usernameField;
    
    private JButton loginBtn;
    private JButton exitBtn;
    
    
    //contstructor
    public LoginUI(LoginCntl loginCntl){
        this.loginCntl = loginCntl;
        setSize(400, 150);
        setTitle("Login");
        setLocationRelativeTo(null);
        setFocusable(true);
        setResizable(false);
        initComponents();
        setVisible(true);
    }
    
    //accessors


    //mutators
    

    //otherMethods
    private void initComponents(){
        //init all interface components
        
        //init with layouts
        mainPanel = new JPanel(new BorderLayout());
        northPanel = new JPanel();
        southPanel = new JPanel();
        
        //init with label text
        usernameLabel = new JLabel("Select a Username: ");
        
        //init with column #
        usernameField = new JTextField(15);
        
        //init with label text
        loginBtn = new JButton("Login");
        exitBtn = new JButton("Exit");
        
        //set btn listeners
        loginBtn.addActionListener(new LoginBtnListener());
        exitBtn.addActionListener(new ExitBtnListener());
        
        //add components to subpanels panels
        northPanel.add(usernameLabel);
        northPanel.add(usernameField);
        
        southPanel.add(exitBtn);
        southPanel.add(loginBtn);
        
        //add panels to main
        mainPanel.add(northPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        //add main to jframe
        this.add(mainPanel);
    }
    
    //actionListeners
    private class LoginBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent evt){
            String name = usernameField.getText();
            if(LoginUI.this.loginCntl.authenticate(name)){
                User user = new User(name);
                DataCntl.getDataCntl().getData().setUser(user);
            }
            LoginUI.this.setVisible(false);
            LoginUI.this.loginCntl.getEditorCntl();
        }
    }
    
    private class ExitBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent evt){
            System.exit(0);
        }
    }
}
