package model;

/**
 * Models a document that is shared with other clients.
 */
public class SharedDocument
{
    /**
     * The contents of the document.
     */
    private String text;
    
    /**
     * Whether the document has edits that have not been sent to the server.
     */
    private boolean hasNewEdits;
    
    public SharedDocument()
    {
        hasNewEdits = false;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    public void setHasNewEdits(boolean newEdits)
    {
        hasNewEdits = newEdits;
    }
    
    public boolean getHasNewEdits()
    {
        return hasNewEdits;
    }
    
    public String getText()
    {
        return text;
    }
}