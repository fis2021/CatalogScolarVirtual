package org.CatalogVirtual.services;

import org.CatalogVirtual.Exceptions.ContulNuExista;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.CatalogVirtual.Exceptions.ContulDejaExista;
import org.CatalogVirtual.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.CatalogVirtual.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }
    public static User login(String username, String password) throws ContulNuExista{
        User crt;

        crt = attemptLogin(username, password);

        if(crt == null){
            throw new ContulNuExista(username);
        }

        return crt;
    }

    public static User attemptLogin(String username, String password){
        for (User user : userRepository.find()) {
            if(Objects.equals(username, user.getUsername()) && Objects.equals(encodePassword(username, password), user.getPassword())){
                return user;
            }
        }

        return null;
    }
    public static void addUser(String username, String password,String Nume,String Prenume,String role,String nrTel,String adresaEmail) throws ContulDejaExista {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password),Nume,Prenume, role,nrTel,adresaEmail));
    }
    private static void checkUserDoesNotAlreadyExist(String username) throws ContulDejaExista {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new ContulDejaExista(username);
        }
    }



    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


    public static ObjectRepository<User> getUserRepository() {
        return userRepository;
    }
}
