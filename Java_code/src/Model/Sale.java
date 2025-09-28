package Model;

import java.io.Serializable;
import java.util.Date;

public class Sale implements Serializable
{
  private double finalPrice;
  private Date dateOfSale;
  private Customer customer;
  private Pet pet;

  public Sale(double finalPrice, Date dateOfSale, Customer customer, Pet pet)
  {
    setDateOfSale(dateOfSale);
    setFinalPrice(finalPrice);
    setCustomer(customer);
    setPet(pet);
  }

  public void setDateOfSale(Date dateOfSale)
  {
    this.dateOfSale = dateOfSale;
  }

  public void setFinalPrice(double finalPrice)
  {
    this.finalPrice = finalPrice;
  }

  public void setPet(Pet pet)
  {
    this.pet = pet;
  }

  public void setCustomer(Customer customer)
  {
    this.customer = customer;
  }

  public Pet getPet()
  {
     return pet;
  }

  public Customer getCustomer()
  {
    return customer;
  }

  public Date getDateOfSale()
  {
    return dateOfSale;
  }

  public double getFinalPrice()
  {
    return finalPrice;
  }



  @Override
  public String toString()
  {
    return "Sale{" +
        "price=" + finalPrice +
        ", dateOfSale=" + dateOfSale +
        ", customer=" + customer +
        ", pet=" + pet +
        '}';
  }
}
