package GUI;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class AddSaleController
{
  @FXML
  private ComboBox customerListComboBox;

  @FXML
  private ComboBox petListComboBox;

  @FXML
  private TextField petFinalPriceTextField;

  @FXML
  private DatePicker datePicker;

  @FXML
  private Button saveAddSaleButton;

  @FXML
  private Button backAddSaleButton;

  @FXML
  private void initializeForCustomerList()
  {
    customerListComboBox.getItems().addAll(MainGUI.viaPet.getCustomerList().getCustomerList());
  }

  @FXML
  private void initializeForPetList()
  {
    //show the list in comboBox
    for (int i = 0; i < MainGUI.viaPet.getPetList().size(); i++)
    {
      //only show if pet is onSale
      if (MainGUI.viaPet.getPetList().getPet(i).getOnSale())
      {
        petListComboBox.getItems().add(MainGUI.viaPet.getPetList().getPet(i));
      }
    }
  }

  @FXML
  public void handleBackAddSale(ActionEvent e) throws IOException
  //this happen when clicked "Back" button
  {
    Stage stage = (Stage) backAddSaleButton.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();

    loader.setLocation(getClass().getResource("Sales.fxml"));
    Parent root = loader.load();
    SalesController salesController = loader.getController();
    salesController.displaySale();
    Scene scene = new Scene(root);
    stage.setScene(scene);
  }
  @FXML
  public void handleSaveAddSale(ActionEvent e) throws IOException
  //this happen when clicked "Save" button
  {
    Object selectedCustomerObject = customerListComboBox.getSelectionModel().getSelectedItem();
    Customer selectedCustomer = (Customer) selectedCustomerObject;
    double finalPrice;
    Object selectedPetObject = petListComboBox.getSelectionModel().getSelectedItem();
    Pet selectedPet = (Pet) selectedPetObject;

    //check if they choose something in comboBox
    if (customerListComboBox.getSelectionModel().isEmpty())
    {
      Alert alert = new Alert(Alert.AlertType.WARNING, "Please select customer");
      alert.show();
      return;
    }

    if(petListComboBox.getSelectionModel().isEmpty())
    {
      Alert alert = new Alert(Alert.AlertType.WARNING, "Please select pet");
      alert.show();
      return;
    }

    //check the valid final price
    try
    {
      finalPrice = Double.parseDouble(petFinalPriceTextField.getText());

      if(finalPrice < 0) {
        Alert alert = new Alert(Alert.AlertType.WARNING,"FINAL PRICE CANNOT BE NEGATIVE NUMBER");
        alert.show();
        return;
      }
    }
    catch (NumberFormatException ex)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING,"FINAL PRICE IS NOT A NUMBER");
      alert.show();
      return;
    }

    Date dateOfSale;
    LocalDate datePickerValue = datePicker.getValue();
    if(datePickerValue == null)
    {
      dateOfSale = VIAPet.getNow();
    }
    else
    {
      ZoneId defaultZoneId = ZoneId.systemDefault();
      dateOfSale = Date.from(datePickerValue.atStartOfDay(defaultZoneId).toInstant());
    }



    //add the sale and make the choose pet no longer on sale
    Sale newSale = new Sale(finalPrice, dateOfSale, selectedCustomer, selectedPet);
    MainGUI.viaPet.addSale(newSale);
    selectedPet.setOnSale(false);
    Alert alert = new Alert(Alert.AlertType.INFORMATION,"Success adding sale");
    alert.setHeaderText("Success");
    alert.showAndWait();
    handleBackAddSale(e);
  }

}
