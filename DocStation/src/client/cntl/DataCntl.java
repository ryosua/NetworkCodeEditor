package client.cntl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Data;
import model.User;

public class DataCntl implements Serializable {

    //References

    private static DataCntl dataCntl;
    private Data theData = new Data();
    private User user = new User("");


    public static DataCntl getDataCntl() {
        if (dataCntl == null) {
            dataCntl = new DataCntl();
        }
        return dataCntl;
    }

    public Data getData() {
        return theData;
    }

    public void write(ObjectOutputStream out){
        try {
            out.writeObject(theData);
        } catch (IOException ex) {
            Logger.getLogger(DataCntl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void read(ObjectInputStream in) {
        try {
            theData = (Data) in.readObject();
        } catch (IOException ex) {
            Logger.getLogger(DataCntl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataCntl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setDocumentText(String text){
        theData.getDocument().setText(text);
    }
	
	public User getUser(){
        return user;
    }
    
    public void setUser(User user){
        this.user = user;
    }
}
