import java.util.Objects;

public class Currency {
  private Long currencyId;
  private String currencyName;
  private String currencyCode;

  public Currency(Long currencyId, String currencyName, String currencyCode) {
    this.currencyId = currencyId;
    this.currencyName = currencyName;
    this.currencyCode = currencyCode;
  }

  @Override
  public String toString() {
    return "Currency{" +
            "currencyId=" + currencyId +
            ", currencyName='" + currencyName + '\'' +
            ", currencyCode='" + currencyCode + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Currency currency)) return false;
    return Objects.equals(currencyId, currency.currencyId) && Objects.equals(currencyName, currency.currencyName) && Objects.equals(currencyCode, currency.currencyCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currencyId, currencyName, currencyCode);
  }

  public Long getCurrencyId() {
    return currencyId;
  }

  public void setCurrencyId(Long currencyId) {
    this.currencyId = currencyId;
  }

  public String getCurrencyName() {
    return currencyName;
  }

  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }
}
