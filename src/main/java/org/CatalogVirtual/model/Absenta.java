package org.CatalogVirtual.model;

public class Absenta {
    private String numeElev;
    private String numeProfesor;
    private String numeMaterie;
    private Data data;
    public Absenta(){}
    public Absenta(String numeElev,String numeProfesor,String numeMaterie,Data data){
        this.numeElev=numeElev;
        this.numeProfesor=numeProfesor;
        this.numeMaterie=numeMaterie;
        this.data=data;
    }

    public String getNumeElev() {
        return numeElev;
    }

    public void setNumeElev(String numeElev) {
        this.numeElev = numeElev;
    }

    public String getNumeProfesor() {
        return numeProfesor;
    }

    public void setNumeProfesor(String numeProfesor) {
        this.numeProfesor = numeProfesor;
    }

    public String getNumeMaterie() {
        return numeMaterie;
    }

    public void setNumeMaterie(String numeMaterie) {
        this.numeMaterie = numeMaterie;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
