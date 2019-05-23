package refactor;

public class ValidateServiceImpl implements ValidateService {

  @Override
  public void validateEntryValues(String messageText, boolean logToConsole, boolean logToFile, boolean logToDatabase,
                                  boolean logError, boolean logMessage, boolean logWarning) throws Exception {
    if (!logToConsole && !logToFile && !logToDatabase) {
      throw new Exception("Invalid configuration");
    }
    if ((!logError && !logMessage && !logWarning)) {
      throw new Exception("Error or Warning or Message must be specified");
    }
    if (messageText == null || messageText.length() == 0) {
      throw new Exception("Message must not be empty");
    }
  }
}
