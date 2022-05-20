package com.example.motivationquote.responce;

import java.util.List;

public class GetImageResponce {

    String status,message;
    List<ImageModel> data;

    public GetImageResponce(String status, String message, List<ImageModel> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public GetImageResponce() {
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

    public List<ImageModel> getData() {
        return data;
    }

    public void setData(List<ImageModel> data) {
        this.data = data;
    }
}
