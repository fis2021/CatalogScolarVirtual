package org.CatalogVirtual.model;

public class Nota {
    private Data data;
    private int value;
    private String materie;
    private String numeProfesor;
    private String numeElev;
    public Nota(){}

    public Nota(Data data,int value,String materie,String numeProfesor,String numeElev){
        this.data=data;
        this.value=value;
        this.materie=materie;
        this.numeElev=numeElev;
        this.numeProfesor=numeProfesor;
    }

    public String getNumeProfesor() {
        return numeProfesor;
    }

    public void setNumeProfesor(String numeProfesor) {
        this.numeProfesor = numeProfesor;
    }

    public String getNumeElev() {
        return numeElev;
    }

    public void setNumeElev(String numeElev) {
        this.numeElev = numeElev;
    }

    public int getValue(){
        return value;
    }
    public Data getData(){
        return data;
    }
    public String getMaterie(){
        return materie;
    }

    public void setMaterie(String materie){
        this.materie=materie;
    }
    public void setValue(int value){
        this.value=value;
    }
    public void setData(Data data){
        this.data=data;
    }

}
