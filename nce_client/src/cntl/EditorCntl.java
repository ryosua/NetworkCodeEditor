package cntl;

import view.EditorUI;

public class EditorCntl
{    
    //UI references
    private EditorUI editorUI;

    //Cntl references 
            
    //contstructor
    public EditorCntl()
    {
        editorUI = new EditorUI(this);
    }
    
    //accessors
    public void getEditorUI()
    {
        if(editorUI != null)
        {
            editorUI.setVisible(true);
        }
        else
        {
            editorUI = new EditorUI(this);
        }
    }

    //mutators
    

    //otherMethods
    public void makeConnection()
    {
        //connect all users to document
    }
}