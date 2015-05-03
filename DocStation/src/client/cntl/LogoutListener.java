package client.cntl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutListener implements ActionListener {

    public LogoutListener() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

}
