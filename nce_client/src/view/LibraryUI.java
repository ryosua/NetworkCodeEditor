/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import cntl.LibraryCntl;
import cntl.NetworkCntl;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import listener.SyncFieldListener;

/**
 *
 * @author Eric
 */
public class LibraryUI extends JFrame{

    private LibraryCntl libraryCntl;
    
    //UI components
    private JPanel mainPanel;
    private JPanel listPanel;
    private JPanel btnPanelSouth;
    private JScrollPane scrollPane;
    
    private JList libraryList;
    private DefaultListModel listModel;
    
    private JButton newBtn;
    private JButton editBtn;
    private JButton deleteBtn;

    //contstructor
    public LibraryUI(LibraryCntl parentCntl){
        libraryCntl = parentCntl;
        setSize(400, 600);
        setTitle("Library");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        setResizable(false);
        initComponents();
        setVisible(true);
        
        addWindowListener(new LogoutWindowAdapter());
    }
    
    //accessors
    

    //mutators
    

    //otherMethods
    private void initComponents(){
        //init all interface components here
        //init with layouts
        mainPanel = new JPanel(new BorderLayout());
        listPanel = new JPanel();
        btnPanelSouth = new JPanel();
        
        listModel = new DefaultListModel();
        libraryList = new JList(listModel);
        scrollPane = new JScrollPane(libraryList);
        
        JLabel sharedFieldLabel = new JLabel("Shared field:");
        JTextField sharedField = new JTextField();
        
        NetworkCntl networkCntl = libraryCntl.getLoginCntl().getNetworkCntl();
        sharedField.addKeyListener(new SyncFieldListener(networkCntl, sharedField));
        //sample List items
        for(int i = 0; i < 20; i++){
            listModel.addElement("Sample Element " + i);
        }
        
        //init with label text
        newBtn = new JButton("NEW");
        editBtn = new JButton("EDIT");
        deleteBtn = new JButton("DELETE");
        
        //add components to subpanels
        btnPanelSouth.add(newBtn);
        btnPanelSouth.add(editBtn);
        btnPanelSouth.add(deleteBtn);
        
        listPanel.add(scrollPane);
        listPanel.add(sharedFieldLabel);
        listPanel.add(sharedField);
        
        //add subpanels to mainPanel
        mainPanel.add(listPanel, BorderLayout.CENTER);
        mainPanel.add(btnPanelSouth, BorderLayout.SOUTH);
        
        //add main panel to jframe
        this.setJMenuBar(new LibraryMenuBar());
        this.add(mainPanel);
    }
    
    //Custom menubar
    private class LibraryMenuBar extends BasicMenuBar{
        
        //menus
        private JMenu fileMenu;
        
        //items
        private JMenuItem logoutItem;
        
        private LibraryMenuBar(){
            super();
            initAdditionalComponents();
        }
        
        private void initAdditionalComponents(){
            
            //init menus
            fileMenu = new JMenu("File");
            
            //init items
            logoutItem = new JMenuItem("Logout");
            logoutItem.addActionListener(new LogoutBtnListener());
            
            //add items to menus
            getClientMenu().add(logoutItem);
            
            //add menus to bar
            this.add(fileMenu);
        }
    }
    
    
    //actionListeners
    private class LogoutBtnListener implements ActionListener{

        public void actionPerformed(ActionEvent ae) {
            libraryCntl.getLoginCntl().getLoginUI();
            setVisible(false);
        }
    }
    
    /**
     * A window adapter that logs the user out when they close the frame.
     */
    private class LogoutWindowAdapter extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent winEvt){
            NetworkCntl networkCntl = libraryCntl.getLoginCntl().getNetworkCntl();
            
            // Disconnect from the server if there is a connection.
            if (networkCntl.getConnectionStatus() == true){
                networkCntl.disconnectFromServer();
            }
            
            //Close the app.
            System.exit(0);
        }
    }
}
