package org.CatalogVirtual.services;



import org.CatalogVirtual.Exceptions.ContulDejaExista;
import org.CatalogVirtual.model.User;
import org.CatalogVirtual.services.FileSystemService;
import org.CatalogVirtual.services.UserService;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.InstanceOfAssertFactories.BYTE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testfx.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
class UserServiceTest {
    @AfterEach
    void tearDown() {
        UserService.getDatabase().close();

    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration-database";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();

    }
    @Test
    @DisplayName("Database is initialized and there are no users")
    void testDataBaseIsInitializedAndNoUserIsPersisted() {
        assertThat(UserService.getAllUsers()).isNotNull();
        assertThat(UserService.getAllUsers()).isEmpty();


    }
    @Test
    @DisplayName("Utilizatorul a fost adaugat cu succes in baza de date")
    void testUserIsAddedToDatabase() throws ContulDejaExista {
        UserService.addUser("daniela","123","Voiculescu","Daniela","Elev","0762364898","daniela@yahoo.com");
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = UserService.getAllUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("daniela");
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword("daniela", "123"));
        assertThat(user.getRole()).isEqualTo("Elev");
        assertThat(user.getNume()).isEqualTo("Voiculescu");
        assertThat(user.getPrenume()).isEqualTo("Daniela");
        assertThat(user.getNrTel()).isEqualTo("0762364898");
        assertThat(user.getAdresaEmail()).isEqualTo("daniela@yahoo.com");
        UserService.addUser("robi","123","Rosca","Robert","Parinte","0762364898","robi@yahoo.com");
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(2);
        User user1 = UserService.getAllUsers().get(1);
        assertThat(user1).isNotNull();
        assertThat(user1.getUsername()).isEqualTo("robi");
        assertThat(user1.getPassword()).isEqualTo(UserService.encodePassword("robi", "123"));
        assertThat(user1.getRole()).isEqualTo("Parinte");
        assertThat(user1.getNume()).isEqualTo("Rosca");
        assertThat(user1.getPrenume()).isEqualTo("Robert");
        assertThat(user1.getNrTel()).isEqualTo("0762364898");
        assertThat(user1.getAdresaEmail()).isEqualTo("robi@yahoo.com");
        UserService.addUser("adina","123","Tuhasu","Adina","Profesor","0762364898","adina@yahoo.com");
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(3);
        User user2 = UserService.getAllUsers().get(2);
        assertThat(user2).isNotNull();
        assertThat(user2.getUsername()).isEqualTo("adina");
        assertThat(user2.getPassword()).isEqualTo(UserService.encodePassword("adina", "123"));
        assertThat(user2.getRole()).isEqualTo("Profesor");
        assertThat(user2.getNume()).isEqualTo("Tuhasu");
        assertThat(user2.getPrenume()).isEqualTo("Adina");
        assertThat(user2.getNrTel()).isEqualTo("0762364898");
        assertThat(user2.getAdresaEmail()).isEqualTo("adina@yahoo.com");

    }
    @Test

    @DisplayName("Utilizatorul deja exista")
    void testUserCanNotBeAddedTwice() {
        assertThrows(ContulDejaExista.class, () -> {
            UserService.addUser("daniela","123","Voiculescu","Daniela","Elev","0762364898","daniela@yahoo.com");
            UserService.addUser("daniela","123","Voiculescu","Daniela","Elev","0762364898","daniela@yahoo.com");
        });

    }
}