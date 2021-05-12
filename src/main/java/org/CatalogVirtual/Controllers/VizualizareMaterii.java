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
    @FXML
    private Button backButton;

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
            Absente absente = loader.getController();
            absente.setUser(user);
            absente.setTableView();
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
    public void handleInapoi() throws Exception{
        try{
            if(user.getRole().equals("Elev")){
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("elevHomePage.fxml"));
                Parent root= loader.load();
                Stage stage = (Stage) (backButton.getScene().getWindow());
                stage.setScene(new Scene(root));
               ElevHomePage elev = loader.getController();
                elev.setUser(user);

                stage.show();
            }
            else{
                if(user.getRole().equals("Parinte")){
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("parinteHomePage.fxml"));
                    Parent root= loader.load();
                    Stage stage = (Stage) (backButton.getScene().getWindow());
                    stage.setScene(new Scene(root));
                    ParinteHomePage parinte = loader.getController();
                    parinte.setUser(user);

                    stage.show();
                }
            }

        }catch (Exception e){
            System.out.println("eroare");
        }
    }


}
