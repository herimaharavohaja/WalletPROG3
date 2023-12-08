package org.example.Model;

import java.util.Date;
import java.util.Objects;

public class Account {
    private int Id;
    private String nom;
    private double solde;
    private Date dateDerniereMiseAJour;
    private Devise devise;

    @Override
    public String toString() {
        return "Account{" +
                "Id=" + Id +
                ", nom='" + nom + '\'' +
                ", solde=" + solde +
                ", dateDerniereMiseAJour=" + dateDerniereMiseAJour +
                ", devise=" + devise +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Id == account.Id && Double.compare(account.solde, solde) == 0 && Objects.equals(nom, account.nom) && Objects.equals(dateDerniereMiseAJour, account.dateDerniereMiseAJour) && Objects.equals(devise, account.devise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, nom, solde, dateDerniereMiseAJour, devise);
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

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Date getDateDerniereMiseAJour() {
        return dateDerniereMiseAJour;
    }

    public void setDateDerniereMiseAJour(Date dateDerniereMiseAJour) {
        this.dateDerniereMiseAJour = dateDerniereMiseAJour;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }
}
