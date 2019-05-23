package refactor;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

interface DataBaseConnectionService {

  Statement createConnection(Map dbParams) throws SQLException;
}
