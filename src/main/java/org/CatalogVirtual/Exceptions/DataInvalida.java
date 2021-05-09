package org.CatalogVirtual.Exceptions;

public class DataInvalida extends Exception{
    private int zi;
    private int luna;
    private int an;
    public DataInvalida(int zi,int luna,int an ){
        super(String.format("Data nu este valida!",zi,luna,an));
    }

}
