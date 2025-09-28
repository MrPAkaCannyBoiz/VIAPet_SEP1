package Model;

public class Rodent extends Pet
{
  private boolean isAgressive;

  public Rodent(char gender, int age, String name, String color, double price,
      String comment, boolean isAgressive, boolean onSale)
  {
    super(gender, age, name, color, price, comment, onSale);
    this.isAgressive = isAgressive;
  }

  public void setIsAgressive(boolean isAgressive)
  {
    this.isAgressive = isAgressive;
  }

  public boolean getIsAgressive()
  {
    return isAgressive;
  }
}