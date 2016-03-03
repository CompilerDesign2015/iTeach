/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteach;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
public class Addition extends javax.swing.JFrame implements KeyListener {

    /**
     * @param args the command line arguments
     */
    static JLabel jLabel2;
    static JLabel jLabel3;
    static int addend1;
    static int addend2;
    //static JLabel[] labelC; 
    static JLabel[] labelC, labelS;
    static BufferedImage[] image;
    AdditionAnim n;
    private Character lastKey;
    JFrame frame1;

    Addition(int num1, int num2) throws IOException {
        addend1 = num1;
        addend2 = num2;
        n = new AdditionAnim(addend1, addend2);
        Initialize();
    }

    Addition() throws IOException {
//       Initialize();  
    }

    public void Initialize() throws IOException {
        frame1 = new JFrame();
        frame1.setSize(1136, 639);
        frame1.addKeyListener(this);
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //frame1.setSize(screenSize.width, screenSize.height);
        frame1.setAlwaysOnTop(true);
        frame1.setResizable(false);
        frame1.setVisible(true);
//        frame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame1.setLayout(null);
        frame1.getContentPane().setBackground(Color.black);
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelC = new JLabel[addend1];
        labelS = new JLabel[addend2];
        // frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //   frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // frame1.setUndecorated(true);

        try {
            Image bi = ImageIO.read(new File("resources/addition/bg.png"));
            jLabel2.setText("");
            jLabel2.setIcon(new ImageIcon(bi.getScaledInstance(1136, 639, 100)));
            frame1.add(jLabel2, 1, 0);

        } catch (Exception e) {
        }

        //  jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\DANIEL KENNETH\\Dropbox\\Compiler Design\\Templates\\Templates\\NEW addition\\bg.png")); // NOI18N
        //  frame1.add(jLabel2);
        jLabel2.setBounds(0, 0, 1136, 639);
        int k = 0;

        jLabel3.setFont(new Font("Comic Sans MS", Font.PLAIN, 60));
        jLabel3.setText(addend1 + "+" + addend2 + "= ?");
        jLabel3.setSize(200, 300);

        frame1.add(jLabel3, 1, 0);

        jLabel3.setBounds(820, -30, 500, 500);

        for (k = 0; k < addend1; k++) {
            labelC[k] = new JLabel("");
            try {
                Image bi = ImageIO.read(new File("resources/addition/original.png"));
                labelC[k].setText("");
                labelC[k].setIcon(new ImageIcon(bi.getScaledInstance(50, 50, 100)));
                frame1.add(labelC[k], 1, 0);

            } catch (Exception e) {
            }
            System.out.println(k);
            if (k > 4) {
                labelC[k].setBounds(((k - 4) * 60) - 10, 220, 50, 50);  //lower
            } else {
                labelC[k].setBounds(350 + ((k - 5) * 60), 180, 50, 50);  //upper
            }

        }

        for (k = 0; k < addend2; k++) {
            labelS[k] = new JLabel("");
            try {
                Image bi = ImageIO.read(new File("resources/addition/original.png"));
                labelS[k].setText("");
                labelS[k].setIcon(new ImageIcon(bi.getScaledInstance(50, 50, 100)));
                frame1.add(labelS[k], 1, 0);

            } catch (Exception e) {
            }
            System.out.println(k);
            if (k > 4) {
                labelS[k].setBounds(((k - 4) * 60) + 370, 230, 50, 50);  //lower
            } else {
                labelS[k].setBounds(720 + ((k - 5) * 60), 180, 50, 50);  //upper
            }

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (lastKey == null || lastKey != e.getKeyChar()) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                System.out.println("langya");
                lastKey = e.getKeyChar();
                Thread t = new Thread(n);
                t.start();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            frame1.dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
