package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import program.*;

import java.awt.*;
import java.io.IOException;

public class Controller {

    public void pressVizualizare(ActionEvent event) throws IOException {
        Stage primaryStage =new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Vizualizare.fxml"));
        primaryStage.setTitle("Vizualizare Locuri");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public void pressLogIn(ActionEvent event) throws IOException {

        Stage primaryStage =new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.show();
    }


}
