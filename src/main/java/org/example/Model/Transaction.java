package org.example.Model;

import java.util.Date;
import java.util.Objects;

public class Transaction {
    private int Id;
    private String label;
    private double montant;
    private Date dateTransaction;
    private String typeTransaction;
    private Account account;

    @Override
    public String toString() {
        return "Transaction{" +
                "Id=" + Id +
                ", label='" + label + '\'' +
                ", montant=" + montant +
                ", dateTransaction=" + dateTransaction +
                ", typeTransaction='" + typeTransaction + '\'' +
                ", account=" + account +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return Id == that.Id && Double.compare(that.montant, montant) == 0 && Objects.equals(label, that.label) && Objects.equals(dateTransaction, that.dateTransaction) && Objects.equals(typeTransaction, that.typeTransaction) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, label, montant, dateTransaction, typeTransaction, account);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
