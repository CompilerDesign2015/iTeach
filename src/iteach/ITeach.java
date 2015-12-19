/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteach;

import java.util.regex.*;

/**
 *
 * @author DANIEL KENNETH
 */
public class ITeach {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
              String code = "background(FIELD) START count(1,2,APPLE) CLOSE END";
        
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
