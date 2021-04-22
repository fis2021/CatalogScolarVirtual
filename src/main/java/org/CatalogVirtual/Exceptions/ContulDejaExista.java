package org.CatalogVirtual.Exceptions;

public class ContulDejaExista extends Exception{
    private String username;

    public ContulDejaExista(String username) {
        super(String.format("A fost deja creat un cont cu acest nume de utilizator!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
