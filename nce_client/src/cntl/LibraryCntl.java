/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cntl;

import view.LibraryUI;

/**
 *
 * @author Eric
 */
public class LibraryCntl{
    
    //UI reference
    private LibraryUI libraryUI;
    
    //Cntl reference
    private LoginCntl loginCntl;

    //contstructor
    public LibraryCntl(LoginCntl parentCntl){
        loginCntl = parentCntl;
        libraryUI = new LibraryUI(this);
    }
    
    //accessors
    public LibraryUI getLibraryUI(){
        return libraryUI;
    }
    
    public LoginCntl getLoginCntl(){
        return loginCntl;
    }

    //mutators
    

    //otherMethods
    private void fetchLibrary(){
        //make connection with database
        //query SELECT * ;
        //add results to listview on libraryUI
    }
}
