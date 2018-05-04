package Controllers;

import Interfaces.IDbContext;
import Interfaces.ISellProductRepository;
import Interfaces.ISellRepository;
import Models.Client;
import Models.Product;
import Models.Sell;
import org.jongo.marshall.jackson.oid.Id;

import java.util.List;

public class SellController implements ISellRepository, ISellProductRepository
{
    IDbContext _context;

    public SellController(IDbContext context)
    {
        _context = context;
    }

    public boolean AddSell(Sell sell)
    {
        return _context.AddSell(sell);
    }

    public Sell GetSellById(int id)
    {
        return _context.GetSellById(id);
    }

    public boolean DeleteSell(Sell sell)
    {
        return _context.DeleteSell(sell);
    }

    public List<Sell> GetClientSells(Client client)
    {

        return _context.GetClientSells(client);
    }

    public List<Product> GetProductSells(Sell sell)
    {
        return _context.GetProductSells(sell);
    }

    public boolean CreateSell(Product product, Sell sell)
    {
        return _context.CreateSell(product, sell);
    }
}
