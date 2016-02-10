/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteach;

import static iteach.Subtraction.labels;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author DANIEL KENNETH
 */
public class SubtractionAnim implements Runnable{
    
    Subtraction N;
    public int min, sub, diff;
  
    SubtractionAnim(int num1, int num2) throws IOException{
        N  = new Subtraction();
        min = num1;
        sub = num2;
        diff = num1 - num2;
    }
   
    
    @Override
    public void run(){
        
        
        
        try {     
         int i,j;
                for ( i = 0; i < sub; i++) {
                    for(j=0; j<80; j++){
                        System.out.println("hehe");
                        //N.labels[i].setLocation(540+(40*i), 340+(j));
                        if(i>4){
                             N.labels[i].setLocation(400+(40*i), 340+(j));
                        }else{
                             N.labels[i].setLocation(600+(40*i), 390+(j));
                        }
                        
                        Thread.sleep(10);
                    }
                }
                for (; i < min; i++) {
                    
                    System.out.println("hehe");
                     //labels[i].setIcon(new javax.swing.ImageIcon("resources/subtraction/AppleGlow.png"));
                       try {
                        Image bi =ImageIO.read(new File("resources/subtraction/AppleGlow.png"));
                        labels[i].setText("");
                        labels[i].setIcon(new ImageIcon(bi.getScaledInstance(75, 75, 100)));
                            } catch (Exception e) {
                            } 
                     
                     
                     Thread.sleep(10);
                }
                N.jLabel3.setText(min + "-" + sub + "= " + diff);
           
        } catch (InterruptedException ex) {
            Logger.getLogger(SubtractionAnim.class.getName()).log(Level.SEVERE, null, ex);
        }
		
    }
    
 
    }

