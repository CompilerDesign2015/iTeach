/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteach;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

/**
 *
 * @author DANIEL KENNETH
 */
public class ITeach extends JPanel {

    /**
     * @param args the command line arguments
     */
    String[] lines;
    private ArrayList<String> stack;
    JTextArea code;
    JButton run;

    public ITeach() {
        initializeWindow();

    }

    public void initializeWindow() {
        run = new JButton();
        run.setText("Run");
        run.addActionListener(new runListener());
        code = new JTextArea(25, 50);

        add(code);
        add(run);
    }

    private void push(int i) {
        stack.add(lines[i]);
    }

    private class runListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            lines = code.getText().split("\\n");
            for (int i = 0; i < lines.length; i++) {
                System.out.println(lines[i]);

            }
            stack = new ArrayList<String>(lines.length);
            parse(lines);
        }
    }

    public void parse(String[] line) {
        Pattern regexBackground = Pattern.compile("(background)(\\()((?:[A-Z][A-Z0-9_]*))(\\))(\\s+)(START)(.*?)(END)");
        Pattern regexContainerCount = Pattern.compile("(count|container)");
        Pattern regexContainer = Pattern.compile("(container)(\\()((?:[A-Z][A-Z0-9_]*))(\\))(\\s+)(OPEN)(.*?)(CLOSE)");
        Pattern regexCountAddSubtract = Pattern.compile("(count|add|subtract)");
        Pattern regexCountAdd = Pattern.compile("(count|add)(\\()([0-5]+)(,)([0-5]+)(,)(\\w+)(\\))");
        Pattern regexSubtract = Pattern.compile("(subtract)(\\()([0-5]+)(,)([0-5]+)(,)(\\w+)(,)(\\w+)(\\))");
        
        Pattern patternLine1 = Pattern.compile("(^background)(\\()((?:[A-Z][A-Z0-9_]*))(\\)$)");
        Pattern patternLine2 = Pattern.compile("(^START$)");
        Pattern patternLine3 = Pattern.compile("(^container)(\\()((?:[A-Z][A-Z0-9_]*))(\\)$)");
        Pattern patternLine4 = Pattern.compile("(^OPEN$)");
        Pattern patternCount = Pattern.compile("(^count)(\\()([0-5]+)(, )([0-9]+)(, )(\\w+)(\\)$)");
        Pattern patternAdd = Pattern.compile("(^add)(\\()([0-5]+)(, )([0-5]+)(, )(\\w+)(\\)$)");
        Pattern patternSubtract = Pattern.compile("(^subtract)(\\()([0-5]+)(, )([0-5]+)(, )(\\w+)(, )(\\w+)(\\)$)");
        Pattern patternLine7 = Pattern.compile("(^CLOSE$)");
        Pattern patternLine8 = Pattern.compile("(^END$)");
        
        Pattern testAll = Pattern.compile("(^background)(\\()((?:[A-Z][A-Z0-9_]*))(\\)$)"
                + "|(^START$)"
                + "|(^END$)"
                + "|(^container)(\\()((?:[A-Z][A-Z0-9_]*))(\\)$)"
                + "|(^OPEN$)"
                + "|(^CLOSE$)"
                + "|(^count|add)(\\()([0-5]+)(, )([0-5]+)(, )(\\w+)(\\)$)"
                + "|(^subtract)(\\()([0-5]+)(, )([0-5]+)(, )(\\w+)(, )(\\w+)(\\)$)");
        
        code.getHighlighter().removeAllHighlights();
        
        Matcher m;
        for (int i = 0; i < line.length; i++) {
            m = testAll.matcher(line[i]);
            if (!m.find()) {
                try {
                    code.getHighlighter().addHighlight(code.getLineStartOffset(i), code.getLineEndOffset(i), new DefaultHighlighter.DefaultHighlightPainter(Color.RED));
                } catch (BadLocationException ex) {
                    Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        for (int i = 0; i < line.length; i++) {
            m = regex
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        JFrame Editor = new JFrame("iTeach");
        Editor.setBounds(50, 100, 800, 500);
        Editor.getContentPane().add(new ITeach());
        Editor.setVisible(true);
        Editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}