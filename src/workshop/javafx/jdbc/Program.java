package workshop.javafx.jdbc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Program extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        ScrollPane scrollPane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        
        Scene scene = new Scene(scrollPane);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }   
}
