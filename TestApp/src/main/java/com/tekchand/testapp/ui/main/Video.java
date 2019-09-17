package com.tekchand.testapp.ui.main;

public class Video {
    private static int count = 0;
    private int id;
    private String published;
    private String title;
    private String description;
    private String url;

    private int autoId(){
        count = count +1;
        return count;
    }

    public Video(String published, String title, String description, String url) {
        this.id = autoId();
        this.published = published;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public String getPublished() {
        return published;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
