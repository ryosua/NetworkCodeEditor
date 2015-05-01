package client.view;

import client.cntl.DataCntl;
import client.cntl.EditorCntl;
import client.cntl.LoadActionListener;
import client.cntl.SaveActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.Document;

public class EditorUI extends JFrame{
    
    private EditorCntl editorCntl;
    
    private ArrayList<KeyEvent> keyList = new ArrayList<KeyEvent>();
    
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
    private JButton loadFileBtn;
    private JButton saveBtn;

    //contstructor
    public EditorUI(EditorCntl editorCntl){
        this.editorCntl = editorCntl;
        setSize(900, 600);
        setTitle("Editor");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        setResizable(false);
        initComponents();
        setVisible(true);
        //new TextUpdater(mainTextArea).start();
    }
    
    //accessors
    

    //mutators
    

    //otherMethods
    private void initComponents(){
        mainPanel = new JPanel(new BorderLayout());
        subPanel = new JPanel(new BorderLayout());
        userPanel = new JPanel(new BorderLayout());
        actionPanel = new JPanel();
        chatPanel = new JPanel(new BorderLayout());
        sendPanel = new JPanel();
        
        String name = DataCntl.getDataCntl().getData().getUser().getUsername();
        
        usernameLabel = new JLabel("Username: " + name);
        connectedUsersLabel = new JLabel("Connected users: ");
        chatLabel = new JLabel("Chat: ");
        
        logoutBtn = new JButton("Logout");
        sendBtn = new JButton("Send");
        loadFileBtn = new JButton("Load File");
        saveBtn = new JButton("Save File");
        
        saveBtn.addActionListener(new SaveActionListener());
                
        mainTextArea = new JTextArea();
        mainTextArea.setLineWrap(true);
        
        mainTextArea.addKeyListener(new TextListener());
        loadFileBtn.addActionListener(new LoadActionListener(this, mainTextArea));
        
        userTextArea = new JTextArea();
        userTextArea.setLineWrap(true);
        chatTextArea = new JTextArea();
        chatTextArea.setLineWrap(true);
        sendTextArea = new JTextArea();
        sendTextArea.setLineWrap(true);
        
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
        actionPanel.add(loadFileBtn);
        actionPanel.add(saveBtn);
        
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
    }
    
    public JTextArea getMainTextArea(){
        return mainTextArea;
    }
    
    public int getCursorPosition(){
        return mainTextArea.getSelectionStart();
    }
    
    public void setCursorStart(int sel){
        mainTextArea.setSelectionStart(sel);
    }
    
    public class TextListener implements KeyListener{
        public void keyTyped(KeyEvent ke) {
            /*KeyEvent key = ke;
            if((ke.getModifiers() & KeyEvent.ALT_DOWN_MASK) == KeyEvent.ALT_DOWN_MASK){
                ke.set
            } 
            ke.consume();
            keyList.add(key);
        }*/
           
            
            new Thread(new Runnable(){
               public void run(){
                   Document doc = DataCntl.getDataCntl().getData().getDocument();
                   synchronized (doc) {
                       String text = EditorUI.this.getMainTextArea().getText();
                       DataCntl.getDataCntl().getData().getDocument().setText(text);
                       EditorUI.this.editorCntl.getNetworkCntl().sendData();
                   }
               } 
            }).start();
        }
        public void keyPressed(KeyEvent ke) {
            
        }
        
        public void keyReleased(KeyEvent ke) {
           
        }
    }
}
