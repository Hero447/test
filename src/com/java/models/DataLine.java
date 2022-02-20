package com.java.models;

public class DataLine {

    private String type;
    private String service;
    private String question;
    private String responseType;
    private String date;
    private int time;

    public DataLine(String type, String service, String question, String responseType, String date, int time) {
        this.type = type;
        this.service = service;
        this.question = question;
        this.responseType = responseType;
        this.date = date;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public String getService() {
        return service;
    }

    public String getQuestion() {
        return question;
    }

    public String getResponseType() {
        return responseType;
    }

    public String getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "DataLine{" +
                "type='" + type + '\'' +
                ", service='" + service + '\'' +
                ", question='" + question + '\'' +
                ", responseType='" + responseType + '\'' +
                ", date='" + date + '\'' +
                ", time=" + time +
                '}';
    }
}

