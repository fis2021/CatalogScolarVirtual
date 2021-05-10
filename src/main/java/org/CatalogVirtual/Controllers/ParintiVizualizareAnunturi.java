package org.CatalogVirtual.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.CatalogVirtual.model.Anunt;
import org.CatalogVirtual.model.Materie;
import org.CatalogVirtual.model.User;
import org.CatalogVirtual.services.AnuntService;
import org.CatalogVirtual.services.MaterieService;
import org.dizitart.no2.objects.ObjectRepository;

public class ParintiVizualizareAnunturi {
    private static final ObjectRepository<Anunt> REPOSITORY = AnuntService.getAnuntRepository();
    @FXML
    private Text anunturi;
    @FXML
    private Button butonInapoi;
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void initializare(){
        String t="";
        for(Anunt anunt:REPOSITORY.find()){
            if(anunt.getNumeParinte().equals(user.getNume()+" "+user.getPrenume())){
                t=t+anunt.getNumeProfesor()+"-"+MaterieService.getnumeMaterie(anunt.getNumeProfesor())+":";
                t=t+anunt.getMesaj();
                t=t+"\n";
            }
            anunturi.setText(t);
        }
    }
    public void handleInapoi() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("parinteHomePage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) (butonInapoi.getScene().getWindow());
            stage.setScene(new Scene(root));
            ParinteHomePage parinte = loader.getController();
            parinte.setUser(user);
            stage.show();
        } catch (Exception e) {
            System.out.println("Eroare");
        }
    }
}
