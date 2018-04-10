package main.java;

import java.time.LocalDateTime;

public class Reservation
{
    //Instance Variables
    private LocalDateTime resTime;
    private Double price;
    private Double amount;
    private Movie movie;
    
    //Constructor
    public Reservation(LocalDateTime resTime, Double price, Double amount, Movie movie) {
        this.resTime = resTime;
        this.price = price;
        this.amount = amount;
        this.movie = movie;
    }
    
    //Properties
    ///Time
    public LocalDateTime getResTime() {
        return this.resTime;
    }
    
    public void setResTime(LocalDateTime resTime) {
        this.resTime = resTime;
    }
    
    ///Price
    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    ///Amount
    public Double getAmount() {
        return this.amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    ///Movie
    public Movie getMovie() {
        return this.movie;
    }
    
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
