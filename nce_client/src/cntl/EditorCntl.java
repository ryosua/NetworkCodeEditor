package cntl;

import model.SharedDocument;
import view.EditorUI;

public class EditorCntl
{    
    private final SharedDocument document;
    
    //UI references
    private EditorUI editorUI;

    //Cntl references 
    private final MainCntl mainCntl;
            
    //contstructor
    public EditorCntl(MainCntl mainCntl)
    {
        this.mainCntl = mainCntl;
        
        document = new SharedDocument();
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
    
    public SharedDocument getDocument()
    {
        return document;
    }
   
    //mutators
    

    //otherMethods
    public void makeConnection()
    {
        //connect all users to document
    }
}