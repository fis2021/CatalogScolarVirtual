package org.CatalogVirtual.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.CatalogVirtual.model.Nota;
import org.CatalogVirtual.model.User;
import org.CatalogVirtual.services.NoteService;
import org.CatalogVirtual.services.ParinteService;
import org.dizitart.no2.objects.ObjectRepository;


public class Medie {
    private User user;


    public void setUser(User user) {
        this.user = user;
    }
    @FXML
    private Text medii;
    @FXML
    private Text MedieAnuala;
    @FXML
    private Button backButton;


    public void setareMedii(){
        int notaMatematica=0;
        int nrNoteMatemetica=0;
        int notaInformatica=0;
        int nrNoteInformatica=0;
        int notaFizica=0;
        int nrNoteFizica=0;
        int notaGeografie=0;
        int nrNoteGeografie=0;
        int notaEngleza=0;
        int nrNoteEngleza=0;
        String matematica="Matematica:";
        String informatica="Informatica:";
        String fizica="Fizica:";
        String geografie="Geografie:";
        String engleza="Engleza:";
        String numeElev;
        if(user.getRole().equals("Elev")){
            numeElev=user.getNume()+" "+user.getPrenume();
        }
        else {
            numeElev= ParinteService.getElev(user.getNume()+" "+user.getPrenume());
        }
        double medieMatematica,medieInformatica,medieFizica,medieGeografie,medieEngleza;
        double medieAnuala;
        for(Nota nota:NoteService.getAllNotes()){
            if(nota.getMaterie().equals("Matematica" )&& nota.getNumeElev().equals(numeElev)){
                notaMatematica=notaMatematica+ nota.getValue();
                nrNoteMatemetica++;
            }
            if(nota.getMaterie().equals("Informatica" )&& nota.getNumeElev().equals(numeElev)){
                notaInformatica=notaInformatica+ nota.getValue();
                nrNoteInformatica++;
            }
            if(nota.getMaterie().equals("Fizica" )&& nota.getNumeElev().equals(numeElev)){
                notaFizica=notaFizica+ nota.getValue();
                nrNoteFizica++;
            }
            if(nota.getMaterie().equals("Geografie" )&& nota.getNumeElev().equals(numeElev)){
                notaGeografie=notaGeografie+ nota.getValue();
                nrNoteGeografie++;
            }
            if(nota.getMaterie().equals("Engleza" )&& nota.getNumeElev().equals(numeElev)){
                notaEngleza=notaEngleza+ nota.getValue();
                nrNoteEngleza++;
            }

        }
        if(nrNoteMatemetica==0)
            medieMatematica=0;
        else {
            medieMatematica=notaMatematica*1.0/nrNoteMatemetica;
        }
        matematica=matematica+String.valueOf(medieMatematica);

        if(nrNoteInformatica==0)
            medieInformatica=0;
        else {
            medieInformatica=notaInformatica*1.0/nrNoteInformatica;
        }
        informatica=informatica+String.valueOf(medieInformatica);

        if(nrNoteFizica==0)
            medieFizica=0;
        else {
            medieFizica=notaFizica*1.0/nrNoteFizica;
        }
       fizica=fizica+String.valueOf(medieFizica);

        if(nrNoteGeografie==0)
            medieGeografie=0;
        else {
            medieGeografie=notaGeografie*1.0/nrNoteGeografie;
        }
        geografie=geografie+String.valueOf(medieGeografie);

        if(nrNoteEngleza==0)
            medieEngleza=0;
        else {
            medieEngleza=notaEngleza*1.0/nrNoteEngleza;
        }
        engleza=engleza+String.valueOf(medieEngleza);
        medii.setText(matematica+"\n"+informatica+"\n"+fizica+"\n"+geografie+"\n"+engleza+"\n");
        if(medieEngleza==0 || medieFizica==0 || medieMatematica==0 || medieInformatica==0 || medieGeografie==0)
            medieAnuala=0;
        else
            medieAnuala=(medieEngleza+medieFizica+medieMatematica+medieInformatica+medieGeografie)/5;
            MedieAnuala.setText(String.valueOf(medieAnuala));



    }
    public void handleInapoi() throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("VizualizareMaterii.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) (backButton.getScene().getWindow());
            stage.setScene(new Scene(root));
            VizualizareMaterii materii= loader.getController();
            materii.setUser(user);
            stage.show();
        } catch (Exception e) {
            System.out.println("Eroare");
        }
    }

}
