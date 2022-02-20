package com.java;

import com.java.models.DataLine;
import com.java.services.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String input = "7 \n" +
                "C 1.1 8.15.1 P 15.10.2012 83 \n" +
                "C 1 10.1 P 01.12.2012 65 \n" +
                "C 1.1 5.5.1 P 01.11.2012 117 \n" +
                "D 1.1 8 P 01.01.2012-01.12.2012 \n" +
                "C 3 10.2 N 02.10.2012 100 \n" +
                "D 1 * P 8.10.2012-20.11.2012 \n" +
                "D 3 10 P 01.12.2012 ";

        Service service = new Service();
        Main main = new Main();
        DataLine[] dataLines = service.getDataLines(input);
        main.printAllAverageWaitingTime(dataLines);
    }

    private void printAllAverageWaitingTime(DataLine[] dataLines) {
        int sumOfWaitingTime = 0;
        int count = 0;

        for (int i = 0; i < dataLines.length; i++) {
            if (dataLines[i].getType().equals("D")) {
                sumOfWaitingTime = 0;
                count = 0;
                for (int j = 0; j < i; j++) {
                    if (dataLines[j].getType().equals("C")) {
                        if (isMatchFilters(dataLines[j], dataLines[i])) {
                            sumOfWaitingTime += dataLines[j].getTime();
                            count++;
                        }
                    }
                }
                if (count == 0) {
                    System.out.println("-");
                } else {
                    System.out.println(sumOfWaitingTime / count);
                }
            }
        }
    }

    private boolean isMatchFilters(DataLine target, DataLine filter) {
        if (!isMatchServices(target.getService(), filter.getService())) {
            return false;
        }
        if (!isMatchQuestion(target.getQuestion(), filter.getQuestion())) {
            return false;
        }
        if (!isMatchResponse(target.getResponseType(), filter.getResponseType())) {
            return false;
        }
        if (!isMatchDate(target.getDate(), filter.getDate())) {
            return false;
        }
        return true;
    }

    private boolean isMatchServices(String target, String filter) {
        String[] filterService = filter.split("\\.");
        String[] targetService = target.split("\\.");
        if (targetService.length < filterService.length) {
            return false;
        }
        if (filterService.length == 1) {
            if (!filterService[0].equals(targetService[0])) {
                return false;
            }
        } else if (filterService.length == 2) {
            if (!filterService[0].equals(targetService[0]) || !filterService[1].equals(targetService[1])) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    private boolean isMatchQuestion(String target, String filter) {
        String[] filterQuestion = filter.split("\\.");
        String[] targetQuestion = target.split("\\.");
        if (filter.equals("*")) {
            return true;
        }
        if (targetQuestion.length < filterQuestion.length) {
            return false;
        }
        if (filterQuestion.length == 1) {
            if (!filterQuestion[0].equals(targetQuestion[0])) {
                return false;
            }
        } else if (filterQuestion.length == 2) {
            if (!filterQuestion[0].equals(targetQuestion[0]) || !filterQuestion[1].equals(targetQuestion[1])) {
                return false;
            }
        } else if (filterQuestion.length == 3) {
            if (!filterQuestion[0].equals(targetQuestion[0]) || !filterQuestion[1].equals(targetQuestion[1]) ||
                    !filterQuestion[2].equals(targetQuestion[2])) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    private boolean isMatchResponse(String target, String filter) {
        return target.equals(filter);
    }

    private boolean isMatchDate(String target, String filter) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        String[] filterDate = filter.split("-");
        Date d1;
        Date d2;
        Date d3;
        try {
            if (filterDate.length == 1) {
                d1 = formatter.parse(filterDate[0]);
                d3 = formatter.parse(target);
                return d3.after(d1);
            } else if (filterDate.length == 2) {
                d1 = formatter.parse(filterDate[0]);
                d2 = formatter.parse(filterDate[1]);
                d3 = formatter.parse(target);
                return d3.after(d1) && d3.before(d2);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
