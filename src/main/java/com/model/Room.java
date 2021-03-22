package com.model;


public class Room {
    private int id;
    private int capacity; //Вместимость номера
    private double price; //Цена за ночь
    private String roomImg; //Фото номера
    private String facilities; // Описание что входит в стоимость
    private Hotel hotel; // Отель, в котором находится номер


    public Room(int id, int capacity, double price, String roomImg, String facilities, Hotel hotel) {
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        this.roomImg = roomImg;
        this.facilities = facilities;
        this.hotel = hotel;
    }

    public Room(int capacity, double price, String roomImg, String facilities, Hotel hotel) {
        this.capacity = capacity;
        this.price = price;
        this.roomImg = roomImg;
        this.facilities = facilities;
        this.hotel = hotel;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoomImg() {
        return roomImg;
    }

    public void setRoomImg(String roomImg) {
        this.roomImg = roomImg;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
