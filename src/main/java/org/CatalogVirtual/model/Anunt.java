package org.CatalogVirtual.model;


public class Anunt {
    private String mesaj;
    private String numeProfesor;
    private String numeParinte;
    public Anunt(){}
    public Anunt(String mesaj,String numeProfesor,String numeParinte){
        this.mesaj=mesaj;
        this.numeProfesor=numeProfesor;
        this.numeParinte=numeParinte;
    }
    public void setNumeParinte(String numeParinte){
        this.numeParinte=numeParinte;
    }

    public String getNumeParinte() {
        return numeParinte;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public String getNumeProfesor() {
        return numeProfesor;
    }

    public void setNumeProfesor(String numeProfesor) {
        this.numeProfesor = numeProfesor;
    }
}
