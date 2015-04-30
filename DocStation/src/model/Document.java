
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
