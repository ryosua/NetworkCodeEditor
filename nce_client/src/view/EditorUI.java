/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import cntl.EditorCntl;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Eric
 */
public class EditorUI extends JFrame{
    
    //Cntl references
    private EditorCntl editorCntl;

    //contstructor
    public EditorUI(EditorCntl parentCntl){
        editorCntl = parentCntl;
        setSize(400, 125);
        setTitle("File Name Goes Here!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        setResizable(false);
        initComponents();
        setVisible(true);
        initComponents();
    }
    
    //accessors


    //mutators
    

    //otherMethods
    private void initComponents(){
        
    }
}
