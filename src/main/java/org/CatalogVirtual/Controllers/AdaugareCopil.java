package org.CatalogVirtual.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.CatalogVirtual.Exceptions.ElevulNuExista;
import org.CatalogVirtual.model.User;
import org.CatalogVirtual.services.ParinteService;


import java.security.spec.ECField;

public class AdaugareCopil {
    private User user;
    @FXML
    private Button setare;
    @FXML
    private Button buttonInapoi;
    @FXML
    private TextField numeElev;
    @FXML
    private Text mesajSetare;
    public void handleInapoi() throws Exception{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("parinteHomePage.fxml"));
            Parent root= loader.load();
            Stage stage = (Stage) (buttonInapoi.getScene().getWindow());
            stage.setScene(new Scene(root));
            ParinteHomePage parinte = loader.getController();
            parinte.setUser(user);
            stage.show();
        }
        catch (Exception e){
            System.out.println("eroare");
        }
    }
    public void handleSetare() throws Exception{
        try{
            ParinteService.addParinte(user.getNume()+" "+user.getPrenume(),numeElev.getText() );
            mesajSetare.setText("Copilul a fost adaugat cu succes");
        }
        catch (Exception e){
            mesajSetare.setText(e.getMessage());
        }
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
