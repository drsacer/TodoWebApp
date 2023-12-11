package com.todoapp.todoapp1.model;

import java.util.Date;

public class Todo {

    private String title;
    private String note;
    private Date date;

    private User user;

    public Todo(String title, String note) {
        this.title = title;
        this.note = note;
    }

    public Todo(String title, String note, Date date) {
        this.title = title;
        this.note = note;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
