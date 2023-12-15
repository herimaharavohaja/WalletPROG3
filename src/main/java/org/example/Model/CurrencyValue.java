package com.walletbyhei.model;

import java.time.LocalDate;
import java.util.Objects;

public class CurrencyValue {
  private Long currencyValueId;
  private int sourceCurrencyId;
  private int destinationCurrencyId;
  private Double value;
  private LocalDate effectiveDate;

  public CurrencyValue(Long currencyValueId, int sourceCurrencyId, int destinationCurrencyId, Double value, LocalDate effectiveDate) {
    this.currencyValueId = currencyValueId;
    this.sourceCurrencyId = sourceCurrencyId;
    this.destinationCurrencyId = destinationCurrencyId;
    this.value = value;
    this.effectiveDate = effectiveDate;
  }

  @Override
  public String toString() {
    return "CurrencyValue{" +
            "currencyValueId=" + currencyValueId +
            ", sourceCurrencyId=" + sourceCurrencyId +
            ", destinationCurrencyId=" + destinationCurrencyId +
            ", value=" + value +
            ", effectiveDate=" + effectiveDate +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CurrencyValue that)) return false;
    return sourceCurrencyId == that.sourceCurrencyId && destinationCurrencyId == that.destinationCurrencyId && Objects.equals(currencyValueId, that.currencyValueId) && Objects.equals(value, that.value) && Objects.equals(effectiveDate, that.effectiveDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currencyValueId, sourceCurrencyId, destinationCurrencyId, value, effectiveDate);
  }

  public Long getCurrencyValueId() {
    return currencyValueId;
  }

  public void setCurrencyValueId(Long currencyValueId) {
    this.currencyValueId = currencyValueId;
  }

  public int getSourceCurrencyId() {
    return sourceCurrencyId;
  }

  public void setSourceCurrencyId(int sourceCurrencyId) {
    this.sourceCurrencyId = sourceCurrencyId;
  }

  public int getDestinationCurrencyId() {
    return destinationCurrencyId;
  }

  public void setDestinationCurrencyId(int destinationCurrencyId) {
    this.destinationCurrencyId = destinationCurrencyId;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public LocalDate getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(LocalDate effectiveDate) {
    this.effectiveDate = effectiveDate;
  }
}
