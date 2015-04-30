/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Eric
 */
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
