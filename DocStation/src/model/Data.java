package model;

import java.io.Serializable;

public class Data implements Serializable{
    
    private Document doc;
    
    public Data(){
        this.doc = new Document();
    }
    
    public void setDocument(Document doc){
        this.doc = doc;
    }
    
    public Document getDocument(){
        return doc;
    }
}
