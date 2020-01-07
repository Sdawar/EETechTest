package hotel.booking;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

public class DriverManager {
  public static final Proxy NO_PROXY = null;

  private WebDriver driver;
  private final Config config;

  public DriverManager(final Config config) {
    this.config = config;
  }

  public WebDriver getDriver() {
    return getDriver(NO_PROXY);
  }

  public WebDriver getDriver(final Proxy proxy) {
    if (driver == null) {
      driver = buildDriver(proxy);
    }
    return driver;
  }

  private WebDriver buildDriver(final Proxy proxy) {
    final WebDriver driver = config.getBrowser().build(config, proxy);
    driver.manage().window().setSize(config.getBrowserWindowSize());
    driver.manage().timeouts().pageLoadTimeout(config.getWebDriverTimeout().getSeconds(), TimeUnit.SECONDS);
    return driver;
  }

  public void shutdownDriver() {
    if (driver != null) {
      driver.quit();
      driver = null;
    }
  }
}
