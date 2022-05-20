package com.example.motivationquote.responce;

import java.util.List;

public class ImageModel {

       String id,image;

    public ImageModel() {
    }

    public ImageModel(String id, String image) {
        this.id = id;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
