package Controllers;

import Interfaces.IClientRepository;
import Models.Client;

import java.util.List;

public class ClientController implements IClientRepository
{
    IClientRepository _context;

    public ClientController(IClientRepository context)
    {
        _context = context;
    }

    public boolean AddClient (Client client)
    {
        return _context.AddClient(client);
    }

    public int AddClients (List<Client> clients)
    {
        int res = 0;
        for (Client client : clients)
        {
            if(_context.AddClient(client))
            {
                res++;
            }
        }
        return res;
    }

    public Client GetClientByName (String name)
    {
        return _context.GetClientByName(name);
    }

    public List<Client> GetAllClients ()
    {
        return _context.GetAllClients();
    }

    public boolean DeleteClient (Client client)
    {
        return _context.DeleteClient(client);
    }

    public void EditClientName (String name, Client client)
    {
        _context.EditClientName(name, client);
    }

    public void EditClientSurname (String surname, Client client)
    {
        _context.EditClientSurname(surname, client);
    }

    public void EditClientEmail (String email, Client client)
    {
        _context.EditClientEmail(email, client);
    }
}
