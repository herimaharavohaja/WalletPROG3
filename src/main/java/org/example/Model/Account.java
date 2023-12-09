package org.example.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Account {
    private int Id;
    private String nom;
    private double solde;
    private Date dateDerniereMiseAJour;
    private Devise devise;
    private List<Transaction> listeTransactions;

    @Override
    public String toString() {
        return "Account{" +
                "Id=" + Id +
                ", nom='" + nom + '\'' +
                ", solde=" + solde +
                ", dateDerniereMiseAJour=" + dateDerniereMiseAJour +
                ", devise=" + devise +
                ", listeTransactions=" + listeTransactions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Id == account.Id && Double.compare(account.solde, solde) == 0 && Objects.equals(nom, account.nom) && Objects.equals(dateDerniereMiseAJour, account.dateDerniereMiseAJour) && Objects.equals(devise, account.devise) && Objects.equals(listeTransactions, account.listeTransactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, nom, solde, dateDerniereMiseAJour, devise, listeTransactions);
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

    public double getSoldeA(Date dateHeure) {
        double soldeA = solde;

        if (listeTransactions != null) {
            for (Transaction transaction : listeTransactions) {
                if (transaction.getDateTransaction().before(dateHeure) || transaction.getDateTransaction().equals(dateHeure)) {
                    if (transaction.getTypeTransaction().equalsIgnoreCase("crédit")) {
                        soldeA += transaction.getMontant();
                    } else if (transaction.getTypeTransaction().equalsIgnoreCase("débit")) {
                        soldeA -= transaction.getMontant();
                    }
                } else {
                    break;
                }
            }
        }

        return soldeA;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
    public double getSolde() {
        return solde;
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

    public List<Transaction> getListeTransactions() {
        return listeTransactions;
    }

    public void setListeTransactions(List<Transaction> listeTransactions) {
        this.listeTransactions = listeTransactions;
    }
    public Account effectuerTransaction(Transaction transaction) {
        transaction.setDateTransaction(new Date());
        if (transaction.getTypeTransaction().equalsIgnoreCase("débit")) {
            if (transaction.getMontant() > solde) {
                System.out.println("Solde insuffisant pour effectuer cette transaction.");
                return this;
            } else {
                solde -= transaction.getMontant();
            }
        } else if (transaction.getTypeTransaction().equalsIgnoreCase("crédit")) {
            solde += transaction.getMontant();
        } else {
            System.out.println("Type de transaction non valide.");
            return this;
        }
        if (listeTransactions == null) {
            listeTransactions = new ArrayList<>();
        }
        listeTransactions.add(transaction);
        dateDerniereMiseAJour = new Date();
        return this;
    }



}
