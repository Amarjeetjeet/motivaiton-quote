package com.example.motivationquote.responce;



public class Model {

String id,quote_data,quote_name,time;

    public Model() {
    }

    public Model(String id, String quote_data, String quote_name, String time) {
        this.id = id;
        this.quote_data = quote_data;
        this.quote_name = quote_name;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuote_data() {
        return quote_data;
    }

    public void setQuote_data(String quote_data) {
        this.quote_data = quote_data;
    }

    public String getQuote_name() {
        return quote_name;
    }

    public void setQuote_name(String quote_name) {
        this.quote_name = quote_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}