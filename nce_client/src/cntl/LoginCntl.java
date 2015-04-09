package cntl;

import view.LoginUI;

public class LoginCntl
{
    private LoginUI loginUI;
    
    //Cntl references 
    private final MainCntl mainCntl;
    
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
        loginUI.setVisible(false);
    }
    
    //accessors
    
    
    public MainCntl getMainCntl()
    {
        return mainCntl;
    }
          
    //mutators
    

    //otherMethods
    public boolean authenticate()
    {
        /* IMPLEMENT LATER */
        //if matches criteria return true
        //else return false
        
        return true;
    }
}