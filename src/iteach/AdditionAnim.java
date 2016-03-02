/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteach;

import static iteach.Addition.addend1;
import static iteach.Addition.labelC;
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
public class AdditionAnim implements Runnable {

    Addition N;
    public int addend1, addend2, sum;

    AdditionAnim(int num1, int num2) throws IOException {
        N = new Addition();
        addend1 = num1;
        addend2 = num2;
        sum = num1 + num2;
    }

    @Override
    public void run() {
        try {
            int i, j, k;
            for (i = 0; i < addend1; i++) {
                for (j = 0, k = 0; j < 80 && k < 80; k++, j++) {
                    System.out.println("hehe");
                    N.labelC[i].setLocation((960 * k / 100) + (30 * i), 270 + (j));
                    Thread.sleep(1);
                    //  N.labelC[i].setIcon(new javax.swing.ImageIcon("C:\\Users\\DANIEL KENNETH\\Dropbox\\Compiler Design\\Templates\\Templates\\NEW addition\\AppleGlow.png"));
                    try {
                        Image bi = ImageIO.read(new File("resources/addition/AppleGlow.png"));
                        N.labelC[i].setText("");
                        N.labelC[i].setIcon(new ImageIcon(bi.getScaledInstance(50, 50, 100)));
                    } catch (Exception e) {
                    }
                }
            }

            for (i = 0; i < addend2; i++) {
                for (j = 0, k = 0; j < 80 && k < 80; k++, j++) {
                    System.out.println("hehe");
                    N.labelS[i].setLocation(420 + (425 * k / 100) + (30 * i), 230 + (j));
                    Thread.sleep(1);
                    try {
                        Image bi = ImageIO.read(new File("resources/addition/AppleGlow.png"));
                        N.labelS[i].setText("");
                        N.labelS[i].setIcon(new ImageIcon(bi.getScaledInstance(50, 50, 100)));
                    } catch (Exception e) {
                    }
                }
            }

            N.jLabel3.setText(addend1 + "+" + addend2 + "= " + sum);

        } catch (InterruptedException ex) {
            Logger.getLogger(SubtractionAnim.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
