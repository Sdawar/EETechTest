package hotel.booking;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public enum Browser {
  CHROME() {
    @Override
    public WebDriver build(final Config config, final Proxy proxy) {
      WebDriverManager.chromedriver().setup();
      final ChromeOptions options = new ChromeOptions();
      setProxy(options, proxy);

      return new ChromeDriver(options);
    }
  },
  CHROME_HEADLESS() {
    @Override
    public WebDriver build(final Config config, final Proxy proxy) {
      WebDriverManager.chromedriver().setup();
      final ChromeOptions options = new ChromeOptions();
      options.addArguments("--headless");
      setProxy(options, proxy);

      return new ChromeDriver(options);
    }
  },
  FIREFOX() {
    @Override
    public WebDriver build(final Config config, final Proxy proxy) {
      WebDriverManager.firefoxdriver().setup();
      final FirefoxOptions options = new FirefoxOptions();
      setProxy(options, proxy);

      return new FirefoxDriver(options);
    }
  },
  INTERNET_EXPLORER() {
    @Override
    public WebDriver build(final Config config, final Proxy proxy) {
      WebDriverManager.iedriver().setup();
      final InternetExplorerOptions options = new InternetExplorerOptions();
      setProxy(options, proxy);

      return new InternetExplorerDriver(options);
    }
  };

  public abstract WebDriver build(final Config config, final Proxy proxy);

  void setProxy(final MutableCapabilities options, final Proxy proxy) {
    if (proxy != null) {
      options.setCapability("proxy", proxy);
    }
  }
}
