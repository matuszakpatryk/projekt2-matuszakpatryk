package Models;

public class Client
{
    public int ClientID;
    public String Name;
    public String Surname;
    public String Email;

    public Client(String name, String surname, String email)
    {
        Name = name;
        Surname = surname;
        Email = email;
    }
}
