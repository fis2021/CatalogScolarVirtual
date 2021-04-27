package org.CatalogVirtual.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.CatalogVirtual.model.User;
import org.CatalogVirtual.services.UserService;

import java.io.IOException;


public class ProfesorHomePage {
        @FXML
    private Button butonAdaugareNote;
        @FXML
    private Button butonAdaugareElevi;
        @FXML
    private Button trimitereAnunturi;
    @FXML
    private Button butonDeconectare;
        @FXML
    private User user;
        @FXML
        private Text text;


    private  String userName;
    public  void setUser(User user){
        this.user=user;
    }
    public void setDetalii(String detalii)
    {
        text.setText(detalii);
    }
    public void handleAdaugareNote() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("adaugarenote.fxml"));
            Stage stage = (Stage) (butonAdaugareNote.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e)
        {
            System.out.println("eroare");
        }
    }
    public void handleAdaugareElevi() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("adaugareelevi.fxml"));
            Stage stage = (Stage) (butonAdaugareElevi.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e)
        {
            System.out.println("eroare");
        }
    }
    public void handleTrimitereAnunturi() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("trimitereanunturi.fxml"));
            Stage stage = (Stage) (trimitereAnunturi.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e)
        {
            System.out.println("eroare");
        }
    }
    public void handleDeconectare() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("logare.fxml"));
            Stage stage = (Stage) (butonDeconectare.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e)
        {
            System.out.println("eroare");
        }
    }
}
