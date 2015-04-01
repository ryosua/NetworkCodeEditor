/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import cntl.LoginCntl;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Eric
 */
public class LoginUI extends JFrame{
    
    private LoginCntl loginCntl;
    
    //UI components
    private JPanel mainPanel;
    private JPanel infoPanel;
    private JPanel btnPanel;
    
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    
    private JTextField usernameField;
    private JTextField passwordField;
    
    private JButton loginBtn;
    private JButton exitBtn;
    
    
    //contstructor
    public LoginUI(LoginCntl parentCntl){
        loginCntl = parentCntl;
        setSize(400, 150);
        setTitle("Login");
        setLocationRelativeTo(null);
        setUndecorated(true);
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
        infoPanel = new JPanel();
        btnPanel = new JPanel();
        
        //init with label text
        usernameLabel = new JLabel("Username: ");
        passwordLabel = new JLabel("Password: ");
        
        //init with column #
        usernameField = new JTextField(25);
        passwordField = new JTextField(25);
        
        //init with label text
        loginBtn = new JButton("Login");
        exitBtn = new JButton("Exit");
        
        //set btn listeners
        loginBtn.addActionListener(new LoginBtnListener());
        exitBtn.addActionListener(new ExitBtnListener());
        
        //add components to subpanels panels
        infoPanel.add(usernameLabel);
        infoPanel.add(usernameField);
        infoPanel.add(passwordLabel);
        infoPanel.add(passwordField);
        
        btnPanel.add(exitBtn);
        btnPanel.add(loginBtn);
        
        //add panels to main
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);
        
        this.setJMenuBar(new BasicMenuBar());
        
        //add main to jframe
        this.add(mainPanel);
    }
    
    //actionListeners
    private class LoginBtnListener implements ActionListener{

        public void actionPerformed(ActionEvent evt) {
            if(loginCntl.authenticate()){
                loginCntl.getLibraryUI();
                setVisible(false);
            }
            
        }
    }
    
    private class ExitBtnListener implements ActionListener{

        public void actionPerformed(ActionEvent evt) {
            System.exit(0);
        }
    }
}
