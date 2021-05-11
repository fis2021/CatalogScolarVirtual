package org.CatalogVirtual.model;

public class Nota {
    private Data data;
    private Integer value;
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

    public Data getData(){
        return data;
    }
    public String getMaterie(){
        return materie;
    }

    public String getNumeProfesor() {
        return numeProfesor;
    }

    public void setNumeProfesor(String numeProfesor) {
        this.numeProfesor = numeProfesor;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setMaterie(String materie){
        this.materie=materie;
    }
    public void setData(Data data){
        this.data=data;
    }

    public String getNumeElev() {
        return numeElev;
    }

    public void setNumeElev(String numeElev) {
        this.numeElev = numeElev;
    }
}
