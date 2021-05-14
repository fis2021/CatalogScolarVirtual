package org.CatalogVirtual.services;

import org.CatalogVirtual.Exceptions.DataInvalida;
import org.CatalogVirtual.Exceptions.ElevulNuAFostAdaugat;
import org.CatalogVirtual.Exceptions.NotaInvalida;
import org.CatalogVirtual.model.Data;
import org.CatalogVirtual.model.Materie;
import org.CatalogVirtual.model.Nota;
import org.CatalogVirtual.model.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.List;

import static org.CatalogVirtual.services.FileSystemService.getPathToFile;

public class NoteService {

    private static ObjectRepository<Nota> notaRepository;
    private static Nitrite database;
    public static void initDatabase() {
        FileSystemService.initDirectory();
         database = Nitrite.builder()
                .filePath(getPathToFile("note.db").toFile())
                .openOrCreate("test", "test");

        notaRepository = database.getRepository(Nota.class);
    }

    public static void addNota(int value, String numeProfesor, String materie, String numeElev, Data data)throws Exception{
        verificaElevulNuAFostAdaugat(numeElev,materie,numeProfesor);
        verificareNotaValida(value);
        verificareDataValida(data.getZi(),data.getLuna(),data.getAn());
        notaRepository.insert(new Nota(data,value,materie,numeProfesor,numeElev));



    }
    private static void verificareNotaValida(int value)throws NotaInvalida {
        if(value<0 || value>10){
            throw new NotaInvalida(value);
        }
    }
    private static void verificareDataValida(int zi,int luna,int an )throws DataInvalida {
        if(luna<=0 || luna>=13) throw new DataInvalida(zi,luna,an);
        if(luna==1 && (zi<=0 || zi>=32)) throw new DataInvalida(zi,luna,an);
        if(luna==2 && (zi<=0 || zi>=29)) throw new DataInvalida(zi,luna,an);
        if(luna==3 && (zi<=0 || zi>=32)) throw new DataInvalida(zi,luna,an);
        if(luna==4 && (zi<=0 || zi>=31)) throw new DataInvalida(zi,luna,an);
        if(luna==5 && (zi<=0 || zi>=32)) throw new DataInvalida(zi,luna,an);
        if(luna==6 && (zi<=0 || zi>=31)) throw new DataInvalida(zi,luna,an);
        if(luna==7 && (zi<=0 || zi>=32)) throw new DataInvalida(zi,luna,an);
        if(luna==8 && (zi<=0 || zi>=32)) throw new DataInvalida(zi,luna,an);
        if(luna==9 && (zi<=0 || zi>=31)) throw new DataInvalida(zi,luna,an);
        if(luna==10 && (zi<=0 || zi>=32)) throw new DataInvalida(zi,luna,an);
        if(luna==11 && (zi<=0 || zi>=31)) throw new DataInvalida(zi,luna,an);
        if(luna==1 && (zi<=0 || zi>=32)) throw new DataInvalida(zi,luna,an);



    }
    private static final ObjectRepository<Materie> REPOSITORY= MaterieService.getMaterieRepository();
    private static void verificaElevulNuAFostAdaugat(String numeElev,String numeMaterie,String numeProfesor)throws  ElevulNuAFostAdaugat{
        for(Materie materie:REPOSITORY.find()){
            if(materie.getNumeMaterie().equals(numeMaterie) && materie.getNumeProfesor().equals(numeProfesor)){
                int i;
                int exista=0;
                for(i=0;i<materie.getContor();i++){
                    if(materie.getelevi()[i].equals(numeElev)){
                        exista=1;
                    }
                }
                if(exista==0)
                    throw new ElevulNuAFostAdaugat(numeElev);
            }
        }
    }

    public static ObjectRepository<Nota> getNotaRepository() {
        return notaRepository;
    }
    public static List<Nota> getAllNotes(){
        return notaRepository.find().toList();
    }
    public static Nitrite getDatabase(){
        return database;
    }
}
