package model;

import java.io.Serializable;

public class Data implements Serializable{
    
    private User user;
    private Document doc;
    
    public Data(){
        this.doc = new Document();
    }
    
    public void setUser(User user){
        this.user = user;
    }
    
    public void setDocument(Document doc){
        this.doc = doc;
    }
    
    public User getUser(){
        return user;
    }
    
    public Document getDocument(){
        return doc;
    }
}
