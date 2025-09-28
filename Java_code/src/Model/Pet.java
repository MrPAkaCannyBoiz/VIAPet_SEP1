package Model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Pet implements Serializable
{
  private char gender;
  private int age;
  private String name, color, comment;
  private double price;
  private boolean onSale;

  public Pet(char gender, int age, String name, String color, double price,
      String comment,boolean onSale)
  {
    this.gender = gender;
    if (age < 0)
    {
      throw new IllegalArgumentException("Age can not be a negative number");
    }
    this.age = age;
    this.name = name;
    this.color = color;
    if (price < 0)
    {
      throw new IllegalArgumentException("Price can not be negative");
    }
    this.price = price;
    this.comment = comment;
    this.onSale =  onSale;
  }

  public void setGender(char gender)
  {
    this.gender = gender;
  }

  public void setAge(int age)
  {
    if (age < 0)
    {
      throw new IllegalArgumentException("Age can not be a negative number");
    }
    this.age = age;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setColor(String color)
  {
    this.color = color;
  }

  public void setPrice(double price)
  {
    if (price < 0)
    {
      throw new IllegalArgumentException("Price can not be negative");
    }
    this.price = price;
  }
  public void setOnSale(boolean onSale){this.onSale=onSale;}

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public char getGender()
  {
    return gender;
  }

  public int getAge()
  {
    return age;
  }

  public String getName()
  {
    return name;
  }

  public String getColor()
  {
    return color;
  }

  public double getPrice()
  {
    return price;
  }

  public boolean getOnSale()
  {
    return onSale;
  }

  public String getComment()
  {
    return comment;
  }

  public String toString()
  {
    return "Gender: " + gender + ", Age: " + age + ", Name: " + name
        + ", Color; " + color + ", price: " + price + ", comment: " + comment + "is for sale: " + onSale;
  }

  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Pet petV2 = (Pet) obj;
    return gender == petV2.gender && age == petV2.age && Objects.equals(name,
        petV2.name) && Objects.equals(color, petV2.color)
        && price == petV2.price && Objects.equals(comment, petV2.comment)&& onSale== petV2.onSale;
  }

  public String getAnimalType()
  {
    return getClass().getSimpleName();
  }

}