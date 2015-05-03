package model;

import java.io.Serializable;

public class Document implements Serializable{
    
    private String text;
    
    public Document(){
        
    }
    
    public void setText(String str){
        this.text = str;
    }
    
    public String getText(){
        return text;
    }
}
