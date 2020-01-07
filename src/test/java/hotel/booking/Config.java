package hotel.booking;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Dimension;

public class Config {
  private static final String CONFIG_DIR = "config/";
  private static final String CONFIG_PROPS_FILE_PATH = CONFIG_DIR + "config.properties";

  private final Properties configPropertiesFromFile;

  public Config() {
    configPropertiesFromFile = loadUtf8PropsFile(CONFIG_PROPS_FILE_PATH);
  }

  private Properties loadUtf8PropsFile(final String path) {
    try {
      final Properties propsFile = new Properties();
      try (FileInputStream fileInputStream = new FileInputStream(path)) {
        propsFile.load(new InputStreamReader(fileInputStream, Charset.forName("UTF-8")));
      }
      return propsFile;
    } catch (IOException ex) {
      throw new UncheckedIOException(ex);
    }
  }

  public Browser getBrowser() {
    final String browserName = getProperty("browser.type");
    try {
      return Browser.valueOf(browserName.toUpperCase().replaceAll(" ", "_"));
    } catch (IllegalArgumentException ex) {
      throw new IllegalStateException(String.format("The value of the 'browser' config value '%s' was not recognised as a valid browser name", browserName));
    }
  }

  public Dimension getBrowserWindowSize() {
    final int width = getPropertyAsInt("browser.width");
    final int height = getPropertyAsInt("browser.height");

    return new Dimension(width, height);
  }

  public String getBaseUrl() {
    return getProperty("browser.url");
  }

  public File getErrorScreenshotDirectory() {
    return new File(getProperty("browser.error.screenshot.dir"));
  }

  public Duration getWebDriverTimeout() {
    return Duration.ofSeconds(getPropertyAsInt("time.webdriver.wait.seconds"));
  }

  private int getPropertyAsInt(final String name) {
    final String propertyValue = getProperty(name);
    try {
      return Integer.parseInt(propertyValue);
    } catch (NumberFormatException ex) {
      throw new IllegalStateException(String.format("Unable to convert the config value for '%s' to an integer, the value was '%s'", name, propertyValue));
    }
  }

  private String getProperty(final String name) {
    final String systemProperty = System.getProperty(name);
    if (systemProperty != null) {
      return systemProperty;
    }

    final String propertyFromFile = configPropertiesFromFile.getProperty(name);
    if (propertyFromFile == null) {
      throw new IllegalStateException(String.format("Unable to find a value for the config key '%s' either in the %s file or in system properties", name, CONFIG_PROPS_FILE_PATH));
    }
    return propertyFromFile;
  }

}
