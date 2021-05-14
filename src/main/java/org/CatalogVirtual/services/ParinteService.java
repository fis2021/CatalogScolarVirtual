package org.CatalogVirtual.services;

import org.CatalogVirtual.Exceptions.ElevulNuExista;
import org.CatalogVirtual.model.Absenta;
import org.CatalogVirtual.model.Parinte;
import org.CatalogVirtual.model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.List;
import java.util.Objects;

import static org.CatalogVirtual.services.FileSystemService.getPathToFile;

public class ParinteService {
    private static ObjectRepository<Parinte> parinteRepository;
    private static final ObjectRepository<User> REPOSITORY= UserService.getUserRepository();
    private static Nitrite database;
    public static void initDatabase() {
        FileSystemService.initDirectory();
         database = Nitrite.builder()
                .filePath(getPathToFile("Baza_de_date_parinti.db").toFile())
                .openOrCreate("test1", "test1");

        parinteRepository = database.getRepository(Parinte.class);

    }
    public static void addParinte(String numeParinte,String numeElev) throws ElevulNuExista{
        VerificaDacaElevulNuExista(numeElev);
        parinteRepository.insert(new Parinte(numeParinte,numeElev));
    }
    private static void VerificaDacaElevulNuExista(String numeElev) throws ElevulNuExista {
        int exista=0;
        for(User user:REPOSITORY.find()){
            if(Objects.equals(user.getNume()+" "+user.getPrenume(),numeElev)&& user.getRole().equals("Elev")){
                exista=1;
                break;
            }


        }
        if(exista==0)
            throw new ElevulNuExista(numeElev);
    }
    public static String getElev(String numeParinte){
        for(Parinte parinte:parinteRepository.find()){
            if(parinte.getNumeParinte().equals(numeParinte))
            {
                return parinte.getNumeElev();
            }
        }
        return null;
    }
    public static List<Parinte> getAllUsers() {
        return parinteRepository.find().toList();
    }
    public static Nitrite getDatabase(){
        return database;
    }
}
