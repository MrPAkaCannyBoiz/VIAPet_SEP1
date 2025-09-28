package Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Customer implements Serializable {
    private String name;
    private String email;
    private String phoneNo;
    //private ArrayList<Booking> bookings;

    public Customer(String name, String email, String phoneNo) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        //bookings = new ArrayList<Booking>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    //TODO add return Bookings arrray
    //public Booking getBooking(int index) {
        //return bookings.get(index);
    //}

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
               // ", bookings=" + bookings +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(phoneNo, customer.phoneNo) ;//&& Objects.equals(bookings, customer.bookings);
    }
}
