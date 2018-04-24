package main.server;


import java.time.LocalDateTime;

import main.server.DBConnector;
import main.server.Controller;
import main.client.ReserveTicketForm;
import main.java.Reservation;
import main.java.Movie;

public class TicketController implements Controller {

    private Integer regularPriceTime = 6;
    private DBConnector connector;
    private ReserveTicketForm resForm;

    public TicketController() {
        connector = new DBConnector();
    }
    
    public void ReserveTicket() {
        resForm = new ReserveTicketForm(this);        
        resForm.initialize();
    }

    public Double select(Integer amount, LocalDateTime time, String movieTitle) {

        Movie movie = connector.getMovie(movieTitle);
        if(time.getHour() > regularPriceTime)
            return movie.regPrice * amount;
        return movie.matPrice * amount;

    }

    public void confirm(Reservation res) {
        connector.saveReservation(res);
    }

    public void cancel() {
        resform.HandleCancel();
    }
}