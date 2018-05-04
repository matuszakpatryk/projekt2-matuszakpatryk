package Interfaces;

import Models.Client;

import java.util.List;

public interface IClientRepository
{
    boolean AddClient(Client client);
    Client GetClientByName(String name);
    List<Client> GetAllClients();
    boolean DeleteClient(Client client);
    void EditClientName(String name, Client client);
    void EditClientSurname(String surname, Client client);
    void EditClientEmail(String email, Client client);
}
