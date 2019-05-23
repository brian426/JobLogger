package refactor;

interface ValidateService {

  void validateEntryValues(String messageText, boolean logToConsole, boolean logToFile, boolean logToDatabase,
                           boolean logError, boolean logMessage, boolean logWarning) throws Exception;
}
