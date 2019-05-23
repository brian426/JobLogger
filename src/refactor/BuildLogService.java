package refactor;

interface BuildLogService {

  String buildGenericLog(String messageText, boolean logError, boolean logWarning, boolean logMessage);

  int buildLogToDataBase(boolean logError, boolean logWarning, boolean logMessage);
}
