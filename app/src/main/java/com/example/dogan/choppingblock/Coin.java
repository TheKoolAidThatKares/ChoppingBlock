package com.example.dogan.choppingblock;

public class Coin
{
    String name;
    String id;
    String price;
    String volume;
    String change;

    public Coin(String nameIn, String idIn, String priceIn, String volumeIn, String changeIn)
    {
        name = nameIn;
        id = idIn;
        price = priceIn;
        volume = volumeIn;
        change = changeIn;
    }
}
