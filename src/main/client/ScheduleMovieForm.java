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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ScheduleMovieForm
{
    private Desktop desktop = Desktop.getDesktop();
    @FXML
    private Button btnChooseImage;
    @FXML
    private Button btnScheduleMovie;
    @FXML
    private Button btnCancelChanges;
    
    @FXML
    public void initialize() {
    
    }
    
    @FXML
    private void HandleChooseImage(MouseEvent event) {
        btnChooseImage.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            final FileChooser fileChooser = new FileChooser();
            
            @Override
            public void handle(MouseEvent event) {
                ConfigureFileChooser(fileChooser);
                File file = fileChooser.showOpenDialog(new Stage());
                if (file != null) {
                    openFile(file);
                }
            }
        });
    }
    
    private static void ConfigureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
    
    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void HandleScheduleMovie(MouseEvent event) {
        btnScheduleMovie.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Opening the schedule movie confirmation");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to confirm this reservation?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    alert.close();
                    //TODO SENDING INFO AND ERROR HANDLING
                    OpenHomeScreen(event);
                } else if (alert.getResult() == ButtonType.NO) {
                    alert.close();
                }
            }
        });
    }
    
    @FXML
    private void HandleCancelChanges(MouseEvent event) {
        btnCancelChanges.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Opening the cancel changes confirmation");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel changes?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.YES) {
                    alert.close();
                    OpenHomeScreen(event);
                } else if (alert.getResult() == ButtonType.NO) {
                    alert.close();
                }
            }
        });
    }
    
    private void OpenHomeScreen(MouseEvent event) {
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
}
