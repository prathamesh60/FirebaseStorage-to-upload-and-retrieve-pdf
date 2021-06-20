package com.example.uploadx;

public class Upload {
    public String name;
    public String url;

    public Upload(String xi,String yi,String ti) {
        this.name = xi;
        this.url=yi;

    }

    public Upload() {
    }

    public String getName() {
        return name;
    }

    public String getUrl(){return url;};


}