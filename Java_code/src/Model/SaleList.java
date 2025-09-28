package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class SaleList implements Serializable
{
  private ArrayList<Sale> sales;

  public SaleList()
  {
    this.sales = new ArrayList<>(0);
  }

  public void addSale(Sale sale)
  {
    if (sale != null)
    {
      sales.add(sale);
      System.out.println("done adding " + sale);
    }
    else
    {
      System.out.println("Invalid sale or sale is now empty");
    }
  }

  public Sale getSale(int index)
  {
    return sales.get(index);
  }

  public void removeSale(Sale sale)
  {
    if (sale != null)
    {
      sales.remove(sale);
      System.out.println("done removed " + sale);
    }
    else
    {
      System.out.println("Invalid sale");
    }
  }

  public ArrayList<Sale> getSaleList()
  {
    return sales;
  }

  public int size()
  {
    return sales.size();
  }

}
