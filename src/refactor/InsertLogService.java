package refactor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

interface InsertLogService {

  void insertLogToFile(String finalMsg, Map dbParams, Logger logger) throws IOException;

  void insertLogToConsole(String finalMsg, Logger logger);

  void insertLogToDataBase(String messageText, int tipoMsg, Map dbParams) throws SQLException;
}
