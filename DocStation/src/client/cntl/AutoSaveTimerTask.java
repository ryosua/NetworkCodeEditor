package client.cntl;

import java.util.TimerTask;

/**
 * Auto-saves the loaded file.
 * @author ryosua
 */
public class AutoSaveTimerTask extends TimerTask {
    
    private final FileCntl fileCntl;
    
    public AutoSaveTimerTask(FileCntl fileCntl) {
        this.fileCntl = fileCntl;
    }

    @Override
    public void run() {
        System.out.println("Autosaving...");
        fileCntl.saveFile();
    }
    
}
