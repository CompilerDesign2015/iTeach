/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteach;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

/**
 *
 * @author iTeach
 */
public class ITeach extends JPanel {

    /**
     * @param args the command line arguments
     */
    String[] lines;
    private ArrayList<String> stack;
    JTextArea code;
    JButton run;
    String delims = "[, ()]+";
    String[] add;
    String[] count;
    String[] subtract;

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
            try {
                parse(lines);
            } catch (IOException ex) {
                Logger.getLogger(ITeach.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void parse(String[] line) throws IOException {
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
        checkSequence(line);
        //Matcher m;
        //for (int i = 0; i < line.length; i++) {
        //    m = testAll.matcher(line[i]);
        //    if (!m.find()) {
        //        highlightLine(i);
        //    }
        //}
    }

    public void highlightLine(int i) {
        try {
            code.getHighlighter().addHighlight(code.getLineStartOffset(i), code.getLineEndOffset(i), new DefaultHighlighter.DefaultHighlightPainter(Color.RED));
        } catch (BadLocationException ex) {
            Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void checkSequence(String[] line) throws IOException {
        Pattern patternBackground = Pattern.compile("(^background)(\\()((?:[A-Z][A-Z0-9_]*))(\\)$)");
        Pattern patternStart = Pattern.compile("(^START$)");
        Pattern patternContainer = Pattern.compile("(^container)(\\()((?:[A-Z][A-Z0-9_]*))(\\)$)");
        Pattern patternOpen = Pattern.compile("(^OPEN$)");
        Pattern patternCount = Pattern.compile("(^count)(\\()([0-9]+)(, )(\\w+)(\\)$)");
        Pattern patternAdd = Pattern.compile("(^add)(\\()([0-5]+)(, )([0-5]+)(, )(\\w+)(, )(\\w+)(\\)$)");
        Pattern patternSubtract = Pattern.compile("(^subtract)(\\()([0-5]+)(, )([0-5]+)(, )(\\w+)(, )(\\w+)(\\)$)");
        Pattern patternClose = Pattern.compile("(^CLOSE$)");
        Pattern patternEnd = Pattern.compile("(^END$)");
        int i;
        boolean check = true;
        for (i = 0; i < line.length;) {
            if (patternBackground.matcher(line[i]).find()) {
                i++;
                if (patternStart.matcher(line[i]).find()) {
                    i++;
                    if (patternCount.matcher(line[i]).find()) {
                        count = line[i].split(delims);
//                        for(int j = 0; j < method.length; j++)
//                        System.out.println(method[j]);
//                        new Counting(Integer.parseInt(method[1]));
                        i++;
                    } else if (patternContainer.matcher(line[i]).find()) {
                        i++;
                        if (patternOpen.matcher(line[i]).find()) {
                            i++;
                            if (patternAdd.matcher(line[i]).find()) {
                                add = line[i].split(delims);
//                                new Addition(Integer.parseInt(method[1]), Integer.parseInt(method[2]));
                                i++;
                            } else if (patternSubtract.matcher(line[i]).find()) {
                                subtract = line[i].split(delims);
                                if(Integer.parseInt(subtract[1])<Integer.parseInt(subtract[2])){
                                    highlightLine(i);
                                    check = false;
                                    break;
                                } else {
//                                    new Subtraction(Integer.parseInt(method[1]), Integer.parseInt(method[2]));
                                }
                                i++;
                            } else {
                                highlightLine(i);
                                i++;
                                check = false;
                                break;
                            }
                        } else {
                            highlightLine(i);
                            i++;
                            check = false;
                            break;
                        }
                        if (patternClose.matcher(line[i]).find()) {
                            i++;
                        } else {
                            highlightLine(i);
                            i++;
                            check = false;
                            break;
                        }
                    } else {
                        highlightLine(i);
                        i++;
                        check = false;
                        break;
                    }
                    if (patternEnd.matcher(line[i]).find()) {
                        i++;
                    } else {
                        highlightLine(i);
                        i++;
                        check = false;
                        break;
                    }
                } else {
                    highlightLine(i);
                    check = false;
                    i++;break;
                }
            } else {
                highlightLine(i);
                i++;
                check = false;
            }
        }
//        System.out.println(i);
        System.out.println(check);
        if(check){
            if (count!=null){
                new Counting(Integer.parseInt(count[1]));
            }
            if(add!=null){
                new Addition(Integer.parseInt(add[1]), Integer.parseInt(add[2]));
            }
            if(subtract!=null){
                System.out.println("hheeere");
                if(Integer.parseInt(subtract[1])>=Integer.parseInt(subtract[2])){
                    new Subtraction(Integer.parseInt(subtract[1]), Integer.parseInt(subtract[2]));
                }
            }
            
        }
    }

    public static void main(String[] args) {
        JFrame Editor = new JFrame("iTeach");
        Editor.setBounds(50, 100, 800, 500);
        Editor.getContentPane().add(new ITeach());
        Editor.setVisible(true);
        Editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
