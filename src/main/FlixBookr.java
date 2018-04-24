package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class FlixBookr extends Application
{
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FlixBookr");
    
        //InitRootLayout();
    
        ShowLoginScreen(primaryStage);
    }
    
    public void ShowLoginScreen(Stage stage) {
        try {
            // Load root layout from fxml file.
            Parent root = FXMLLoader.load(getClass().getResource("client/Login.fxml"));
            stage.initStyle(StageStyle.UNDECORATED);
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
