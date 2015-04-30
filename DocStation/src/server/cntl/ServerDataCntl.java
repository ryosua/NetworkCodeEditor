package server.cntl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static java.lang.System.out;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Data;
import model.Document;

public class ServerDataCntl implements Serializable {
    //References
    private static ServerDataCntl dataCntl;
    private Data theData = new Data();
    private static final String fileName = "data.ser";

    private ServerDataCntl(){
       this.readDataFile();
    }
    
    public static ServerDataCntl getDataCntl(){
        if(dataCntl == null)
        {
            dataCntl = new ServerDataCntl();
        }
        return dataCntl;
    }
    
    public Data getData(){
        return theData;
    }
    
    //Accessors
    public void readDataFile(){
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try{
            fis = new FileInputStream(fileName);
            in = new ObjectInputStream(fis);
            theData = (Data)in.readObject();
            in.close();
        }
        catch(FileNotFoundException ex){
            writeDataFile();
            readDataFile();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
    
    public void writeDataFile(){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try{
            fos = new FileOutputStream(fileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(theData);
            out.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
   
    
    public void readFromClient(ObjectInputStream in) {
            try { 
                while (true) {
                    theData = (Data) in.readObject();
                    System.out.println("From Client: " + theData.getDocument().getText());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
    }
    
    public void writeToClient(ObjectOutputStream out){
        try {
            out.writeObject(theData);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setData(Data data){
        theData = data;
    }
}
