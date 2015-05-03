package client.main;

import client.cntl.LoginCntl;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ClientApp {
    public static void main(String[] args){
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException e) {
           e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
           e.printStackTrace();
        }
        catch (InstantiationException e) {
           e.printStackTrace();
        }
        catch (IllegalAccessException e) {
           e.printStackTrace();
        }
        
        LoginCntl loginCntl = new LoginCntl();
    }
}
