package client.cntl;

import model.User;
import client.view.LoginUI;

public class LoginCntl {
    
    private LoginUI loginUI;
    
    private NetworkCntl networkCntl;
    private EditorCntl editorCntl;
    
    public LoginCntl(){
        loginUI = new LoginUI(this);
        networkCntl = new NetworkCntl(this);
    }
    
    public boolean authenticate(String username){
        if(username != ""){
            System.out.println("Authenticated " + username);
            return true;
        }
        return false;
    }
    
    public void showLoginUI(){
        if(loginUI != null){
            loginUI.setVisible(true);
        }else{
            loginUI = new LoginUI(this);
        }
    }
    
    public NetworkCntl getNetworkCntl(){
        return networkCntl;
    }
    
    public EditorCntl getEditorCntl(){
        if(editorCntl == null){
            editorCntl = new EditorCntl(this, networkCntl);
        }
        return editorCntl;
    }
}
