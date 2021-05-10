package org.CatalogVirtual.model;

public class Parinte{
    private String numeElev;
    private String numeParinte;

    public Parinte(String numeParinte, String numeElev) {
        this.numeElev = numeElev;
        this.numeParinte = numeParinte;
    }
    public Parinte(){}

    public String getNumeElev() {
        return numeElev;
    }

    public void setNumeElev(String numeElev) {
        this.numeElev = numeElev;
    }

    public String getNumeParinte() {
        return numeParinte;
    }

    public void setNumeParinte(String numeParinte) {
        this.numeParinte = numeParinte;
    }
}
