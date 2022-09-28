package gui;

import gui.util.Alerts;
import workshop.javafx.jdbc.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController implements Initializable{
    
    @FXML
    private MenuItem menuItemSeller;
    
    @FXML
    private MenuItem menuItemDepartment;
    
    @FXML
    private MenuItem menuItemAbout;
    
    @FXML
    public void onMenuItemSellerAction() {
        System.out.println("onMenuItemSellerAction");
    }
    
    @FXML
    public void onMenuItemDepartmentAction() {
        loadView2("/gui/DepartmentList.fxml");
    }
    
    @FXML
    public void onMenuItemSAboutAction() {
        loadView("/gui/About.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    private synchronized void loadView(String absolteName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absolteName));
            VBox newVBox = loader.load();
            
            Scene mainScene = Main.getMainScene();
            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
            
            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(newVBox.getChildren());
            
        }
        catch (IOException e){
            Alerts.showAlert("IOException", "Error loader view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private synchronized void loadView2(String absolteName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absolteName));
            VBox newVBox = loader.load();
            
            Scene mainScene = Main.getMainScene();
            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
            
            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(newVBox.getChildren());
            
            departmentlistController controller = loader.getController();
            controller.setDepartmentService(new DepartmentService());
            controller.updateTableView();
        }
        catch (IOException e){
            Alerts.showAlert("IOException", "Error loader view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
