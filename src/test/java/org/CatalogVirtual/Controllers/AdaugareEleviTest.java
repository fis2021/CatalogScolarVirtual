package org.CatalogVirtual.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.CatalogVirtual.services.FileSystemService;
import org.CatalogVirtual.services.MaterieService;
import org.CatalogVirtual.services.NoteService;
import org.CatalogVirtual.services.UserService;
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
class AdaugareEleviTest {
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
        UserService.addUser("daniela","123","Voiculescu","Daniela","Elev","0762364898","daniela@yahoo.com");
        MaterieService.initDatabase();
        MaterieService.addMaterie("Informatica","Manta Denisa");


    }
    @AfterEach
    void tearDown() throws IOException {
        UserService.getDatabase().close();
        MaterieService.getDatabase().close();

    }
    @Start
    void start(Stage stage)throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("logare.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Test
    void testAdaugareElevi(FxRobot robot) {
        robot.clickOn("#usernameField");
        robot.write("denisa");
        robot.clickOn("#passwordField");
        robot.write("123");
        robot.clickOn("#buttonLogare");
        robot.clickOn("#butonAdaugareElevi");
        robot.clickOn("#numeElev");
        robot.write("Voiculescu Daniela");
        robot.clickOn("#AdaugareButon");
        assertThat(MaterieService.getAllMaterii().size()).isEqualTo(1);
    }



}