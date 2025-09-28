package GUI;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Hashtable;

public class AddPetController
{

  @FXML private CheckBox onSaleCheck;
  @FXML private TextField nameTextField;

  @FXML private TextField ageTextField;

  @FXML private TextField genderTextField;

  @FXML private TextField colorTextField;

  @FXML private TextField priceTextField;

  @FXML private TextArea commentTextArea;

  @FXML private ComboBox animalTypeComboBox;

  @FXML private TextField breedTextField;

  @FXML private TextField breederTextField;

  @FXML private TextField speciesTextField;

  @FXML private TextField prefferedFoodTextField;

  @FXML private CheckBox isAggressiveCheck;

  @FXML private CheckBox isSaltWaterCheck;

  @FXML private CheckBox isPredatorCheck;

  @FXML private Button backAddPetButton;

  @FXML private Button saveAddPetButton;
  private Parent[] uniqueElements;
  private Hashtable<String, Parent[]> animalToComponents;

  @FXML private void initialize()
  {

    Parent[] uniq = {breedTextField, breederTextField, speciesTextField,
        prefferedFoodTextField, isAggressiveCheck, isSaltWaterCheck,
        isPredatorCheck};

    for (Parent parent : uniq)
    {
      parent.setDisable(true);
    }

    Parent[] rodentComponents = {isAggressiveCheck};
    Parent[] birdComponents = {speciesTextField, prefferedFoodTextField};
    Parent[] catComponents = {breedTextField, breederTextField};
    Parent[] dogComponents = {breedTextField, breederTextField};
    Parent[] fishComponents = {isSaltWaterCheck, speciesTextField,
        isPredatorCheck};
    Parent[] variousComponents = {speciesTextField};
    animalToComponents = new Hashtable<>();

    animalToComponents.put("Rodent", rodentComponents);
    animalToComponents.put("Bird", birdComponents);
    animalToComponents.put("Cat", catComponents);
    animalToComponents.put("Dog", dogComponents);
    animalToComponents.put("Fish", fishComponents);
    animalToComponents.put("Various", variousComponents);

    String[] animalTypes = {"Rodent", "Bird", "Cat", "Dog", "Fish", "Various"};

    animalTypeComboBox.getItems().addAll(animalTypes);
    animalTypeComboBox.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          handleSelectAnimal((String) oldValue, (String) newValue);
        });

    animalTypeComboBox.getSelectionModel().selectFirst();

  }

  public void handleSelectAnimal(String oldValue, String newValue)
  {
    if (oldValue != null)
    {
      Parent[] elementsToDisable = animalToComponents.get(oldValue);
      for (int i = 0; i < elementsToDisable.length; i++)
      {
        elementsToDisable[i].setDisable(true);
      }
    }
    Parent[] elementsToEnable = animalToComponents.get(newValue);
    for (int i = 0; i < elementsToEnable.length; i++)
    {
      elementsToEnable[i].setDisable(false);
    }
  }

  public void handleBackAddPet(ActionEvent actionEvent) throws IOException
  {
    Stage stage = (Stage) backAddPetButton.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();

    loader.setLocation(getClass().getResource("Pets.fxml"));
    Parent root = loader.load();
    PetController petController = loader.getController();
    Scene scene = new Scene(root);
    stage.setScene(scene);
  }

  public void handleSavePet(ActionEvent actionEvent) throws IOException
  {
    String name = nameTextField.getText();
    int age;
    char gender;
    String color;
    double price;
    String comment;
    boolean onSale = onSaleCheck.isSelected();

    try
    {
      age = Integer.parseInt(ageTextField.getText());
    }
    catch (NumberFormatException ex)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "Enter a valid number for age.");
      alert.show();
      return;
    }

    char[] genderValue = genderTextField.getText().toCharArray();
    if (genderValue.length != 1)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "Enter one character for gender");
      alert.show();
      return;
    }
    gender = genderValue[0];

    color = colorTextField.getText();

    try
    {
      price = Double.parseDouble(priceTextField.getText());
    }
    catch (NumberFormatException ex)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "Enter a valid number for price");
      alert.show();
      return;
    }

    comment = commentTextArea.getText();

    String selectedAnimal = (String) animalTypeComboBox.getValue();

    if (MainGUI.viaPet.getPet(gender, age, name, color, price, selectedAnimal)
        != null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "PET WITH PROVIDED ATTRIBUTES ALREADY EXISTS");
      alert.show();
      return;
    }

    try
    {
      switch (selectedAnimal)
      {
        case "Rodent":
          boolean isAggressive = isAggressiveCheck.isSelected();
          Rodent rodent = new Rodent(gender, age, name, color, price, comment,
              isAggressive, onSale);
          MainGUI.viaPet.addPet(rodent);
          System.out.println("Rodent");
          break;
        case "Bird":
          String speciesBird = speciesTextField.getText();
          String preferredFood = prefferedFoodTextField.getText();
          Bird bird = new Bird(gender, age, name, color, price, comment,
              speciesBird, preferredFood, onSale);
          MainGUI.viaPet.addPet(bird);
          System.out.println("Bird");
          break;
        case "Cat":
          String breedCat = breedTextField.getText();
          String breederCat = breederTextField.getText();
          Cat cat = new Cat(gender, age, name, color, price, comment, breedCat,
              breederCat, onSale);
          MainGUI.viaPet.addPet(cat);
          System.out.println("Cat");
          break;
        case "Dog":
          String breedDog = breedTextField.getText();
          String breederDog = breederTextField.getText();
          Dog dog = new Dog(gender, age, name, color, price, comment, breedDog,
              breederDog, onSale);
          MainGUI.viaPet.addPet(dog);
          System.out.println("Dog");
          break;
        case "Fish":
          boolean isSaltwater = isSaltWaterCheck.isSelected();
          String speciesFish = speciesTextField.getText();
          boolean isPredator = isPredatorCheck.isSelected();
          Fish fish = new Fish(gender, age, name, color, price, comment,
              isPredator, isSaltwater, speciesFish, onSale);
          MainGUI.viaPet.addPet(fish);
          System.out.println("Fish");
          break;
        case "Various":
          String speciesVarious = speciesTextField.getText();
          Various various = new Various(gender, age, name, color, price,
              comment, speciesVarious, onSale);
          MainGUI.viaPet.addPet(various);
          System.out.println("Various");
          break;
      }
      handleBackAddPet(actionEvent);
    }catch (IllegalArgumentException ex){
      Alert alert = new Alert(Alert.AlertType.WARNING, ex.getMessage());
      alert.show();
    }
  }
}
