package com.example.client;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Suggestion {
    public StringProperty name;
    public StringProperty email;
    public StringProperty price;

    public Suggestion(String name, String email, String price) {
        this.name = new SimpleStringProperty(this, "city", name);
        this.email = new SimpleStringProperty(this, "email", email);
        this.price = new SimpleStringProperty(this, "price", price);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPrice() {
        return price.get();
    }

    public StringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }
}
