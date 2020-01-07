package hotel.booking.pages;

import hotel.booking.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {


  private final WebDriver driver;
  private final Config config;

  Page(final WebDriver driver, final Config config) {
    this.driver = driver;
    this.config = config;
    PageFactory.initElements(driver, this);
  }

  abstract String getUrlPath();

  public void waitForPageToLoad() {
    new WebDriverWait(driver, config.getWebDriverTimeout().getSeconds()).until(pageIsLoadedCheck());
  }

  ExpectedCondition<Boolean> pageIsLoadedCheck() {
    return ExpectedConditions.urlContains(getUrlPath());
  }

  void setTextFieldValue(final WebElement field, final String value) {
    field.clear();
    field.sendKeys(value == null ? "" : value);
  }


}
