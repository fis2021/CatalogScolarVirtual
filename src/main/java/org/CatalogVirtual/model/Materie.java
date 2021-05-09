package org.CatalogVirtual.model;

import org.dizitart.no2.objects.Id;

import java.util.ArrayList;
import java.util.HashMap;

public class Materie {
    private String numeProfesor;
    @Id
    private String numeMaterie;
    private String[] elevi=new String[1];

    private int contor=0;
    //private ArrayList<String> elevi;


    public Materie(String numeMaterie,String numeProfesor){
        this.numeMaterie=numeMaterie;
        this.numeProfesor=numeProfesor;
        //elevi=new ArrayList<String>(10);

    }
    public int getContor(){
        return contor;
    }
    public Materie(){}

    public void setNumeMaterie(String numeMaterie) {
        this.numeMaterie = numeMaterie;
    }
    public void setNumeProfesor(String numeProfesor){
        this.numeProfesor=numeProfesor;
    }
    public String getNumeProfesor(){
        return numeProfesor;
    }
    public String getNumeMaterie(){
        return numeMaterie;
    }
   public String[] getelevi(){ return elevi;}

   /* public void addElev(String numeElev){
        elevi.add(numeElev);
    }*/
    public void addElev(String numeElev){
        if(contor==elevi.length){
            String[] aux=new String[2*contor];
            int i;
            for(i=0;i<contor;i++){
                aux[i]=elevi[i];

            }
            elevi=aux;

        }
        elevi[contor++]=numeElev;
    }
    public boolean verificaElev(String numeElev)
    {
        int i;
        for(i=0;i<contor;i++)
        {
            if(numeElev.equals(elevi[i]))
            {
                return true;
            }
        }
        return false;
    }



}
