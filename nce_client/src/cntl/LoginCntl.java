/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cntl;

import view.LoginUI;

/**
 *
 * @author Eric
 */
public class LoginCntl{
    
    private LoginUI loginUI;
    private LibraryCntl libraryCntl;
    private EditorCntl editorCntl;

    //contstructor
    public LoginCntl(){
        loginUI = new LoginUI(this);
        System.out.println("Client App Started");
    }
    
    //accessors
    public void getLoginUI(){
        if(loginUI != null){
            loginUI.setVisible(true);
        }else{
            loginUI = new LoginUI(this);
        }
    }
    
    public void getLibraryUI(){
        if(libraryCntl != null){
            libraryCntl.getLibraryUI().setVisible(true);
        }else{
            libraryCntl = new LibraryCntl(this);
        }
    }
    
    public void getEditorUI(){
        if(editorCntl != null){
            editorCntl.getEditorUI();
        }else{
            editorCntl = new EditorCntl();
        }
    }

    //mutators
    

    //otherMethods
    public boolean authenticate(){
        /* IMPLEMENT LATER */
        //if matches criteria return true
        //else return false
        return true;
    }
}
