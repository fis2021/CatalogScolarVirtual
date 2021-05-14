package org.CatalogVirtual.services;

import org.CatalogVirtual.Exceptions.*;
import org.CatalogVirtual.model.Data;
import org.CatalogVirtual.model.Nota;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NoteServiceTest {
    @AfterEach
    void tearDown() {
        NoteService.getDatabase().close();

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
        MaterieService.addMaterie("Matematica","Tuhasu Adina");
        MaterieService.addElev("Voiculescu Daniela","Matematica","Tuhasu Adina");
        NoteService.initDatabase();
    }
    @Test
    @DisplayName("Verifica daca baza de date nu exista sau este goala")
    void testDataBaseIsInitializedAndNoUserIsPersisted(){
        assertThat(NoteService.getAllNotes()).isNotNull();
        assertThat(NoteService.getAllNotes()).isEmpty();
    }
    @Test
    @DisplayName("Nota a fost adaugata")
    void testNotaAFostAdaugata() throws Exception {
        int zi=12,luna=6,an=2020;
        Data data=new Data(zi,luna,an);
        NoteService.addNota(10,"Tuhasu Adina","Matematica","Voiculescu Daniela",data);
        assertThat(NoteService.getAllNotes()).isNotEmpty();
        assertThat(NoteService.getAllNotes()).size().isEqualTo(1);
        Nota nota=NoteService.getAllNotes().get(0);
        assertThat(nota.getData().getZi()).isEqualTo(12);
        assertThat(nota.getData().getLuna()).isEqualTo(6);
        assertThat(nota.getData().getAn()).isEqualTo(2020);
        assertThat(nota.getNumeElev()).isEqualTo("Voiculescu Daniela");
        assertThat(nota.getMaterie()).isEqualTo("Matematica");
        assertThat(nota.getNumeProfesor()).isEqualTo("Tuhasu Adina");
        assertThat(nota.getValue()).isEqualTo(10);
    }

    @Test
    @DisplayName("Verifica nota valida")
    void testNotaValida() throws NotaInvalida {
        assertThrows(NotaInvalida.class, () -> {
            int zi=13,luna=6,an=2020;
            Data data=new Data(zi,luna,an);
            NoteService.addNota(12,"Tuhasu Adina","Matematica","Voiculescu Daniela",data);
        });

    }
    @Test
    @DisplayName("Verifica data valida")
    void testDataValida() throws DataInvalida {
        assertThrows(DataInvalida.class, () -> {
            int zi=13,luna=14,an=2020;
            Data data=new Data(zi,luna,an);
            NoteService.addNota(10,"Tuhasu Adina","Matematica","Voiculescu Daniela",data);
        });

    }
    @Test
    @DisplayName("Elevul nu a fost adaugat")
    void testElevAdaugat() throws ElevulNuAFostAdaugat{
        assertThrows(ElevulNuAFostAdaugat.class, () -> {
            int zi=13,luna=6,an=2020;
            Data data=new Data(zi,luna,an);
            NoteService.addNota(10,"Tuhasu Adina","Matematica","Popescu Dan",data);
        });
    }
}