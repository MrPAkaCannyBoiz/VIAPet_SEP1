package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class PetList implements Serializable
{
  private ArrayList<Pet> pets;

  public PetList()
  {
    this.pets = new ArrayList<>();
  }

  public void addPet(Pet pet)
  {
    if (pet != null)
    {
      pets.add(pet);
      System.out.println("Pet added: " + pet.getName());
      return;
    }
    else
    {
      System.out.println("can't added invalid pet");
    }
  }

  public Pet getPet(int index)
  {
    return pets.get(index);
  }

  public boolean removePet(Pet pet)
  {
    return pets.remove(pet);
  }

  public int size()
  {
    return pets.size();
  }

  public ArrayList<Pet> getPetList()
  {
    return pets;
  }

  public int indexOf(Pet pet)
  {
    return pets.indexOf(pet);
  }

  public void set(int index, Pet pet) throws IndexOutOfBoundsException
  {
    pets.set(index, pet);
  }
}