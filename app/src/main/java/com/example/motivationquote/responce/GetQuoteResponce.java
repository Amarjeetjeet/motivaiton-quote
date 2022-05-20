package com.example.motivationquote.responce;


import java.util.List;

public class GetQuoteResponce {
    String status,message;
    List<Model> data;

    public GetQuoteResponce(String status, String message, List<Model> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Model> getData() {
        return data;
    }

    public void setData(List<Model> data) {
        this.data = data;
    }
}
