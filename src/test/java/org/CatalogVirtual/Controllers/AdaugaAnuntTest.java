package org.CatalogVirtual.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
class AdaugaAnuntTest {
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
        MaterieService.initDatabase();
        MaterieService.addMaterie("Matematica","Manta Denisa");
        AnuntService.initDatabase();
    }
    @AfterEach
    void tearDown() throws IOException {
        UserService.getDatabase().close();
        MaterieService.getDatabase().close();
        AnuntService.getDatabase().close();

    }
    @Start
    void start(Stage stage)throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("logare.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @Test
    void testAdaugareAnunt(FxRobot robot) {
        robot.clickOn("#usernameField");
        robot.write("denisa");
        robot.clickOn("#passwordField");
        robot.write("123");
        robot.clickOn("#buttonLogare");
        robot.clickOn("#trimitereAnunturi");
        robot.clickOn("#numeParinte");
        robot.write("Voiculescu Daniela");
        robot.clickOn("#mesaj");
        robot.write("Sedinta");
        robot.clickOn("#buttonAdauga");
        assertThat(AnuntService.getAllAnunturi().size()).isEqualTo(1);
    }



}