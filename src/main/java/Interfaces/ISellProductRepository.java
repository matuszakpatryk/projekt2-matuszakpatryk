package Interfaces;

import Models.Client;
import Models.Product;
import Models.Sell;

import java.util.List;

public interface ISellProductRepository
{
    List<Sell> GetClientSells(Client client);
    List<Product> GetProductSells(Sell sell);
    boolean CreateSell(Product product, Sell sell);
}
