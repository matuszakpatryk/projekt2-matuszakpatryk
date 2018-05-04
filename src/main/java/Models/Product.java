package Models;

public class Product
{
    public int ProductID;
    public String Name;
    public double Price;
    public int Quantity;

    public Product(String name, double price, int quantity)
    {
        Name = name;
        Price = price;
        Quantity = quantity;
    }
}
