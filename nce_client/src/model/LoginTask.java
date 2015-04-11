package model;

import cntl.LoginCntl;
import cntl.MainCntl;
import cntl.NetworkCntl;
import javax.swing.SwingWorker;

public class LoginTask extends SwingWorker<Void, Void>
{
    private final LoginCntl loginCntl;
    private final MainCntl mainCntl;
    private final NetworkCntl networkCntl;
    
    public LoginTask(LoginCntl loginCntl, MainCntl mainCntl, NetworkCntl networkCntl)
    {
        this.loginCntl = loginCntl;
        this.mainCntl = mainCntl;
        this.networkCntl = networkCntl;
    }

    @Override
    protected Void doInBackground()
    {
        networkCntl.connectToServer();
        
        return null;
    }

    @Override
    protected void done()
    {
        loginCntl.closeLoginUI();
        mainCntl.getEditorCntl().openEditorUI();
    }
    
}