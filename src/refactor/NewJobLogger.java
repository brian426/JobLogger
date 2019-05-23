package refactor;

import java.util.Map;
import java.util.logging.Logger;

public class NewJobLogger {

  private static boolean logToFile;
  private static boolean logToConsole;
  private static boolean logToDatabase;

  private static boolean logMessage;
  private static boolean logWarning;
  private static boolean logError;

  private static Map dbParams;
  private static Logger logger;

  // Interfaces a utilizar
  private ValidateService validateService;
  private BuildLogService buildLogService;
  private InsertLogService insertLogService;

  public NewJobLogger(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam,
                      boolean logMessageParam, boolean logWarningParam, boolean logErrorParam, Map dbParamsMap,
                      ValidateService validateService, BuildLogService buildLogService,
                      InsertLogService insertLogService) {
    logger = Logger.getLogger("MyLog");
    logError = logErrorParam;
    logMessage = logMessageParam;
    logWarning = logWarningParam;
    logToDatabase = logToDatabaseParam;
    logToFile = logToFileParam;
    logToConsole = logToConsoleParam;
    dbParams = dbParamsMap;

    // Inyectando dependencias
    this.validateService = validateService;
    this.buildLogService = buildLogService;
    this.insertLogService = insertLogService;
  }

  public void runJobLogger(String messageText) throws Exception {

    // Empezamos validando los datos de entrada
    validateService.validateEntryValues(messageText, logToConsole, logToFile, logToDatabase, logError,
            logMessage, logWarning);
    messageText = messageText.trim();

    // Construccion del cuerpo del mensaje a guardar
    String finalMsg = buildLogService.buildGenericLog(messageText, logError, logWarning, logMessage);

    // Inserción de mensajes segun log
    if (logToFile) {
      insertLogService.insertLogToFile(finalMsg, dbParams, logger);
    }
    if (logToConsole) {
      insertLogService.insertLogToConsole(finalMsg, logger);
    }
    if (logToDatabase) {
      int tipoMsg = buildLogService.buildLogToDataBase(logError, logWarning, logMessage);
      insertLogService.insertLogToDataBase(messageText, tipoMsg, dbParams);
    }
  }
}
