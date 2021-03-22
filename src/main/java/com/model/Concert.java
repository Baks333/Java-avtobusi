package com.model;

public class Concert {
    private int id;
    private String title; //название концерта
    private String band; //группа
    private String location; // место проведения
    private String poster; // ссылка на картинку для постера
    private double price; // цена
    private String details; //Описание концерта
    private String date; // Дата проведения

    public Concert(int id, String title, String band, String location, String poster, double price, String details, String date) {
        this.id = id;
        this.title = title;
        this.band = band;
        this.location = location;
        this.poster = poster;
        this.price = price;
        this.details = details;
        this.date = date;
    }

    public Concert(String title, String band, String location, String poster, String seat, double price, String details, String date) {
        this.title = title;
        this.band = band;
        this.location = location;
        this.poster = poster;
        this.price = price;
        this.details = details;
        this.date = date;
    }

    public Concert() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

