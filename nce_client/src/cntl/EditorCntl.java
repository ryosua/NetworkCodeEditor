/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cntl;

import view.EditorUI;

/**
 *
 * @author Eric
 */
public class EditorCntl{
    
    //UI references
    private EditorUI editorUI;

    //Cntl references 
            
    //contstructor
    public EditorCntl(){
        editorUI = new EditorUI(this);
    }
    
    //accessors
    public void getEditorUI(){
        if(editorUI != null){
            editorUI.setVisible(true);
        }else{
            editorUI = new EditorUI(this);
        }
    }

    //mutators
    

    //otherMethods
    public void makeConnection(){
        //connect all users to document
    }
}
