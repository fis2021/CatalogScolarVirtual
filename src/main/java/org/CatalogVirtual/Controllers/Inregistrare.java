package org.CatalogVirtual.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.CatalogVirtual.Exceptions.ContulDejaExista;
import org.CatalogVirtual.services.UserService;

import java.io.IOException;

public class Inregistrare<numarTelefon, numberField> {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField Nume;
    @FXML
    private TextField Prenume;
    @FXML
    private ChoiceBox role;
    @FXML
    private TextField nrTel;
    @FXML
    private TextField adresaEmail;
    @FXML
    private Button button;
    @FXML
    private Button buttonInregistrare;


    @FXML
    public void initialize() {
        role.getItems().addAll("Profesor", "Elev","Parinte");
    }

    @FXML
    public void handleRegisterAction() throws Exception {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(),Nume.getText(),Prenume.getText(), (String) role.getValue(), nrTel.getText(),adresaEmail.getText());
            registrationMessage.setText("Contul a fost creat cu succes!");


        } catch (ContulDejaExista e) {
            registrationMessage.setText(e.getMessage());
        }
    }
    @FXML
    public void Backaction() throws Exception{
        try{
            Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("logare.fxml"));
            Stage stage = (Stage) (button.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();

        } catch(IOException e){
            registrationMessage.setText("eroare!");
        }
    }
}


