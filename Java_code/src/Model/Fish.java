package Model;

public class Fish extends Pet
{
  private boolean isSaltWater;
  private String species;
  private boolean isPredator;

  public Fish(char gender, int age, String name, String color, double price,
      String comment, boolean isPredator, boolean isSaltWater, String species, boolean onSale)
  {
    super(gender, age, name, color, price, comment, onSale);
    this.isPredator = isPredator;
    this.isSaltWater = isSaltWater;
    this.species = species;
  }

  public void setSpecies(String species)
  {
    this.species = species;
  }

  public String getSpecies()
  {
    return species;
  }

  public void setPredator(boolean predator)
  {
    isPredator = predator;
  }

  public boolean isPredator()
  {
    return isPredator;
  }

  public void setSaltWater(boolean saltWater)
  {
    isSaltWater = saltWater;
  }

  public boolean isSaltWater()
  {
    return isSaltWater;
  }
}
