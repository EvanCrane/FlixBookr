package main.java;


import java.time.LocalDateTime;

import main.server.DBConnector;
import main.java.client.ReserveTicketForm;

public class TicketController implements Controller {

    private Integer regularPriceTime = 6;
    private DBConnector connector;
    private ReserveTicketForm resForm;

    public TicketController() {
        connector = new DBConnector();
    }
    
    public void ReserveTicket() {
        //resForm = new ReserveTicketForm(this);
        //resForm.initialize();
    }

    public Double select(Integer amount, LocalDateTime time, String movieTitle) {

        Movie movie = connector.getPrice(movieTitle);
        if(time.getHour() > regularPriceTime)
            return movie.getRegPrice() * amount;
        return movie.getMatPrice() * amount;

    }

    public void confirm(LocalDateTime resTime, 
                        Double price,
                        Integer amount,
                        String mTitle,
                        String uName) {
        Movie movie = new Movie();
        User user = new User();
        Reservation res = new Reservation();
        
        user.setuName(uName);
        movie.setTitle(mTitle);
        res.setMovie(movie);
        res.setUser(user);
        res.setAmount(amount);
        res.setResTime(resTime);
        res.setPrice(price);

        connector.saveReservation(res);
    }
}