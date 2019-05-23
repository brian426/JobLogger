package test;

import org.junit.Before;
import org.junit.Test;
import refactor.BuildLogServiceImpl;

import static org.junit.Assert.assertEquals;

public class BuildLogServiceImplTest {

  private BuildLogServiceImpl buildLogService = new BuildLogServiceImpl();

  @Before
  private void prepareValues() {
  }

  @Test
  public void buildGenericLogTest_FinalMessage_Empty_Expected() {
    String messageText = "Mensaje de prueba";
    boolean logError = false;
    boolean logWarning = false;
    boolean logMessage = false;

    String finalMsg = buildLogService.buildGenericLog(messageText, logError, logWarning, logMessage);

    assertEquals(finalMsg, "");
  }

  @Test
  public void buildGenericLogTest_LogError_Expected() {
    String messageText = "Error interno: Consulte con el administrador.";
    boolean logError = true;
    boolean logWarning = false;
    boolean logMessage = false;

    String finalMsg = buildLogService.buildGenericLog(messageText, logError, logWarning, logMessage);

    assertEquals(finalMsg, "error 2019-05-23 15:08:03 UTC Error interno: Consulte con el administrador.");
  }

  @Test
  public void buildGenericLogTest_LogWarning_Expected() {
    String messageText = "Advertencia: Debe usar solo números.";
    boolean logError = false;
    boolean logWarning = true;
    boolean logMessage = false;

    String finalMsg = buildLogService.buildGenericLog(messageText, logError, logWarning, logMessage);

    assertEquals(finalMsg, "warning 2019-05-23 15:08:03 UTC Advertencia: Debe usar solo números.");
  }

  @Test
  public void buildGenericLogTest_LogMessage_Expected() {
    String messageText = "Información: Log de consola creado con éxito.";
    boolean logError = false;
    boolean logWarning = false;
    boolean logMessage = true;

    String finalMsg = buildLogService.buildGenericLog(messageText, logError, logWarning, logMessage);

    assertEquals(finalMsg, "warning 2019-05-23 15:08:03 UTC Información: Log de consola creado" +
            " con éxito.");
  }

  @Test
  public void buildLogToDataBase_Zero_Expected() {
    boolean logError = false;
    boolean logWarning = false;
    boolean logMessage = false;

    int tipoLog = buildLogService.buildLogToDataBase(logError, logWarning, logMessage);

    assertEquals(tipoLog, 0);
  }

  @Test
  public void buildLogToDataBase_LogMessage_Expected() {
    boolean logError = false;
    boolean logWarning = false;
    boolean logMessage = true;

    int tipoLog = buildLogService.buildLogToDataBase(logError, logWarning, logMessage);

    assertEquals(tipoLog, 1);
  }

  @Test
  public void buildLogToDataBase_LogError_Expected() {
    boolean logError = true;
    boolean logWarning = false;
    boolean logMessage = false;

    int tipoLog = buildLogService.buildLogToDataBase(logError, logWarning, logMessage);

    assertEquals(tipoLog, 2);
  }

  @Test
  public void buildLogToDataBase_LogWarning_Expected() {
    boolean logError = false;
    boolean logWarning = true;
    boolean logMessage = false;

    int tipoLog = buildLogService.buildLogToDataBase(logError, logWarning, logMessage);

    assertEquals(tipoLog, 3);
  }
}
