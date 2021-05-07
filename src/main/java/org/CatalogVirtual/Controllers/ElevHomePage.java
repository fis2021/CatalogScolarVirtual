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

public class ElevHomePage {
    @FXML
    private Button butonProfesori;
    @FXML
    private Button butonSituatieScolara;
    @FXML
    private Button butonDeconectare;
    @FXML
    private User user;

    public void setUser(User user)
    {
        this.user=user;
    }


    public void handleProfesori() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("vizualizareProfesori.fxml"));
            Parent root= loader.load();
            Stage stage = (Stage) (butonProfesori.getScene().getWindow());
            stage.setScene(new Scene(root));
            VizualizareProfesori vizualizare = loader.getController();
            vizualizare.setUser(user);
            vizualizare.setTableView();
            stage.show();
        }
        catch (IOException e)
        {
            System.out.println("eroare");
        }
    }
    public void handleSituatieScolara() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("listanote.fxml"));
            Stage stage = (Stage) (butonSituatieScolara.getScene().getWindow());
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
