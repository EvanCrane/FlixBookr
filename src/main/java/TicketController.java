package main.java;


import java.io.IOException;
import java.time.LocalDateTime;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.server.DBConnector;
import main.java.client.ReserveTicketForm;

public class TicketController implements Controller {

    private Integer regularPriceTime = 6;
    private DBConnector connector;
    private ReserveTicketForm resForm;

    public TicketController() {
        connector = new DBConnector();
    }
    
    public void ReserveTicket(Stage stage, String mTitle) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("client/ReserveTicket.fxml"));
            resForm = new ReserveTicketForm();
            Parent root1 = fxmlLoader.load();
            resForm = fxmlLoader.getController();
            resForm.initialize(this, mTitle);
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Reserve Ticket");
            
            stage.show();
            resForm.DisplayDetails(mTitle);
            // Hide this current window (if this is what you want)
            //((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    
    public void OpenHomeScreen(boolean isAdmin) {
    
    }
}