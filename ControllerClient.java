package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import program.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class ControllerClient implements Initializable {
    @FXML
    private TableView<Raport> locuriTeatru;
    @FXML private TableColumn<Raport, Integer> id11;
    @FXML
    private TableColumn<Raport, String> nume11;
    @FXML
    private TableColumn<Raport, String> data1;
    @FXML
    private TableColumn<Raport, String> ora1;
    @FXML
    private TableColumn<Raport,Integer> loc_libere;
    @FXML
    private TableColumn<Raport,Integer> loc_ocupate;
    @FXML
    private  TableColumn<Raport,String> pret_l1;
    @FXML
    private TableColumn<Raport, String> pret_r1;
    public void initialize(URL url, ResourceBundle rb){
        id11.setCellValueFactory(new PropertyValueFactory<Raport, Integer>("id"));
        nume11.setCellValueFactory(new PropertyValueFactory<Raport, String>("nume"));
        data1.setCellValueFactory(new PropertyValueFactory<Raport, String>("data"));
        ora1.setCellValueFactory(new PropertyValueFactory<Raport, String>("ora"));
        loc_libere.setCellValueFactory(new PropertyValueFactory<Raport, Integer>("loc_l"));
        loc_ocupate.setCellValueFactory(new PropertyValueFactory<Raport, Integer>("loc_o"));
        pret_l1.setCellValueFactory(new PropertyValueFactory<Raport, String>("pret_l"));
        pret_r1.setCellValueFactory(new PropertyValueFactory<Raport, String>("pret_r"));
        loadDate();
    }
    public void loadDate(){
        ServiceRaport sl = new ServiceRaport();
        Vector<Raport> sp = sl.raportSpectacol();
        System.out.println(sp.size());
        ObservableList<Raport> data = FXCollections.observableArrayList(sp);
        locuriTeatru.setItems(data);
    }

}
