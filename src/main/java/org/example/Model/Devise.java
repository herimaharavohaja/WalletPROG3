package org.example.Model;

import java.util.Objects;

public class Devise {
    private int Id;
    private String nom;
    private String code;

    @Override
    public String toString() {
        return "Devise{" +
                "Id=" + Id +
                ", nom='" + nom + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Devise devise)) return false;
        return Id == devise.Id && Objects.equals(nom, devise.nom) && Objects.equals(code, devise.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, nom, code);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
