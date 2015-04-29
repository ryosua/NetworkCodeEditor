package cntl;

public class Client
{    
    public static void main(String[] args)
    {
        try
        {
            MainCntl mainCntl = new MainCntl();
            mainCntl.startApp();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}