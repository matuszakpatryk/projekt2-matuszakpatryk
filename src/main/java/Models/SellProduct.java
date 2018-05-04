package Models;

public class SellProduct
{
    public int ProductID;
    public int SellID;

    public SellProduct(int idProduct, int idOrder)
    {
        ProductID = idProduct;
        SellID = idOrder;
    }
}
