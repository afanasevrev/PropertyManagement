package com.example.Server.hibernate;

import jakarta.persistence.*;

@Entity
@Table(name="estate")
public class Estate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="city")
    private String city;

    @Column(name="address")
    private String address;

    @Column(name="number_of_rooms")
    private int number_of_rooms;

    @Column(name="price")
    private String price;

    @Lob
    @Column(name="photo")
    private byte[] photo;

    public Estate(){}

    public Estate(String city, String address, int number_of_rooms, String price, byte[] photo) {
        this.city = city;
        this.address = address;
        this.number_of_rooms = number_of_rooms;
        this.price = price;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber_of_rooms() {
        return number_of_rooms;
    }

    public void setNumber_of_rooms(int number_of_rooms) {
        this.number_of_rooms = number_of_rooms;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
