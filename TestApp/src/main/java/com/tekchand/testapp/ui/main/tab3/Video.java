package com.tekchand.testapp.ui.main.tab3;

import androidx.annotation.NonNull;


/**
 * @author Tek Chand
 * Video class represents the information of a youtube video such as Published date, Title, URl, Description
 */
public class Video {
    private static int count = 0;
    private int id;
    private String published;
    private String title;
    private String description;
    private String url;

    /**
     * increase Id by one when new video is added.
     * @return id of the video
     */
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

    /**
     * get the id
     * @return a unique Id of the video
     */
    public int getId() {
        return id;
    }

    /**
     * get the title of the video
     * @return title of the video
     */
    public String getTitle() {
        return title;
    }

    /**
     * get the URL of the video
     * @return URL of the video
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the url of the video
     * @param url URL of the video
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * get the URL of the video
     * @return description of the video
     */
    public String getDescription() {
        return description;
    }

    /**
     * get the published date of the video
     * @return published date of the video
     */
    public String getPublished() {
        return published;
    }

    /**
     * set the id of the video
     * @param id Unique id of the video
     */
    public void setId(@NonNull final int id) {
        this.id = id;
    }

    /**
     * set the published date of the video
     * @param published date of published of the video
     */
    public void setPublished(@NonNull final String published) {
        this.published = published;
    }

    /**
     * set the title of the video
     * @param title title of the video
     */
    public void setTitle(@NonNull final String title) {
        this.title = title;
    }

    /**
     * set the description about the video
     * @param description description about the video
     */
    public void setDescription(@NonNull final String description) {
        this.description = description;
    }
}
