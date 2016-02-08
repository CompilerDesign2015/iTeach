/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteach;

import static iteach.Addition.labelS;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author DANIEL KENNETH
 */
public class Subtraction extends javax.swing.JFrame {

    /**
     * @param args the command line arguments
     */
    JLabel jLabel2;
    static int num,min,sub;
    //static JLabel[] labels; 
    static JLabel[] labels;

    Subtraction (int num1, int num2) throws IOException{
        num = num1;
        min = num1;
        sub = num2;
        SubtractionAnim n = new SubtractionAnim(min,sub);
        Thread t = new Thread(n);
        t.start();

    }
    Subtraction() throws IOException{
         Initialize();
    }

    public static void Initialize() {
        JFrame frame1 = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame1.setSize(screenSize.width, screenSize.height);
        frame1.setAlwaysOnTop(true);
        frame1.setResizable(false);       
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame1.setLayout(null);
        frame1.getContentPane().setBackground(Color.black);
        //frame1.setSize(700, 500);
        //frame1.setSize(1024, 576);        
        JLabel jLabel2 = new javax.swing.JLabel();
        labels = new JLabel[num];

        frame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame1.setLayout(null);
        
        
               try {
            Image bi =ImageIO.read(new File("resources/subtraction/bg.png"));
            jLabel2.setText("");
            jLabel2.setIcon(new ImageIcon(bi.getScaledInstance(screenSize.width, screenSize.height, 100)));
            frame1.add(jLabel2, 1, 0);

              } catch (Exception e) {
                } 

      //  jLabel2.setIcon(new javax.swing.ImageIcon("resources/subtraction/sssss.png")); // NOI18N
       // frame1.add(jLabel2);

       // jLabel2.setBounds(0, 30, 600, 337);
        jLabel2.setBounds(0, 0, screenSize.width, screenSize.height);
        int c;

        for (int k = 0; k < num; k++) {
            labels[k] = new JLabel("");
           // labels[k].setIcon(new javax.swing.ImageIcon("resources/subtraction/apol.png"));
           // frame1.add(labels[k], 1, 0);
            
            
             try {
            Image bi =ImageIO.read(new File("resources/subtraction/original.png"));
            labels[k].setText("");
            labels[k].setIcon(new ImageIcon(bi.getScaledInstance(75, 75, 100)));
            frame1.add(labels[k], 1, 0);
            

              } catch (Exception e) {
                } 
             
             //labels[k].setBounds(0, 0, 50, 50);
            System.out.println(k);
            if(k>4){
                labels[k].setBounds(680 + ((k-4) * 75), 300, 75, 75);
            }else{
                labels[k].setBounds(700 + (k * 75), 200, 75, 75);
            }

        }

    }
}


