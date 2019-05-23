package refactor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import static refactor.util.Constants.*;

public class DataBaseConnectionServiceImpl implements DataBaseConnectionService {

  @Override
  public Statement createConnection(Map dbParams) throws SQLException {
    Connection connection;
    Properties connectionProps = new Properties();
    connectionProps.put(TAG_USER, dbParams.get(USER_VALUE));
    connectionProps.put(TAG_PASSWORD, dbParams.get(PASSWORD_VALUE));

    connection = DriverManager.getConnection(JDBC + dbParams.get(DBMS) + SLASH + dbParams.get(SERVER_NAME)
            + POINTS + dbParams.get(PORT_NUMBER) + SLASH_S, connectionProps);

    Statement stmt = null;
    try {
      stmt = connection.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return stmt;
  }
}
