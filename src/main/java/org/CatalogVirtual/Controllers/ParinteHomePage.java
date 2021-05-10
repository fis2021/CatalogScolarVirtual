package org.CatalogVirtual.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.CatalogVirtual.model.User;

import java.io.IOException;

public class ParinteHomePage {

    @FXML
    private Button butonVizualizareNote;
    @FXML
    private Button butonAnunturi;
    @FXML
    private Button butonProfesori;
    @FXML
    private Button butonDeconectare;
    @FXML
    private User user;
    public void setUser(User user)
    {
        this.user=user;
    }
    public void handleVizualizareNote(){}
    public void handleAnunturi() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ParintiAnunturi.fxml"));
            Parent root= loader.load();
            Stage stage = (Stage) (butonAnunturi.getScene().getWindow());
            stage.setScene(new Scene(root));
            ParintiVizualizareAnunturi vizualizare = loader.getController();
            vizualizare.setUser(user);
            vizualizare.initializare();
            stage.show();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void handleProfesori() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("listaprofesori.fxml"));
            Stage stage = (Stage) (butonProfesori.getScene().getWindow());
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
