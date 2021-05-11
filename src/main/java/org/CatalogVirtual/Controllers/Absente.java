package org.CatalogVirtual.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.CatalogVirtual.model.Absenta;
import org.CatalogVirtual.model.Data;
import org.CatalogVirtual.model.Nota;
import org.CatalogVirtual.model.User;
import org.CatalogVirtual.services.AbsentaService;
import org.CatalogVirtual.services.NoteService;
import org.CatalogVirtual.services.ParinteService;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.ArrayList;

public class Absente {
    private User user;
    private  final ObjectRepository<Absenta> REPOSITORY = AbsentaService.getAbsentaRepository();

    public void setUser(User user) {
        this.user = user;
    }
    @FXML
    private TableView<Absenta> tableView;
    @FXML
    private Button backButton;
    private  ArrayList<Absenta> listOfAbsente = new ArrayList<Absenta>();
    private ObservableList<Absenta> absenta ;
    public void setTableView() {
        tableView.setEditable(true);
        String numeElev;
        if(user.getRole().equals("Elev")){
            numeElev=user.getNume()+" "+user.getPrenume();
        }
        else {
            numeElev= ParinteService.getElev(user.getNume()+" "+user.getPrenume());
        }
        TableColumn firstNameCol = new TableColumn("Nume materie");
        TableColumn dataCol = new TableColumn("Data");
        //TableColumn materieCol = new TableColumn("Materie");
        firstNameCol.setMinWidth(20);
        firstNameCol.setStyle("-fx-backround-color:NAVAJOWHITE");
       firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Absenta,String>("NumeMaterie")
        );
        dataCol.setCellValueFactory(
                new PropertyValueFactory<Absenta, Data>("data")
        );
        for (Absenta absenta : REPOSITORY.find()) {
            if (absenta.getNumeElev().equals(numeElev)==true) {
                try {
                    listOfAbsente.add(absenta);
                }
                catch (Exception e)
                {
                    System.out.println("eroare");
                }
            }
        }
        absenta= FXCollections.observableArrayList(listOfAbsente);
        tableView.setItems(absenta);
        tableView.getColumns().addAll(firstNameCol, dataCol);

    }
    public void handleInapoi() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("VizualizareMaterii.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) (backButton.getScene().getWindow());
            stage.setScene(new Scene(root));
            VizualizareMaterii materii= loader.getController();
            materii.setUser(user);
            stage.show();
        } catch (Exception e) {
            System.out.println("Eroare");
        }
    }
}
