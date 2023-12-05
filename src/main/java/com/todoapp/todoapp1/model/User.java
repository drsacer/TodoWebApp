package com.todoapp.todoapp1.model;

public class User {

    private int id;
    static int idCounter = 0; // zašto static? inače ne radi

    private int type = 0; // 0 - employee, 1 - supervisor

    private String fname;
    private String lname;
    private String oib;
    private String email;
    private String password;

    public User(String fname, String lname, String oib, String email, String password) {
        this.fname = fname;
        this.lname = lname;
        this.oib = oib;
        this.email = email;
        this.password = password;

        id=idCounter++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCounter() {
        return idCounter;
    }

    public void setIdCounter(int idCounter) {
        this.idCounter = idCounter;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
