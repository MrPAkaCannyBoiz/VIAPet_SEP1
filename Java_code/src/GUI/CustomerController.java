package GUI;

import Model.Customer;
import Model.CustomerList;
import Model.Pet;
import Model.VIAPet;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;;

public class CustomerController {
    VIAPet viaPet;

    @FXML private TableView<Customer> customerTableView;
    @FXML private TableColumn<Customer, String> nameColumn;
    @FXML private TableColumn<Customer, String> emailColumn;
    @FXML private TableColumn<Customer, String> phoneNoColumn;

    @FXML
    private Button addCustomerButton;

    @FXML
    private Button editCustomerButton;

    @FXML
    private Button backCustomerButton;

    @FXML
    public void handleClickAddCustomer(ActionEvent e) throws IOException {
        Stage stage = (Stage) addCustomerButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("AddCustomer.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


    @FXML
    public void handleClickEditCustomer(ActionEvent e) throws IOException {
        Stage stage = (Stage) editCustomerButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();

        Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "No customer selected to edit.");
            alert.show();
            return;
        }

        loader.setLocation(getClass().getResource("EditCustomer.fxml"));
        Parent root = loader.load();

        EditCustomerController editCustomerController =  loader.getController();
        editCustomerController.setCustomer(selectedCustomer);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


    @FXML
    public void handleBackCustomer(ActionEvent e) throws IOException{
        Stage stage = (Stage) backCustomerButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("MainPane.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void initialize() {
        viaPet = MainGUI.viaPet;
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));


        customerTableView.setItems(
                FXCollections.observableArrayList(viaPet.getCustomerList().getCustomerList()));
    }
}
