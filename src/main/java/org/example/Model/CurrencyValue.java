package org.example.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class CurrencyValue {
    private int id;
    private int deviseSourceId;
    private int deviseDestinationId;
    private BigDecimal montant;
    private LocalDateTime dateEffet;

    @Override
    public String toString() {
        return "CurrencyValue{" +
                "id=" + id +
                ", deviseSourceId=" + deviseSourceId +
                ", deviseDestinationId=" + deviseDestinationId +
                ", montant=" + montant +
                ", dateEffet=" + dateEffet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyValue that)) return false;
        return id == that.id && deviseSourceId == that.deviseSourceId && deviseDestinationId == that.deviseDestinationId && Objects.equals(montant, that.montant) && Objects.equals(dateEffet, that.dateEffet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deviseSourceId, deviseDestinationId, montant, dateEffet);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeviseSourceId() {
        return deviseSourceId;
    }

    public void setDeviseSourceId(int deviseSourceId) {
        this.deviseSourceId = deviseSourceId;
    }

    public int getDeviseDestinationId() {
        return deviseDestinationId;
    }

    public void setDeviseDestinationId(int deviseDestinationId) {
        this.deviseDestinationId = deviseDestinationId;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public LocalDateTime getDateEffet() {
        return dateEffet;
    }

    public void setDateEffet(LocalDateTime dateEffet) {
        this.dateEffet = dateEffet;
    }
}
