package client.view;

import client.cntl.AutoSaveTimerTask;
import client.cntl.DataCntl;
import client.cntl.EditorCntl;
import client.cntl.FileCntl;
import client.cntl.LoadActionListener;
import client.cntl.LogoutListener;
import client.cntl.SaveActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.Document;
import model.User;

public class EditorUI extends JFrame{
    
    private final EditorCntl editorCntl;
    
    private ArrayList<KeyEvent> keyList = new ArrayList<>();
    
     //UI components
    private JPanel mainPanel;
    private JPanel subPanel;
    private JPanel actionPanel;
    private JPanel filesPanel;
    private JPanel chatPanel;
    private JPanel sendPanel;
    
    private JScrollPane editorScrollPane;
    private JScrollPane filesScollPane;
    private JScrollPane chatScrollPane;
    private JScrollPane sendScrollPane;
    
    private JTextArea mainTextArea;
    private JTextArea chatTextArea;
    private JTextArea sendTextArea;
    
    private JLabel usernameLabel;
    private JLabel chatLabel;
    private JLabel filesLabel;
    
    private JList fileList;
    
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
        setResizable(true);
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
        filesPanel = new JPanel(new BorderLayout());
        actionPanel = new JPanel();
        chatPanel = new JPanel(new BorderLayout());
        sendPanel = new JPanel();
               
        DataCntl dataCntl = DataCntl.getDataCntl();
        User user = dataCntl.getUser();
        String username = user.getUsername();
        
        usernameLabel = new JLabel("Username: " + username);
        filesLabel = new JLabel("Files: ");
        chatLabel = new JLabel("Chat: ");
        
        logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(new LogoutListener());
        sendBtn = new JButton("Send");
        loadFileBtn = new JButton("Load File");
        saveBtn = new JButton("Save File");
	sendBtn.addActionListener(new SendListener());
        
        mainTextArea = new JTextArea();
        mainTextArea.setLineWrap(true);
        
        DefaultListModel fileListModel = new DefaultListModel();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogType(fileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setFileSelectionMode(fileChooser.FILES_AND_DIRECTORIES);
        fileList = new JList(fileListModel);
        
        TextListener textListener = new TextListener();
        mainTextArea.addKeyListener(textListener);
        FileCntl fileCntl = new FileCntl(fileChooser, fileList, this, mainTextArea, textListener);
        saveBtn.addActionListener(new SaveActionListener(fileCntl));
        loadFileBtn.addActionListener(new LoadActionListener(fileCntl));
       
        chatTextArea = new JTextArea();
        chatTextArea.setLineWrap(true);
        chatTextArea.setEditable(false);
        sendTextArea = new JTextArea();
        sendTextArea.setLineWrap(true);
        
        editorScrollPane = new JScrollPane(mainTextArea);
        editorScrollPane.setPreferredSize(new Dimension(435, 500));
        editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        filesScollPane = new JScrollPane(fileList);
        filesScollPane.setPreferredSize(new Dimension(100, 75));
        filesScollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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
        
        filesPanel.add(filesLabel, BorderLayout.NORTH);
        filesPanel.add(filesScollPane, BorderLayout.CENTER);
        
        sendPanel.add(sendScrollPane);
        sendPanel.add(sendBtn);
        
        chatPanel.add(chatLabel, BorderLayout.NORTH);
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);
        chatPanel.add(sendPanel, BorderLayout.SOUTH);
        
        subPanel.add(actionPanel, BorderLayout.NORTH);
        subPanel.add(chatPanel, BorderLayout.CENTER);
        
        mainPanel.add(editorScrollPane, BorderLayout.CENTER);
        mainPanel.add(subPanel, BorderLayout.EAST);
        mainPanel.add(filesPanel, BorderLayout.WEST);
        
        this.add(mainPanel);
        
        // Start autosaving the doc.
        // It only saves if a file is loaded.
        AutoSaveTimerTask saver  = new AutoSaveTimerTask(fileCntl);
        Timer timer = new Timer(true);
        
        long delay = 0;
        long periodInMilliseconds = 30000;
        timer.schedule(saver, delay, periodInMilliseconds);
    }
    
    public JTextArea getMainTextArea(){
        return mainTextArea;
    }
    
    public JTextArea getChatTextArea(){
        return chatTextArea;
    }
    
    public JTextArea getSendTextArea(){
        return sendTextArea;
    }
    
    public int getCursorPosition(){
        return mainTextArea.getSelectionStart();
    }
    
    public void setCursorStart(int sel){
        mainTextArea.setSelectionStart(sel);
    }
    
    public class TextListener implements KeyListener{
        
        public void sendData() {
            new Thread(() -> {
                Document doc = DataCntl.getDataCntl().getData().getDocument();
                synchronized (doc) {
                    String text = EditorUI.this.getMainTextArea().getText();
                    DataCntl.getDataCntl().getData().getDocument().setText(text);
                    EditorUI.this.editorCntl.getNetworkCntl().sendData();
                } 
            }).start();
        }
                
        @Override
        public void keyTyped(KeyEvent ke) {
            sendData();
        }
        
        @Override
        public void keyPressed(KeyEvent ke) {
            
        }
        
        @Override
        public void keyReleased(KeyEvent ke) {
           
        }
    }
	
    public class SendListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            String text = EditorUI.this.getSendTextArea().getText();
            EditorUI.this.getSendTextArea().setText("");
            String user = DataCntl.getDataCntl().getUser().getUsername();
            EditorUI.this.editorCntl.getNetworkCntl().setMessage(user + ": " + text);
            EditorUI.this.editorCntl.getNetworkCntl().sendMessage();
        }
    }
}
