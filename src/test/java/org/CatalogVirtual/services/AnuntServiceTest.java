package org.CatalogVirtual.services;

import org.CatalogVirtual.Exceptions.DataInvalida;
import org.CatalogVirtual.Exceptions.ElevulNuAFostAdaugat;
import org.CatalogVirtual.Exceptions.ParinteleNuExista;
import org.CatalogVirtual.model.Absenta;
import org.CatalogVirtual.model.Anunt;
import org.CatalogVirtual.model.Data;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnuntServiceTest {
    @AfterEach
    void tearDown() {
        AnuntService.getDatabase().close();

    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-database";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        MaterieService.initDatabase();
        UserService.addUser("adina","123","Voiculescu","Daniela","Parinte","0768235681","adina@yahoo.com");
        UserService.addUser("robi","123","Rosca","Robert","Parinte","0789562312","robi@yahoo.com");
        MaterieService.addMaterie("Matematica","Tuhasu Adina");
        AnuntService.initDatabase();
    }
    @Test
    @DisplayName("Verifica daca baza de date nu exista sau este goala")
    void testDataBaseIsInitializedAndNoUserIsPersisted(){
        assertThat(AnuntService.getAllAnunturi()).isNotNull();
        assertThat(AnuntService.getAllAnunturi()).isEmpty();
    }
    @Test
    @DisplayName("Anuntul a fost adaugat")
    void testAnuntulAFostAdaugat() throws Exception {
        AnuntService.addAnunt("Sedinta","Tuhasu Adina","Rosca Robert");
        assertThat(AnuntService.getAllAnunturi()).isNotEmpty();
        assertThat(AnuntService.getAllAnunturi()).size().isEqualTo(1);
        Anunt anunt=AnuntService.getAllAnunturi().get(0);
        assertThat(anunt.getNumeParinte()).isEqualTo("Rosca Robert");
        assertThat(anunt.getNumeProfesor()).isEqualTo("Tuhasu Adina");
        assertThat(anunt.getMesaj()).isEqualTo("Sedinta");
    }



    @Test
    @DisplayName("Parinte nu exista")
    void testParinteleNuExista() throws ParinteleNuExista {
        assertThrows(ParinteleNuExista.class, () -> {
            AnuntService.addAnunt("Sedinta","Tuhasu Adina","Rosca Vasile");
        });
    }
}