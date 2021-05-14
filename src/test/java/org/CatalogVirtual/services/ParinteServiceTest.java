package org.CatalogVirtual.services;

import org.CatalogVirtual.Exceptions.ContulDejaExista;
import org.CatalogVirtual.Exceptions.ElevulNuExista;
import org.CatalogVirtual.model.Parinte;
import org.CatalogVirtual.model.User;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.text.Element;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ParinteServiceTest {
    @AfterEach
    void tearDown() {
        ParinteService.getDatabase().close();

    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-database";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        ParinteService.initDatabase();
        UserService.addUser("daniela","123","Voiculescu","Daniela","Elev","0762364898","daniela@yahoo.com");
        UserService.addUser("robi","123","Rosca","Robert","Parinte","0762364898","robi@yahoo.com");
    }
    @Test
    @DisplayName("Verifica daca baza de date nu exista sau este goala")
    void testDataBaseIsInitializedAndNoUserIsPersisted(){
        assertThat(ParinteService.getAllUsers()).isNotNull();
        assertThat(ParinteService.getAllUsers()).isEmpty();
    }
    @Test
    @DisplayName("Parintele a fost adaugat cu succes")
    void testParinteleAFostAdaugat() throws ElevulNuExista {

        ParinteService.addParinte("Rosca Robert","Voiculescu Daniela");
        assertThat(ParinteService.getAllUsers()).isNotEmpty();
        assertThat(ParinteService.getAllUsers()).size().isEqualTo(1);
        Parinte parinte = ParinteService.getAllUsers().get(0);
        assertThat(parinte).isNotNull();
        assertThat(parinte.getNumeParinte()).isEqualTo("Rosca Robert");
        assertThat(parinte.getNumeElev()).isEqualTo("Voiculescu Daniela");
    }
    @Test
    @DisplayName("Elevul nu exista")
    void testElevulNuExita() throws ElevulNuExista{

        assertThrows(ElevulNuExista.class, () -> {
            ParinteService.addParinte("Rosca Robert","Voiculescu Maria");
        });
    }
    @Test
    @DisplayName("Verifica daca se returneaza corect elevul dupa nume")
    void testGetElev() throws ElevulNuExista{
        ParinteService.addParinte("Rosca Robert","Voiculescu Daniela");
        assertThat(ParinteService.getElev("Rosca Robert")).isEqualTo("Voiculescu Daniela");
    }
}