package org.example.Model;

import java.util.Objects;

public class Devise {
    private int Id;
    private String nom;
    private String code;
    private String Type;

    @Override
    public String toString() {
        return "Devise{" +
                "Id=" + Id +
                ", nom='" + nom + '\'' +
                ", code='" + code + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Devise devise)) return false;
        return Id == devise.Id && Objects.equals(nom, devise.nom) && Objects.equals(code, devise.code) && Objects.equals(Type, devise.Type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, nom, code, Type);
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
