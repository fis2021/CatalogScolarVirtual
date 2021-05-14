package org.CatalogVirtual.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
class LogareTest {
    private Logare logare;


    @AfterAll
    static void afterAll() throws TimeoutException {
        FxToolkit.cleanupStages();

    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-database";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        UserService.addUser("denisa","123","Manta","Denisa","Profesor","0786554331","denisa@gmail.com");
        MaterieService.initDatabase();
        NoteService.initDatabase();
    }


    @AfterEach
    void tearDown() {
        UserService.getDatabase().close();
        MaterieService.getDatabase().close();
        NoteService.getDatabase().close();

    }

    @Start
    void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("logare.fxml"));
        Parent root= loader.load();
        stage.setScene(new Scene(root));
        stage.show();


    }
    @Test
    void testLogare(FxRobot robot){
        robot.clickOn("#usernameField");
        robot.write("denisa");
        robot.clickOn("#passwordField");
        robot.write("123");
        robot.clickOn("#buttonLogare");




    }

}