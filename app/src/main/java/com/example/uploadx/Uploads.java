package com.example.uploadx;

public class Uploads {
    public String name;
    public String url;

    public Uploads(String xi, String yi) {
        this.name = xi;
        this.url = yi;
    }

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)

    public Uploads() {
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
