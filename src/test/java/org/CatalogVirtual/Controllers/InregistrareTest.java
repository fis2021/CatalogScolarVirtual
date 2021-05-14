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
    class InregistrareTest {
        private Inregistrare inregistrare;


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
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("inregistrare.fxml"));
            Parent root= loader.load();
            stage.setScene(new Scene(root));
            stage.show();



        }
        @Test
        void testCreareContElev(FxRobot robot){
            robot.clickOn("#Nume");
            robot.write("Manta ");
            robot.clickOn("#Prenume");
            robot.write("Denisa");
            robot.clickOn("#usernameField");
            robot.write("denisa");
            robot.clickOn("#passwordField");
            robot.write("123");
            robot.clickOn("#role");
            robot.type(KeyCode.DOWN);
            robot.type(KeyCode.ENTER);
            robot.clickOn("#nrTel");
            robot.write("0785436772");
            robot.clickOn("#adresaEmail");
            robot.write("denisa@gmail.com");
            robot.clickOn("#buttonInregistrare");
            assertThat(UserService.getAllUsers().size()).isEqualTo(1);



        }
        @Test
        void testCreareContProfesor(FxRobot robot){
            robot.clickOn("#Nume");
            robot.write("Manta ");
            robot.clickOn("#Prenume");
            robot.write("Denisa");
            robot.clickOn("#usernameField");
            robot.write("denisa");
            robot.clickOn("#passwordField");
            robot.write("123");
            robot.clickOn("#role");
            robot.type(KeyCode.ENTER);
            robot.clickOn("#nrTel");
            robot.write("0785436772");
            robot.clickOn("#adresaEmail");
            robot.write("denisa@gmail.com");
            robot.clickOn("#buttonInregistrare");
            assertThat(UserService.getAllUsers().size()).isEqualTo(1);



        }
        @Test
        void testCreareContParinte(FxRobot robot) {
            robot.clickOn("#Nume");
            robot.write("Manta ");
            robot.clickOn("#Prenume");
            robot.write("Denisa");
            robot.clickOn("#usernameField");
            robot.write("denisa");
            robot.clickOn("#passwordField");
            robot.write("123");
            robot.clickOn("#role");
            robot.type(KeyCode.DOWN);
            robot.type(KeyCode.DOWN);
            robot.type(KeyCode.ENTER);

            robot.clickOn("#nrTel");
            robot.write("0785436772");
            robot.clickOn("#adresaEmail");
            robot.write("denisa@gmail.com");
            robot.clickOn("#buttonInregistrare");
            assertThat(UserService.getAllUsers().size()).isEqualTo(1);
        }
        }

