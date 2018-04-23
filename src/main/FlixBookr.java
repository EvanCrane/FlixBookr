package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.client.FlixBookrController;
import main.client.HomeScreenController;

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
        
        InitRootLayout();
        
        ShowHomeScreen();
    }
    
    private void InitRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FlixBookr.class
                    .getResource("client/FlixBookr.fxml"));
            rootLayout = loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            
            // Give the controller access to the main app.
            FlixBookrController controller = loader.getController();
            controller.setMainForm(this);
            
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    private void ShowHomeScreen() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FlixBookr.class
                    .getResource("client/HomeScreen.fxml"));
            AnchorPane homeScreen = loader.load();
            
            // Show the scene containing the root layout.
            rootLayout.setCenter(homeScreen);
            
            // Give the controller access to the main app.
            HomeScreenController controller = loader.getController();
            controller.setMainForm(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
