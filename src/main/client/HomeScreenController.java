package main.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import main.FlixBookr;

public class HomeScreenController
{
    private FlixBookr flixBookr;
    
    @FXML
    private ToolBar toolBar1;
    @FXML
    private Button toolBtn1;
    
    public void setMainForm(FlixBookr flixBookr) {
        this.flixBookr = flixBookr;
    }
    
    // called by the FXML loader after the labels declared above are injected:
    @FXML
    public void initialize() {
        
        // do initialization and configuration work...
        
        // trivial example, could also be done directly in the fxml:
        toolBtn1.setText("Login");
    }
}
