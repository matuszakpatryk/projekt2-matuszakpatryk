package Interfaces;

import Models.Product;

import java.util.List;

public interface IProductRepository
{
    boolean AddProduct(Product product);
    Product GetProductByName(String name);
    List<Product> GetAllProducts();
    boolean DeleteProduct(Product product);
    void EditProductPrice(double price, Product product);
    void EditProductQuantity(int quantity, Product product);
}
