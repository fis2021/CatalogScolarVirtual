package org.CatalogVirtual.services;

import org.CatalogVirtual.Exceptions.ElevulDejaExista;
import org.CatalogVirtual.Exceptions.ElevulNuExista;
import org.CatalogVirtual.model.Materie;
import org.CatalogVirtual.model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.List;
import java.util.Objects;

import static org.CatalogVirtual.services.FileSystemService.getPathToFile;

public class MaterieService {
    private static ObjectRepository<Materie> materieRepository;
    private static Nitrite database;
    public static void initDatabase() {
        FileSystemService.initDirectory();
        database = Nitrite.builder()
                .filePath(getPathToFile("Baza_de_date_materie.db").toFile())
                .openOrCreate("test1", "test1");

        materieRepository = database.getRepository(Materie.class);
    }

    public static void addMaterie(String numeMaterie,String numeProfesor){
        materieRepository.insert(new Materie(numeMaterie,numeProfesor));
    }
    private static void VerificaDacaElevulAFostDejaAdaugat(String numeElev,String numeMaterie,String numeProfesor) throws ElevulDejaExista {
        for(Materie materie:materieRepository.find()){
            int i;
            if(Objects.equals(numeMaterie,materie.getNumeMaterie())&&Objects.equals(numeProfesor,materie.getNumeProfesor())){
                    for(i=0;i<materie.getelevi().length;i++){
                        if(Objects.equals(materie.getelevi()[i],numeElev)){
                            throw new ElevulDejaExista(numeElev);
                        }
                }
            }
        }

    }

    private static void VerificaDacaElevulNuExista(String numeElev) throws ElevulNuExista {
        int exista=0;
        for(User user:UserService.getAllUsers()){
            if(Objects.equals(user.getNume()+" "+user.getPrenume(),numeElev)&& user.getRole().equals("Elev")){
                exista=1;
                break;
            }


        }
        if(exista==0)
            throw new ElevulNuExista(numeElev);
    }



    public static void addElev(String numeElev,String numeMaterie,String numeProfesor) throws Exception{
        VerificaDacaElevulNuExista(numeElev);
        for(Materie materie:materieRepository.find()){
            if(Objects.equals(numeMaterie,materie.getNumeMaterie())&&Objects.equals(numeProfesor,materie.getNumeProfesor())){
                VerificaDacaElevulAFostDejaAdaugat(numeElev,numeMaterie,numeProfesor);
                materie.addElev(numeElev);
                materieRepository.update(materie);

            }

        }

    }
    public static String getnumeMaterie(String numeProfesor) {
        for (Materie materie : materieRepository.find()) {
            if (Objects.equals(numeProfesor, materie.getNumeProfesor())) {
                return materie.getNumeMaterie();
            }
        }
        return null;
    }
    public static List<Materie> getAllMaterii(){
        return materieRepository.find().toList();
    }
    public static Nitrite getDatabase(){
        return database;
    }
}
