package cntl;

/**
 * A controller for accessing all the other controllers.
 * @author ryosua
 */
public final class MainCntl
{
    private EditorCntl editorCntl;
    private LoginCntl loginCntl;
    private NetworkCntl networkCntl;
      
    public void startApp()
    {
        System.out.println("Client App Started");
        getLoginCntl().openLoginUI();
    }
    
    public EditorCntl getEditorCntl()
    {
        if (editorCntl == null)
        {
            editorCntl = new EditorCntl(this);
        }
        
        return editorCntl;
    }
       
    public LoginCntl getLoginCntl()
    {
        if (loginCntl == null)
        {
            loginCntl = new LoginCntl(this);
        }
        
        return loginCntl;
    }
    
    public NetworkCntl getNetworkCntl()
    {
        if(networkCntl == null)
        {
            networkCntl = new NetworkCntl(this);
        }

        return networkCntl;
    }
}