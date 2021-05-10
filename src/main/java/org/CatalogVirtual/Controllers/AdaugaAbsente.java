package org.CatalogVirtual.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.CatalogVirtual.Exceptions.DataInvalida;
import org.CatalogVirtual.Exceptions.ElevulNuAFostAdaugat;
import org.CatalogVirtual.model.Data;
import org.CatalogVirtual.model.User;
import org.CatalogVirtual.services.AbsentaService;
import org.CatalogVirtual.services.MaterieService;

public class AdaugaAbsente {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }
    @FXML
    private Text setareMessage;
    @FXML
    private TextField NumeElev;
    @FXML
    private TextField zi;
    @FXML
    private TextField luna;
    @FXML
    private TextField an;
    @FXML
    private Button buttonInapoi;
    @FXML
    private Button buttonAbsenta;
    public void handleInapoi()throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AdaugareNote.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) (buttonInapoi.getScene().getWindow());
            stage.setScene(new Scene(root));
            AdaugareNote adauga = loader.getController();
            adauga.setUser(user);
            stage.show();
        }catch(Exception e){
            System.out.println("Eroare!");
        }
    }
    public void handleAdaugareAbsente()throws  Exception{
        try{
            int zi1=Integer.parseInt(zi.getText());
            int luna1=Integer.parseInt(luna.getText());
            int an1=Integer.parseInt(an.getText());
            Data d=new Data(zi1,luna1,an1);
            AbsentaService.addAbsenta(NumeElev.getText(),user.getNume()+" "+user.getPrenume(), MaterieService.getnumeMaterie(user.getNume()+" "+user.getPrenume()),d);
            setareMessage.setText("Absenta a fost adaugata cu succes!");
        } catch(DataInvalida e){
            setareMessage.setText(e.getMessage());
        }
        catch(ElevulNuAFostAdaugat e){
            setareMessage.setText(e.getMessage());
        }
    }
}
