import Controllers.SellController;
import Interfaces.IDbContext;
import Interfaces.ISellRepository;
import Models.Client;
import Models.Product;
import Models.Sell;
import Storage.AppDbContextMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomMockTest
{
    private final String CLIENT_NAME = "Patryk";
    private final String CLIENT_SURNAME = "Matuszak";
    private final String CLIENT_EMAIL = "test@gmail.com";

    private final String CLIENT_NAME2 = "Marek";
    private final String CLIENT_SURNAME2 = "Tatarek";
    private final String CLIENT_EMAIL2 = "testtt@gmail.com";

    private final String CLIENT_NAME3 = "Michal";
    private final String CLIENT_SURNAME3 = "Kichal";
    private final String CLIENT_EMAIL3 = "tescik@gmail.com";

    private final String NEW_NAME = "Weronika";
    private final String NEW_SURNAME = "Blika";
    private final String NEW_EMAIL = "costam@wp.pl";

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

    private Sell sell;
    private Client client;

    private IDbContext dbMock;
    private SellController sellController;

    @BeforeEach
    void SetUp ()
    {
        dbMock = new AppDbContextMock();
        sellController = new SellController(dbMock);
        client = new Client (CLIENT_NAME, CLIENT_SURNAME, CLIENT_EMAIL);
        sell = new Sell(0);
    }

    @Test
    void CheckAddSell_ShouldAddSell ()
    {
        boolean resultult = sellController.AddSell(sell);
        assertTrue(resultult);
    }

    @Test void CheckAddWronglySell_ShouldReturnFalse ()
    {
        boolean result = sellController.AddSell(null);
        assertFalse(result);
    }

    @Test void CheckAddGetSell_ShouldReturnProperSell ()
    {
        dbMock.AddSell(sell);
        Sell result = sellController.GetSellById(0);

        assertThat(result).isEqualTo(sell);
    }

    @Test void CheckGetSell_SellDoesNotExist ()
    {
        dbMock.AddSell(sell);
        Sell result = sellController.GetSellById(2);

        assertThat(result).isEqualTo(null);
    }

    @Test void CheckDeleteSell_ShouldDeleteReturnTrue ()
    {
        dbMock.AddSell(sell);
        assertTrue(sellController.DeleteSell(sell));
    }

    @Test void CheckDeleteSell_SellDoesNotExists_ShouldReturnFalse ()
    {
        dbMock.AddSell(sell);
        Sell ord = new Sell(0);
        assertFalse(sellController.DeleteSell(ord));
    }

    @Test void CheckGetClientSells_SellsDoesNotExists_ShouldReturnEmptyList ()
    {
        dbMock.AddClient(client);
        assertThat(sellController.GetClientSells(client)).isEmpty();
    }

    @Test void CheckGetClientSells_ShouldReturnProperValue ()
    {
        dbMock.AddClient(client);
        sellController.AddSell(sell);
        sellController.AddSell(sell);
        sellController.AddSell(sell);
        sellController.AddSell(sell);

        assertThat(sellController.GetClientSells(client)).hasSize(4).contains(sell);
    }

    @Test void CheckGetClientSells_TwoClients_ShouldReturnProperValue ()
    {
        Client client2 = new Client(CLIENT_NAME, CLIENT_SURNAME, CLIENT_EMAIL);
        Sell sell2 = new Sell( 1);

        dbMock.AddClient(client);
        dbMock.AddClient(client2);

        sellController.AddSell(sell);
        sellController.AddSell(sell);
        sellController.AddSell(sell2);
        sellController.AddSell(sell);
        sellController.AddSell(sell2);
        sellController.AddSell(sell);
        sellController.AddSell(sell2);
        sellController.AddSell(sell);

        assertThat(sellController.GetClientSells(client)).hasSize(5).contains(sell);
        assertThat(sellController.GetClientSells(client2)).hasSize(3).contains(sell2);
    }

    @Test void CheckCreateSell_ShouldCreate ()
    {
        Product product = new Product(PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_QUANTITY);
        dbMock.AddProduct(product);
        dbMock.AddSell(sell);

        assertThat(sellController.CreateSell(product, sell)).isTrue();
    }

    @Test void CheckCreateSell_ProductDoesNotExists_ShouldReturnFalse ()
    {
        Product product = new Product(PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_QUANTITY);
        dbMock.AddSell(sell);

        assertThat(sellController.CreateSell(product, sell)).isFalse();
    }

    @Test void CheckGetProductsBySell_SellDoesNotExists_ShouldReturnEmptyList ()
    {
        Product product = new Product(PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_QUANTITY);
        dbMock.AddProduct(product);
        dbMock.AddSell(sell);

        assertThat(sellController.GetProductSells(sell)).isEmpty();
    }

    @Test void CheckGetProductsBySell_ShouldReturnProperList ()
    {
        Product product = new Product(PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_QUANTITY);
        Product product2 = new Product(PRODUCT_NAME2, PRODUCT_PRICE2, PRODUCT_QUANTITY2);
        Product product3 = new Product(PRODUCT_NAME3, PRODUCT_PRICE3, PRODUCT_QUANTITY3);

        dbMock.AddSell(sell);
        dbMock.AddProduct(product);
        dbMock.AddProduct(product2);
        dbMock.AddProduct(product3);

        sellController.CreateSell(product, sell);
        sellController.CreateSell(product2, sell);
        sellController.CreateSell(product3, sell);

        assertThat(sellController.GetProductSells(sell)).hasSize(3).containsOnly(product, product2, product3);
    }

    @Test void CheckGetProductsBySell_FewSells_ShouldReturnProperList ()
    {
        Product product = new Product(PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_QUANTITY);
        Product product2 = new Product(PRODUCT_NAME2, PRODUCT_PRICE2, PRODUCT_QUANTITY2);
        Product product3 = new Product(PRODUCT_NAME3, PRODUCT_PRICE3, PRODUCT_QUANTITY3);
        Sell sell2 = new Sell(1);
        Sell sell3 = new Sell(2);

        dbMock.AddSell(sell);
        dbMock.AddSell(sell2);
        dbMock.AddSell(sell3);
        dbMock.AddProduct(product);
        dbMock.AddProduct(product2);
        dbMock.AddProduct(product3);

        sellController.CreateSell(product, sell);
        sellController.CreateSell(product2, sell);
        sellController.CreateSell(product3, sell);

        sellController.CreateSell(product, sell2);
        sellController.CreateSell(product, sell2);
        sellController.CreateSell(product, sell2);

        sellController.CreateSell(product2, sell3);
        sellController.CreateSell(product3, sell3);

        assertThat(sellController.GetProductSells(sell)).hasSize(8).contains(product, product2, product3);
        assertThat(sellController.GetProductSells(sell2)).hasSize(8).contains(product, product, product);
        assertThat(sellController.GetProductSells(sell3)).hasSize(8).contains(product2, product3);
    }

    @Test
    void CheckAddClient_ShouldNotAddNullClient()
    {
        boolean result = dbMock.AddClient(null);
        assertFalse(result);
    }

    @Test
    void CheckGetClientByName_ShouldReturnProperValue()
    {
        dbMock.AddClient(client);
        assertThat(dbMock.GetClientByName(CLIENT_NAME).equals(client));
    }


}
