package GUI;

import Model.*;
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

public class EditPetController
{
  @FXML private ComboBox animalTypeComboBox;
  @FXML private TextField breedTextField;
  @FXML private TextField nameTextField;
  @FXML private TextField ageTextField;
  @FXML TextField genderTextField;
  @FXML private TextField breederTextField;
  @FXML private TextField colorTextField;
  @FXML private TextField speciesTextField;
  @FXML private TextField priceTextField;
  @FXML private TextField prefferedFoodTextField;
  @FXML private TextArea commentTextArea;
  @FXML private CheckBox isAggressiveCheck;
  @FXML private CheckBox isSaltWaterCheck;
  @FXML private CheckBox isPredatorCheck;
 @FXML private CheckBox onSaleCheck;
  @FXML private Button backEditPetButton;
  @FXML private Button updatePetButton;
  private Parent[] uniqueElements;
  private Hashtable<String, Parent[]> animalToComponents;

  private Pet selectedPet;


  @FXML void initialize(Pet selectedPet){

    Parent[] uniq = {breedTextField, breederTextField, speciesTextField,
        prefferedFoodTextField, isAggressiveCheck, isSaltWaterCheck,
        isPredatorCheck};

    for (int i = 0; i < uniq.length; i++)
    {
      uniq[i].setDisable(true);
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

    this.selectedPet= selectedPet;
    nameTextField.setText(selectedPet.getName());
    ageTextField.setText(String.valueOf(selectedPet.getAge()));
    genderTextField.setText(String.valueOf(selectedPet.getGender()));
    colorTextField.setText(selectedPet.getColor());
    priceTextField.setText(String.valueOf(selectedPet.getPrice()));
    commentTextArea.setText(selectedPet.getComment());
   onSaleCheck.setSelected(selectedPet.getOnSale());
   switch (selectedPet.getAnimalType()){
     case "Rodent":
       isAggressiveCheck.setSelected(((Rodent)selectedPet).getIsAgressive());
       break;
     case "Bird":
       speciesTextField.setText(((Bird) selectedPet).getSpecies());
       prefferedFoodTextField.setText(((Bird) selectedPet).getPreferredFood());
       break;
     case "Cat":
       breedTextField.setText(((Cat) selectedPet).getBreed());
       breederTextField.setText(((Cat)selectedPet).getBreeder());
       break;
     case "Dog":
       breedTextField.setText(((Dog)selectedPet).getBreed());
       breederTextField.setText(((Dog)selectedPet).getBreeder());
       break;
     case "Fish":
       speciesTextField.setText(((Fish)selectedPet).getSpecies());
       isSaltWaterCheck.setSelected(((Fish)selectedPet).isSaltWater());
       isPredatorCheck.setSelected(((Fish)selectedPet).isPredator());
       break;
     case "Various":
       speciesTextField.setText(((Various) selectedPet).getSpecies());
       break;
     default:
       Alert alert = new Alert(Alert.AlertType.WARNING, "Unknown animal type.");
       alert.show();
       break;
   }
    animalTypeComboBox.getSelectionModel().select(selectedPet.getAnimalType());
  }

  public void handleBackEditPet(ActionEvent actionEvent) throws IOException
  {
    Stage stage = (Stage) backEditPetButton.getScene().getWindow();
    FXMLLoader loader = new FXMLLoader();

    loader.setLocation(getClass().getResource("Pets.fxml"));
    Parent root = loader.load();
    PetController petController = loader.getController();
    Scene scene = new Scene(root);
    stage.setScene(scene);
  }

  public void handleUpdatePet(ActionEvent actionEvent) throws IOException
  {
    int index = MainGUI.viaPet.getPetList().indexOf(selectedPet);
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


    try{
      switch (selectedAnimal)
      {
        case "Rodent":
          if (selectedPet instanceof Rodent)
          {
            Rodent rodent = (Rodent) selectedPet;
            rodent.setName(name);
            rodent.setAge(age);
            rodent.setGender(gender);
            rodent.setColor(color);
            rodent.setPrice(price);
            rodent.setComment(comment);
            rodent.setOnSale(onSale);
            rodent.setIsAgressive(isAggressiveCheck.isSelected());
          }
          break;
        case "Bird":
          if (selectedPet instanceof Bird)
          {
            Bird bird = (Bird) selectedPet;
            bird.setName(name);
            bird.setAge(age);
            bird.setGender(gender);
            bird.setColor(color);
            bird.setPrice(price);
            bird.setComment(comment);
            bird.setOnSale(onSale);
            bird.setSpecies(speciesTextField.getText());
            bird.setPreferredFood(prefferedFoodTextField.getText());
          }
          break;
        case "Cat":
          if (selectedPet instanceof Cat)
          {
            Cat cat = (Cat) selectedPet;
            cat.setName(name);
            cat.setAge(age);
            cat.setGender(gender);
            cat.setColor(color);
            cat.setPrice(price);
            cat.setComment(comment);
            cat.setOnSale(onSale);
            cat.setBreed(breedTextField.getText());
            cat.setBreeder(breederTextField.getText());
          }
          break;
        case "Dog":
          if (selectedPet instanceof Dog)
          {
            Dog dog = (Dog) selectedPet;
            dog.setName(name);
            dog.setAge(age);
            dog.setGender(gender);
            dog.setColor(color);
            dog.setPrice(price);
            dog.setComment(comment);
            dog.setOnSale(onSale);
            dog.setBreed(breedTextField.getText());
            dog.setBreeder(breederTextField.getText());
          }
          break;
        case "Fish":
          if (selectedPet instanceof Fish)
          {
            Fish fish = (Fish) selectedPet;
            fish.setName(name);
            fish.setAge(age);
            fish.setGender(gender);
            fish.setColor(color);
            fish.setPrice(price);
            fish.setComment(comment);
            fish.setOnSale(onSale);
            fish.setSpecies(speciesTextField.getText());
            fish.setSaltWater(isSaltWaterCheck.isSelected());
            fish.setPredator(isPredatorCheck.isSelected());
          }
          break;
        case "Various":
          if (selectedPet instanceof Various)
          {
            Various various = (Various) selectedPet;
            various.setName(name);
            various.setAge(age);
            various.setGender(gender);
            various.setColor(color);
            various.setPrice(price);
            various.setComment(comment);
            various.setOnSale(onSale);
            various.setSpecies(speciesTextField.getText());
          }
          break;
        default:
          Alert alert = new Alert(Alert.AlertType.WARNING,
              "Unknown animal type.");
          alert.show();
          return;
      }
        handleBackEditPet(actionEvent);
    }catch (IllegalArgumentException ex){
      Alert alert = new Alert(Alert.AlertType.WARNING, ex.getMessage());
      alert.show();
    }
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
}
