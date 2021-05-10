package org.CatalogVirtual.Exceptions;

public class ParinteleNuExista extends Exception{
    private String numeParinte;

    public ParinteleNuExista(String numeParinte) {
        super(String.format("Nu extista niciun parinte cu acest nume",numeParinte));
    }
}
