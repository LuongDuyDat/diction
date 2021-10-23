package com.example.diction.Entry;


public class word {
    private int id;
    private String word;
    private String html;
    private String description;
    private String pronounce;
    private String exception;

    public word() {
    }

    public word(String word, String description, String exception) {
        this.word = word;
        this.description = description;
        this.exception = exception;
    }

    public word(String exception) {
        this.exception = exception;
    }

    public word(int id, String word, String html, String description, String pronounce) {
        this.id = id;
        this.word = word;
        this.description = description;
        this.pronounce = pronounce;
        exception = "false";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
