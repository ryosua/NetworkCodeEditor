package cntl;

import view.LoginUI;

public class LoginCntl
{
    private LoginUI loginUI;
    
    //Cntl references 
    private final MainCntl mainCntl;
    
    private String username;
    
    //contstructor
    public LoginCntl(MainCntl mainCntl)
    {
        this.mainCntl = mainCntl;
    }
    
    public void openLoginUI()
    {
        if(loginUI == null)
        {
            loginUI = new LoginUI(this);
        }
        
        loginUI.setVisible(true);
    }
    
    public void closeLoginUI()
    {
        if (loginUI == null)
        {
            throw new IllegalStateException("Can not close the loginUI if it"
                    + "is not open.");
        }
        
        loginUI.setVisible(false);
    }
    
    //accessors
    
    
    public MainCntl getMainCntl()
    {
        return mainCntl;
    }
    
    public String getUsername()
    {
        return username;
    }
          
    //mutators
    

    //otherMethods
    public boolean authenticate(String username)
    {
        /* IMPLEMENT LATER */
        //if matches criteria return true
        //else return false
        
        this.username = username;
        
        return true;
    }
}