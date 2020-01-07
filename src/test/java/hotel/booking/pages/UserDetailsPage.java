package hotel.booking.pages;

import hotel.booking.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsPage extends Page {

  @FindBy(how = How.ID, using = "firstname")
  private WebElement firstNameField;

  @FindBy(how = How.ID, using = "lastname")
  private WebElement lastNameField;

  @FindBy(how = How.ID, using = "totalprice")
  private WebElement priceField;

  @FindBy(how = How.ID, using = "checkin")
  private WebElement checkInDateField;

  @FindBy(how = How.ID, using = "checkout")
  private WebElement checkOutDateField;

  @FindBy(how = How.ID, using = "depositpaid")
  private WebElement depositField;

  @FindBy(how = How.ID, using = "bookings")
  private WebElement bookingRows;

  @FindBy(how = How.XPATH, using = "//div[@id='form']//div[7]//input[1]")
  private WebElement saveButton;

  @FindBy(how = How.CSS, using = "[type='button'][value='Delete']")
  private WebElement deleteButton;

  @FindBy(how = How.XPATH, using = "//*[@id='bookings']/div[@class='row']")
  private List<WebElement> hotelBookingRow;



  public UserDetailsPage(final WebDriver webDriver, final Config config) {
    super(webDriver, config);
  }

  @Override
  String getUrlPath() {
    return "/";
  }

  @Override
  ExpectedCondition<Boolean> pageIsLoadedCheck() { return ExpectedConditions.titleIs("Hotel booking form"); }

  public void setFirstName(final String value) {
    setTextFieldValue(firstNameField, value);
  }

  public void setLastName(final String value) {
    setTextFieldValue(lastNameField, value);
  }

  public void setPrice(final String value) {
    setTextFieldValue(priceField, value);
  }

  public void isDepositPaid(boolean depositPaid){ Select depositDropDown = new Select(depositField);
    if(depositPaid)
      depositDropDown.selectByVisibleText("true");
    else
      depositDropDown.selectByVisibleText("false");
  }

  public void setCheckInDate(final String value) {
    setTextFieldValue(checkInDateField, value);
  }

  public void setCheckOutDate(final String value) {
    setTextFieldValue(checkOutDateField, value);
  }

  public void clickSave(){ saveButton.click(); }

  public void clickDelete(){
    deleteButton.click();
  }

  public List<BookingRow> getListOfBookings() {
    List<BookingRow> bookingRows = new ArrayList<>();
      for (WebElement element : hotelBookingRow){
      String firstName = element.findElement(By.xpath("div[1]")).getText();
      String lastName = element.findElement(By.xpath("div[2]")).getText();
      String price = element.findElement(By.xpath("div[3]")).getText();
      String deposit = element.findElement(By.xpath("div[4]")).getText();
      String checkIn = element.findElement(By.xpath("div[5]")).getText();
      String checkOut = element.findElement(By.xpath("div[6]")).getText();
      BookingRow row = new BookingRow(firstName,lastName,price,deposit,checkIn,checkOut);
      bookingRows.add(row);

    }
    bookingRows.remove(0);
    return bookingRows;
  }
}
