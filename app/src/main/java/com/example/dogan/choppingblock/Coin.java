package com.example.dogan.choppingblock;

public class Coin
{
    String name;
    String id;
    String price;
    String marketcap;
    String volume;
    String supply;
    String change;

    public Coin(String nameIn, String idIn, String marketcapIn, String priceIn, String volumeIn, String supplyIn, String changeIn)
    {
        name = nameIn;
        id = idIn;
        marketcap = marketcapIn;
        price = priceIn;
        volume = volumeIn;
        supply = supplyIn;
        change = changeIn;
    }
    public String getDetails()
    {
        return "Name: " + name + "  ID: " + id + "  Price: " + price + "  Market Cap: " + marketcap + "  Volume: " + volume + "  Circulating Supply: " + supply + " units  24-Hour Change: " + change;
    }
}
