package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.LoginController;

import java.io.IOException;

public class FlixBookr extends Application
{
    private Stage primaryStage;
    private BorderPane rootLayout;
    private LoginController loginController = new LoginController();
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FlixBookr");
    
        loginController.login(primaryStage);
    }
}
