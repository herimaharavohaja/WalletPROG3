package org.example.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Account {
    private int Id;
    private String nom;
    private BigDecimal solde;
    private LocalDateTime dateMajSolde;
    private Devise devise;

    @Override
    public String toString() {
        return "Account{" +
                "Id=" + Id +
                ", nom='" + nom + '\'' +
                ", solde=" + solde +
                ", dateMajSolde=" + dateMajSolde +
                ", devise=" + devise +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Id == account.Id && Objects.equals(nom, account.nom) && Objects.equals(solde, account.solde) && Objects.equals(dateMajSolde, account.dateMajSolde) && Objects.equals(devise, account.devise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, nom, solde, dateMajSolde, devise);
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

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    public LocalDateTime getDateMajSolde() {
        return dateMajSolde;
    }

    public void setDateMajSolde(LocalDateTime dateMajSolde) {
        this.dateMajSolde = dateMajSolde;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

}
