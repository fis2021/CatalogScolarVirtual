package org.CatalogVirtual.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.CatalogVirtual.Exceptions.ContulNuExista;
import org.CatalogVirtual.Exceptions.ElevulDejaExista;
import org.CatalogVirtual.Exceptions.ElevulNuExista;
import org.CatalogVirtual.model.Materie;
import org.CatalogVirtual.model.User;
import org.CatalogVirtual.services.MaterieService;
import org.CatalogVirtual.services.UserService;
import org.dizitart.no2.objects.ObjectRepository;



public class AdaugareElevi {
    @FXML
    private User user;
    @FXML
    private TextField numeElev;
    @FXML
    private Button AdaugareButon;
    @FXML
    private Button ButonInapoi;
    @FXML
    private Text mesajAdaugare;

    private final ObjectRepository<Materie> REPOSITORY= MaterieService.getMaterieRepository();
     public void setUser(User user) {
         this.user=user;
     }
    @FXML
    public void handleAdaugareAction() throws Exception {
        try {
          MaterieService.addElev(numeElev.getText(),MaterieService.getnumeMaterie(user.getNume()+" "+user.getPrenume()),user.getNume()+" "+user.getPrenume());
            mesajAdaugare.setText("Elevul a fost adaugat cu succes!");
        } catch (ElevulDejaExista e) {
            mesajAdaugare.setText(e.getMessage());
        }
        catch(ElevulNuExista e){
            mesajAdaugare.setText(e.getMessage());
        }
    }
    @FXML
    public void handleInapoiAction() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("profesorHomePage.fxml"));
            Parent root= loader.load();
            Stage stage = (Stage) (ButonInapoi.getScene().getWindow());
            stage.setScene(new Scene(root));
            ProfesorHomePage profesor = loader.getController();
            profesor.setUser(user);
            stage.show();}

        catch (Exception e) {
            System.out.println("Eroare");
        }
    }
}
