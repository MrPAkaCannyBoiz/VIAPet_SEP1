package Model;

import Utils.FileHandler;
import Utils.XMLHandler;
import parser.ParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class VIAPet
{
    private PetList petList;
    private SaleList saleList;
    private BookingList bookingList;
    private CustomerList customerList;

    public VIAPet() throws IOException, ClassNotFoundException
    {
        try{
            petList = (PetList) FileHandler.readFromBinaryFile("PetList.bin");
            if(petList == null) throw new IOException();
        }catch(IOException e){
            petList = new PetList();
            System.out.println(e);
        }

        try{
            saleList = (SaleList) FileHandler.readFromBinaryFile("SaleList.bin");
            if(saleList == null) throw new IOException();
        }catch(IOException e){
            saleList = new SaleList();
            System.out.println(e);
        }

        try{
            bookingList = (BookingList) FileHandler.readFromBinaryFile("BookingList.bin");
            if(bookingList == null) throw new IOException();
        }catch(IOException e){
            bookingList = new BookingList();
            System.out.println(e);
        }

        try{
            customerList = (CustomerList) FileHandler.readFromBinaryFile("CustomerList.bin");
            if(customerList == null) throw new IOException();
        }catch(IOException e){
            customerList = new CustomerList();
            System.out.println(e);
        }
    }

    public void addBooking(Booking booking){
        bookingList.addBooking(booking);
    }

    public void addCurrentBooking(Booking booking){
        bookingList.addCurrentBooking(booking);
    }

    public boolean removeBooking(Booking booking){
        return bookingList.removeBooking(booking);
    }

    public boolean removeCurrentBooking(Booking booking){
        return bookingList.removeCurrentBooking(booking);
    }

    public void addCustomer(Customer customer){
        customerList.addCustomer(customer);
    }

    public Customer getCustomer(String phoneNo){
        for (int i = 0; i < customerList.size(); i++)
        {
            Customer customer = customerList.getCustomer(i);
            if(customer.getPhoneNo().equals(phoneNo))
                return customer;
        }

        return null;
    }

    public void addPet(Pet pet){
        petList.addPet(pet);
    }

    public Pet getPet(Character gender, Integer age, String name, String color, Double price, String animalType){
        for (int i = 0; i < petList.size(); i++)
        {
            Pet pet = petList.getPet(i);
            if ((gender == null || pet.getGender() == gender) && (age == null
                || pet.getAge() == age) && (name == null || pet.getName()
                .equalsIgnoreCase(name)) && (color == null || pet.getColor()
                .equalsIgnoreCase(color)) && (price == null
                || pet.getPrice() == price) && (animalType == null
                || pet.getAnimalType().equalsIgnoreCase(animalType)))
            {
                return pet;
            }
        }
        System.out.println("No pet found matching the given attributes.");
        return null;
    }

    public void addSale(Sale sale){
        saleList.addSale(sale);
    }

    public Sale getSale(int i){
        return saleList.getSale(i);
    }

    public PetList getPetList()
    {
        return petList;
    }

    public CustomerList getCustomerList()
    {
        return customerList;
    }

    public SaleList getSaleList()
    {
        return saleList;
    }

    public BookingList getBookingList()
    {
        return bookingList;
    }

    public int getNumberOfBookingsOnDate(Date date)
    {
        ArrayList<Booking> currentBookings = bookingList.getCurrentBookingList(); // 1 time unit
        if(date.before(getNow())) { // 2 time units (1 call to date.before() + 1 call to getNow())
            return -1; // 1 time unit
        }
        int numberOfBookings = 0; // 1 time unit

        for(int i = 0; i < currentBookings.size(); i++) {
            Booking checkedBooking = currentBookings.get(i); // 1 time unit per iteration
            if((getNow()).after(checkedBooking.getEndDate())) { // 2 time units (1 call to getNow() + 1 call to after())
                currentBookings.remove(i); // O(N) time complexity (removing element from ArrayList)
                addBooking(checkedBooking); // O(1)
                continue; // 1 time unit
            }
            if(!checkedBooking.getStartDate().after(date) && checkedBooking.getEndDate().after(date)) {
                // 2 time units (2 calls to after())
                numberOfBookings += 1; // 1 time unit
            }
        }

        return numberOfBookings; // 1 time unit
    }

    public void saveAllToFiles() throws IOException, ParserException
    {

        FileHandler.writeToBinaryFile("PetList.bin", petList);
        XMLHandler.writeToXml("PetList.xml", petList);
        FileHandler.writeToBinaryFile("SaleList.bin", saleList);
        FileHandler.writeToBinaryFile("BookingList.bin", bookingList);
        FileHandler.writeToBinaryFile("CustomerList.bin", customerList);
        FileHandler.writeToTextFile("PetsInKennel.txt",String.valueOf(getNumberOfBookingsOnDate(getNow())));
    }

    public static ArrayList<Date> getDatesBetween(Date startDate, Date endDate) {
        ArrayList<Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        while (!calendar.getTime().after(endDate)) {
            dates.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1); // go to the next day
        }

        return dates;
    }

    public static Date getNow() {
        Calendar calendar = Calendar.getInstance();


        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date now = calendar.getTime();
        return now;
    }
}
