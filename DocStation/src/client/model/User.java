/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.model;

import java.io.Serializable;

/**
 *
 * @author Eric
 */
public class User implements Serializable{
    
    private String username;
    
    public User(String username){
        this.username = username;
    }
    
    public String getUsername(){
        return username;
    }
}
