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

import static GUI.MainGUI.viaPet;

public class EditSaleController
{
  private Sale saleToEdit;

  @FXML
  private ComboBox customerListComboBox;

  @FXML
  private ComboBox petListComboBox;

  @FXML
  private TextField petFinalPriceTextField;

  @FXML
  private DatePicker datePicker;

  @FXML
  private Button saveEditSaleButton;

  @FXML
  private Button backEditSaleButton;

  @FXML
  private void initializeForCustomerList()
  {
    customerListComboBox.getItems().addAll(MainGUI.viaPet.getCustomerList().getCustomerList());
  }

  @FXML
  private void initializeForPetList()
  {
    for (int i = 0; i < viaPet.getPetList().size(); i++)
    {
      //only show if pet is onSale
      if (MainGUI.viaPet.getPetList().getPet(i).getOnSale())
      {
        petListComboBox.getItems().add(MainGUI.viaPet.getPetList().getPet(i));
      }
    }


  }

  public void setSale(Sale sale)
  {
    this.saleToEdit = sale;

    customerListComboBox.getSelectionModel().select(sale.getCustomer());
    petListComboBox.getSelectionModel().select(sale.getPet());
    petFinalPriceTextField.setText(String.valueOf(sale.getFinalPrice()));
    datePicker.setValue(sale.getDateOfSale().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
  }

  @FXML
  public void handleBackEditSale(ActionEvent e) throws IOException
  //this happen when clicked "Back" button
  {
    Stage stage = (Stage) backEditSaleButton.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();

    loader.setLocation(getClass().getResource("Sales.fxml"));
    Parent root = loader.load();
    SalesController salesController = loader.getController();
    salesController.displaySale();
    Scene scene = new Scene(root);
    stage.setScene(scene);
  }

  @FXML
  public void handleSaveEditSale(ActionEvent e) throws IOException
  //this happen when clicked "Save" button
  {
    Object selectedCustomerObject = customerListComboBox.getSelectionModel().getSelectedItem();
    Customer selectedCustomer = (Customer) selectedCustomerObject;
    double finalPrice;
    Object selectedPetObject = petListComboBox.getSelectionModel().getSelectedItem();
    Pet selectedPet = (Pet) selectedPetObject;

    //no need to have condition of if isEmpty for ComboBox

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

    //select the date
    Date dateOfSale;
    LocalDate datePickerValue = datePicker.getValue();
    //if no date given, automatically set to current time
    if(datePickerValue == null)
    {
      dateOfSale = VIAPet.getNow();
    }
    else
    {
      ZoneId defaultZoneId = ZoneId.systemDefault();
      dateOfSale = Date.from(datePickerValue.atStartOfDay(defaultZoneId).toInstant());
    }

    //if selectedPet is changed, change the previous one to true for onSale, and false for current one
    Pet currentPet = saleToEdit.getPet();
    Object newSelectedPetObject = petListComboBox.getSelectionModel().getSelectedItem();
    Pet newSelectedPet = (Pet) newSelectedPetObject;
    if (!currentPet.equals(selectedPet))
    {
      newSelectedPet.setOnSale(false);
      currentPet.setOnSale(true);
    }

    //update the sale Object
    saleToEdit.setCustomer(selectedCustomer);
    saleToEdit.setDateOfSale(dateOfSale);
    saleToEdit.setPet(selectedPet);
    saleToEdit.setFinalPrice(finalPrice);


    Alert alertForDoneEdit = new Alert(Alert.AlertType.INFORMATION, "Done editing sale");
    alertForDoneEdit.setHeaderText("Success");
    alertForDoneEdit.showAndWait();
    handleBackEditSale(e);
  }

}
