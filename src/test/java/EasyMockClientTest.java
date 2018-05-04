import Controllers.ClientController;
import Interfaces.IClientRepository;
import Models.Client;
import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.getCurrentArguments;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EasyMockClientTest extends EasyMockSupport
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

    private Client client;

    @Mock
    private IClientRepository dbMock;

    @TestSubject
    private ClientController clientController;

    @BeforeEach
    void SetUp()
    {
        dbMock = createMock(IClientRepository.class);
        clientController = new ClientController(dbMock);
        client = new Client(CLIENT_NAME, CLIENT_SURNAME, CLIENT_EMAIL);
    }

    @Test
    void CheckAddClient_ShouldAddNewClient()
    {
        expect(dbMock.AddClient(client)).andReturn(true);
        replay(dbMock);

        clientController.AddClient(client);
        verify(dbMock);
    }

    @Test void CheckAddCLient_ShouldNotAddNewCLient ()
    {
        expect(dbMock.AddClient(client)).andReturn(false);
        replay(dbMock);

        clientController.AddClient(client);
        verify(dbMock);
    }

    @Test void CheckAddWronglyClient_ShouldThrownException ()
    {
        expect(dbMock.AddClient(client)).andThrow(new IllegalArgumentException("Wrong!"));
        replay(dbMock);

        assertThrows(IllegalArgumentException.class, () ->
        {
            clientController.AddClient(client);
        });
        verify(dbMock);
    }

    @Test void CheckAddMultipleClients_ShouldReturnProperCount ()
    {
        Client client2 = new Client(CLIENT_NAME2, CLIENT_SURNAME2, CLIENT_EMAIL2);
        Client client3 = new Client(CLIENT_NAME3, CLIENT_SURNAME3, CLIENT_EMAIL3);

        List<Client> clients = new ArrayList<>(Arrays.asList(new Client[]{client, client2, client3}));

        expect(dbMock.AddClient(client)).andReturn(true);
        expect(dbMock.AddClient(client2)).andReturn(true);
        expect(dbMock.AddClient(client3)).andReturn(true);
        replay(dbMock);

        clientController.AddClients(clients);
        verify(dbMock);
    }

    @Test void CheckAddMultipleClients_ShouldNotAddEveryClient ()
    {
        Client client2 = new Client(CLIENT_NAME2, CLIENT_SURNAME2, CLIENT_EMAIL2);
        Client client3 = new Client(CLIENT_NAME3, CLIENT_SURNAME3, CLIENT_EMAIL3);

        List<Client> clients = new ArrayList<>(Arrays.asList(new Client[]{client, client2, client2, client3}));

        expect(dbMock.AddClient(client)).andReturn(true);
        expect(dbMock.AddClient(client2)).andReturn(false).times(2);
        expect(dbMock.AddClient(client3)).andReturn(true);
        replay(dbMock);

        clientController.AddClients(clients);
        verify(dbMock);
    }

    @Test void CheckGetClientByName_ShouldReturnProperClient ()
    {
        expect(dbMock.GetClientByName(CLIENT_NAME)).andReturn(client);
        replay(dbMock);

        clientController.GetClientByName(CLIENT_NAME);
        verify(dbMock);
    }

    @Test void CheckGetAllClient_EmptyDB_ShouldReturnEmptyList ()
    {
        expect(dbMock.GetAllClients()).andReturn(new ArrayList<>());
        replay(dbMock);

        clientController.GetAllClients();
        verify(dbMock);
    }

    @Test void CheckGetAllClients_ShouldReturnProperValue ()
    {
        Client client2 = new Client(CLIENT_NAME2, CLIENT_SURNAME2, CLIENT_EMAIL2);
        Client client3 = new Client(CLIENT_NAME3, CLIENT_SURNAME3, CLIENT_EMAIL3);

        List<Client> clients = new ArrayList<>(Arrays.asList(new Client[]{client, client2, client2, client3}));

        expect(dbMock.GetAllClients()).andReturn(clients);
        replay(dbMock);

        assertThat(clientController.GetAllClients()).hasSize(4).containsOnly(client, client2, client3);
        verify(dbMock);
    }

    @Test void CheckDeleteClient_ShouldDeleteClient ()
    {
        expect(dbMock.DeleteClient(client)).andReturn(true);
        replay(dbMock);

        clientController.DeleteClient(client);
        verify(dbMock);
    }

    @Test void CheckDeleteClient_ClientDontExists_ShouldReturnFalse ()
    {
        expect(dbMock.DeleteClient(client)).andReturn(true);
        replay(dbMock);

        clientController.DeleteClient(client);
        verify(dbMock);
    }

    @Test void CheckEditClientName_ShouldEdit ()
    {
        dbMock.EditClientName(eq(NEW_NAME), eq(client));
        EasyMock.expectLastCall().andAnswer(() ->
        {
            Object[] args = getCurrentArguments();
            if (args.length >1)
            {
                String name = (String)args[0];
                Client _client = (Client)args[1];
                _client.Name = name;
            }
            return null;
        });
        replay(dbMock);

        clientController.EditClientName(NEW_NAME, client);
        assertEquals(NEW_NAME, client.Name);
        verify(dbMock);
    }

    @Test void CheckEditClientSurnname_ShouldEdit()
    {
        dbMock.EditClientSurname(eq(NEW_NAME), eq(client));
        EasyMock.expectLastCall().andAnswer(() ->
        {
            Object[] args = getCurrentArguments();
            if (args.length >1)
            {
                String name = (String)args[0];
                Client _client = (Client)args[1];
                _client.Name = name;
            }
            return null;
        });
        replay(dbMock);

        clientController.EditClientSurname(NEW_NAME, client);
        assertEquals(NEW_NAME, client.Name);
        verify(dbMock);
    }

    @Test void CheckEditClientEmail_ShouldEdit()
    {
        dbMock.EditClientEmail(eq(NEW_NAME), eq(client));
        EasyMock.expectLastCall().andAnswer(() ->
        {
            Object[] args = getCurrentArguments();
            if (args.length >1)
            {
                String name = (String)args[0];
                Client _client = (Client)args[1];
                _client.Name = name;
            }
            return null;
        });
        replay(dbMock);

        clientController.EditClientEmail(NEW_NAME, client);
        assertEquals(NEW_NAME, client.Name);
        verify(dbMock);
    }
}
