package com.cultivation.javaBasicExtended.wordProcessor;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class TextProcessor {
    private final TextProcessorSettings settings;

    TextProcessor(int width) {
        this(width, null);
    }

    TextProcessor(int width, char[] whitespaces) {
        if (width < 10 || width > 80) {
            throw new IllegalArgumentException("Width out of range.");
        }

        settings = new TextProcessorSettings(width, getWhitespaces(whitespaces));
    }

    private char[] getWhitespaces(char[] whitespaces) {
        return whitespaces == null ?
                new char[]{' '} :
                whitespaces;
    }

    String process(String text) {
        // TODO: Please implement the method to pass all the test
        // <--start
        /*1.将单词与空格分离出来
        2.记录行号及空格的个数*/

        if (text == null)
            throw new IllegalArgumentException("ERROR: Invalid character detected!");

        int totalLine = getLine(text);

        StringBuilder res = new StringBuilder();
        int count = 0;
        int currentLine = 1;

        if (!text.contains(" ")) {
            processSingleWord(text, currentLine, totalLine, count, res);
        } else {
            String[] strings = text.split(" ");
            for (String str : strings) {
                int occupiedLine = getLine(str);
                if (!str.contains(" ")) {
                    processSingleWord(str, currentLine, occupiedLine, count, res);
                    count += str.length();
                    if (count >= settings.getWidth()) {
                        currentLine++;
                        count = count % settings.getWidth();
                    }
                    processSpace(currentLine, res);
                    count++;
                }
            }
        }
        return res.toString();
        // --end-->
    }

    private int getLine(String text) {
        int lineHelper = text.length() / settings.getWidth();
        return text.length() % settings.getWidth() == 0 ? lineHelper : lineHelper + 1;
    }

    private void processSingleWord(String str, int currentLine, int occupiedLine, int count, StringBuilder res) {
        if (count < settings.getWidth()) {
            appendStr(str, currentLine, occupiedLine, res);
        } else {
            appendStr(str, currentLine, occupiedLine, res);
        }
    }

    private void processSpace(int occupiedLine, StringBuilder res) {
        res.append(" (").append(occupiedLine).append(");");
    }


    private void appendStr(String str, int currentLine, int occupiedLine, StringBuilder res) {
        res.append(str).append("(");
        for (int i = currentLine; i < occupiedLine; i++) {
            res.append(i).append(",");
        }
        res.append(occupiedLine).append(");");
    }
}

