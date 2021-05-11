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

public class VizualizareMaterii {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private Button Nota;
    @FXML
    private Button Medie;
    @FXML
    private Button Absente;

    public void handleNote() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("VizualizareNote.fxml"));
            Parent root= loader.load();
            Stage stage = (Stage) (Nota.getScene().getWindow());
            stage.setScene(new Scene(root));
            Note nota = loader.getController();
            nota.setUser(user);
            nota.setTableView();
            stage.show();

        }
        catch (IOException e)
        {
            System.out.println("eroare");
        }
    }
    public void handleAbsente() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("VizualizareAbsente.fxml"));
            Parent root= loader.load();
            Stage stage = (Stage) (Nota.getScene().getWindow());
            stage.setScene(new Scene(root));
            Absente nota = loader.getController();
            nota.setUser(user);
            nota.setTableView();
            stage.show();

        }
        catch (IOException e)
        {
            System.out.println("eroare");
        }
    }
    public void handleMedie() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("VizualizareMedii.fxml"));
            Parent root= loader.load();
            Stage stage = (Stage) (Medie.getScene().getWindow());
            stage.setScene(new Scene(root));
            Medie medie = loader.getController();
            medie.setUser(user);
            medie.setareMedii();

            stage.show();

        }
        catch (IOException e)
        {
            System.out.println("eroare");
        }
    }



}
