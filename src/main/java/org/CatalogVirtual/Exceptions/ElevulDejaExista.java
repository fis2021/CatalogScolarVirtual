package org.CatalogVirtual.Exceptions;

public class ElevulDejaExista extends Exception{
    private String numeElev;
    public ElevulDejaExista(String numeElev){
        super(String.format("Elevul deja este adaugat la aceasta  materie!",numeElev));
        this.numeElev=numeElev;
    }
}
