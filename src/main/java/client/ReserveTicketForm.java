package main.java.client;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.TicketController;

import java.io.IOException;

public class ReserveTicketForm
{
    private TicketController controller;
    @FXML
    private Label txtMovieTitle1;
    @FXML
    private ImageView imageMovie;
    @FXML
    private TextArea txtDescription;
    @FXML
    private ChoiceBox choiceTickets;
    @FXML
    private DatePicker dateMovieDate;
    @FXML
    private ChoiceBox choiceShowTime;
    @FXML
    private Label labelAmount;
    @FXML
    private Button btnReserve;
    @FXML
    private Button btnCancel;
    
    @FXML
    public void initialize(TicketController ticketController, String mTitle) {
        this.controller = ticketController;
        
    }
    //public ReserveTicketForm(TicketController controller) {
    //    this.controller = controller;
    //}
    
    public void DisplayDetails(String mTitle) {
        txtMovieTitle1.setText(mTitle);
    }
    
    @FXML
    private void HandleReserve(MouseEvent event) {
        btnReserve.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) {
                //Validation for fields
                if (choiceTickets.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an amount of tickets", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
                if(dateMovieDate.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a date for the reservation", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
                if(choiceShowTime.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a showtime", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
                
                //TODO HANDLE AMOUNT ADDING AND CONFIRMATION
                System.out.println("Opening the OpenHomeScreen reservation confirmation");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to confirm this reservation?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    //TODO PLACE METHOD FOR RESERVING TICKET HERE
                    ///controller.confirm(Double)
                    alert.close();
                    // Hide this current window
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } else if (alert.getResult() == ButtonType.NO) {
                    alert.close();
                }
            }
        });
    }
    
    @FXML
    private void HandleCancel(MouseEvent event) {
        btnCancel.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Opening the OpenHomeScreen reservation alert");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to OpenHomeScreen reserving?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    alert.close();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } else if (alert.getResult() == ButtonType.NO) {
                    alert.close();
                }
            }
        });
    }
}
