package com.example.rrr;


/**
 * Created by user on 20/1/2018.
 */

public class Items {
    private String  filename, path, date, category, description, condition;

    public Items( String filename, String path, String date, String category, String description, String condition ){
        this.setFilename(filename);
        this.setPath(path);
        this.setDate(date);
        this.setCategory(category);
        this.setDescription(description);
        this.setCondition(condition);
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

    public String getImgUrl(){
         String url;
         url = path + filename;
         return url;
    }
}
