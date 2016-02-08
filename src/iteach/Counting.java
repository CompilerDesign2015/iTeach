/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteach;

import static iteach.Subtraction.labels;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author DANIEL KENNETH
 */
public class Counting extends javax.swing.JFrame {
    private static Object image1;

    /**
     * @param args the command line arguments
     */
    JLabel jLabel2;

    static int last;
    //static JLabel[] labelC; 
    static JLabel[] labelC;
    static BufferedImage[] image;

    Counting(int num) throws IOException{
        last = num;
        CountingAnim n = new CountingAnim(last);
        Thread t = new Thread(n);
        t.start();

    }
    
    Counting() throws IOException{
       Initialize();  
    }

    public static void Initialize() throws IOException {
        JFrame frame1 = new JFrame();
        frame1.setVisible(true);
        frame1.setSize(700, 500);
        JLabel jLabel2 = new javax.swing.JLabel();
        labelC = new JLabel[last];

        frame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame1.setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon("/Users/jethrodivino/Dropbox/Compiler Design/Templates/Templates/Counting/bg.png")); // NOI18N
        frame1.add(jLabel2);

        jLabel2.setBounds(0, 30, 600, 337);
        int c;
        Graphics g = null;

        for (int k = 0; k < last; k++) {
            labelC[k] = new JLabel("");
           try {
            Image bi =ImageIO.read(new File("/Users/jethrodivino/Dropbox/Compiler Design/Templates/Templates/Counting/original.png"));
            labelC[k].setText("");
            labelC[k].setIcon(new ImageIcon(bi.getScaledInstance(100, 100, 100)));
            frame1.add(labelC[k], 1, 0);

              } catch (Exception e) {
                } 
            System.out.println(k);
            if(k>4){
                labelC[k].setBounds(((k-4) * 100)-50, 200, 100, 100);
            }else{
                labelC[k].setBounds(550 + ((k-5) * 100), 100, 100, 100);
            }

        }

    }
}
