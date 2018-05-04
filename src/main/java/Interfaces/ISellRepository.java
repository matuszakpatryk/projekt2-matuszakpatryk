package Interfaces;

import Models.Sell;

public interface ISellRepository
{
    boolean AddSell(Sell sell);
    Sell GetSellById(int id);
    boolean DeleteSell(Sell sell);
}
