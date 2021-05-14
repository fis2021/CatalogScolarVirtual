package org.CatalogVirtual.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.CatalogVirtual.model.Data;
import org.CatalogVirtual.model.User;
import org.CatalogVirtual.services.*;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class NoteTest {
    @AfterAll
    static void afterAll()throws TimeoutException {
        FxToolkit.cleanupStages();

    }
    @BeforeEach
    void setUp()throws Exception{
        FileSystemService.APPLICATION_FOLDER = ".test-application-database";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.addUser("denisa","123","Manta","Denisa","Profesor","0786554331","denisa@gmail.com");
        UserService.addUser("daniela","123","Voiculescu","Daniela","Parinte","0762364898","daniela@yahoo.com");
        UserService.addUser("adina","123","Tuhasu","Adina","Elev","0789364521","adina@yahoo.com");
        MaterieService.initDatabase();
        MaterieService.addMaterie("Matematica","Manta Denisa");
        MaterieService.addElev("Tuhasu Adina","Matematica","Manta Denisa");
        NoteService.initDatabase();
        NoteService.addNota(10,"Manta Denisa","Matematica","Tuhasu Adina",new Data(10,10,2020));
        ParinteService.initDatabase();
        ParinteService.addParinte("Voiculescu Daniela","Tuhasu Adina");
        AbsentaService.initDatabase();
        AbsentaService.addAbsenta("Tuhasu Adina","Manta Denisa","Matematic",new Data(10,10,2020));
    }
    @AfterEach
    void tearDown() throws IOException {
        UserService.getDatabase().close();
        MaterieService.getDatabase().close();
        NoteService.getDatabase().close();
        ParinteService.getDatabase().close();
        AbsentaService.getDatabase().close();
    }
    @Start
    void start(Stage stage)throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("logare.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @Test
    void testVizualizareNote(FxRobot robot) {
        robot.clickOn("#usernameField");
        robot.write("daniela");
        robot.clickOn("#passwordField");
        robot.write("123");
        robot.clickOn("#buttonLogare");
        robot.clickOn("#butonVizualizareNote");
        robot.clickOn("#Nota");
        //assertThat(AnuntService.getAllAnunturi().size()).isEqualTo(1);
    }
    @Test
    void testVizualizareAbsente(FxRobot robot) {
        robot.clickOn("#usernameField");
        robot.write("daniela");
        robot.clickOn("#passwordField");
        robot.write("123");
        robot.clickOn("#buttonLogare");
        robot.clickOn("#butonVizualizareNote");
        robot.clickOn("#Absente");
        //assertThat(AnuntService.getAllAnunturi().size()).isEqualTo(1);
    }
    @Test
    void testVizualizareMedii(FxRobot robot) {
        robot.clickOn("#usernameField");
        robot.write("daniela");
        robot.clickOn("#passwordField");
        robot.write("123");
        robot.clickOn("#buttonLogare");
        robot.clickOn("#butonVizualizareNote");
        robot.clickOn("#Medie");
        //assertThat(AnuntService.getAllAnunturi().size()).isEqualTo(1);
    }


}