package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Booking implements Serializable
{
  private Date startDate, endDate;
  private Customer customer;
  private Pet pet;
  private double price;


  public Booking(Date startDate,Date endDate,Customer customer, Pet pet,double price)
  {
    this.startDate=startDate;
    this.endDate=endDate;
    this.customer=customer;
    this.pet=pet;
    this.price=price;
  }


  public Date getStartDate()
  {
    return startDate;
  }

  public void setStartDate(Date startDate)
  {
    this.startDate = startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public void setEndDate(Date endDate)
  {
    this.endDate = endDate;
  }

  public double getPrice()
  {
    return price;
  }

  public void setPrice(double price)
  {
    this.price = price;
  }

  public void setCustomer(Customer customer) {this.customer = customer;}

  public Customer getCustomer() {return customer;}

  public void setPet(Pet pet)
  {
    this.pet = pet;
  }

  public Pet getPet() {return pet;}

  public String toString()
  {
    return "Booking: " + "startDate=" + startDate + ", endDate=" + endDate
        + ", Customer="+ customer + ", Pet=" + pet + ", price=" + price;
  }

  public boolean equals(Object o)
  {
    if (o == null || getClass() != o.getClass())
      return false;
    Booking booking = (Booking) o;
    return Double.compare(price, booking.price) == 0 && Objects.equals(
        startDate, booking.startDate) && Objects.equals(endDate,
        booking.endDate) && Objects.equals(customer, booking.customer)
        && Objects.equals(pet, booking.pet);
  }
}
