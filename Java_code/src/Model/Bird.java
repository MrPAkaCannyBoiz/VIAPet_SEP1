package Model;

public class Bird extends Pet
{
  private String species;
  private String preferredFood;

  public Bird(char gender, int age, String name, String color, double price,
      String comment, String species, String preferredFood, boolean onSale)
  {
    super(gender, age, name, color, price, comment,onSale);
    this.species = species;
    this.preferredFood = preferredFood;
  }

  public void setSpecies(String species)
  {
    this.species = species;
  }

  public String getSpecies()
  {
    return species;
  }

  public void setPreferredFood(String preferredFood)
  {
    this.preferredFood = preferredFood;
  }

  public String getPreferredFood()
  {
    return preferredFood;
  }
}