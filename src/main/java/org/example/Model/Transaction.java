package org.example.Model;

import java.util.Date;
import java.util.Objects;

public class Transaction {
    private int identifiant;
    private String label;
    private double montant;
    private Date dateTransaction;
    private String typeTransaction;

    @Override
    public String toString() {
        return "Transaction{" +
                "identifiant=" + identifiant +
                ", label='" + label + '\'' +
                ", montant=" + montant +
                ", dateTransaction=" + dateTransaction +
                ", typeTransaction='" + typeTransaction + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return identifiant == that.identifiant && Double.compare(that.montant, montant) == 0 && Objects.equals(label, that.label) && Objects.equals(dateTransaction, that.dateTransaction) && Objects.equals(typeTransaction, that.typeTransaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiant, label, montant, dateTransaction, typeTransaction);
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }
}
