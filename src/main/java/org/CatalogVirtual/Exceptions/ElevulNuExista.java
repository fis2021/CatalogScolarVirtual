package org.CatalogVirtual.Exceptions;

public class ElevulNuExista extends Exception {
    private String numeElev;
    public ElevulNuExista(String numeElev){
        super(String.format("Elevul nu poate fi gasit!",numeElev));
        this.numeElev=numeElev;
    }
}
