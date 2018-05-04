package Controllers;

import Interfaces.IDbContext;
import Interfaces.IProductRepository;
import Models.Product;

import java.util.List;

public class ProductController implements IProductRepository
{
    IProductRepository _iDbContext;

    public ProductController(IProductRepository _iDbContext)
    {
        this._iDbContext = _iDbContext;
    }

    public boolean AddProduct (Product product)
    {
        return _iDbContext.AddProduct(product);
    }

    public int AddProducts (List<Product> products)
    {
        int res = 0;
        for (Product product : products)
        {
            if(_iDbContext.AddProduct(product))
            {
                res++;
            }
        }
        return res;
    }

    public Product GetProductByName(String name)
    {
        return _iDbContext.GetProductByName(name);
    }

    public List<Product> GetAllProducts()
    {
        return _iDbContext.GetAllProducts();
    }

    public boolean DeleteProduct(Product product)
    {
        return _iDbContext.DeleteProduct(product);
    }

    public void EditProductPrice(double price, Product product)
    {
        _iDbContext.EditProductPrice(price, product);
    }

    public void EditProductQuantity(int quantity, Product product)
    {
        _iDbContext.EditProductQuantity(quantity, product);
    }
}
