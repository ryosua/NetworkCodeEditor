package cntl;

import view.EditorUI;

public class EditorCntl
{    
    //UI references
    private EditorUI editorUI;

    //Cntl references 
    private final MainCntl mainCntl;
            
    //contstructor
    public EditorCntl(MainCntl mainCntl)
    {
        this.mainCntl = mainCntl;
    }
    
    public void openEditorUI()
    {
        if(editorUI == null)
        {
            editorUI = new EditorUI(this);
        }
        
        editorUI.setVisible(true);
    }
    
    //accessors
    
    public MainCntl getMainCntl()
    {
        return mainCntl;
    }

    //mutators
    

    //otherMethods
    public void makeConnection()
    {
        //connect all users to document
    }
}