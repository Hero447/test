package com.java.services;

import com.java.models.DataLine;

public class Service {

    public DataLine[] getDataLines(String input) {
        String[] lines = input.lines().skip(1).toArray(String[]::new);
        DataLine[] dataLines = new DataLine[lines.length];
        for (int i = 0; i < lines.length; i++) {
            dataLines[i] = parseToDataLine(lines[i]);
        }
        return dataLines;
    }

    private DataLine parseToDataLine(String line) {
        String[] elementsInLine = line.split(" ");
        DataLine dataLine = null;
        if (elementsInLine[0].equals("C")) {
            dataLine = new DataLine(elementsInLine[0], elementsInLine[1], elementsInLine[2], elementsInLine[3],
                    elementsInLine[4], Integer.parseInt(elementsInLine[5]));
        } else if (elementsInLine[0].equals("D")) {
            dataLine = new DataLine(elementsInLine[0], elementsInLine[1], elementsInLine[2], elementsInLine[3],
                    elementsInLine[4], -1);
        }
        return dataLine;
    }
}
