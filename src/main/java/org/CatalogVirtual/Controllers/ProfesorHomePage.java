package org.CatalogVirtual.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.CatalogVirtual.model.User;
import org.CatalogVirtual.services.MaterieService;

import java.io.IOException;


public class ProfesorHomePage {
    @FXML
    private Button butonAlegereMaterie;
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



    private  String userName;
    private String Materie;
    public void setMaterie( String Materie){this.Materie=Materie;}
    public  void setUser(User user){
        this.user=user;
    }

    public void handleAlegereMaterie() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AlegereMaterie.fxml"));
            Parent root= loader.load();
            Stage stage = (Stage) (butonAlegereMaterie.getScene().getWindow());
            stage.setScene(new Scene(root));
            AlegereMaterie materie = loader.getController();

            materie.setUser(user);
            //profesor.setText(detalii);
            stage.show();
        }
        catch (IOException e)
        {
            System.out.println("eroare");
        }
    }

    public void handleAdaugareNote() throws IOException {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AdaugareNote.fxml"));
                Parent root= loader.load();
                Stage stage = (Stage) (butonAdaugareNote.getScene().getWindow());
                stage.setScene(new Scene(root));
                AdaugareNote adaugare = loader.getController();
                adaugare.setUser(user);
                stage.show();

        }
        catch (IOException e)
        {
            System.out.println("eroare");
        }
    }
    public void handleAdaugareElevi() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AdaugareElevi.fxml"));
            Parent root= loader.load();
            Stage stage = (Stage) (butonAdaugareElevi.getScene().getWindow());
            stage.setScene(new Scene(root));
            AdaugareElevi adaugare = loader.getController();
            adaugare.setUser(user);
            stage.show();}

        catch (IOException e)
        {
            System.out.println("eroare");
        }
    }
    public void handleTrimitereAnunturi() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("trimitereanunturi.fxml"));
            Parent root= loader.load();
            Stage stage = (Stage) (trimitereAnunturi.getScene().getWindow());
            stage.setScene(new Scene(root));
            AdaugaAnunt adaugare = loader.getController();
            adaugare.setUser(user);
            stage.show();;
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
    public User getUser(){
        return user;
    }

}
