package Model;

public class SeperatorBooking
{
  private String startDate, endDate;
  private String customer;
  private String pet;
  private String price;
  public String getPrice()
  {
    return price;
  }
  public String getCustomer() {return customer;}
  public String getPet() {return pet;}
  public String getEndDate()
  {
    return endDate;
  }
  public String getStartDate()
  {
    return startDate;
  }

  public SeperatorBooking()
  {
    startDate="-";
    endDate="-";
    customer="-";
    pet="-";
    price="Current Bookings";
  }
}
