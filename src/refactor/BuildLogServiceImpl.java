package refactor;

import java.text.DateFormat;
import java.util.Date;

import static refactor.util.Constants.*;

public class BuildLogServiceImpl implements BuildLogService {

  @Override
  public String buildGenericLog(String messageText, boolean logError, boolean logWarning, boolean logMessage) {
    String finalMsg = EMPTY;
    if (logError) {
      finalMsg = finalMsg + TAG_ERROR + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
    }

    if (logWarning) {
      finalMsg = finalMsg + TAG_WARNING + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
    }

    if (logMessage) {
      finalMsg = finalMsg + TAG_MESSAGE + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
    }
    return finalMsg;
  }

  @Override
  public int buildLogToDataBase(boolean logError, boolean logWarning, boolean logMessage) {
    int t = 0;
    if (logMessage) {
      t = 1;
    }

    if (logError) {
      t = 2;
    }

    if (logWarning) {
      t = 3;
    }
    return t;
  }
}
