package org.CatalogVirtual.services;

import org.CatalogVirtual.Exceptions.DataInvalida;
import org.CatalogVirtual.Exceptions.ElevulNuAFostAdaugat;
import org.CatalogVirtual.Exceptions.NotaInvalida;
import org.CatalogVirtual.model.Absenta;
import org.CatalogVirtual.model.Data;
import org.CatalogVirtual.model.Nota;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AbsentaServiceTest {
    @AfterEach
    void tearDown() throws IOException {
        AbsentaService.getDatabase().close();
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
        AbsentaService.initDatabase();
    }
    @Test
    @DisplayName("Verifica daca baza de date nu exista sau este goala")
    void testDataBaseIsInitializedAndNoUserIsPersisted(){
        assertThat(AbsentaService.getAllAbstente()).isNotNull();
        assertThat(AbsentaService.getAllAbstente()).isEmpty();
    }
    @Test
    @DisplayName("Absenta a fost adaugata")
    void testAbsentaAFostAdaugata() throws Exception {
        int zi=12,luna=6,an=2020;
        Data data=new Data(zi,luna,an);
        AbsentaService.addAbsenta("Voiculescu Daniela","Tuhasu Adina","Matematica",data);
        assertThat(AbsentaService.getAllAbstente()).isNotEmpty();
        assertThat(AbsentaService.getAllAbstente()).size().isEqualTo(1);
        Absenta absenta=AbsentaService.getAllAbstente().get(0);
        assertThat(absenta.getData().getZi()).isEqualTo(12);
        assertThat(absenta.getData().getLuna()).isEqualTo(6);
        assertThat(absenta.getData().getAn()).isEqualTo(2020);
        assertThat(absenta.getNumeElev()).isEqualTo("Voiculescu Daniela");
        assertThat(absenta.getNumeMaterie()).isEqualTo("Matematica");
        assertThat(absenta.getNumeProfesor()).isEqualTo("Tuhasu Adina");

    }


    @Test
    @DisplayName("Verifica data valida")
    void testDataValida() throws DataInvalida {
        assertThrows(DataInvalida.class, () -> {
            int zi=13,luna=14,an=2020;
            Data data=new Data(zi,luna,an);
            AbsentaService.addAbsenta("Voiculescu Daniela","Tuhasu Adina","Matematica",data);
        });

    }
    @Test
    @DisplayName("Elevul nu a fost adaugat")
    void testElevAdaugat() throws ElevulNuAFostAdaugat {
        assertThrows(ElevulNuAFostAdaugat.class, () -> {
            int zi=13,luna=6,an=2020;
            Data data=new Data(zi,luna,an);
            AbsentaService.addAbsenta("Voiculescu Maria","Tuhasu Adina","Matematica",data);
        });
    }

}