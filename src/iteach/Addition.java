/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteach;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author DANIEL KENNETH
 */
public class Addition extends javax.swing.JFrame {
    private static Object image1;

    /**
     * @param args the command line arguments
     */
    JLabel jLabel2;
    static int addend1;
    static int addend2;
    //static JLabel[] labelC; 
    static JLabel[] labelC, labelS;
    static BufferedImage[] image;

  
    Addition(int num1, int num2) throws IOException{
       addend1 = num1;
       addend2 = num2;
       AdditionAnim n = new AdditionAnim(addend1,addend2);
       Thread t = new Thread(n);
       t.start();
    }
    
    Addition() throws IOException{
       Initialize();  
    }
    

    public static void Initialize() throws IOException {
        JFrame frame1 = new JFrame();
        frame1.setVisible(true);
        frame1.setSize(1366, 768);
        JLabel jLabel2 = new javax.swing.JLabel();
        labelC = new JLabel[addend2];
        labelS = new JLabel[addend2];
        
        frame1.setLayout(null);
        
        
        try {
            Image bi =ImageIO.read(new File("/Users/jethrodivino/Dropbox/Compiler Design/Templates/Templates/NEW addition/bg.png"));
            jLabel2.setText("");
            jLabel2.setIcon(new ImageIcon(bi.getScaledInstance(1366, 768, 100)));
            frame1.add(jLabel2, 1, 0);

              } catch (Exception e) {
                } 

      //  jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\DANIEL KENNETH\\Dropbox\\Compiler Design\\Templates\\Templates\\NEW addition\\bg.png")); // NOI18N
      //  frame1.add(jLabel2);

        jLabel2.setBounds(0, 30, 1366, 768);
        int c;
        Graphics g = null;

        for (int k = 0; k < addend1; k++) {
            labelC[k] = new JLabel("");
           try {
            Image bi =ImageIO.read(new File("/Users/jethrodivino/Dropbox/Compiler Design/Templates/Templates/NEW addition/original.png"));
            labelC[k].setText("");
            labelC[k].setIcon(new ImageIcon(bi.getScaledInstance(75, 75, 100)));
            frame1.add(labelC[k], 1, 0);

              } catch (Exception e) {
                } 
            System.out.println(k);
            if(k>4){
                labelC[k].setBounds(((k-4) * 70)-10, 300, 75, 75);  //lower
            }else{
                labelC[k].setBounds(370 + ((k-5) * 60), 225, 75, 75);  //upper
            }

        }
        
        
          for (int k = 0; k < addend2; k++) {
            labelS[k] = new JLabel("");
           try {
            Image bi =ImageIO.read(new File("/Users/jethrodivino/Dropbox/Compiler Design/Templates/Templates/NEW addition/original.png"));
            labelS[k].setText("");
            labelS[k].setIcon(new ImageIcon(bi.getScaledInstance(75, 75, 100)));
            frame1.add(labelS[k], 1, 0);

              } catch (Exception e) {
                } 
            System.out.println(k);
            if(k>4){
                labelS[k].setBounds(((k-4) * 70)+ 430, 300, 75, 75);  //lower
            }else{
                labelS[k].setBounds(820 + ((k-5) * 60), 225, 75, 75);  //upper
            }

        }


    }
}