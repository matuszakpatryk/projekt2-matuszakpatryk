package Repository;
import Interfaces.IClientRepository;
import Interfaces.IDbContext;
import Models.Client;

import java.util.List;

public class ClientRepository implements IClientRepository
{
    IDbContext _iDbContext;
    public ClientRepository(IDbContext _iDbContext)
    {
        this._iDbContext = _iDbContext;
    }
    @Override
    public boolean AddClient(Client client)
    {
         return _iDbContext.AddClient(client);
    }

    @Override
    public Client GetClientByName(String name)
    {
        return _iDbContext.GetClientByName(name);
    }

    @Override
    public List<Client> GetAllClients()
    {
        return _iDbContext.GetAllClients();
    }

    @Override
    public boolean DeleteClient(Client client)
    {
        return _iDbContext.DeleteClient(client);
    }

    @Override
    public void EditClientName(String name, Client client)
    {
        _iDbContext.EditClientName(name, client);
    }

    @Override
    public void EditClientSurname(String surname, Client client)
    {
        _iDbContext.EditClientSurname(surname, client);
    }

    @Override
    public void EditClientEmail(String email, Client client)
    {
        _iDbContext.EditClientEmail(email, client);
    }
}
