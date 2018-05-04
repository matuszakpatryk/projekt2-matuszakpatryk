package Interfaces;

import Models.Client;
import Models.Product;
import Models.Sell;
import java.util.List;

public interface IDbContext
{
    boolean AddClient(Client client);
    Client GetClientByName(String name);
    List<Client> GetAllClients();
    boolean DeleteClient(Client client);
    void EditClientName(String name, Client client);
    void EditClientSurname(String surname, Client client);
    void EditClientEmail(String email, Client client);

    boolean AddProduct(Product product);
    Product GetProductByName(String name);
    List<Product> GetAllProducts();
    boolean DeleteProduct(Product product);
    void EditProductPrice(double price, Product product);

    List<Sell> GetClientsSell(Client client);
    List<Product> GetProductsSell(Sell sell);
    boolean CreateOrder(Product product, Sell sell);

    boolean AddSell(Sell sell);
    Sell GetSellById(int id);
    boolean DeleteSell(Sell sell);
}
