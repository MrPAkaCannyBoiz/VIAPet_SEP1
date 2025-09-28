package GUI;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditCustomerController {
    VIAPet viaPet;
    Customer customer;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneNoTextField;

    @FXML
    private Button backEditCustomerButton;

    public void setCustomer(Customer customer) {
        viaPet = MainGUI.viaPet;
        this.customer = customer;

        nameTextField.setText(customer.getName());
        emailTextField.setText(customer.getEmail());
        phoneNoTextField.setText(customer.getPhoneNo());
    }

    @FXML
    public void handleBackEditCustomer() throws IOException {
        Stage stage = (Stage) backEditCustomerButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("Customers.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void handleSaveEditCustomer() throws IOException {
        //Create a new customer
        //3 method calls + constructor call
        Customer editedCustomer = new Customer(nameTextField.getText(), emailTextField.getText(),phoneNoTextField.getText());

        //Check if phoneNo was changed if yes, check if customer with provided phoneNo already exists
        //2 method calls and 1 equals check
        if(!customer.getPhoneNo().equals(editedCustomer.getPhoneNo())) {
            //1 method call + O(N)
            if(viaPet.getCustomer(editedCustomer.getPhoneNo()) != null ) {
                Alert alert = new Alert(Alert.AlertType.WARNING,
                        "Customer with provided phone number already exists in the system");//1 time unit
                alert.show();//1 time unit
                return;
            }
        }

        //get sale list and update all sales with old customer to new one
        SaleList saleList = viaPet.getSaleList();//1 time unit

        //loops n2 times, where n2 is size of the saleList
        for(int i = 0; i < saleList.size(); i++){
            Sale sale = saleList.getSale(i); //2 time unit
            Customer saleCustomer = sale.getCustomer(); // 2 time unit
            if(saleCustomer.equals(customer)) { // 1 time unit
                sale.setCustomer(editedCustomer);//1 time unit(if above statement is true)
            }
        }

        //get current bookins list and update all bookings with old customer to new one
        BookingList bookingList = viaPet.getBookingList(); // 1 time unit

        // loop runs n3 times, where n3 is number of current bookings
        for(Booking booking: bookingList.getCurrentBookingList()) {
            Customer bookingCustomer = booking.getCustomer();//2 time unit
            if(bookingCustomer.equals(customer)) {// 1 time unit
                booking.setCustomer(editedCustomer);//1 time unit(if above statement is true)
            }
        }

        //get past bookins list and update all past bookings with old customer to new one
        // loop runs n4 times, where n4 is number of past bookings
        for(Booking booking: bookingList.getBookingList()) {
            Customer bookingCustomer = booking.getCustomer();//2 time unit
            if(bookingCustomer.equals(customer)) {//1 time unit
                booking.setCustomer(editedCustomer);//1 time unit(if above statement is true)
            }
        }

        CustomerList customerList = viaPet.getCustomerList(); // 2 time unit
        customerList.set(customerList.indexOf(customer),editedCustomer);//O(N) for index of method + 1 time unit to set

        handleBackEditCustomer();//1 time unit
    }
}