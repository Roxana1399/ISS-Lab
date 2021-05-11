package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import program.*;
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Home");
        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        /*RepoClient repoClient = new RepoClient();
        repoClient.addDb();
        System.out.println(repoClient.size());
        RepoSpectacol repoSpectacol = new RepoSpectacol();
        repoSpectacol.addDb();
        System.out.println(repoSpectacol.size());
        RepoLoc repoLoc = new RepoLoc();
        repoLoc.addDb();
        System.out.println(repoLoc.size());
        RepoRezervare repoRezervare = new RepoRezervare();
        repoRezervare.addBd();
        System.out.println(repoRezervare.size());*/

    }



}