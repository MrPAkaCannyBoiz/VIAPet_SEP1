package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class BookingList implements Serializable
{
  private ArrayList<Booking> bookings;
  private ArrayList<Booking> currentBooking;

  public BookingList()
  {
    this.bookings=new ArrayList<>();
    this.currentBooking = new ArrayList<>();
  }

  public void addBooking(Booking booking)
  {
    if (booking != null)
    {
      bookings.add(booking);
      System.out.println("Booking added");
    }
  }

  public void addCurrentBooking(Booking booking)
  {
    if (booking != null)
    {
      currentBooking.add(booking);
      System.out.println("Booking added");
    }
  }

  public int size()
  {
    return bookings.size();
  }

  public int currentBookingSize()
  {
  return currentBooking.size();
  }

  public Booking getBookings(int index)
  {
    return bookings.get(index);
  }

  public Booking getCurrentBooking(int index)
  {
    return currentBooking.get(index);
  }

  public boolean removeBooking(Booking booking)
  {
    if (bookings != null)
    {
      return bookings.remove(booking);
    }
    return false;
  }

  public boolean removeCurrentBooking(Booking booking)
  {
    if (currentBooking != null)
    {
      return currentBooking.remove(booking);
    }
    return false;
  }

  public ArrayList<Booking> getBookingList() {
    return bookings;
  }
  public ArrayList<Booking> getCurrentBookingList()
  {
    return currentBooking;
  }
}
