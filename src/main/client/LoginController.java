package main.client;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController
{
    @FXML
    private Label closeX;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private Button btnLogin;
    
    //private FlixBookr flixBookr;
    
    public LoginController() {
    
    }
    
    @FXML
    private void initialize() {
    
    }
    
    @FXML
    private void HandleClose(MouseEvent event) {
        closeX.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to exit FlixBookr", ButtonType.NO, ButtonType.YES);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    alert.close();
                    System.exit(0);
                } else if (alert.getResult() == ButtonType.NO) {
                    alert.close();
                }
            }
        });
    }
    
    @FXML
    private void HandleLoginButton(ActionEvent event) {
        //When login button is clicked
        btnLogin.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event) {
                if (txtUsername.getText().isEmpty()) {
                    Alert alert = new Alert(AlertType.CONFIRMATION, "Please Enter a Username", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
                if (pwdPassword.getText().isEmpty()) {
                    Alert alert = new Alert(AlertType.CONFIRMATION, "Please Enter a Password", ButtonType.OK);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK) {
                        alert.close();
                    }
                    return;
                }
                System.out.println("Attempting to log in using given credentials.");
                OpenHomeScreen(event);
            }
        });
    }
    
    private void OpenHomeScreen(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomeScreen.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
    public void setMainForm(FlixBookr flixBookr) {
        this.flixBookr = flixBookr;
    }
    */
}
