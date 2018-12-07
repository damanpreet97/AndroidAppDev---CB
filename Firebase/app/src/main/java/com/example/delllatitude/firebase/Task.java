package com.example.delllatitude.firebase;

public class Task {

    String title, subtile;
    String id;
    boolean status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtile() {
        return subtile;
    }

    public void setSubtile(String subtile) {
        this.subtile = subtile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Task() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Task(String title, String subtile, String id, boolean status) {
        this.title = title;
        this.subtile = subtile;
        this.id = id;
        this.status = status;

    }
}
