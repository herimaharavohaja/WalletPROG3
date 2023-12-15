import java.time.LocalDateTime;
import java.util.Objects;

public class Balance {
  private Long balanceId;
  private LocalDateTime balanceDateTime;
  private Double amount;
  private Account account;

  public Balance(Long balanceId, LocalDateTime balanceDateTime, Double amount, Account account) {
    this.balanceId = balanceId;
    this.balanceDateTime = balanceDateTime;
    this.amount = amount;
    this.account = account;
  }

  @Override
  public String toString() {
    return "Balance{" +
            "balanceId=" + balanceId +
            ", balanceDateTime=" + balanceDateTime +
            ", amount=" + amount +
            ", account=" + account +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Balance balance)) return false;
    return Objects.equals(balanceId, balance.balanceId) && Objects.equals(balanceDateTime, balance.balanceDateTime) && Objects.equals(amount, balance.amount) && Objects.equals(account, balance.account);
  }

  @Override
  public int hashCode() {
    return Objects.hash(balanceId, balanceDateTime, amount, account);
  }

  public Long getBalanceId() {
    return balanceId;
  }

  public void setBalanceId(Long balanceId) {
    this.balanceId = balanceId;
  }

  public LocalDateTime getBalanceDateTime() {
    return balanceDateTime;
  }

  public void setBalanceDateTime(LocalDateTime balanceDateTime) {
    this.balanceDateTime = balanceDateTime;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Double getBalance() {
    return this.amount;
  }

  public void credit(double amount) {
    this.amount += amount;
  }

  public void debit(double amount) {
    if (this.amount >= amount) {
      this.amount -= amount;
    } else {
      throw new RuntimeException("Insufficient funds");
    }
  }
}
