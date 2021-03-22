package com.model;

public class Hotel {
        private int id;
        private String title; //Название отеля
        private int star; // Количество звезд
        private double rating; // Рейтинг
        private String hotelImg; // Фото отеля
        private String address; // Адрес
        private String phoneNumber; // Контактный номер
        private String details; // Описание отеля
        private Tour tour; // Туры

    public Hotel(int id, String title, int star, double rating, String hotelImg, String address, String phoneNumber, String details, Tour tour) {
        this.id = id;
        this.title = title;
        this.star = star;
        this.rating = rating;
        this.hotelImg = hotelImg;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.details = details;
        this.tour = tour;
    }

    public Hotel(String title, int star, double rating, String hotelImg, String address, String phoneNumber, String details, Tour tour) {
        this.title = title;
        this.star = star;
        this.rating = rating;
        this.hotelImg = hotelImg;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.details = details;
        this.tour = tour;
    }

    public Hotel() {
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

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getHotelImg() {
        return hotelImg;
    }

    public void setHotelImg(String hotelImg) {
        this.hotelImg = hotelImg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
