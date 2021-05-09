package org.CatalogVirtual.Exceptions;

public class NotaInvalida extends Exception{
    private int value;
    public NotaInvalida(int value){
        super(String.format("Nota nu este valida!",value));
    }
}
