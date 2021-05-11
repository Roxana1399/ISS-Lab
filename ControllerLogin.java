package sample;
import java.sql.*;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import program.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

public class ControllerLogin implements Initializable {
    @FXML
    private TextField email;
    @FXML
    private TextField parola;
    @FXML
    private TextField numeSpec;
    @FXML
    private TextField locuri;
    @FXML
    private Label mesaj;
    @FXML
    private Label numeLogatClient;
    private static String numeClient;
    private static String id_client;
    @FXML
    private TextField nume;
    @FXML
    private TextField data;
    @FXML
    private TextField ora;
    @FXML
    private TextField pret_l;
    @FXML
    private TextField pre_r;
    @FXML
    private TextField sterge;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void pressLogInAc(ActionEvent event) throws IOException {
        String e = email.getText();
        String p = parola.getText();
        email.clear();
        parola.clear();
        RepoClient repoClient = new RepoClient();
        repoClient.addDb();
        String cnp = null;
        Integer ok = 0, oka = 0;
        for (int i = 0; i < repoClient.size(); i++) {
            Client c = repoClient.getClienti().get(i);
            String e_c = c.getEmail();
            String p_c = c.getParola();
            if (e.equals(e_c) && p.equals(p_c)) {
                ok = 1;
                cnp = c.getCnp();
                numeClient = c.getNume().concat(" ").concat(c.getPrenume());
                System.out.println(numeClient);
                break;
            }
        }
        if (ok == 1) {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Client.fxml"));
            primaryStage.setTitle("Client");
            primaryStage.setScene(new Scene(root, 300, 500));
            primaryStage.show();
            id_client = cnp;
            //System.out.println(id_client);
        }
        if (ok == 0) {
            RepoAdmin repoAdmin = new RepoAdmin();
            repoAdmin.addDb();
            for (int i = 0; i < repoAdmin.size(); i++) {
                Admin a = repoAdmin.getAdmini().get(i);
                String e_a = a.getEmail();
                String p_a = a.getParola();
                if (e.equals(e_a) && p.equals(p_a)) oka = 1;
            }
        }
        if (oka == 1) {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
            primaryStage.setTitle("Admin");
            primaryStage.setScene(new Scene(root, 300, 550));
            primaryStage.show();

        }
        if (ok == 0 && oka == 0)
            mesaj.setText("E-mail sau parola gresita!");
    }

    public void pressVizualizare(ActionEvent event) throws IOException {
        numeLogatClient.setText(numeClient);
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Vizualizare.fxml"));
        primaryStage.setTitle("Vizualizare");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void pressRezervare(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("rezervare.fxml"));
        primaryStage.setTitle("Rezervare");
        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.show();
    }

    @FXML
    private Label error1;

    public void pressRezerva(ActionEvent event) throws IOException {
        String numeSpectacol = numeSpec.getText();
        numeSpec.clear();
        Integer locRez = -1;
        try {
            locRez = Integer.parseInt(locuri.getText());
        } catch (Exception e) {
            locRez = 0;
        }
        locuri.clear();
        RepoLoc repoLoc = new RepoLoc();
        repoLoc.addDb();
        RepoSpectacol repoSpectacol = new RepoSpectacol();
        repoSpectacol.addDb();
        Integer id_s = repoSpectacol.getIdName(numeSpectacol);
        Integer id_l = repoLoc.getIdNumar(locRez);
        RepoRezervare repoRezervare = new RepoRezervare();
        if (id_s != null && id_l != null) {
            if (repoRezervare.verifRez(id_s, id_l) == true) {
                ServiceLocSpec sp = new ServiceLocSpec();
                //  System.out.println(sp.addReze(id_client,id_l, id_s));
                if (sp.addReze(id_client, id_l, id_s)) error1.setText("Rezervare cu Succes !");
            } else {
                error1.setText("Loc indisponibil !");
            }
        } else {
            error1.setText("Date Gresite");
        }
    }

    public void pressIsporicView(ActionEvent event) throws IOException {
        ServiceLocSpec sl = new ServiceLocSpec();
        Vector<SpecLoc> sp = sl.locuriIstoric(id_client);
        Scene scene = new Scene(new Group());
        Stage stage = new Stage();
        TableView<SpecLoc> table = new TableView<SpecLoc>();
        final ObservableList<SpecLoc> data =
                FXCollections.observableArrayList(sp);
        stage.setTitle("Istoric");
        stage.setWidth(400);
        stage.setHeight(550);

        final Label label = new Label(""); //Pacienti alefabetic
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("Spectacol");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<SpecLoc, String>("spectacol"));
        TableColumn locCol = new TableColumn("Loc");
        locCol.setMinWidth(20);
        locCol.setCellValueFactory(
                new PropertyValueFactory<SpecLoc, Integer>("nr"));
        TableColumn tipCol = new TableColumn("Tip");
        tipCol.setMinWidth(50);
        tipCol.setCellValueFactory(
                new PropertyValueFactory<SpecLoc, String>("tip"));
        TableColumn pretCol = new TableColumn("Pret");
        pretCol.setMinWidth(50);
        pretCol.setCellValueFactory(
                new PropertyValueFactory<SpecLoc, String>("pret"));
        TableColumn dataCol = new TableColumn("Data");
        dataCol.setMinWidth(50);
        dataCol.setCellValueFactory(
                new PropertyValueFactory<SpecLoc, String>("data"));
        TableColumn oraCol = new TableColumn("Ora");
        oraCol.setMinWidth(50);
        oraCol.setCellValueFactory(
                new PropertyValueFactory<SpecLoc, String>("ora"));
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, locCol, tipCol, pretCol, dataCol, oraCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    public void pressAnulare(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Anulare.fxml"));
        primaryStage.setTitle("Anulare Rezervare");
        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.show();
    }

    @FXML
    private TextField numeAnulare; //numespectacol -> id_s
    @FXML
    private TextField locAnulare; // numar -> Id_l
    @FXML
    private Label error2;

    public void pressAnuleaza(ActionEvent event) throws IOException {
        RepoSpectacol repoSpectacol = new RepoSpectacol();
        repoSpectacol.addDb();
        RepoLoc repoLoc = new RepoLoc();
        repoLoc.addDb();
        Integer id_l = -1, id_s = -1;
        try {
            id_l = repoLoc.getIdNumar(Integer.parseInt(locAnulare.getText()));
        } catch (Exception e) {
            id_l = null;
        }
        //System.out.println("Id" + id_l);
        id_s = repoSpectacol.getIdName(numeAnulare.getText());
       // System.out.println("id2" + id_s);
        locAnulare.clear();
        numeAnulare.clear();
        error2.setText(" ");
        if (id_l != null && id_s != null) {
            ServiceLocSpec sp = new ServiceLocSpec();
            //System.out.println(sp.anulareRez(id_client, id_l, id_s));
            Boolean ok= sp.anulareRez(id_client, id_l, id_s);
            System.out.println("Anulare "+ok);
            if (!ok) error2.setText("Anulare cu Succes !");
            else {
                error2.setText("Data invalide!");}

        } else {
            error2.setText("Data invalide!");
        }

    }

    public void pressVizualizareSp(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Istoric.fxml"));
        primaryStage.setTitle("Istoric");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public void adaugaSpectacol(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("adaugare.fxml"));
        primaryStage.setTitle("Adauga Spectacol");
        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.show();
    }

    @FXML
    private Label error3;

    public void pressadauga(ActionEvent event) throws IOException {
        if (nume.getText().length() != 0 && data.getText().length() != 0 && ora.getText().length() != 0 && pret_l.getText().length() != 0 && pre_r.getText().length() != 0) {
            Spectacol spectacol = new Spectacol(1, nume.getText(), data.getText(), ora.getText(), pret_l.getText(), pre_r.getText());
            ServiceLocSpec sp = new ServiceLocSpec();
            Boolean ok = sp.writeDB(spectacol);
            if (ok == true) error3.setText("Adaugare cu succes !");
            else error3.setText("Date invalide !");
        } else error3.setText("Date invalide !");

    }

    public void stergeSpectacol(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Stergere.fxml"));
        primaryStage.setTitle("Stergere Spectacol");
        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.show();
    }

    @FXML
    private Label error4;

    public void presssterge(ActionEvent event) throws IOException {
        try {
            ServiceLocSpec sp = new ServiceLocSpec();
            Boolean make = sp.removeBD(Integer.parseInt(sterge.getText()));
            if (make == true) {
                error4.setText("Stergere cu succes !");
            } else error4.setText("Insucces !");
        } catch (Exception e) {
            error4.setText("Insucces !");
        }

    }

    public void modificaSpectacol(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Modifica.fxml"));
        primaryStage.setTitle("Modifica Spectacol");
        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.show();
    }

    @FXML
    private TextField datam;
    @FXML
    private TextField numesp;
    @FXML
    private Label error5;

    public void pressModificaData(ActionEvent event) throws IOException {
        try {
            ServiceLocSpec sp = new ServiceLocSpec();
            Boolean make = sp.modificaData(Integer.parseInt(numesp.getText()), datam.getText());
            if (make == true) {
                error5.setText("Modificare cu succes !");
            } else error5.setText("Insucces !");
            datam.clear();
        } catch (Exception e) {
            error5.setText("Insucces !");
        }
    }

    @FXML
    private TextField oram;

    public void pressModificaOra(ActionEvent event) throws IOException {
        try {
            ServiceLocSpec sp = new ServiceLocSpec();
            Boolean make = sp.modificaOra(Integer.parseInt(numesp.getText()), oram.getText());
            if (make == true) {
                error5.setText("Modificare cu succes !");
            } else error5.setText("Insucces !");
            oram.clear();
        } catch (Exception e) {
            error5.setText("Insucces !");
        }
    }

    @FXML
    private TextField pretlm;

    public void pressModificaPretl(ActionEvent event) throws IOException {
        try {
            ServiceLocSpec sp = new ServiceLocSpec();
            Boolean make = sp.modificaPretl(Integer.parseInt(numesp.getText()), pretlm.getText());
            if (make == true) {
                error5.setText("Modificare cu succes !");
            } else error5.setText("Insucces !");
            pretlm.clear();
        } catch (Exception e) {
            error5.setText("Insucces !");
        }
    }

    @FXML
    private TextField pretrm;

    public void pressModificaPretr(ActionEvent event) throws IOException {
        try {
            ServiceLocSpec sp = new ServiceLocSpec();
            Boolean make = sp.modificaPretr(Integer.parseInt(numesp.getText()), pretrm.getText());
            if (make == true) {
                error5.setText("Modificare cu succes !");
            } else error5.setText("Insucces !");
            pretrm.clear();
        } catch (Exception e) {
            error5.setText("Insucces !");
        }
    }

    public void cont(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("create.fxml"));
        primaryStage.setTitle("Modifica Spectacol");
        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.show();
    }

    @FXML
    private TextField numeC;
    @FXML
    private TextField prenumeC;
    @FXML
    private TextField cnpC;
    @FXML
    private TextField emailc;
    @FXML
    private TextField telefonc;
    @FXML
    private Label error6;

    /*public void creareCont(ActionEvent event) throws IOException {
        Integer ok=0;
            if (numeC.getText().length() != 0 && prenumeC.getText().length() != 0 && cnpC.getText().length() != 0 && emailc.getText().length() != 0 && telefonc.getText().length() != 0) {
                try {
                    Client c = new Client(cnpC.getText(), numeC.getText(), prenumeC.getText(), emailc.getText(), telefonc.getText(), "0");
                    ServiceLocSpec sp = new ServiceLocSpec();
                    //Boolean ok = sp.insertClient(c);
                    String parola = sp.getParola(numeC.getText());
                    error6.setText(parola);
                    System.out.println(parola);
                } catch (Exception e) {
                    error6.setText("Date invalide !");
                }

                // else error6.setText("Date invalide !");

            }else error6.setText("Date invalide !");


    }*/
    public void creareCont(ActionEvent event) throws IOException {
        if(numeC.getText().length()!=0 && prenumeC.getText().length()!=0 && cnpC.getText().length()!=0 && emailc.getText().length()!=0 && telefonc.getText().length()!=0) {
            Client c= new Client(cnpC.getText(), numeC.getText(), prenumeC.getText(), emailc.getText(),telefonc.getText(),"0");
            ServiceLocSpec sp = new ServiceLocSpec();
            String ok = sp.insertClient(c);
            error6.setText("Parola: "+ok);

        }
        else error6.setText("Date invalide !");


    }
}