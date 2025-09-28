package Model;

public class Dog extends Pet
{
  private String breed;
  private String breeder;

  public Dog(char gender, int age, String name, String color, double price,
      String comment, String breeder, String breed, boolean onSale)
  {
    super(gender, age, name, color, price, comment, onSale);
    this.breeder = breeder;
    this.breed = breed;
  }

  public void setBreed(String breed)
  {
    this.breed = breed;
  }

  public String getBreed()
  {
    return breed;
  }

  public void setBreeder(String breeder)
  {
    this.breeder = breeder;
  }

  public String getBreeder()
  {
    return breeder;
  }
}
