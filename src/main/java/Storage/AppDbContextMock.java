package Storage;

import Interfaces.IDbContext;
import Models.Client;
import Models.Product;
import Models.Sell;
import Models.SellProduct;

import java.util.ArrayList;
import java.util.List;

public class AppDbContextMock implements IDbContext
{
    private List<Client> _clients;
    private List<Product> _products;
    private List<Sell> _sells;
    private List<SellProduct> _sellsProducts;

    private int _idClient = 0;
    private int _idProduct = 0;
    private int _idOrder = 0;

    public AppDbContextMock()
    {
        _clients = new ArrayList<>();
        _products = new ArrayList<>();
        _sells = new ArrayList<>();
        _sellsProducts = new ArrayList<>();
    }

    @Override
    public boolean AddClient(Client client)
    {
        if(client == null)
        {
            return false;
        }
        else
        {
            client.ClientID = _idClient++;
            _clients.add(client);
            return true;
        }
    }

    @Override
    public Client GetClientByName(String name)
    {
        for (Client client : _clients)
        {
            if (client.Name == name)
            {
                return client;
            }
        }
        return null;
    }

    @Override
    public List<Client> GetAllClients()
    {
        return _clients;
    }

    @Override
    public boolean DeleteClient(Client _client)
    {
        if(_client == null)
        {
            return false;
        }
        else
        {
            for (Client client : _clients)
            {
                if (client.equals(_client))
                {
                    _clients.remove(client);
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public void EditClientName(String name, Client _client)
    {
        for (Client client : _clients)
        {
            if(client.equals(_client))
            {
                client.Name = name;
            }
        }
    }

    @Override
    public void EditClientSurname(String surname, Client _client)
    {
        for (Client client : _clients)
        {
            if(client.equals(_client))
            {
                client.Surname = surname;
            }
        }
    }

    @Override
    public void EditClientEmail(String email, Client _client)
    {
        for (Client client : _clients)
        {
            if(client.equals(_client))
            {
                client.Email = email;
            }
        }
    }

    @Override
    public boolean AddProduct(Product product)
    {
        if(product == null)
        {
            return false;
        }
        else
        {
            product.ProductID = _idProduct++;
            _products.add(product);
            return true;
        }
    }

    @Override
    public Product GetProductByName(String name)
    {
        for (Product product : _products)
        {
            if (product.Name == name)
            {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> GetAllProducts()
    {
        return _products;
    }

    @Override
    public boolean DeleteProduct(Product _product)
    {
        if(_product == null)
        {
            return false;
        }
        else
        {
            for (Client product : _clients)
            {
                if (product.equals(_product))
                {
                    _products.remove(product);
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public void EditProductPrice(double price, Product _product)
    {
        for (Product product : _products)
        {
            if(product.equals(_product))
            {
                product.Price = price;
            }
        }
    }

    @Override
    public void EditProductQuantity(int quantity, Product _product)
    {
        for (Product product : _products)
        {
            if(product.equals(_product))
            {
                product.Quantity = quantity;
            }
        }
    }

    @Override
    public List<Sell> GetClientSells(Client client)
    {
        List<Sell> clientSells = new ArrayList<>();

        for (Sell order : _sells)
        {
            if(order.ClientID == client.ClientID)
            {
                clientSells.add(order);
            }
        }
        return clientSells;
    }

    @Override
    public List<Product> GetProductSells(Sell sell)
    {
        List<Product> products = new ArrayList<>();

        for (SellProduct sells : _sellsProducts)
        {
            if(sells.SellID == sells.SellID)
            {
                products.add(_products.get(sells.ProductID));
            }
        }
        return products;
    }

    @Override
    public boolean CreateSell(Product product, Sell sell)
    {
        if(_products.contains(product) && _sells.contains(sell))
        {
            _sellsProducts.add(new SellProduct(product.ProductID, sell.SellID));
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean AddSell(Sell sell)
    {
        if(sell == null)
        {
            return false;
        }
        else
        {
            sell.SellID = _idOrder++;
            _sells.add(sell);
        }
        return true;
    }

    @Override
    public Sell GetSellById(int id)
    {
        for (Sell sell : _sells)
        {
            if (sell.SellID == id)
            {
                return sell;
            }
        }
        return null;
    }

    @Override
    public boolean DeleteSell(Sell _sell)
    {
        if(_sell == null)
        {
            return false;
        }
        else
        {
            for (Sell sell : _sells)
            {
                if (sell.equals(_sell))
                {
                    _sells.remove(sell);
                    return true;
                }
            }
        }
        return false;
    }
}
