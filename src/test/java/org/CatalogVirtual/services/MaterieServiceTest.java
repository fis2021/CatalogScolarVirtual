package org.CatalogVirtual.services;

import org.CatalogVirtual.Exceptions.ElevulDejaExista;
import org.CatalogVirtual.Exceptions.ElevulNuExista;
import org.CatalogVirtual.model.Materie;
import org.CatalogVirtual.model.User;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MaterieServiceTest {
    @AfterEach
    void tearDown() {
        MaterieService.getDatabase().close();

    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-database";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        MaterieService.initDatabase();
        UserService.addUser("daniela","123","Voiculescu","Daniela","Elev","0762364898","daniela@yahoo.com");
        UserService.addUser("adina","123","Voiculescu","Daniela","Parinte","0768235681","adina@yahoo.com");

    }
    @Test
    @DisplayName("Verifica daca baza de date nu exista sau este goala")
    void testDataBaseIsInitializedAndNoUserIsPersisted(){
        assertThat(MaterieService.getAllMaterii()).isNotNull();
        assertThat(MaterieService.getAllMaterii()).isEmpty();
    }
    @Test
    @DisplayName("Materia a fost adaugata cu succes")
    void testMateriaAFostAdaugat() {


       MaterieService.addMaterie("Matematica","Tuhasu Adina");
       assertThat(MaterieService.getAllMaterii()).isNotEmpty();
       assertThat(MaterieService.getAllMaterii()).size().isEqualTo(1);
       Materie materie = MaterieService.getAllMaterii().get(0);
       assertThat(materie).isNotNull();
       assertThat(materie.getNumeMaterie()).isEqualTo("Matematica");
       assertThat(materie.getNumeProfesor()).isEqualTo("Tuhasu Adina");
       assertThat(materie.getContor()).isEqualTo(0);
    }
    @Test
    @DisplayName("Elevul a fost deja adaugat")
    void testElevulNuExita() throws Exception {
        MaterieService.addMaterie("Matematica","Tuhasu Adina");
        MaterieService.addElev("Voiculescu Daniela","Matematica","Tuhasu Adina");
        assertThrows(ElevulDejaExista.class, () -> {
            MaterieService.addElev("Voiculescu Daniela","Matematica","Tuhasu Adina");
        });
    }
    @Test
    @DisplayName("Verifica daca se adauga corect un elev")
    void testaddElev() throws Exception{
        MaterieService.addMaterie("Matematica","Tuhasu Adina");
        MaterieService.addElev("Voiculescu Daniela","Matematica","Tuhasu Adina");
        assertThat(MaterieService.getAllMaterii()).isNotEmpty();
        assertThat(MaterieService.getAllMaterii()).size().isEqualTo(1);
        Materie materie = MaterieService.getAllMaterii().get(0);
        assertThat(materie).isNotNull();
        assertThat(materie.getNumeMaterie()).isEqualTo("Matematica");
        assertThat(materie.getNumeProfesor()).isEqualTo("Tuhasu Adina");
        assertThat(materie.getContor()).isEqualTo(1);
        assertThat(materie.getelevi()[0]).isEqualTo("Voiculescu Daniela");
    }
}