/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cntl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import static java.lang.System.in;
import static java.lang.System.out;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Connection;
import model.Data;
import model.Document;

/**
 *
 * @author Eric
 */
public class DataCntl implements Serializable {

    //References

    private static DataCntl dataCntl;
    private Data theData = new Data();

    private DataCntl() {
        
    }

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
}
