/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteach;

import javax.swing.JFrame;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;

/**
 *
 * @author ivanwchua
 */
public class GUI extends JFrame{
    
    private Button btnExit;
    private Button btnOpen;
    private Button btnRun;
    private Button btnSave;
    private JTextArea txtAreaCode;
    
    private final Compiler compiler;
    
    public GUI() {
        initComponents();
        compiler = new Compiler(txtAreaCode);
    }
    
    private void initComponents() {

        txtAreaCode = new JTextArea();
        btnRun = new Button();
        btnOpen = new Button();
        btnSave = new Button();
        btnExit = new Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("iTeach");

        btnRun.setLabel("Run");
        btnRun.addActionListener(new RunListener());

        btnOpen.setLabel("Open");
        btnOpen.addActionListener(new OpenListener());
        
        btnSave.setLabel("Save");
        btnSave.addActionListener(new SaveListener());

        btnExit.setLabel("Exit");
        btnExit.addActionListener(new ExitListener());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtAreaCode, GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRun, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRun, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(txtAreaCode, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }

    private class RunListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] lines = txtAreaCode.getText().split("\\n");
            for (String line : lines) {
                System.out.println(line);
            }
            try {
                compiler.parse(lines);
            } catch (IOException ex) {
                Logger.getLogger(ITeach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     private class OpenListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
    
     private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    }
     
     private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            dispose();
        }
    }
}
