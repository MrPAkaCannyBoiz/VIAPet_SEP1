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
import java.util.Date;

import static GUI.MainGUI.viaPet;

public class EditBookingController
{
  private Booking bookingToEdit;
  public Button saveEditBookingButton;
  public Button backToBookingButton;
  public DatePicker endDatePicker;
  public DatePicker startDatePicker;
  public TextField priceTextField;
  public ComboBox petComboBox;
  public ComboBox customerComboBox;

  @FXML
  private void initializeForCustomerBox()
  {
    customerComboBox.getItems().addAll(MainGUI.viaPet.getCustomerList().getCustomerList());
  }

  @FXML
  private void initializeForPetBox()
  {
    for (int i = 0; i < viaPet.getPetList().size(); i++)
    {
      if (!MainGUI.viaPet.getPetList().getPet(i).getOnSale())
      {
        petComboBox.getItems().add(MainGUI.viaPet.getPetList().getPet(i));
      }
    }
  }

  public void setBooking(Booking booking)
  {
    this.bookingToEdit = booking;

    customerComboBox.getSelectionModel().select(booking.getCustomer());
    petComboBox.getSelectionModel().select(booking.getPet());
    priceTextField.setText(String.valueOf(booking.getPrice()));
    startDatePicker.setValue(
        booking.getStartDate().toInstant().atZone(ZoneId.systemDefault())
            .toLocalDate());
    endDatePicker.setValue(booking.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
  }

  public void handleSaveEditBooking(ActionEvent e) throws IOException
  {
    Object selectedCustomerObject = customerComboBox.getSelectionModel().getSelectedItem();
    Customer selectedCustomer = (Customer) selectedCustomerObject;
    double price;
    Object selectedPetObject = petComboBox.getSelectionModel().getSelectedItem();
    Pet selectedPet = (Pet) selectedPetObject;
    Date now = VIAPet.getNow();


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
    } else
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
    viaPet.removeCurrentBooking(bookingToEdit);

    ArrayList<Date> allBookingDates = VIAPet.getDatesBetween(startDateOfBooking, endDateOfBooking);
    System.out.println(allBookingDates);
    for(int i = 0; i < allBookingDates.size() - 1;i++) {
      Date checkedDate = allBookingDates.get(i);
      if(viaPet.getNumberOfBookingsOnDate(checkedDate)>= 10) {
        Alert alert = new Alert(Alert.AlertType.WARNING,"NO SPACE IN THE KENNEL");
        alert.show();
        System.out.println("AAA");
        viaPet.addCurrentBooking(bookingToEdit);
        System.out.println("AAA");
        return;
      }
    }
    Booking newBooking = new Booking(startDateOfBooking,endDateOfBooking,selectedCustomer,selectedPet,price);

    viaPet.addCurrentBooking(newBooking);

    Alert alertForDoneEdit = new Alert(Alert.AlertType.INFORMATION, "Done editing booking");
    alertForDoneEdit.setHeaderText("Success");
    alertForDoneEdit.showAndWait();

    Stage stage = (Stage) saveEditBookingButton.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();

    loader.setLocation(getClass().getResource("Bookings.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stage.setScene(scene);
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
}
