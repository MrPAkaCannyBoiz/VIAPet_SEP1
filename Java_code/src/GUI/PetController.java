package GUI;

import Model.Pet;
import Model.PetList;
import Model.Sale;
import Model.VIAPet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.IOException;

import static GUI.MainGUI.viaPet;

public class PetController
{
  public Button removePetButton;
  @FXML private TableView<Pet> petsTableView;
  @FXML private TableColumn<Pet, String> nameColumn;
  @FXML private TableColumn<Pet, Integer> ageColumn;
  @FXML private TableColumn<Pet, Character> genderColumn;
  @FXML private TableColumn<Pet, String> colorColumn;
  @FXML private TableColumn<Pet, Double> priceColumn;
  @FXML private TableColumn<Pet, Boolean> onSaleColumn;
  @FXML private TableColumn<Pet, String> animalTypeColumn;

  @FXML private Button addPetButton;

  @FXML private Button editPetButton;

  @FXML private Button backPetButton;

  @FXML public void initialize()
  {
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
    genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
    colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    animalTypeColumn.setCellValueFactory(
        new PropertyValueFactory<>("animalType"));
    onSaleColumn.setCellValueFactory(new PropertyValueFactory<>("onSale"));

    petsTableView.setItems(
        FXCollections.observableArrayList(viaPet.getPetList().getPetList()));
  }

  @FXML public void handlerAddPet(ActionEvent e) throws IOException
  {
    Stage stage = (Stage) addPetButton.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();

    loader.setLocation(getClass().getResource("AddPet.fxml"));
    Parent root = loader.load();
    AddPetController addPetController = loader.getController();
    Scene scene = new Scene(root);
    stage.setScene(scene);
  }

  @FXML public void handlerBackPetButton(ActionEvent e) throws IOException
  {
    Stage stage = (Stage) backPetButton.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();

    loader.setLocation(getClass().getResource("MainPane.fxml"));
    Parent root = loader.load();
    MainController mainController = loader.getController();
    Scene scene = new Scene(root);
    stage.setScene(scene);
  }

  public void handlerEditPet(ActionEvent actionEvent) throws IOException
  {
    Pet selectedPet = petsTableView.getSelectionModel().getSelectedItem();

    if (selectedPet == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "No pet selected to edit.");
      alert.show();
      return;
    }

    FXMLLoader loader = new FXMLLoader(getClass().getResource("EditPet.fxml"));
    Parent root = loader.load();
    EditPetController editPetController = loader.getController();
    editPetController.initialize(selectedPet);
    Stage stage = (Stage) editPetButton.getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);

  }
}
