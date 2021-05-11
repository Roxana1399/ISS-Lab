package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import program.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.Vector;

public class ControllerLocuri implements Initializable {
    @FXML
    private TableView<SpecLoc> locuriDisponibile;
    @FXML
    private TableColumn<SpecLoc, String> spectacol;
    @FXML
    private TableColumn<SpecLoc,Integer> loc;
    @FXML
    private  TableColumn<SpecLoc,String> tip;
    @FXML
    private TableColumn<SpecLoc, String> pret;
    public void initialize(URL url, ResourceBundle rb){
        spectacol.setCellValueFactory(new PropertyValueFactory<SpecLoc, String>("spectacol"));
        loc.setCellValueFactory(new PropertyValueFactory<SpecLoc, Integer>("nr"));
        tip.setCellValueFactory(new PropertyValueFactory<SpecLoc, String>("tip"));
        pret.setCellValueFactory(new PropertyValueFactory<SpecLoc, String>("pret"));
        loadDate();
    }
    public void loadDate(){
        ServiceLocSpec sl = new ServiceLocSpec();
        Vector<SpecLoc> sp = sl.listareLoc();
        //System.out.println(sp.size());
        ObservableList<SpecLoc> data = FXCollections.observableArrayList(sp);
        locuriDisponibile.setItems(data);
    }

}
