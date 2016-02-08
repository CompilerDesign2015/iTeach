/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteach;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
        frame1.setVisible(true);
        frame1.setSize(700, 500);
        JLabel jLabel2 = new javax.swing.JLabel();
        labels = new JLabel[num];

        frame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame1.setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon("/Users/jethrodivino/Dropbox/Compiler Design/Templates/Templates/NEW subtraction/sssss.png")); // NOI18N
        frame1.add(jLabel2);

        jLabel2.setBounds(0, 30, 600, 337);
        int c;

        for (int k = 0; k < num; k++) {
            labels[k] = new JLabel("");
            labels[k].setIcon(new javax.swing.ImageIcon("/Users/jethrodivino/Dropbox/Compiler Design/Templates/Templates/NEW subtraction/apol.png"));
            frame1.add(labels[k], 1, 0);
            System.out.println(k);
            if(k>4){
                labels[k].setBounds(180 + ((k-4) * 50), 180, 50, 50);
            }else{
                labels[k].setBounds(180 + (k * 50), 140, 50, 50);
            }

        }

    }
}


