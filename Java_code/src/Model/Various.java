package Model;

public class Various extends Pet
{
  private String species;

  public Various(char gender, int age, String name, String color, double price,
      String comment, String species, boolean onSale)
  {
    super(gender, age, name, color, price, comment, onSale);
    this.species = species;
  }

  public String getSpecies()
  {
    return species;
  }

  public void setSpecies(String species)
  {
    this.species = species;
  }
}
