/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteach;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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
        frame1.setSize(1136, 639);
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //frame1.setSize(screenSize.width, screenSize.height);
        frame1.setAlwaysOnTop(true);
        frame1.setResizable(false);       
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame1.setLayout(null);
        frame1.getContentPane().setBackground(Color.black);
        JLabel jLabel2 = new javax.swing.JLabel();
        labelC = new JLabel[addend1];
        labelS = new JLabel[addend2];
       // frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
     //   frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
       // frame1.setUndecorated(true);
        
        
        try {
            Image bi =ImageIO.read(new File("resources/addition/bg.png"));
            jLabel2.setText("");
            jLabel2.setIcon(new ImageIcon(bi.getScaledInstance( 1136, 639, 100)));
            frame1.add(jLabel2, 1, 0);

              } catch (Exception e) {
                } 

      //  jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\DANIEL KENNETH\\Dropbox\\Compiler Design\\Templates\\Templates\\NEW addition\\bg.png")); // NOI18N
      //  frame1.add(jLabel2);

        jLabel2.setBounds(0, 0, 1136, 639);
       int  k = 0;

        for ( k = 0; k < addend1; k++) {
            labelC[k] = new JLabel("");
           try {
            Image bi =ImageIO.read(new File("resources/addition/original.png"));
            labelC[k].setText("");
            labelC[k].setIcon(new ImageIcon(bi.getScaledInstance(50, 50, 100)));
            frame1.add(labelC[k], 1, 0);

              } catch (Exception e) {
                } 
            System.out.println(k);
            if(k>4){
                labelC[k].setBounds(((k-4) * 60)-10, 220, 50, 50);  //lower
            }else{
                labelC[k].setBounds(320 + ((k-5) * 60), 180, 50, 50);  //upper
            }

        }
        
        
          for (k = 0; k < addend2; k++) {
            labelS[k] = new JLabel("");
           try {
            Image bi =ImageIO.read(new File("resources/addition/original.png"));
            labelS[k].setText("");
            labelS[k].setIcon(new ImageIcon(bi.getScaledInstance(50, 50, 100)));
            frame1.add(labelS[k], 1, 0);

              } catch (Exception e) {
                } 
            System.out.println(k);
            if(k>4){
                labelS[k].setBounds(((k-4) * 60)+ 370, 230, 50, 50);  //lower
            }else{
                labelS[k].setBounds(720 + ((k-5) * 60), 180, 50, 50);  //upper
            }

        }


    }
}