import org.example.Connection.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountRepository implements com.walletbyhei.repository.InterfaceRepository<Account> {

  // ... Autres méthodes

  @Override
  public Account findById(int toFind) {
    String SELECT_BY_ID_QUERY = "SELECT * FROM account WHERE account_id = ?";
    Connection connection = ConnectionDatabase.getConnection();

    try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
      statement.setLong(1, toFind);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        // Utilisation du constructeur sans paramètres pour créer un nouvel Account
        return new Account();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  @Override
  public List<Account> findAll() {
    List<Account> accounts = new ArrayList<>();

    Connection connection = ConnectionToDb.getConnection();
    String SELECT_ALL_QUERY = "SELECT * FROM account";

    try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        // Utilisation du constructeur sans paramètres pour créer un nouvel Account
        Account account = new Account();
        accounts.add(account);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Failed to retrieve all account : " + e.getMessage());
    } finally {
      closeResources(connection, null, null);
    }
    return accounts;
  }

  @Override
  public Account save(Account toSave) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = ConnectionToDb.getConnection();
      connection.setAutoCommit(false); // Début de la transaction

      // Reste du code pour la sauvegarde de l'Account...

      connection.commit(); // Validation de la transaction après la sauvegarde

    } catch (SQLException e) {
      try {
        if (connection != null) {
          connection.rollback(); // Annulation de la transaction en cas d'échec
        }
      } catch (SQLException ex) {
        throw new RuntimeException("Failed to rollback transaction: " + ex.getMessage());
      }
      throw new RuntimeException("Failed to save account: " + e.getMessage());
    } finally {
      closeResources(connection, statement, resultSet);
    }
    return toSave;
  }


}
