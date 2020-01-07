package hotel.booking.pages;

public class BookingRow {

  private String firstNameField;

  private String lastNameField;

  private String priceField;

  private String depositField;

  private String checkInField;

  private String checkOutField;

  public BookingRow(String firstNameField, String lastNameField, String priceField, String depositField, String checkInField, String checkOutField) {
    this.firstNameField = firstNameField;
    this.lastNameField = lastNameField;
    this.priceField = priceField;
    this.depositField = depositField;
    this.checkInField = checkInField;
    this.checkOutField = checkOutField;
  }

  public String getFirstNameField() {
    return firstNameField;
  }

  public String getLastNameField() {
    return lastNameField;
  }

  public String getPriceField() {
    return priceField;
  }

  public String getDepositField() {
    return depositField;
  }

  public String getCheckInField() {
    return checkInField;
  }

  public String getCheckOutField() {
    return checkOutField;
  }
}
