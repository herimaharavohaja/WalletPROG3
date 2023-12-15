package org.example.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Transaction {
    private int Id;
    private String label;
    private BigDecimal montant;
    private LocalDateTime dateHeure;
    private String type;
    private Account account;

    @Override
    public String toString() {
        return "Transaction{" +
                "Id=" + Id +
                ", label='" + label + '\'' +
                ", montant=" + montant +
                ", dateHeure=" + dateHeure +
                ", type='" + type + '\'' +
                ", account=" + account +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return Id == that.Id && Objects.equals(label, that.label) && Objects.equals(montant, that.montant) && Objects.equals(dateHeure, that.dateHeure) && Objects.equals(type, that.type) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, label, montant, dateHeure, type, account);
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

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
