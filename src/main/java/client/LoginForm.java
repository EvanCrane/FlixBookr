package main.java.client;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import main.java.LoginController;

public class LoginForm
{
    private LoginController loginController;

    @FXML
    private Label closeX;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private Button btnLogin;

    //private FlixBookr flixBookr;

    @FXML
    public void initialize(LoginController loginController) {
        this.loginController = loginController;
        HandleLoginButton();
    
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
    private void HandleLoginButton() {
        //When login button is clicked
        btnLogin.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) {
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
                //loginController.submit(txtUsername.getText(),pwdPassword.getText());
                loginController.OpenHomeScreen();
                ((Node) (event.getSource())).getScene().getWindow().hide();
                //OpenHomeScreen(event);
            }
        });
    }
}
