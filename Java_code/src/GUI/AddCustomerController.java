package GUI;

import Model.Customer;
import Model.VIAPet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCustomerController {
    VIAPet viaPet;

    @FXML
    private Button backAddCustomerButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneNoTextField;

    @FXML
    public void handleSaveCustomer(ActionEvent e) throws IOException{

        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String phoneNo = phoneNoTextField.getText();


        if(name == null || email == null || phoneNo == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"FIELDS CANNOT BE EMPTY");
            alert.show();
            return;
        }

        for(int i = 0; i < phoneNo.length();i++) {
            if(!((phoneNo.charAt(i) <= '9'  && phoneNo.charAt(i) >= '0') || phoneNo.charAt(i) == '+')) {
                Alert alert = new Alert(Alert.AlertType.WARNING,"Phone No only allows numbers and + sign");
                alert.show();
                return;
            }
        }

        if(viaPet.getCustomer(phoneNo) != null) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"CUSTOMER WITH PROVIDED PHONE NO ALREADY EXISTS");
            alert.show();
            return;
        }

        Customer customer = new Customer(name, email,phoneNo);
        viaPet.addCustomer(customer);
        handleBackAddCustomer();
    }

    @FXML
    public void  handleBackAddCustomer() throws IOException {
        Stage stage = (Stage) backAddCustomerButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("Customers.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void initialize() {
        viaPet = MainGUI.viaPet;
    }
}