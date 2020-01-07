package hotel.booking.acceptance.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hotel.booking.Config;
import hotel.booking.DriverManager;
import hotel.booking.pages.UserDetailsPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StepDefinitions {
  public WebDriver driver;
  public DriverManager driverManager;
  private Config config;
  private UserDetailsPage userDetailsPage;
  private int numberOfRows;


  @Before
  public void setUp() throws IOException {
    config = new Config();
    driverManager = new DriverManager(config);
    driver = driverManager.getDriver();
    userDetailsPage = new UserDetailsPage(driver, config);

  }

  @After
  public void tearDown(final Scenario scenario) throws IOException {
    try {
      if (scenario.isFailed()) {
        if (driver instanceof TakesScreenshot) {
          final byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
          saveScreenshot(screenshotBytes, scenario.getName());
        }
      }
    } finally {
      driverManager.shutdownDriver();
    }
  }

  private void saveScreenshot(final byte[] screenshotBytes, final String scenarioName) throws IOException {
    final String fileName = String.format("failure-screenshot-%s.png", scenarioName.replaceAll(" ", "_"));

    final File screenshotDir = config.getErrorScreenshotDirectory();
    if (!screenshotDir.exists()) {
      screenshotDir.mkdirs();
    }

    final File screenshotFile = new File(screenshotDir, fileName);

    try (OutputStream writer = new FileOutputStream(screenshotFile)) {
      writer.write(screenshotBytes);
    }
  }

  @Given("^I started application as a Agent in chrome and go to hotel booking site$")
  public void iStartedApplicationAsAAgentInChromeAndGoToHotelBookingSite() {
    driver.get(config.getBaseUrl());
    userDetailsPage.waitForPageToLoad();
  }

  @And("I check how many rows are returned")
  public void iCheckHowManyRowsAreReturned() {
    userDetailsPage.waitForPageToLoad();
    WebDriverWait wait = new WebDriverWait(driver, 20);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='bookings']/div[@class='row']")));
    numberOfRows = userDetailsPage.getListOfBookings().size();
  }

  @When("I click on delete button")
  public void iClickOnDeleteButton() {
    userDetailsPage.clickDelete();
  }

  @Then("the booking for {string} is deleted")
  public void theBookingForIsDeleted(String user) {
    userDetailsPage.waitForPageToLoad();
    driver.navigate().refresh();
    assertFalse(userDetailsPage.getListOfBookings().contains(user));
  }

  @When("I click on save button")
  public void iClickOnSaveButton() {
    userDetailsPage.clickSave();
  }

  @And("I check booking for {string} details is saved")
  public void iCheckBookingForDetailsIsSaved(String booking) {
    assertTrue(userDetailsPage.getListOfBookings().contains(booking));
  }

  @And("I enter user details on the hotel page")
  public void iEnterUserDetailsOnTheHotelPage() {
    userDetailsPage.setFirstName("Delete");
    userDetailsPage.setLastName("User");
    userDetailsPage.isDepositPaid(true);
    userDetailsPage.setPrice("456.78");
    userDetailsPage.setCheckInDate("2019-02-01");
    userDetailsPage.setCheckOutDate("2019-03-01");
  }

  @And("I enter user details for booking")
  public void iEnterUserDetailsForBooking() {
    userDetailsPage.setFirstName("Add");
    userDetailsPage.setLastName("User");
    userDetailsPage.isDepositPaid(true);
    userDetailsPage.setPrice("490.78");
    userDetailsPage.setCheckInDate("2019-02-01");
    userDetailsPage.setCheckOutDate("2019-03-01");
  }

  @Then("booking row should have increased by one")
  public void bookingRowShouldHaveIncreasedByOne() {
    WebDriverWait wait = new WebDriverWait(driver, 20);
    wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id='bookings']/div[@class='row']"), 2+numberOfRows));
    assertThat(userDetailsPage.getListOfBookings().size(), is(++numberOfRows));
  }
}
