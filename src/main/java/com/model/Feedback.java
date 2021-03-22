package com.model;

public class Feedback {
    private int id;
    private User user; // Пользователь, написавший отзыв
    private String date; // Дата написания отзыва
    private String text; // Отзыв
    private Hotel hotel; // Отель, для которого опубликован отзыв
    private int rating; // Рейтинг, поставленный с отзывом


    public Feedback(int id, User user, String date, String text, Hotel hotel, int rating) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.text = text;
        this.hotel = hotel;
        this.rating = rating;
    }

    public Feedback(User user, String date, String text, Hotel hotel, int rating) {
        this.user = user;
        this.date = date;
        this.text = text;
        this.hotel = hotel;
        this.rating = rating;
    }

    public Feedback() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
