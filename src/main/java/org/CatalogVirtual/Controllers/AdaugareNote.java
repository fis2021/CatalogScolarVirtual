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
import org.CatalogVirtual.Exceptions.DataInvalida;
import org.CatalogVirtual.Exceptions.ElevulNuAFostAdaugat;
import org.CatalogVirtual.Exceptions.NotaInvalida;
import org.CatalogVirtual.model.Data;
import org.CatalogVirtual.model.Materie;
import org.CatalogVirtual.model.User;
import org.CatalogVirtual.services.MaterieService;
import org.CatalogVirtual.services.NoteService;
import org.dizitart.no2.objects.ObjectRepository;

public class AdaugareNote {
    @FXML
    private Text setareMessage;
    @FXML
    private TextField NumeElev;
    @FXML
    private TextField Nota;
    @FXML
    private TextField zi;
    @FXML
    private TextField luna;
    @FXML
    private TextField an;
    @FXML
    private Button buttonInapoi;
    @FXML
    private Button buttonAdaugare;
    private User user;
    public void setUser(User user){
        this.user=user;

    }
    private static final ObjectRepository<Materie> REPOSITORY= MaterieService.getMaterieRepository();
    public void handleInapoi()throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ProfesorHomePage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) (buttonInapoi.getScene().getWindow());
            stage.setScene(new Scene(root));
            ProfesorHomePage profesor = loader.getController();
            profesor.setUser(user);
            stage.show();
        }catch(Exception e){
            System.out.println("Eroare!");
        }
    }
@FXML
    public void handleSetareNota()throws Exception{
        try{

        int zi1=Integer.parseInt(zi.getText());
        int luna1=Integer.parseInt(luna.getText());
       int an1=Integer.parseInt(an.getText());
        int nota=Integer.parseInt(Nota.getText());
        Data d=new Data(zi1,luna1,an1);
        NoteService.addNota(nota,user.getNume()+" "+user.getPrenume(),MaterieService.getnumeMaterie(user.getNume()+" "+user.getPrenume()),NumeElev.getText(),d);
        setareMessage.setText("Nota a fost adaugata cu succes!");

    } catch(ElevulNuAFostAdaugat e){
            setareMessage.setText(e.getMessage());

        }
        catch(NotaInvalida e){
            setareMessage.setText(e.getMessage());

        }
        catch(DataInvalida e){
            setareMessage.setText(e.getMessage());

        }

    }



}
