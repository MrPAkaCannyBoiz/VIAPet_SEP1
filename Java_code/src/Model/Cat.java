package Model;

public class Cat extends Pet
{
  private String breed;
  private String breeder;

  public Cat(char gender, int age, String name, String color, double price,
      String comment, String breed, String breeder, boolean onSale)
  {
    super(gender, age, name, color, price, comment, onSale);
    this.breed = breed;
    this.breeder = breeder;
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
