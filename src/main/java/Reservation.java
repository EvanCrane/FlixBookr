package main.java;

import java.time.LocalDateTime;

public class Reservation
{
    //Instance Variables
    private LocalDateTime resTime;
    private Double price;
    private Double amount;
    private Movie movie;
    
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
