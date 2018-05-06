import Controllers.ProductController;
import Interfaces.IProductRepository;
import Models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MockitoProductTest
{
    private final String PRODUCT_NAME = "Amarylis";
    private final double PRODUCT_PRICE = 12.45;
    private final int PRODUCT_QUANTITY = 10;

    private final String PRODUCT_NAME2 = "Eustoma";
    private final double PRODUCT_PRICE2 = 4.45;
    private final int PRODUCT_QUANTITY2 = 20;

    private final String PRODUCT_NAME3 = "Bratek";
    private final double PRODUCT_PRICE3 = 2.45;
    private final int PRODUCT_QUANTITY3 = 30;

    private final double NEW_PRICE = 11.11;

    private Product product;

    @Mock
    private IProductRepository dbMock;

    //@InjectMocks
    private ProductController productController;

    @BeforeEach
    void SetUp ()
    {
        dbMock = mock(IProductRepository.class);
        productController = new ProductController(dbMock);
        product = new Product(PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_QUANTITY);
    }

    @Test
    void CheckAddProduct_ShouldAddNewProductTest()
    {
        when(dbMock.AddProduct(any(Product.class))).thenReturn(true);

        assertTrue(productController.AddProduct(product));
        verify(dbMock, times(1)).AddProduct(any(Product.class));
    }

    @Test
    void CheckAddProduct_ShouldNotAddProductTest()
    {
        when(dbMock.AddProduct(any(Product.class))).thenReturn(false);

        assertFalse(productController.AddProduct(product));
        verify(dbMock, times(1)).AddProduct(any(Product.class));
    }

    @Test
    void CheckAddProduct_ShouldThrowExceptionWhenProductIsNotCorrectTest()
    {
        Product product = new Product("Smth", 10, 10);
        when(dbMock.AddProduct(any(Product.class))).thenThrow(new IllegalArgumentException("Wrong!"));

        assertThrows(IllegalArgumentException.class, () ->
        {
            productController.AddProduct(product);
        });
    }

    @Test
    void CheckAddProducts_ShouldReturnProperValueTest()
    {
        Product product2 = new Product(PRODUCT_NAME2, PRODUCT_PRICE2, PRODUCT_QUANTITY2);
        Product product3 = new Product(PRODUCT_NAME3, PRODUCT_QUANTITY2, PRODUCT_QUANTITY3);

        List<Product> products = new ArrayList<>(Arrays.asList(new Product[]{product, product2, product3}));

        when(dbMock.AddProduct(any(Product.class))).thenReturn(true, true, true, true);

        assertEquals(productController.AddProducts(products), 3);
        verify(dbMock, times(3)).AddProduct(any(Product.class));
    }

    @Test
    void CheckAddProduct_ShouldNotAddEveryProductTest()
    {
        Product product2 = new Product(PRODUCT_NAME2, PRODUCT_PRICE2, PRODUCT_QUANTITY2);
        Product product3 = new Product(PRODUCT_NAME3, PRODUCT_QUANTITY2, PRODUCT_QUANTITY3);

        List<Product> products = new ArrayList<>(Arrays.asList(new Product[]{product, product2, product3}));

        when(dbMock.AddProduct(any(Product.class))).thenReturn(true, false, true, false);

        assertEquals(2, productController.AddProducts(products));
        verify(dbMock, times(3)).AddProduct(any(Product.class));
    }

    @Test
    void CheckGetProductByName_ShouldReturnProperProductTest()
    {
        doReturn(product).when(dbMock).GetProductByName(PRODUCT_NAME);

        assertEquals(productController.GetProductByName(PRODUCT_NAME), product);
        verify(dbMock, times(1)).GetProductByName(PRODUCT_NAME);
    }

    @Test
    void CheckGetAllProducts_ShouldReturnEmptyListTest()
    {
        doReturn(new ArrayList<>()).when(dbMock).GetAllProducts();

        assertThat(productController.GetAllProducts()).hasSize(0);
        verify(dbMock, times(1)).GetAllProducts();
    }

    @Test
    void CheckGetAllProducts_ShouldReturnProperValueTest()
    {
        Product product2 = new Product(PRODUCT_NAME2, PRODUCT_PRICE2, PRODUCT_QUANTITY2);
        Product product3 = new Product(PRODUCT_NAME3, PRODUCT_QUANTITY2, PRODUCT_QUANTITY3);

        List<Product> products = new ArrayList<>(Arrays.asList(new Product[]{product, product2, product3}));

        doReturn(products).when(dbMock).GetAllProducts();

        assertThat(productController.GetAllProducts()).hasSize(3).containsOnly(product, product2, product3);

        verify(dbMock, times(1)).GetAllProducts();
    }

    @Test
    void CheckDeleteProduct_ShouldDeleteProductTest()
    {
        when(dbMock.DeleteProduct(product)).thenReturn(true);
        assertThat(productController.DeleteProduct(product)).isTrue();
        verify(dbMock, times(1)).DeleteProduct(product);
    }

    @Test
    void CheckDeleteProduct_ShouldReturnFalseTest()
    {
        when(dbMock.DeleteProduct(product)).thenReturn(false);
        assertThat(productController.DeleteProduct(product)).isFalse();
        verify(dbMock, times(1)).DeleteProduct(product);
    }

    @Test
    void CheckEditProductPrice_ShouldEditProductPriceTest()
    {
        doAnswer(invocationOnMock ->
        {
            Object[] args = invocationOnMock.getArguments();
            if(args.length == 2)
            {
                double price = (double)args[0];
                Product product = (Product)args[1];
                product.Price = price;
            }
            return null;
        }).when(dbMock).EditProductPrice(any(Double.class), any(Product.class));

        productController.EditProductPrice(NEW_PRICE, product);
        assertThat(product.Price).isEqualTo(NEW_PRICE);
    }

    @Test
    void CheckEditProductQuantity_ShouldEditProductQuantityTest()
    {
        doAnswer(invocationOnMock ->
        {
            Object[] args = invocationOnMock.getArguments();
            if(args.length == 2)
            {
                int quantity = (int)args[0];
                Product _product = (Product)args[1];
                _product.Quantity = quantity;
            }
            return null;
        }).when(dbMock).EditProductQuantity(any(Integer.class), any(Product.class));

        int NEW_QUANTITY = 5;
        productController.EditProductQuantity(NEW_QUANTITY, product);
        assertThat(product.Quantity).isEqualTo(NEW_QUANTITY);
    }
}
