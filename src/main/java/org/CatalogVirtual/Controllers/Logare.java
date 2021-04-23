package org.CatalogVirtual.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.CatalogVirtual.Exceptions.ContulDejaExista;
import org.CatalogVirtual.Exceptions.ContulNuExista;
import org.CatalogVirtual.services.UserService;

import java.io.IOException;

public class Logare {
    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Button buttonLogare;
    @FXML
    private Button buttonCreare;
    public void handleLogareAction() throws Exception {
        try {
            UserService.checkUserDoesAlreadyExist(usernameField.getText());
            Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("paginaprincipala.fxml"));
            Stage stage = (Stage) (buttonLogare.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();


        } catch (ContulNuExista e) {
            registrationMessage.setText(e.getMessage());
        }
    }
    @FXML
    public void handleInregistrareAction() throws Exception{
        try{
            Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("inregistrare.fxml"));
            Stage stage = (Stage) (buttonCreare.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();

        } catch(IOException e){
            registrationMessage.setText("eroare!");
        }
    }

}
