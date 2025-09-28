package GUI;

import Model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static GUI.MainGUI.viaPet;

public class BookingController
{

BookingList bookings;

@FXML
private Button addBookingButton;

@FXML
private Button editBookingButton;

@FXML
private Button removeBookingButton;

@FXML
private Button backBookingButton;

public TableColumn endDateColumn;

public TableView bookingTableView;

public TableColumn customerColumn;

public TableColumn petTableColumn;

public TableColumn priceColumn;

public TableColumn startDateColumn;

Date dateNow;

  @FXML
  public void handleAddBooking(ActionEvent e) throws IOException
  {
    Stage stage= (Stage) addBookingButton.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();

    loader.setLocation(getClass().getResource("AddBooking.fxml"));
    Parent root = loader.load();
    AddBookingController addBookingController = loader.getController();
    Scene scene = new Scene(root);
    stage.setScene(scene);
  }

  @FXML
  private void initialize()
  {
    customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
    petTableColumn.setCellValueFactory(new PropertyValueFactory<>("pet"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
    endDateColumn.setCellValueFactory((new PropertyValueFactory<>("endDate")));

    dateNow = VIAPet.getNow();
    ArrayList<Booking> toRemove = new ArrayList<>();

    for (Booking booking : viaPet.getBookingList().getCurrentBookingList()) {
      if (booking.getEndDate().before(dateNow)) {
        viaPet.getBookingList().addBooking(booking);
        toRemove.add(booking); // Collect bookings to be removed
      }
    }

    viaPet.getBookingList().getCurrentBookingList().removeAll(toRemove);

    bookingTableView.setItems(FXCollections.observableArrayList(viaPet.getBookingList().getBookingList()));
    bookingTableView.getItems().add(new SeperatorBooking());
    bookingTableView.getItems().addAll(FXCollections.observableArrayList(viaPet.getBookingList().getCurrentBookingList()));
  }

  public void handleEditBooking(ActionEvent e) throws IOException
  {
    Booking selectedBooking = (Booking) bookingTableView.getSelectionModel().getSelectedItem();

    if (selectedBooking == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING, "No booking selected to edit.");
      alert.show();
      return;
    }
    if(selectedBooking.getEndDate().before(dateNow)) {
      Alert alert = new Alert(Alert.AlertType.WARNING, "You cannot edit booking in the past");
      alert.show();
      return;
    }
    // Load the EditBooking view
    FXMLLoader loader = new FXMLLoader(getClass().getResource("EditBooking.fxml"));
    Parent root = loader.load();

    // Pass the selected Booking to the EditBookingController
    EditBookingController editBookingController = loader.getController();
    //editBookingController.setViaPet(viaPet); // Pass viaPet object if needed
    editBookingController.setBooking(selectedBooking); // Pass selected sale to edit

    // Open the EditSale window
    Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
  }

  public void handleRemoveBooking(ActionEvent e) throws IOException
  {

    Booking selectedBooking = (Booking) bookingTableView.getSelectionModel().getSelectedItem();

    if (selectedBooking == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("No Selection");
      alert.setHeaderText(null);
      alert.setContentText("Please select a booking to remove.");
      alert.show();
    }
    else
    {
      viaPet.removeBooking(selectedBooking);
      viaPet.removeCurrentBooking(selectedBooking);
      int selectedRowByIndex = bookingTableView.getSelectionModel().getSelectedIndex();
      bookingTableView.getItems().remove(selectedRowByIndex);
    }
  }

  public void handleBackBooking(ActionEvent e) throws IOException
  {
    Stage stage = (Stage) backBookingButton.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();

    loader.setLocation(getClass().getResource("MainPane.fxml"));
    Parent root = loader.load();
    MainController mainController = loader.getController();
    //mainController.setViaPet(viaPet);
    Scene scene = new Scene(root);
    stage.setScene(scene);
  }

}
