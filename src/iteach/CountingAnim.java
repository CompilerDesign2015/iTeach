/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteach;

import static iteach.Counting.labelC;
import static iteach.Subtraction.labels;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author DANIEL KENNETH
 */
public class CountingAnim implements Runnable{
    
    Counting N;
    public int last;
  
    CountingAnim(int num2) throws IOException{
        N = new Counting();
        last = num2;
    }
    
    @Override
    public void run(){
        try {     
         int i,j;
                for ( i = 0; i < last;i++ ) {
                   
                    System.out.println("hehe");
                 //    Image bi =ImageIO.read(new File("resources/counting/"+ ++i +".png"));
                  //  labelC[--i].setIcon(new ImageIcon(bi.getScaledInstance(100, 100, 100)));
                    
                     try {
            Image bi =ImageIO.read(new File("resources/counting/"+ ++i +".png"));
            labelC[--i].setIcon(new ImageIcon(bi.getScaledInstance(250, 250, 100)));

              } catch (Exception e) {
                } 
                     Thread.sleep(500);
                }
           
        } catch (InterruptedException ex) {
            Logger.getLogger(SubtractionAnim.class.getName()).log(Level.SEVERE, null, ex);
        
		
    }
}
}

