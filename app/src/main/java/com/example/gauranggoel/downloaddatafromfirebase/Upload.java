package com.example.gauranggoel.downloaddatafromfirebase;

/**
 * Created by gaurang goel on 25-07-2018.
 */

public class Upload {
    String name,url;

    public Upload(){

    }
    public Upload(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
