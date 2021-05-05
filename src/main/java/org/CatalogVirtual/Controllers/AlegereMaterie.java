package org.CatalogVirtual.Controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import org.CatalogVirtual.model.User;
import org.CatalogVirtual.services.MaterieService;

public class AlegereMaterie {
    @FXML
    private ChoiceBox Materie;
    @FXML
    private Button buttonAlegereMaterie;
    @FXML
    private User user;
    public void setUser(User user){
        this.user=user;
    }
    @FXML
    public void handleAlegere() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("profesorHomePage.fxml"));
        Parent root= loader.load();
        Stage stage = (Stage) (buttonAlegereMaterie.getScene().getWindow());
        stage.setScene(new Scene(root));
        ProfesorHomePage profesor = loader.getController();
        profesor.setUser(user);
        MaterieService.addMaterie((String) Materie.getValue(),user.getNume()+" "+user.getPrenume());
        stage.show();}



    @FXML
    public void initialize(){Materie.getItems().addAll("Matematica","Informatica","Fizica","Geografie","Engleza");}
}
