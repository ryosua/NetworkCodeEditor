package nce_server;

public class SharedDocument
{
    private String text;
    
    public synchronized void setText(String text)
    {
        this.text = text;
    }
    
    public String getText()
    {
        return text;
    }
}