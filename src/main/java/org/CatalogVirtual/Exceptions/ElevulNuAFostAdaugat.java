package org.CatalogVirtual.Exceptions;

public class ElevulNuAFostAdaugat extends Exception{
    private String numeElev;
    public ElevulNuAFostAdaugat (String numeElev){
        super(String.format("Elevul nu a fost adaugat la aceasta materie!",numeElev));
    }
}
