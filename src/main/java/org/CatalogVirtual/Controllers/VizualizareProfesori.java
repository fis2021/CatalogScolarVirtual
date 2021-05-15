package org.CatalogVirtual.Controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.CatalogVirtual.model.Materie;
import org.CatalogVirtual.model.Parinte;
import org.CatalogVirtual.model.User;
import org.CatalogVirtual.services.MaterieService;
import org.CatalogVirtual.services.ParinteService;
import org.CatalogVirtual.services.UserService;
import org.dizitart.no2.objects.ObjectRepository;


import java.util.ArrayList;
import java.util.Objects;


public class VizualizareProfesori {

    @FXML
    private User user;
    private String numeElev;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private TableView<User> tableView=new TableView<User>();
    @FXML
    private Button backButton;
    private ArrayList<User> listOfProfesori = new ArrayList<User>();
    private ObservableList<User> profesori;

    public void setTableView() {
        tableView.setEditable(true);

        TableColumn firstNameCol = new TableColumn("Nume");
        TableColumn lastNameCol = new TableColumn("Prenume");
        TableColumn numberCol = new TableColumn("Numar de telefon");
        TableColumn emailCol = new TableColumn("Email");
        //TableColumn materieCol = new TableColumn("Materie");
        firstNameCol.setMinWidth(20);
        firstNameCol.setStyle("-fx-backround-color:NAVAJOWHITE");
        lastNameCol.setMinWidth(20);
        numberCol.setMinWidth(150);
        emailCol.setMinWidth(150);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<User,String>("Nume")
        );
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<User,String>("Prenume")
        );
        emailCol.setCellValueFactory(
                new PropertyValueFactory<User,String>("adresaEmail")
        );
        numberCol.setCellValueFactory(
                new PropertyValueFactory<User,String>("nrTel")
        );
        if(user.getRole().equals("Elev")){
            numeElev= user.getNume()+" "+user.getPrenume();
        }
        else
            if (user.getRole().equals("Parinte"))
        {
            numeElev= ParinteService.getElev(user.getNume()+" "+user.getPrenume());
        }
        for (Materie materie : MaterieService.getAllMaterii()) {
            if (materie.verificaElev(numeElev)==true) {
                try {
                    User profesor = UserService.getUserDupaNume(materie.getNumeProfesor());
                    listOfProfesori.add(profesor);
                }
                catch (Exception e)
                {
                    System.out.println("eroare");
                }
            }
        }
        profesori=FXCollections.observableArrayList(listOfProfesori);
        tableView.setItems(profesori);
        tableView.getColumns().addAll(firstNameCol, lastNameCol, numberCol, emailCol);

    }

    public void handleBackAction() throws Exception {
        try {
            if (user.getRole().equals("Elev"))
            {FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("elevHomePage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) (backButton.getScene().getWindow());
            stage.setScene(new Scene(root));
            ElevHomePage elev = loader.getController();
            elev.setUser(user);
            stage.show();}
            else
                if (user.getRole().equals("Parinte")){
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("parinteHomePage.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) (backButton.getScene().getWindow());
                    stage.setScene(new Scene(root));
                    ParinteHomePage elev = loader.getController();
                    elev.setUser(user);
                    stage.show();
                }
        } catch (Exception e) {
            System.out.println("Eroare");
        }
    }
}
