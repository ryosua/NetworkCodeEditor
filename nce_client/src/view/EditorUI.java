package view;

import cntl.EditorCntl;
import cntl.LoginCntl;
import cntl.NetworkCntl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.SyncFieldListener;

public class EditorUI extends JFrame
{
    //Cntl references
    private final EditorCntl editorCntl;
    // TODO: We need a reference to the networkCntl to add the shared field.
    //private final NetworkCntl networkCntl;
    //private final LoginCntl logingCntl;
                
    //UI components
    private JPanel mainPanel;
    private JPanel subPanel;
    private JPanel actionPanel;
    private JPanel userPanel;
    private JPanel chatPanel;
    private JPanel sendPanel;
    
    private JScrollPane editorScrollPane;
    private JScrollPane userScrollPane;
    private JScrollPane chatScrollPane;
    private JScrollPane sendScrollPane;
    
    private JTextArea mainTextArea;
    private JTextArea userTextArea;
    private JTextArea chatTextArea;
    private JTextArea sendTextArea;
    
    private JLabel usernameLabel;
    private JLabel chatLabel;
    private JLabel connectedUsersLabel;
    
    private JButton logoutBtn;
    private JButton sendBtn;

    //contstructor
    public EditorUI(EditorCntl parentCntl)
    {
        editorCntl = parentCntl;
        
        // TODO: We need a reference to the networkCntl to add the shared field.
        //logingCntl = editorCntl.getLoginCntl();
        //networkCntl = logingCntl.getNetworkCntl();
        
        setSize(800, 600);
        setTitle("Editor");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        setResizable(false);
        initComponents();
        setVisible(true);
        initComponents();
    }
    
    //accessors
    

    //mutators
    

    //otherMethods
    private void initComponents()
    {
        mainPanel = new JPanel(new BorderLayout());
        subPanel = new JPanel(new BorderLayout());
        userPanel = new JPanel(new BorderLayout());
        actionPanel = new JPanel();
        chatPanel = new JPanel(new BorderLayout());
        sendPanel = new JPanel();
        
        usernameLabel = new JLabel("Username ex: ewp5080");
        connectedUsersLabel = new JLabel("Connected users: ");
        chatLabel = new JLabel("Chat: ");
        
        logoutBtn = new JButton("Logout");
        sendBtn = new JButton("Send");
        
        mainTextArea = new JTextArea();
        mainTextArea.setLineWrap(true);
        userTextArea = new JTextArea();
        userTextArea.setLineWrap(true);
        chatTextArea = new JTextArea();
        chatTextArea.setLineWrap(true);
        sendTextArea = new JTextArea();
        sendTextArea.setLineWrap(true);
        
        // TODO: We need a reference to the networkCntl to add the shared field.
        //addKeyListener(new SyncFieldListener(networkCntl, mainTextArea));
        
        editorScrollPane = new JScrollPane(mainTextArea);
        editorScrollPane.setPreferredSize(new Dimension(435, 500));
        editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        userScrollPane = new JScrollPane(userTextArea);
        userScrollPane.setPreferredSize(new Dimension(100, 75));
        userScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sendScrollPane = new JScrollPane(sendTextArea);
        sendScrollPane.setPreferredSize(new Dimension(210, 50));
        sendScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        chatScrollPane = new JScrollPane(chatTextArea);
        chatScrollPane.setPreferredSize(new Dimension(100, 325));
        chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        actionPanel.add(usernameLabel);
        actionPanel.add(logoutBtn);
        
        userPanel.add(connectedUsersLabel, BorderLayout.NORTH);
        userPanel.add(userScrollPane, BorderLayout.CENTER);
        
        sendPanel.add(sendScrollPane);
        sendPanel.add(sendBtn);
        
        chatPanel.add(chatLabel, BorderLayout.NORTH);
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);
        chatPanel.add(sendPanel, BorderLayout.SOUTH);
        
        subPanel.add(actionPanel, BorderLayout.NORTH);
        subPanel.add(userPanel, BorderLayout.CENTER);
        subPanel.add(chatPanel, BorderLayout.SOUTH);
        
        mainPanel.add(editorScrollPane, BorderLayout.CENTER);
        mainPanel.add(subPanel, BorderLayout.EAST);
        this.add(mainPanel);
        
        this.setJMenuBar(new BasicMenuBar());
    }
}