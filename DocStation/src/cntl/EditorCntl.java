/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cntl;

import javax.swing.JTextArea;
import model.Data;
import view.EditorUI;

/**
 *
 * @author Eric
 */
public class EditorCntl {
    
    private LoginCntl loginCntl;
    private NetworkCntl networkCntl;
    
    private EditorUI editorUI;
    
    public EditorCntl(LoginCntl parentCntl, NetworkCntl networkCntl){
        this.editorUI = new EditorUI(this);
        this.networkCntl = networkCntl;
        this.loginCntl = parentCntl;
    }
    
    public void updateText() {
        new Thread(new Runnable() {
            public void run() {
                Data data = DataCntl.getDataCntl().getData();
                synchronized (data) {
                    int sel = EditorCntl.this.editorUI.getCursorPosition();
                    String text = DataCntl.getDataCntl().getData().getDocument().getText();
                    int length = text.length();
                    EditorCntl.this.editorUI.getMainTextArea().setText(text);
                    EditorCntl.this.editorUI.setCursorStart(sel);
                    System.out.println("Update: " + text);
                }
            }
        }).start();
    }
    
    public LoginCntl getLoginCntl(){
        return loginCntl;
    }
    
    public NetworkCntl getNetworkCntl(){
        return networkCntl;
    }
}
