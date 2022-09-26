package workshop.javafx.jdbc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
    
    private static Scene mainScane;
    
    @Override
    public void start(Stage stage) throws Exception {
        ScrollPane scrollPane = FXMLLoader.load(getClass().getResource("/gui/MainView.fxml"));
        
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        
        mainScane = new Scene(scrollPane);
        
        stage.setScene(mainScane);
        stage.show();
    }
    
    public static Scene getMainScene() {
        return mainScane;
    }

    public static void main(String[] args) {
        launch(args);
    }   
}
