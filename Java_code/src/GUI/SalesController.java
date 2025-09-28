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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

import static GUI.MainGUI.viaPet;

public class SalesController
{
  @FXML
  private TableView<Sale> saleTableView;

  @FXML
  private TableColumn<Sale, Customer> customerPhoneColumn;

  @FXML
  private TableColumn<Sale, Pet> petTableColumn;

  @FXML
  private TableColumn<Sale, Double> finalPriceColumn;

  @FXML
  private TableColumn<Sale, Date> dateOfSaleColumn;

  @FXML
  private Button addSaleButton;

  @FXML
  private Button removeSaleButton;

  @FXML
  private Button editSaleButton;

  @FXML
  private Button backSaleButton;

  public void displaySale()
  {
    //    for (int i = 0; i < viaPet.getSaleList().size() ; i++)
    //    {
    //      Sale sale = viaPet.getSale(i);
    //      TableRow newSale = new TableRow();
    //      saleTableView.getColumns().add(sale);
    //    }
    saleTableView.setItems(FXCollections.observableArrayList(viaPet.getSaleList().getSaleList()));
  }

  @FXML
  private void initialize()
  {
    //PropertyValueFactory use getter of the class
    customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
    finalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
    petTableColumn.setCellValueFactory(new PropertyValueFactory<>("pet"));
    dateOfSaleColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfSale"));

    // text wrapping for customer column
    customerPhoneColumn.setCellFactory(column -> new TableCell<>()
    {
      private final Text text = new Text();

      {
        text.wrappingWidthProperty().bind(customerPhoneColumn.widthProperty().subtract(10)); // Adjust for padding
        setGraphic(text);
      }

      @Override
      //since this tableColumn expected returning the Customer, expect the parameter as the Customer
      // so chosen getter in Sale class need to return Customer as well
      protected void updateItem(Customer item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null)
        {
          text.setText(null);
        } else
        {
          text.setText(item.getName() + "/ " + item.getPhoneNo() + "/ " + item.getEmail());
        }
      }
    });

    // text wrapping for pet column
    petTableColumn.setCellFactory(column -> new TableCell<>()
    {
      private final Text text = new Text();

      {
        text.wrappingWidthProperty().bind(petTableColumn.widthProperty().subtract(10)); // Adjust for padding
        setGraphic(text);
      }

      @Override
      // since this tableColumn and updateItem expected returning or
      // expect the parameter as the Customer, so chosen getter Sale class need to return Customer as well
      protected void updateItem(Pet item, boolean empty)
      {
        super.updateItem(item, empty);
        if (empty || item == null)
        {
          text.setText(null);
        } else
        {
          text.setText(item.getName() + " (" + item.getAnimalType() + ")");
        }
      }
      });

    dateOfSaleColumn.setCellFactory(column -> new TableCell<>()
    {
      private final Text text = new Text();
      {
        text.wrappingWidthProperty().bind(dateOfSaleColumn.widthProperty().subtract(10));
        setGraphic(text);
      }
      @Override
      protected void updateItem(Date item, boolean empty)
      {
        super.updateItem(item, empty);
        if (item == null || empty)
        {
          text.setText(null);
        }
        else
        {
          text.setText(String.valueOf(item));
        }
      }

    });

    //set the data on tableView
    saleTableView.setItems(FXCollections.observableArrayList(viaPet.getSaleList().getSaleList()));
  }

  @FXML
  public void handleAddSale(ActionEvent e) throws IOException
  {
    Stage stage= (Stage) addSaleButton.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("AddSale.fxml"));
    Parent root = loader.load();


    Scene scene = new Scene(root);
    stage.setScene(scene);


    saleTableView.setItems(FXCollections.observableArrayList(viaPet.getSaleList().getSaleList()));
  }

  public void handleEditSale(ActionEvent e) throws IOException
  {
    Sale selectedSale = saleTableView.getSelectionModel().getSelectedItem();

    if (selectedSale == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING, "No sale selected to edit.");
      alert.show();
      return;
    }
    // Load the EditSale view
   FXMLLoader loader = new FXMLLoader(getClass().getResource("EditSale.fxml"));
   Parent root = loader.load();

    // Pass the selected Sale to the EditSaleController
    EditSaleController editSaleController = loader.getController();
//    editSaleController.setViaPet(viaPet); // Pass viaPet object if needed
    editSaleController.setSale(selectedSale); // Pass selected sale to edit

    // Open the EditSale window
    Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
  }

  public void handleRemoveSale(ActionEvent e) throws IOException
  {

    Sale selectedPetOnTable = saleTableView.getSelectionModel().getSelectedItem();
    
    if (selectedPetOnTable == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("No Selection");
      alert.setHeaderText(null);
      alert.setContentText("Please select a sale to remove.");
      alert.show();
    }
    else
    {
      viaPet.getSaleList().removeSale(selectedPetOnTable);
      int selectedRowByIndex = saleTableView.getSelectionModel().getSelectedIndex();
      saleTableView.getItems().remove(selectedRowByIndex);
    }
  }

  public void handleBackSale(ActionEvent e) throws IOException
  {
    Stage stage = (Stage) backSaleButton.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();

    loader.setLocation(getClass().getResource("MainPane.fxml"));
    Parent root = loader.load();
    //MainController mainController = loader.getController();
    //mainController.setViaPet(viaPet);
    Scene scene = new Scene(root);
    stage.setScene(scene);
  }

}