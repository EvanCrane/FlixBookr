package main.client;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ReserveTicketForm
{
    //private TicketController controller;
    @FXML
    private Button btnReserve;
    @FXML
    private Button btnCancel;
    
    @FXML
    public void initialize() {
    
    }
    //public ReserveTicketForm(TicketController controller) {
    //    this.controller = controller;
    //}
    
    @FXML
    private void HandleReserve(MouseEvent event) {
        btnReserve.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) {
                //TODO HANDLE AMOUNT ADDING AND CONFIRMATION
                System.out.println("Opening the cancel reservation confirmation");
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
                System.out.println("Opening the cancel reservation alert");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel reserving?", ButtonType.YES, ButtonType.NO);
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
