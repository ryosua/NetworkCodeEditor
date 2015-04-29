package model;

import cntl.NetworkCntl;
import java.util.TimerTask;

public class SyncFieldTimerTask extends TimerTask
{
    private final SharedDocument document;
    private final NetworkCntl networkController;

    public SyncFieldTimerTask(SharedDocument document, NetworkCntl networkController)
    {
        this.document = document;
        this.networkController = networkController;
    }
    
    @Override
    public void run()
    {   
        // If the document has new edits since the last transmission, send them.
        if (document.getHasNewEdits() == true)
        {
            networkController.sendMessage(document.getText());
            document.setHasNewEdits(false);
        }
        
    }
}