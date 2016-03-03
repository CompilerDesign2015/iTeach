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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollPane;

/**
 *
 * @author ivanwchua
 */
public class GUI extends JFrame {

    private Button btnExit;
    private Button btnOpen;
    private Button btnRun;
    private Button btnSave;
    private JScrollPane scrollPane;
    private JTextArea txtAreaCode;

    private final Compiler compiler;

    private File file = null;

    public GUI() {
        initComponents();
        compiler = new Compiler(txtAreaCode);
    }

    private void initComponents() {

        scrollPane = new JScrollPane();
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

        scrollPane.setViewportView(txtAreaCode);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
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
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE)
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
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            chooser.setFileFilter(filter);
            int retVal = chooser.showOpenDialog(btnOpen);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                BufferedReader in = null;
                try {
                    file = chooser.getSelectedFile();
                    in = new BufferedReader(new FileReader(file));
                    String line = in.readLine();
                    while (line != null) {
                        txtAreaCode.append(line + "\n");
                        line = in.readLine();
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        in.close();
                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    private class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (file == null) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
                chooser.setFileFilter(filter);
                chooser.setApproveButtonText("Save");
                int retVal = chooser.showOpenDialog(btnSave);
                if (retVal == JFileChooser.APPROVE_OPTION) {
                    file = new File(chooser.getSelectedFile() + ".txt");
                }
            }

            BufferedWriter out;
            try {
                out = new BufferedWriter(new FileWriter(file));
                txtAreaCode.write(out);
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
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
