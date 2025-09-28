package GUI;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static GUI.MainGUI.viaPet;

public class AddBookingController
{

  public Button saveAddBookingButton;

  public Button backToBookingButton;

  public ComboBox customerComboBox;

  public ComboBox petComboBox;

  public DatePicker startDatePicker;

  public DatePicker endDatePicker;

  @FXML private TextField priceTextField;

  @FXML private void initialize()
  {
    for (int i = 0; i < viaPet.getPetList().size(); i++)
    {
      if (!MainGUI.viaPet.getPetList().getPet(i).getOnSale())
    {
      petComboBox.getItems().add(MainGUI.viaPet.getPetList().getPet(i));
    }
    }
    for (int i = 0; i < MainGUI.viaPet.getCustomerList().size(); i++)
    {
    customerComboBox.getItems().add(MainGUI.viaPet.getCustomerList().getCustomer(i));
    }
  }


  public void handleBackToBooking(ActionEvent e) throws IOException
  {
    Stage stage = (Stage) backToBookingButton.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();

    loader.setLocation(getClass().getResource("Bookings.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stage.setScene(scene);
  }

  public void handleSaveAddBooking(ActionEvent e) throws IOException

  {
    Date now = VIAPet.getNow();

    Object selectedCustomer = customerComboBox.getSelectionModel().getSelectedItem();
    double price;
    Object selectedPet = petComboBox.getSelectionModel().getSelectedItem();

    if (customerComboBox.getSelectionModel().isEmpty())
    {
      Alert alert = new Alert(Alert.AlertType.WARNING, "Please select customer");
      alert.show();
      return;
    }

    if(petComboBox.getSelectionModel().isEmpty())
    {
      Alert alert = new Alert(Alert.AlertType.WARNING, "Please select pet");
      alert.show();
      return;
    }

    try
    {
      price = Double.parseDouble(priceTextField.getText());
      if(price < 0){
        throw new IllegalArgumentException("Price can not be negative");
      }
    }
    catch (NumberFormatException ex)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING,"PRICE CANNOT BE EMPTY");
      alert.show();
      return;
    }
    catch (IllegalArgumentException ex)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING, ex.getMessage());
      alert.show();
      return;
    }

    Date startDateOfBooking;
    LocalDate datePickerValue = startDatePicker.getValue();
    if(datePickerValue == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING,"START DATE CANNOT BE EMPTY");
      alert.show();
      return;
    }
    else
    {
      ZoneId defaultZoneId = ZoneId.systemDefault();
      startDateOfBooking = Date.from(datePickerValue.atStartOfDay(defaultZoneId).toInstant());
    }

    Date endDateOfBooking;
    LocalDate datePickerValue2 = endDatePicker.getValue();
    if(datePickerValue2 == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING,"END DATE CANNOT BE EMPTY");
      alert.show();
      return;
    }
    else
    {
      ZoneId defaultZoneId = ZoneId.systemDefault();
      endDateOfBooking = Date.from(datePickerValue2.atStartOfDay(defaultZoneId).toInstant());
    }
    if(!endDateOfBooking.after(startDateOfBooking)) {
      Alert alert = new Alert(Alert.AlertType.WARNING,"END DATE HAS TO BE AFTER START DATE");
      alert.show();
      return;
    }



    if((now).after(startDateOfBooking)){
      Alert alert = new Alert(Alert.AlertType.WARNING,"CANNOT MAKE A BOOKING IN THE PAST");
      alert.show();
      return;
    }

      ArrayList<Date> allBookingDates = VIAPet.getDatesBetween(startDateOfBooking, endDateOfBooking);
      System.out.println(allBookingDates);
      for(int i = 0; i < allBookingDates.size() - 1;i++) {
        Date checkedDate = allBookingDates.get(i);
        if(viaPet.getNumberOfBookingsOnDate(checkedDate)>= 10) {
          Alert alert = new Alert(Alert.AlertType.WARNING,"NO SPACE IN THE KENNEL");
          alert.show();
          return;
        }
      }

    Booking newBooking = new Booking(startDateOfBooking, endDateOfBooking,(Customer) selectedCustomer, (Pet) selectedPet, price);
    viaPet.addCurrentBooking(newBooking);

    Stage stage = (Stage) saveAddBookingButton.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();

    loader.setLocation(getClass().getResource("Bookings.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stage.setScene(scene);
  }

}
