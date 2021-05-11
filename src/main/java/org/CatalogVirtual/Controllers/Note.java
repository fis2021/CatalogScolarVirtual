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
import org.CatalogVirtual.model.Data;
import org.CatalogVirtual.model.Materie;
import org.CatalogVirtual.model.Nota;
import org.CatalogVirtual.model.User;
import org.CatalogVirtual.services.MaterieService;
import org.CatalogVirtual.services.NoteService;
import org.CatalogVirtual.services.UserService;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.ArrayList;

public class Note {
    private User user;
    private static final ObjectRepository<Nota> REPOSITORY = NoteService.getNotaRepository();

    public void setUser(User user) {
        this.user = user;
    }
    @FXML
    private TableView<Nota> tableView;
    @FXML
    private Button backButton;
    private static ArrayList<Nota> listOfNote = new ArrayList<Nota>();
    private ObservableList<Nota>note ;
    public void setTableView() {
        tableView.setEditable(true);

        TableColumn firstNameCol = new TableColumn("Nume materie");
        TableColumn notaCol = new TableColumn("Nota");
        TableColumn dataCol = new TableColumn("Data");
        //TableColumn materieCol = new TableColumn("Materie");
        firstNameCol.setMinWidth(20);
        firstNameCol.setStyle("-fx-backround-color:NAVAJOWHITE");
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Nota,String>("materie")
        );
        dataCol.setCellValueFactory(
                new PropertyValueFactory<Nota, Data>("data")
        );
        notaCol.setCellValueFactory(
                new PropertyValueFactory<Nota,Integer>("value")
        );
        for (Nota nota : REPOSITORY.find()) {
            if (nota.getNumeElev().equals(user.getNume() + " " + user.getPrenume())==true) {
                try {
                    listOfNote.add(nota);
                }
                catch (Exception e)
                {
                    System.out.println("eroare");
                }
            }
        }
        note= FXCollections.observableArrayList(listOfNote);
        tableView.setItems(note);
        tableView.getColumns().addAll(firstNameCol, notaCol, dataCol);

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
