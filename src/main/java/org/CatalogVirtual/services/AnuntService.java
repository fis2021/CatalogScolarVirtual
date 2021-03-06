package org.CatalogVirtual.services;

import org.CatalogVirtual.Exceptions.ParinteleNuExista;
import org.CatalogVirtual.model.Anunt;
import org.CatalogVirtual.model.Materie;
import org.CatalogVirtual.model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.List;

import static org.CatalogVirtual.services.FileSystemService.getPathToFile;

public class AnuntService {
    private static ObjectRepository<Anunt> anuntRepository;
    public static Nitrite database;
    public static void initDatabase() {
        FileSystemService.initDirectory();
        database = Nitrite.builder()
                .filePath(getPathToFile("Baza_de_date_anunturi.db").toFile())
                .openOrCreate("test1", "test1");

        anuntRepository = database.getRepository(Anunt.class);

    }
    public static void addAnunt(String mesaj,String numeProfesor,String numeParinte) throws ParinteleNuExista{
        VerificaParinte(numeParinte);
        anuntRepository.insert(new Anunt(mesaj,numeProfesor,numeParinte));
    }

    private static void VerificaParinte(String numeParinte) throws ParinteleNuExista{
        int exista=0;
        for (User user:UserService.getAllUsers()){
            if(numeParinte.equals(user.getNume()+" "+user.getPrenume())&&user.getRole().equals("Parinte"))
            {
                exista=1;
                break;
            }
        }
        if(exista==0)
            throw new ParinteleNuExista(numeParinte);

    }
    public static Nitrite getDatabase(){
        return database;
    }
    public static List<Anunt> getAllAnunturi(){
        return anuntRepository.find().toList();
    }
}
