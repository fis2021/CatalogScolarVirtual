package org.CatalogVirtual.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.CatalogVirtual.Exceptions.ParinteleNuExista;
import org.CatalogVirtual.model.User;
import org.CatalogVirtual.services.AnuntService;


public class AdaugaAnunt {
    @FXML
    private Button buttonAdauga;
    @FXML
    private Button buttonInapoi;
    @FXML
    private TextField numeParinte;
    @FXML
    private TextField mesaj;
    @FXML
    private Text mesajadaugare;
    private User user;

    public void setUser(User user) {
        this.user = user;
    }
    public void handleAdaugaAnunt() throws Exception{
        try {
            AnuntService.addAnunt(mesaj.getText(), user.getNume()+" "+user.getPrenume(),numeParinte.getText());
            mesajadaugare.setText("Anuntul a fost trimis cu succes");
        }
        catch (ParinteleNuExista e)
        {
            mesajadaugare.setText(e.getMessage());
        }
    }
    public void handleInapoi() throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("profesorHomePage.fxml"));
            Parent root= loader.load();
            Stage stage = (Stage) (buttonInapoi.getScene().getWindow());
            stage.setScene(new Scene(root));
            ProfesorHomePage profesor = loader.getController();
            profesor.setUser(user);
        }
        catch (Exception e){
            System.out.println("eroare!");
        }
    }
}
