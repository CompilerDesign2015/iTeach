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
            checkSyntaxWhole(code.getText());
            //parse(lines);
        }
    }

    public void parse(String[] line) {
        Pattern regexBackground = Pattern.compile("(background)(\\()((?:[A-Z][A-Z0-9_]*))(\\))(\\s+)(START)(.*?)(END)");
        Pattern regexContainerCount = Pattern.compile("(count|container)");
        Pattern regexContainer = Pattern.compile("(container)(\\()((?:[A-Z][A-Z0-9_]*))(\\))(\\s+)(OPEN)(.*?)(CLOSE)");
        Pattern regexCountAddSubtract = Pattern.compile("(count|add|subtract)");
        Pattern regexCountAdd = Pattern.compile("(count|add)(\\()([0-5]+)(,)([0-5]+)(,)(\\w+)(\\))");
        Pattern regexSubtract = Pattern.compile("(subtract)(\\()([0-5]+)(,)([0-5]+)(,)(\\w+)(,)(\\w+)(\\))");

        Pattern testAll = Pattern.compile("(background)(\\()((?:[A-Z][A-Z0-9_]*))(\\))"
                + "|(START)"
                + "|(END)"
                + "|(container)(\\()((?:[A-Z][A-Z0-9_]*))(\\))"
                + "|(OPEN)"
                + "|(CLOSE)"
                + "|(count|add)(\\()([0-5]+)(,)([0-5]+)(,)(\\w+)(\\))"
                + "|(subtract)(\\()([0-5]+)(,)([0-5]+)(,)(\\w+)(,)(\\w+)(\\))");
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

    public static void checkSyntaxWhole(String code) {
        // TODO code application logic here

        String regexBackground = "(background)(\\()((?:[A-Z][A-Z0-9_]*))(\\))(\\s+)(START)(.*?)(END)";
        String regexContainerCount = "(count|container)";
        String regexContainer = "(container)(\\()((?:[A-Z][A-Z0-9_]*))(\\))(\\s+)(OPEN)(.*?)(CLOSE)";
        String regexCountAddSubtract = "(count|add|subtract)";
        String regexCountAdd = "(count|add)(\\()([0-9]+)(,)([0-9]+)(,)(\\w+)(\\))";
        String regexSubtract = "(subtract)(\\()([0-9]+)(,)([0-9]+)(,)(\\w+)(,)(\\w+)(\\))";

        // TEST CODES HERE : USE ONLY ONE "code" VARIABLE AT A TIME    
        //Add Slide Code
        //        String code = "background(FIELD) START container(TREE) OPEN add(1,2,APPLE) CLOSE END";
        //Count Slide Code
        //String code = "background(FIELD) START count(1,2,APPLE) CLOSE END";

        //Subtract Slide Code
        //        String code = "background(FIELD) START container(TREE) OPEN subtract(1,2,APPLE,BASKET) CLOSE END";
        Pattern pBackground = Pattern.compile(regexBackground);
        Matcher mBackground = pBackground.matcher(code);
        int n = 1;
        String containerCountString = null;
        String addSubtractString = null;
        String countString = null;

        if (mBackground.find()) {
            while (n < 9) {
                System.out.println(mBackground.group(n++));
                if (n == 7) {
                    containerCountString = mBackground.group(n);
                }
            }
            Pattern pContainerCount = Pattern.compile(regexContainerCount);
            Matcher mContainerCount = pContainerCount.matcher(containerCountString);
            if (mContainerCount.find()) {
                System.out.println(mContainerCount.group(1));
                if (mContainerCount.group(1).equals("count")) {
                    Pattern pCount = Pattern.compile(regexCountAdd);
                    Matcher mCount = pCount.matcher(containerCountString);
                    if (mCount.find()) {
                        n = 1;
                        while (n < 9) {
                            System.out.println(mCount.group(n++));
                        }
                    }
                } else if (mContainerCount.group(1).equals("container")) {
                    Pattern pContainer = Pattern.compile(regexContainer);
                    Matcher mContainer = pContainer.matcher(containerCountString);
                    if (mContainer.find()) {
                        n = 1;
                        while (n < 9) {
                            System.out.println(mContainer.group(n++));
                            if (n == 7) {
                                addSubtractString = mContainer.group(n);
                            }
                        }
                    }
                    System.out.println(addSubtractString);
                    Pattern pAddSubtract = Pattern.compile(regexCountAddSubtract);
                    Matcher mAddSubtract = pAddSubtract.matcher(addSubtractString);
                    if (mAddSubtract.find()) {
                        System.out.println(mAddSubtract.group(1));
                        if (mAddSubtract.group(1).equals("add")) {
                            System.out.println("ADD SIYA");
                            Pattern pAdd = Pattern.compile(regexCountAdd);
                            Matcher mAdd = pAdd.matcher(addSubtractString);
                            if (mAdd.find()) {
                                n = 1;
                                while (n < 9) {
                                    System.out.println(mAdd.group(n++));
                                }
                            }

                        } else if (mAddSubtract.group(1).equals("subtract")) {
                            System.out.println("SUBTRACT SIYA");
                            Pattern pSubtract = Pattern.compile(regexSubtract);
                            Matcher mSubtract = pSubtract.matcher(addSubtractString);
                            if (mSubtract.find()) {
                                n = 1;
                                while (n < 11) {
                                    System.out.println(mSubtract.group(n++));
                                }
                            }
                        }
                    }

                }
            }

        }

    }
}
