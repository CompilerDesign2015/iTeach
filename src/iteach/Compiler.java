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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

/**
 *
 * @author ivanwesleychua
 */
public class Compiler extends JPanel {

    /**
     * @param args the command line arguments
     */
    String[] lines;
    private ArrayList<String> stack;
    JTextArea code;
    JButton run;

    public Compiler() {
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
        int endCnt = 0;
        int closeCnt = 0;
        for (int i = 0; i < line.length; i++) {
            m = testAll.matcher(line[i]);
            if (!m.find()) {
                try {
                    code.getHighlighter().addHighlight(code.getLineStartOffset(i), code.getLineEndOffset(i), new DefaultHighlighter.DefaultHighlightPainter(Color.RED));
                } catch (BadLocationException ex) {
                    Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            m = regexBackground.matcher(line[i]);
            if (m.find()) {
                endCnt++;
            }
            m = regexContainerCount.matcher(line[i]);
            if (m.find()) {

                closeCnt++;
            }
            m = regexContainer.matcher(line[i]);
            if (m.find()) {

            }
            m = regexCountAddSubtract.matcher(line[i]);
            if (m.find()) {

            }
            m = regexCountAdd.matcher(line[i]);
            if (m.find()) {

            }
            m = regexSubtract.matcher(line[i]);
            if (m.find()) {

            }

            if (line[i].matches("END")) {
                System.out.println(line.length);
                endCnt--;
            }

            if (line[i].matches("CLOSE")) {
                System.out.println(line.length);
                closeCnt--;
            }

            if (i == line.length - 1 && endCnt != 0 && closeCnt != 0) {
                JOptionPane.showMessageDialog(null, "Take a look at your closing tags!");
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        JFrame Editor = new JFrame("iTeach");
        Editor.setBounds(50, 100, 800, 500);
        Editor.getContentPane().add(new Compiler());
        Editor.setVisible(true);
        Editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}