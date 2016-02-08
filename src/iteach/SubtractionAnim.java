/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteach;

import static iteach.Subtraction.labels;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author DANIEL KENNETH
 */
public class SubtractionAnim implements Runnable{
    
    Subtraction N;
    public int min, sub;
  
    SubtractionAnim(int num1, int num2) throws IOException{
        N  = new Subtraction();
        min = num1;
        sub = num2;
    }
    
    @Override
    public void run(){
        try {     
         int i,j;
                for ( i = 0; i < sub; i++) {
                    for(j=0; j<80; j++){
                        System.out.println("hehe");
                        N.labels[i].setLocation(200+(40*i), 180+(j));
                        Thread.sleep(10);
                    }
                }
                for (; i < min; i++) {
                    
                    System.out.println("hehe");
                     labels[i].setIcon(new javax.swing.ImageIcon("/Users/jethrodivino/Dropbox/Compiler Design/Templates/Templates/NEW subtraction/glow.png"));
                     Thread.sleep(10);
                }
           
        } catch (InterruptedException ex) {
            Logger.getLogger(SubtractionAnim.class.getName()).log(Level.SEVERE, null, ex);
        }
		
    }
}
