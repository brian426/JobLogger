package refactor;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertLogServiceImpl implements InsertLogService {

  private DataBaseConnectionService dataBaseConnectionService;

  public InsertLogServiceImpl(DataBaseConnectionService dataBaseConnectionService) {
    this.dataBaseConnectionService = dataBaseConnectionService;
  }

  @Override
  public void insertLogToFile(String finalMsg, Map dbParams, Logger logger) throws IOException {
    FileHandler fh = new FileHandler(dbParams.get("logFileFolder") + "/logFile.txt");
    logger.addHandler(fh);
    logger.log(Level.INFO, finalMsg);
  }

  @Override
  public void insertLogToConsole(String finalMsg, Logger logger) {
    ConsoleHandler ch = new ConsoleHandler();
    logger.addHandler(ch);
    logger.log(Level.INFO, finalMsg);
  }

  @Override
  public void insertLogToDataBase(String messageText, int tipoMsg, Map dbParams) throws SQLException {
    Statement stmt = dataBaseConnectionService.createConnection(dbParams);
    stmt.executeUpdate("insert into Log_Values('" + messageText + "', " + tipoMsg + ")");
    // Close connection
  }
}
