/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteach;

import static iteach.Subtraction.labels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
public class Counting extends javax.swing.JFrame implements KeyListener{
    private static Object image1;

    /**
     * @param args the command line arguments
     */
    JLabel jLabel2;
    static int last;
    //static JLabel[] labelC; 
    static JLabel[] labelC;
    static BufferedImage[] image;
//    Thread t;
    CountingAnim n;

    Counting(int num) throws IOException{
        last = num;
        n = new CountingAnim(last);
//        Thread t = new Thread(n);
        Initialize();
    }
    
    Counting() throws IOException{
       Initialize();  
    }

    public void Initialize() throws IOException {
        
        JFrame frame1 = new JFrame();
        //frame1.setVisible(true);
        frame1.addKeyListener(this);
        frame1.setSize( 1136, 768);
       // Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //frame1.setSize(screenSize.width, screenSize.height);
        frame1.setAlwaysOnTop(true);
        frame1.setResizable(false);       
        frame1.setVisible(true);
//        frame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame1.setLayout(null);
        frame1.getContentPane().setBackground(Color.black);
        JLabel jLabel2 = new javax.swing.JLabel();
        labelC = new JLabel[last];

        frame1.setLayout(null);

        //jLabel2.setIcon(new javax.swing.ImageIcon("resources/counting/bg.png")); // NOI18N
        //frame1.add(jLabel2);
        
         try {
            Image bi =ImageIO.read(new File("resources/counting/bg.png"));
            jLabel2.setText("");
            jLabel2.setIcon(new ImageIcon(bi.getScaledInstance( 1136, 768, 100)));
            frame1.add(jLabel2, 1, 0);

              } catch (Exception e) {
                } 

        //jLabel2.setBounds(0, 30, 600, 337);
         jLabel2.setBounds(0, 0,  1136, 768);
        int c;
        Graphics g = null;

        for (int k = 0; k < last; k++) {
            labelC[k] = new JLabel("");
           try {
            Image bi =ImageIO.read(new File("resources/counting/original.png"));
            labelC[k].setText("");
            labelC[k].setIcon(new ImageIcon(bi.getScaledInstance(250, 250, 100)));
            frame1.add(labelC[k], 1, 0);

              } catch (Exception e) {
                } 
            System.out.println(k);
            if(k>4){
                labelC[k].setBounds(((k-4) * 200)-190, 300, 250, 250);
            }else{
                labelC[k].setBounds(1000 + ((k-5) * 200), 100, 250, 250);
            }

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            System.out.println("langya");
            Thread t = new Thread(n);
            t.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
