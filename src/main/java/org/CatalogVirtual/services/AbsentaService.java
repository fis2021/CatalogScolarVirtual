package org.CatalogVirtual.services;

import org.CatalogVirtual.Exceptions.DataInvalida;
import org.CatalogVirtual.Exceptions.ElevulNuAFostAdaugat;
import org.CatalogVirtual.model.Absenta;
import org.CatalogVirtual.model.Anunt;
import org.CatalogVirtual.model.Data;
import org.CatalogVirtual.model.Materie;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import static org.CatalogVirtual.services.FileSystemService.getPathToFile;

public class AbsentaService {
    private static ObjectRepository<Absenta> absentaRepository;

    public static ObjectRepository<Absenta> getAbsentaRepository() {
        return absentaRepository;
    }

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("Baza_de_date_absente.db").toFile())
                .openOrCreate("test1", "test1");

        absentaRepository = database.getRepository(Absenta.class);

    }

    public static void addAbsenta(String numeElev, String numeProfesor, String numeMaterie, Data data) throws Exception{
       verificaElevulNuAFostAdaugat(numeElev,numeMaterie,numeProfesor);
       verificareDataValida(data.getZi(),data.getLuna(),data.getAn());
        absentaRepository.insert(new Absenta(numeElev,numeProfesor,numeMaterie,data));
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
    private static void verificaElevulNuAFostAdaugat(String numeElev,String numeMaterie,String numeProfesor)throws ElevulNuAFostAdaugat {
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
}
