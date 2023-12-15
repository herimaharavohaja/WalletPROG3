
public class Account {
  private Long accountId;
  private String accountName;
  private AccountType accountType;
  private List<Transaction> transactionList;
  private Balance balance;
  private Currency currency;

  public Account(Long accountId, String accountName, AccountType accountType, List<Transaction> transactionList, Balance balance, Currency currency) {
    this.accountId = accountId;
    this.accountName = accountName;
    this.accountType = accountType;
    this.transactionList = transactionList;
    this.balance = balance;
    this.currency = currency;
  }

  @Override
  public String toString() {
    return "Account{" +
            "accountId=" + accountId +
            ", accountName='" + accountName + '\'' +
            ", accountType=" + accountType +
            ", transactionList=" + transactionList +
            ", balance=" + balance +
            ", currency=" + currency +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Account)) return false;
    Account account = (Account) o;
    return Objects.equals(accountId, account.accountId) && Objects.equals(accountName, account.accountName) && accountType == account.accountType && Objects.equals(transactionList, account.transactionList) && Objects.equals(balance, account.balance) && Objects.equals(currency, account.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId, accountName, accountType, transactionList, balance, currency);
  }
}
