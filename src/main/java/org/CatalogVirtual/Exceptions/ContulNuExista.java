package org.CatalogVirtual.Exceptions;

public class ContulNuExista extends  Exception{
    private String username;
    public ContulNuExista(String username) {
        super(String.format("Contul nu exista!", username));
        this.username = username;
    }
    public String getUsername()
    {
        return username;
    }
}
