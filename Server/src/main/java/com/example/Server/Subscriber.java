package com.example.Server;

public class Subscriber {
    private int id;

    private String name;

    private String email;

    private String price;

    public Subscriber() {}

    public Subscriber(int id, String name, String email, String price) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
