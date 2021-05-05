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
import org.CatalogVirtual.Exceptions.ContulNuExista;
import org.CatalogVirtual.model.User;
import org.CatalogVirtual.services.UserService;
import org.dizitart.no2.objects.ObjectRepository;

import java.io.IOException;

public class Logare {
    private final ObjectRepository<User> REPOSITORY = UserService.getUserRepository();
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
            User user = UserService.login(usernameField.getText(), passwordField.getText());
            String detalii = user.toString();
            if(user.getRole().equals("Profesor")) {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("profesorHomePage.fxml"));
                Parent root= loader.load();
                Stage stage = (Stage) (buttonLogare.getScene().getWindow());
                stage.setScene(new Scene(root));
                ProfesorHomePage profesor = loader.getController();

                profesor.setUser(user);
                stage.show();}
            else
                if(user.getRole().equals("Elev")){
                    FXMLLoader loader1 = new FXMLLoader(getClass().getClassLoader().getResource("elevHomePage.fxml"));
                    Parent root= loader1.load();
                    Stage stage = (Stage) (buttonLogare.getScene().getWindow());
                    stage.setScene(new Scene(root));
                    ElevHomePage elev = loader1.getController();
                    elev.setUser(user);

                    stage.show();
                }
                else if(user.getRole().equals("Parinte"))
                {
                    FXMLLoader loader2 = new FXMLLoader(getClass().getClassLoader().getResource("parinteHomePage.fxml"));
                    Parent root= loader2.load();
                    Stage stage = (Stage) (buttonLogare.getScene().getWindow());
                    stage.setScene(new Scene(root));
                    ParinteHomePage parinte = loader2.getController();
                    parinte.setUser(user);
                    stage.show();
                }


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
    public String getUsername(){
        return usernameField.getText();
    }
    public String getPassword(){
        return passwordField.getText();
    }

}
