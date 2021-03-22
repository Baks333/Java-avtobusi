package com.model;

public class Order {
    private int id;
    private Concert concert; // Выбранный концерт
    private Tour tour; // Выбранный тур
    private User user; // Клиент
    private Room room; // Выбранный номер
    private double cost; // Общая стоимость
    private String status; // Состояние заказа


    public Order(int id, Concert concert, Tour tour, User user, Room room, double cost, String status) {
        this.id = id;
        this.concert = concert;
        this.tour = tour;
        this.user = user;
        this.room = room;
        this.cost = cost;
        this.status = status;
    }

    public Order(Concert concert, Tour tour, User user, Room room, double cost, String status) {
        this.concert = concert;
        this.tour = tour;
        this.user = user;
        this.room = room;
        this.cost = cost;
        this.status = status;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
