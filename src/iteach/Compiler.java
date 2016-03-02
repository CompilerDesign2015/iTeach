/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteach;

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

/**
 *
 * @author ivanwesleychua
 */
public class Compiler {

    private final JTextArea txtAreaCode;

    public Compiler(JTextArea tAreaCode) {
        this.txtAreaCode = tAreaCode;
    }

    public void highlightLine(int i) {
        try {
            txtAreaCode.getHighlighter().addHighlight(txtAreaCode.getLineStartOffset(i), txtAreaCode.getLineEndOffset(i), new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));
        } catch (BadLocationException ex) {
            Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void parse(String[] line) throws IOException {
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

        String delims = "[, ()]+";

        String[] add = null;
        String[] count = null;
        String[] subtract = null;

        txtAreaCode.getHighlighter().removeAllHighlights();

        for (i = 0; i < line.length;) {
            if (patternBackground.matcher(line[i]).find()) {
                i++;
                if (patternStart.matcher(line[i]).find()) {
                    i++;
                    if (patternCount.matcher(line[i]).find()) {
                        count = line[i].split(delims);
                        i++;
                    } else if (patternContainer.matcher(line[i]).find()) {
                        i++;
                        if (patternOpen.matcher(line[i]).find()) {
                            i++;
                            if (patternAdd.matcher(line[i]).find()) {
                                add = line[i].split(delims);
                                i++;
                            } else if (patternSubtract.matcher(line[i]).find()) {
                                subtract = line[i].split(delims);
                                if (Integer.parseInt(subtract[1]) < Integer.parseInt(subtract[2])) {
                                    highlightLine(i);
                                    check = false;
                                    break;
                                } else {
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
                    i++;
                    break;
                }
            } else {
                highlightLine(i);
                i++;
                check = false;
            }
        }
        System.out.println(check);
        if (check) {
            if (count != null) {
                new Counting(Integer.parseInt(count[1]));
            }
            if (add != null) {
                new Addition(Integer.parseInt(add[1]), Integer.parseInt(add[2]));
            }
            if (subtract != null) {
                System.out.println("Checking validity of subtraction inputs...");
                if (Integer.parseInt(subtract[1]) >= Integer.parseInt(subtract[2])) {
                    new Subtraction(Integer.parseInt(subtract[1]), Integer.parseInt(subtract[2]));
                }
            }
        }
    }
}
