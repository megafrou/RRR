package com.example.rrr;


import java.io.Serializable;

/**
 * Created by Christine Megarchioti on 20/1/2018.
 */

public class Items  {
    private String  filename, path, date, category, description, condition, user;

    public Items( String filename, String path, String date, String category, String description, String condition, String user ){
        this.filename = filename;
        this.path = path;
        this.date = date;
        this.category = category;
        this.description = description;
        this.condition = condition;
        this.user = user;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getImgUrl(){
         String url;
         url = path + filename;
         return url;
    }
}
