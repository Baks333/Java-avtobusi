package com.model;

public class Tour {
    private int id;
    private String licensePlate; // номера
    private String model; // модель + марка
    private String type; // класс
    private String date; // даты тура
    private double price; // стоимость тура
    private String details; // Описание тура
    private Concert concert; // Концерты


    public Tour(int id, String licensePlate, String model, String type, String date, double price, String details, Concert concert) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.model = model;
        this.type = type;
        this.date = date;
        this.price = price;
        this.details = details;
        this.concert = concert;
    }

    public Tour(String licensePlate, String model, String type, String date, double price, String details, Concert concert) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.type = type;
        this.date = date;
        this.price = price;
        this.details = details;
        this.concert = concert;
    }

    public Tour() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }
}
