package view;

import cntl.EditorCntl;
import cntl.LoginCntl;
import cntl.MainCntl;
import cntl.NetworkCntl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.SaveChangesListener;
import model.SyncFieldTimerTask;

public class EditorUI extends JFrame
{
    //Cntl references
    private final EditorCntl editorCntl;
    private final LoginCntl loginCntl;
    private final MainCntl mainCntl;
    private final NetworkCntl networkCntl;
                
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
    public EditorUI(EditorCntl editorCntl)
    {
        this.editorCntl = editorCntl;
        mainCntl = editorCntl.getMainCntl();
        loginCntl = mainCntl.getLoginCntl();
        networkCntl = mainCntl.getNetworkCntl();
        
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
        
        usernameLabel = new JLabel("Username: " + loginCntl.getUsername());
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
        
        // Get updates from the server.
        networkCntl.updateAreaFromServer(mainTextArea);
        
        // Save changes from the text area to the document.
        mainTextArea.addKeyListener(new SaveChangesListener(editorCntl.getDocument(), mainTextArea));

        // Send updates to the server.
        final SyncFieldTimerTask sender  = new SyncFieldTimerTask(editorCntl.getDocument(), networkCntl);
        Timer timer = new Timer();
        long delay = 0;
        long periodInMilliseconds = 15000;
        timer.schedule(sender, delay, periodInMilliseconds);
        
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