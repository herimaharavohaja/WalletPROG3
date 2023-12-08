package org.example.Model;

import java.util.Date;
import java.util.Objects;

public class CurrencyValue {
    private int id;
    private Devise devise;
    private double value;
    private Date date;

    @Override
    public String toString() {
        return "CurrencyValue{" +
                "id=" + id +
                ", devise=" + devise +
                ", value=" + value +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyValue that)) return false;
        return id == that.id && Double.compare(that.value, value) == 0 && Objects.equals(devise, that.devise) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, devise, value, date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
